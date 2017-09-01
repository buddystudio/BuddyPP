#include <Arduino.h>
#include "Track.h"
//构造函数
Track::Track(int a, int b, int c, int d, int e,bool f){
	Light[0] = a; Light[1] = b; Light[2] = c;
	Light[3] = d; Light[4] = e; flag = f;
}
//将引脚配置为输入
void Track::Mode(){
	for (int i = 0; i<5; i++)
		pinMode(Light[i], INPUT);
}
//设置阀值，区分黑和白
void Track::Set(int a, int b, int c, int d, int e){
	NUM[0] = a; NUM[1] = b; NUM[2] = c; NUM[3] = d; NUM[4] = e;
}
//读取模拟量
void Track::Test(){
	for (int i = 0; i<5; i++)
		value[i] = analogRead(Light[i]);
}

//读取信息，并返回对应的整型值
int Track::Digital_in(int a){
	int sum = 0;
	Test();
	for (int i = 0; i<5; i++){
		if (a == i)return (value[i]>NUM[i] == flag);
		sum = sum * 10 + (value[i]>NUM[i] == flag);
	}
	return sum;
}
