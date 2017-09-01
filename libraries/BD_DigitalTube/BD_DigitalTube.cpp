#include <Arduino.h> 
#include <BD_DigitalTube.h>

TM1637 tm1637;

void BD_DigitalTube::Init(uint8_t din, uint8_t clk)
{
	tm1637.setPin(clk, din);
	
	tm1637.init();
	
	tm1637.set(BRIGHT_TYPICAL);

}

void BD_DigitalTube::Display(int num)
{
	
	// 0~9, A, b, C, d, E, F
	int8_t NumTab[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
	int8_t ListDisp[4];
	
	unsigned char i = 0;
	unsigned char count = 0;
	
	// —” ±150ms
	delay(150);
  
	int x = num;
	
	int b[4];  
	
	b[0]=x%10;   
	b[1]=((x-b[0])%100)/10;   
	b[2]=((x-b[0]-b[1]*10)%1000)/100;  
	b[3]=(x-b[0]-b[1]*10-b[2]*100)/1000; 
	
	tm1637.display(0, b[3]);
	tm1637.display(1, b[2]);
	tm1637.display(2, b[1]);
	tm1637.display(3, b[0]);
	
}

void BD_DigitalTube::Display(uint8_t BitAddr,int8_t DispData)
{
	tm1637.display(BitAddr, DispData);
	
}

void BD_DigitalTube::SetPoint(boolean flag)
{
	if(flag)
	{
		tm1637.point(POINT_ON);
	}
	else
	{
		tm1637.point(POINT_OFF);
	}
}