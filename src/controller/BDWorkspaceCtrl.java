/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.AWTException;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import model.BDCodeModel;
import model.BDCodeTabModel;
import model.BDConsoleTabModel;
import model.BDParameters;

import view.BDWorkspaceView;

/**
 *
 * @author gsh
 */
public final class BDWorkspaceCtrl 
{
    public BDWorkspaceView workspaceView;
    
    //private static final Logger logger = LogManager.getLogger();
    
    public BDWorkspaceCtrl(BDWorkspaceView workspaceView)
    {
        this.workspaceView = workspaceView;
       
        try 
        {
            // 启动时至少有一个代码标签页
            //this.addConsoleTab();
            this.addNewTab("INO");
            
            // 启动时显示开始页面可新建打开文件
        } 
        catch (AWTException ex) 
        {
        	//logger.error("",ex);
            //Logger.getLogger(BDWorkspaceCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addTab(BDCodeModel newCode, String type) throws AWTException
    { 
    	// 获取编辑器参数
        BDParameters.getEditorProfile();
        
        boolean isRepeat = false;
        
        // 判断文件是否重复打开
        for(int i = 0; i < this.workspaceView.workspaceModel.tabList.size(); i++)
        {
        	String path01 = this.workspaceView.workspaceModel.tabList.get(i).code.path;
        	String path02 = newCode.path;

        	// 路径比对
        	if(path01.compareTo(path02) == 0)
        	{
        		isRepeat = true;
        		
        		return;
        	}
        }
        
        // 当前拖入文件已经打开
        if(isRepeat == true)
        {
        	System.out.println("repeat");
        	
        	return;
        }
        
        //BDCodeModel newCode = new BDCodeModel();
        BDCodeTabModel newTab = new BDCodeTabModel(newCode, "INO", this);
        
        newTab.code = newCode;
        
        this.workspaceView.workspaceModel.tabList.add(newTab);
        
        this.workspaceView.getTabs().add(newTab.tab);
        
        this.workspaceView.workspaceModel.curTab = newTab;
        
        //this.workspaceView.getSelectionModel().select(this.workspaceView.getTabs().size() - 1);
        this.workspaceView.getSelectionModel().select(newTab.tab);
        
        // 创建标签控制器
        BDTabCtrl tabCtrl = new BDTabCtrl(newTab, this);
        
        tabCtrl.workspaceView = this.workspaceView;
        
        // Select the new code tab.
        //newTab.tab.getTabPane().getSelectionModel().get
    }
    
    // 创建一个源码标签页
    public void addNewTab(String type) throws AWTException
    { 
        BDCodeModel newCode = new BDCodeModel(type);
        
        BDCodeTabModel newTab = new BDCodeTabModel(newCode, type, this);
        
        this.workspaceView.workspaceModel.tabList.add(newTab);

        this.workspaceView.workspaceModel.curTab = newTab;
        
        this.workspaceView.getTabs().add(newTab.tab);

        // 创建标签控制器
        BDTabCtrl tabCtrl = new BDTabCtrl(newTab, this);
        
        tabCtrl.workspaceView = this.workspaceView;
        
        //this.workspaceView.getSelectionModel().select(newTab.tab);
        
        //this.workspaceView.getSelectionModel().select(this.workspaceView.getTabs().size() - 1);
        
        // 命名序号自加
        BDParameters.codeIdCount++;
        
        // Select the first code tab;
        newTab.tab.getTabPane().getSelectionModel().selectLast();
    }
    
    // 添加控制台标签页
    public void addConsoleTab() throws AWTException
    { 
    	BDConsoleTabModel newTab = new BDConsoleTabModel(this);
    	
    	this.workspaceView.getTabs().add(newTab.tab);
    }
}
