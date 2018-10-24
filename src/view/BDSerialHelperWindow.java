/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;

/**
 *
 * @author gsh
 */
public class BDSerialHelperWindow extends BDWindow
{
    public Button submitBtn = new Button("确定");
    
    public ComboBox<String> value1CmbBox = new ComboBox<String>();
    public ComboBox<String> value2CmbBox = new ComboBox<String>();
    
    public BDSerialHelperWindow()
    {
    	// 窗口初始化
        super.init(800, 792);
        
        // 总在最前方
        //this.setAlwaysOnTop(true);
       
        // 只有关闭按钮的窗口
        this.initStyle(StageStyle.UTILITY);
        this.setResizable(false);
       
        this.setTitle("  串口通讯模板");
        this.setScene(scene);
        
        HBox contain  = new HBox();
        
        try
		{
        	String curPath = this.getClass().getResource("/").getPath();
        	System.out.println(curPath + "style/serialHelper.fxml");
        	Parent root = FXMLLoader.load(getClass().getResource("BDSerialHelperView.fxml"));
		
			contain.getChildren().add(root);
		} 
        catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        rootPanel.getChildren().add(contain);
    }
}
