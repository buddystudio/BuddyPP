/*
  ReadAndWrite
 Read the serial number of the IC card.
 
 TIDY by YFRobot < http://www.yfrobot.com >
 */

#include <SPI.h>
#include <RFID.h>

//D10 - 读卡器CS引脚、D5 - 读卡器RST引脚
RFID rfid(10,5);   

//4字节卡序列号，第5字节为校验字节
unsigned char serNum[5];
unsigned char status;
unsigned char str[MAX_LEN];
unsigned char blockAddr;        //选择操作的块地址0～63

//写卡数据，想要写的数据（16个字节内）
unsigned char writeDate[16] ={
  'W', 'e', 'l', 'c', 'o', 'm', 'e', ' ', 'Y', 'F', 'R', 'o', 'b', 'o', 't', 0};

//原扇区A密码，16个扇区，每个扇区密码6Byte
unsigned char sectorKeyA[16][16] = {
  {  0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF  } ,
  {  0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF  } ,
  {  0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF  } ,
  {  0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF  } ,
  {  0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF  } ,
  {  0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF  } ,
  {  0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF  } ,
  {  0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF  } ,
  {  0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF  } ,
  {  0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF  } ,
  {  0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF  } ,
  {  0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF  } ,
  {  0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF  } ,
  {  0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF  } ,
  {  0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF  } ,
  {  0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF  } ,};

void setup()
{
  Serial.begin(9600);
  SPI.begin();
  rfid.init();
}

void loop()
{
  //找卡
  rfid.findCard(PICC_REQIDL, str);
    //防冲突检测,读取卡序列号
    if (rfid.anticoll(str) == MI_OK){
      
      Serial.print("The card's number is  : ");
      //显示卡序列号
      for(int i = 0; i < 4; i++){
        Serial.print(0x0F & (str[i] >> 4),HEX);
        Serial.print(0x0F & str[i],HEX);
      }
      Serial.println("");
      memcpy(rfid.serNum,str,5);
    }
  //选卡，返回卡容量（锁定卡片，防止多次读写）
  rfid.selectTag(rfid.serNum);

  //向卡数据块中写数据（16字节内）,这里以数据块4为例
  writeCard(4);
  //读数据块中数据，这里以数据块4为例
  readCard(4);
 
  rfid.halt();
} 

//写数据卡
void writeCard(int blockAddr){
  
  if (rfid.auth(PICC_AUTHENT1A, blockAddr, sectorKeyA[blockAddr/4], rfid.serNum) == MI_OK)  //认证
  {
    //写数据
    //status = rfid.write((blockAddr/4 + 3*(blockAddr/4+1)), sectorKeyA[0]);
    Serial.print("set the new card password, and can modify the data of the Sector: ");
    Serial.println(blockAddr/4,DEC);
    ////选择扇区中的块写数据
    if(rfid.write(blockAddr, writeDate) == MI_OK){
      Serial.println("Write card OK!");
    }
  }
}

//读卡
void readCard(int blockAddr){
 
  if ( rfid.auth(PICC_AUTHENT1A, blockAddr, sectorKeyA[blockAddr/4], rfid.serNum) == MI_OK)  //认证
  {
    //选择扇区中的块读数据
    Serial.print("Read from the blockAddr of the card : ");
    Serial.println(blockAddr,DEC);
    if( rfid.read(blockAddr, str) == MI_OK){
      Serial.print("The data is : ");
      Serial.println((char *)str);
    }
  }
}
