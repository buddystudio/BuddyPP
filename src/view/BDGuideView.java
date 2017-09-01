/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author gsh
 */
public class BDGuideView extends BorderPane
{
    public boolean isHide = true;
    
    public ImageView consoleBtn;
    public ImageView hideBtn;
    
    public Image spRightIco;
    public Image spLeftIco;
    public Image consoleIco;
    
    public Label consoleTitle = new Label();
    //public TextArea console = new TextArea();
    
    public VBox list = new VBox();
    
    public Button examplesBtn = new Button();
    public Button sensorBtn = new Button();
    public Button libBtn = new Button();
    public Button lanBtn = new Button();
    
    public BDGuideView()
    {
        consoleTitle.setText("教程资源");
        //consoleTitle.setPadding(new Insets(0,0,0,15));

        consoleTitle.setPrefHeight(33);
        //consoleTitle.setPrefHeight(40);
        
        consoleTitle.setStyle("-fx-text-fill: #ffffff;");

        //console.setWrapText(true);      // 自动换行
        //console.setEditable(false);   // 不可编辑
        
        /* 
        console.setStyle("-fx-text-fill: white;"+
                        "-fx-font: Courier New;"+
                        "-fx-font-family: Courier New;"+
                        "-fx-font-size: 12;");
        */

        /*
        console.setStyle("-fx-text-fill: white;"+
                        "-fx-background-color: black;"+
                        "-fx-font: Courier New;"+
                        "-fx-font-family: Courier New;"+
                        "-fx-font-weight: bold;"+
                        "-fx-font-size: 12;");
        */
        
        VBox divPanel = new VBox(); 
        
        divPanel.setAlignment(Pos.CENTER);
        
        //Button hideBtn = new Button();
        
        spRightIco = new Image("images/spRight.png");
        spLeftIco = new Image("images/spLeft.png");
        consoleIco = new Image("images/iconConsole18.png");
        
        hideBtn = new ImageView(spRightIco);
        consoleBtn = new ImageView(consoleIco);
        
        hideBtn.setStyle("-fx-background-color: #42b2e4;");
        //hideBtn.setGraphic(spRightIcoIv);
        
        //divPanel.setPrefWidth(20);
        
        divPanel.getChildren().add(hideBtn);

        HBox titlePanel = new HBox();
        
        titlePanel.setAlignment(Pos.CENTER_LEFT);
        
        titlePanel.getChildren().add(consoleTitle);
        titlePanel.getChildren().add(consoleBtn);
        
        // 展示隐藏编译信息窗口按钮
        consoleBtn.setVisible(false);
        
        examplesBtn.getStyleClass().add("examplesBtn"); 
        sensorBtn.getStyleClass().add("sensorBtn"); 
        libBtn.getStyleClass().add("libBtn2"); 
        lanBtn.getStyleClass().add("lanBtn"); 
        
        examplesBtn.setPrefSize(230, 145);
        sensorBtn.setPrefSize(230, 145);
        libBtn.setPrefSize(112, 112);
        lanBtn.setPrefSize(112, 112);
        
        list.setSpacing(6); // 设置边距
        
        list.getChildren().add(examplesBtn);
        list.getChildren().add(sensorBtn);
        
        HBox innerList = new HBox();
        
        innerList.setSpacing(6); // 设置边距
        
        innerList.getChildren().add(libBtn);
        innerList.getChildren().add(lanBtn);
        
        list.getChildren().add(innerList);
        
        this.setTop(titlePanel);
        this.setLeft(divPanel);
        //this.setTop(consoleTitle);
        
        //this.setCenter(console);
        this.setCenter(list);
        
        this.setStyle("-fx-background-color: #333333;"); 
        
        this.setPrefWidth(250);
    }
}
