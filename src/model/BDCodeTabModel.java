/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.AWTException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import controller.BDEditorCtrl;
import controller.BDWorkspaceCtrl;
import javafx.embed.swing.SwingNode;
import javafx.scene.Cursor;
import view.BDEditorView;

/**
 *
 * @author gsh
 */
public class BDCodeTabModel extends BDTabModel
{
	private String curPath = this.getClass().getResource("/").getPath();
	private String editorUrl = "file://" + curPath + "resources/ace-builds-master/editor.html";
	private String acePath = "/resources/ace-builds-master/src-noconflict";
	
	public BDEditorView editorView;
	public BDEditorCtrl editorCtrl;
	
	private static final Logger logger = LogManager.getLogger();
    
    public RSyntaxTextArea textArea;
    
    //public SwingNode sn = new SwingNode();
    
    public BDCodeTabModel(BDCodeModel code, BDWorkspaceCtrl workspaceCtrl) throws AWTException
    {
        this.code = code;

        // 设置标签标题
        tab.setText(this.code.getName());

        hlink1.setGraphic(iv1);
        hlink1.setFocusTraversable(false);
        hlink1.setCursor(Cursor.DEFAULT);
        
        hlink2.setGraphic(iv2);
        hlink2.setFocusTraversable(false);
        hlink2.setCursor(Cursor.DEFAULT);
        //hlink1.setMouseTransparent(true);

        //tab.setGraphic(iv1);
        tab.setGraphic(hlink1);
        tab.setClosable(false);
        //tab.setContent(sn);

        textArea = new RSyntaxTextArea(20, 60);

        //------------------------------------------------------------------------
        
        String tmpCode = code.getCodeText().replaceAll("\"","\\\\\"");
        
        editorView = new BDEditorView(editorUrl);
		editorCtrl = new BDEditorCtrl(editorView, tmpCode);
		
		editorCtrl.codeModel = code;
		
		tab.setContent(editorView);	
    } 
}
