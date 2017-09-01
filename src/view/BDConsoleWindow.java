/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.mongcj.util.base.Preferences;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.StageStyle;

/**
 *
 * @author gsh
 */
public class BDConsoleWindow extends BDWindow 
{
	public Image icon_msg_detail_img = new Image("images/icon_msg_detail.png");
	public Image icon_msg_error_img = new Image("images/icon_msg_error.png");
	public Image icon_msg_press_img = new Image("images/icon_msg_press.png");
	
	public ImageView icon_btn = new ImageView(icon_msg_detail_img);
	
	// public TextArea consoleTxt = new TextArea();
	//public BDEditorConsole consoleTxt = BDEditorConsole.getEditorConsoleInstance();
	//public BDTextAreaConsole consoleTxt=BDTextAreaConsole.getTextAreaConsoleInstance();
	
	public ProgressBar progressBarDebug = new ProgressBar(0);
	// public ProgressBar progressBarUpload=new ProgressBar(0);

	// public TextField txtProgress=new TextField();
	public Label lbl = new Label("程序编译中...");
	
	public HBox msgBtnBar = new HBox();
	public Label lblHint = new Label("  点击图标查看详细信息");

	boolean isShow = false;
	
	public BDConsoleMsgWindow msgWindow = new BDConsoleMsgWindow();
	
	public Button okBtn = new Button("确定");
    public Button detailBtn = new Button("查看详情");
    
    public HBox bottomPanel = new HBox();
    public VBox contain = new VBox();

	public BDConsoleWindow() 
	{
		isShow = Boolean.parseBoolean(Preferences.get("showdebug"));
		
		//---先屏蔽编译的信息提示
		isShow = false;
		//isShow = true;
		
		msgBtnBar.setVisible(false);
		
		//---
		if (isShow)
			showDebugLayout();
		else
			hideDebugLayout();
	}
	
	

	private void hideDebugLayout() 
	{
		super.init(420, 150);

		scene.getStylesheets().add("style/consoleStyle.css");
		scene.getStylesheets().add("style/progressbarStyle.css");
		scene.getStylesheets().add("style/compileStyle.css");
		scene.getStylesheets().add("style/msgWindowStyle.css");

		// 总在最前方
		this.setAlwaysOnTop(true);

		// 只有关闭按钮的窗口
		this.initStyle(StageStyle.UTILITY);
		this.setResizable(false);

		this.setTitle("  编译");
		this.setScene(scene);
		
		detailBtn.setPrefWidth(105);
	    detailBtn.setPrefHeight(20);
		okBtn.setPrefWidth(105);
	    okBtn.setPrefHeight(20);
	    
	    detailBtn.setFont(Font.font(null, FontWeight.NORMAL, 14));
	    okBtn.setFont(Font.font(null, FontWeight.NORMAL, 14));
	    
	    okBtn.getStyleClass().add("okBtn");
	    detailBtn.getStyleClass().add("okBtn");
	    
	    bottomPanel.setPadding(new Insets(15,0,15,0));   // 设置边距
	    bottomPanel.setSpacing(7);  
	       
	    bottomPanel.setAlignment(Pos.CENTER);
	    bottomPanel.getChildren().add(detailBtn);
	    bottomPanel.getChildren().add(okBtn);
	    
	    //bottomPanel.setVisible(false);

		VBox lablePane = new VBox();
		
		lbl = new Label("程序编译中...");
		lbl.setFont(Font.font(null, FontWeight.BOLD, 18));
		
		lablePane.getChildren().add(lbl);
		
		//contain.setStyle("-fx-background-color:#ffffff");
		lablePane.setStyle("-fx-background-color:#ffffff");
		lablePane.setPadding(new Insets(30, 0, 25, 0));
		lablePane.setAlignment(Pos.CENTER);
		lablePane.setPrefHeight(150);

		//consoleTxt.setAutoscrolls(true);
		//consoleTxt.setSize(640, 480);
		//--------------
		//consoleTxt.setPrefWidth(640);
		//consoleTxt.setPrefHeight(480);
		
		//SwingNode sn = new SwingNode();
		//sn.setContent(consoleTxt);
		
		//msgWindow.content.getChildren().add(sn);
	
		//msgWindow.content.getChildren().add(new VirtualizedScrollPane<>(consoleTxt));
		progressBarDebug.setProgress(0.0);
		progressBarDebug.setPrefHeight(71);
		progressBarDebug.setPrefWidth(420);
		
		msgBtnBar.setAlignment(Pos.CENTER_LEFT);
		msgBtnBar.setPadding(new Insets(0, 0, 10, 10));
		msgBtnBar.getChildren().add(icon_btn);
		msgBtnBar.getChildren().add(lblHint);

		contain.getChildren().add(lablePane);
		//contain.getChildren().add(msgBtnBar);
		contain.getChildren().add(progressBarDebug);
		//contain.getChildren().add(bottomPanel);

		rootPanel.getChildren().add(contain);
	}

	private void showDebugLayout() 
	{
		super.init(420, 150);
		scene.getStylesheets().add("style/consoleStyle.css");
		scene.getStylesheets().add("style/progressbarStyle.css");
		scene.getStylesheets().add("style/compileStyle.css");
		
		// 总在最前方
		this.setAlwaysOnTop(true);

		// 只有关闭按钮的窗口
		this.initStyle(StageStyle.UTILITY);
		this.setResizable(false);

		this.setTitle("  编译");
		this.setScene(this.scene);

		VBox lablePanel = new VBox();
		lbl = new Label("程序编译中...");
		lbl.setFont(Font.font(null, FontWeight.BOLD, 18));
		lablePanel.getChildren().add(lbl);
		VBox contain = new VBox();

		contain.setStyle("-fx-background-color:#ffffff");
		lablePanel.setPadding(new Insets(30, 0, 25, 0));
		lablePanel.setAlignment(Pos.CENTER);

		//consoleTxt.setAutoscrolls(true);
		//SwingNode sn = new SwingNode();
		//sn.setContent(consoleTxt);
		
		progressBarDebug.setProgress(0.0);		
		progressBarDebug.setPrefHeight(40);
		progressBarDebug.setPrefWidth(600);

		
		contain.getChildren().add(lablePanel);
		contain.getChildren().add(progressBarDebug);
		//contain.getChildren().add(consoleTxt);
		//msgWindow.content.getChildren().add(new VirtualizedScrollPane<>(consoleTxt));
		// 暂时先把输出控制台屏蔽掉
		//contain.getChildren().add(sn);
		
		//msgWindow.content.getChildren().add(sn);
		
		rootPanel.getChildren().add(contain);
	}

}
