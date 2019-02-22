
#include<BD.h>  
#include<BD_RGBLED.h>  
	  
BD_RGBLED rgbLED;  
	  
void setup()  
{  
	// R->D5, G->D6, B->D9  
	rgbLED.Init(D5, D6, D9);  
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
}