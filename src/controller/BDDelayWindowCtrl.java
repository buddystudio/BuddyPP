/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import view.BDDelayWindow;

/**
 *
 * @author gsh
 */
public class BDDelayWindowCtrl 
{
    public BDDelayWindowCtrl(BDDelayWindow delayWindow, BDWorkspaceCtrl workspaceCtrl)
    {
        // 清空输入内容
        delayWindow.timeTxt.setText("");
        
        delayWindow.submitBtn.setOnAction(new EventHandler<ActionEvent>() 
        {    
            @Override
            public void handle(ActionEvent event) 
            {
                String value = delayWindow.timeTxt.getText();
                
                // 验证输入
                try
                {
                    if(Integer.parseInt(value) < 0)
                    {
                        delayWindow.timeTxt.setText("");
                        
                        delayWindow.timeTxt.setPromptText("输入正确的数据");
                        
                        return;
                    }   
                }
                catch (NumberFormatException e)
                {
                    delayWindow.timeTxt.setText("");
                    
                    delayWindow.timeTxt.setPromptText("输入正确的数据");
                        
                    return;
                }
                
                // 生成语句
                String code = "delay(" + value + ");";
                
                // 插入语句
                code = code.replaceAll("\"","\\\\\"");
                
                workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.insert(code);
                
                // 关闭窗口
                delayWindow.root.close();
            }
        });
    }
}
