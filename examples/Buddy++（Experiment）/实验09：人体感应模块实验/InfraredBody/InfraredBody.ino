/**
 ***************************************
 *
 * @file InfraredBody.ino
 * @brief 人体感应模块实验
 * 
 * 人体感应模块通过红外释热信号检测人体靠近事件，当传感器检测到人体
 * 靠近，SIG引脚将会输出一个高电平信号，，测试程序演示了如何通过人
 * 体感应模块检测人体靠近事件。
 * 
 * @author gsh
 * @version 1.0
 * @date 2015.12.1
 *
 ***************************************
 */

int bodyPin = 9;	// 定义变量bodyPin指向数字端口D9

// 初始化
void setup()
{
	// 设置变量bodyPin指向的端口为OUTPUT模式
	pinMode(bodyPin, INPUT);
}

// 主循环
void loop()
{
	// 读取并显示人体感应器的数据
	Serial.println(digitalRead(bodyPin));

	// 延时1秒
	delay(1000);
}
