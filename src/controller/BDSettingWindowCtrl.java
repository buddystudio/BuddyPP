/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.RadioMenuItem;
import model.BDLibsModel;
import model.BDParameters;
import util.io.BDCodeReader;
import view.BDLibWindow;
import view.BDSettingWindow;

/**
 *
 * @author gsh
 */
public class BDSettingWindowCtrl
{
	private static final Logger logger = LogManager.getLogger();
	private String acePath = "/resources/ace-builds-master/src-noconflict";
	
    public BDSettingWindowCtrl(BDSettingWindow settingWindow, BDWorkspaceCtrl workspaceCtrl)
    {
    	// 获取编辑器参数
        BDParameters.getEditorProfile();
        
    	String selectedTheme = "";
    	String selectedSize = "";
    	
    	selectedTheme = BDParameters.editorTheme;
    	selectedSize = BDParameters.editorFontSize;     
		
    	for(int i = 9; i < 31; i++)
    	{
    		settingWindow.strList2.add(i + " px");
    	}
    	
    	File dirFile = new File(this.getClass().getResource("/").getPath() + this.acePath);
	    
	    if (!dirFile.exists()) 
	    {  
            System.out.println("do not exit");
            
            return ;  
        }
	    
	    String[] fileList = dirFile.list();
	    
	    for (int i = 0; i < fileList.length; i++) 
	    {
	    	File file = new File(dirFile.getPath(), fileList[i]);  
            
	    	String name = file.getName();

            if(name.substring(0, 6).equals("theme-"))
            {
            	settingWindow.strList.add(name.substring(6, name.length() - 3));	
            }      
	    }
	    
	    // 选择当前主题选项
	    for(int i = 0; i < settingWindow.strList.size(); i++)
    	{
    		if(settingWindow.strList.get(i).equals(selectedTheme))
    		{
    			settingWindow.themeList.getSelectionModel().select(i);
    		}
    	}
	    
	    // 选择当前字体大小选项
    	for(int i = 0; i < settingWindow.strList2.size(); i++)
    	{
    		if(settingWindow.strList2.get(i).equals(selectedSize + " px"))
    		{
    			settingWindow.sizeList.getSelectionModel().select(i);
    		}
    	}
    	
    	settingWindow.importBtn.setOnAction(new EventHandler<ActionEvent>() 
    	{    
            @Override
            public void handle(ActionEvent event) 
            {
            	String theme = settingWindow.themeList.getSelectionModel().getSelectedItem();
            	String fontSize = settingWindow.sizeList.getSelectionModel().getSelectedItem();
            	
            	// 写入新的编辑器参数
            	BDParameters.setEditorProfile(theme, fontSize);
            	
            	int size = Integer.parseInt(BDParameters.editorFontSize.substring(0, BDParameters.editorFontSize.length() - 2));
            	
            	// 初始化编辑器
            	//workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.setTheme(BDParameters.editorTheme);
            	//workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.setFontSize(size);
            	
            	for(int i = 0; i < workspaceCtrl.workspaceView.workspaceModel.tabList.size(); i++)
            	{
            		workspaceCtrl.workspaceView.workspaceModel.tabList.get(i).editorCtrl.setTheme(BDParameters.editorTheme);
            		workspaceCtrl.workspaceView.workspaceModel.tabList.get(i).editorCtrl.setFontSize(size);
            	}

                // 关闭窗口
            	settingWindow.close();
            }
        });
    }
}
