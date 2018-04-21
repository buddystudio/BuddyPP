/**
 *************************************************************************
 *
 * @file RGBLEDTest.ino
 * @brief 全彩LED模块应用演示
 * 
 * 实验程序将演示使用BD_RGBLED库控制RGBLED模块的方法。
 * 
 * @author gsh
 * @version 1.0
 * @date 2016.9.30
 *
 *************************************************************************
 */

#include<BD.h>
#include<BD_RGBLED.h>

BD_RGBLED rgbLED;

void setup()
{
	// R->D5, G->D9, B->D3
	rgbLED.Init(D5, D9, D3);
}

void loop()
{
	// Set color by three methods.
	rgbLED.SetColor(1, 0, 0);
	delay(1000);
	rgbLED.SetColor(0, 1, 0);
	delay(1000);
	rgbLED.SetColor(0, 0, 1);
	delay(1000);
	rgbLED.SetColor(1, 1, 0);
	delay(1000);
	rgbLED.SetColor(1, 0, 1);
	delay(1000);
	rgbLED.SetColor(0, 1, 1);
	delay(1000);
	rgbLED.SetColor(1, 1, 1);
	delay(1000);
	rgbLED.SetColor(1, 0, 1);

	//rgbLED.SetColor("#ff1020");
	//rgbLED.SetColorValue(255, 100, 10);

	/*
	for(int rr = 0; rr < 255; rr+=55)
	{
		for(int gg = 0; gg < 255; gg+=55)
		{
			for(int bb = 0; bb < 255; bb+=55)
			{
				rgbLED.SetColorValue(rr, gg, bb);
		   }
		}
   }
   */
   
}
