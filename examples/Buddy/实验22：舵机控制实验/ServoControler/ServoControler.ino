/**
 *************************************************************************
 *
 * @file ServoControler.ino
 * @brief 舵机控制实验
 * 
 * 本次实验用于演示舵机的控制方法，我们可以通过一个电位器模块使用旋钮控
 * 制舵机实时的旋转角度。
 *
 * @author gsh
 * @version 1.0
 * @date 2015.12.25
 *
 *************************************************************************
 */

#include <Servo.h>

#define potpin A5 // 电位器模块的SIG引脚连接开发板A5端口

Servo myservo; // 声明一个舵机对象

int val; // 声明一个变量用于存储电位器的读数

// 初始化
void setup()
{
	// 通过D9端口输出舵机控制信号（仅支持PWM输出端口）
	myservo.attach(9);
}

// 主循环
void loop()
{
	// 读取电位器读数
	val = analogRead(potpin);
  
	// 显示电位器读数
	Serial.println(val);
	
	// 利用map()函数把0-1023之间的数值映射到0-179的角度数值
	val = map(val, 0, 1023, 0, 179);
	
	// 设定舵机旋转的的位置
	myservo.write(val);
	
	// 延时等待电机旋转到目标角度
	delay(15);
}
