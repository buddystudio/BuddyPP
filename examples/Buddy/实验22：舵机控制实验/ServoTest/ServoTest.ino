/**
 *************************************************************************
 *
 * @file ServoTest.ino
 * @brief 舵机转动实验
 * 
 * 本次实验用于验证舵机的正常使用和演示舵机的控制方法，舵机共有三线，红线
 * 为VCC连接开发板5V端口，黑线为地线连接开发板GND端口，黄线为信号线连接开
 * 发板数字端口。
 *
 * @author gsh
 * @version 1.0
 * @date 2015.12.25
 *
 *************************************************************************
 */

#include <Servo.h>

Servo myservo;  // 声明一个伺服电机对象

int val = 0;    // 声明一个命名为val的变量用于存储舵机转量

// 初始化
void setup()
{
	// 通过D9端口输出舵机控制信号（仅支持PWM输出端口）
	myservo.attach(9);  
}

// 主循环
void loop()
{
	if(val > 1024)
	{
		val = 0;
	}
  
	// 利用map()函数把0-1023之间的数值映射到0-179的角度数值
	int ang = map(val, 0, 1023, 0, 179);     
	
	// 设定舵机旋转的的位置
	myservo.write(ang);     
	
	// 每次旋转10度
	val += 10;
	
	// 延时等待电机旋转到目标角度
	delay(15);
}
