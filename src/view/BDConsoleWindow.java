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
import javafx.scene.control.ProgressBar;
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
	public ProgressBar progressBarDebug = new ProgressBar(0);
	// public ProgressBar progressBarUpload=new ProgressBar(0);

	public Label lbl = new Label("程序编译中...");
	
	public HBox msgBtnBar = new HBox();
	
	//public BDConsoleMsgWindow msgWindow = new BDConsoleMsgWindow();
	//public BDConsoleMsgWindow msgWindow;
	public Button okBtn = new Button("确定");
    public Button detailBtn = new Button("查看详情");
    
    public HBox bottomPanel = new HBox();
    public VBox contain = new VBox();

	public BDConsoleWindow() 
	{
		super.init(420, 150 + 30);
		
		scene.getStylesheets().add("resources/style/consoleStyle.css");
		scene.getStylesheets().add("resources/style/progressbarStyle.css");
		scene.getStylesheets().add("resources/style/compileStyle.css");
		scene.getStylesheets().add("resources/style/msgWindowStyle.css");

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
	    //bottomPanel.getChildren().add(detailBtn);
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

		progressBarDebug.setProgress(0.0);
		progressBarDebug.setPrefHeight(71);
		progressBarDebug.setPrefWidth(420);
		
		msgBtnBar.setAlignment(Pos.CENTER_LEFT);
		msgBtnBar.setPadding(new Insets(0, 0, 10, 10));

		contain.getChildren().add(lablePane);
		//contain.getChildren().add(msgBtnBar);
		contain.getChildren().add(progressBarDebug);
		//contain.getChildren().add(bottomPanel);

		rootPanel.getChildren().add(contain);
	}
}
