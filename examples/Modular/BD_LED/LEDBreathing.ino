/**
 *************************************************************************
 *
 * @file LEDBreathing.ino
 * @brief LED呼吸灯功能演示
 * 
 * 实验程序将演示使用BD_LED库调用呼吸灯功能的方法。
 * 
 * @author gsh
 * @version 1.0
 * @date 2016.9.30
 *
 *************************************************************************
 */
 
#include<BD.h>
#include<BD_LED.h>

BD_LED led;

void setup()
{
	led.Init(D13);
}

void loop()
{
	led.FadeIn(10);
	delay(1000);
	led.FadeOut(10);
	delay(1000);
}

