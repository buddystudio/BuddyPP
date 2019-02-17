	
#include <IRremote.h>  
	  
int RECV_PIN = 5;  
	  
IRrecv irrecv(RECV_PIN);  
	  
decode_results results;
	  
void setup()  
{  
	Serial.begin(9600);  
	    
	irrecv.enableIRIn(); // Start the receiver  
	      
	pinMode(9, OUTPUT);  
	pinMode(3, OUTPUT);  
	      
}  
  
void loop()   
{  
	if (irrecv.decode(&results))   
	{  
		Serial.println(results.value);  
	      
		irrecv.resume();  
		// Receive the next value  
	          
	if(results.value == 16724175)  
	{  
		digitalWrite(9, HIGH);  
	}  
	else if(results.value == 16718055)  
	{  
		digitalWrite(9, LOW);  
	}  
}  
    delay(100);  
}  