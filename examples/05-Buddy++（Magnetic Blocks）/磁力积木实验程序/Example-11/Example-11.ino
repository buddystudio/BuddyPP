	  
#include<BD.h>  
#include<BD_DHT11.h>  
	  
BD_DHT11 dht11;  
	  
void setup()  
{  
	// 初始化代码  
	Serial.begin(9600);  
			
	// SIG->D4(or A22)  
	dht11.Init(D9);  
		  
	pinMode(5, OUTPUT);  
}  
	  
void loop()  
{  
	// 主程序代码  
	dht11.Read();  
    
    // 显示所有数据  
	//dht11.Display();  
	    
	// 获取湿度  
	Serial.println(dht11.GetHumidity(), 2);  
	    
	// 获取摄氏温度  
	Serial.println(dht11.GetTemperature(), 2);  
	    
	// 获取华氏温度  
	Serial.println(dht11.GetFahrenheit(), 2);  
	  
    // 获取开氏温度  
	Serial.println(dht11.GetKelvin(), 2);  
	    
	// 获取露点（摄氏温度）  
	Serial.println(dht11.GetDewPoint(), 2);  
	     
	// 获取露点快速（摄氏温度）  
	Serial.println(dht11.GetDewFast(), 2);  
    
	delay(5000);
}  
