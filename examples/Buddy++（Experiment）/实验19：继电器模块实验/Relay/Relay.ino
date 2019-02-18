/**
 ***************************************
 *
 * @file Relay.ino
 * @brief 继电器模块实验
 * 
 * 本次实验用于演示继电模块的使用方式，继电模块有三个引脚，VCC为5V电源正
 * 极，GND为接地，SIG为信号输入引脚，SIG引脚可连接开发板数字端口或模拟端
 * 口，当SIG引脚输入高电平信号，继电器常闭端口将连通，常开端口将会断开，
 * 当SIG引脚输入低电平信号，继电器常闭端口将闭合，常开端口连通，测试程序
 * 中将循环交替这两种状态。
 *
 * @author gsh
 * @version 1.0
 * @date 2015.12.25
 *
 ***************************************
 */

int pin = 3; // 继电器模块SIG引脚连接D3端口

// 初始化
void setup()
{
	// 设置端口模式
	pinMode(pin, OUTPUT);
	
	// 设置端口初始默认输出低电平
	digitalWrite(pin, LOW);
}

// 主循环
void loop()
{
	// 延时2秒
	delay(2000);
	
	// 电路接通
	digitalWrite(pin, HIGH);
	
	// 延时2秒
	delay(2000);
	
	// 电路断开
	digitalWrite(pin, LOW);	
}
