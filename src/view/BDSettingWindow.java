/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;

/**
 *
 * @author gsh
 */
public class BDSettingWindow extends BDWindow
{ 
    public ObservableList<String> strList = FXCollections.observableArrayList();
    public ObservableList<String> strList2 = FXCollections.observableArrayList();
    
    public ListView<String> themeList = new ListView<>(strList);
    public ListView<String> sizeList = new ListView<>(strList2);
    
    public Button importBtn = new Button("确定");
    
    public BDSettingWindow()
    {
        // 窗口初始化
        super.init(400, 300);
        
        scene.getStylesheets().add("style/listViewStyle.css");
        
        // 总在最前方
        this.setAlwaysOnTop(true);
       
        // 只有关闭按钮的窗口
        this.initStyle(StageStyle.UTILITY);
        this.setResizable(false);
       
        this.setTitle("  设置主题与字体大小");
        this.setScene(scene);
       
        VBox contain  = new VBox();
        HBox subContain = new HBox();
       
        contain.setPadding(new Insets(0,0,5,0));  // 设置边距
        contain.setSpacing(5);                    // 设置间距
        contain.setAlignment(Pos.CENTER);
        
        subContain.getChildren().add(themeList);
        subContain.getChildren().add(sizeList);
        
        contain.getChildren().add(subContain);
        contain.getChildren().add(importBtn);
        
        themeList.setPrefSize(250, 300);
        themeList.getStyleClass().add("mylistview"); 
        themeList.setStyle("-fx-font-size: 16;");
        
        sizeList.setPrefSize(150, 300);
        sizeList.getStyleClass().add("mylistview"); 
        sizeList.setStyle("-fx-font-size: 16;");

        importBtn.setStyle("-fx-background-radius: 0, 0;");
        importBtn.setPrefSize(150, 70);

        rootPanel.getChildren().add(contain);
    }
}
