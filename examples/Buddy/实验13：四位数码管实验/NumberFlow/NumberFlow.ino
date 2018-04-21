/**
 *************************************************************************
 *
 * @file NumberFlow.ino
 * @brief 数字流水灯显示实验
 * 
 * 本次实验用于演示四位数码管模块的使用方法，四位数码管模块采用共阳数码显示管。
 * 测试程序将会演示如何使用四位数码管实现一个数字/字母流水灯。
 *
 * @author gsh
 * @version 1.0
 * @date 2015.12.25
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
	tm1637.init();
	
	// 设置数码管光亮度
	// BRIGHT_TYPICAL = 2
	// BRIGHT_DARKEST = 0
	// BRIGHTEST = 7
	tm1637.set(BRIGHT_TYPICAL);
}

// 主循环
void loop()
{
	int8_t NumTab[] = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};//0~9,A,b,C,d,E,F
	int8_t ListDisp[4];
	
	unsigned char i = 0;
	unsigned char count = 0;
	
	// 延时150ms
	delay(150);
	
	while(1)
	{
		i = count;
		
		count ++;
    
		if(count == sizeof(NumTab)) count = 0;
    
		// 显示所有数字和字母组合
		for(unsigned char BitSelect = 0; BitSelect < 4; BitSelect++)
		{
			ListDisp[BitSelect] = NumTab[i];
			
			i++;

			if(i == sizeof(NumTab)) i = 0;
		}
		
		// 设置显示的内容
		tm1637.display(0, ListDisp[0]);
		tm1637.display(1, ListDisp[1]); 
		tm1637.display(2, ListDisp[2]);
		tm1637.display(3, ListDisp[3]);
		
		// 延时300ms
		delay(300);
	}
}