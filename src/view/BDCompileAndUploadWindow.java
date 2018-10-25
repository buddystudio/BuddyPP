package view;

import controller.BDAdvConsoleCtrl;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
//import jfxtras.styles.jmetro8.JMetro;
import model.BDBoardManager;
import model.BDSerialManager2;

public class BDCompileAndUploadWindow extends BDWindow
{
	private MenuBar menuBar = new MenuBar();
	
	private Menu fileMenu = new Menu("文件");
    
	private MenuItem InoFileMenuItem = new MenuItem("打开Ino文件");
	private MenuItem HexFileMenuItem = new MenuItem("打开Hex文件");
	
	private Menu paraMenu = new Menu("参数");
	
	private MenuItem displayMenuItem = new MenuItem("显示");
	private MenuItem setMenuItem = new MenuItem("设置");
    
	private VBox topPanel = new VBox();
	private HBox btnsBar = new HBox();
	
	private Button openBtn 				= new Button("打开");
	private Button compileBtn 			= new Button("编译");
	
	private Button uploadBtn 			= new Button("上传");
	private Button compileAndUploadBtn 	= new Button("编译并上传");
	private Button stopBtn				= new Button("停止");
	private Button clearBtn				= new Button("清除");
	private Button updateBtn			= new Button("更新");
	
	private ProgressBar progressBar = new ProgressBar(0);
	
	private ObservableList<String> options;
	private ComboBox<String> serialListCombox;
	private ComboBox<String> boardListCombox;
	
	private BDBoardManager boardManager = new BDBoardManager();
	
	private BDAdvConsoleView acvView = new BDAdvConsoleView();
	private BDAdvConsoleCtrl acvCtrl = new BDAdvConsoleCtrl(acvView);
	
	public BDCompileAndUploadWindow()
	{
		// 窗口初始化
        super.init(750, 760);
        
        // 总在最前方
        //this.setAlwaysOnTop(true);
       
        // 只有关闭按钮的窗口
        this.initStyle(StageStyle.UTILITY);
        this.setResizable(false);
                
        this.setMaximized(false);
        this.setFullScreen(false);
        this.setIconified(false);
       
        this.setTitle("  编译与上传");
        this.setScene(scene);

        BorderPane root = new BorderPane();
        
        try
        {
        	this.fileMenu.getItems().addAll(this.InoFileMenuItem, this.HexFileMenuItem);
        	this.paraMenu.getItems().addAll(this.displayMenuItem, this.setMenuItem);
        	
        	this.menuBar.getMenus().addAll(this.fileMenu, this.paraMenu);
        	
        	// 屏蔽菜单功能
        	//this.menuBar.setVisible(false);
        	this.menuBar.setDisable(true);

			//this.btnsBar.setMinWidth(865);
        	this.btnsBar.setMinWidth(750);
			this.btnsBar.setPadding(new Insets(20, 20, 20, 20));
			this.btnsBar.setSpacing(10);
			
			this.openBtn.setPrefSize(60, 30);
			this.compileBtn.setPrefSize(60, 30);
			this.uploadBtn.setPrefSize(60, 30);
			this.compileAndUploadBtn.setPrefSize(110, 30);
			this.stopBtn.setPrefSize(60, 30);
			this.clearBtn.setPrefSize(60, 30);
			this.updateBtn.setPrefSize(60, 30);

			options = new BDSerialManager2().getPortList();
	
			serialListCombox = new ComboBox<>(options);
	
			serialListCombox.getSelectionModel().select(0);
			serialListCombox.setMinWidth(110);
			
			boardListCombox = new ComboBox<>(boardManager.getBoardsList());
			
			boardListCombox.getSelectionModel().select(0);
			boardListCombox.setMaxWidth(250);
		
			this.btnsBar.getChildren().addAll(/*openBtn,*/ 
											  compileBtn, 
											  /*uploadBtn,*/ 
											  compileAndUploadBtn, 
											  stopBtn, 
											  clearBtn, 
											  serialListCombox, 
											  updateBtn, 
											  boardListCombox);
		
			this.acvView.setPrefHeight(610);
			this.progressBar.setPrefSize(750, 40);
			
			topPanel.getChildren().add(this.menuBar);
			topPanel.getChildren().add(this.btnsBar);
			
			root.setTop(topPanel);
			root.setCenter(this.progressBar);
	        root.setBottom(this.acvView);
	        
	        rootPanel.getChildren().add(root);
	        
	        //new JMetro(JMetro.Style.LIGHT).applyTheme(rootPanel);
	        //new JMetro(JMetro.Style.DARK).applyTheme(rootPanel);
	        
	        rootPanel.getStylesheets().add("style/modena_dark.css");

        } 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public MenuItem getInoFileMenuItem() {
		return InoFileMenuItem;
	}

	public void setInoFileMenuItem(MenuItem inoFileMenuItem) {
		InoFileMenuItem = inoFileMenuItem;
	}

	public MenuItem getHexFileMenuItem() {
		return HexFileMenuItem;
	}

	public void setHexFileMenuItem(MenuItem hexFileMenuItem) {
		HexFileMenuItem = hexFileMenuItem;
	}
	
	public HBox getBtnsBar() {
		return btnsBar;
	}

	public void setBtnsBar(HBox btnsBar) {
		this.btnsBar = btnsBar;
	}

	public Button getOpenBtn() {
		return openBtn;
	}

	public void setOpenBtn(Button openBtn) {
		this.openBtn = openBtn;
	}

	public Button getCompileBtn() {
		return compileBtn;
	}

	public void setCompileBtn(Button compileBtn) {
		this.compileBtn = compileBtn;
	}

	public Button getUploadBtn() {
		return uploadBtn;
	}

	public void setUploadBtn(Button uploadBtn) {
		this.uploadBtn = uploadBtn;
	}

	public Button getCompileAndUploadBtn() {
		return compileAndUploadBtn;
	}

	public void setCompileAndUploadBtn(Button compileAndUploadBtn) {
		this.compileAndUploadBtn = compileAndUploadBtn;
	}

	public Button getStopBtn() {
		return stopBtn;
	}

	public void setStopBtn(Button stopBtn) {
		this.stopBtn = stopBtn;
	}

	public Button getClearBtn() {
		return clearBtn;
	}

	public void setClearBtn(Button clearBtn) {
		this.clearBtn = clearBtn;
	}

	public Button getUpdateBtn() {
		return updateBtn;
	}

	public void setUpdateBtn(Button updateBtn) {
		this.updateBtn = updateBtn;
	}

	public ProgressBar getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(ProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public ObservableList<String> getOptions() {
		return options;
	}

	public void setOptions(ObservableList<String> options) {
		this.options = options;
	}

	public ComboBox<String> getSerialListCombox() {
		return serialListCombox;
	}

	public void setSerialListCombox(ComboBox<String> serialListCombox) {
		this.serialListCombox = serialListCombox;
	}

	public BDAdvConsoleView getAcvView() {
		return acvView;
	}

	public void setAcvView(BDAdvConsoleView acvView) {
		this.acvView = acvView;
	}

	public BDAdvConsoleCtrl getAcvCtrl() {
		return acvCtrl;
	}

	public void setAcvCtrl(BDAdvConsoleCtrl acvCtrl) {
		this.acvCtrl = acvCtrl;
	}


	public ComboBox<String> getBoardListCombox() {
		return boardListCombox;
	}

	public void setBoardListCombox(ComboBox<String> boardListCombox) {
		this.boardListCombox = boardListCombox;
	}

	public BDBoardManager getBoardManager() {
		return boardManager;
	}

	public void setBoardManager(BDBoardManager boardManager) {
		this.boardManager = boardManager;
	}

	public Menu getParaMenu() {
		return paraMenu;
	}

	public void setParaMenu(Menu paraMenu) {
		this.paraMenu = paraMenu;
	}

	public MenuItem getDisplayMenuItem() {
		return displayMenuItem;
	}

	public void setDisplayMenuItem(MenuItem displayMenuItem) {
		this.displayMenuItem = displayMenuItem;
	}

	public MenuItem getSetMenuItem() {
		return setMenuItem;
	}

	public void setSetMenuItem(MenuItem setMenuItem) {
		this.setMenuItem = setMenuItem;
	}
}
