
#include<BD_DHT11.h>

void BD_DHT11::Init(uint8_t pin)
{
	dht11Pin = pin;
	
	Serial.println("DHT11 TEST PROGRAM ");
	Serial.print("LIBRARY VERSION: ");
	Serial.println(DHT11LIB_VERSION);
	Serial.println();
}

void BD_DHT11::Read()
{
	// 读取传感器数据
	chk = DHT11.read(dht11Pin);
}

// 摄氏温度度转化为华氏温度
double BD_DHT11::Fahrenheit(double celsius) 
{
	return 1.8 * celsius + 32;
}

// 摄氏温度转化为开氏温度
double BD_DHT11::Kelvin(double celsius)
{
	return celsius + 273.15;
}     

// 露点（点在此温度时，空气饱和并产生露珠）
double BD_DHT11::DewPoint(double celsius, double humidity)
{
	double A0= 373.15/(273.15 + celsius);
	double SUM = -7.90298 * (A0-1);
	
	SUM += 5.02808 * log10(A0);
	SUM += -1.3816e-7 * (pow(10, (11.344*(1-1/A0)))-1) ;
	SUM += 8.1328e-3 * (pow(10,(-3.49149*(A0-1)))-1) ;
	SUM += log10(1013.246);
	
	double VP = pow(10, SUM-3) * humidity;
	double T = log(VP/0.61078);   // temp var
	
	return (241.88 * T) / (17.558-T);
}

// 快速计算露点，速度是5倍dewPoint()
double BD_DHT11::DewPointFast(double celsius, double humidity)
{
	double a = 17.271;
	double b = 237.7;
	double temp = (a * celsius) / (b + celsius) + log(humidity/100);
	double Td = (b * temp) / (a - temp);
	
	return Td;
}

// 获取湿度
double BD_DHT11::GetHumidity()
{

	if(chk == DHTLIB_OK)
	{
		return (double)DHT11.humidity;
	}
	
}

// 获取摄氏温度
double BD_DHT11::GetTemperature()
{

	if(chk == DHTLIB_OK)
	{
		return (double)DHT11.temperature;
	}
	
}

// 获取华氏温度
double BD_DHT11::GetFahrenheit()
{

	if(chk == DHTLIB_OK)
	{
		return (double)(Fahrenheit(DHT11.temperature));
	}
	
}

// 获取开氏温度
double BD_DHT11::GetKelvin()
{

	if(chk == DHTLIB_OK)
	{
		return (double)(Kelvin(DHT11.temperature));
	}
	
}

// 获取露点（摄氏度）
double BD_DHT11::GetDewPoint()
{

	if(chk == DHTLIB_OK)
	{
		return (double)(DewPoint(DHT11.temperature, DHT11.humidity));
	}
	
}

// 获取露点快速（摄氏度）
double BD_DHT11::GetDewFast()
{

	if(chk == DHTLIB_OK)
	{
		return (double)(DewPointFast(DHT11.temperature, DHT11.humidity));
	}
	
}

// 显示传感器读取的信息
void BD_DHT11::Display()
{
	Serial.println("\n");

	// 读取传感器数据
	//int chk = DHT11.read(dht11Pin);

	Serial.print("Read sensor: ");
	
	// 判断传感器状态
	switch (chk)
	{
		case DHTLIB_OK:
		
			Serial.println("OK"); 
                
			break;
                
		case DHTLIB_ERROR_CHECKSUM:
		
			Serial.println("Checksum error");
                 
			break;
                
		case DHTLIB_ERROR_TIMEOUT: 
		
			Serial.println("Time out error"); 
                
			break;
                
		default: 
		
			Serial.println("Unknown error"); 
                
			break;
	}

	// 显示运算结果
	
	Serial.print("Humidity (%): ");
	Serial.println((float)DHT11.humidity, 2);

	Serial.print("Temperature (oC): ");
	Serial.println((float)DHT11.temperature, 2);

	Serial.print("Temperature (oF): ");
	Serial.println(Fahrenheit(DHT11.temperature), 2);

	Serial.print("Temperature (K): ");
	Serial.println(Kelvin(DHT11.temperature), 2);

	Serial.print("Dew Point (oC): ");
	Serial.println(DewPoint(DHT11.temperature, DHT11.humidity));

	Serial.print("Dew PointFast (oC): ");
	Serial.println(DewPointFast(DHT11.temperature, DHT11.humidity));

	// 延时2秒
	delay(2000);
}