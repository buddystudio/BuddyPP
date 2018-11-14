/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.BDParameters;
import view.BDAwWindow;

/**
 *
 * @author gsh
 */
public class BDAwWindowCtrl 
{
	//private static final Logger logger=LogManager.getLogger(BDAwWindowCtrl.class);
	
    public BDAwWindowCtrl(BDAwWindow awWindow, BDWorkspaceCtrl workspaceCtrl)
    {
        Matcher Variables;
        
        // 临时字符串
        String tmpStr = workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.getCode();
        
        // 在字符串首加入一个换行符
        tmpStr = "/n" + tmpStr;
       
        // 匹配变量定义语句，不包含for循环内的计步变量
        Variables = Pattern.compile("[^(for)](int|int|unsigned int)(\\s+)([a-z0-9A-Z_ ]*)\\b").matcher(tmpStr);

        // 清空变量
        awWindow.value1CmbBox.getItems().clear();
        awWindow.value2CmbBox.getItems().clear();
        
        //drWindow.value1CmbBox.getSelectionModel().select(-1);
        //drWindow.value2CmbBox.getSelectionModel().select(-1);
        
        awWindow.value1CmbBox.setValue("");
        awWindow.value2CmbBox.setValue("");
        
        while(Variables.find())
        {
            awWindow.value1CmbBox.getItems().add(Variables.group(3));
            awWindow.value2CmbBox.getItems().add(Variables.group(3));
        }
        
        for(int i = 0; i < 6; i++)
    	{
        	awWindow.value1CmbBox.getItems().add("A" + i);
    	}
        
        if(BDParameters.boardType.equals("Arduino/Genuino Mega w/ ATmega2560") || 
           BDParameters.boardType.equals("Arduino Mega w/ ATmega1280"))
        {
        	for(int i = 6; i < 16; i++)
        	{
        		awWindow.value1CmbBox.getItems().add("A" + i);
        	}
        }
        
        awWindow.submitBtn.setOnAction(new EventHandler<ActionEvent>() 
        {    
            @Override
            public void handle(ActionEvent event) 
            {
                // 如果缺省输入
                if(awWindow.value1CmbBox.getValue().equals("") || awWindow.value2CmbBox.getValue().equals(""))
                {
                    return;
                }
                
                String value = awWindow.value2CmbBox.getValue().toString();
                
                try
                {
                    int num = Integer.parseInt(value);

                    if(num < 0 || num > 255)
                    {
                        awWindow.value2CmbBox.setValue("");
                        
                        awWindow.value2CmbBox.setPromptText("0~255");

                        return;
                    }
                }
                catch (NumberFormatException e)
                {
                	//logger.error("",e);
                    //return;
                }

                String code  = "";
                code += "analogWrite(" + awWindow.value1CmbBox.getValue().toString() + ", " + awWindow.value2CmbBox.getValue().toString() + ");";
             
                // 插入语句
                code = code.replaceAll("\"","\\\\\"");
                
                workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.insert(code);
                
                // 关闭窗口
                awWindow.close();
            }
        });
    }
}
