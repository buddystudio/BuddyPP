/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;

/**
 *
 * @author gsh
 */
public class BDComWindow extends BDWindow
{
    public BorderPane rootContain = new BorderPane();
    
    public TextField sendMsgTxt = new TextField();              	// 发送信息文本
    public Button sendMsgBtn = new Button("发送");              		// 发送信息按钮
    public Button ctrlBtn = new Button("开始");						// 控制按钮

    public CheckBox isAutoScroll = new CheckBox("自动滚屏");    		// 是否自动滚屏
    public CheckBox lineChkBox = new CheckBox("自动换行");      		// 是否自动换行
    public ComboBox<String> rateComoBox = new ComboBox<String>();  	// 波特率
    public TextArea recMsgtxt = new TextArea();                		// 接受信息文本

    public BDComWindow() 
    {
        // 窗口初始化
        super.init(500, 550);
        
        scene.getStylesheets().add("style/listViewStyle.css");

        rootPanel.getChildren().add(rootContain);
        
        // 总在最前方
        this.setAlwaysOnTop(true);

        // 只有关闭按钮的窗口
        this.initStyle(StageStyle.UTILITY);
        this.setResizable(false);

        this.setTitle("  串口通讯");
        this.setScene(scene);

        HBox topPanel = new HBox();
        HBox bottomPanel = new HBox();

        sendMsgTxt.setPrefWidth(400);
        sendMsgBtn.setPrefWidth(80);

        topPanel.setPadding(new Insets(5, 5, 5, 7));    // 设置边距
        topPanel.setSpacing(5);                       	// 设置间距

        bottomPanel.setPadding(new Insets(5, 5, 5, 5)); // 设置边距
        bottomPanel.setSpacing(5);                    	// 设置间距

        bottomPanel.setAlignment(Pos.CENTER);

        //CheckBox isAutoScroll = new CheckBox("自动滚屏");
        //CheckBox lineChkBox = new CheckBox("自动换行");
        //ComboBox rateComoBox = new ComboBox();
        lineChkBox.setPrefWidth(150);
        rateComoBox.setPrefWidth(150);
        rateComoBox.getItems().clear();
        
        ctrlBtn.setPrefWidth(80);
        ctrlBtn.setStyle("-fx-background-radius: 0, 0;");
        
        String[] rates = {"300", "1200", "2400", "4800", "9600", "14400",
            "19200", "28800", "38400", "57600", "115200"};
        
        rateComoBox.getItems().addAll(rates);        
        rateComoBox.getSelectionModel().select(4);
        rateComoBox.setStyle("-fx-background-radius: 0, 0;-fx-font-size: 15;");

        bottomPanel.getChildren().add(isAutoScroll);
        bottomPanel.getChildren().add(lineChkBox);
        bottomPanel.getChildren().add(ctrlBtn);
        bottomPanel.getChildren().add(rateComoBox);
        
        // Disable two checkbox.
        isAutoScroll.setDisable(true);
        lineChkBox.setDisable(true);
        
        sendMsgTxt.setStyle("-fx-text-fill: black; -fx-font-size: 15;-fx-background-radius: 0, 0; -fx-text-inner-color:#ffffff");
        sendMsgTxt.setPromptText("输入发送的信息...");
        topPanel.getChildren().add(sendMsgTxt);
        topPanel.getChildren().add(sendMsgBtn);
        
        sendMsgBtn.setStyle("-fx-background-radius: 0, 0;");
        sendMsgBtn.setPrefHeight(30);
        
        recMsgtxt.getStyleClass().add("mylistview");
        //recMsgtxt.setStyle("-fx-font-size: 16;");
        recMsgtxt.setStyle("-fx-font-size: 20;");
        
        recMsgtxt.setEditable(false);

        //TextArea recMsgtxt = new TextArea();
        rootContain.setTop(topPanel);
        rootContain.setCenter(recMsgtxt);
        rootContain.setBottom(bottomPanel);

    }
}
