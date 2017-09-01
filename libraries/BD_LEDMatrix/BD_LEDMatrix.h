#include <Arduino.h> 

class BD_LEDMatrix
{
	private:

		uint8_t DINPin;
		uint8_t CLKPin;
		uint8_t LTHPin;

		byte scan[8];
		byte img[];

	public:

		void Init(uint8_t din, uint8_t clk, uint8_t lth);
		void Display(byte *img);
		void Display(byte *img, unsigned long delay);
};