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
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;

/**
 *
 * @author gsh
 */
public final class BDExampleWindow extends BDWindow
{
    public ObservableList<String> strList = FXCollections.observableArrayList();

    public Button importBtn = new Button("打开例程");
    
    public TreeItem<String> rootItem;
    public TreeView<String> tree = new TreeView<String> ();
    
    public VBox contain  = new VBox();
    
    public BDExampleWindow()
    {
        // 窗口初始化
        super.init(450, 400);
        
        //scene.getStylesheets().add("style/listViewStyle.css");
        
        // 总在最前方
        this.setAlwaysOnTop(true);
       
        // 只有关闭按钮的窗口
        this.initStyle(StageStyle.UTILITY);
        this.setResizable(false);
       
        this.setTitle("  请选择例程");
        this.setScene(scene);
        
        importBtn.setStyle("-fx-background-radius: 0, 0;");
       
        contain.setPadding(new Insets(0, 0, 10, 0));  	// 设置边距
        contain.setSpacing(10);                    		// 设置间距
        contain.setAlignment(Pos.CENTER);

        tree.setPrefSize(400, 300);
       
        importBtn.setPrefSize(100, 70);
       
        rootPanel.getChildren().add(contain);
    }
}
