/**
 ***************************************
 *
 * @file Conversation.ino
 * @brief 串口通讯实验
 * 
 * 通过开发环境所提供的串口调试工具进行信息交换，进行简单的信息输出与应答。
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

char val; // 定义一个命名为val的字符类型变量

// 主循环
void loop()
{
	val = Serial.read();
    
	// 如果串口接收的信息不为空
	if(Serial.available())
	{
		// 如果串口接收的信息为字符a
		if(val == 'a')
		{
			// 串口输出Hello world！
			Serial.println("Hello world!");	
		}
	}
  	
	// 延时1秒
	delay(1000);
}