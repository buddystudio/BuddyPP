/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;
import model.BDLang;

/**
 *
 * @author gsh
 */
public class BDPluginWindow extends BDSubWindow
{
    public Button magLedsBtn = new Button(); 	// LEDs插件
    public Button magMusicBtn = new Button(); 	// 谱曲插件
    public Button magColorBtn = new Button(); 	// 调色插件
    
    public BDLEDsWindow ledsWindow = new BDLEDsWindow();
    public BDMusicWindow musicWindow = new BDMusicWindow();
    public BDColorWindow colorWindow = new BDColorWindow();
    
    public BDPluginWindow()
    {
       // 窗口初始化
       super.init(500, 350 + 30);
       
       scene.getStylesheets().add("resources/style/settingStyle.css");

       // 定义无边框窗体
       //this.initStyle(StageStyle.UNDECORATED);
        
        // 总在最前方
       this.setAlwaysOnTop(true);
       
       // 只有关闭按钮的窗口
       //this.initStyle(StageStyle.UTILITY);
       //this.setResizable(false);
       
       //BDTitleView  titlePanel  = new BDTitleView();
       
       this.setTitle("  " + BDLang.rb.getString("工具"));
       this.setScene(scene);
       
       this.setNewTitle(BDLang.rb.getString("工具"));
       
       HBox contain  = new HBox();
       
       //contain.getChildren().add(titlePanel);
       
       contain.setAlignment(Pos.TOP_CENTER);
       
       contain.setStyle("-fx-background-color: #333333;");

       magLedsBtn.setPrefSize(100, 100);
       magMusicBtn.setPrefSize(100, 100);
       magColorBtn.setPrefSize(100, 100);
       
       Tooltip magLedsTip = new Tooltip(BDLang.rb.getString("LED阵列工具"));
       Tooltip magMusicTip = new Tooltip(BDLang.rb.getString("谱曲工具"));
       Tooltip magColorTip = new Tooltip(BDLang.rb.getString("调色工具"));
       
       magLedsBtn.getStyleClass().add("mgaLEDsBtn");
       magMusicBtn.getStyleClass().add("mgaMusicBtn");
       magColorBtn.getStyleClass().add("mgaColorBtn");
       
       magLedsTip.getStyleClass().add("tip");
       magMusicTip.getStyleClass().add("tip");
       magColorTip.getStyleClass().add("tip");
       
       magLedsBtn.setTooltip(magLedsTip);
       magMusicBtn.setTooltip(magMusicTip);
       magColorBtn.setTooltip(magColorTip);

       this.rootPanel.getStylesheets().add("resources/style/pluginStyle.css"); 

       contain.setMinHeight(350);
       contain.setSpacing(10);
       contain.setAlignment(Pos.TOP_LEFT);
       contain.getChildren().add(magLedsBtn);
       contain.getChildren().add(magMusicBtn);
       contain.getChildren().add(magColorBtn);
       contain.setPadding(new Insets(10, 10, 0, 10));

       rootPanel.getChildren().add(contain);
    }
}
