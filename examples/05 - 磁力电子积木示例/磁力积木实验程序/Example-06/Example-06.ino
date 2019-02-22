
int value = 0;  // 暂存按钮状态  
	  
void setup()  
{  
	// 设置串口通讯波特率为9600  
	Serial.begin(9600);  
   
	// 设置端口模式  
	pinMode(9, INPUT_PULLUP);  
	pinMode(5, OUTPUT);  
	pinMode(A5, OUTPUT);  
}  
	  
void loop()  
{  
	// 读取D3端口电平状态  
	value = digitalRead(9);  

	// 高电平为常态，低电平为按下状态  
	// 如果轻触开关被按下，蜂鸣器发声  
	if(value == 0)  
	{  
		analogWrite(A5, 255);  
	}  
	else  
	{  
		analogWrite(A5, 0);  
	}  

	// 延时0.1秒（每0.1秒更获取一次状态）  
	delay(100);
} 