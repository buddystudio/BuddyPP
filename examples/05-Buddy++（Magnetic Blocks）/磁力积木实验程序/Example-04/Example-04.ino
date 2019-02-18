	
int value = 0;  // 暂存按钮状态  
	  
void setup()  
{  
	// 设置串口通讯波特率为9600  
	Serial.begin(9600);  
	      
	// 设置端口模式  
	pinMode(9, INPUT_PULLUP);
	pinMode(5, OUTPUT);  
}  
  
void loop()  
{  
	// 读取D3端口电平状态  
	value = digitalRead(9);  
	  
	// 输出按钮状态  
	// 高电平为常态，低电平为按下状态  
	Serial.print("Value = ");  
	Serial.println(value);  
	  
	// 延时0.1秒（每0.1秒更获取一次状态）  
	delay(100);  
}  