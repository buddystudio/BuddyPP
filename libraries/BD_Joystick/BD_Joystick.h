#include <Arduino.h>

class BD_Joystick
{
	private:

		uint8_t XPin;
		uint8_t YPin;
		uint8_t ZPin;
		
		uint16_t xVal;
		uint16_t yVal;
		bool     zVal;

	public:

		void Init(uint8_t xPin, uint8_t yPin, uint8_t zPin);
		void Read();
		void ReadRevise(uint8_t accuracy);
		int GetX();
		int GetY();
		bool GetZ();
		
};