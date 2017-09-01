#include <Arduino.h>
#include <IRremote.h>

class BD_IR
{
	private:
	
		
		decode_results results;
		uint8_t irPin;
		int code;
		
	public:

		void Init(uint8_t inPin);
		boolean Read();
		
		int GetCode();
};