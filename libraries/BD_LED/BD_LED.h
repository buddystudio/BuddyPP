#include <Arduino.h>

class BD_LED
{
	private:

		uint8_t ledPin;
		uint8_t *leds;
		uint8_t len;
		
	public:

		void Init(uint8_t inPin);
		void Init(uint8_t leds[]);
		
		void LightUp(bool flag);
		void LightUpLEDs(bool flag);
		
		void Flash(int interval);
		void FlashLEDs(int interval);
		
		void FadeIn(uint8_t interval);
		void FadeInLEDs(uint8_t interval);
		void FadeOut(uint8_t interval);
		void FadeOutLEDs(uint8_t interval);
		
		void Breathing(uint8_t interval);
};