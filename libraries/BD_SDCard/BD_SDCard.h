#include <Arduino.h>
#include <SPI.h>
#include <SD.h>

#define UNIT_b 1
#define UNIT_Kb 2
#define UNIT_Mb 3

class BD_SDCard
{
	private:

		uint8_t csPin;

		Sd2Card card;
        SdVolume volume;
        SdFile root;

        int chipSelect;

        File myFile;

        String cardType;
        String volumeType;

        uint32_t volumesize;

        void printDirectory(File dir, int numTabs);
		
	public:

		void Init();
        void ShowList();

        String ReadFile(char *fileName);
        void WriteFile(char *fileName, char *data);

        String GetCardType();
        String GetVolumeType();

        uint32_t GetVolumeSize();
        uint32_t GetVolumeSize(uint8_t unit);

};