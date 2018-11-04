/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.BDLang;
import model.BDParameters;
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
    	
    	//File dirFile = new File(this.getClass().getResource("/").getPath() + this.acePath);
    	
    	String curPath = this.getClass().getResource("/").getPath();
    	String filePath = curPath + "resources/ace-builds-master/src-noconflict";
	    
	    //File dirFile = new File("E:/Projects/ACE4JavaFX/bin/resources/ace-builds-master/src-noconflict");
	    //File dirFile = new File(this.getClass().getResource("/").getPath() + this.acePath);
    	File dirFile = new File(filePath);
	    
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
    	
    	settingWindow.cancelBtn.setOnAction(new EventHandler<ActionEvent>() 
    	{    
            @Override
            public void handle(ActionEvent event) 
            {
                // 关闭窗口
            	settingWindow.close();
            }
        });
    	
    	settingWindow.submitBtn.setOnAction(new EventHandler<ActionEvent>() 
    	{    
            @Override
            public void handle(ActionEvent event) 
            {
            	String theme = settingWindow.themeList.getSelectionModel().getSelectedItem();
            	String fontSize = settingWindow.sizeList.getSelectionModel().getSelectedItem();
            	
            	// 写入新的编辑器参数
            	BDParameters.editorTheme = theme;
            	BDParameters.editorFontSize = fontSize.replace(" ", "");

            	int size = Integer.parseInt(BDParameters.editorFontSize.substring(0, BDParameters.editorFontSize.length() - 2));
            	
            	// 更新当前设置
            	BDParameters.editorTheme = theme;
            	
            	String tSize = fontSize.substring(0, fontSize.length() - 2);
            	
            	tSize = tSize.replace(" ", "");
            	
            	BDParameters.editorFontSize = tSize;
            	BDParameters.txtAreafontSize = size;

            	for(int i = 0; i < workspaceCtrl.workspaceView.workspaceModel.tabList.size(); i++)
            	{
            		workspaceCtrl.workspaceView.workspaceModel.tabList.get(i).editorCtrl.setTheme(BDParameters.editorTheme);
            		workspaceCtrl.workspaceView.workspaceModel.tabList.get(i).editorCtrl.setFontSize(size);
            	}
            	
            	// 更新语言
            	String lang = settingWindow.langList.getSelectionModel().getSelectedItem().toString();
            	
            	if(lang.equals("简体中文"))
            	{
            		BDLang.locale = new Locale("zh", "CN");
            	}
            	else if(lang.equals("繁體中文"))
            	{
            		BDLang.locale = new Locale("zh", "HK");
            	}
            	else if(lang.equals("English"))
            	{
            		BDLang.locale = new Locale("en", "US");
            	}
            	
            	BDParameters.langues = lang;
            	
            	if(settingWindow.isCustomChk.isSelected() == true)
            	{
            		BDParameters.editorIsCustom = "1";
            	}
            	else
            	{
            		BDParameters.editorIsCustom = "0";
            	}
            	
            	// 把新的参数写入配置文件
            	BDParameters.writeProfile();

                // 关闭窗口
            	settingWindow.close();
            }
        });
    }
}
