/**
 ***************************************
 *
 * @file XYRocker.ino
 * @brief 摇杆模块实验
 * 
 * 本次实验用于演示摇杆模块的使用方式，X/Y/Z引脚可连接开发板数字端口或模拟
 * 端口，当按下轻触按钮时Z引脚输出一个高电平信号，当摇动摇杆时X/Y引脚会输
 * 出对应的偏移量。
 *
 * @author gsh
 * @version 1.0
 * @date 2015.12.25
 *
 ***************************************
 */

int x = 10;	// x引脚连接D10端口
int y = 9;	// y引脚连接D9端口
int z = 8;	// z引脚连接D8端口

// 初始化
void setup(void)
{
	// 设置串口通信波特率为9600
	Serial.begin(9600);
	
	// 设置端口模式
	pinMode(z, INPUT_PULLUP);
}

// 主循环
void loop(void)
{
	// 读取X轴偏移量并显示
	Serial.print("X= ");
	Serial.print(analogRead(x));
	Serial.print(",");

	// 读取Y轴偏移量并显示
	Serial.print("Y= ");
	Serial.print(analogRead(y));
	Serial.print(",");

	// 读按Z轴按钮状态并显示
	Serial.print("Z state = ");
	Serial.println(digitalRead(z));

	// 每0.1秒刷新一次
	delay(100);   
}
