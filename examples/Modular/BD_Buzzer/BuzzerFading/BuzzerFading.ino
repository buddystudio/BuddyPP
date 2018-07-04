/**
 ***************************************
 *
 * @file BuzzerFading.ino
 * @brief 蜂鸣器变调演示程序
 * 
 * 实验程序将演示使用BD_Buzzer库控制蜂鸣器模块变调。
 * 
 * @author gsh
 * @version 1.0
 * @date 2016.9.30
 *
 ***************************************
 */
 
#include <BD.h>
#include <BD_Buzzer.h>

BD_Buzzer buzzer;

void setup()
{
	// SIG->D9
	buzzer.Init(D9);
}

void loop()
{ 
	buzzer.FadeIn(15);
	delay(1000);
	buzzer.FadeOut(15);
	delay(1000);
}
