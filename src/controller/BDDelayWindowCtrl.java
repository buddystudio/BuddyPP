/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.BDCodeAgent;
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
                
                //workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
                
                // 插入代码
                BDCodeAgent codeAgent = new BDCodeAgent(workspaceCtrl.workspaceView.workspaceModel.curTab);
                
                codeAgent.insert(code);
                
               // System.out.println(combType.getValue().toString() + " " + txtName.getText() + " " + txtValue.getText() + ";");
                //System.out.println("delay(" + delayWindow.timeTxt.getText() + ");");
                
                //textArea.insert("123", textArea.getCaretPosition());
                
                // 关闭窗口
                delayWindow.root.close();
            }
        });
    }
}
