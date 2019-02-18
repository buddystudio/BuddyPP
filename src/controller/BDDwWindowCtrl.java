/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.BDParameters;
import view.BDDwWindow;

/**
 *
 * @author gsh
 */
public class BDDwWindowCtrl 
{
    public BDDwWindowCtrl(BDDwWindow dwWindow, BDWorkspaceCtrl workspaceCtrl)
    {
        Matcher Variables;
        
        Matcher Variables2;
        
        // 临时字符串
        String tmpStr = workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.getCode();
        
        // 在字符串首加入一个换行符
        tmpStr = "/n" + tmpStr;
       
        // 匹配变量定义语句，不包含for循环内的计步变量
        Variables = Pattern.compile("[^(for)](int|int)(\\s+)([a-z0-9A-Z_ ]*)\\b").matcher(tmpStr);
        Variables2 = Pattern.compile("[^(for)](boolean|boolean)(\\s+)([a-z0-9A-Z_ ]*)\\b").matcher(tmpStr);

        // 清空变量
        dwWindow.value1CmbBox.getItems().clear();
        dwWindow.value2CmbBox.getItems().clear();
        
        //drWindow.value1CmbBox.getSelectionModel().select(-1);
        //drWindow.value2CmbBox.getSelectionModel().select(-1);
        
        dwWindow.value1CmbBox.setValue("");
        dwWindow.value2CmbBox.setValue("");
        
        while(Variables.find())
        {
            dwWindow.value1CmbBox.getItems().add(Variables.group(3));
        }
        
        while(Variables2.find())
        {
            dwWindow.value2CmbBox.getItems().add(Variables2.group(3));
        }
        
        for(int i = 0; i < 14; i++)
    	{
        	dwWindow.value1CmbBox.getItems().add("D" + i);
    	}
        
        if(BDParameters.boardType.equals("Arduino/Genuino Mega w/ ATmega2560") || 
           BDParameters.boardType.equals("Arduino Mega w/ ATmega1280"))
        {
        	for(int i = 14; i < 54; i++)
        	{
        		dwWindow.value1CmbBox.getItems().add("D" + i);
        	}
        }
        
        dwWindow.value2CmbBox.getItems().add("HIGH");
        dwWindow.value2CmbBox.getItems().add("LOW");
        
        //dwWindow.value2CmbBox.getSelectionModel().select(0);
        
        dwWindow.submitBtn.setOnAction(new EventHandler<ActionEvent>() 
        {    
            @Override
            public void handle(ActionEvent event) 
            {
            	String dgValue = dwWindow.value1CmbBox.getValue().toString();
            	
                // 去掉前缀“D”
            	if(dgValue.matches("[D][0-9]*"))
            	{
            		dgValue = dgValue.substring(1, dgValue.length());
            	}
                
                // 如果缺省输入
                if(dwWindow.value1CmbBox.getValue().equals("") || dwWindow.value2CmbBox.getValue().equals(""))
                {
                    return;
                }
                
                String code  = "";

                code += "digitalWrite(" + dgValue + ", " + dwWindow.value2CmbBox.getValue().toString() + ");";
             
                // 插入语句
                code = code.replaceAll("\"","\\\\\"");
                
                workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.insert(code);
                
                // 关闭窗口
                dwWindow.close();
            }
        });
    }
}
