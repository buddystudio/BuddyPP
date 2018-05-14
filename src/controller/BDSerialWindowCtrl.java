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

import view.BDSerialWindow;

/**
 *
 * @author gsh
 */
public class BDSerialWindowCtrl 
{
    @SuppressWarnings("unchecked")
	public BDSerialWindowCtrl(BDSerialWindow comtWindow, BDWorkspaceCtrl workspaceCtrl)
    {
        Matcher Variables;
        
        // 临时字符串
        String tmpStr = workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getText();
        
        // 在字符串首加入一个换行符
        tmpStr = "/n" + tmpStr;
       
        // 匹配变量定义语句，不包含for循环内的计步变量
        Variables = Pattern.compile("[^(for)](String|string)(\\s+)([a-z0-9A-Z_ ]*)\\b").matcher(tmpStr);

        // 清空变量
        comtWindow.value1CmbBox.getItems().clear();
        comtWindow.value2CmbBox.getItems().clear();
        
        comtWindow.value1CmbBox.setValue("");
        
        while(Variables.find())
        {
            comtWindow.value1CmbBox.getItems().add(Variables.group(3));
        }

        comtWindow.value2CmbBox.getItems().add("300");
        comtWindow.value2CmbBox.getItems().add("1200");
        comtWindow.value2CmbBox.getItems().add("2400");
        comtWindow.value2CmbBox.getItems().add("4800");
        comtWindow.value2CmbBox.getItems().add("9600");
        comtWindow.value2CmbBox.getItems().add("14400");
        comtWindow.value2CmbBox.getItems().add("1920");
        comtWindow.value2CmbBox.getItems().add("28800");
        comtWindow.value2CmbBox.getItems().add("28800");
        comtWindow.value2CmbBox.getItems().add("38400");
        comtWindow.value2CmbBox.getItems().add("57600");
        comtWindow.value2CmbBox.getItems().add("115200");
        
        comtWindow.value2CmbBox.getSelectionModel().select(4);
        
        comtWindow.submitBtn.setOnAction(new EventHandler<ActionEvent>() 
        {    
            @Override
            public void handle(ActionEvent event) 
            {
            	// 获取词位
                int tabCount = workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.getCurColumn() - 1;
                
                String rate = comtWindow.value2CmbBox.getValue().toString();
                
                String code  = "Serial.begin(" + rate + ");  ";
                
                code += "// 建议将该行代码剪贴到setup()函数内。 \\n\\n";
                
                String valName = comtWindow.value1CmbBox.getValue().toString();
                
                // 没有选中变量和输入或不符合变量命名规则
                if(valName.isEmpty() || !valName.matches("[a-zA-Z_$][a-zA-Z0-9_$]*"))
                {
                	// 清空输入内容
                	comtWindow.value1CmbBox.setValue(null);
                	
                	// 更新提示内容
                	comtWindow.value1CmbBox.setPromptText("请输入正确变量名");
                	
                	return;
                }
                
                // 添加缩进
                for(int i = 0; i < tabCount; i++)
                {
                    code += " ";
                }

                if(comtWindow.value1CmbBox.getSelectionModel().getSelectedIndex() == -1)
            	{
            		code += "String " + comtWindow.value1CmbBox.getValue().toString() + " = \"\";\\n\\n";
            	}
            	else
            	{
            		// 没有选中直接输入
                    code += comtWindow.value1CmbBox.getValue().toString() + " = \"\";\\n\\n";
            	}

                // 添加缩进
                for(int i = 0; i < tabCount; i++)
                {
                    code += " ";
                }
                
                code += "while (Serial.available() > 0)\\n";
                
                // 添加缩进
                for(int i = 0; i < tabCount; i++)
                {
                    code += " ";
                }
                
                code += "{\\n";
                
                // 添加缩进
                for(int i = 0; i < tabCount + 4; i++)
                {
                    code += " ";
                }
                
                code += comtWindow.value1CmbBox.getValue().toString() + " += char(Serial.read());\\n";
                
                // 添加缩进
                for(int i = 0; i < tabCount + 4; i++)
                {
                    code += " ";
                }
                
                code += "delay(2);\\n";
                
                // 添加缩进
                for(int i = 0; i < tabCount; i++)
                {
                    code += " ";
                }
                
                code += "}\\n\\n";
                
                // 添加缩进
                for(int i = 0; i < tabCount; i++)
                {
                    code += " ";
                }
                
                code += "if(" + comtWindow.value1CmbBox.getValue().toString() + ".length() > 0)\\n";
                
                // 添加缩进
                for(int i = 0; i < tabCount; i++)
                {
                    code += " ";
                }
                
                code += "{\\n";
                
                // 添加缩进
                for(int i = 0; i < tabCount + 4; i++)
                {
                    code += " ";
                }
                
                code += "Serial.println(" + comtWindow.value1CmbBox.getValue().toString() + ");\\n";
                
                // 添加缩进
                for(int i = 0; i < tabCount + 4; i++)
                {
                    code += " ";
                }
                
                code += comtWindow.value1CmbBox.getValue().toString() + " = \"\";\\n";
                
                // 添加缩进
                for(int i = 0; i < tabCount; i++)
                {
                    code += " ";
                }
                
                //code += "}\\n";
                code += "}";

                // 插入语句
                code = code.replaceAll("\"","\\\\\"");
                
                workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.insert(code);
                
                // 关闭窗口
                comtWindow.close();
            }
        });
    }
}
