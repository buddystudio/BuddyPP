/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.control.TabPane;
import model.BDWorkspaceModel;

/**
 *
 * @author gsh
 */
public class BDWorkspaceView extends TabPane
{
    public BDWorkspaceModel workspaceModel = new BDWorkspaceModel();
    
    public BDWorkspaceView()
    {
        //this.getStylesheets().add("style/workspaceStyle.css");
        
        // 屏蔽动画效果
        this.setStyle("-fx-open-tab-animation: NONE; -fx-close-tab-animation: NONE;");
        
        this.setFocusTraversable(true);
    }
}
