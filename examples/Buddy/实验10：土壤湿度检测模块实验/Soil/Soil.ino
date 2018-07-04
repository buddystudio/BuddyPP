/**
 ***************************************
 *
 * @file Soil.ino
 * @brief 土壤湿度传感器模块实验
 * 
 * 分贝传感模块可以检测环境声音大小，SIG引脚将会输出一个模拟值，通过
 * 使用螺丝刀调整模块上的电位器可以适当调整传感器的灵敏程度，测试程序
 * 演示了如何通过分贝模块检测环境声音大小。
 * 
 * @author gsh
 * @version 1.0
 * @date 2015.12.1
 *
 ***************************************
 */

int ASignal = A5;	// 定义变量ASignal指向模拟端口A5
int LEDPin 	= 13; 	// 定义变量LEDPin指向数字端口D13
int val 	= 900;  // 定义变量val用于暂存传感器数据

// 初始化
void setup() 
{
	// 初始化数字端口模式
	pinMode(LEDPin, OUTPUT);    
	pinMode(ASignal, INPUT);
	
	// 默认LED灯为熄灭状态
	digitalWrite(LEDPin, LOW);  
	
	// 设定串口波特率为9600
	Serial.begin(9600);
 
}
 
// 主循环
void loop() 
{
	// 读取土壤湿度
	int sensorValue = analogRead(ASignal);
  
	// 如果读数超过阈值点亮LED灯否则熄灭LED灯
	if(analogRead(ASignal) > val)
	{
		// 点亮LED灯
		digitalWrite(LEDPin, HIGH);

		// 延时0.3秒
		delay(300);
		
		// 熄灭LED灯
		digitalWrite(LEDPin, LOW);

		// 延时0.3秒
		delay(300);
	}
	else 
	{
		// 熄灭LED灯
		digitalWrite(LEDPin, LOW);  
 
		// 延时1秒
		delay(1000);
	}
}

