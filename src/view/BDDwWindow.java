/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;

/**
 *
 * @author gsh
 */
public class BDDwWindow extends BDWindow
{
    public Button submitBtn = new Button("确定");
    
    public ComboBox value1CmbBox = new ComboBox();
    public ComboBox value2CmbBox = new ComboBox();
    
    public BDDwWindow()
    {
        // 窗口初始化
        super.init(700, 60);
        
        // 总在最前方
       this.setAlwaysOnTop(true);
       
       // 只有关闭按钮的窗口
       this.initStyle(StageStyle.UTILITY);
       this.setResizable(false);
       
       this.setTitle("  写入数字端口");
       this.setScene(scene);

       HBox contain  = new HBox();
       
       contain.getChildren().add(new Label("端口："));
       
       value1CmbBox.setEditable(true);
       value2CmbBox.setEditable(true);
       
       value1CmbBox.setPromptText("端口或端口变量");
       value2CmbBox.setPromptText("变量");
       
       submitBtn.setPrefSize(80, 30);

       value2CmbBox.setStyle("-fx-background-radius: 0, 0;-fx-font-size: 15;");
       value1CmbBox.setStyle("-fx-background-radius: 0, 0;-fx-font-size: 15;");
       submitBtn.setStyle("-fx-background-radius: 0, 0;");
       
       contain.setPadding(new Insets(15, 15, 15, 15));  // 设置边距
       contain.setSpacing(10);                          // 设置间距
       contain.setAlignment(Pos.CENTER);                // 居中排列
       
       contain.getChildren().add(value1CmbBox);
       contain.getChildren().add(new Label("变量："));
       contain.getChildren().add(value2CmbBox);
       contain.getChildren().add(submitBtn);

       rootPanel.getChildren().add(contain);
    }
}
