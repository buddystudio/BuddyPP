
#include<BD.h>  
#include<BD_LEDMatrix.h>  
	  
BD_LEDMatrix ledm;  
	  
// 心形图案（需要修改的数据）  
byte img1[] = {0x0, 0x66, 0xff, 0xff, 0xff, 0x7e, 0x3c, 0x18};  
	  
void setup()   
{  
	// DIO->D9, CLK->D6, LE->D5  
	ledm.Init(D9, D6, D5);  
}  
  
void loop()   
{  
	ledm.Display(img1);
} 