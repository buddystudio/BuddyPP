/**
 ***************************************
 *
 * @file Rheostat.ino
 * @brief 电位器模块实验
 * 
 * 旋转旋钮即可改变电位器的阻值，测试程序中将读取点位器数值并把相关数
 * 值在窗口调试工具中显示。
 *
 * @author gsh
 * @version 1.0
 * @date 2015.12.25
 *
 ***************************************
 */

#define pin A5	// 电位器模块的SIG引脚与A5端口相接

int buffer = 0;

// 初始化
void setup()
{
	// 设置串口通讯波特率为9600
	Serial.begin(9600);
}

// 主循环
void loop()
{
	// 读取电位器数据
	buffer = analogRead(pin);
	
	// 显示电位器
	Serial.print("Value = ");
	Serial.println(buffer);
	
	// 延时0.5秒
	delay(500);
}

