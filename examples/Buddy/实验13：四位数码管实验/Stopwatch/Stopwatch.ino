/**
 ***************************************
 *
 * @file StopWatch.ino
 * @brief 秒表显示实验
 * 
 * 本次实验用于演示四位数码管模块的使用方法，四位数码管模块采用共阳数码显示管。
 * 测试程序将会演示如何使用四位数码管模块显示一个计时秒表。
 *
 * @author gsh
 * @version 1.0
 * @date 2015.12.25
 *
 ***************************************
 */
 
#include <EEPROM.h>
#include <TimerOne.h>
#include <avr/pgmspace.h>
#include "TM1637.h"
#define ON 1
#define OFF 0

int8_t TimeDisp[] = {0x00,0x00,0x00,0x00};

unsigned char ClockPoint = 1;
unsigned char Update;
unsigned char microsecond_10 = 0;
unsigned char second;
unsigned char _microsecond_10 = 0;
unsigned char _second;
unsigned int eepromaddr;

boolean Flag_ReadTime;

#define CLK 3	// 数字端口D3连接模块CLK端口      
#define DIO 2	// 数字端口D2连接模块DIO端口

TM1637 tm1637(CLK,DIO);

// 初始化
void setup()
{
	Serial.begin(9600);
	tm1637.set(BRIGHT_TYPICAL);//BRIGHT_TYPICAL = 2,BRIGHT_DARKEST = 0,BRIGHTEST = 7;
	tm1637.init();
	Timer1.initialize(10000);//timing for 10ms
	Timer1.attachInterrupt(TimingISR);//declare the interrupt serve routine:TimingISR  
	
	// 在串口串口中显示操作
	Serial.println("Please send command to control the stopwatch:");
	Serial.println("S - start");
	Serial.println("P - pause");
	Serial.println("L - list the time");
	Serial.println("W - write the time to EEPROM ");
	Serial.println("R - reset");
}

// 主循环
void loop()
{
	char command;
  
	// 获取串口输入的命令
	command = Serial.read();
  
	// 根据命令执行不同的操作
	switch(command)
	{
		case 'S':stopwatchStart();Serial.println("Start timing...");break;
		case 'P':stopwatchPause();Serial.println("Stopwatch was paused");break;
		case 'L':readTime();break;
		case 'W':saveTime();Serial.println("Save the time");break;
		case 'R':stopwatchReset();Serial.println("Stopwatch was reset");break;

		default:break;
	}
	
	// 更新并显示时间
	if(Update == ON)
	{
		TimeUpdate();
		tm1637.display(TimeDisp);
	}
}

//************************************************

void TimingISR()
{
  microsecond_10 ++;
  Update = ON;
  if(microsecond_10 == 100){
    second ++;
    if(second == 60)
    {
      second = 0;
    }
    microsecond_10 = 0;  
  }
  ClockPoint = (~ClockPoint) & 0x01;
  if(Flag_ReadTime == 0)
  {
    _microsecond_10 = microsecond_10;
    _second = second;
  }
}
void TimeUpdate(void)
{
  if(ClockPoint)tm1637.point(POINT_ON);//POINT_ON = 1,POINT_OFF = 0;
  else tm1637.point(POINT_ON); 
  TimeDisp[2] = _microsecond_10 / 10;
  TimeDisp[3] = _microsecond_10 % 10;
  TimeDisp[0] = _second / 10;
  TimeDisp[1] = _second % 10;
  Update = OFF;
}
void stopwatchStart()//timer1 on
{
  Flag_ReadTime = 0;
  TCCR1B |= Timer1.clockSelectBits; 
}
void stopwatchPause()//timer1 off if [CS12 CS11 CS10] is [0 0 0].
{
  TCCR1B &= ~(_BV(CS10) | _BV(CS11) | _BV(CS12));
}
void stopwatchReset()
{
  stopwatchPause();
  Flag_ReadTime = 0;
  _microsecond_10 = 0;
  _second = 0;
  microsecond_10 = 0;
  second = 0;
  Update = ON;
}
void saveTime()
{
  EEPROM.write(eepromaddr ++,microsecond_10);
  EEPROM.write(eepromaddr ++,second);
}
void readTime()
{
  Flag_ReadTime = 1;
  if(eepromaddr == 0)
  {
    Serial.println("The time had been read");
    _microsecond_10 = 0;
    _second = 0;
  }
  else{
  _second = EEPROM.read(-- eepromaddr);
  _microsecond_10 = EEPROM.read(-- eepromaddr);
  Serial.println("List the time");
  }
  Update = ON;
}