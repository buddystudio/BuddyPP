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
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;
import model.BDDefineVariableWindowModel;
import model.BDLang;

/**
 *
 * @author gsh
 */
public class BDVariableWindow extends BDSubWindow
{
    public Button submitBtn = new Button(BDLang.rb.getString("确定"));
    public ComboBox combType = new ComboBox();
    public TextField txtName = new TextField();
    public TextField txtValue = new TextField();
    
    public BDVariableWindow()
    {
    	// 窗口初始化
    	//super.init(630, 60);
    	super.init(630, 90 + 10);
        
    	// 总在最前方
    	this.setAlwaysOnTop(true);
       
    	// 只有关闭按钮的窗口
    	//this.initStyle(StageStyle.UTILITY);
    	this.setResizable(false);
                
    	this.setMaximized(false);
    	this.setFullScreen(false);
    	this.setIconified(false);
       
    	this.setTitle("  " + BDLang.rb.getString("定义变量"));
    	this.setScene(scene);
    	
    	this.setNewTitle(BDLang.rb.getString("定义变量"));
       
    	HBox contain  = new HBox();
       
    	Label lbType     = new Label(BDLang.rb.getString("类型") + "：");
    	Label lbName     = new Label(BDLang.rb.getString("名称") + "：");
       	Label lbValue    = new Label(BDLang.rb.getString("数值") + "：");

       	combType.setPrefWidth(90);
           
       	// 初始化变量定义数据模型
       	BDDefineVariableWindowModel dvwm = new BDDefineVariableWindowModel();
       
       	combType.setItems(dvwm.typeList);
       
       	// 默认选项
       	combType.getSelectionModel().select(0);
       
       	txtName.setPrefWidth(100);
       	txtValue.setPrefWidth(100);
       	submitBtn.setPrefSize(80, 30);
       
       	submitBtn.setStyle("-fx-background-radius: 0, 0;");
       	combType.setStyle("-fx-background-radius: 0, 0;-fx-font-size: 15;");
       	txtName.setStyle("-fx-background-radius: 0, 0;-fx-font-size: 15;");
       	txtValue.setStyle("-fx-background-radius: 0, 0;-fx-font-size: 15;");
       
       	contain.getChildren().add(lbType);
       	contain.getChildren().add(combType);
       	contain.getChildren().add(lbName);
       	contain.getChildren().add(txtName);
       	contain.getChildren().add(lbValue);
       	contain.getChildren().add(txtValue);
       	contain.getChildren().add(submitBtn);
       
       	contain.setPadding(new Insets(15, 15, 25, 15));  // 设置边距
       	contain.setSpacing(10);                          // 设置间距
       	contain.setAlignment(Pos.CENTER);                // 居中排列
       
       	rootPanel.getChildren().add(contain); 
    }
}