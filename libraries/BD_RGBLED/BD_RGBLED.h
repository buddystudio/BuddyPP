#include <Arduino.h>

class BD_RGBLED
{
	private:

		uint8_t RPin;
		uint8_t GPin;
		uint8_t BPin;

	public:

		void Init(uint8_t rPin, uint8_t gPin, uint8_t bPin);
		void SetColor(bool red, bool green, bool blue);
		void SetColor(String hexstring);
		void SetColorValue(int red, int green, int blue);
		
};