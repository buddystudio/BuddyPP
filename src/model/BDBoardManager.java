package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BDBoardManager
{
	private ObservableList<BDBoardModel> boards = FXCollections.observableArrayList();
	
	public BDBoardManager()
	{
		// 加载板型信息
        BDParameters.loadBoardsInfo();
		
		// 添加拓展板型信息
		for(int i = 0; i < BDParameters.exBoardsList.size(); i++)
		{
			BDBoardInfoModel board = BDParameters.exBoardsList.get(i);
			
			boards.add(new BDBoardModel(board.getBoardName(), "ex", "", "", "", "", "",""));
		}

		boards.add(new BDBoardModel("Arduino/Genuino Uno", 
                					"avrdude", 
                					"115200", 
                					"atmega328p", 
                					"AVR_UNO", 
                					"arduino", 
                					"arduino",
									"arduino:avr:uno"));
		
		boards.add(new BDBoardModel("Arduino/Genuino Micro", 
									"avrdude", 
									"57600", 
									"atmega32u4", 
									"AVR_UNO", 
									"arduino", 
									"avr109",
									"arduino:avr:micro"));

		boards.add(new BDBoardModel("Arduino Leonardo", 
									"avrdude", 
									"57600", 
									"atmega32u4", 
									"AVR_LEONARDO", 
									"arduino", 
									"avr109",
									"arduino:avr:leonardo"));
		
		boards.add(new BDBoardModel("Arduino Leonardo ETH", 
									"avrdude", 
									"57600", 
									"atmega32u4", 
									"AVR_LEONARDO_ETH", 
									"arduino", 
									"avr109",
									"arduino:avr:leonardoeth"));
		
		boards.add(new BDBoardModel("Arduino Esplora", 
									"avrdude", 
									"57600", 
									"atmega32u4", 
									"AVR_ESPLORA", 
									"arduino", 
									"avr109",
									"arduino:avr:esplora"));
		
		boards.add(new BDBoardModel("Arduino Fio", 
									"avrdude", 
									"57600", 
									"atmega328p", 
									"AVR_FIO", 
									"arduino", 
									"arduino",
									"arduino:avr:fio"));
		
		boards.add(new BDBoardModel("Arduino BT w/ ATmega328P", 
									"avrdude", 
									"19200", 
									"atmega328p", 
									"AVR_BT", 
									"arduino", 
									"arduino",
									"arduino:avr:bt:cpu=atmega328"));
		
		boards.add(new BDBoardModel("Arduino BT w/ ATmega168", 
									"avrdude", 
									"19200", 
									"atmega168", 
									"AVR_BT", 
									"arduino", 
									"arduino",
									"arduino:avr:bt:cpu=atmega168"));
		
		boards.add(new BDBoardModel("Arduino Nano w/ ATmega328P", 
									"avrdude", 
									"57600", 
									"atmega328p", 
									"AVR_NANO", 
									"arduino", 
									"arduino",
									"arduino:avr:nano:cpu=atmega328"));
		
		boards.add(new BDBoardModel("Arduino Nano w/ ATmega168", 
									"avrdude", 
									"19200", 
									"atmega168", 
									"AVR_NANO", 
									"arduino", 
									"arduino",
									"arduino:avr:nano:cpu=atmega168"));
		
		boards.add(new BDBoardModel("Arduino/Genuino Mega w/ ATmega2560", 
									"avrdude", 
									"115200", 
									"atmega2560", 
									"AVR_MEGA2560", 
									"arduino", 
									"wiring",
									"arduino:avr:mega:cpu=atmega2560"));

		boards.add(new BDBoardModel("Arduino Mega w/ ATmega1280", 
									"avrdude", 
									"57600", 
									"atmega1280", 
									"AVR_MEGA", 
									"arduino", 
									"arduino",
									"arduino:avr:mega:cpu=atmega1280"));
		
		boards.add(new BDBoardModel("LilyPad Arduino USB", 
									"avrdude", 
									"57600", 
									"atmega32u4", 
									"AVR_LILYPAD", 
									"arduino", 
									"avr109",
									"arduino:avr:LilyPadUSB"));
		
		boards.add(new BDBoardModel("LilyPad Arduino w/ ATmega328", 
									"avrdude", 
									"57600", 
									"atmega328p", 
									"AVR_LILYPAD", 
									"arduino", 
									"arduino",
									"arduino:avr:lilypad:cpu=atmega328"));
		
		boards.add(new BDBoardModel("LilyPad Arduino w/ ATmega168", 
									"avrdude", 
									"19200", 
									"atmega168", 
									"AVR_LILYPAD", 
									"arduino", 
									"arduino",
									"arduino:avr:lilypad:cpu=atmega168"));
		
		boards.add(new BDBoardModel("Arduino Mini w/ ATmega328P", 
									"avrdude", 
									"115200", 
									"atmega328p", 
									"AVR_MINI", 
									"arduino", 
									"arduino",
									"arduino:avr:mini:cpu=atmega328"));
		
		boards.add(new BDBoardModel("Arduino Mini w/ ATmega168", 
									"avrdude", 
									"19200", 
									"atmega168", 
									"AVR_MINI", 
									"arduino", 
									"arduino",
									"arduino:avr:mini:cpu=atmega168"));
		
		boards.add(new BDBoardModel("Arduino Pro or Pro Mini (5V, 16 MHz) w/ ATmega328P", 
									"avrdude", 
									"57600", 
									"atmega328p", 
									"AVR_PRO", 
									"arduino", 
									"arduino",
									"arduino:avr:pro:cpu=16MHzatmega328"));
		
		boards.add(new BDBoardModel("Arduino Pro or Pro Mini (3.3V, 8 MHz) w/ ATmega328P", 
									"avrdude", 
									"57600", 
									"atmega328p", 
									"AVR_PRO", 
									"arduino", 
									"arduino",
									"arduino:avr:pro:cpu=8MHzatmega328"));
		
		boards.add(new BDBoardModel("Arduino Pro or Pro Mini (5V, 16 MHz) w/ ATmega168", 
									"avrdude", 
									"19200", 
									"atmega168", 
									"AVR_PRO", 
									"arduino", 
									"arduino",
									"arduino:avr:pro:cpu=16MHzatmega168"));
		
		boards.add(new BDBoardModel("Arduino Pro or Pro Mini (3.3V, 8 MHz) w/ ATmega168", 
									"avrdude", 
									"19200", 
									"atmega168", 
									"AVR_PRO", 
									"arduino", 
									"arduino",
									"arduino:avr:pro:cpu=8MHzatmega168"));
		
		boards.add(new BDBoardModel("Arduino Duemilanove or Diecimila w/ ATmega328P", 
									"avrdude", 
									"57600", 
									"atmega328p", 
									"AVR_DUEMILANOVE", 
									"arduino", 
									"arduino",
									"arduino:avr:diecimila:cpu=atmega328"));
		
		boards.add(new BDBoardModel("Arduino Duemilanove or Diecimila w/ ATmega168", 
									"avrdude", 
									"19200", 
									"atmega168", 
									"AVR_DUEMILANOVE", 
									"arduino", 
									"arduino",
									"arduino:avr:diecimila:cpu=atmega168"));
		
		boards.add(new BDBoardModel("Arduino Yún", 
									"avrdude", 
									"57600", 
									"atmega32u4", 
									"AVR_YUN", 
									"arduino", 
									"avr109",
									"arduino:avr:yun"));
		
		boards.add(new BDBoardModel("Arduino Yún Mini", 
									"avrdude", 
									"57600", 
									"atmega32u4", 
									"AVR_YUNMINI", 
									"arduino", 
									"avr109",
									"arduino:avr:yunmini"));
		
		boards.add(new BDBoardModel("Arduino Uno WiFi", 
									"avrdude", 
									"115200", 
									"atmega328p", 
									"AVR_UNO_WIFI_DEV_ED", 
									"arduino", 
									"arduino",
									"arduino:avr:unowifi"));
		
		boards.add(new BDBoardModel("Arduino Ethernet", 
									"avrdude", 
									"115200", 
									"atmega328p", 
									"AVR_UNO_WIFI_DEV_ED", 
									"arduino", 
									"arduino",
									"arduino:avr:ethernet"));
	}
	
	public ObservableList<String> getBoardsList()
	{
		ObservableList<String> boardList = FXCollections.observableArrayList("");
		
		boardList.clear();
		
		for(int i = 0; i < boards.size(); i++)
		{
			boardList.add(boards.get(i).getName());
			
			//System.out.println(boards.get(i).getName());
		}
		
		return boardList;
	}
	
	public ObservableList<BDBoardModel> getBoards()
	{
		return boards;
	}

}
