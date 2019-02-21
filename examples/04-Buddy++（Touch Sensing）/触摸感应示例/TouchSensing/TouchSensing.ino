/**
 ***************************************
 *
 * @file TouchTest.ino
 * @brief 触摸感应示例
 * 
 * 实验程序将演示获取触摸信号的方式。
 * 
 * @author gsh
 * @version 1.0
 * @date 2019.2.18
 *
 ***************************************
 */

int capval_01;

boolean isPressed01 = false;

void setup()
{
	Serial.begin(9600);
	
	pinMode(13, OUTPUT);
}

void loop ()
{
	// 读取触摸信号（当触摸引脚D9时触发信号）
	capval_01 = readCapacitivePin(9);
  
	if (capval_01 > 7)
	{
		Serial.println("Key_01 Press...");

		isPressed01 = true;
		
		// 点亮指示灯
		digitalWrite(13, HIGH);
	}
	else
	{
		if(isPressed01 == true)
		{
		    Serial.println("Key_01 Release...");

		    isPressed01 = false;
		    
		    // 熄灭指示灯
		    digitalWrite(13, LOW);
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

