package view;

import org.fxmisc.flowless.VirtualizedScrollPane;

import view.BDTextAreaConsole;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;

public class BDConsoleMsgWindow extends BDWindow 
{
	public HBox content = new HBox();
	public boolean isError = false;
	public BDTextAreaConsole consoleTxt = BDTextAreaConsole.getTextAreaConsoleInstance();

	public BDConsoleMsgWindow() 
	{
		// 窗口初始化
		super.init(640, 480);

		// scene.getStylesheets().add("style/listViewStyle.css");

		// 总在最前方
		this.setAlwaysOnTop(true);

		// 只有关闭按钮的窗口
		this.initStyle(StageStyle.UTILITY);
		this.setResizable(false);

		this.setTitle("  编译与烧录信息");
		this.setScene(scene);

		// Set default value.
		isError = false;
		consoleTxt.setPrefWidth(640);
		consoleTxt.setPrefHeight(480);
		content.getChildren().add(new VirtualizedScrollPane<>(consoleTxt));
		
		this.rootPanel.getChildren().add(content);

	}
	public void clearText()
	{
		consoleTxt.clear();
	}

}
