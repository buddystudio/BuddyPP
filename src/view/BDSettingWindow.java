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
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
        super.init(500, 500);
        
        //scene.getStylesheets().add("style/listViewStyle.css");
        
        // 总在最前方
        this.setAlwaysOnTop(true);
       
        // 只有关闭按钮的窗口
        this.initStyle(StageStyle.UTILITY);
        this.setResizable(false);
       
        this.setTitle("  设置");
        this.setScene(scene);
        
        TabPane tabPane = new TabPane();
        
        //tabPane.setSide(Side.LEFT);

        VBox contain  = new VBox();
        HBox subContain = new HBox();
       
        //contain.setPadding(new Insets(10, 0, 10, 0));  // 设置边距
        //contain.setSpacing(10);                    // 设置间距
        //contain.setAlignment(Pos.CENTER);
        
        subContain.getChildren().add(themeList);
        subContain.getChildren().add(sizeList);
        
        contain.getChildren().add(subContain);
        //contain.getChildren().add(importBtn);
        
        themeList.setPrefSize(350, 450);
        themeList.getStyleClass().add("mylistview"); 
        themeList.setStyle("-fx-font-size: 16;");
        
        sizeList.setPrefSize(150, 450);
        sizeList.getStyleClass().add("mylistview"); 
        sizeList.setStyle("-fx-font-size: 16;");

        importBtn.setStyle("-fx-background-radius: 0, 0;");
        importBtn.setPrefSize(150, 30);
        
        Tab tabBase = new Tab("基础设置");
        Tab tabEditor = new Tab("编辑器");
        
        tabBase.setClosable(false);
        tabEditor.setClosable(false);
        
        tabBase.setClosable(false);
        
        tabEditor.setContent(contain);
        
        tabPane.getTabs().addAll(tabBase, tabEditor);
        
        VBox settingPane = new VBox();
        
        settingPane.getChildren().addAll(tabPane, importBtn);
        
        settingPane.setAlignment(Pos.CENTER);
        settingPane.setSpacing(10);
        settingPane.setPadding(new Insets(0, 0, 15, 0));  // 设置边距

        rootPanel.getChildren().add(settingPane);
    }
}
