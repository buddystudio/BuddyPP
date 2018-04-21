/**
 *************************************************************************
 *
 * @file JoystickTest.ino
 * @brief 游戏手柄模块应用演示
 * 
 * 实验程序将演示使用BD_Joystick库获取游戏手柄反馈数据的方法。
 * 
 * @author gsh
 * @version 1.0
 * @date 2016.9.30
 *
 *************************************************************************
 */

#include<BD.h>
#include<BD_Joystick.h>

BD_Joystick joystick;

void setup()
{
	Serial.begin(9600);
  
	// xPin->A0, yPin->A1, zPin->A2
	joystick.Init(A0, A1, A2);
}

void loop()
{
	//joystick.Read();
  
	// Accuracy 0-255
	joystick.ReadRevise(10);
  
	Serial.print("X: ");
	Serial.println(joystick.GetX());
	Serial.print("Y: ");
	Serial.println(joystick.GetY());
	Serial.print("Z: ");
	Serial.println(joystick.GetZ());
  
	delay(1000);
}
