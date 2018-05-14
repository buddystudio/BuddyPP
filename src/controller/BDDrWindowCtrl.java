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

import view.BDDrWindow;

/**
 *
 * @author gsh
 */
class BDDrWindowCtrl 
{
    @SuppressWarnings("unchecked")
	public BDDrWindowCtrl(BDDrWindow drWindow, BDWorkspaceCtrl workspaceCtrl)
    {
        Matcher Variables;
        
        // 临时字符串
        String tmpStr = workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.getCode();
        
        // 在字符串首加入一个换行符
        tmpStr = "/n" + tmpStr;
       
        // 匹配变量定义语句，不包含for循环内的计步变量
        Variables = Pattern.compile("[^(for)](int|int|unsigned int)(\\s+)([a-z0-9A-Z_ ]*)\\b").matcher(tmpStr);

        // 清空变量
        drWindow.value1CmbBox.getItems().clear();
        drWindow.value2CmbBox.getItems().clear();
        
        //drWindow.value1CmbBox.getSelectionModel().select(-1);
        //drWindow.value2CmbBox.getSelectionModel().select(-1);
        
        drWindow.value1CmbBox.setValue("");
        drWindow.value2CmbBox.setValue("");
        
        while(Variables.find())
        {
            drWindow.value1CmbBox.getItems().add(Variables.group(3));
            drWindow.value2CmbBox.getItems().add(Variables.group(3));
        }
        
        drWindow.value2CmbBox.getItems().add("D0");
        drWindow.value2CmbBox.getItems().add("D1");
        drWindow.value2CmbBox.getItems().add("D2");
        drWindow.value2CmbBox.getItems().add("D3");
        drWindow.value2CmbBox.getItems().add("D4");
        drWindow.value2CmbBox.getItems().add("D5");
        drWindow.value2CmbBox.getItems().add("D6");
        drWindow.value2CmbBox.getItems().add("D7");
        drWindow.value2CmbBox.getItems().add("D8");
        drWindow.value2CmbBox.getItems().add("D9");
        drWindow.value2CmbBox.getItems().add("D10");
        drWindow.value2CmbBox.getItems().add("D11");
        drWindow.value2CmbBox.getItems().add("D12");
        drWindow.value2CmbBox.getItems().add("D13");
        
        drWindow.submitBtn.setOnAction(new EventHandler<ActionEvent>() 
        {    
            @Override
            public void handle(ActionEvent event) 
            {
            	drWindow.value1CmbBox.setPromptText("选择或输入变量名称");
            	
            	String valName = drWindow.value1CmbBox.getValue().toString();
                
                // 如果缺省输入
                if(drWindow.value1CmbBox.getValue().equals("") || drWindow.value2CmbBox.getValue().equals(""))
                {
                    return;
                }
                
                // 判断输入变量命名规则
                if(!valName.matches("[a-zA-Z_$][a-zA-Z0-9_$]*"))
                {
	                	// 清空文本框内容
	                	drWindow.value1CmbBox.setValue(null);
	                	
	                	drWindow.value1CmbBox.setPromptText("请输入正确的变量名");
                	
	                	return;
                }
                
                String code  = "";
                
                //System.out.println("Index: " + drWindow.value1CmbBox.getSelectionModel().getSelectedIndex());
                
                String dgValue = drWindow.value2CmbBox.getValue().toString();
            	
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
                
                if(drWindow.value1CmbBox.getSelectionModel().getSelectedIndex() == -1)
                {
                    // 没有选中直接输入
                    code += "int " + drWindow.value1CmbBox.getValue().toString() + " = digitalRead(" +  dgValue + ");";
                }
                else
                {		
                    // 选变量
                    code += drWindow.value1CmbBox.getValue().toString() + " = digitalRead(" +  dgValue + ");";
                }
             
                code = code.replaceAll("\"","\\\\\"");
                
                workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.insert(code);

                // 关闭窗口
                drWindow.close();
            }
        });
    }
}
