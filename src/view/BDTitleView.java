/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author gsh
 */
public class BDTitleView extends BorderPane
{
    public Button closeBtn = new Button();
    public Button minBtn = new Button();
    public Button maxBtn = new Button();
    
    public Button simpleBtn = new Button();
    public Button aboutBtn = new Button();
    
    public Button settingBtn = new Button();
        
    public BDTitleView()
    {
        // 标题栏
        //BorderPane titlePanel = new BorderPane();
    	
        this.setStyle("-fx-background-color: #333333;"); 
        
        Image imageICON = new Image("resources/images/title.png");
        ImageView icon = new ImageView(imageICON);
        
        this.setLeft(icon);
        
        HBox titleBtns = new HBox();

        maxBtn.getStyleClass().add("maxBtn"); 
        minBtn.getStyleClass().add("minBtn"); 
        closeBtn.getStyleClass().add("closeBtn");
        simpleBtn.getStyleClass().add("minBtn"); 
        aboutBtn.getStyleClass().add("aboutBtn");
        settingBtn.getStyleClass().add("setBtn");
        
        minBtn.setPrefSize(45, 29);
        maxBtn.setPrefSize(45, 29);
        closeBtn.setPrefSize(45, 29);
        simpleBtn.setPrefSize(45, 29);
        aboutBtn.setPrefSize(45, 29);
        settingBtn.setPrefSize(45, 29);
        
        //titleBtns.getChildren().add(simpleBtn);
        titleBtns.getChildren().add(settingBtn);
        titleBtns.getChildren().add(aboutBtn);
        titleBtns.getChildren().add(minBtn);
        titleBtns.getChildren().add(maxBtn);
        titleBtns.getChildren().add(closeBtn);
        
        this.setRight(titleBtns);
        
        //this.getChildren().add(titlePanel);
    }
    
}
