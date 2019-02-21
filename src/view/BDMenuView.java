/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.BDLang;
import model.BDWindowsManager;

/**
 *
 * @author gsh
 */
public class BDMenuView extends HBox
{
    public SplitPane splitPanel 			= null;
    public Stage primaryStage				= null;

    public Button menuOpenBtn      = new Button(); // 打开按钮
    public Button menuNewBtn       = new Button(); // 新建按钮
    public Button menuNewPyBtn     = new Button(); // 新建按钮
    public Button menuSaveBtn      = new Button(); // 保存按钮
    public Button menuSaveAsBtn    = new Button(); // 另存为按钮
    
    public Button menuExampleBtn   = new Button(); // 示例按钮
        
    public Button menuUndoBtn      = new Button(); // 恢复按钮
    public Button menuRedoBtn      = new Button(); // 重做按钮
    public Button menuSearchBtn    = new Button(); // 搜索按钮
        
    public Button menuLibBtn       = new Button(); // 添加库按钮
    public Button menuVerifyBtn    = new Button(); // 编译按钮
    public Button menuUploadBtn    = new Button(); // 编译上传按钮
    public Button menuComBtn       = new Button(); // 串口通讯按钮
    public Button menuSettingBtn   = new Button(); // 设置按钮
    public Button menuAboutBtn     = new Button(); // 社区按钮
    public Button menuHelpBtn      = new Button(); // 社区按钮
    
    public Button menuPreSetBtn    = new Button(); // 预设置按钮
    
    public HBox forumPanel = new HBox();
    public HBox settingPanel = new HBox();
    
    public Label lbBoard   = new Label();
    public Label lbCom     = new Label();
    
    public VBox rightLables;
        
    public BDMenuView()
    {
    	BDWindowsManager.hintDialogWindow = new BDHintDialogWindow("  提示", "请先确定计算机已经连接开发板！");
    	
        this.setStyle("-fx-background-color: #42b2e4;");
            
        // 设置菜单栏位置
        this.setPrefHeight(60);
        this.setPadding(new Insets(0, 0, 0, 40));
        this.setSpacing(5);
        //this.setPadding(new Insets(5, 0, 0, 15));
        //this.setSpacing(-5);
        
        this.setAlignment(Pos.CENTER_LEFT);  // 居中排列
        
        Image image = new Image("resources/images/sp.png");
        
        ImageView sp01 = new ImageView(image);
        ImageView sp02 = new ImageView(image);
        ImageView sp03 = new ImageView(image);

        menuOpenBtn.setPrefSize(50, 50);
        menuNewBtn.setPrefSize(50, 50);
        menuNewPyBtn.setPrefSize(50, 50);
        menuSaveBtn.setPrefSize(50, 50);
        menuSaveAsBtn.setPrefSize(50, 50);
        menuExampleBtn.setPrefSize(50, 50);
        menuUndoBtn.setPrefSize(50, 50);
        menuRedoBtn.setPrefSize(50, 50);
        menuSearchBtn.setPrefSize(50, 50);
        menuLibBtn.setPrefSize(50, 50);
        menuVerifyBtn.setPrefSize(50, 50);
        menuUploadBtn.setPrefSize(50, 50);
        menuComBtn.setPrefSize(50, 50);
        menuSettingBtn.setPrefSize(50, 50);
        menuAboutBtn.setPrefSize(50, 50);
        menuHelpBtn.setPrefSize(50, 50);
        
        Tooltip menuOpenBtnTip = new Tooltip(BDLang.rb.getString("打开"));
        Tooltip menuNewBtnTip = new Tooltip(BDLang.rb.getString("新建") + " INO");
        Tooltip menuNewPyBtnTip = new Tooltip(BDLang.rb.getString("新建") + " PY");
        Tooltip menuSaveBtnTip = new Tooltip(BDLang.rb.getString("保存"));
        Tooltip menuSaveAsBtnTip = new Tooltip(BDLang.rb.getString("另存为"));
        Tooltip menuExampleBtnTip = new Tooltip(BDLang.rb.getString("示例"));
        Tooltip menuUndoBtnTip = new Tooltip(BDLang.rb.getString("恢复"));
        Tooltip menuRedoBtnTip = new Tooltip(BDLang.rb.getString("重做"));
        Tooltip menuSearchBtnTip = new Tooltip(BDLang.rb.getString("搜索"));
        Tooltip menuLibBtnTip = new Tooltip(BDLang.rb.getString("添加库"));
        Tooltip menuVerifyBtnTip = new Tooltip(BDLang.rb.getString("编译"));
        Tooltip menuUploadBtnTip = new Tooltip(BDLang.rb.getString("烧录"));
        Tooltip menuComBtnTip = new Tooltip(BDLang.rb.getString("串口通讯"));
        Tooltip menuSettingBtnTip = new Tooltip(BDLang.rb.getString("工具"));
        Tooltip menuForumBtnTip = new Tooltip(BDLang.rb.getString("关于"));
        //Tooltip menuSettingBtnTip = new Tooltip(BDLang.rb.getString("设置"));
        //Tooltip menuForumBtnTip = new Tooltip(BDLang.rb.getString("社区"));
        Tooltip menuHelpBtnTip = new Tooltip(BDLang.rb.getString("社区"));

        menuOpenBtnTip.getStyleClass().add("tip");
        menuNewBtnTip.getStyleClass().add("tip");
        menuNewPyBtnTip.getStyleClass().add("tip");
        menuSaveBtnTip.getStyleClass().add("tip");
        menuSaveAsBtnTip.getStyleClass().add("tip");
        menuExampleBtnTip.getStyleClass().add("tip");
        menuUndoBtnTip.getStyleClass().add("tip");
        menuRedoBtnTip.getStyleClass().add("tip");
        menuSearchBtnTip.getStyleClass().add("tip");
        menuLibBtnTip.getStyleClass().add("tip");
        menuVerifyBtnTip.getStyleClass().add("tip");
        menuUploadBtnTip.getStyleClass().add("tip");
        menuComBtnTip.getStyleClass().add("tip");
        menuSettingBtnTip.getStyleClass().add("tip");
        menuForumBtnTip.getStyleClass().add("tip");
        menuHelpBtnTip.getStyleClass().add("tip");

        menuOpenBtn.setTooltip(menuOpenBtnTip);
        menuNewBtn.setTooltip(menuNewBtnTip);
        menuNewPyBtn.setTooltip(menuNewPyBtnTip);
        menuSaveBtn.setTooltip(menuSaveBtnTip);
        menuSaveAsBtn.setTooltip(menuSaveAsBtnTip);
        menuExampleBtn.setTooltip(menuExampleBtnTip);
        menuUndoBtn.setTooltip(menuUndoBtnTip);
        menuRedoBtn.setTooltip(menuRedoBtnTip);
        menuSearchBtn.setTooltip(menuSearchBtnTip);
        menuLibBtn.setTooltip(menuLibBtnTip);
        menuVerifyBtn.setTooltip(menuVerifyBtnTip);
        menuUploadBtn.setTooltip(menuUploadBtnTip);
        menuComBtn.setTooltip(menuComBtnTip);
        menuSettingBtn.setTooltip(menuSettingBtnTip);
        menuAboutBtn.setTooltip(menuForumBtnTip);
        menuHelpBtn.setTooltip(menuHelpBtnTip);
        
        menuOpenBtn.getStyleClass().add("openBtn");
        menuNewBtn.getStyleClass().add("newBtn");
        menuNewPyBtn.getStyleClass().add("newBtn");
        menuSaveBtn.getStyleClass().add("saveBtn");
        menuSaveAsBtn.getStyleClass().add("saveAsBtn");
        menuExampleBtn.getStyleClass().add("exampleBtn");
        menuUndoBtn.getStyleClass().add("undoBtn");
        menuRedoBtn.getStyleClass().add("redoBtn");
        menuSearchBtn.getStyleClass().add("searchBtn");
        menuLibBtn.getStyleClass().add("libBtn");
        menuVerifyBtn.getStyleClass().add("verifyBtn");
        menuUploadBtn.getStyleClass().add("uploadBtn");
        menuComBtn.getStyleClass().add("comBtn");
        menuSettingBtn.getStyleClass().add("settingBtn");
        menuAboutBtn.getStyleClass().add("forumBtn");
        menuHelpBtn.getStyleClass().add("helpBtn");
        
        this.getChildren().add(menuNewBtn);
        // 展示隐藏创建Python文件的功能
        //this.getChildren().add(menuNewPyBtn);
        this.getChildren().add(menuOpenBtn);
        this.getChildren().add(menuSaveBtn);
        this.getChildren().add(menuSaveAsBtn);
        this.getChildren().add(menuExampleBtn);
        this.getChildren().add(sp01);
        this.getChildren().add(menuUndoBtn);
        this.getChildren().add(menuRedoBtn);
        this.getChildren().add(menuSearchBtn);
        this.getChildren().add(sp02);
        this.getChildren().add(menuLibBtn);
        this.getChildren().add(menuComBtn);
        //this.getChildren().add(menuVerifyBtn);
        this.getChildren().add(menuUploadBtn);
        this.getChildren().add(sp03);
        //this.getChildren().add(menuSettingBtn);
        this.getChildren().add(menuHelpBtn);

        //this.getChildren().add(settingPanel);
        
        // 屏蔽提示标签
        //this.getChildren().add(rightLables);
        
        //this.getChildren().add(menuAboutBtn);
        
        //HBox forumPanel = new HBox();
        
        // 暂时屏蔽
        //forumPanel.getChildren().add(menuSettingBtn);
        //forumPanel.getChildren().add(menuAboutBtn);
        
        //forumPanel.setPadding(new Insets(0, 0, 0, 150));
        
        forumPanel.setAlignment(Pos.CENTER_LEFT);
        
        this.getChildren().add(forumPanel);
    }
}
