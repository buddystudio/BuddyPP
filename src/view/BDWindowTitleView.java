/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import model.BDLang;

/**
 *
 * @author gsh
 */
public class BDWindowTitleView extends BorderPane
{
    public Button closeBtn = new Button();
    public Label titleLbl = new Label();
    public String title = "";
        
    public BDWindowTitleView()
    {
    	this.setStyle("-fx-background-color: #ffffff;"); 
    	this.getStylesheets().add("resources/style/titleStyle.css");
    	this.setPadding(new Insets(5, 5, 5, 5));  // 设置边距
        
        HBox titleBtns = new HBox();

        closeBtn.getStyleClass().add("closeBtn2");
        closeBtn.setMinSize(26, 26);

        titleBtns.getChildren().add(closeBtn);
        //titleBtns.setPadding(new Insets(0, 0, 0, 0));  // 设置边距

        titleLbl = new Label(title);
        
        titleLbl.setPadding(new Insets(3, 0, 0, 15));  // 设置边距
        titleLbl.setTextFill(Color.web("#000000"));
        titleLbl.setStyle("-fx-font-size: 16;");
        
        this.setLeft(titleLbl);
        this.setRight(titleBtns);
    }
}
