/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.BDParameters;
import model.BDWindowsManager;
import util.base.BDDrawUtil;
import view.BDToolsView;
import view.BDWindow;

/**
 *
 * @author gsh
 */
public class BDToolsCtrl 
{
    public BDWorkspaceCtrl workspaceCtrl;
    
    public BDToolsCtrl(BDToolsView toolsView)
    {
       // 定义变量窗口弹出
       toolsView.toolsDe.setOnAction(new EventHandler<ActionEvent>() 
       {
            @Override
            public void handle(ActionEvent event) 
            {
                BDDefineVariableWindowCtrl defineVariableWindowCtrl = new BDDefineVariableWindowCtrl(BDWindowsManager.dfvWindow, workspaceCtrl);
                
                // 弹出变量定义窗口
                //toolsView.dfvWindow.show();
                BDWindowsManager.dfvWindow.show();
                
                BDDrawUtil.showInTheMiddle(BDWindowsManager.dfvWindow);
            }
        });
       
       // If条件分支窗口弹出
       toolsView.toolsIF.setOnAction(new EventHandler<ActionEvent>() 
       {
            @Override
            public void handle(ActionEvent event) 
            {
                BDIfWindowCtrl ifWindowCtrl = new BDIfWindowCtrl(BDWindowsManager.ifWindow, workspaceCtrl);
                
                BDWindowsManager.ifWindow.show();
                
                BDDrawUtil.showInTheMiddle(BDWindowsManager.ifWindow);
            }
        });
       
       // PinMode端口设置窗口弹出
       toolsView.toolsPinMode.setOnAction(new EventHandler<ActionEvent>() 
       {
            @Override
            public void handle(ActionEvent event) 
            {
                BDPinModeWindowCtrl pinModeWindowCtrl = new BDPinModeWindowCtrl(BDWindowsManager.pinModeWindow, workspaceCtrl);
                
                // 弹出端口定义窗口
                BDWindowsManager.pinModeWindow.show();
                
                BDDrawUtil.showInTheMiddle(BDWindowsManager.pinModeWindow);
            }
        });
       
       // While条件循环窗口弹出
       toolsView.toolsWhile.setOnAction(new EventHandler<ActionEvent>() 
       {  
            @Override
            public void handle(ActionEvent event) 
            {   
                BDWhileWindowCtrl whileWindowCtrl = new BDWhileWindowCtrl(BDWindowsManager.whileWindow, workspaceCtrl);

                // 弹出while循环语句设置窗口
                BDWindowsManager.whileWindow.show();
                
                BDDrawUtil.showInTheMiddle(BDWindowsManager.whileWindow);
            }
        });
       
       // For条件循环窗口弹出
       toolsView.toolsFor.setOnAction(new EventHandler<ActionEvent>() 
       {  
            @Override
            public void handle(ActionEvent event) 
            {   
                //forWindow = new BDForWindow();
                
                BDForWindowCtrl forWindowCtrl = new BDForWindowCtrl(BDWindowsManager.forWindow, workspaceCtrl);

                // 弹出循环语句设置窗口
                BDWindowsManager.forWindow.show();
                
                BDDrawUtil.showInTheMiddle(BDWindowsManager.forWindow);
            }
        });
       
       // Switch-Case条件循环窗口弹出
       toolsView.toolsSwitch.setOnAction(new EventHandler<ActionEvent>() 
       {  
            @Override
            public void handle(ActionEvent event) 
            {   

                BDSwitchWindowCtrl switchWindowCtrl = new BDSwitchWindowCtrl(BDWindowsManager.switchWindow, workspaceCtrl);

                // 弹出循环语句设置窗口
                BDWindowsManager.switchWindow.show();
                
                BDDrawUtil.showInTheMiddle(BDWindowsManager.switchWindow);
            }
        });
       
       // digitalRead数字端口读取窗口弹出
       toolsView.toolsDR.setOnAction(new EventHandler<ActionEvent>() 
       {
            @Override
            public void handle(ActionEvent event) 
            {
                //ifWindow = new BDIfWindow();
                
                BDDrWindowCtrl drWindowCtrl = new BDDrWindowCtrl(BDWindowsManager.drWindow, workspaceCtrl);
                
                BDWindowsManager.drWindow.show();
                
                BDDrawUtil.showInTheMiddle(BDWindowsManager.drWindow);
            }
        });
       
       // analogRead模拟端口读取窗口弹出
       toolsView.toolsAR.setOnAction(new EventHandler<ActionEvent>() 
       {
            @Override
            public void handle(ActionEvent event) 
            {
                BDArWindowCtrl arWindowCtrl = new BDArWindowCtrl(BDWindowsManager.arWindow, workspaceCtrl);
                
                BDWindowsManager.arWindow.show();
                
                BDDrawUtil.showInTheMiddle(BDWindowsManager.arWindow);
            }
        });
       
       // digitalWrite数字端口写入窗口弹出
       toolsView.toolsDW.setOnAction(new EventHandler<ActionEvent>() 
       {
            @Override
            public void handle(ActionEvent event) 
            {
                BDDwWindowCtrl dwWindowCtrl = new BDDwWindowCtrl(BDWindowsManager.dwWindow, workspaceCtrl);
                
                BDWindowsManager.dwWindow.show();
                
                BDDrawUtil.showInTheMiddle(BDWindowsManager.dwWindow);
            }
        });
       
       // analogWrite模拟端口写入窗口弹出
       toolsView.toolsAW.setOnAction(new EventHandler<ActionEvent>() 
       {
            @Override
            public void handle(ActionEvent event) 
            {
                BDAwWindowCtrl awWindowCtrl = new BDAwWindowCtrl(BDWindowsManager.awWindow, workspaceCtrl);
                
                BDWindowsManager.awWindow.show();
                
                BDDrawUtil.showInTheMiddle(BDWindowsManager.awWindow);
            }
        });
       
       // Delay延时设置窗口弹出
       toolsView.toolsDelay.setOnAction(new EventHandler<ActionEvent>() 
       {
            @Override
            public void handle(ActionEvent event) 
            {
                //delayWindow = new BDDelayWindow();
                
                // 设置延时函数控制器
                BDDelayWindowCtrl delayWindowCtrl = new BDDelayWindowCtrl(BDWindowsManager.delayWindow, workspaceCtrl);

                // 弹出延时设置窗口
                BDWindowsManager.delayWindow.show();
                
                BDDrawUtil.showInTheMiddle(BDWindowsManager.delayWindow);
            }
        });
       
       // Serial串口通讯模板窗口弹出
       toolsView.toolsSerial.setOnAction(new EventHandler<ActionEvent>() 
       {
            @Override
            public void handle(ActionEvent event) 
            {
                // 创建串口模版生成控制器
                BDSerialWindowCtrl comtWindowCtrl = new BDSerialWindowCtrl(BDWindowsManager.serialWindow, workspaceCtrl);
                
                // 显示串口通讯模板
                BDWindowsManager.serialWindow.show();
                
                BDDrawUtil.showInTheMiddle(BDWindowsManager.serialWindow);
            }
        });
       
       // 扩展工具窗口弹出
       toolsView.toolsPlugs.setOnAction(new EventHandler<ActionEvent>() 
       {
            @Override
            public void handle(ActionEvent event) 
            {
            	// 弹出扩展工具窗口
            	BDPluginWindowCtrl pluginWindowVtrl = new BDPluginWindowCtrl(BDWindowsManager.pluginWindow, workspaceCtrl);
            	
            	BDWindowsManager.pluginWindow.show();
            	
            	BDDrawUtil.showInTheMiddle(BDWindowsManager.pluginWindow);
            }
        });
       
       /*
       // 8*8 LED阵列生成工具
       toolsView.toolsLEDs.setOnAction(new EventHandler<ActionEvent>() 
       {
            
            @Override
            public void handle(ActionEvent event) 
            {
                toolsView.ledsWindow = new BDLEDsWindow();
                
                // 创建控制器
                BDLEDsWindowCtrl ledsWindowCtrl = new BDLEDsWindowCtrl(toolsView.ledsWindow, workspaceCtrl);
                
                // 显示工具窗口
                toolsView.ledsWindow.show();
            }
        });
        */
    }
}
