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
        pinList.add("A0");
        pinList.add("A1");
        pinList.add("A2");
        pinList.add("A3");
        pinList.add("A4");
        pinList.add("A5");
        
        // 添加数字端口
        pinList.add("D0");
        pinList.add("D1");
        pinList.add("D2");
        pinList.add("D3");
        pinList.add("D4");
        pinList.add("D5");
        pinList.add("D6");
        pinList.add("D7");
        pinList.add("D8");
        pinList.add("D9");
        pinList.add("D10");
        pinList.add("D11");
        pinList.add("D12");
        pinList.add("D13");
        
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
