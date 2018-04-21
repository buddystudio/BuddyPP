/**
 *************************************************************************
 *
 * @file IRTest.ino
 * @brief 红外接收模块应用演示
 * 
 * 实验程序将演示使用BD_IR库获取红外信号接收模块反馈键码的方法。
 * 
 * @author gsh
 * @version 1.0
 * @date 2016.9.30
 *
 *************************************************************************
 */

#include <BD.h>
#include <BD_IR.h>

BD_IR ir;

void setup()
{
	// SIG->D2
	ir.Init(D2);
}

void loop()
{
	ir.Read();
  
	//Serial.println(ir.GetCode());
}
