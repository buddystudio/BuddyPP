/*
  SerialNumberRead
 Read the serial number of the IC card.
 
 TIDY by YFRobot < http://www.yfrobot.com >
 */

#include <SPI.h>
#include <RFID.h>

//D10 - 读卡器CS引脚、D5 - 读卡器RST引脚
RFID rfid(10,5);   
unsigned char status;
unsigned char str[MAX_LEN];  //MAX_LEN为16，数组最大长度

void setup()
{
  Serial.begin(9600);
  SPI.begin();
  rfid.init(); //初始化
}

void loop()
{
  //Search card, return card types
  if (rfid.findCard(PICC_REQIDL, str) == MI_OK) {
    Serial.println("Find the card!");
    // Show card type
    ShowCardType(str);
    //防冲突检测,读取卡序列号
    if (rfid.anticoll(str) == MI_OK) {
      Serial.print("The card's number is  : ");
      //显示卡序列号
      for(int i = 0; i < 4; i++){
        Serial.print(0x0F & (str[i] >> 4),HEX);
        Serial.print(0x0F & str[i],HEX);
      }
      Serial.println("");
    }
    //选卡（锁定卡片，防止多数读取，去掉本行将连续读卡）
    rfid.selectTag(str);
  }
  rfid.halt();  //命令卡片进入休眠状态
}

void ShowCardType(unsigned char * type)
{
  Serial.print("Card type: ");
  if(type[0]==0x04&&type[1]==0x00) 
    Serial.println("MFOne-S50");
  else if(type[0]==0x02&&type[1]==0x00)
    Serial.println("MFOne-S70");
  else if(type[0]==0x44&&type[1]==0x00)
    Serial.println("MF-UltraLight");
  else if(type[0]==0x08&&type[1]==0x00)
    Serial.println("MF-Pro");
  else if(type[0]==0x44&&type[1]==0x03)
    Serial.println("MF Desire");
  else
    Serial.println("Unknown");
}

