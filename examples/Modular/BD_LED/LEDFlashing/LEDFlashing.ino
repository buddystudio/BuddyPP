/**
 ***************************************
 *
 * @file LEDFlashing.ino
 * @brief LED闪灯控制演示
 * 
 * 实验程序将演示使用BD_LED库控制LED模块进行闪烁的方法。
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
	led.Flash(500);
}
