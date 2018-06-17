/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import util.io.BDCodeWriter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import model.BDCodeTabModel;
import model.BDParameters;
import view.BDDialogWindow;
import view.BDGUIView;

/**
 *
 * @author gsh
 */
public class BDGUICtrl 
{
    private BDGUIView gui;

    private double xOffset = 0;
    private double yOffset = 0;

    private static final Logger logger = LogManager.getLogger();
    //private BDDialogWindow saveWindow = new BDDialogWindow("保存提示", "是否保存当前文件");

    public BDGUICtrl(BDGUIView gui) 
    {
        this.gui = gui;
        
        // 获取编辑器参数
        BDParameters.getEditorProfile();
        
        // 添加菜单操作侦听器
        gui.clearItem.setOnAction(editHandler);
        gui.undoItem.setOnAction(editHandler);
        gui.redoItem.setOnAction(editHandler);
        gui.cutItem.setOnAction(editHandler);
        gui.copyItem.setOnAction(editHandler);
        gui.pasteItem.setOnAction(editHandler);
        gui.searchItem.setOnAction(editHandler);
        
        this.gui.saveWindow = new BDDialogWindow("  保存提示", "是否保存当前文件");
        
        // 检测分割面板尺寸变化
        gui.workspacePanel.widthProperty().addListener((obs, oldVal, newVal) -> 
        {
        	// 获取分割位置信息
        	double pos = gui.splitPanel.getDividers().get(0).getPosition();
        	
        	if(pos > 0.99)
        	{
        		gui.arrowBtn.setImage(gui.iconArrowLeftImg);
        	}
        	else
        	{
        		gui.arrowBtn.setImage(gui.iconArrowRightImg);
        	}
        });
        
        // 点击 展开 / 收起 按钮
        gui.arrowBtn.setOnMouseClicked(new EventHandler<MouseEvent>()
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	// 获取分割位置信息
            	double pos = gui.splitPanel.getDividers().get(0).getPosition();
            	
            	if(pos > 0.99)
            	{
            		gui.arrowBtn.setImage(gui.iconArrowLeftImg);
            		gui.splitPanel.setDividerPosition(0, 0.5);
            	}
            	else
            	{
            		gui.arrowBtn.setImage(gui.iconArrowRightImg);
            		gui.splitPanel.setDividerPosition(0, 1);
            	}
            }
        });
        
        // 点击设置按钮
        gui.titlePanel.settingBtn.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
            	gui.settingWindow.show();
            }
        });
        
        // 点击关闭按钮
        gui.titlePanel.closeBtn.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                int len = gui.workspaceCtrl.workspaceView.workspaceModel.tabList.size();

                //BDCodeTabModel tab = gui.workspaceCtrl.workspaceView.workspaceModel.tabList.get(len - 1);
                isClose(len - 1);
            }
        });

        // 点击最大化按钮
        gui.titlePanel.maxBtn.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
	            // Don't work in Mac OS X.
	        	//if(!BDParameters.os.equals("Mac OS X"))
	        	{
	        		// 窗口最大化与恢复
	            	setResizeWindow();
	        	}
            }
        });
        
        // 点击最小化按钮
        gui.titlePanel.minBtn.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                // 最小化
                //gui.primaryStage.setIconified(true);
            	setMinWindow();
            }
        });
        
        // 双击标题栏放大与恢复窗口
        gui.titlePanel.setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent mouseEvent) 
            {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY))
                {
                    if(mouseEvent.getClickCount() == 2)
                    {
                    	// Don't work in Mac OS X.
                    	//if(!BDParameters.os.equals("Mac OS X"))
                    	{
                    	 	// 窗口最大化与恢复
                        	setResizeWindow();
                    	}	
                    }
                }
            }
        });
        
        // 点击关于按钮
        gui.titlePanel.aboutBtn.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                // 弹出关于我们按钮
            	gui.aboutWindow.show();
            }
        });
        
        // 点击精简按钮
        gui.titlePanel.simpleBtn.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                // 判断界面是否为精简模式
            	if(BDParameters.guiIsSimple == false)
            	{
            		//gui.menuPanel.setDisable(false);
            		//gui.toolsPanel.setDisable(false);
            		//gui.consolePanel.setDisable(false);
            		
            		gui.topPanel.getChildren().remove(gui.menuPanel);
                	gui.root.getChildren().remove(gui.toolsPanel);
                	gui.root.getChildren().remove(gui.consolePanel);
                	
                	BDParameters.guiIsSimple = true;
            	}
            	else
            	{
            		//gui.menuPanel.setDisable(true);
            		//gui.toolsPanel.setDisable(true);
            		//gui.consolePanel.setDisable(true);
            		
            		gui.topPanel.getChildren().add(gui.menuPanel);
                	gui.root.getChildren().add(gui.toolsPanel);
                	gui.root.getChildren().add(gui.consolePanel);
                	
                	BDParameters.guiIsSimple = false;
            	}
            	
            }
        });

        // 点击开始拖动窗口
        gui.titlePanel.setOnMousePressed(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event) 
            {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        // 拖动窗口
        gui.titlePanel.setOnMouseDragged(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event) 
            {
                // 如果是最大化则不能拖动（或恢复窗口）
                if(gui.guiModel.isMaximized == false)
                {
                	// 不能拖动
                    gui.primaryStage.setX(event.getScreenX() - xOffset);
                    gui.primaryStage.setY(event.getScreenY() - yOffset);
                }
                else
                {
                	// 恢复窗口
                    setResizeWindow();
                }   
            }
        });
    }

    // 
    private void isClose(int index) 
    {
        if (index < 0) 
        {
            // 关闭窗口
            System.exit(0);

            return;
        }

        BDCodeTabModel tab = gui.workspaceCtrl.workspaceView.workspaceModel.tabList.get(index);
        
        

        // 关闭提示窗口
        this.gui.saveWindow.close();

        // 如果文件未保存
        if (!tab.code.isSaved) 
        {
            // 提示保存
            this.gui.saveWindow.msgLbl.setText("是否保存对" + tab.code.getName() + "文件的修改？");

            this.gui.saveWindow.show();

            // 点击取消按钮
            this.gui.saveWindow.cancleBtn.setOnMouseClicked(new EventHandler<MouseEvent>() 
            {
                @Override
                public void handle(MouseEvent event) 
                {
                    // 关闭提示窗口
                    gui.saveWindow.close();
                }
            });

            // 点击放弃按钮
            this.gui.saveWindow.giveupBtn.setOnMouseClicked(new EventHandler<MouseEvent>() 
            {
                @Override
                public void handle(MouseEvent event) 
                {
                    if (index == 0) 
                    {
                        // 关闭提示窗口
                        gui.saveWindow.close();

                        // 关闭窗口
                        System.exit(0);
                    } 
                    else 
                    {
                        // 移除标签页
                        gui.workspaceCtrl.workspaceView.workspaceModel.tabList.remove(index);
                        gui.workspaceCtrl.workspaceView.getTabs().remove(tab.tab);

                        // 继续征询一下个标签操作
                        isClose(index - 1);
                    }
                }
            });

            // 点击确定按钮
            this.gui.saveWindow.okBtn.setOnMouseClicked(new EventHandler<MouseEvent>() 
            {
                @Override
                public void handle(MouseEvent event) 
                {
                	/*
                    if (index == 0) 
                    {
                        // 关闭提示窗口
                        gui.saveWindow.close();

                        // 关闭窗口
                        System.exit(0);
                    } 
                    else
                    */ 
                    {
                        // 关闭提示窗口
                        gui.saveWindow.close();

                        // 保存文件
                        if (tab.code.path == "") 
                        {
                            // 另存为文件
                            saveAsFile(tab);
                        } 
                        else 
                        {
                            try 
                            {
                                // 保存文件
                                saveFile(tab);
                            } 
                            catch (Exception ex) 
                            {
                                // 另存为文件
                                saveAsFile(tab);
                            }
                        }

                        // 移除标签页
                        gui.workspaceCtrl.workspaceView.workspaceModel.tabList.remove(index);
                        gui.workspaceCtrl.workspaceView.getTabs().remove(tab.tab);

                        // 继续征询一下个标签操作
                        isClose(index - 1);
                    }
                }
            });
        } 
        else 
        {
            // 关闭窗口
            System.exit(0);
        }
    }
    
    private void setMinWindow()
    {
    	gui.primaryStage.setIconified(true);
    }
    
    private void setResizeWindow()
    {
    	
    	//if(gui.primaryStage.isMaximized() == true)
        if (gui.guiModel.isMaximized == true) 
        {
            // 恢复原来的位置
            gui.primaryStage.setX(gui.guiModel.preX);
            gui.primaryStage.setY(gui.guiModel.preY);

            // 恢复原来的尺寸
            //gui.primaryStage.setWidth(1024 - 120);
            //gui.primaryStage.setWidth(1024);
            gui.primaryStage.setWidth(940);
            gui.primaryStage.setHeight(640 + 10 + 10);

            // 最大化
            //gui.primaryStage.setMaximized(false);
            gui.guiModel.isMaximized = false;

            // 设置面板靠右
            //gui.menuPanel.settingPanel.setPrefWidth(315);
            //gui.menuPanel.settingPanel.setPrefWidth(265);
            //gui.menuPanel.settingPanel.setPrefWidth(250);
            
            //gui.menuPanel.settingPanel.setPrefWidth(250);
            //gui.menuPanel.rightLables.setPrefWidth(300);
        } 
        else 
        {
            // 最大化
            //primaryStage.setMaximized(true);

            gui.guiModel.isMaximized = true;

            // 记录窗体当前的位置
            gui.guiModel.preX = gui.primaryStage.getX();
            gui.guiModel.preY = gui.primaryStage.getY();

            // 自定义最大化
            if(BDParameters.os.equals("Mac OS X"))
            {
            	gui.primaryStage.setX(0);
                gui.primaryStage.setY(22);
                gui.primaryStage.setWidth(gui.visualBounds.getWidth());
                gui.primaryStage.setHeight(gui.visualBounds.getHeight() + 4);
            }
            else
            {
            	gui.primaryStage.setX(-1);
                gui.primaryStage.setY(-1);
                gui.primaryStage.setWidth(gui.visualBounds.getWidth() + 2);
                gui.primaryStage.setHeight(gui.visualBounds.getHeight() + 2);
            }

            //primaryStage.setFullScreen(false);
            // 总在顶端
            //primaryStage.setAlwaysOnTop(true);
            // 设置面板靠右
            //gui.menuPanel.settingPanel.setPrefWidth(gui.visualBounds.getWidth() - 1024 + 315);
            //gui.menuPanel.settingPanel.setPrefWidth(gui.visualBounds.getWidth() - 1024 + 265);
            //gui.menuPanel.settingPanel.setPrefWidth(gui.visualBounds.getWidth() - 1024 - 120 + 250);

            // 尝试不重绘
            //gui.workspaceCtrl.workspaceView.workspaceModel.curTab.spp.updateUI();
        }
    	
    }

    // 保存文件
    private void saveFile(BDCodeTabModel tab) 
    {
        try 
        {
            String code = tab.editorCtrl.getCode();

            // 写入文件
            BDCodeWriter.fileWriter(tab.code.path, code);

            tab.code.setCodeText(code);

            // 更改保存状态
            tab.code.isSaved = true;

        } 
        catch (IOException ex) 
        {
        	logger.error("",ex);
            //Logger.getLogger(BDMenuCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // 另存为文件
    private void saveAsFile(BDCodeTabModel tab) 
    {
    	try 
        {
    		File file;

	    	FileChooser fileChooser = new FileChooser();
	
	    	// 设置文件后缀过滤
	    	FileChooser.ExtensionFilter extFilterTXT = new FileChooser.ExtensionFilter("文本文档  (*.txt)", "*.txt");
	    	FileChooser.ExtensionFilter extFilterINO = new FileChooser.ExtensionFilter("程序源码  (*.ino)", "*.ino");
	    	FileChooser.ExtensionFilter extFilterCPP = new FileChooser.ExtensionFilter("C++程序源码  (*.cpp)", "*.cpp");
	    	FileChooser.ExtensionFilter extFilterC = new FileChooser.ExtensionFilter("C程序源码  (*.c)", "*.c");
	    	FileChooser.ExtensionFilter extFilterH = new FileChooser.ExtensionFilter("头文件  (*.h)", "*.h");
	
	    	fileChooser.getExtensionFilters().add(extFilterINO);
	    	fileChooser.getExtensionFilters().add(extFilterTXT);
	    	fileChooser.getExtensionFilters().add(extFilterCPP);
	    	fileChooser.getExtensionFilters().add(extFilterC);
	    	fileChooser.getExtensionFilters().add(extFilterH);
	        
	        
	        // Show open file dialog
	        //file = fileChooser.showSaveDialog(null);
	        file = fileChooser.showSaveDialog(gui.saveWindow);
	        	
	        if(file == null || !file.exists())
	        {
	        	return;
	        }

            // File write.
            BDCodeWriter.fileWriter(file.getPath(), tab.editorCtrl.getCode());

            // Update the file path.
            tab.code.path = file.getPath();

            // Update the tab name.
            tab.tab.setText(file.getName());

            // Update the tab state.
            tab.code.isSaved = true;
        } 
        catch (IOException ex) 
        {
        	logger.error("",ex);
            //Logger.getLogger(BDMenuCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private EventHandler<ActionEvent> editHandler = new EventHandler<ActionEvent>()
	{
		@Override
		public void handle(ActionEvent arg0) 
		{
			String name = ((MenuItem)arg0.getTarget()).getText();
			
			if(name.endsWith("Undo"))
			{
				// Undo.
				gui.workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.undo();
			}
			else if(name.equals("Redo"))
			{
				// Redo.
				gui.workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.redo();
			}
			else if(name.equals("Copy"))
			{
				// Copy
				gui.workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.copy();
			}
			else if(name.equals("Cut"))
			{
				// Cut
				gui.workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.cut();
			}
			else if(name.equals("Paste"))
			{
				// Paste
				gui.workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.paste();
			}
			else if(name.equals("Search"))
			{
				// Search
				gui.workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.search();
			}
			else if(name.equals("Clear"))
			{
				// Search
				gui.workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.setCode("");
			}
		}	
	};
}
