/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.BDGuideViewCtrl;
import controller.BDMenuCtrl;
import controller.BDToolsCtrl;
import controller.BDWorkspaceCtrl;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
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
    
    public BDAboutWindow aboutWindow = new BDAboutWindow();	// 关于我们窗口

    public Rectangle2D visualBounds;
    
    public BDGUIModel guiModel = new BDGUIModel();
    
    public BDDialogWindow saveWindow;
    
    public BDGUIView(Stage primaryStage)
    {
        // 把工作区控制器传入菜单控制器
        menuCtrl.workspaceCtrl = this.workspaceCtrl;
        
        // 把工作区控制器传入工具控制器
        toolsCtrl.workspaceCtrl = this.workspaceCtrl;
        
        // 设置快捷键
        menuCtrl.setHotKey();
        
        this.primaryStage = primaryStage;
        
        // 定义无边框窗体
        primaryStage.initStyle(StageStyle.UNDECORATED);
        
        // 获取屏幕尺寸（不包含任务栏）
        this.visualBounds = Screen.getPrimary().getVisualBounds();
        
        // Set application icon.
        this.primaryStage.getIcons().add(new Image("/images/icon_64.png"));

        DropShadow ds = new DropShadow();
        
        ds.setOffsetX(50.0);
        ds.setOffsetY(50.0);
        ds.setColor(Color.GREEN);
        
        Reflection reflection = new Reflection();
        
        ds.setInput(reflection);
        
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
        this.root.setRight(this.consolePanel); // 右侧栏暂时屏蔽
        
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
}
