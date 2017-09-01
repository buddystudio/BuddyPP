#include<Arduino.h>
#include<BD_LED.h>

void BD_LED::Init(uint8_t inPin)
{
	ledPin = inPin;
	
	pinMode(ledPin, OUTPUT);
	
	digitalWrite(ledPin, 0);
}

void BD_LED::Init(uint8_t ledArray[])
{
	len = sizeof(ledArray) / sizeof(ledArray[0]);
	
	leds = new uint8_t[len];
	
	for(uint8_t i = 0; i <= len; i++)
	{
		leds[i] = ledArray[i];
		
		digitalWrite(leds[i], 0);
	}
}

void BD_LED::LightUp(bool flag)
{
	digitalWrite(ledPin, flag);
}

void BD_LED::LightUpLEDs(bool flag)
{
	for(uint8_t i = 0; i <= len; i++)
	{
		digitalWrite(leds[i], flag);
	}
}

void BD_LED::Flash(int interval)
{
	digitalWrite(ledPin, 1);
	delay(interval);
	digitalWrite(ledPin, 0);
	delay(interval);
}

void BD_LED::FlashLEDs(int interval)
{
	for(uint8_t i = 0; i <= len; i++)
	{
		digitalWrite(leds[i], 1);	
	}
	
	delay(interval);
	
	for(uint8_t i = 0; i <= len; i++)
	{
		digitalWrite(leds[i], 0);	
	}
	
	delay(interval);
}

void BD_LED::FadeIn(uint8_t interval)
{
	for (int a = 0; a <= 255; a++)
	{
		analogWrite(ledPin, a);
		delay(interval);
	}
}

void BD_LED::FadeInLEDs(uint8_t interval)
{
	for (int a = 0; a <= 255; a++)
	{
		for(uint8_t i = 0; i <= len; i++)
		{
			analogWrite(leds[i], a);	
		}
		
		delay(interval);
	}
}

void BD_LED::FadeOut(uint8_t interval)
{
	for (int a = 255; a >= 0; a--)
	{
		analogWrite(ledPin, a);
		delay(interval);
	}
}

void BD_LED::FadeOutLEDs(uint8_t interval)
{
	for (int a = 255; a >= 0; a--)
	{
		for(uint8_t i = 0; i <= len; i++)
		{
			analogWrite(leds[i], a);	
		}
		
		delay(interval);
	}
}