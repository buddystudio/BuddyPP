/*
 SparkFun microSD Shield Sample Sketch
 
 Demonstrates how to use the FAT16 library to create, read and write to
 files located on the microSD card of the microSD Shield.
 
 The library will only work on SD cards after they've been formatted
 to FAT16.
 
 The microSD Shield is available from SparkFun's
 website:
 
 http://www.sparkfun.com/commerce/product_info.php?products_id=9520
 
 created Nov. 24, 2009
 by Ryan Owens
*/
//Include all the libraries necessary for FAT32
#include <byteordering.h>
#include <fat.h>
#include <FAT16.h>
#include <fat_config.h>
#include <partition.h>
#include <partition_config.h>
#include <sd-reader_config.h>
#include <sd_raw.h>
#include <sd_raw_config.h>

FAT TestFile;      //This will be the file we manipulate in the sketch
char buffer[512];  //Data will be temporarily stored to this buffer before being written to the file
int read_size=0;   //Used as an indicator for how many characters are read from the file
int count=0;       //Miscellaneous variable

void setup()
{
  Serial.begin(9600);  //Initiate serial communication at 9600 bps
  
  TestFile.initialize();  //Initialize the SD card and the FAT file system.
  Serial.println("Starting...");
  TestFile.create_file("Sample.txt");  //Create a file on the SD card named "Read_File_Test.txt"
                                       //NOTE: This function will return a 0 value if it was unable to create the file.
  TestFile.open();  //Now that the file has been created, open it so we can write to it.
}

void loop()
{
  TestFile.write("This is test data.");  //using the write function will always write to the beginning of the file. Here we add some text to the file.
  TestFile.close();  //We are done writing to the file for now. Close it for later use.
  
  while(1){
    TestFile.open();  //Open the file. When the file is opened we will be looking at the beginning of the file.
    read_size=TestFile.read(buffer); //Read the contents of the file. This will only read the amount of data specified by the size of 'buffer.'

    Serial.println(read_size, DEC);  //Print the number of characters read by the read function.
    for(int i=0; i<read_size; i++)   
    {
      Serial.print(buffer[i], BYTE);  //Print out the contents of the buffer.
    }
    Serial.println();

    sprintf(buffer, "%d", count++); //Now we'll use the buffer to write data back to the file. Here's we'll only add one value to buffer, the 'count' variable. 
    TestFile.write(buffer);         //Write the new buffer to the end of the file
    TestFile.close();               //Close the file for later use.

    delay(1000);  //Wait one second before repeating the loop.
  }
}

