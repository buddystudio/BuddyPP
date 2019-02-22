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
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;
import model.BDLang;

/**
 *
 * @author gsh
 */
public class BDDelayWindow extends BDSubWindow
{
    public TextField timeTxt  = new TextField();
    public Button submitBtn = new Button(BDLang.rb.getString("确定"));
    
    public BDDelayWindow()
    {
        // 窗口初始化
        super.init(470, 90 + 10);
        
        // 总在最前方
       this.setAlwaysOnTop(true);
       
       // 只有关闭按钮的窗口
       //this.initStyle(StageStyle.UTILITY);
       this.setResizable(false);
                
       this.setMaximized(false);
       this.setFullScreen(false);
       this.setIconified(false);
       
       this.setTitle("  " + BDLang.rb.getString("设置延时"));
       this.setScene(scene);
       
       this.setNewTitle(BDLang.rb.getString("设置延时"));
       
       HBox contain  = new HBox();
       
       contain.setPadding(new Insets(15, 15, 15, 15));  // 设置边距
       contain.setSpacing(10);                      	// 设置间距
       
       contain.setAlignment(Pos.CENTER);            	// 居中排列
       
       Label hintLbl      = new Label(BDLang.rb.getString("延时") + "(1s=1000ms)：");
       Label unitLbl      = new Label("ms");
       
       timeTxt.setPrefWidth(120);
       submitBtn.setPrefSize(80, 30);
       
       //timeTxt.setStyle("-fx-background-radius: 0, 0;-fx-font-size: 15;");
       submitBtn.setStyle("-fx-background-radius: 0, 0;");
       
       timeTxt.setPromptText("1s = 1000ms");
      
       contain.getChildren().add(hintLbl);
       contain.getChildren().add(timeTxt);
       contain.getChildren().add(unitLbl);
       contain.getChildren().add(submitBtn);
       
       rootPanel.getChildren().add(contain);
    }
}
