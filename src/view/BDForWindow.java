/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import model.BDLang;
import model.BDParameters;

/**
 *
 * @author gsh
 */
public final class BDForWindow extends BDSubWindow
{
    public Button submitBtn = new Button(BDLang.rb.getString("确定"));
    public RadioButton norRB = new RadioButton(BDLang.rb.getString("基本"));
    public RadioButton advRB = new RadioButton(BDLang.rb.getString("高级"));
    
    public TextField countTxt = new TextField();        // 循环次数
    
    public TextField initValueTxt = new TextField();    // 初始值
    public TextField limitValueTxt = new TextField();   // 界限值
    public TextField stepValueTxt = new TextField();    // 步长
    
    // 设置运算符
    public ComboBox<String> operaCmbBox = new ComboBox<String>();
    public ComboBox<String> opera2CmbBox = new ComboBox<String>();
    
    public ToggleGroup group = new ToggleGroup();
    
    public HBox norPanel = new HBox();
    public HBox advPanel = new HBox();
    
    public BDForWindow()
    {
        // 窗口初始化
    	if(BDParameters.langues.equals("English"))
    	{
    		super.init(900, 165 + 30 + 5);
    	}
    	else
    	{
    		super.init(755, 165 + 30 + 5);
    	}
        
        // 总在最前方
        this.setAlwaysOnTop(true);
       
        // 只有关闭按钮的窗口
        //this.initStyle(StageStyle.UTILITY);
        this.setResizable(false);
       
        this.setTitle("  " + BDLang.rb.getString("循环语句"));
        this.setScene(scene);
        
        this.setNewTitle(BDLang.rb.getString("循环语句"));
       
        HBox contain  = new HBox();
       
        VBox selPanel = new VBox();
        VBox optPanel = new VBox();
       
        norRB.setToggleGroup(group);
        advRB.setToggleGroup(group);
       
        // 默认选中简单模式
        norRB.setSelected(true);  
       
        norPanel.setSpacing(5);                          // 设置间距
        norPanel.setAlignment(Pos.CENTER_LEFT);          // 居中排列
       
        advPanel.setSpacing(5);                          // 设置间距
        advPanel.setAlignment(Pos.CENTER_LEFT);          // 居中排列
       
        selPanel.setSpacing(5);                          // 设置间距
        selPanel.setAlignment(Pos.CENTER_LEFT);          // 居中排列
       
        countTxt.setPrefWidth(50);
        countTxt.setText("10");

        norPanel.getChildren().add(new Label(BDLang.rb.getString("循环次数") + "："));
        norPanel.getChildren().add(countTxt);
        norPanel.getChildren().add(new Label(BDLang.rb.getString("次")));
       
        initValueTxt.setText("0");
        initValueTxt.setPrefWidth(50);
       
        limitValueTxt.setText("10");
        limitValueTxt.setPrefWidth(50);
       
        stepValueTxt.setText("1");
        stepValueTxt.setPrefWidth(50);
       
        operaCmbBox.getItems().add("<");
        operaCmbBox.getItems().add("<=");
        operaCmbBox.getItems().add(">");
        operaCmbBox.getItems().add(">=");
       
        operaCmbBox.getSelectionModel().select(0);
       
        opera2CmbBox.getItems().add(BDLang.rb.getString("自增"));
        opera2CmbBox.getItems().add(BDLang.rb.getString("自减"));
       
        opera2CmbBox.getSelectionModel().select(0);
       
        advPanel.getChildren().add(new Label(BDLang.rb.getString("初始值") + "："));
        advPanel.getChildren().add(initValueTxt);
        advPanel.getChildren().add(new Label(BDLang.rb.getString("条件") + "："));
        advPanel.getChildren().add(operaCmbBox);
        advPanel.getChildren().add(new Label(BDLang.rb.getString("界限值") + "："));
        advPanel.getChildren().add(limitValueTxt);
        advPanel.getChildren().add(new Label(BDLang.rb.getString("条件") + "："));
        advPanel.getChildren().add(opera2CmbBox);
        advPanel.getChildren().add(new Label(BDLang.rb.getString("步长") + "："));
        advPanel.getChildren().add(stepValueTxt);
       
        submitBtn.setPrefSize(80, 30);
       
        countTxt.setStyle("-fx-background-radius: 0, 0;-fx-font-size: 15;");
        initValueTxt.setStyle("-fx-background-radius: 0, 0;-fx-font-size: 15;");
        limitValueTxt.setStyle("-fx-background-radius: 0, 0;-fx-font-size: 15;");
        stepValueTxt.setStyle("-fx-background-radius: 0, 0;-fx-font-size: 15;");
        operaCmbBox.setStyle("-fx-background-radius: 0, 0;-fx-font-size: 15;");
        opera2CmbBox.setStyle("-fx-background-radius: 0, 0;-fx-font-size: 15;");
        submitBtn.setStyle("-fx-background-radius: 0, 0;");
       
        selPanel.getChildren().add(norRB);
        selPanel.getChildren().add(advRB);
       
        optPanel.getChildren().add(norPanel);
        optPanel.getChildren().add(advPanel);
       
        contain.getChildren().add(selPanel);
        contain.getChildren().add(optPanel);
       
        selPanel.setSpacing(25);                         // 设置间距
        optPanel.setSpacing(11);                         // 设置间距
       
        contain.setPadding(new Insets(20, 15, 15, 15));  // 设置边距
        contain.setSpacing(20);                          // 设置间距
        contain.setAlignment(Pos.CENTER);                // 居中排列
    
        selPanel.getChildren().add(submitBtn);
       
        rootPanel.getChildren().add(contain);
       
        // 默认为基本模式
        norPanel.setDisable(false);
        advPanel.setDisable(true);
    }
}
