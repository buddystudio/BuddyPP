	  
#include<BD.h>  
#include<BD_DigitalTube.h>  
	  
BD_DigitalTube digitalTube;  
	  
void setup()   
{  
	Serial.begin(9600);  
			
	// DIO->D2, CLK->D3  
	digitalTube.Init(D6, D5);  
			  
	pinMode(9, OUTPUT);  
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
