/**
 ***************************************
 *
 * @file Blink.ino
 * @brief LED模块实验
 * 
 * 实验程序为间歇闪灯，LED点亮一秒然后熄灭一秒，如此循环。
 * 
 * @author gsh
 * @version 1.0
 * @date 2015.12.1
 *
 ***************************************
 */

int ledPin = 13; // 定义变量ledPin指向数字端口D13

// 初始化
void setup()
{
	// 设置变量ledPin指向的端口为OUTPUT模式
	pinMode(ledPin, OUTPUT);
}

// 主循环
void loop()
{
	// 数字端口D13写入高电平，LED灯点亮
	digitalWrite(ledPin, HIGH);

	// 延时1秒
	delay(1000);
  
	// 数字端口D13写入低电平，LED灯熄灭
	digitalWrite(ledPin, LOW);

	// 延时1秒
	delay(1000);
}