/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import view.BDGuideView;
import view.BDMenuView;

/**
 *
 * @author gsh
 */
public class BDGuideViewCtrl 
{
	private final static Logger logger=LogManager.getLogger();
    public BDGuideViewCtrl(BDGuideView consoleView, BDWorkspaceCtrl workspaceCtrl, BDMenuView menuView)
    {
        consoleView.consoleTitle.setText("");
        //setTop(null);
        consoleView.setCenter(null);
        consoleView.setPrefWidth(0);
                    
        consoleView.isHide = false;
                    
        consoleView.hideBtn.setImage(consoleView.spLeftIco);
                    
        consoleView.hideBtn.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event) 
            {

                //workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.requestFocus();
                
                //workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.requestFocusInWindow();
                
                // 隐藏编译信息 
                if(consoleView.isHide == true)
                {
                    consoleView.consoleTitle.setPadding(new Insets(0,0,0,0));

                    consoleView.consoleTitle.setText("");
                    //setTop(null);
                    consoleView.setCenter(null);
                    consoleView.setPrefWidth(0);
                    
                    consoleView.isHide = false;
                    
                    consoleView.hideBtn.setImage(consoleView.spLeftIco);
                }
                else
                {
                    consoleView.consoleTitle.setPadding(new Insets(0,160,0,15));
                    
                    consoleView.consoleTitle.setText("教程资源");
                    
                    //setTop(consoleTitle);
                    //consoleView.setCenter(consoleView.console);
                    consoleView.setCenter(consoleView.list);
                    consoleView.setPrefWidth(250);
                    
                    consoleView.isHide = true;
                    
                    consoleView.hideBtn.setImage(consoleView.spRightIco);
                }
                
                // 重绘
                workspaceCtrl.workspaceView.workspaceModel.curTab.spp.updateUI();
            }
        });
        
        consoleView.consoleBtn.setOnMouseClicked(new EventHandler<MouseEvent>()
        {    
            @Override
            public void handle(MouseEvent event) 
            {
                BDConsoleWindowCtrl consoleWindowCtrl = new BDConsoleWindowCtrl(menuView.consoleWindow);
                
                menuView.consoleWindow.show();
            }
        });
        
        // 基础案例按钮
        consoleView.examplesBtn.setOnMouseClicked(new EventHandler<MouseEvent>()
        {    
            @Override
            public void handle(MouseEvent event) 
            {
                java.net.URI uri;
                
                try 
                {
                    uri = new java.net.URI("http://buddy.mongcj.com/blk/examples");
                    
                    java.awt.Desktop.getDesktop().browse(uri);
                } 
                catch (URISyntaxException ex) 
                {
                	logger.error("",ex);
                    //Logger.getLogger(BDMenuCtrl.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                	logger.error("",ex);
                    //Logger.getLogger(BDMenuCtrl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        // 传感器实验按钮
        consoleView.sensorBtn.setOnMouseClicked(new EventHandler<MouseEvent>()
        {    
            @Override
            public void handle(MouseEvent event) 
            {
                java.net.URI uri;
                
                try 
                {
                    uri = new java.net.URI("http://buddy.mongcj.com/blk/experiments");
                    
                    java.awt.Desktop.getDesktop().browse(uri);
                } 
                catch (URISyntaxException ex) 
                {
                	logger.error("",ex);
                    //Logger.getLogger(BDMenuCtrl.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    logger.error("",ex);
                	//Logger.getLogger(BDMenuCtrl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        // 函数库按钮
        consoleView.libBtn.setOnMouseClicked(new EventHandler<MouseEvent>()
        {    
            @Override
            public void handle(MouseEvent event) 
            {
                java.net.URI uri;
                
                try 
                {
                    uri = new java.net.URI("http://buddy.mongcj.com/blk/libraries");
                    
                    java.awt.Desktop.getDesktop().browse(uri);
                } 
                catch (URISyntaxException ex) 
                {
                	logger.error("",ex);
                    //Logger.getLogger(BDMenuCtrl.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    logger.error("",ex);
                	//Logger.getLogger(BDMenuCtrl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        // 语法参考按钮
        consoleView.lanBtn.setOnMouseClicked(new EventHandler<MouseEvent>()
        {    
            @Override
            public void handle(MouseEvent event) 
            {
                java.net.URI uri;
                
                try 
                {
                    uri = new java.net.URI("http://buddy.mongcj.com/blk/reference");
                    
                    java.awt.Desktop.getDesktop().browse(uri);
                } 
                catch (URISyntaxException ex) 
                {
                	logger.error("",ex);
                    //Logger.getLogger(BDMenuCtrl.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    logger.error("",ex);
                	//Logger.getLogger(BDMenuCtrl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
