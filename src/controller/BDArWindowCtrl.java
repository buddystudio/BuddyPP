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
import model.BDLang;
import model.BDParameters;
import view.BDArWindow;

/**
 *
 * @author gsh
 */
public class BDArWindowCtrl
{
    @SuppressWarnings("unchecked")
	public BDArWindowCtrl(BDArWindow arWindow, BDWorkspaceCtrl workspaceCtrl)
    {
        Matcher Variables;
        
        // 临时字符串
        String tmpStr = workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.getCode();
        
        // 在字符串首加入一个换行符
        tmpStr = "/n" + tmpStr;
       
        // 匹配变量定义语句，不包含for循环内的计步变量
        Variables = Pattern.compile("[^(for)](int|int|unsigned int)(\\s+)([a-z0-9A-Z_ ]*)\\b").matcher(tmpStr);

        // 清空变量
        arWindow.value1CmbBox.getItems().clear();
        arWindow.value2CmbBox.getItems().clear();
        
        arWindow.value1CmbBox.setValue("");
        arWindow.value2CmbBox.setValue("");
        
        while(Variables.find())
        {
            arWindow.value1CmbBox.getItems().add(Variables.group(3));
            arWindow.value2CmbBox.getItems().add(Variables.group(3));
        }
        
        for(int i = 0; i < 6; i++)
    	{
    		arWindow.value2CmbBox.getItems().add("A" + i);
    	}
        
        if(BDParameters.boardType.equals("Arduino/Genuino Mega w/ ATmega2560") || 
           BDParameters.boardType.equals("Arduino Mega w/ ATmega1280"))
        {
        	for(int i = 6; i < 16; i++)
        	{
        		arWindow.value2CmbBox.getItems().add("A" + i);
        	}
        }
        
        arWindow.submitBtn.setOnAction(new EventHandler<ActionEvent>() 
        {    
            @Override
            public void handle(ActionEvent event) 
            {
            	arWindow.value1CmbBox.setPromptText(BDLang.rb.getString("选择或输入变量名称"));
            	
            	String valName = arWindow.value1CmbBox.getValue().toString();
            	
            		// 如果缺省输入
                if(arWindow.value1CmbBox.getValue().equals("") || arWindow.value2CmbBox.getValue().equals(""))
                {
                    return;
                }
                
                // 判断输入变量命名规则
                if(!valName.matches("[a-zA-Z_$][a-zA-Z0-9_$]*"))
                {
	                	// 清空文本框内容
	                	arWindow.value1CmbBox.setValue(null);
	                	
	                	arWindow.value1CmbBox.setPromptText(BDLang.rb.getString("请输入正确的变量名"));
	                	
	                	return;
                }

                String code  = "";
                
                //System.out.println("Index: " + arWindow.value1CmbBox.getSelectionModel().getSelectedIndex());
                
                if(arWindow.value1CmbBox.getSelectionModel().getSelectedIndex() == -1)
                {
                    // 没有选中直接输入
                    code += "int " + arWindow.value1CmbBox.getValue().toString() + " = analogRead(" +  arWindow.value2CmbBox.getValue().toString() + ");";
                }
                else
                {
                    // 选变量
                    code += arWindow.value1CmbBox.getValue().toString() + " = analogRead(" +  arWindow.value2CmbBox.getValue().toString() + ");";
                }
             
                // 插入语句
                code = code.replaceAll("\"","\\\\\"");
                
                workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.insert(code);
                
                // 关闭窗口
                arWindow.close();
            }
        });
    }
}
