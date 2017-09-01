/*
  FAT32.cpp - FAT32 library
  Copyright (c) 2009 SparkFun Electronics.  All right reserved.
  Written by Ryan Owens
  This appication is based on the FAT library published by Roland Riegal
  http://www.roland-riegel.de/

  This library is free software; you can redistribute it and/or
  modify it under the terms of the GNU Lesser General Public
  License as published by the Free Software Foundation; either
  version 2.1 of the License, or (at your option) any later version.

  This library is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
  Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public
  License along with this library; if not, write to the Free Software
  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
*/

/******************************************************************************
 * Includes
 ******************************************************************************/

#include "WConstants.h"
#include <stdio.h>
#include <avr/io.h>
#include <avr/interrupt.h>
#include <string.h>

#include "fat.h"
#include "fat_config.h"
#include "partition.h"
#include "sd_raw.h"
#include "sd_raw_config.h"

#include "FAT16.h"

/******************************************************************************
 * Definitions
 ******************************************************************************/

/******************************************************************************
 * Constructors
 ******************************************************************************/
//When the class is constructed, initialize the filesystem
FAT::FAT()
{
	
}

/******************************************************************************
 * User API
 ******************************************************************************/
char FAT::initialize()
{
	//setup sd card slot 
	if(!sd_raw_init())
	{
		return 0;
	}

	//open first partition
	_partition = partition_open(sd_raw_read,
									sd_raw_read_interval,
									sd_raw_write,
									sd_raw_write_interval,
									0
							   );

	if(!_partition)
	{
		//If the partition did not open, assume the storage device
		//is a "superfloppy", i.e. has no MBR.
		_partition = partition_open(sd_raw_read,
								   sd_raw_read_interval,
								   sd_raw_write,
								   sd_raw_write_interval,
								   -1
								  );
		if(!_partition)
		{
			return 0;
		}
	}

	//Open file system
	//struct fat_fs_struct* fs = fat_open(partition);
	_fs = fat_open(_partition);
	if(!_fs)
	{
		return 0;
	}

	//Open root directory
	//struct fat_dir_entry_struct directory;
	fat_get_dir_entry_of_path(_fs, "/", &_directory);

	//struct fat_dir_struct* dd = fat_open_dir(fs, &directory);
	_dd=fat_open_dir(_fs, &_directory);
	if(!_dd)
	{
		return 0;
	}
	return 1;
}

char FAT::create_file(char * file_name)
{
	struct fat_dir_entry_struct file_entry;
	char name[32];
	
	_file_name=file_name;
	sprintf(name, file_name);
	//Check to see if a file with the same name has been created. If so, get out!
	if(find_file_in_dir(_fs,_dd, name,&_directory))
		return 0;
	//Try creating the file in the system
	if(!fat_create_file(_dd, name, &file_entry))
		return 0;
	
	
	return 1;
}

void FAT::write(const char * data)
{
	//Always write to the end of the file.
	fat_seek_file(_file_handle, 0, FAT_SEEK_END);
	fat_write_file(_file_handle, (uint8_t *)data, strlen(data));
	sd_raw_sync();
}

void FAT::close()
{
	fat_close_file(_file_handle);
}

int FAT::read(char * data_buffer)
{
	return fat_read_file(_file_handle, (uint8_t *)data_buffer, 512);
}

void FAT::open()
{
	_file_handle=open_file_in_dir(_fs, _dd, _file_name);
}