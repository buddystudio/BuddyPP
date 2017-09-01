#include <Arduino.h> 
#include <dht11.h>

class BD_DHT11
{
	private:

		uint8_t dht11Pin;
		dht11 DHT11;
		int chk;

	public:

		void Init(uint8_t pin);
		void Display();
		void Read();
		
		double Fahrenheit(double celsius);
		double Kelvin(double celsius);
		double DewPoint(double celsius, double humidity);
		double DewPointFast(double celsius, double humidity);
		
		double GetHumidity();
		double GetTemperature();
		double GetFahrenheit();
		double GetKelvin();
		double GetDewPoint();
		double GetDewFast();
};