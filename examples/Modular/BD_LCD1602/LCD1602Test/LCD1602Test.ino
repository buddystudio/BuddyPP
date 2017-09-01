/**
 *************************************************************************
 *
 * @file LCD1602Test.ino
 * @brief LCD1602模块应用演示
 * 
 * 实验程序将演示使用BD_LCD1602库控制液晶模块显示内容的方法。
 * 
 * @author gsh
 * @version 1.0
 * @date 2016.9.30
 *
 *************************************************************************
 */

#include <Wire.h>
#include <BD_LCD1602.h>
 
// Set the LCD address to 0x27 for a 16 chars and 2 line display.
BD_LCD1602 lcd(0x27, 16, 2); 

void setup()
{
	// Initialize the LCD.
	lcd.init();                      
 
	// Print a message to the LCD.
	lcd.backlight();
	lcd.print("Hello, world!");
	lcd.setCursor(0,1);
	lcd.print("buddy.mongcj.com");
}

void loop()
{
}
