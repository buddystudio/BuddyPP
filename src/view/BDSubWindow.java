/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author gsh
 */
public class BDSubWindow extends BDWindow
{
    public Pane rootPanel;
    public Stage root;
    public Scene scene;
    
    public BDWindowTitleView titlePanel = new BDWindowTitleView();
    
    protected void init(){};
    
    protected void init(double w, double h)
    {
        rootPanel   = new StackPane();
    	//rootPanel   = new VBox();
        scene       = new Scene(rootPanel, w, h);
        root        = this;
        
        this.setWidth(w);
        this.setHeight(h);
        
        // 模态窗口
        this.initModality(Modality.APPLICATION_MODAL);
        
        // 定义无边框窗体
        //this.initStyle(StageStyle.UNDECORATED);
        
        rootPanel.getStylesheets().add("resources/style/modena_dark.css");

        //rootPanel.getChildren().add(titlePanel);
        
        // 定义无边框窗体
        //root.initStyle(StageStyle.UNDECORATED);
        
        //scene.getStylesheets().add("resources/style/titleStyle.css"); 
        
        // 点击关闭按钮
        this.titlePanel.closeBtn.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
            	root.close();
            }
        });
    
    };
    
    
}
