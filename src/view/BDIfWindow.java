/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;
import model.BDLang;
import model.BDParameters;

/**
 *
 * @author gsh
 */
public final class BDIfWindow extends BDWindow
{
    public Button submitBtn = new Button(BDLang.rb.getString("确定"));
    public ComboBox<String> optCmbBox = new ComboBox<String>();
    
    public ComboBox<String> value1CmbBox = new ComboBox<String>();
    public ComboBox<String> value2CmbBox = new ComboBox<String>();
    
    public CheckBox isBranch = new CheckBox(BDLang.rb.getString("分支"));
    
    public BDIfWindow()
    {
        // 窗口初始化
    	if(BDParameters.langues.equals("English"))
    	{
    		super.init(850, 60 + 30);
    	}
    	else
    	{
    		super.init(750, 60 + 30);
    	}
        
        // 总在最前方
        this.setAlwaysOnTop(true);
       
        // 只有关闭按钮的窗口
        this.initStyle(StageStyle.UTILITY);
        this.setResizable(false);

        this.setTitle("  " + BDLang.rb.getString("输入条件"));
        this.setScene(scene);

        HBox contain  = new HBox();

        contain.getChildren().add(new Label(BDLang.rb.getString("条件") + "1："));

        value1CmbBox.setEditable(true);
        value2CmbBox.setEditable(true);

        value1CmbBox.setPromptText(BDLang.rb.getString("变量") + "1");
        value2CmbBox.setPromptText(BDLang.rb.getString("变量") + "2");

        optCmbBox.getItems().add("==");
        optCmbBox.getItems().add("&&");
        optCmbBox.getItems().add("||");
        optCmbBox.getItems().add("!");
        optCmbBox.getItems().add("!=");
        optCmbBox.getItems().add("<=");
        optCmbBox.getItems().add("<");
        optCmbBox.getItems().add(">=");
        optCmbBox.getItems().add(">");

        optCmbBox.getSelectionModel().select(0);
        
        optCmbBox.setStyle("-fx-background-radius: 0, 0;-fx-font-size: 15;");
        value1CmbBox.setStyle("-fx-background-radius: 0, 0;-fx-font-size: 15;");
        value2CmbBox.setStyle("-fx-background-radius: 0, 0;-fx-font-size: 15;");
        submitBtn.setStyle("-fx-background-radius: 0, 0;");
        
        submitBtn.setPrefSize(80, 30);
        
        contain.setPadding(new Insets(15, 15, 15, 15));  // 设置边距
        contain.setSpacing(10);                          // 设置间距
        contain.setAlignment(Pos.CENTER);                // 居中排列
        
        value1CmbBox.setPrefWidth(160);
        value2CmbBox.setPrefWidth(160);

        contain.getChildren().add(value1CmbBox);
        contain.getChildren().add(optCmbBox);
        contain.getChildren().add(new Label(BDLang.rb.getString("条件") + "2："));
        contain.getChildren().add(value2CmbBox);
        contain.getChildren().add(isBranch);			 // 分支
        contain.getChildren().add(submitBtn);

        rootPanel.getChildren().add(contain);
    }
}
