package view;

import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Window;

public class BDWebView extends BorderPane 
{
	protected Window root	= null;
	protected String url	= null;
	
	public WebView webView 	= new WebView();
	
	public BDWebView(Window ownerWindow, String homePageUrl) 
	{
		this.root = ownerWindow;
		this.url = homePageUrl;
		this.webView.getEngine().load(homePageUrl);
		this.setCenter(webView);
	}
	
	public BDWebView(String homePageUrl) 
	{
		this.url = homePageUrl;
		this.webView.getEngine().load(homePageUrl);
		this.setCenter(webView);
	}
	
	public WebView getWebView() 
	{
		return webView;
	}
}
