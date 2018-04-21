/**
 *************************************************************************
 *
 * @file Buzzer.ino
 * @brief 蜂鸣器发声实验
 * 
 * 测试程序将演示蜂鸣器的发声控制，蜂鸣器响两秒停顿两秒，如此循环。
 * 
 * @author gsh
 * @version 1.0
 * @date 2015.12.1
 *
 *************************************************************************
 */

int buzzer = 3; // 定义变量buzzer指向数字端口D3

// 初始化
void setup()
{
	// 设置变量buzzer指向的端口为OUTPUT模式
	pinMode(buzzer, OUTPUT);
}

// 主循环
void loop()
{
	// 当数字端口D3写入高电平时蜂鸣器开始发声
	// 当数字端口D3写入低电平时蜂鸣器停止发声
	
	// 蜂鸣器开始发声
	digitalWrite(buzzer, HIGH);
  
	// 延时2秒
	delay(2000);
  
	// 蜂鸣器停止发声
	digitalWrite(buzzer, LOW);
  
	// 延时2秒
	delay(2000);
}
