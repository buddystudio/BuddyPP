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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;

/**
 *
 * @author gsh
 */
public class BDSearchWindow extends BDWindow
{
    public TextField searchTxt = new TextField();
    public TextField replaceTxt = new TextField();
    public Button searchBtn = new Button("搜索");
    public Button replaceBtn = new Button("替代");
    public Button repAllBtn = new Button("替换全部");
    
    public CheckBox distChkBox = new CheckBox("区分大小写");
    public CheckBox reverseChkBox = new CheckBox("反向");
        
    public BDSearchWindow()
    {
        // 窗口初始化
        super.init(400, 120);
        
        // 总在最前方
        this.setAlwaysOnTop(true);
       
        // 只有关闭按钮的窗口
        this.initStyle(StageStyle.UTILITY);
        this.setResizable(false);
       
        this.setTitle("  搜索");
        this.setScene(scene);
       
        VBox contain  = new VBox();
       
        contain.setPadding(new Insets(10, 5, 5, 5));  // 设置边距
        contain.setSpacing(5);                        // 设置间距
       
        HBox searchPanel = new HBox();
        HBox replacePanel = new HBox();
        HBox optionPanel = new HBox();
       
        Label searchLbl = new Label("查找文本：");
        //TextField searchTxt = new TextField();
        //Button searchBtn = new Button("搜索");
       
        searchTxt.setPrefWidth(220);
        searchBtn.setPrefWidth(80);
       
        searchPanel.getChildren().add(searchLbl);
        searchPanel.getChildren().add(searchTxt);
        searchPanel.getChildren().add(searchBtn);
       
        Label replaceLbl = new Label("替代文本：");
        //TextField replaceTxt = new TextField();
        //Button replaceBtn = new Button("替代");
       
        replaceTxt.setPrefWidth(220);
        replaceBtn.setPrefWidth(80);
       
        replacePanel.getChildren().add(replaceLbl);
        replacePanel.getChildren().add(replaceTxt);
        replacePanel.getChildren().add(replaceBtn);
       
        //CheckBox distChkBox = new CheckBox("区分大小写");
        //CheckBox reverseChkBox = new CheckBox("反向");
        //Button repAllBtn = new Button("替换全部");
       
        reverseChkBox.setPrefWidth(198);
       
        repAllBtn.setPrefWidth(80);
        
        replaceTxt.setStyle("-fx-background-radius: 0, 0;-fx-font-size: 15;");
        searchTxt.setStyle("-fx-background-radius: 0, 0;-fx-font-size: 15;");
        searchBtn.setStyle("-fx-background-radius: 0, 0;");
        replaceBtn.setStyle("-fx-background-radius: 0, 0;");
        repAllBtn.setStyle("-fx-background-radius: 0, 0;");
        
        searchBtn.setPrefSize(80, 30);
        replaceBtn.setPrefSize(80, 30);
        repAllBtn.setPrefSize(80, 30);
       
        optionPanel.getChildren().add(distChkBox);
        optionPanel.getChildren().add(reverseChkBox);
        optionPanel.getChildren().add(repAllBtn);
        
        reverseChkBox.setVisible(false);
       
        //searchPanel.setPadding(new Insets(0, 5, 5, 5)); 	// 设置边距
        searchPanel.setSpacing(8);                       	// 设置间距
       
        //replacePanel.setPadding(new Insets(0, 5, 5, 5)); 	// 设置边距
        replacePanel.setSpacing(8);                       	// 设置间距
       
        optionPanel.setPadding(new Insets(0, 5, 5, 5));  	// 设置边距
        optionPanel.setSpacing(8);                       	// 设置间距
       
        searchPanel.setAlignment(Pos.CENTER);                // 居中排列
        replacePanel.setAlignment(Pos.CENTER);               // 居中排列
        optionPanel.setAlignment(Pos.CENTER);                // 居中排列
       
        contain.getChildren().add(searchPanel);
        contain.getChildren().add(replacePanel);
        contain.getChildren().add(optionPanel);
       
        rootPanel.getChildren().add(contain);
    }
}
