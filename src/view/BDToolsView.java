/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import model.BDLang;

/**
 *
 * @author gsh
 */
public class BDToolsView extends VBox
{
    //public BDDefineVariableWindow dfvWindow = new BDDefineVariableWindow();     // 变量定义窗口
    //public BDPinModeWindow pinModeWindow    = new BDPinModeWindow();            // pinMode端口设置窗口
    //public BDDelayWindow delayWindow        = new BDDelayWindow();              // Delay延时设置窗口
    //public BDForWindow forWindow            = new BDForWindow();                // For条件循环设置窗口
    //public BDWhileWindow whileWindow        = new BDWhileWindow();              // While条件循环设置窗口
    //public BDIfWindow ifWindow              = new BDIfWindow();                 // If条件循环设置窗口
    //public BDDrWindow drWindow              = new BDDrWindow();                 // DigitalRead数字端口读取设置窗口
    //public BDDwWindow dwWindow              = new BDDwWindow();                 // DigitalWrite数字端口写入设置窗口
    //public BDArWindow arWindow              = new BDArWindow();                 // AnalogRead模拟端口读取设置窗口
    //public BDAwWindow awWindow              = new BDAwWindow();                 // AnalogWrite模拟端口写入设置窗口
    //public BDSerialWindow serialWindow      = new BDSerialWindow();             // Serial串口通讯模板设置窗口
    //public BDSwitchWindow switchWindow      = new BDSwitchWindow();             // SwitchCase条件分支设置窗口
    //public BDLEDsWindow ledsWindow          = new BDLEDsWindow();
    
    //public BDPluginWindow pluginWindow      = new BDPluginWindow();             // 工具插件窗口
    
    public Button toolsDe      = new Button();  // 变量定义按钮
    public Button toolsIF      = new Button();  // If条件判断按钮
    public Button toolsFor     = new Button();  // For条件循环按钮
    public Button toolsWhile   = new Button();  // While条件循环按钮
    public Button toolsSwitch  = new Button();  // Switch-Case条件分支按钮
    public Button toolsPinMode = new Button();  // PinMode端口状态设置按钮
    public Button toolsDR      = new Button();  // DigitalRead数字端口读取按钮
    public Button toolsDW      = new Button();  // DigitalWrite数字端口写入按钮
    public Button toolsAR      = new Button();  // AnalogRead模拟端口读取按钮
    public Button toolsAW      = new Button();  // AnalogWrite模拟端口写入按钮
    public Button toolsDelay   = new Button();  // Delay延时设置按钮
    public Button toolsSerial  = new Button();  // Serial窗口通讯设置按钮
    public Button toolsLEDs    = new Button();
    public Button toolsPlugs   = new Button();	// 插件按钮
        
    public BDToolsView()
    {
        // 设置左侧工具栏的背景颜色
        this.setStyle("-fx-background-color: #333333;"); 
        
        // 设置左侧工具栏图标上边距
        this.setPadding(new Insets(30, 0, 0, 0));
        
        // 设置工具按钮的尺寸
        toolsDe.setPrefSize(40, 40);
        toolsIF.setPrefSize(40, 40);
        toolsFor.setPrefSize(40, 40);
        toolsWhile.setPrefSize(40, 40);
        toolsSwitch.setPrefSize(40, 40);
        toolsPinMode.setPrefSize(40, 40);
        toolsDR.setPrefSize(40, 40);
        toolsDW.setPrefSize(40, 40);
        toolsAR.setPrefSize(40, 40);
        toolsAW.setPrefSize(40, 40);
        toolsDelay.setPrefSize(40, 40);
        toolsSerial.setPrefSize(40, 40);
        toolsLEDs.setPrefSize(40, 40);
        toolsPlugs.setPrefSize(40, 40);
        
        // 绑定工具按钮的样式
        toolsDe.getStyleClass().add("deBtn");
        toolsIF.getStyleClass().add("ifBtn"); 
        toolsFor.getStyleClass().add("forBtn"); 
        toolsWhile.getStyleClass().add("whileBtn");
        toolsSwitch.getStyleClass().add("switchBtn");
        toolsPinMode.getStyleClass().add("pinModeBtn");
        toolsDR.getStyleClass().add("drBtn");
        toolsDW.getStyleClass().add("dwBtn");
        toolsAR.getStyleClass().add("arBtn");
        toolsAW.getStyleClass().add("awBtn");
        toolsDelay.getStyleClass().add("delayBtn");
        toolsSerial.getStyleClass().add("tcomBtn");
        toolsLEDs.getStyleClass().add("ledsBtn");
        toolsPlugs.getStyleClass().add("ledsBtn");
        
        // 设置弹出的提示信息
        /*Tooltip toolsDeTip = new Tooltip("定义变量");
        Tooltip toolsIFTip = new Tooltip("If（条件判断）");
        Tooltip toolsForTip = new Tooltip("For（条件循环）");
        Tooltip toolsWhileTip = new Tooltip("While（条件循环）");
        Tooltip toolsSwitchTip = new Tooltip("Switch-Case（条件分支）");
        Tooltip toolsPinModeTip = new Tooltip("PinMode（端口设置）");
        Tooltip toolsDRTip = new Tooltip("DigitalRead（数字端口读取）");
        Tooltip toolsDWTip = new Tooltip("DigitalWrite（数字端口写入）");
        Tooltip toolsARTip = new Tooltip("AnalogRead（模拟端口读取）");
        Tooltip toolsAWTip = new Tooltip("AnalogWrite（模拟端口读取）");
        Tooltip toolsDelayTip = new Tooltip("Delay（延时设置）");
        Tooltip toolsComTip = new Tooltip("Serial（串口通讯）");
        Tooltip toolsPlugsTip = new Tooltip("扩展工具");*/
        
        Tooltip toolsDeTip = new Tooltip(BDLang.rb.getString("定义变量"));
        Tooltip toolsIFTip = new Tooltip(BDLang.rb.getString("If（条件判断）"));
        Tooltip toolsForTip = new Tooltip(BDLang.rb.getString("For（条件循环）"));
        Tooltip toolsWhileTip = new Tooltip(BDLang.rb.getString("While（条件循环）"));
        Tooltip toolsSwitchTip = new Tooltip(BDLang.rb.getString("Switch-Case（条件分支）"));
        Tooltip toolsPinModeTip = new Tooltip(BDLang.rb.getString("PinMode（端口设置）"));
        Tooltip toolsDRTip = new Tooltip(BDLang.rb.getString("DigitalRead（数字端口读取）"));
        Tooltip toolsDWTip = new Tooltip(BDLang.rb.getString("DigitalWrite（数字端口写入）"));
        Tooltip toolsARTip = new Tooltip(BDLang.rb.getString("AnalogRead（模拟端口读取）"));
        Tooltip toolsAWTip = new Tooltip(BDLang.rb.getString("AnalogWrite（模拟端口读取）"));
        Tooltip toolsDelayTip = new Tooltip(BDLang.rb.getString("Delay（延时设置）"));
        Tooltip toolsComTip = new Tooltip(BDLang.rb.getString("Serial（串口通讯）"));
        Tooltip toolsPlugsTip = new Tooltip(BDLang.rb.getString("扩展工具"));
        
        // 绑定提示信息
        toolsDe.setTooltip(toolsDeTip);
        toolsIF.setTooltip(toolsIFTip);
        toolsFor.setTooltip(toolsForTip);
        toolsWhile.setTooltip(toolsWhileTip);
        toolsSwitch.setTooltip(toolsSwitchTip);
        toolsPinMode.setTooltip(toolsPinModeTip);
        toolsDR.setTooltip(toolsDRTip);
        toolsDW.setTooltip(toolsDWTip);
        toolsAR.setTooltip(toolsARTip);
        toolsAW.setTooltip(toolsAWTip);
        toolsDelay.setTooltip(toolsDelayTip);
        toolsSerial.setTooltip(toolsComTip);
        toolsPlugs.setTooltip(toolsPlugsTip);
        
        // 设置提示信息样式
        toolsDeTip.getStyleClass().add("tip");
        toolsIFTip.getStyleClass().add("tip");
        toolsForTip.getStyleClass().add("tip");
        toolsWhileTip.getStyleClass().add("tip");
        toolsSwitchTip.getStyleClass().add("tip");
        toolsPinModeTip.getStyleClass().add("tip");
        toolsDRTip.getStyleClass().add("tip");
        toolsDWTip.getStyleClass().add("tip");
        toolsARTip.getStyleClass().add("tip");
        toolsAWTip.getStyleClass().add("tip");
        toolsDelayTip.getStyleClass().add("tip");
        toolsComTip.getStyleClass().add("tip");
        toolsPlugsTip.getStyleClass().add("tip");

        // 把按钮添加到主容器上
        this.getChildren().add(toolsDe);
        this.getChildren().add(toolsIF);
        this.getChildren().add(toolsFor);
        this.getChildren().add(toolsWhile);
        this.getChildren().add(toolsSwitch);
        this.getChildren().add(toolsPinMode);
        this.getChildren().add(toolsDR);
        this.getChildren().add(toolsDW);
        this.getChildren().add(toolsAR);
        this.getChildren().add(toolsAW);
        this.getChildren().add(toolsDelay);
        this.getChildren().add(toolsSerial);
        //this.getChildren().add(toolsLEDs);
        this.getChildren().add(toolsPlugs);
    }
}
