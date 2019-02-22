	
#include<BD.h>  
#include<BD_LED.h>  
	  
BD_LED led;  
	  
int value = 0;  // 暂存按钮状态  
	  
void setup()  
{  
	// 设置串口通讯波特率为9600  
	Serial.begin(9600);  
	      
	led.Init(9);  
	    
	// 磁力电子积木添加这行  
	pinMode(5, OUTPUT);  
	pinMode(A5, INPUT_PULLUP);  
}  
	  
void loop()  
{  
	// 读取A5端口电平状态  
	value = analogRead(A5);  
	      
	Serial.println(value);  
	  
	// 根据电位器读数调整LED灯亮度  
	analogWrite(9, value);  
	  
	delay(100);  
}  