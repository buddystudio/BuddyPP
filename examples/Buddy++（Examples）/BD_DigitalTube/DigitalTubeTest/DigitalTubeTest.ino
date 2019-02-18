/**
 ***************************************
 *
 * @file DigitalTubeTest.ino
 * @brief 四位数码管模块应用演示
 * 
 * 实验程序将演示使用BD_DigitalTube库控制四位数码管模块的方法。
 * 
 * @author gsh
 * @version 1.0
 * @date 2016.9.30
 *
 ***************************************
 */

#include<BD.h>
#include<BD_DigitalTube.h>

BD_DigitalTube digitalTube;

void setup() 
{
	Serial.begin(9600);
  
	// DIO->D2, CLK->D3
	digitalTube.Init(D2, D3);
}

void loop() 
{
	// 点亮中间分割点
	digitalTube.SetPoint(1);
	
	// 显示4位数值
	digitalTube.Display(1990);
	delay(1000);
	
	// 关闭中间分割点
	digitalTube.SetPoint(0);
	
	// 显示4位数值
	digitalTube.Display(2016);
	delay(1000);
	
	// 点亮中间分割点
	digitalTube.SetPoint(1);
	
	// 逐位显示数字
	digitalTube.Display(0, 8);
	digitalTube.Display(1, 7);
	digitalTube.Display(2, 6);
	digitalTube.Display(3, 5);
	delay(3000);
}
