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
import view.BDWhileWindow;

/**
 *
 * @author gsh
 */
public class BDWhileWindowCtrl 
{
    public BDWhileWindow whileWindow;
    
    public BDWhileWindowCtrl(BDWhileWindow whileWindow, BDWorkspaceCtrl workspaceCtrl)
    {
        this.whileWindow = whileWindow;
        
        Matcher Variables;
        
        // 临时字符串
        String tmpStr = workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getText();
        
        // 在字符串首加入一个换行符
        tmpStr = "/n" + tmpStr;
       
        // 匹配变量定义语句，不包含for循环内的计步变量
        Variables = Pattern.compile("[^(for)](int|float|double|boolean|char|unsigned int|unsigned long)(\\s+)([a-z0-9A-Z_ ]*)\\b").matcher(tmpStr);
        
        // 清空变量
        this.whileWindow.value1CmbBox.getItems().clear();
        this.whileWindow.value2CmbBox.getItems().clear();
        
        this.whileWindow.value1CmbBox.setValue("");
        this.whileWindow.value2CmbBox.setValue("");
        
        // 默认选项
        this.whileWindow.optCmbBox.getSelectionModel().select(0);
        
        while(Variables.find())
        {
            //System.out.println(Variables.group(3));
            
            whileWindow.value1CmbBox.getItems().add(Variables.group(3));
            whileWindow.value2CmbBox.getItems().add(Variables.group(3));
        }
        
        whileWindow.submitBtn.setOnAction(new EventHandler<ActionEvent>() 
        {    
            @Override
            public void handle(ActionEvent event) 
            {
                // 获取词位
            	int tabCount = workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.getCurColumn() - 1;
                
                // 生成语句
                String code = "";

                // 判断do-while
                if(whileWindow.isBranch.isSelected() == true)
                {
                    code += "do\\n";
                    
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
                    
                    code += "}\\n";
                    
                    // 添加缩进
                    for(int i = 0; i < tabCount; i++)
                    {
                        code += " ";
                    }    
                    
                    if(whileWindow.value1CmbBox.getValue().equals("") && whileWindow.value2CmbBox.getValue().equals(""))
                    {
                        code += "while(1);";
                    }
                    else
                    {
                        // 如果输入不完整
                        if(whileWindow.value1CmbBox.getValue().equals("") || whileWindow.value2CmbBox.getValue().equals(""))
                        {
                            if(whileWindow.value1CmbBox.getValue().equals(""))
                            {
                                whileWindow.value1CmbBox.setPromptText("请输入条件");
                            }
                            
                            if(whileWindow.value2CmbBox.getValue().equals(""))
                            {
                                whileWindow.value2CmbBox.setPromptText("请输入条件");
                            }
                            
                            return;
                        }

                        code += "while(" + whileWindow.value1CmbBox.getValue().toString() + " " + whileWindow.optCmbBox.getValue().toString() + " " + whileWindow.value2CmbBox.getValue().toString() + ");";
                    }
                    
                }
                else
                {
                    if(whileWindow.value1CmbBox.getValue().equals("") && whileWindow.value2CmbBox.getValue().equals(""))
                    {
                        code += "while(1);\\n";
                    }
                    else
                    {
                        code += "while(" + whileWindow.value1CmbBox.getValue().toString() + " " + whileWindow.optCmbBox.getValue().toString() + " " + whileWindow.value2CmbBox.getValue().toString() + ")\\n";
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
                }
                
                // 插入语句
                code = code.replaceAll("\"","\\\\\"");
                
                workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.insert(code);
                
                // 关闭窗口
                whileWindow.close();
            }
        });
    }
}
