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
import view.BDIfWindow;

/**
 *
 * @author gsh
 */
public class BDIfWindowCtrl 
{
    public BDIfWindow ifWindow;
    
    public BDIfWindowCtrl(BDIfWindow ifWindow, BDWorkspaceCtrl workspaceCtrl)
    {
        this.ifWindow = ifWindow;
        
        Matcher Variables;
        
        // 临时字符串
        String tmpStr = workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getText();
        
        // 在字符串首加入一个换行符
        tmpStr = "/n" + tmpStr;
       
        // 匹配变量定义语句，不包含for循环内的计步变量
        Variables = Pattern.compile("[^(for)](int|float|double|boolean|char|unsigned int|unsigned long)(\\s+)([a-z0-9A-Z_ ]*)\\b").matcher(tmpStr);
        
        // 清空变量
        this.ifWindow.value1CmbBox.getItems().clear();
        this.ifWindow.value2CmbBox.getItems().clear();
        
        this.ifWindow.value1CmbBox.setValue("");
        this.ifWindow.value2CmbBox.setValue("");
        
        // 默认选项
        this.ifWindow.optCmbBox.getSelectionModel().select(0);
        
        while(Variables.find())
        {
            //System.out.println(Variables.group(3));
            
            ifWindow.value1CmbBox.getItems().add(Variables.group(3));
            ifWindow.value2CmbBox.getItems().add(Variables.group(3));
        }
        
        ifWindow.submitBtn.setOnAction(new EventHandler<ActionEvent>() 
        {    
            @Override
            public void handle(ActionEvent event) 
            {
                // 获取词位
                int tabCount = workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.getCurColumn() - 1;
                
                // 生成语句
                String code = "";
                
                code += "if(" + ifWindow.value1CmbBox.getValue().toString() + " " + ifWindow.optCmbBox.getValue().toString() + " " + ifWindow.value2CmbBox.getValue().toString() + ")\\n";
                
                // 如果两个条件为空返回则条件为true
                if(ifWindow.value1CmbBox.getValue().equals("") && ifWindow.value2CmbBox.getValue().equals(""))
                {
                    code = "if(1)\\n";
                }
                else if(ifWindow.value1CmbBox.getValue().equals("") || ifWindow.value2CmbBox.getValue().equals(""))
                {
                    // 条件输入不完整
                    if(ifWindow.value1CmbBox.getValue().equals(""))
                    {
                        ifWindow.value1CmbBox.setPromptText("输入条件");
                    }
                    else
                    {
                        ifWindow.value2CmbBox.setPromptText("输入条件");
                    }
  
                    return;
                }
                
                // 添加缩进
                for(int i = 0; i < tabCount; i++)
                {
                    code += " ";
                }    
                
                code += "{\\n";
                
                // 添加缩进
                for(int i = 0; i < tabCount; i++)
                {
                    code += " ";
                } 
                
                code += "\\n";
                
                // 添加缩进
                for(int i = 0; i < tabCount; i++)
                {
                    code += " ";
                } 
                
                //code += "}\\n";
                code += "}";
                
                // 判断分支
                if(ifWindow.isBranch.isSelected() == true)
                {
                	code += "\\n";
                	
                    // 添加缩进
                    for(int i = 0; i < tabCount; i++)
                    {
                        code += " ";
                    } 
                    
                    code += "else\\n";
                    
                    // 添加缩进
                    for(int i = 0; i < tabCount; i++)
                    {
                        code += " ";
                    }
                    
                    code += "{\\n";
                    
                    // 添加缩进
                    for(int i = 0; i < tabCount; i++)
                    {
                        code += " ";
                    }
                    
                    code += "\\n";
                    
                    // 添加缩进
                    for(int i = 0; i < tabCount; i++)
                    {
                        code += " ";
                    }
                    
                    //code += "}\\n";
                    code += "}";
                }

                // 插入语句
                code = code.replaceAll("\"","\\\\\"");
                
                workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.insert(code);
                
                // 关闭窗口
                ifWindow.close();
            }
        });
    }
}
