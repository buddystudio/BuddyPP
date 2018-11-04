/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.StageStyle;
import model.BDLang;
import model.BDParameters;

/**
 *
 * @author gsh
 */
public class BDSettingWindow extends BDWindow
{ 
    public ObservableList<String> strList = FXCollections.observableArrayList();
    public ObservableList<String> strList2 = FXCollections.observableArrayList();
    
    public ListView<String> themeList = new ListView<>(strList);
    public ListView<String> sizeList = new ListView<>(strList2);
    
    public Button submitBtn = new Button("确定");
    public Button cancelBtn = new Button("取消");
    
    public ComboBox<String> langList;
    
    public CheckBox isCustomChk;
    
    public BDSettingWindow()
    {
        // 窗口初始化
        super.init(500, 500);
        
        //scene.getStylesheets().add("style/listViewStyle.css");
        
        // 总在最前方
        this.setAlwaysOnTop(true);
       
        // 只有关闭按钮的窗口
        this.initStyle(StageStyle.UTILITY);
        this.setResizable(false);
       
        this.setTitle("  设置");
        this.setScene(scene);
        
        TabPane tabPane = new TabPane();
        
        //tabPane.setSide(Side.LEFT);

        Tab tabBase 	= new Tab("  通用  ");
        Tab tabEditor 	= new Tab("  编辑器  ");
        Tab tabHotKey 	= new Tab("  快捷键  ");
        Tab tabNetwork 	= new Tab("  网络 ");
        Tab tabUser 	= new Tab("  账户  ");
        
        tabBase.setClosable(false);
        tabEditor.setClosable(false);
        tabHotKey.setClosable(false);
        tabNetwork.setClosable(false);
        tabUser.setClosable(false);
        
        // 以下功能暂未开放
        tabNetwork.setDisable(true);
        tabUser.setDisable(true);
        
        tabBase.setContent(getBaseContent());
        tabEditor.setContent(getEditorContent());
        tabHotKey.setContent(getHotKeyContent());
        
        
        tabPane.getTabs().addAll(tabBase, tabEditor, tabHotKey, tabNetwork, tabUser);
        
        VBox settingPane = new VBox();
        
        HBox btnsBar = new HBox();
        
        btnsBar.getChildren().addAll(cancelBtn, submitBtn);
        
        btnsBar.setAlignment(Pos.CENTER);
        btnsBar.setSpacing(10);
        
        settingPane.getChildren().addAll(tabPane, btnsBar);
        
        settingPane.setAlignment(Pos.CENTER);
        settingPane.setSpacing(10);
        settingPane.setPadding(new Insets(0, 0, 15, 0));  // 设置边距

        rootPanel.getChildren().add(settingPane);
    }
    
    private VBox getBaseContent()
    {
    	VBox contain  		= new VBox();
    	
    	HBox subContain01 	= new HBox();
    	HBox subContain02 	= new HBox();
    	HBox subContain03 	= new HBox();
    	
    	Label lbl01 = new Label("选择语言");
    	
    	langList = new ComboBox<String>(FXCollections.observableArrayList("简体中文", "繁體中文", "English"));
    	
    	langList.setPrefWidth(180);

    	if(BDParameters.langues.equals("简体中文"))
    	{
    		langList.getSelectionModel().select("简体中文");
    	}
    	else if(BDParameters.langues.equals("繁體中文"))
    	{
    		langList.getSelectionModel().select("繁體中文");
    	}
    	else if(BDParameters.langues.equals("English"))
    	{
    		langList.getSelectionModel().select("English");
    	}
    	else
    	{
    		langList.getSelectionModel().select(0);
    	}
    	
    	Label lbl_lan = new Label("重启后生效");
    	
    	lbl_lan.setStyle("-fx-text-fill: #777777;");
    	
    	subContain01.setSpacing(35);
    	subContain01.setPadding(new Insets(40, 0, 30, 50));  // 设置边距
    	subContain01.setAlignment(Pos.CENTER_LEFT);
    	
    	subContain01.getChildren().addAll(lbl01, langList, lbl_lan);
    	
    	Label lbl02 = new Label("临时目录");
    	
    	VBox pathContain = new VBox();

    	TextField pathTxt = new TextField();
    	Button changeBtn	= new Button("更改目录");
    	Label lbl = new Label("编译过程中所产生文件的放置目录");
    	
    	pathTxt.setText(BDParameters.tempPath);
    	pathTxt.setPrefWidth(300);
    	changeBtn.setPrefWidth(120);
    	changeBtn.setDisable(true);
    	lbl.setStyle("-fx-text-fill: #777777;");
    	
    	pathContain.setPadding(new Insets(-5, 0, 0, 0));  // 设置边距
    	pathContain.setSpacing(15);
    	pathContain.getChildren().addAll(pathTxt, lbl, changeBtn);
    	
    	subContain02.setSpacing(35);
    	subContain02.setPadding(new Insets(20, 0, 10, 50));  // 设置边距
    	subContain02.getChildren().addAll(lbl02, pathContain);
    	
    	Label lbl03 = new Label("通用选项");
    	isCustomChk = new CheckBox("启动时恢复上一次窗口状态");
    	CheckBox isUpdateNoticeChk = new CheckBox("有版本更新时通知我");
    	
    	// 默认选项
    	isCustomChk.setSelected(true);
    	isUpdateNoticeChk.setSelected(true);
    	
    	// 是否选中恢复上一次窗口状态
    	if(BDParameters.editorIsCustom.equals("1"))
    	{
    		isCustomChk.setSelected(true);
    	}
    	else
    	{
    		isCustomChk.setSelected(false);
    	}
    	
    	VBox optsContain = new VBox();
    	
    	optsContain.getChildren().addAll(isCustomChk, isUpdateNoticeChk);
    	optsContain.setSpacing(15);
    	
    	subContain03.setSpacing(35);
    	subContain03.setPadding(new Insets(30, 0, 10, 50));  // 设置边距
    	//subContain03.setAlignment(Pos.CENTER_LEFT);
    	
    	subContain03.getChildren().addAll(lbl03, optsContain);
    	
    	contain.getChildren().addAll(subContain01, subContain02, subContain03);
    	
    	return contain;
    }
    
    private VBox getEditorContent()
    {
    	VBox contain  	= new VBox();
        HBox subContain = new HBox();
        
        subContain.getChildren().add(themeList);
        subContain.getChildren().add(sizeList);
        
        contain.getChildren().add(subContain);
        
        themeList.setPrefSize(350, 450);
        themeList.getStyleClass().add("mylistview"); 
        themeList.setStyle("-fx-font-size: 16;");
        
        sizeList.setPrefSize(150, 450);
        sizeList.getStyleClass().add("mylistview"); 
        sizeList.setStyle("-fx-font-size: 16;");

        submitBtn.setStyle("-fx-background-radius: 0, 0;");
        submitBtn.setPrefSize(100, 30);
        cancelBtn.setStyle("-fx-background-radius: 0, 0;");
        cancelBtn.setPrefSize(100, 30);
        
        return contain;
    }
    
    private ScrollPane getHotKeyContent()
    {
    	VBox contain  		= new VBox();
    	
    	HBox subContain01 	= new HBox();
    	HBox subContain02 	= new HBox();
    	HBox subContain03 	= new HBox();
    	HBox subContain04 	= new HBox();
    	HBox subContain05 	= new HBox();
    	HBox subContain06 	= new HBox();
    	HBox subContain07 	= new HBox();
    	HBox subContain08 	= new HBox();
    	HBox subContain09 	= new HBox();
    	HBox subContain10 	= new HBox();
    	HBox subContain11 	= new HBox();
    	HBox subContain12 	= new HBox();
    	HBox subContain13 	= new HBox();
    	HBox subContain14 	= new HBox();
    	
    	Label lbl01 = new Label("保存文件");
    	Label lbl02 = new Label("文本搜索");
    	Label lbl03 = new Label("搜索替换");
    	Label lbl04 = new Label("复制文本");
    	Label lbl05 = new Label("粘贴文本");
    	Label lbl06 = new Label("剪切文本");
    	Label lbl07 = new Label("全选文本");
    	Label lbl08 = new Label("恢复操作");
    	Label lbl09 = new Label("重复操作");
    	Label lbl10 = new Label("整行删除");
    	Label lbl11 = new Label("查找空格");
    	Label lbl12 = new Label("当前参数");
    	Label lbl13 = new Label("注释代码");
    	Label lbl14 = new Label("解除注释");
    	
    	Button btn01 = new Button("CTRL + S");			// 保存文件
    	Button btn02 = new Button("CTRL + F");			// 文本搜索
    	Button btn03 = new Button("CTRL + H");			// 搜索替换
    	Button btn04 = new Button("CRTL + C");			// 复制文本
    	Button btn05 = new Button("CTRL + V");			// 粘贴文本
    	Button btn06 = new Button("CTRL + X");			// 剪切文本
    	Button btn07 = new Button("CTRL + A");			// 全选文本
    	Button btn08 = new Button("CTRL + Z");			// 恢复操作
    	Button btn09 = new Button("CTRL + R");			// 重复操作
    	Button btn10 = new Button("CTRL + D");			// 整行删除
    	Button btn11 = new Button("CTRL + K");			// 查找空格
    	Button btn12 = new Button("CTRL + <");			// 当前参数
    	Button btn13 = new Button("SHITF + CTRL + /"); 	// 注释代码
    	Button btn14 = new Button("SHITF + CTRL + \\");	// 注释代码
    	
    	btn01.setDisable(true);
    	btn02.setDisable(true);
    	btn03.setDisable(true);
    	btn04.setDisable(true);
    	btn05.setDisable(true);
    	btn06.setDisable(true);
    	btn07.setDisable(true);
    	btn08.setDisable(true);
    	btn09.setDisable(true);
    	btn10.setDisable(true);
    	btn11.setDisable(true);
    	btn12.setDisable(true);
    	btn13.setDisable(true);
    	btn14.setDisable(true);
    	
    	btn01.setPrefWidth(200);
    	btn02.setPrefWidth(200);
    	btn03.setPrefWidth(200);
    	btn04.setPrefWidth(200);
    	btn05.setPrefWidth(200);
    	btn06.setPrefWidth(200);
    	btn07.setPrefWidth(200);
    	btn08.setPrefWidth(200);
    	btn09.setPrefWidth(200);
    	btn10.setPrefWidth(200);
    	btn11.setPrefWidth(200);
    	btn12.setPrefWidth(200);
    	btn13.setPrefWidth(200);
    	btn14.setPrefWidth(200);
    	
    	subContain01.setSpacing(35);
    	subContain02.setSpacing(35);
    	subContain03.setSpacing(35);
    	subContain04.setSpacing(35);
    	subContain05.setSpacing(35);
    	subContain06.setSpacing(35);
    	subContain07.setSpacing(35);
    	subContain08.setSpacing(35);
    	subContain09.setSpacing(35);
    	subContain10.setSpacing(35);
    	subContain11.setSpacing(35);
    	subContain12.setSpacing(35);
    	subContain13.setSpacing(35);
    	subContain14.setSpacing(35);
    	
    	subContain01.getChildren().addAll(lbl01, btn01);
    	subContain02.getChildren().addAll(lbl02, btn02);
    	subContain03.getChildren().addAll(lbl03, btn03);
    	subContain04.getChildren().addAll(lbl04, btn04);
    	subContain05.getChildren().addAll(lbl05, btn05);
    	subContain06.getChildren().addAll(lbl06, btn06);
    	subContain07.getChildren().addAll(lbl07, btn07);
    	subContain08.getChildren().addAll(lbl08, btn08);
    	subContain09.getChildren().addAll(lbl09, btn09);
    	subContain10.getChildren().addAll(lbl10, btn10);
    	subContain11.getChildren().addAll(lbl11, btn11);
    	subContain12.getChildren().addAll(lbl12, btn12);
    	subContain13.getChildren().addAll(lbl13, btn13);
    	subContain14.getChildren().addAll(lbl14, btn14);
    	
    	ScrollPane scrollPane = new ScrollPane();
    	
        scrollPane.setContent(contain);

    	contain.getChildren().addAll(subContain01, 
    								 subContain02, 
    								 subContain03,
    								 subContain04,
    								 subContain05,
    								 subContain06,
    								 subContain07,
    								 subContain08,
    								 subContain09,
    								 subContain10,
    								 subContain11,
    								 subContain12,
    								 subContain13,
    								 subContain14);
    	contain.setSpacing(15);
    	contain.setPadding(new Insets(15, 0, 15, 50));
    	
    	for(int i = 0; i < contain.getChildren().size(); i ++)
    	{
    		HBox c = (HBox) contain.getChildren().get(i);
    		
    		c.setAlignment(Pos.CENTER_LEFT);
    	}
    	
    	
    	
    	return scrollPane;
    }
}
