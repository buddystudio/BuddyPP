#include "InfraredRemote.h"

int Pulse_Width = 0;//存储脉宽
unsigned int ir_code = 0x00;// 用户编码值
unsigned int adrL_code = 0x00;//命令码
unsigned int adrH_code = 0x00;//命令码反码

void timer1_init(void)//定时器初始化函数
{ 
	TCCR1A = 0X00; 
	TCCR1B = 0X05;//给定时器时钟源			//64us进行一次计数器累加
	TCCR1C = 0X00; 
	TCNT1 = 0X00; 
	TIMSK1 = 0X00;  //禁止定时器溢出中断
} 

void remote_deal(void)//执行译码结果函数
{ //数据显示
        Serial.print("\nir_code:");
	Serial.println(ir_code,HEX);//16进制显示
        Serial.print("adrL_code:");
	Serial.println(adrL_code,HEX);//16进制显示
        Serial.print("adrH_code:");
        Serial.println(adrH_code,HEX);//16进制显示
        
}

char logic_value()//判断逻辑值“0”和“1”子函数
{ 
	TCNT1 = 0X00; 
	while(!(digitalRead(IR_IN))); //低等待
	Pulse_Width=TCNT1; 
	TCNT1=0; 
	if(Pulse_Width>=7&&Pulse_Width<=10)//低电平560us 
	{ 
		while(digitalRead(IR_IN));//是高就等待
		Pulse_Width=TCNT1; 
		TCNT1=0; 
		if(Pulse_Width>=7&&Pulse_Width<=10)//接着高电平560us 
			return 0; 
		else if(Pulse_Width>=25&&Pulse_Width<=27) //接着高电平1.7ms 
			return 1; 
	} 
	return -1; 
}

void pulse_deal()//接收地址码和命令码脉冲函数
{ 
	int i; 
	int j; 
	ir_code=0x00;// 清零
	adrL_code=0x00;// 清零
	adrH_code=0x00;// 清零
	//解析遥控器编码中的用户编码值
	for(i = 0 ; i < 16; i++) 
	{ 
		if(logic_value() == 1) //是1 
			ir_code |= (1<<i);//保存键值
	} 
	//解析遥控器编码中的命令码
	for(i = 0 ; i < 8; i++) 
	{ 
		if(logic_value() == 1) //是1 
			adrL_code |= (1<<i);//保存键值
	} 
	//解析遥控器编码中的命令码反码
	for(j = 0 ; j < 8; j++) 
	{ 
		if(logic_value() == 1) //是1 
			adrH_code |= (1<<j);//保存键值
	} 

        remote_deal();
} 

void remote_decode(void)//译码函数
{ 
	TCNT1=0X00; 
	while(digitalRead(IR_IN))//是高就等待
	{ 
		//if(TCNT1>=1563) //当高电平持续时间超过100ms，表明此时没有按键按下
		//{ 
		//	ir_code=0x00ff;// 用户编码值
		//	adrL_code=0x00;//键码前一个字节值
		//	adrH_code=0x00;//键码后一个字节值
		//	return; 
		//} 
	} 
	//如果高电平持续时间不超过100ms 
	TCNT1=0X00; 
	while(!(digitalRead(IR_IN))); //低等待
	Pulse_Width=TCNT1; 
	TCNT1=0; 
	if(Pulse_Width>=140&&Pulse_Width<=141)//9ms 
	{ 
		while(digitalRead(IR_IN));//是高就等待
		Pulse_Width=TCNT1; 
		TCNT1=0; 
		if(Pulse_Width>=68&&Pulse_Width<=72)//4.5ms 
		{ 
			pulse_deal(); 
			return; 
		} 
		else if(Pulse_Width>=34&&Pulse_Width<=36)//2.25ms 
		{ 
			while(!(digitalRead(IR_IN)));//低等待
			Pulse_Width=TCNT1; 
			TCNT1=0; 
			if(Pulse_Width>=7&&Pulse_Width<=10)//560us 
			{ 
				return; 
			} 
		} 
	} 
}
