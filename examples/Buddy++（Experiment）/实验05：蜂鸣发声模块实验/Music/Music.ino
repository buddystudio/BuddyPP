/**
 ***************************************
 *
 * @file Buzzer.ino
 * @brief 蜂鸣器音乐播放实验
 * 
 * 控制高低电平输出频率可以控制蜂鸣器的音调，程序将使用蜂鸣器演奏音乐。
 * 
 * @author gsh
 * @version 1.0
 * @date 2015.12.1
 *
 ***************************************
 */

#define NTD0 -1
#define NTD1 294
#define NTD2 330
#define NTD3 350
#define NTD4 393
#define NTD5 441
#define NTD6 495
#define NTD7 556

#define NTDL1 147
#define NTDL2 165
#define NTDL3 175
#define NTDL4 196
#define NTDL5 221
#define NTDL6 248
#define NTDL7 278

#define NTDH1 589
#define NTDH2 661
#define NTDH3 700
#define NTDH4 786
#define NTDH5 882
#define NTDH6 990
#define NTDH7 112

//c pinlv
#define WHOLE 1
#define HALF 0.5
#define QUARTER 0.25
#define EIGHTH 0.25
#define SIXTEENTH 0.625

// 设定乐谱
int tune[]=
{
  NTD3, NTD3, NTD4, NTD5,
  NTD5, NTD4, NTD3, NTD2,
  NTD1, NTD1, NTD2, NTD3,
  NTD3, NTD2, NTD2,
  NTD3, NTD3, NTD4, NTD5,
  NTD5, NTD4, NTD3, NTD2,
  NTD1, NTD1, NTD2, NTD3,
  NTD2, NTD1, NTD1,
  NTD2, NTD2, NTD3, NTD1,
  NTD2, NTD3, NTD4, NTD3, NTD1,
  NTD2, NTD3, NTD4, NTD3, NTD2,
  NTD1, NTD2, NTDL5,NTD0,
  NTD3, NTD3, NTD4, NTD5,
  NTD5, NTD4, NTD3, NTD4, NTD2,
  NTD1, NTD1, NTD2, NTD3,
  NTD2, NTD1, NTD1
};

// 设定节拍
float durt[]=
{
  1, 1, 1, 1,
  1, 1, 1, 1,
  1, 1, 1, 1,
  1 + 0.5, 0.5, 1 + 1,
  1, 1, 1, 1,
  1, 1, 1, 1,
  1, 1, 1, 1,
  1 + 0.5, 0.5, 1 + 1,
  1, 1, 1, 1,
  1, 0.5, 0.5, 1, 1,
  1, 0.5, 0.5, 1, 1,
  1, 1, 1, 1,
  1, 1, 1, 1,
  1, 1, 1, 0.5, 0.5,
  1, 1, 1, 1,
  1 + 0.5, 0.5, 1 + 1,
};


int length;
int tonepin = 3; // 定义变量tonepin指向数字端口D3

// 初始化
void setup()
{
	// 设置数字端口模式为OUTPUT
	pinMode(tonepin, OUTPUT);
	
	length = sizeof(tune) / sizeof(tune[0]);
}

// 主循环
void loop()
{
	// 循环播放欢乐颂
	// 每循环一次播放一个音符
	for(int x = 0; x < length; x++)
	{
		// tone(pin, frequency)
		// tone函数可产生不同频率相同占空比(50%)的波形。
		// pin需要输出方波的引脚
		// frequency输出的频率
		tone(tonepin, tune[x]);
	
		delay(500 * durt[x]);
	
		// 暂停发声
		noTone(tonepin);
    
	}

	// 延时2秒
	delay(2000);
}
