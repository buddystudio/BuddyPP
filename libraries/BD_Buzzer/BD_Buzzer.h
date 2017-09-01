#include <Arduino.h>
#include <BD_Tune.h>
class BD_Buzzer
{
	private:

		uint8_t buzzerPin;
		uint8_t *buzzers;
		uint8_t len;
		
	public:

		void Init(uint8_t inPin);
		void Init(uint8_t buzzers[]);
		
		void Sound(bool flag);
		void SoundBuzzers(bool flag);
		
		void Alert(int interval);
		void AlertBuzzers(int interval);
		void FadeIn(uint8_t interval);
		void FadeInBuzzers(uint8_t interval);
		void FadeOut(uint8_t interval);
		
		void Music(int tune[], float durt[], int length);
};