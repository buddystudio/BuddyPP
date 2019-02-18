/**
 ***************************************
 *
 * @file LEDLighting.ino
 * @brief LED控制演示程序
 * 
 * 实验程序将演示使用BD_LED库控制LED模块的方法。
 * 
 * @author gsh
 * @version 1.0
 * @date 2016.9.30
 *
 ***************************************
 */

#include<BD.h>
#include<BD_LED.h>

BD_LED led;

void setup()
{
  led.Init(D9);
}

void loop()
{
	led.LightUp(1);
	delay(1000);
	led.LightUp(0);
	delay(1000);
}