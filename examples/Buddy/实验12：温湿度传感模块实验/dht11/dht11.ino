/**
 ***************************************
 *
 * @file DHT11.ino
 * @brief 温湿度传感器模块实验
 * 
 * 测试程序中将读取传感器数值并将其换算为环境温度数值和环境湿度数值，
 * 接着在串口调试工具中显示。
 * 
 * @author gsh
 * @version 1.0
 * @date 2015.12.9
 *
 ***************************************
 */

// 摄氏温度度转化为华氏温度
double Fahrenheit(double celsius) 
{
	return 1.8 * celsius + 32;
}    

// 摄氏温度转化为开氏温度
double Kelvin(double celsius)
{
	return celsius + 273.15;
}     

// 露点（点在此温度时，空气饱和并产生露珠）
double dewPoint(double celsius, double humidity)
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
double dewPointFast(double celsius, double humidity)
{
	double a = 17.271;
	double b = 237.7;
	double temp = (a * celsius) / (b + celsius) + log(humidity/100);
	double Td = (b * temp) / (a - temp);
	
	return Td;
}

#include <dht11.h>

dht11 DHT11;

#define DHT11PIN 2	// 使用字符串DHT11PIN替代字符串A2

// 初始化
void setup()
{
	// 设定串口波特率为9600
	Serial.begin(9600);
	
	Serial.println("DHT11 TEST PROGRAM ");
	Serial.print("LIBRARY VERSION: ");
	Serial.println(DHT11LIB_VERSION);
	Serial.println();
}

// 主循环
void loop()
{
	Serial.println("\n");

	// 读取传感器数据
	int chk = DHT11.read(DHT11PIN);

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
	Serial.println(dewPoint(DHT11.temperature, DHT11.humidity));

	Serial.print("Dew PointFast (oC): ");
	Serial.println(dewPointFast(DHT11.temperature, DHT11.humidity));

	// 延时2秒
	delay(2000);
}
