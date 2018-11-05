/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import model.BDLang;

/**
 *
 * @author gsh
 */
public class BDColorWindow extends BDWindow
{
    public Button submitBtn = new Button(BDLang.rb.getString("生成颜色数值"));
    public Button submitBtn2 = new Button(BDLang.rb.getString("生成颜色分量"));
    
    public final ColorPicker colorPicker = new ColorPicker();

    public BDColorWindow()
    {
        // 窗口初始化
        super.init(200, 150);
        
        // 总在最前方
        this.setAlwaysOnTop(true);
       
        // 只有关闭按钮的窗口
        this.initStyle(StageStyle.UTILITY);
       
        this.setResizable(false);
       
        this.setTitle("  " + BDLang.rb.getString("调色工具"));
        this.setScene(scene);

        VBox contain  = new VBox();
       
        contain.setPadding(new Insets(12,0,5,0));  // 设置边距
        contain.setSpacing(5);                    // 设置间距
       
        contain.setAlignment(Pos.CENTER);

        colorPicker.setPrefSize(150, 30);
        submitBtn.setPrefSize(150, 30);
        submitBtn2.setPrefSize(150, 30);

        submitBtn.setStyle("-fx-background-radius: 0, 0;");
       
        contain.getChildren().add(colorPicker);
        contain.getChildren().add(submitBtn);
        contain.getChildren().add(submitBtn2);

        rootPanel.getChildren().add(contain);
    }
}
