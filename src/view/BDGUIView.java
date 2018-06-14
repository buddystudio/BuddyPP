/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.BDGuideViewCtrl;
import controller.BDMenuCtrl;
import controller.BDSettingWindowCtrl;
import controller.BDToolsCtrl;
import controller.BDWorkspaceCtrl;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import model.BDGUIModel;
import model.BDParameters;

/**
 *
 * @author gsh
 */
public class BDGUIView 
{
    public Stage primaryStage;
    
    public BorderPane root  = new BorderPane();
    public VBox topPanel    = new VBox();
    
    public BDTitleView  titlePanel          = new BDTitleView();
    public BDMenuView   menuPanel           = new BDMenuView();
    public BDToolsView  toolsPanel          = new BDToolsView();
    public BDWorkspaceView workspacePanel   = new BDWorkspaceView();
    public BDGuideView consolePanel         = new BDGuideView(); 
    
    public BDMenuCtrl menuCtrl              = new BDMenuCtrl(menuPanel);
    public BDToolsCtrl toolsCtrl            = new BDToolsCtrl(toolsPanel);
    public BDWorkspaceCtrl workspaceCtrl    = new BDWorkspaceCtrl(workspacePanel);
    public BDGuideViewCtrl consoleCtrl      = new BDGuideViewCtrl(consolePanel, workspaceCtrl, menuPanel);
    
    public BDSettingWindow settingWindow	= new BDSettingWindow();
    public BDSettingWindowCtrl settingWindowCtrl = new BDSettingWindowCtrl(settingWindow, workspaceCtrl);
    
    public BDAboutWindow aboutWindow = new BDAboutWindow();	// 关于我们窗口

    public Rectangle2D visualBounds;
    
    public BDGUIModel guiModel = new BDGUIModel();
    
    public BDDialogWindow saveWindow;
    
    public MenuBar menuBar = new MenuBar();
     
    public BDGUIView(Stage primaryStage)
    {
        // 把工作区控制器传入菜单控制器
        menuCtrl.workspaceCtrl = this.workspaceCtrl;
        
        // 把工作区控制器传入工具控制器
        toolsCtrl.workspaceCtrl = this.workspaceCtrl;
        
        // 设置快捷键
        menuCtrl.setHotKey();
        
        this.primaryStage = primaryStage;
        
        this.primaryStage.setTitle("Buddy++");
        
        // 定义无边框窗体
        primaryStage.initStyle(StageStyle.UNDECORATED);
        
        // 获取屏幕尺寸（不包含任务栏）
        this.visualBounds = Screen.getPrimary().getVisualBounds();
        
        // Set application icon.
        this.primaryStage.getIcons().add(new Image("/images/icon_64.png"));
        
        // Set main panel style
        String panelStyle = "";
        
        panelStyle += "-fx-background-radius:2px;";
        panelStyle += "-fx-border-color: #333333;";
        panelStyle += "-fx-border-width:2px;";
        panelStyle += "-fx-border-radius:3px;";
        panelStyle += "-fx-background:transparent;";
        
        root.setStyle(panelStyle);

        topPanel.getChildren().add(this.titlePanel);
        topPanel.getChildren().add(this.menuPanel);
        
        this.root.setTop(this.topPanel);
        this.root.setLeft(this.toolsPanel);
        this.root.setCenter(this.workspacePanel);
        
        // 右侧栏暂时屏蔽
        //this.root.setRight(this.consolePanel); 
        
        // 添加菜单
        this.addMenuBar();
        
        // 初始化主窗口并设置尺寸
        //Scene scene = new Scene(this.root, 1024 - 110, 640 + 10 + 10);
        Scene scene = new Scene(this.root, 940, 640 + 10 + 10);
        
        this.root.setFocusTraversable(true);

        // 引用界面CSS样式
        scene.getStylesheets().add("style/titleStyle.css"); 
        scene.getStylesheets().add("style/menuStyle.css");
        scene.getStylesheets().add("style/workspaceStyle.css");
        scene.getStylesheets().add("style/toolsStyle.css");
        scene.getStylesheets().add("style/consoleStyle.css");
        scene.getStylesheets().add("style/magnetStyle.css");
        scene.getStylesheets().add("style/msgWindowStyle.css");
        
        // Set panel background transparent.
        scene.setFill(Color.TRANSPARENT);
        
        primaryStage.setTitle("Buddy++");
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }
    
    // File menu - new, save, exit
    public Menu fileMenu = new Menu("File");
    
    public MenuItem newMenuItem  = new MenuItem("New");
    public MenuItem openMenuItem = new MenuItem("Open");
    public MenuItem saveMenuItem = new MenuItem("Save");
    public MenuItem saveAsMenuItem = new MenuItem("Save As");
    public MenuItem exitMenuItem = new MenuItem("Exit");
    
    public Menu editMenu = new Menu("Edit");
    
    public MenuItem clearItem 	= new MenuItem("Clear");
    public MenuItem undoItem 	= new MenuItem("Undo");
    public MenuItem redoItem 	= new MenuItem("Redo");
    public MenuItem copyItem 	= new MenuItem("Copy");
    public MenuItem cutItem  	= new MenuItem("Cut");
    public MenuItem pasteItem 	= new MenuItem("Paste");
    public MenuItem searchItem = new MenuItem("Search");
    
    public Menu toolsMenu = new Menu("Tools");
    
    public MenuItem exampleItem = new MenuItem("Example");
    public MenuItem librariesItem = new MenuItem("Libraries");
    public MenuItem serialItem = new MenuItem("Serial");
    
    public Menu runMenu = new Menu("Run");
    
    public MenuItem compileItem = new MenuItem("Compile");
    public MenuItem uploadItem = new MenuItem("Upload");
    public MenuItem compileAndUploadItem = new MenuItem("Compile And UploadItem");
    
    public Menu helpMenu = new Menu("Help");
    
    public MenuItem settingItem = new MenuItem("Setting");
    public MenuItem aboutItem = new MenuItem("About");
    
    public void addMenuBar()
    {
    	MenuBar menuBar = new MenuBar();
    	
	    fileMenu.getItems().addAll(newMenuItem, openMenuItem ,saveMenuItem, saveAsMenuItem, new SeparatorMenuItem(), exitMenuItem);
	    editMenu.getItems().addAll(clearItem, undoItem, redoItem, copyItem, cutItem, pasteItem, searchItem);
	    toolsMenu.getItems().addAll(exampleItem, librariesItem, serialItem);
	    runMenu.getItems().addAll(compileItem, uploadItem, compileAndUploadItem);
	    helpMenu.getItems().addAll(settingItem, aboutItem);
	    
	    menuBar.setUseSystemMenuBar(true); 
	    
	    menuBar.getMenus().addAll(fileMenu, editMenu, toolsMenu, runMenu, helpMenu);
	    
        // 设置不可见的菜单（Mac OS）
        if(BDParameters.os.equals("Mac OS X"))
	 	{
        	this.root.setBottom(menuBar);
	 	}
    }
}
