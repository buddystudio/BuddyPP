/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import view.BDPinModeWindow;

/**
 *
 * @author gsh
 */
class BDPinModeWindowCtrl 
{
    public BDPinModeWindowCtrl(BDPinModeWindow pinModeWindow, BDWorkspaceCtrl workspaceCtrl)
    {
        pinModeWindow.submitBtn.setOnAction(new EventHandler<ActionEvent>() 
        {    
            @Override
            public void handle(ActionEvent event) 
            {
                // 验证输入
            	String dgValue = pinModeWindow.combPin.getValue().toString();
            	
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
