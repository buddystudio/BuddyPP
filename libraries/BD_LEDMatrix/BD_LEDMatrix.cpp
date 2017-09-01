#include <Arduino.h> 
#include <BD_LEDMatrix.h>

void BD_LEDMatrix::Init(uint8_t din, uint8_t clk, uint8_t lth)
{
	DINPin = din;
	CLKPin = clk;
	LTHPin = lth;

	//scan[8] = {0xFE, 0xFD, 0xFB, 0xF7, 0xEF, 0xDF, 0xBF, 0x7F};
	
	scan[0] = 0xFE;
	scan[1] = 0xFD;
	scan[2] = 0xFB;
	scan[3] = 0xF7;
	scan[4] = 0xEF;
	scan[5] = 0xDF;
	scan[6] = 0xBF;
	scan[7] = 0x7F;
	
	pinMode(DINPin, OUTPUT);
	pinMode(CLKPin, OUTPUT);
	pinMode(LTHPin, OUTPUT);

}

void BD_LEDMatrix::Display(byte *img)
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

void BD_LEDMatrix::Display(byte *img, unsigned long delay)
{
	unsigned long curTime;
	unsigned long preTime;
		
	curTime = millis(); // 获取当前的系统运行时间长度
	
	while((millis() - curTime) < delay)
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
	
}