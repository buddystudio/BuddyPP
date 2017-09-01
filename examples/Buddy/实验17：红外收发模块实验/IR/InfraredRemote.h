#ifndef __InfraredRemote__H__
#define __InfraredRemote__H__

#include <Arduino.h>

#define IR_IN 2 //红外接收

extern void timer1_init(void);	//定时器初始化函数
extern char logic_value();//判断逻辑值“0”和“1”子函数
extern void pulse_deal();//接收地址码和命令码脉冲函数
extern void remote_decode(void);
extern void remote_deal(void);//执行译码结果函数

extern unsigned int ir_code;// 用户编码值
extern unsigned int adrL_code;//命令码
extern unsigned int adrH_code;//命令码反码

#endif
