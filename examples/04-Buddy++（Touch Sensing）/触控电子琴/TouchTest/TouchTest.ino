/**
 ***************************************
 *
 * @file TouchTest.ino
 * @brief 触摸电子琴测试程序
 * 
 * 实验程序将演示获取触摸信号的方式。
 * 
 * @author gsh
 * @version 1.0
 * @date 2019.2.17
 *
 ***************************************
 */

#include <Keyboard.h>

int capval_01;
int capval_02;
int capval_03;
int capval_04;
int capval_05;
int capval_06;
int capval_07;
int capval_08;

int pre_01 = 0;
int pre_02 = 0;
int pre_03 = 0;
int pre_04 = 0;
int pre_05 = 0;
int pre_06 = 0;
int pre_07 = 0;
int pre_08 = 0;

void setup()
{
	Serial.begin(9600);
	Serial.println("Touch Test");

	pinMode(12, INPUT);

	Keyboard.releaseAll();
}

void loop ()
{
	// D12接高电平，防止鼠键乱跳动
	if(digitalRead(12) != HIGH)
	{
		return;
	}
  
	// 读取触摸信号
	capval_01 = readCapacitivePin(4);
	capval_02 = readCapacitivePin(5);
	capval_03 = readCapacitivePin(6);
	capval_04 = readCapacitivePin(7);
	capval_05 = readCapacitivePin(8);
	capval_06 = readCapacitivePin(9);
	capval_07 = readCapacitivePin(10);
	capval_08 = readCapacitivePin(11);

	//Keyboard.releaseAll();
  
	if (capval_01 > 7) 
	{
		Serial.println("Key_01 Touching...");
		Keyboard.press(65);

		pre_01 = 1;
	}
	else
	{
		if(pre_01 == 1)
		{
		Keyboard.release(65);

		pre_01 = 0;
		}
	}
  
	if (capval_02 > 7) 
	{
		Serial.println("Key_02 Touching...");
		Keyboard.press(83);
		pre_02 = 1;
	}
	else
	{
		if(pre_02 == 1)
		{
		Keyboard.release(83);
		pre_02 = 0;
		}
	}

	if (capval_03 > 7) 
	{
		Serial.println("Key_03 Touching...");
		Keyboard.press(68);
		pre_03 = 1;
	}
	else
	{
		if(pre_03 == 1)
		{
		Keyboard.release(68);
		pre_03 = 0;
		}
	}

	if (capval_04 > 7) 
	{
		Serial.println("Key_04 Touching...");
		Keyboard.press(70);
		pre_04 = 1;
	}
	else
	{
		if(pre_04 == 1)
		{
			Keyboard.release(70);
			pre_04 = 0;
		}
	}

	if (capval_05 > 7) 
	{
		Serial.println("Key_05 Touching...");
		Keyboard.press(71);
		pre_05 = 1;
	}
	else
	{
		if(pre_05 == 1)
		{
			Keyboard.release(71);
			pre_05 = 0;
		}
	}

	if (capval_06 > 7) 
	{
		Serial.println("Key_06 Touching...");
		Keyboard.press(72);
		pre_06 = 1;
	}
	else
	{
		if(pre_06 == 1)
		{
			Keyboard.release(72);
			pre_06 = 0;
		}
	}

	if (capval_07 > 7) 
	{
		Serial.println("Key_07 Touching...");
		Keyboard.press(74);
		pre_07 = 1;
	}
	else
	{
		if(pre_07 == 1)
		{
			Keyboard.release(74);
			pre_07 = 0;
		}
	}

	if (capval_08 > 7) 
	{
		Serial.println("Key_08 Touching...");
		Keyboard.press(75);
		pre_08 = 1;
	}
	else
	{
		if(pre_08 == 1)
		{
			Keyboard.release(75);
			pre_08 = 0;
		}
	}
}

uint8_t readCapacitivePin(int pinToMeasure) 
{
	// Variables used to translate from Arduino to AVR pin naming
	volatile uint8_t* port;
	volatile uint8_t* ddr;
	volatile uint8_t* pin;
  
	// Here we translate the input pin number from
	// Arduino pin number to the AVR PORT, PIN, DDR,
	// and which bit of those registers we care about.
	byte bitmask;
  
	port = portOutputRegister(digitalPinToPort(pinToMeasure));
	ddr = portModeRegister(digitalPinToPort(pinToMeasure));
	bitmask = digitalPinToBitMask(pinToMeasure);
	pin = portInputRegister(digitalPinToPort(pinToMeasure));
  
	// Discharge the pin first by setting it low and output
	*port &= ~(bitmask);
	*ddr |= bitmask;
	delay(1);
	// Make the pin an input with the internal pull-up on
	*ddr &= ~(bitmask);
	*port |= bitmask;

	// Now see how long the pin to get pulled up. This manual unrolling of the loop
	// decreases the number of hardware cycles between each read of the pin,
	// thus increasing sensitivity.
	uint8_t cycles = 17;
	if (*pin & bitmask) 
	{
		cycles = 0;
	}
	else if (*pin & bitmask) {
		cycles = 1;
	}
	else if (*pin & bitmask) {
		cycles = 2;
	}
	else if (*pin & bitmask) {
		cycles = 3;
	}
	else if (*pin & bitmask) {
		cycles = 4;
	}
	else if (*pin & bitmask) {
		cycles = 5;
	}
	else if (*pin & bitmask) {
		cycles = 6;
	}
	else if (*pin & bitmask) {
		cycles = 7;
	}
	else if (*pin & bitmask) {
		cycles = 8;
	}
	else if (*pin & bitmask) {
		cycles = 9;
	}
	else if (*pin & bitmask) {
		cycles = 10;
	}
	else if (*pin & bitmask) {
		cycles = 11;
	}
	else if (*pin & bitmask) {
		cycles = 12;
	}
	else if (*pin & bitmask) {
		cycles = 13;
	}
	else if (*pin & bitmask) {
		cycles = 14;
	}
	else if (*pin & bitmask) {
		cycles = 15;
	}
	else if (*pin & bitmask) {
		cycles = 16;
	}

	// Discharge the pin again by setting it low and output
	// It's important to leave the pins low if you want to
	// be able to touch more than 1 sensor at a time - if
	// the sensor is left pulled high, when you touch
	// two sensors, your body will transfer the charge between
	// sensors.
	*port &= ~(bitmask);
	*ddr |= bitmask;

	return cycles;
}

