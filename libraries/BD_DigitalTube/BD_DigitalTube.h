#include "TM1637.h"

class BD_DigitalTube
{
	private:

		uint8_t DINPin;
		uint8_t CLKPin;

	public:

		void Init(uint8_t din, uint8_t clk);
		void Display(int num);
		void Display(uint8_t BitAddr,int8_t DispData);
		void SetPoint(boolean flag);
};