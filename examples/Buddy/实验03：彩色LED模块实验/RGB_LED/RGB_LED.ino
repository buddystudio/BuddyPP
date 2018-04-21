/**
 *************************************************************************
 *
 * @file RGB_LED.ino
 * @brief 彩色LED模块实验
 * 
 * 测试程序为彩色循环闪灯，彩色LED灯将每两秒换一种颜色，如此循环。
 * 
 * @author gsh
 * @version 1.0
 * @date 2015.12.1
 *
 *************************************************************************
 */

int pinR = 8;	// 定义变量ledR指向数字端口D8
int pinG = 9;	// 定义变量ledR指向数字端口D9
int pinB = 10;	// 定义变量ledR指向数字端口D10

void setup()
{
	// 设置端口模式为OUTPUT
	pinMode(pinR, OUTPUT);
	pinMode(pinG, OUTPUT);
	pinMode(pinB, OUTPUT);
}

void loop()
{
	// 显示红色（R=0,G=1,B=1）
	digitalWrite(pinR, LOW);
	digitalWrite(pinG, HIGH);
	digitalWrite(pinB, HIGH);
  
	// 延时2秒
	delay(2000);
  
	// 显示绿色（R=1,G=0,B=1）
	digitalWrite(pinR, HIGH);
	digitalWrite(pinG, LOW);
	digitalWrite(pinB, HIGH);

	// 延时2秒
	delay(2000);
  
	// 显示蓝色（R=1,G=1,B=0）
	digitalWrite(pinR, HIGH);
	digitalWrite(pinG, HIGH);
	digitalWrite(pinB, LOW);
 
	// 延时2秒
	delay(2000);
  
	// 显示紫色（R=0,G=1,B=0）
	digitalWrite(pinR, LOW);
	digitalWrite(pinG, HIGH);
	digitalWrite(pinB, LOW);
 
	// 延时2秒
	delay(2000);
  
	// 显示黄色（R=1,G=0,B=0）
	digitalWrite(pinR, HIGH);
	digitalWrite(pinG, LOW);
	digitalWrite(pinB, LOW);
 
	// 延时2秒
	delay(2000);
  
	// 显示青色（R=0,G=0,B=1）
	digitalWrite(pinR, LOW);
	digitalWrite(pinG, LOW);
	digitalWrite(pinB, HIGH);
 
	// 延时2秒
	delay(2000);
  
	// 显示白色（R=0,G=0,B=0）
	digitalWrite(pinR, LOW);
	digitalWrite(pinG, LOW);
	digitalWrite(pinB, LOW);

	// 延时2秒
	delay(2000);
	
	// 熄灭（R=1,G=1,B=1）
	digitalWrite(pinR, HIGH);
	digitalWrite(pinG, HIGH);
	digitalWrite(pinB, HIGH);

	// 延时2秒
	delay(2000);

}
