	  
#include <BD.h>  
#include <BD_IR.h>  
	  
BD_IR ir;  
	  
void setup()  
{  
	// SIG->D5  
	ir.Init(D5);  
}  
  
void loop()  
{  
	ir.Read();  
	    
	//Serial.println(ir.GetCode()); 
} 
