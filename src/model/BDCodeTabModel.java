/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import controller.BDAssistantMenuCtrl;
import controller.BDEditorCtrl;
import controller.BDWorkspaceCtrl;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import view.BDAssistantMenuView;
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
    public RTextScrollPane spp = new RTextScrollPane(textArea);
    
    public SwingNode sn = new SwingNode();
    
    // 右键菜单中添加代码助手功能
    public BDAssistantMenuView assistantMenu;
                
    // 创建菜单控制器
    public BDAssistantMenuCtrl assistantMenuCtrl;
    
    public BDCodeTabModel(BDCodeModel code, BDWorkspaceCtrl workspaceCtrl) throws AWTException
    {
        assistantMenu = new BDAssistantMenuView("代码助手", workspaceCtrl);
                
        // 创建菜单控制器
        assistantMenuCtrl = new BDAssistantMenuCtrl(assistantMenu);
    
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
        spp = new RTextScrollPane(textArea);
                
        // 代码规则选用Arduino
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_ARDUINO);
        //textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
                
        // 设置代码编辑区文本字体与大小
        textArea.setFont(new Font(BDParameters.txtAreafont, Font.PLAIN, BDParameters.txtAreafontSize));
                
        // 代码可折叠
        textArea.setCodeFoldingEnabled(true);
                
        // 抗锯齿
        textArea.setAntiAliasingEnabled(true);
                      
        // 设定制表符的长度
        textArea.setTabSize(4);

        textArea.getPopupMenu().add(assistantMenu);
                
        spp = new RTextScrollPane(textArea);

        try
        {
        	textArea.setText(code.getCodeText());
        }
        catch(Exception e)
        {
        	logger.error("",e);
        }

        spp.setBorder(javax.swing.BorderFactory.createEmptyBorder());
                
        spp.setVisible(true);
        sn.setContent(spp);
        sn.getContent().setBackground(Color.red);

                
        // 文本内容监控（插入/删除/改变）时触发
        textArea.getDocument().addDocumentListener(new DocumentListener() 
        {
        	@Override
            public void insertUpdate(DocumentEvent e) 
            {
        		code.isSaved = false;
                code.isCompiled = false;
            }

            @Override
            public void removeUpdate(DocumentEvent e) 
            {
            	code.isSaved = false;
            	code.isCompiled=false;
            }

            @Override
            public void changedUpdate(DocumentEvent e) 
            {
            	code.isSaved = false;
            	code.isCompiled = false;
            }
        });

        textArea.addFocusListener(new FocusAdapter() 
        {
        	@Override
            public void focusGained(final FocusEvent arg0) 
            { 
        		spp.updateUI(); 
            }

            @Override
            public void focusLost(FocusEvent e) 
            {
            	if(tab.equals(workspaceCtrl.workspaceView.workspaceModel.curTab.tab))
                { 
            		// 延时获得焦点
                    final Timeline animation = new Timeline(
                    		new KeyFrame(Duration.millis(25),
                            new EventHandler<ActionEvent>() 
                            {
                    			@Override
                                public void handle(ActionEvent actionEvent) 
                                {
                    				Platform.runLater(new Runnable() 
                                    {
                                    	@Override
                                        public void run() 
                                        {
                                    		tab.getContent().requestFocus();
                                                        
                                    		spp.updateUI();
                                        }
                                     });
                                 }
                             }));
                    
                      animation.setCycleCount(1);
                      animation.play();
                }
            }
        });

        textArea.addComponentListener(new ComponentAdapter() 
        {
        	@Override
            public void componentResized(ComponentEvent e) 
            {
        		spp.updateUI();
            }    
        });
                
        spp.addComponentListener(new ComponentAdapter() 
        {
        	@Override
            public void componentResized(ComponentEvent e) 
            {
        		workspaceCtrl.workspaceView.workspaceModel.curTab.spp.updateUI();

                spp.updateUI();
             }
        });
        
        //tab.setContent(sn);
        //------------------------------------------------------------------------
        
        String tmpCode = code.getCodeText().replaceAll("\"","\\\\\"");
        
        editorView = new BDEditorView(editorUrl);
		editorCtrl = new BDEditorCtrl(editorView, tmpCode);
		
		editorCtrl.codeModel = code;
		
		tab.setContent(editorView);	
    } 
}
