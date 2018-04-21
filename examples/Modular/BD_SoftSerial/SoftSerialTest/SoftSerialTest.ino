/**
 *************************************************************************
 *
 * @file SoftSerialTest.ino
 * @brief 软串口通讯演示
 * 
 * 实验程序将演示软串口通讯的方法。
 * 
 * @author gsh
 * @version 1.0
 * @date 2016.9.30
 *
 *************************************************************************
 */

#include<SoftwareSerial.h>

//SoftwareSerial softSerial(3, 4);
SoftwareSerial softSerial(A4, A5);

void setup() 
{
	Serial.begin(9600);
  
	softSerial.begin(9600);
	softSerial.listen();

	delay(100);
}

void loop() 
{
	// Put your main code here, to run repeatedly:
	if(softSerial.available() > 0)
	{
		delay(100);

		char inchar = (char)softSerial.read();

		Serial.println(inchar);
	}
}
