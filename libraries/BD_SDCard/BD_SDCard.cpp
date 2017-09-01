#include<BD_SDCard.h>

void BD_SDCard::Init()
{
	chipSelect = 10;
	
    Serial.begin(9600);
  
    delay(3000);
    while (!Serial) {
        ; // wait for serial port to connect. Needed for Leonardo only
    }


    Serial.print("\nInitializing SD card...");
    // On the Ethernet Shield, CS is pin 4. It's set as an output by default.
    // Note that even if it's not used as the CS pin, the hardware SS pin 
    // (10 on most Arduino boards, 53 on the Mega) must be left as an output 
    // or the SD library functions will not work. 

    //pinMode(SS, OUTPUT);


    // we'll use the initialization code from the utility libraries
    // since we're just testing if the card is working!
    while (!card.init(SPI_HALF_SPEED, chipSelect)) {
        Serial.println("initialization failed. Things to check:");
        Serial.println("* is a card is inserted?");
        Serial.println("* Is your wiring correct?");
        Serial.println("* did you change the chipSelect pin to match your shield or module?");
    } 
    
    // print the type of card
    //Serial.print("\nCard type: ");
    switch(card.type()) {
        case SD_CARD_TYPE_SD1:
            //Serial.println("SD1");
            cardType = "SD1";
        break;
        case SD_CARD_TYPE_SD2:
            //Serial.println("SD2");
            cardType = "SD2";
        break;
        case SD_CARD_TYPE_SDHC:
            //Serial.println("SDHC");
            cardType = "SDHC";
        break;
        default:
            //Serial.println("Unknown");
            cardType = "Unknown";
    }

    // Now we will try to open the 'volume'/'partition' - it should be FAT16 or FAT32
    if (!volume.init(card)) {
        Serial.println("Could not find FAT16/FAT32 partition.\nMake sure you've formatted the card");
        return;
    }


    // print the type and size of the first FAT-type volume
    
    /*
    Serial.print("\nVolume type is FAT");
    Serial.println(volume.fatType(), DEC);
    Serial.println();
    */

    volumeType = (String)volume.fatType();
    
    volumesize = volume.blocksPerCluster();    // clusters are collections of blocks
    volumesize *= volume.clusterCount();       // we'll have a lot of clusters
    volumesize *= 512;                            // SD card blocks are always 512 bytes

    /*
    Serial.print("Volume size (bytes): ");
    Serial.println(volumesize);
    Serial.print("Volume size (Kbytes): ");
    //volumesize /= 1024;
    Serial.println(volumesize / 1024);
    Serial.print("Volume size (Mbytes): ");
    //volumesize /= 1024;
    Serial.println(volumesize / 1024 / 1024);
    */

}

String BD_SDCard::GetCardType()
{
    return cardType;
}

String BD_SDCard::GetVolumeType()
{

    return volumeType;
}

uint32_t BD_SDCard::GetVolumeSize()
{
    return volumesize;
}

uint32_t BD_SDCard::GetVolumeSize(uint8_t unit)
{
    if(unit == 1)
    {
        return volumesize;
    }
    else if(unit == 2)
    {
        return volumesize / 1024;
    }
    else if(unit == 3)
    {
        return volumesize / 1024 / 1024;
    }

}

void BD_SDCard::ShowList()
{
    root.openRoot(volume);
    
    // list all files in the card with date and size
    root.ls(LS_R | LS_DATE | LS_SIZE);
}

String BD_SDCard::ReadFile(char *fileName)
{
    //char * content;
    String content;

    if (!SD.begin(chipSelect)) {
        Serial.println("initialization failed!");
        return "";
    }

    Serial.println("initialization done.");
    
    // re-open the file for reading:
    myFile = SD.open(fileName);

    if (myFile) 
    {        
        // read from the file until there's nothing else in it:
        while (myFile.available()) 
        {
            int c = myFile.read();

            //Serial.write(c);

            content += (String)((char)c);
        }

        // close the file:
        myFile.close();
    } 
    else 
    {
        // if the file didn't open, print an error:
        Serial.println("error opening file");
    }

    return content;

}

void BD_SDCard::WriteFile(char *fileName, char *data)
{
    if (!SD.begin(chipSelect)) 
    {
        Serial.println("initialization failed!");
        return;
    }

    Serial.println("initialization done.");

    // open the file. note that only one file can be open at a time,
    // so you have to close this one before opening another.
    myFile = SD.open(fileName, FILE_WRITE);
    
    // if the file opened okay, write to it:
    if (myFile) 
    {
        Serial.print("Writing to file...");
        //myFile.println("testing 1, 2, 3.");
        myFile.println(data);

        // close the file:
        myFile.close();
        Serial.println("done.");
    } 
    else 
    {
        // if the file didn't open, print an error:
        Serial.println("error opening file");
    }

}