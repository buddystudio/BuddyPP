package view;

import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Window;
import netscape.javascript.JSObject;


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
	}
	
	public WebView getWebView() 
	{
		return webView;
	}
}
