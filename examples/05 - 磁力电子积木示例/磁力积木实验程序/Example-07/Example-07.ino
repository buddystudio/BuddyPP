	
int value = 0;  // 暂存电位器状态  
	  
void setup()  
{  
	// 设置串口通讯波特率为9600  
	Serial.begin(9600);  
  
	pinMode(A5, INPUT_PULLUP);  
}  
	  
void loop()  
{  
	// 读取A5端口电平状态  
	value = analogRead(A5);  
	      
	// 输出电位器读数  
	Serial.println(value);  
  
	delay(100);
}  
