/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.BDSubWindowCtrl;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.base.BDDrawUtil;

/**
 *
 * @author gsh
 */
public class BDSubWindow extends Stage
{
	//public Pane rootPanel;
	public VBox rootPanel;
    public Stage root;
    public Scene scene;

    public BDWindowTitleView titlePanel = new BDWindowTitleView();

    protected void init(){};
    
    protected void init(double w, double h)
    {
        //rootPanel   = new StackPane();
    	rootPanel   = new VBox();
        scene       = new Scene(rootPanel, w, h + 36);
        root        = this;
        
        this.setWidth(w);
        this.setHeight(h);
        
        // 模态窗口
        this.initModality(Modality.APPLICATION_MODAL);
        
        rootPanel.getStylesheets().add("resources/style/modena_dark.css");

        //rootPanel.getChildren().add(titlePanel);
        
        // 定义无边框窗体
        root.initStyle(StageStyle.UNDECORATED);
        
        // Set main panel style
        String panelStyle = "";
        
        // 设置主窗体边框
        panelStyle += "-fx-background-radius:1px;";
        //panelStyle += "-fx-border-color: #333333;";
        panelStyle += "-fx-border-color: #7a7a7a;";
        panelStyle += "-fx-border-width:1px;";
        panelStyle += "-fx-border-radius:1px;";
        panelStyle +="-fx-font-size: 16px;";
        
        rootPanel.setStyle(panelStyle);
        
        // 添加标题栏
        rootPanel.getChildren().add(titlePanel);
        
        new BDSubWindowCtrl(this);
    };
    
    public void setResizeable()
    {
    	// 添加窗体拉伸效果
        BDDrawUtil.addDrawSubFunc(root, rootPanel);
    }

	public void setNewTitle(String title)
    {
    	this.titlePanel.titleLbl.setText(title);
    }
}
