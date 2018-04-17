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
import org.fxmisc.flowless.VirtualizedScrollPane;

import controller.BDAssistantMenuCtrl;
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
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import view.BDAssistantMenuView;
import view.BDTextAreaConsole;

/**
 *
 * @author gsh
 */
public class BDConsoleTabModel extends BDTabModel
{
	private static final Logger logger = LogManager.getLogger();
	
    public Tab tab = new Tab();
    public BDCodeModel code = new BDCodeModel();

    //public BDTextAreaConsole consoleTxt = BDTextAreaConsole.getTextAreaConsoleInstance();

    public BDConsoleTabModel(BDWorkspaceCtrl workspaceCtrl) throws AWTException
    {
    	HBox content = new HBox();
    	
    	String title = "控制台";
    	
        // 设置标签标题
        tab.setText(title);
        tab.setClosable(false);
        //content.getChildren().add(new VirtualizedScrollPane<>(consoleTxt));
        //tab.setContent(consoleTxt);
    }
}
