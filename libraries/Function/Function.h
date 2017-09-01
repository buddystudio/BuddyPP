#ifndef Function_h
#define Function_h
#include <Arduino.h>
#include "Servo.h"
#include "ColSensor.h"
#include "Track.h"
#include "Car.h"
#include "ColQueue.h"
#define N 12
/*存储检测到的12个位置方块的颜色，第一个元素
Memory[0]的值无效，下标对应小车的12个路口*/
int Memory[N + 1];
ColQueue Red(N), Green(N), Blue(N);
//小车左轮的速度
int SPEED1 = 150;
//小车右轮的速度
int SPEED2 = 150 - 10;
//小车转弯时的速度
int TURN = 180;
//定义小车电机电平的延时
int TIME = 1;
//小车转弯时的延时
int DELAY = 800;
//小车停止时的延时
int STOP = 250;
//小车后退时的延时
int BACK = 200;
//记录小车所在的路口，分别取值为1至12
int num = 0;
Car mycar(8, 9, 10, 11, 5, 6);
//循迹模块为黑色型号的用户需要在参数列表中增加一个false
Track mytrack(A5, A4, A3, A2, A1, false);
ColSensor mysensor(0, 1, 3, 4, 2, 10);
/*舵机设置：1表示控制夹子的舵机，2表示控制机械
臂摆动的舵机，3表示控制机械臂底座旋转的舵机*/
Servo myservo1, myservo2, myservo3;
/********************小车转弯函数********************/
//小车直角左转
void TurnL(){
	mycar.Move(0, 0, 5, STOP);
	mycar.Move(TURN, TURN, 4, DELAY / 2);
	do{
		mycar.Move(TURN, TURN, 4, TIME);
	} while (mytrack.Digital_in(2));
	mycar.Move(0, 0, 5, 1);
}
//小车直角右转
void TurnR(){
	mycar.Move(0, 0, 5, STOP);
	mycar.Move(TURN, TURN, 6, DELAY / 2);
	do{
		mycar.Move(TURN, TURN, 6, TIME);
	} while (mytrack.Digital_in(2));
	mycar.Move(0, 0, 5, 1);
}
//小车原地转180度
void TurnC(){
	mycar.Move(0, 0, 5, STOP);
	mycar.Move(TURN, TURN, 4, DELAY);
	do{
		mycar.Move(TURN, TURN, 4, TIME);
	} while (mytrack.Digital_in(2));
	mycar.Move(0, 0, 5, 1);
}
/********************记忆功能相关函数********************/
void Counter(){
	mysensor.g_count++;
}
/*检测方块颜色，并返回一个值。1表示红色，2表示绿色，3表示蓝色，0表示没有检测到颜
色。考虑到颜色传感器的有效距离比较短，为了提高检测结果的准确率，本程序在采用差值比
较的方法。其原理是：先找出RGB值中的最大值，只要它比其它两个值中的某一个相差100
或以上，则检测到的颜色由最大值所对应的颜色确定；否则则认为没有检测到符合要求的颜色*/
int Color(){
	int i, min1, min2;
	mysensor.Get();
	if (mysensor.g_array[0]>mysensor.g_array[1]){
		i = 0; min1 = mysensor.g_array[1];
	}
	else{
		i = 1; min1 = mysensor.g_array[0];
	}
	if (mysensor.g_array[i]>mysensor.g_array[2])
		min2 = mysensor.g_array[2];
	else {
		min2 = mysensor.g_array[i]; i = 2;
	}
	if ((mysensor.g_array[i] - min1)>100 ||
		(mysensor.g_array[i] - min2)>100)return i + 1;
	return 0;
}
void Push(int i){
	switch (Memory[i]){
	case 1:Red.Enter(i); break;
	case 2:Blue.Enter(i); break;
	case 3:Green.Enter(i); break;
	default:break;
	}
}
int Check(int i){
	int flag;
	mycar.Move(0, 0, 5, STOP);
	//检测三次，减小失误机率
	for (int i = 0; i<3; i++)
	if (flag = Color()){
		digitalWrite(13, HIGH); delay(500);
		digitalWrite(13, LOW); delay(500);
		break;
	}
	return Memory[i] = flag;
}
/********************舵机转动函数********************/
//控制舵机的转动
void ServoMove(int a, int b, int c){
	Servo p; int t;
	switch (c){
	case 1:p = myservo1; t = 2; break;
	case 2:p = myservo2; t = 2; break;
	case 3:p = myservo3; t = 2; break;
	default:break;
	}
	//考虑到舵机的精确度，此函数用脉冲值来进行控制
	a = map(a, 0, 180, 544, 2400);
	b = map(b, 0, 180, 544, 2400);
	if (a>b)
	for (int i = a; i>b; i--){
		p.writeMicroseconds(i); delay(t);
	}
	else
	for (int i = a; i<b; i++){
		p.writeMicroseconds(i); delay(t);
	}
}
//修正速度，抵消因电压降低所引起的速度减小
void Plus(){
	SPEED1++; SPEED2++; TURN++;
}
//智能刹车
void Stoping(int& a){
	if (a > 2){
		mycar.Move(SPEED1, SPEED2, 2, BACK / (6.0 / a));
		mycar.Move(0, 0, 5, 1);
	}
	a = 0;
}
void Tracking(int a, int b, int temp = 11011){
	int t = 1;
	while (t){
		t = mytrack.Digital_in();
		Serial.println(t);
		if (t == 100 || t == 1110 || t == 10101 || t == 11111)t = temp;
		switch (t){

		case 11110: case 11100: case 11101:
		case 1100: case 1101: case 10100:
			mycar.Move(a, b, 6, TIME); temp = t; break;

		case 1111: case 110: case 101:
		case 10110: case 10111: case 111:
			mycar.Move(a, b, 4, TIME); temp = t; break;

		case 11000: case 1000: case 1001: case 11010:
			mycar.Move(a, b, 6, TIME); temp = t; break;

		case 11: case 10: case 10010: case 1011:
			mycar.Move(a, b, 4, TIME); temp = t; break;

		case 11011: case 1010: case 11001: case 10011:
			mycar.Move(a, b, 8, TIME); temp = t; break;

		case 0: case 10001: case 1: case 10000: 
			/*使小车越过黑线，防止红外重复检测同一黑线造成判断错
			误，当红外最左端和最右端均检测到白色时退出此循环*/
			while (mytrack.Digital_in(1) || mytrack.Digital_in(3)){
				mycar.Move(a, b, 8, 1);
			}t = 0; break;

		default:break;
		}
	}
	mycar.Move(0, 0, 5, 1);
}
void In(){
	TurnL();
	Tracking(SPEED1, SPEED2, 1);
	mycar.Move(SPEED1, SPEED2, 2, BACK);
	mycar.Move(0, 0, 5, 1);

}
void Out(){
	TurnC();
	Tracking(SPEED1, SPEED2, 1);
	mycar.Move(SPEED1, SPEED2, 2, BACK / 3);
	mycar.Move(0, 0, 5, 1);
	TurnL();
}
/********************校正函数********************/
//调节白平衡
void Ready(){
	pinMode(13, OUTPUT);
	attachInterrupt(0, Counter, RISING);
	delay(1000);
	mysensor.Test();
	for (int i = 0; i<1000 / 200; i++){
		digitalWrite(13, HIGH); delay(100);
		digitalWrite(13, LOW); delay(100);
	}
	for (int i = 0; i<1;){
		int flag = Color();
		if (flag){
			digitalWrite(13, HIGH); delay(500);
			digitalWrite(13, LOW); delay(500);
			i++;
		}
	}
	for (int i = 0; i<3000 / 200; i++){
		digitalWrite(13, HIGH); delay(100);
		digitalWrite(13, LOW); delay(100);
	}
}
//自动调整阈值
void Modify(){
	int test1[5] = { 0, 0, 0, 0, 0 };
	int test2[5] = { 0, 0, 0, 0, 0 };
	int t[5] = { 0, 0, 0, 0, 0 };
	pinMode(13, OUTPUT);
	//在指示灯停止闪烁前，应将红外全部对准黑线部分放置
	for (int i = 0; i<3000 / 200; i++){
		digitalWrite(13, HIGH); delay(100);
		digitalWrite(13, LOW); delay(100);
	}
	for (int i = 0; i<20; i++){
		mytrack.Test();
		for (int i = 0; i<5; i++){
			test1[i] += mytrack.value[i];
		}
		delay(10);
	}
	//在指示灯停止闪烁前，应将红外全部对准白色部分放置
	for (int i = 0; i<3000 / 1000; i++){
		digitalWrite(13, HIGH); delay(500);
		digitalWrite(13, LOW); delay(500);
	}
	for (int i = 0; i<20; i++){
		mytrack.Test();
		for (int i = 0; i<5; i++){
			test2[i] += mytrack.value[i];
		}
		delay(10);
	}
	for (int i = 0; i<5; i++){
		test1[i] = test1[i] / 20;
		test2[i] = test2[i] / 20;
		t[i] = (test1[i] + test2[i]) / 2;
	}
	mytrack.Set(t[0], t[1], t[2], t[3], t[4]);
	for (int i = 0; i<3000 / 200; i++){
		digitalWrite(13, HIGH); delay(100);
		digitalWrite(13, LOW); delay(100);
	}
}
#endif