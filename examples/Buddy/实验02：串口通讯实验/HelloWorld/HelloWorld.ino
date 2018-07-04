/**
 ***************************************
 *
 * @file HelloWorld.ino
 * @brief 串口通讯实验
 * 
 * 本实验演示了开发环境所提供的串口调试工具的使用方法
 * 
 * @author gsh
 * @version 1.0
 * @date 2015.12.1
 *
 ***************************************
 */

// 初始化
void setup()
{
	// 设定串口波特率为9600
    Serial.begin(9600);
}

// 主循环
void loop()
{
    // 串口输出字符串Hello world！
    Serial.println("Hello world!");	
	
    // 延时1秒
    delay(1000);									
}
