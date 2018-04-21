/**
 *************************************************************************
 *
 * @file ClockDisplay.ino
 * @brief 时间显示实验
 * 
 * 本次实验用于演示四位数码管模块的使用方法，四位数码管模块采用共阳数码显示管。
 * 测试程序将会演示如何使用四位数码管模块显示时间并动态更新。
 *
 * @author gsh
 * @version 1.0
 * @date 2015.12.25
 *
 *************************************************************************
 */
 
#include <TimerOne.h>
#include "TM1637.h"
#define ON 1
#define OFF 0

int8_t TimeDisp[] = {0x00, 0x00, 0x00, 0x00};

unsigned char ClockPoint = 1;
unsigned char Update;
unsigned char halfsecond = 0;
unsigned char second;
unsigned char minute = 0;
unsigned char hour = 12;


#define CLK 3	// D3端口接CLK 
#define DIO 2	// D2端口接DIO

TM1637 tm1637(CLK, DIO);

// 初始化
void setup()
{
	// TM1637驱动程序初始化
	tm1637.set();
	tm1637.init();
	
	// 更新频率设为500ms
	Timer1.initialize(500000); 
	
	// 声明中断事件 
	Timer1.attachInterrupt(TimingISR); 
}

// 主循环
void loop()
{
	if(Update == ON)
	{
		// 更新当前时间并显示
		TimeUpdate();
		
		tm1637.display(TimeDisp);
	}
  
}

// 更新时间
void TimingISR()
{
	halfsecond ++;
	
	Update = ON;
	
	if(halfsecond == 2)
	{
		second ++;
    
		// 计算秒
		if(second == 60)
		{
			minute ++;
      
			// 计算分
			if(minute == 60)
			{
				hour ++;
				
				// 计算时
				if(hour == 24)hour = 0;
				minute = 0;
			}
			
			second = 0;
		}
		
		halfsecond = 0;  
	}
  
	// Serial.println(second);
	ClockPoint = (~ClockPoint) & 0x01;
}

// 显示时间
void TimeUpdate(void)
{
	if(ClockPoint)tm1637.point(POINT_ON);
	else tm1637.point(POINT_OFF); 
	
	// 设定每个数码管显示的内容
	TimeDisp[0] = hour / 10;
	TimeDisp[1] = hour % 10;
	TimeDisp[2] = minute / 10;
	TimeDisp[3] = minute % 10;
	
	Update = OFF;
}