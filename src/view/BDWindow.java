/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author gsh
 */
public class BDWindow extends Stage
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
        
        // 模态窗口
        this.initModality(Modality.APPLICATION_MODAL);

        //rootPanel.getChildren().add(titlePanel);
        
        // 定义无边框窗体
        //root.initStyle(StageStyle.UNDECORATED);
        
        //scene.getStylesheets().add("style/titleStyle.css"); 
    
    };
}
