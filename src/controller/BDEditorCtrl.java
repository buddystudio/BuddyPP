package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.web.WebView;
import model.BDCodeModel;
import model.BDParameters;
import netscape.javascript.JSObject;
import view.BDEditorView;

public class BDEditorCtrl
{
	public BDCodeModel codeModel;
	
	public WebView webView;
	
	public BDEditorCtrl(BDEditorView editor, String code) 
	{
		this.webView = editor.webView;
		
		// 获取编辑器参数
        //BDParameters.getEditorProfile();
		
		JSObject win  = (JSObject) webView.getEngine().executeScript("window");
		
		win.setMember("test", this);

		// Load complete.
		this.webView.getEngine().getLoadWorker().stateProperty().addListener(  
	        	new ChangeListener<State>() 
	            {  
	            	@Override public void changed(ObservableValue ov, State oldState, State newState) 
	                {  
	            		if (newState == Worker.State.SUCCEEDED) 
	                    {
	            			// 初始化编辑器
	            			//int size = Integer.parseInt(BDParameters.editorFontSize.substring(0, BDParameters.editorFontSize.length() - 2));
	            			
	            			setTheme(BDParameters.editorTheme);
	            			setFontSize(Integer.parseInt(BDParameters.editorFontSize));
	            			
	                        // ***** Test code *****
	                        
	                        //setFontSize(30);
	            			
	                        //insert("Hello World!");
	                        //find("void");
	                        //repalceAll("void", "abcd");
	                        
	                        //System.out.println(getCode());
	                        //System.out.println("Code length is: " + getLength());
	                        
	                        // ***** End test *****

	                        webView.getEngine().executeScript("editor.getSession().on('change', function(e) {test.onChange();});");
	                        webView.getEngine().executeScript("editor.getSession().selection.on('changeCursor', function(e) {test.onChangeCursor();});");
	                        webView.getEngine().executeScript("editor.getSession().selection.on('changeSelection', function(e) {test.onChangeSelection();});");

	                        //setTheme("chaos");
	                        //setTheme("xcode");
	                        setMode("arduino");
	                        setCode(code);
	                    }  
	                    else if (newState == Worker.State.FAILED)
	                    {
	                    }
	                    else
	                    {
	                    }
	                }  
	            });
	}
	
	public void onChange()
    {
		codeModel.isSaved = false;
        codeModel.isCompiled = false;
        
        // 更新当前代码
        codeModel.setCodeText(getCode());
        
		//System.out.println("on change...");
    }
	
	public void onChangeCursor()
    {
		//System.out.println("on change cursor...");
    }
	
	public void onChangeSelection()
    {
		//System.out.println("on change selection...");
    }
	
	// Set code font size.
	public void setFontSize(int size)
	{
		// Set code font size.
        webView.getEngine().executeScript("editor.setFontSize(" + size + ");");
	}
	
	// Set mode.
	public void setMode(String mode)
	{
		// Set code mode. 
		webView.getEngine().executeScript("editor.session.setMode(\"ace/mode/"+ mode +"\");");	
	}
	
	// Set theme.
	public void setTheme(String theme)
	{
		// Set theme.
		webView.getEngine().executeScript("editor.setTheme(\"ace/theme/"+ theme +"\");");
	}
	
	public void setTabSize(int size)
	{
		// 设置默认制表符的大小
		webView.getEngine().executeScript("editor.getSession().setTabSize(" + size + ");");
	}
	
	public void setReadOnly(boolean flag)
	{
		// 设置编辑器只读
		webView.getEngine().executeScript("editor.setReadOnly(" + flag + ");");
	}
	
	public void setCode(String code)
	{
		// 设置代码（参数1，光标移动到最后一个字符后，参数-1，光标移动到第一个字符前）
		webView.getEngine().executeScript("editor.setValue(\"" + code + "\", -1);");
	}
	
	public void resize()
	{
		webView.getEngine().executeScript("editor.resize();");
	}
	
	// Undo
	public void undo()
	{
		webView.getEngine().executeScript("editor.undo();");
	}
	
	// Redo
	public void redo()
	{
		webView.getEngine().executeScript("editor.redo();");
	}
	
	// Copy.
	public void copy()
	{
		String text = (String)webView.getEngine().executeScript("editor.session.getTextRange(editor.getSelectionRange());");
		
		Clipboard clipboard = Clipboard.getSystemClipboard();
		ClipboardContent cc = new ClipboardContent();
		
		cc.putString(text);
		clipboard.setContent(cc);
	}
	
	// Cut.
	public void cut()
	{
		String text = (String)webView.getEngine().executeScript("editor.session.getTextRange(editor.getSelectionRange());");
		
		Clipboard clipboard = Clipboard.getSystemClipboard();
		ClipboardContent cc = new ClipboardContent();
		
		cc.putString(text);
		clipboard.setContent(cc);
		
		webView.getEngine().executeScript("editor.insert(\"\");");
	}
	
	// Paste.
	public void paste()
	{
		Clipboard clipboard = Clipboard.getSystemClipboard();
		String text = clipboard.getContent(DataFormat.PLAIN_TEXT).toString();
		
		webView.getEngine().executeScript("editor.insert(\"" + text +"\");");
	}
	
	// Insert the code.
	public void insert(String code)
	{
		webView.getEngine().executeScript("editor.insert(\"" + code +"\");");		
	}
	
	// Search
	public void search()
	{
		// 与ctrl+f功能一致
		webView.getEngine().executeScript("editor.execCommand('find');");
	}
	
	// Find.
	public void find(String keyword)
	{
		webView.getEngine().executeScript("editor.find('" + keyword + "',{backwards: false,wrap: false,caseSensitive: false,wholeWord: false,regExp: false});");
	}
	
	// Find next.
	public void findNext()
	{
		webView.getEngine().executeScript("editor.findNext();");
	}
	
	// Find previous
	public void findPrevious()
	{
		webView.getEngine().executeScript("editor.findPrevious();");
	}
	
	// Replace.
	public void repalce(String keyword_A, String keyword_B)
	{
		webView.getEngine().executeScript("editor.find('" + keyword_A + "');");
        webView.getEngine().executeScript("editor.replace('" + keyword_B + "');");	
	}
	
	// Replace.
	public void repalceAll(String keyword_A, String keyword_B)
	{
		webView.getEngine().executeScript("editor.find('" + keyword_A + "');");
        webView.getEngine().executeScript("editor.replaceAll('" + keyword_B + "');");
	}
	
	public void focus()
	{
		// 获取焦点
		webView.getEngine().executeScript("editor.focus();");
	}
	
	// Go to line.
	public void gotoLine(int num)
	{
		webView.getEngine().executeScript("editor.gotoLine(" + num + ");");
	}
	
	public void moveCursorTo(int rows, int columns)
	{
		// 移动光标至第r行，第c列 
		webView.getEngine().executeScript("editor.moveCursorTo(" + rows + ", " + columns + ");");
	}
	
	// Get source code from the editor.
	public String getCode()
	{
		// Get code.
		String code = (String) webView.getEngine().executeScript("editor.getValue()");
        
		return code;
	}
	
	public JSObject getCursor()
	{	
		JSObject win  = (JSObject) webView.getEngine().executeScript("editor.selection.getCursor();");
        
		return win;
	}
	
	public int getCurRow()
	{
		JSObject win = (JSObject) webView.getEngine().executeScript("editor.selection.getCursor();");
		
		return (int)win.getMember("row") + 1;
	}
	
	public int getCurColumn()
	{
		JSObject win = (JSObject) webView.getEngine().executeScript("editor.selection.getCursor();");
		
		return (int)win.getMember("column") + 1;
	}
	
	public int getLength()
	{
		int length = (int)webView.getEngine().executeScript("editor.session.getLength();");
        
		return length;
	}
}
