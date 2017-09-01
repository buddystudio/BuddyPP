#include<BD_RGBLED.h>
#include<Arduino.h>

void BD_RGBLED::Init(uint8_t rPin, uint8_t gPin, uint8_t bPin)
{
	RPin = rPin;
	GPin = gPin;
	BPin = bPin;
	
	pinMode(RPin, OUTPUT);
	pinMode(GPin, OUTPUT);
	pinMode(BPin, OUTPUT);
}

void BD_RGBLED::SetColor(bool red, bool green, bool blue)
{
	digitalWrite(RPin, !red);
	digitalWrite(GPin, !green);
	digitalWrite(BPin, !blue);
}

void BD_RGBLED::SetColor(String hexstring)
{
	// get rid of '#' and convert it to integer.
	long number = strtol(&hexstring[1], NULL, 16);

	// split them up into r, g, b values.
	long r = number >> 16;
	long g = number >> 8 & 0xFF;
	long b = number & 0xFF;
	
	analogWrite(RPin, 1023 - map(r, 0, 255, 0, 1023));
	analogWrite(GPin, 1023 - map(g, 0, 255, 0, 1023));
	analogWrite(BPin, 1023 - map(b, 0, 255, 0, 1023));
}

void BD_RGBLED::SetColorValue(int red, int green, int blue)
{
	analogWrite(RPin, 1023 - map(red, 0, 255, 0, 1023));
	analogWrite(GPin, 1023 - map(green, 0, 255, 0, 1023));
	analogWrite(BPin, 1023 - map(blue, 0, 255, 0, 1023));
}