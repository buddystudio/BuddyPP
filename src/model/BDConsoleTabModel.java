/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.AWTException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import controller.BDWorkspaceCtrl;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;

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
