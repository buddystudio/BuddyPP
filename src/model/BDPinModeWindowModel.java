/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author gsh
 */
public class BDPinModeWindowModel 
{
    //ObservableList<String> pinList = FXCollections.observableArrayList("A0","A1","A2");
    public ObservableList<String> pinList = FXCollections.observableArrayList();    // 端口列表
    public ObservableList<String> modeList = FXCollections.observableArrayList();   // 端口状态（INPUT / OUTPUT）
    
    public BDPinModeWindowModel()
    {
        // 添加模拟端口
        for(int i = 0; i < 6; i++)
    	{
        	 pinList.add("A" + i);
    	}
        
        if(BDParameters.boardType.equals("Arduino/Genuino Mega w/ ATmega2560") || 
           BDParameters.boardType.equals("Arduino Mega w/ ATmega1280"))
        {
        	for(int i = 6; i < 16; i++)
        	{
        		 pinList.add("A" + i);
        	}
        }
        
        // 添加数字端口
        for(int i = 0; i < 14; i++)
    	{
        	 pinList.add("D" + i);
    	}
        
        if(BDParameters.boardType.equals("Arduino/Genuino Mega w/ ATmega2560") || 
                BDParameters.boardType.equals("Arduino Mega w/ ATmega1280"))
        {
         	for(int i = 14; i < 54; i++)
        	{
            	pinList.add("D" + i);
            }
        }
        
        // 添加端口状态
        modeList.add("INPUT");
        modeList.add("OUTPUT");
        
        // Other mode.
        modeList.add("OUTPUT_OPEN_DRAIN");
        modeList.add("INPUT_ANALOG");
        modeList.add("INPUT_PULLUP");
        modeList.add("INPUT_PULLDOWN");
        modeList.add("INPUT_FLOATING");
        modeList.add("PWM");
        modeList.add("PWM_OPEN_DRAIN");
        
    }
}
