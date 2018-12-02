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
import model.BDLang;

/**
 *
 * @author gsh
 */
public class BDComWindow extends BDWindow
{
    public BorderPane rootContain = new BorderPane();
    
    public TextField sendMsgTxt = new TextField();              			// 发送信息文本
    public Button sendMsgBtn 	= new Button(BDLang.rb.getString("发送"));   // 发送信息按钮
    public Button updateBtn 	= new Button(BDLang.rb.getString("刷新"));	// 刷新按钮
    public Button ctrlBtn 		= new Button(BDLang.rb.getString("开始"));	// 控制按钮
    public Button clearBtn		= new Button(BDLang.rb.getString("清屏"));	// 清屏按钮
    public CheckBox timeChkBox = new CheckBox(BDLang.rb.getString("时间"));  // 是否显示时间
    public CheckBox lineChkBox = new CheckBox(BDLang.rb.getString("换行"));  // 是否自动换行
    public ComboBox<String> portComoBox = new ComboBox<String>();  			// 波特率
    public ComboBox<String> rateComoBox = new ComboBox<String>();  			// 波特率
    public TextArea recMsgtxt = new TextArea();                				// 接受信息文本

    public BDComWindow() 
    {
        // 窗口初始化
        super.init(550, 600 + 30);
        
        //scene.getStylesheets().add("resources/style/listViewStyle.css");

        rootPanel.getChildren().add(rootContain);
        
        // 总在最前方
        this.setAlwaysOnTop(true);

        // 只有关闭按钮的窗口
        this.initStyle(StageStyle.UTILITY);
        this.setResizable(false);

        this.setTitle("  " + BDLang.rb.getString("串口通讯工具"));
        this.setScene(scene);

        HBox topPanel = new HBox();
        HBox bottomPanel = new HBox();

        topPanel.setPadding(new Insets(10, 5, 10, 7));    	// 设置边距
        topPanel.setSpacing(10);                       		// 设置间距

        bottomPanel.setPadding(new Insets(10, 5, 10, 5)); 	// 设置边距
        bottomPanel.setSpacing(10);                    		// 设置间距

        bottomPanel.setAlignment(Pos.CENTER);

        lineChkBox.setPrefWidth(80);
        rateComoBox.setPrefSize(80, 30);
        rateComoBox.getItems().clear();
        
        updateBtn.setPrefSize(80, 30);
        clearBtn.setPrefSize(80, 30);
        sendMsgBtn.setPrefSize(80, 30);
        ctrlBtn.setPrefSize(80, 30);
        
        sendMsgTxt.setPrefWidth(445);
        rateComoBox.setPrefSize(100, 30);
        
        ctrlBtn.setStyle("-fx-background-radius: 0, 0;");
        
        portComoBox.setPrefSize(155, 30);
        
        String[] rates = {"300", "1200", "2400", "4800", "9600", "14400",
            "19200", "28800", "38400", "57600", "115200"};
        
        rateComoBox.getItems().addAll(rates);        
        rateComoBox.getSelectionModel().select(4);
        rateComoBox.setStyle("-fx-background-radius: 0, 0;-fx-font-size: 15;");

        //bottomPanel.getChildren().add(timeChkBox);
        //bottomPanel.getChildren().add(lineChkBox);
        bottomPanel.getChildren().add(portComoBox);
        bottomPanel.getChildren().add(updateBtn);
        bottomPanel.getChildren().add(rateComoBox);
        bottomPanel.getChildren().add(ctrlBtn);
        bottomPanel.getChildren().add(clearBtn);
        
        sendMsgTxt.setStyle("-fx-text-fill: white; -fx-font-size: 15;-fx-background-radius: 0, 0; -fx-text-inner-color:#ffffff");
        sendMsgTxt.setPromptText(BDLang.rb.getString("输入发送的信息") + "...");
        
        topPanel.getChildren().add(sendMsgTxt);
        topPanel.getChildren().add(sendMsgBtn);
        
        sendMsgBtn.setStyle("-fx-background-radius: 0, 0;");
        sendMsgBtn.setPrefHeight(30);
        
        recMsgtxt.getStyleClass().add("mylistview");
        //recMsgtxt.setStyle("-fx-font-size: 16;");
        recMsgtxt.setStyle("-fx-font-size: 20;");
        
        recMsgtxt.setEditable(false);

        //TextArea recMsgtxt = new TextArea();
        /*rootContain.setTop(topPanel);
        rootContain.setCenter(recMsgtxt);
        rootContain.setBottom(bottomPanel);*/
        
        rootContain.setTop(bottomPanel);
        rootContain.setCenter(recMsgtxt);
        rootContain.setBottom(topPanel);

    }
}
