/**
 ***************************************
 *
 * @file SDCardTest.ino
 * @brief SD卡模块应用演示
 * 
 * 实验程序将演示使用BD_SDCard库读取SD卡信息与读/写SD卡内数据的方法。
 * 
 * @author gsh
 * @version 1.0
 * @date 2016.9.30
 *
 ***************************************
 */

#include <BD_SDCard.h>

BD_SDCard SDCard;

void setup()
{
	SDCard.Init();
  
	// Print card info.
	Serial.println("CardType is : " + SDCard.GetCardType());
	Serial.println("VolumeType is : Fat " + SDCard.GetVolumeType());
  
	Serial.println("VolumeSize is : " + (String)SDCard.GetVolumeSize() + " b");
	Serial.println("VolumeSize is : " + (String)SDCard.GetVolumeSize(UNIT_b) + " b");
	Serial.println("VolumeSize is : " + (String)SDCard.GetVolumeSize(UNIT_Kb) + " Kb");
	Serial.println("VolumeSize is : " + (String)SDCard.GetVolumeSize(UNIT_Mb) + " Mb");
  
	// Print the file dir.
	Serial.println("Files found on the card (name, date and size in bytes): ");
	Serial.println("===============================================================");
	SDCard.ShowList();
  
	//SDCard.ReadFile("hello.txt");
	//SDCard.WriteFile("hello.txt", "hello world!!!");
  
	//String cc = SDCard.ReadFile("hello.txt");
	String cc = SDCard.ReadFile("data.txt");

	// Print file content
	Serial.println("===============================================================");
	Serial.println(cc);

	//SDCard.WriteFile("data.txt", "www.geek-papa.com");
}

void loop()
{

}
