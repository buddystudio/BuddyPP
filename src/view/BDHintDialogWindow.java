/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

/**
 *
 * @author gsh
 */
public class BDHintDialogWindow extends BDWindow
{
    public Image image;
    public Label msgLbl;
    
    public Button okBtn = new Button("确定");
    
    private BDHintDialogWindow root = this;
    
    public BDHintDialogWindow(String title, String msg)
    {
        // 窗口初始化
        super.init(360, 132);
        
        rootPanel.getStylesheets().clear();

        // 总在最前方
        this.setAlwaysOnTop(true);
        
        // 模态窗口
        this.initModality(Modality.APPLICATION_MODAL);
       
        // 只有关闭按钮的窗口
        this.initStyle(StageStyle.UTILITY);
        this.setResizable(false);
       
        this.setTitle(title);
        this.setScene(scene);
       
        okBtn.setPrefWidth(105);
        okBtn.setPrefHeight(20);
       
        okBtn.setFont(Font.font(null, FontWeight.NORMAL, 14));

        VBox contain  = new VBox();
       
        //contain.setPadding(new Insets(10,0,5,0));  // 设置边距
        //contain.setSpacing(5);                    // 设置间距
       
        contain.setAlignment(Pos.CENTER);
       
        HBox topPanel = new HBox();
        HBox bottomPanel = new HBox();
       
        topPanel.setPadding(new Insets(25,0,25,0));      // 设置边距
        //topPanel.setSpacing(5);                        // 设置间距
        bottomPanel.setPadding(new Insets(15,0,15,0));   // 设置边距
        bottomPanel.setSpacing(7);                       // 设置间距
       
        topPanel.setAlignment(Pos.CENTER);
        bottomPanel.setAlignment(Pos.CENTER);
       
        image = new Image("resources/images/iconIsSave.png");
       
        ImageView iv = new ImageView(image);
       
        msgLbl = new Label(msg);
       
        msgLbl.setFont(Font.font(null, FontWeight.BOLD, 18));
       
        topPanel.setStyle("-fx-background-color:#ffffff");
        //topPanel.getChildren().add(iv);
        topPanel.getChildren().add(msgLbl);
       
        this.scene.getStylesheets().add("resources/style/msgWindowStyle.css");;
       
        //okBtn.setStyle("-fx-background-color: #c2c2c2;");
       
        okBtn.getStyleClass().add("okBtn");

        bottomPanel.getChildren().add(okBtn);
       
        contain.getChildren().add(topPanel);
        contain.getChildren().add(bottomPanel);
       
        rootPanel.getChildren().add(contain);
       
        // 点击关闭按钮
        okBtn.setOnAction(new EventHandler<ActionEvent>() 
        {
        	@Override
        	public void handle(ActionEvent event) 
        	{
        		// 关闭窗口
        		root.close();
        	}
        });
    }
}
