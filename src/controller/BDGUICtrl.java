/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.GraphicsConfiguration;
import java.io.File;

import util.base.BDDrawUtil;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import util.io.BDFileProc;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.BDCodeTabModel;
import model.BDLang;
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
    
    boolean isDragging = false;
    boolean isEdged	= false;

    //private static final Logger logger = LogManager.getLogger();
    //private BDDialogWindow saveWindow = new BDDialogWindow("保存提示", "是否保存当前文件");
    
    private double pos = 0;

    public BDGUICtrl(BDGUIView gui) 
    {
        this.gui = gui;
        
        //pos = gui.splitPanel.getDividers().get(0).getPosition();
        
        // 获取编辑器参数
        //BDParameters.getEditorProfile();

        // 添加菜单操作侦听器
        gui.clearItem.setOnAction(editHandler);
        gui.undoItem.setOnAction(editHandler);
        gui.redoItem.setOnAction(editHandler);
        gui.cutItem.setOnAction(editHandler);
        gui.copyItem.setOnAction(editHandler);
        gui.pasteItem.setOnAction(editHandler);
        gui.searchItem.setOnAction(editHandler);
        
        //this.gui.saveWindow = new BDDialogWindow("  保存提示", "是否保存当前文件");
        this.gui.saveWindow = new BDDialogWindow("  " + BDLang.rb.getString("保存提示"), BDLang.rb.getString("是否保存当前文件"));
        
        // 检测分割面板尺寸变化
        gui.workspacePanel.widthProperty().addListener((obs, oldVal, newVal) -> 
        {
        	// 获取分割位置信息
        	pos = gui.splitPanel.getDividers().get(0).getPosition();
        	
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
            		// 解除锁定右侧面板
            		SplitPane.setResizableWithParent(gui.consolePanel, true);
            		
            		if (gui.guiModel.isMaximized == false) 
            		{
            			gui.primaryStage.setWidth(BDParameters.curWidth + 200);
            		}

            		gui.arrowBtn.setImage(gui.iconArrowLeftImg);
            		gui.splitPanel.setDividerPosition(0, 0.5);
            	}
            	else
            	{
            		Thread thread = new Thread() 
            		{	 
                        @Override
                        public void run() 
                        {
                        	if (gui.guiModel.isMaximized == false) 
                    		{
                        		gui.primaryStage.setWidth(BDParameters.curWidth);
                    		}
                    		
                    		gui.arrowBtn.setImage(gui.iconArrowRightImg);
                    		gui.splitPanel.setDividerPosition(0, 1);
                    		
                    		try
							{
                    			// 稍作延时
								Thread.sleep(50);
								
								// 锁定右侧面板
	                    		SplitPane.setResizableWithParent(gui.consolePanel, false);
								
							} catch (InterruptedException e)
							{
								e.printStackTrace();
							}
                        }
                    };
                    
                    thread.start();
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

            	BDDrawUtil.showInTheMiddle(gui.settingWindow);
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

        // 点击最大化按钮(与恢复)
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
            	
            	BDDrawUtil.showInTheMiddle(gui.aboutWindow);
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
                
                if(gui.guiModel.isMaximized == false)
                {
                	// 记录窗体当前的位置
                    gui.guiModel.preX = gui.primaryStage.getX();
                    gui.guiModel.preY = gui.primaryStage.getY();
                }
                
                SplitPane.setResizableWithParent(gui.consolePanel, false);
            }
        });
        
        gui.titlePanel.setOnMouseReleased(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event) 
            {
            	double screenWidth = gui.visualBounds.getWidth();
            	double screenHeight = gui.visualBounds.getHeight();
            	
            	double screenX = event.getScreenX();
            	double screenY = event.getScreenY();
            	
            	double offset = 10;

            	if(isDragging == true)
            	{	
            		if(BDParameters.gds.length == 1)
            		{
            			// 左边缘
                		if((screenX < offset) && (screenY > offset))
                		{
                            // 窗体依附左边缘
                            if(BDParameters.os.equals("Mac OS X"))
                            {
                            	gui.primaryStage.setX(0);
                                gui.primaryStage.setY(22);
                                gui.primaryStage.setWidth(gui.visualBounds.getWidth() / 2);
                                gui.primaryStage.setHeight(gui.visualBounds.getHeight() + 4);
                            }
                            else
                            {
                            	gui.primaryStage.setX(-1);
                                gui.primaryStage.setY(-1);
                                gui.primaryStage.setWidth(gui.visualBounds.getWidth() / 2 + 2);
                                gui.primaryStage.setHeight(gui.visualBounds.getHeight() + 2);
                            }
                            
                            isEdged = true;
                		}
                		else if((screenX > screenWidth - offset) && (screenY > offset))
                		{
                			// 窗体依附右边缘
                            if(BDParameters.os.equals("Mac OS X"))
                            {
                            	gui.primaryStage.setX(gui.visualBounds.getWidth() / 2);
                                gui.primaryStage.setY(22);
                                gui.primaryStage.setWidth(gui.visualBounds.getWidth() / 2);
                                gui.primaryStage.setHeight(gui.visualBounds.getHeight() + 4);
                            }
                            else
                            {
                            	gui.primaryStage.setX(-1 + gui.visualBounds.getWidth() / 2 + 2);
                                gui.primaryStage.setY(-1);
                                gui.primaryStage.setWidth(gui.visualBounds.getWidth() / 2 + 2);
                                gui.primaryStage.setHeight(gui.visualBounds.getHeight() + 2);
                            }
                            
                            isEdged = true;
                		}
                		else if(screenY < offset)
                		{
                			// 自定义最大化
                            if(BDParameters.os.equals("Mac OS X"))
                            {
                            	BDParameters.curWidth = gui.primaryStage.getWidth();
                                BDParameters.curHeight = gui.primaryStage.getHeight();
                                
                            	gui.primaryStage.setX(0);
                                gui.primaryStage.setY(22);
                                gui.primaryStage.setWidth(gui.visualBounds.getWidth());
                                gui.primaryStage.setHeight(gui.visualBounds.getHeight() + 4);
                            }
                            else
                            {
                            	BDParameters.curWidth = gui.primaryStage.getWidth();
                                BDParameters.curHeight = gui.primaryStage.getHeight();
                                
                            	gui.primaryStage.setX(-1);
                                gui.primaryStage.setY(-1);
                                gui.primaryStage.setWidth(gui.visualBounds.getWidth() + 2);
                                gui.primaryStage.setHeight(gui.visualBounds.getHeight() + 2);
                            }
                            
                            // 更新状态标签
                            gui.guiModel.isMaximized = true;
                		}
            		}
            		
            		isDragging = false;
            	}
            }
        });

        // 拖动窗口
        gui.titlePanel.setOnMouseDragged(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event) 
            {
            	isDragging = true;
            	
            	gui.primaryStage.setX(event.getScreenX() - xOffset);
                gui.primaryStage.setY(event.getScreenY() - yOffset);
                
                if(gui.guiModel.isMaximized == true)
                {
                	/*//gui.primaryStage.setX(event.getScreenX() - gui.primaryStage.getWidth() / 2);
                    //gui.primaryStage.setY(event.getScreenY() - 20);
                	
                	gui.primaryStage.setX(event.getScreenX() - xOffset);
                    gui.primaryStage.setY(event.getScreenY() - yOffset);
                    
                    // 恢复原来的尺寸
                    gui.primaryStage.setWidth(BDParameters.curWidth);
                    gui.primaryStage.setHeight(BDParameters.curHeight);

                    gui.guiModel.isMaximized = false;*/
                    
                    setResizeWindow();
                }
                
                if(isEdged == true)
                {
                	setResizeWindow();
                }
            }
        });
    }

    // 
    private void isClose(int index) 
    {
    	// 关闭窗口时记录当前位置与尺寸
    	BDParameters.editorPosX = String.valueOf(this.gui.primaryStage.getX());
    	BDParameters.editorPosY = String.valueOf(this.gui.primaryStage.getY());
    	
    	BDParameters.editorWidth = String.valueOf(this.gui.primaryStage.getWidth());
    	BDParameters.editorHeight = String.valueOf(this.gui.primaryStage.getHeight());
    	
        if (index < 0) 
        {
        	// 保存参数
        	BDParameters.writeProfile();
        	
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
            this.gui.saveWindow.msgLbl.setText(BDLang.rb.getString("是否保存对") + " " + tab.code.getName() + " " + BDLang.rb.getString("文件的修改？"));

            this.gui.saveWindow.show();
            
            if(BDParameters.gds.length != 1)
        	{
            	BDDrawUtil.showInTheMiddle(this.gui.saveWindow);
        	}

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
                        
                        // 保存参数
                    	BDParameters.writeProfile();

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

                        // 保存文件(文件路径为空)
                        if (tab.code.path == "") 
                        {
                        	// 另存为文件
                            //if(saveAsFile(tab) == false)
                        	if(BDFileProc.saveAsFile(gui.workspaceCtrl) == false) 
                            {
                            	return;
                            }
                        } 
                        else 
                        {
                            try 
                            {
                                // 保存文件
                            	BDFileProc.saveFile(gui.workspaceCtrl);
                            } 
                            catch (Exception ex) 
                            {
                                // 另存为文件
                                //if(saveAsFile(tab) == false) 
                            	if(BDFileProc.saveAsFile(gui.workspaceCtrl) == false)
                                {
                                	return;
                                }
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
        	// 如果文件不存在(保存后文件被删除的情况)
        	if(!new File(tab.code.path).exists())
        	{
        		// 另存为文件
                //if(saveAsFile(tab) == false)
        		if(BDFileProc.saveFile(gui.workspaceCtrl) == false) 
                {
                	return;
                }
        	}
        	
        	// 保存参数
        	BDParameters.writeProfile();
        	
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
        if (gui.guiModel.isMaximized == true) 
        {
        	// 获取分割位置信息
        	//pos = gui.splitPanel.getDividers().get(0).getPosition();
        	
            // 恢复原来的位置
            gui.primaryStage.setX(gui.guiModel.preX);
            gui.primaryStage.setY(gui.guiModel.preY);

            // 恢复原来的尺寸
            //gui.primaryStage.setWidth(940);
            //gui.primaryStage.setHeight(640 + 10 + 10);
            gui.primaryStage.setWidth(BDParameters.curWidth);
            gui.primaryStage.setHeight(BDParameters.curHeight);

            // 更新状态标签
            gui.guiModel.isMaximized = false;
        }
        else if(isEdged == true)
        {
        	// 恢复原来的位置
            gui.primaryStage.setX(gui.guiModel.preX);
            gui.primaryStage.setY(gui.guiModel.preY);

            // 恢复原来的尺寸
            gui.primaryStage.setWidth(BDParameters.curWidth);
            gui.primaryStage.setHeight(BDParameters.curHeight);
            
            isEdged = false;
        }
        else 
        {
            // 获取分割位置信息
        	//pos = gui.splitPanel.getDividers().get(0).getPosition();

            // 记录窗体当前的位置
            gui.guiModel.preX = gui.primaryStage.getX();
            gui.guiModel.preY = gui.primaryStage.getY();
            
            int yOffset = -1;
            int hOffset = 2;
            
            BDParameters.curWidth = gui.primaryStage.getWidth();
            BDParameters.curHeight = gui.primaryStage.getHeight();

            // 自定义最大化
            if(BDParameters.os.equals("Mac OS X"))
            {
                yOffset = 22;
                hOffset = 4;
            }
            
            // 多屏幕的情况
        	if(BDParameters.gds.length == 1)
        	{
        		// 单一屏幕
            	gui.primaryStage.setX(-1);
                gui.primaryStage.setY(yOffset);
                gui.primaryStage.setWidth(gui.visualBounds.getWidth() + 2);
                gui.primaryStage.setHeight(gui.visualBounds.getHeight() + hOffset);
        	}
        	else if(BDParameters.gds.length == 2)
        	{
        		// 两个屏幕
        		GraphicsConfiguration gc0 = BDParameters.gds[0].getDefaultConfiguration();
        		GraphicsConfiguration gc1 = BDParameters.gds[1].getDefaultConfiguration();
        		
        		int w0 = BDParameters.gds[0].getDisplayMode().getWidth();
    			int h0 = BDParameters.gds[0].getDisplayMode().getHeight();
    			
    			int w1 = BDParameters.gds[1].getDisplayMode().getWidth();
    			int h1 = BDParameters.gds[1].getDisplayMode().getHeight();
        		
        		Boolean isLeft = true;
        		
        		System.out.println("测试");
        		
        		System.out.println(gc0.getBounds().x);
        		System.out.println(gc1.getBounds().x);
        		
        		BDParameters.curWidth = gui.primaryStage.getWidth();
                BDParameters.curHeight = gui.primaryStage.getHeight();
        		
        		if(gc1.getBounds().x > -1)
        		{
        			// 左边为主屏幕
        			if(gui.guiModel.preX + BDParameters.curWidth / 2 < w0)
        			{
        				System.out.println("左边为主屏幕-左边");
        				
        				gui.primaryStage.setX(-1);
        			}
        			else
        			{
        				System.out.println("左边为主屏幕-右边");
        				
        				gui.primaryStage.setX(-1 + gc0.getBounds().x);
        			}
        		}
        		else
        		{
        			// 右边为主屏幕
        			
        			// 当前窗口在主屏幕
        			if(gui.guiModel.preX + BDParameters.curWidth / 2 > 0)
        			{
        				System.out.println("右边为主屏幕-右边");

                    	gui.primaryStage.setX(-1 + gc0.getBounds().x);
        			}
        			else
        			{
        				// 当前窗口在次屏幕
        				System.out.println("右边为主屏幕-左边");
                        
                    	gui.primaryStage.setX(-1 + gc1.getBounds().x);
        			}
        		}
        		
        		gui.primaryStage.setY(yOffset);
                gui.primaryStage.setWidth(gui.visualBounds.getWidth() + 2);
                gui.primaryStage.setHeight(gui.visualBounds.getHeight() + hOffset);
        	}	
            
            // 更新状态标签
            gui.guiModel.isMaximized = true;
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


