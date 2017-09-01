/**
 *************************************************************************
 *
 * @file Light.ino
 * @brief 光敏传感模块实验
 * 
 * 测试程序中将读取光敏数值并把相关数值在窗口调试工具中显示。
 * 
 * @author gsh
 * @version 1.0
 * @date 2015.12.1
 *
 *************************************************************************
 */
 
//宏定义（用于定义常量）
#define ADpin A5	// 使用字符串ADpin替代字符串A5
#define LED 13		// 使用字符串LED替代数字13

int ADBuffer = 0;	// 定义变量ADBuffer指向模拟端口A0

// 初始化
void setup()
{
	// 设置端口模式为OUTPUT
	pinMode(LED, OUTPUT);
	
	// 设定串口波特率为9600
	Serial.begin(9600);
}

// 主循环
void loop()
{
	// 把A0端口读取到的数据赋值给变量ADBuffer
	ADBuffer = analogRead(ADpin);
  
	Serial.print("AD = ");
 
	// 在串口调试窗口中显示数值
	Serial.println(ADBuffer);
  
	// 如果读取的数据超过阈值就点亮LED灯
	if(ADBuffer > 800)
	{
		// 点亮数字端口D13控制的LED灯
		digitalWrite(LED, HIGH);
	}
	else
	{
		// 熄灭数字端口D13控制的LED灯
		digitalWrite(LED, LOW);
	}
  
	// 延时1秒
	delay(1000);
}

