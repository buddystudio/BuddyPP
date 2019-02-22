
/**
 ************************************************
 * 
 * @file    : TouchTest.ino
 * @brief   : 本案例用于演示使用BD_Touch库获取触摸信号的方法 
 * @author  :  
 * @version : 1.0.0 
 * @date    : 2019-02-22 14:32:19
 * 
 ************************************************
*/

#include <BD_Touch.h>

BD_Touch touch;

void setup()
{
	// 初始化代码
    Serial.begin(9600);
	
	pinMode(13, OUTPUT);
}

void loop()
{
	// 主程序代码
    if(touch.readCapacitivePin(9) > 7)
    {
		// 当人体触碰D9端口时触发信号
		// 串口输出Press...文本信息
		// 板载LED灯点亮
        Serial.println("Press...");
        
        digitalWrite(13, HIGH);
        
        delay(100);
    }
    else
    {
		// 当人体没有接触D9端口时
		// 串口输出Release...文本信息
		// 板载LED灯熄灭
        Serial.println("Release...");
        
        digitalWrite(13, LOW);
    }
    
}
