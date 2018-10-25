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
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;

/**
 *
 * @author gsh
 */
public class BDLibWindow extends BDWindow
{ 
    public ObservableList<String> strList = FXCollections.observableArrayList();
    public ListView<String> listView = new ListView<>(strList);
    public Button importBtn = new Button("导入");
    
    public BDLibWindow()
    {
        // 窗口初始化
        super.init(450, 450);
        
        //scene.getStylesheets().add("style/listViewStyle.css");
        
        // 总在最前方
        this.setAlwaysOnTop(true);
       
        // 只有关闭按钮的窗口
        this.initStyle(StageStyle.UTILITY);
        this.setResizable(false);
       
        this.setTitle("  请选择导入库");
        this.setScene(scene);
       
        VBox contain  = new VBox();
       
        contain.setPadding(new Insets(0, 0, 10, 0));  	// 设置边距
        contain.setSpacing(10);                    		// 设置间距
        contain.setAlignment(Pos.CENTER);
        
        contain.getChildren().add(listView);
        contain.getChildren().add(importBtn);
        
        listView.setPrefSize(400, 450);
        listView.getStyleClass().add("mylistview"); 
        listView.setStyle("-fx-font-size: 16;");

        importBtn.setStyle("-fx-background-radius: 0, 0;");
        importBtn.setPrefSize(150, 70);

        rootPanel.getChildren().add(contain);
    }
}
