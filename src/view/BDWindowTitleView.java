/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author gsh
 */
public class BDWindowTitleView extends BorderPane
{
    public Button closeBtn = new Button();
        
    public BDWindowTitleView()
    {
        // 标题栏
        //BorderPane titlePanel = new BorderPane();
        
        //this.setStyle("-fx-background-color: #333333;");
    	this.setStyle("-fx-background-color: #ffffff;"); 
    	
    	//this.setPrefHeight(20);
        
        //this.setLeft(icon);
        
        HBox titleBtns = new HBox();

        closeBtn.getStyleClass().add("closeBtn2"); 
        
        closeBtn.setPrefSize(20, 20);
        
        titleBtns.getChildren().add(closeBtn);
        
        //titleBtns.setPadding(new Insets(2, 2, 2, 2));  // 设置边距
        
        this.setPadding(new Insets(2, 2, 2, 2));  // 设置边距
        
        this.setRight(titleBtns);
    }
    
}
