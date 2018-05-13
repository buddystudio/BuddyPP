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
import model.BDCodeAgent;
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
        String tmpStr = workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getText();
        
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
        
        dwWindow.value1CmbBox.getItems().add("D0");
        dwWindow.value1CmbBox.getItems().add("D1");
        dwWindow.value1CmbBox.getItems().add("D2");
        dwWindow.value1CmbBox.getItems().add("D3");
        dwWindow.value1CmbBox.getItems().add("D4");
        dwWindow.value1CmbBox.getItems().add("D5");
        dwWindow.value1CmbBox.getItems().add("D6");
        dwWindow.value1CmbBox.getItems().add("D7");
        dwWindow.value1CmbBox.getItems().add("D8");
        dwWindow.value1CmbBox.getItems().add("D9");
        dwWindow.value1CmbBox.getItems().add("D10");
        dwWindow.value1CmbBox.getItems().add("D11");
        dwWindow.value1CmbBox.getItems().add("D12");
        dwWindow.value1CmbBox.getItems().add("D13");
        
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
                switch(dgValue)
            	{
            		case "D0":
            			dgValue = "0";
            			break;
            		case "D1":
            			dgValue = "1";
            			break;
            		case "D2":
            			dgValue = "2";
            			break;
            		case "D3":
            			dgValue = "3";
            			break;
            		case "D4":
            			dgValue = "4";
            			break;
            		case "D5":
            			dgValue = "5";
            			break;
            		case "D6":
            			dgValue = "6";
            			break;
            		case "D7":
            			dgValue = "7";
            			break;
            		case "D8":
            			dgValue = "8";
            			break;
            		case "D9":
            			dgValue = "9";
            			break;
            		case "D10":
            			dgValue = "10";
            			break;
            		case "D11":
            			dgValue = "11";
            			break;
            		case "D12":
            			dgValue = "12";
            			break;
            		case "D13":
            			dgValue = "13";
            			break;
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
