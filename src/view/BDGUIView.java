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
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.BDDocTabModel;
import model.BDGUIModel;
import model.BDParameters;
import model.BDRefTabModel;
import util.base.BDDrawUtil;

/**
 *
 * @author gsh
 */
public class BDGUIView 
{
    public Stage primaryStage;
    
    public BorderPane window		= new BorderPane();
    public BorderPane root  		= new BorderPane();
    public VBox topPanel    		= new VBox();
    public BorderPane workspaceRoot	= new BorderPane();
    
    public BDTitleView  titlePanel          = new BDTitleView();
    public BDMenuView   menuPanel           = new BDMenuView();
    public BDToolsView  toolsPanel          = new BDToolsView();
    public BDWorkspaceView workspacePanel   = new BDWorkspaceView();
    public BDGuideView guidePanel         	= new BDGuideView(); 
    
    public SplitPane splitPanel 			= new SplitPane();
    public BorderPane consolePanel 			= new BorderPane();
    //public CodeArea msgArea					= new CodeArea();
    public VBox dividerPanel 				= new VBox();
    
    public BDMenuCtrl menuCtrl              = new BDMenuCtrl(menuPanel);
    public BDToolsCtrl toolsCtrl            = new BDToolsCtrl(toolsPanel);
    public BDWorkspaceCtrl workspaceCtrl    = new BDWorkspaceCtrl(workspacePanel);
    public BDGuideViewCtrl consoleCtrl      = new BDGuideViewCtrl(guidePanel , workspaceCtrl, menuPanel);
    
    public BDSettingWindow settingWindow	= new BDSettingWindow();
    public BDSettingWindowCtrl settingWindowCtrl = new BDSettingWindowCtrl(settingWindow, workspaceCtrl);
    
    public BDAboutWindow aboutWindow = new BDAboutWindow();	// 关于我们窗口

    public Rectangle2D visualBounds;
    
    public BDGUIModel guiModel = new BDGUIModel();
    
    public BDDialogWindow saveWindow;
    
    public MenuBar menuBar = new MenuBar();
    
    public Image iconArrowRightImg = new Image("resources/images/arrow_right.png");
    public Image iconArrowLeftImg = new Image("resources/images/arrow_left.png");
    
    public Image iconClear01Img = new Image("resources/images/console_clear_icon_01.jpg");
    public Image iconClear02Img = new Image("resources/images/console_clear_icon_02.jpg");
    
    public Image iconCopy01Img = new Image("resources/images/console_copy_icon_01.jpg");
    public Image iconCopy02Img = new Image("resources/images/console_copy_icon_02.jpg");
    
    public ImageView arrowBtn = new ImageView(iconArrowLeftImg);
    public ImageView clearBtn = new ImageView(iconClear01Img);
    public ImageView copyBtn = new ImageView(iconCopy01Img);
    
    public ContextMenu menu = new ContextMenu();
    
    public MenuItem clearMenuItem 		= new MenuItem("  清除  ");
    public MenuItem copyMenuItem 		= new MenuItem("  复制  ");
    public MenuItem selectAllMenuItem 	= new MenuItem("  全选  ");
     
    public BDGUIView(Stage primaryStage)
    {
        // 把工作区控制器传入菜单控制器
        menuCtrl.workspaceCtrl = this.workspaceCtrl;
        
        // 把控制台信息组件传入菜单视图
        //menuPanel.consloeArea = this.msgArea;
        menuPanel.splitPanel = this.splitPanel;
        menuPanel.primaryStage = primaryStage;
        
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
        this.primaryStage.getIcons().add(new Image("resources/images/icon_64.png"));
        
        // Set main panel style
        String panelStyle = "";
        
        // 设置主窗体边框
        panelStyle += "-fx-background-radius:2px;";
        panelStyle += "-fx-border-color: #333333;";
        panelStyle += "-fx-border-width:2px;";
        panelStyle += "-fx-border-radius:1px;";
        panelStyle += "-fx-background:transparent;";
        
        String panelStyle2 = "";
        
        // 设置主窗体边框
        panelStyle2 += "-fx-background-radius:0px;";
        panelStyle2 += "-fx-border-color: #333333;";
        panelStyle2 += "-fx-border-width:3px;";
        panelStyle2 += "-fx-border-radius:0px;";
        panelStyle2 += "-fx-background:transparent;";
        
        window.setStyle(panelStyle);
        root.setStyle(panelStyle2);
        

        //topPanel.getChildren().add(this.titlePanel);
        topPanel.getChildren().add(this.menuPanel);
        
        consolePanel.setStyle("-fx-background-color: #ffffff;");
        
        // 添加窗体拉伸效果
        BDDrawUtil.addDrawFunc(primaryStage, root);
        
        // 横向菜单（清除 / 复制 / 帮助）
        HBox consoleTitlePanel = new HBox();
        
        consoleTitlePanel.setAlignment(Pos.CENTER_LEFT);
        //consoleTitlePanel.getChildren().add(clearBtn);
        //consoleTitlePanel.getChildren().add(copyBtn);
        
        BorderPane consoleMsgPanel = new BorderPane();	// 控制台信息
        
        consoleTitlePanel.setStyle("-fx-background-color: #444444;");
        //consoleTitlePanel.setPadding(new Insets(0, 0, 38, 0));
        //consoleTitlePanel.setPrefHeight(33);
        //consoleTitlePanel.setPrefHeight(38);

        dividerPanel.setStyle("-fx-background-color: #444444;");
        dividerPanel.setPrefWidth(8);

        //consolePanel.setTop(consoleTitlePanel);
        //consolePanel.setCenter(consoleMsgPanel);
        
        String curPath = this.getClass().getResource("/").getPath();
    	String refUrl = "file://" + curPath + "resources/reference/cn/index.html";
    	String docUrl = "file://" + curPath + "resources/doc/index.html";
    	
    	if(BDParameters.langues.equals("English"))
    	{
    		refUrl = "file://" + curPath + "resources/reference/en/HomePage.html";
    	}

        BDWebView refView = new BDWebView(refUrl);
        BDWebView docView = new BDWebView(docUrl);
        
        //BDMsgTabModel msgTabModel = new BDMsgTabModel(consoleMsgPanel);
        BDRefTabModel refTabModel = new BDRefTabModel(refView);
        BDDocTabModel docTabModel = new BDDocTabModel(docView);
        
        TabPane msgTab = new TabPane();

        // 屏蔽控制台标签
        //msgTab.getTabs().add(msgTabModel.tab);
        
        msgTab.getTabs().add(refTabModel.tab);
        msgTab.getTabs().add(docTabModel.tab);
        
        //msgTab.setStyle(".tab-pane:top *.tab-header-area{-fx-padding: 7 0 -1 0;}");
        
        consolePanel.setCenter(msgTab);

        workspaceRoot.setCenter(workspacePanel);
        workspaceRoot.setRight(dividerPanel);
        
        dividerPanel.setAlignment(Pos.CENTER);
        dividerPanel.getChildren().add(arrowBtn);

        // 设置右侧控制台最多占60%空间
        //consolePanel.maxWidthProperty().bind(splitPanel.widthProperty().multiply(0.6));
        workspaceRoot.minWidthProperty().bind(splitPanel.widthProperty().multiply(0.4));

        splitPanel.setDividerPosition(0, 1);
        SplitPane.setResizableWithParent(consolePanel, false);	// 锁定右侧面板
        splitPanel.getItems().addAll(this.workspaceRoot, consolePanel);

        // 初始化布局
        this.root.setTop(this.topPanel);
        this.root.setLeft(this.toolsPanel);
        this.root.setCenter(this.splitPanel);
        
        window.setTop(this.titlePanel);
        window.setCenter(this.root);
        
        //BDTextAreaConsole consoleTxt = BDTextAreaConsole.getTextAreaConsoleInstance();
        
        //consoleTxt.gui = this;
        
        // 为控制台添加右键菜单
        //menu.getItems().addAll(clearMenuItem, copyMenuItem, selectAllMenuItem);
        
        // 屏蔽原来控制台信息的功能
        /*msgArea.setParagraphGraphicFactory(LineNumberFactory.get(msgArea));
        msgArea.setEditable(false);
        //msgArea.setPrefHeight(535);
        msgArea.setAutoScrollOnDragDesired(true);
        //msgArea.setWrapText(true);
        //msgArea.autosize();
		msgArea.setContextMenu(menu);
        msgArea.getStylesheets().add("resources/style/compileStyle.css");
        
        // 设置控制台信息高亮
        msgArea.richChanges().filter(ch -> !ch.getInserted().equals(ch.getRemoved())).subscribe(change -> 
        {
        	Platform.runLater(new Runnable() 
	        {
	            @Override
	            public void run() 
	            {
	            	// 如果当前控制台信息为空则返回
	            	if(msgArea.getText().length() == 0)
	            	{
	            		return;
	            	}
	            	
	                // 更新JavaFX的主线程的代码放在此处
	            	msgArea.setStyleSpans(0, BDTextAreaConsole.computeHighlighting(msgArea.getText()));
	            }
	        });
        	
        	// 光标跟随
        	msgArea.setEstimatedScrollY(msgArea.getCaretPosition());
		});*/
   
        //consoleMsgPanel.setTop(consoleTitlePanel);
        //consoleMsgPanel.setCenter(msgArea);

        // 右侧栏暂时屏蔽
        //this.root.setRight(this.guidePanel); 
        
        // 添加菜单
        this.addMenuBar();
        
        //BDParameters.getProfile();
        
        double edtWidth = BDParameters.defWidth;
        double edtHeight = BDParameters.defHeight;
        
        double edtPosX = Double.parseDouble(BDParameters.editorPosX);
        double edtPosY = Double.parseDouble(BDParameters.editorPosY);
        
        if(BDParameters.editorIsCustom.equals("1"))
        {
        	edtWidth = Double.parseDouble(BDParameters.editorWidth);
            edtHeight = Double.parseDouble(BDParameters.editorHeight);
            
        	if(edtWidth < BDParameters.minWidth)
        	{
        		edtWidth = BDParameters.minWidth;
        	}
        	
        	if(edtHeight < BDParameters.minHeight)
        	{
        		edtHeight = BDParameters.minHeight;
        	}
        	
        	primaryStage.setX(edtPosX);
            primaryStage.setY(edtPosY);
        }
        
        // 初始化主窗口并设置尺寸
        //Scene scene = new Scene(this.root, 1024 - 110, 640 + 10 + 10);
        //Scene scene = new Scene(window, 940, 640 + 10 + 10);
        //Scene scene = new Scene(window, 900, 670 + 10 + 10);
        Scene scene = new Scene(window, edtWidth, edtHeight);
        
        this.root.setFocusTraversable(true);

        // 引用界面CSS样式
        scene.getStylesheets().add("resources/style/titleStyle.css"); 
        scene.getStylesheets().add("resources/style/menuStyle.css");
        scene.getStylesheets().add("resources/style/workspaceStyle.css");
        scene.getStylesheets().add("resources/style/toolsStyle.css");
        scene.getStylesheets().add("resources/style/consoleStyle.css");
        scene.getStylesheets().add("resources/style/magnetStyle.css");
        scene.getStylesheets().add("resources/style/msgWindowStyle.css");
        
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
