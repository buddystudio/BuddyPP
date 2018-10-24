package view;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class BDAdvConsoleView extends BorderPane
{

	private HTMLEditor 	htmlEditor 	= new HTMLEditor();
	private WebView 	console 	= new WebView();
	private WebEngine 	webEngine 	= new WebEngine();
	
	private String colorOnMouseOver = "#00FF00";
	private String colorOnMouseOut = "#FFFFFF";
	private String colorMsgFont = "#0000FF";
	private String colorLineBlockBg = "#DDDDDD";
	private String colorLineFont = "#656565";
	private String msgFontSize = "16";
	private String msgLineHight = "5";
	
	public BDAdvConsoleView()
	{
		webEngine = console.getEngine();

		//hideHTMLEditorToolbars(htmlEditor);
		
		//htmlEditor.setPadding(new Insets(0, 0, 0, 0));
		
		this.setCenter(console);
	}
	
	private static void hideHTMLEditorToolbars(final HTMLEditor editor)
	{
	    editor.setVisible(false);
	    
	    Platform.runLater(new Runnable()
	    {
	        @Override
	        public void run()
	        {
	            Node[] nodes = editor.lookupAll(".tool-bar").toArray(new Node[0]);
	            
	            for(Node node : nodes)
	            {
	                node.setVisible(false);
	                node.setManaged(false);
	            }
	            
	            editor.setVisible(true);
	        }
	    });
	}

	public HTMLEditor getHtmlEditor() {
		return htmlEditor;
	}

	public void setHtmlEditor(HTMLEditor htmlEditor) {
		this.htmlEditor = htmlEditor;
	}

	public WebView getConsole() {
		return console;
	}

	public void setConsole(WebView console) {
		this.console = console;
	}

	public WebEngine getWebEngine() {
		return webEngine;
	}

	public void setWebEngine(WebEngine webEngine) {
		this.webEngine = webEngine;
	}

	public String getColorOnMouseOver() {
		return colorOnMouseOver;
	}

	public void setColorOnMouseOver(String colorOnMouseOver) {
		this.colorOnMouseOver = colorOnMouseOver;
	}

	public String getColorOnMouseOut() {
		return colorOnMouseOut;
	}

	public void setColorOnMouseOut(String colorOnMouseOut) {
		this.colorOnMouseOut = colorOnMouseOut;
	}

	public String getColorLineBlockBg() {
		return colorLineBlockBg;
	}

	public void setColorLineBlockBg(String colorLineBlockBg) {
		this.colorLineBlockBg = colorLineBlockBg;
	}

	public String getColorMsgFont() {
		return colorMsgFont;
	}

	public void setColorMsgFont(String colorMsgFont) {
		this.colorMsgFont = colorMsgFont;
	}

	public String getColorLineFont() {
		return colorLineFont;
	}

	public void setColorLineFont(String colorLineFont) {
		this.colorLineFont = colorLineFont;
	}

	public String getMsgFontSize() {
		return msgFontSize;
	}

	public void setMsgFontSize(String msgFontSize) {
		this.msgFontSize = msgFontSize;
	}

	public String getMsgLineHight() {
		return msgLineHight;
	}

	public void setMsgLineHight(String msgLineHight) {
		this.msgLineHight = msgLineHight;
	}

}
