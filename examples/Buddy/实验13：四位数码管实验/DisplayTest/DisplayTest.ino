/**
 *************************************************************************
 *
 * @file DisplayTest.ino
 * @brief 四位数码管显示实验
 * 
 * 本次实验用于演示四位数码管模块的使用方法，四位数码管模块采用共阳数码显示管。
 * 测试程序将会演示如何使用四位数码管模块显示数字。
 *
 * @author gsh
 * @version 1.1
 * @date 2016.9.5
 *
 *************************************************************************
 */
 
#include "TM1637.h"

#define CLK 3	// 数字端口D3连接模块CLK端口      
#define DIO 2	// 数字端口D2连接模块DIO端口

TM1637 tm1637(CLK, DIO);

// 初始化
void setup()
{
	// 设定串口波特率为9600
	Serial.begin(9600);
  
	// 数码管模块初始化
	tm1637.init();
	
	// 设置数码管光亮度
	// BRIGHT_TYPICAL = 2
	// BRIGHT_DARKEST = 0
	// BRIGHTEST = 7
	tm1637.set(BRIGHT_TYPICAL);

	pinMode(8 , INPUT);   // sig
	pinMode(9 , OUTPUT);  // alarm
	pinMode(10 , OUTPUT); // reset
}


// 主循环
void loop()
{
	// 0~9, A, b, C, d, E, F
	int8_t NumTab[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
	int8_t ListDisp[4];
	
	// 延时150ms
	delay(150);
  
	// 设置显示的内容
	tm1637.display(0, 1);
	tm1637.display(1, 2); 
	tm1637.display(2, 3);
	tm1637.display(3, 4);

}
