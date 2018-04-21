/**
 *************************************************************************
 *
 * @file LEDs.ino
 * @brief 8x8 LED点阵模块实验
 * 
 * 实验程序演示8x8 LED点阵模块的使用方法，通过点阵模块显示心形图案。
 * 
 * @author gsh
 * @version 1.0
 * @date 2015.12.28
 *
 *************************************************************************
 */

const int DINPin = 2;	// 数据引脚 连接到数字D2端口
const int CLKPin = 4;	// 时钟引脚 连接到数字D4端口
const int LTHPin = 7;	// 锁存引脚 连接到数字D7端口

byte scan[8] = {0xFE, 0xFD, 0xFB, 0xF7, 0xEF, 0xDF, 0xBF, 0x7F};

// 心形图案（需要修改的数据）
byte img[] = {0x0, 0x66, 0xff, 0xff, 0xff, 0x7e, 0x3c, 0x18};

void setup() 
{
	pinMode(DINPin,OUTPUT);		// 设置数据引脚为输出
	pinMode(CLKPin,OUTPUT);		// 设置锁存引脚为输出
	pinMode(LTHPin,OUTPUT);		// 设置时钟引脚为输出
}

void loop() 
{
	for(char x = 0; x < 8; x++) 
	{
		// 显示图形
		shiftOut(DINPin, CLKPin, MSBFIRST, scan[x]);
		shiftOut(DINPin, CLKPin, MSBFIRST, img[x]);
    
		// 刷新显示
		digitalWrite(LTHPin, LOW); 
		digitalWrite(LTHPin, HIGH);
	}
}
