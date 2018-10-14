/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import util.base.Preferences;
import util.debug.BDSerial;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import model.BDParameters;
import model.BDWindowsManager;
import view.BDMenuView;
import view.BDPreSettingWindow;

/**
 *
 * @author gsh
 */
public class BDPreSetWindowCtrl 
{
    public BDPreSettingWindow preSettingWindow;
    public BDMenuView menuView;
            
    public BDPreSetWindowCtrl(BDMenuView menuView) throws Exception
    {
        this.menuView = menuView;
        //this.preSettingWindow = menuView.psw;
        this.preSettingWindow = BDWindowsManager.psw;
        
        Preferences.init(null);
        Preferences.set("upload.verbose", "true");    
        
        if(preSettingWindow.combPin.getItems().size()==0)        
        { 
            preSettingWindow.combPin.setValue("未连接");
            
            // 弹出设置按钮时判断开发板是否已经连接并提示
            menuView.lbCom.setText("当前串口：" + preSettingWindow.combPin.getValue().toString());
            
            BDParameters.serialports.clear();
            BDParameters.connectCom = null;
        }
        else
        {    
            BDParameters.serialports = BDSerial.list();
            
            Preferences.set("serial.port", preSettingWindow.combPin.getSelectionModel().getSelectedItem().toString());
            
            BDParameters.connectCom = Preferences.get("serial.port");
        }
        
        preSettingWindow.btnSubmit.setOnAction(new EventHandler<ActionEvent>() 
        {    
            @Override
            public void handle(ActionEvent event) 
            {
                if(BDParameters.serialports.isEmpty())
                {
                    // 如果未连接则返回
                    //return;
                }
                
                menuView.lbBoard.setText("当前板型：" + preSettingWindow.combMode.getValue().toString());
                menuView.lbCom.setText("当前串口：" + preSettingWindow.combPin.getValue().toString());
                
                Preferences.set("serial.port", preSettingWindow.combPin.getSelectionModel().getSelectedItem().toString());
                
                // 设置开发板型号和串口序号
                BDParameters.boardType  = preSettingWindow.combMode.getValue().getType();
                Preferences.set("board",BDParameters.boardType);
                BDParameters.connectCom = preSettingWindow.combPin.getValue().toString();
                Preferences.save();
                preSettingWindow.close();
            }
        });
        
        // 暂时屏蔽显示编译细节的选项
        /*
        preSettingWindow.chkShowDebug.selectedProperty().addListener((ov, old_val, new_val)->{
			if(new_val){
				Preferences.set("showdebug", "True");
			}
			else{
				Preferences.set("showdebug", "False");
			}
			Preferences.save();
		});
		*/
		
    }
}
