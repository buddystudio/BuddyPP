#include<Arduino.h>
#include<BD_Joystick.h>

void BD_Joystick::Init(uint8_t xPin, uint8_t yPin, uint8_t zPin)
{
	XPin = xPin;
	YPin = yPin;
	ZPin = zPin;
	
	pinMode(XPin, INPUT);
	pinMode(YPin, INPUT);
	pinMode(ZPin, INPUT);
}

void BD_Joystick::Read()
{
	xVal = analogRead(XPin);
	yVal = analogRead(YPin);
	
	zVal = digitalRead(ZPin);
}

void BD_Joystick::ReadRevise(uint8_t accuracy)
{
	uint8_t zCount = 0;
	
	for(uint8_t i = 1; i < accuracy; i++)
	{
		xVal += analogRead(XPin);
		yVal += analogRead(YPin);
		
		zCount += digitalRead(ZPin);
	}
	
	xVal /= accuracy;
	yVal /= accuracy;
	
	if((zCount / 2) >= (accuracy / 3))
	{
		zVal = 1;
	}
	else
	{
		zVal = 0;
	}
}

int BD_Joystick::GetX()
{
	return xVal;
}

int BD_Joystick::GetY()
{
	return yVal;
}

bool BD_Joystick::GetZ()
{
	return zVal;
}