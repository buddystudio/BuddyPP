package view;

import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Window;

public class BDEditorView extends BorderPane 
{	
	protected Window root	= null;
	protected String url	= null;
	
	public WebView webView 	= new WebView();
	
	public BDEditorView(Window ownerWindow, String homePageUrl) 
	{
		this.root = ownerWindow;
		this.url = homePageUrl;
		this.webView.getEngine().load(homePageUrl);
		this.setCenter(webView);
	}
	
	public BDEditorView(String homePageUrl) 
	{
		this.url = homePageUrl;
		this.webView.getEngine().load(homePageUrl);
		this.setCenter(webView);
		
		// 启用滚动条样式
		this.getStylesheets().add("resources/style/listViewStyle.css");
		
		this.webView.getStyleClass().add("mylistview");
	}
	
	public WebView getWebView() 
	{
		return webView;
	}
}
