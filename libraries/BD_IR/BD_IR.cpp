#include<Arduino.h>
#include<BD_IR.h>

IRrecv irrecv;

void BD_IR::Init(uint8_t inPin)
{
	irPin = inPin;
	
	irrecv.init(irPin);
	
	Serial.begin(9600);
	
	// Start the receiver
	irrecv.enableIRIn(); 
}

boolean BD_IR::Read()
{
	if (irrecv.decode(&results)) 
	{
		Serial.print("NativeCode:");
		//Serial.println(results.value, HEX);
		Serial.println(results.value);
		code = results.value;
		irrecv.resume(); // Receive the next value
	}
	else
	{
		return false;
	}
	
	delay(100);
	
	return true;
}

int BD_IR::GetCode()
{
	return code;
}