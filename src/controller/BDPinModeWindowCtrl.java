/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.BDPinModeWindowModel;
import view.BDPinModeWindow;

/**
 *
 * @author gsh
 */
class BDPinModeWindowCtrl 
{
    public BDPinModeWindowCtrl(BDPinModeWindow pinModeWindow, BDWorkspaceCtrl workspaceCtrl)
    {
    	// 更新列表选项
    	BDPinModeWindowModel pmwm = new BDPinModeWindowModel();
        
    	pinModeWindow.combPin.setItems(pmwm.pinList);
    	pinModeWindow.combMode.setItems(pmwm.modeList);
    	
    	pinModeWindow.combPin.getSelectionModel().select(0);
    	pinModeWindow.combMode.getSelectionModel().select(1);
    	
        pinModeWindow.submitBtn.setOnAction(new EventHandler<ActionEvent>() 
        {    
            @Override
            public void handle(ActionEvent event) 
            {
                // 验证输入
            	String dgValue = pinModeWindow.combPin.getValue().toString();
            	
                // 去掉前缀“D”
            	if(dgValue.substring(0, 1).equals("D"))
            	{
            		dgValue = dgValue.substring(1, dgValue.length());
            	}
            	
                // 生成语句
                String code = "pinMode(" + dgValue + ", " + pinModeWindow.combMode.getValue() + ");";
             
                // 插入语句
                code = code.replaceAll("\"","\\\\\"");
                
                workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.insert(code);
                //workspaceCtrl.workspaceView.workspaceModel.curTab.editorView.webView.requestFocus();
                
                // 关闭窗口
                pinModeWindow.close();
            }
        });
    }
}
