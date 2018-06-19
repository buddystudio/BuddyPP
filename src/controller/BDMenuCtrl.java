/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import util.debug.BDAvrdudeUploader;
import util.debug.BDCompiler;
import util.debug.BDRunnerException;
import util.debug.BDSerial;
import util.debug.BDUploader;
import util.io.BDCodeReader;
import util.io.BDCodeWriter;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.FileChooser;
import javafx.stage.WindowEvent;
import model.BDCodeModel;
import model.BDParameters;

import view.BDExampleWindow;
import view.BDLibWindow;
import view.BDMenuView;
import view.BDWindow;

/**
 *
 * @author gsh
 */
public class BDMenuCtrl 
{
	public BDWorkspaceCtrl workspaceCtrl;
	
	public Thread compileThread = null;
	public Thread uploadThread 	= null;
	
	private BDMenuCtrl root;
	private BDMenuView menuView;
	
	private static final Logger logger = LogManager.getLogger(BDCompiler.class);

	public void setHotKey() 
	{
		KeyCombination ctrlS = KeyCodeCombination.keyCombination("Ctrl+S");
		KeyCombination ctrlF = KeyCodeCombination.keyCombination("Ctrl+F");

		workspaceCtrl.workspaceView.setOnKeyPressed(event -> 
		{
			if (ctrlS.match(event)) 
			{
				System.out.println("Save File");

				// Ctrl + S 保存文件
				if (workspaceCtrl.workspaceView.workspaceModel.curTab.code.path == "") 
				{
					// 另存为文件
					saveAsFile();
				} 
				else 
				{
					try 
					{
						// 保存文件
						saveFile();
					} 
					catch (Exception ex) 
					{
						// 另存为文件
						saveAsFile();
					}
				}
			}
			
			if (ctrlF.match(event)) 
			{
				new BDSearchWindowCtrl(menuView.searchWindow, workspaceCtrl);
				
				// 显示搜索窗口
				menuView.searchWindow.show();		
			}
		});
	}

	public BDMenuCtrl(BDMenuView menuView) 
	{
		this.menuView = menuView;

		// 打开文件
		menuView.menuOpenBtn.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				// 打开文件
				File file;
				FileChooser fileChooser = new FileChooser();

				// Set extension filter
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
				file = fileChooser.showOpenDialog(null);
				
				if(file == null)
					return;
				
				BDCodeModel code = new BDCodeModel();
				
				code.setName(file.getName());

				try 
				{
					//code.setCodeText(BDCodeReader.readFileByLines2(file.getPath()));
					code.setCodeText(BDCodeReader.readFileByLines(file.getPath()));

					// 写入文件路径
					code.path = file.getPath();
				} 
				catch (FileNotFoundException ex) 
				{
					logger.error(ex.getMessage());
				} 
				catch (IOException ex) 
				{
					logger.error(ex.getMessage());
				}

				try 
				{
					// 添加代码标签页
					workspaceCtrl.addTab(code);
					
				} 
				catch (AWTException ex) 
				{
					logger.error(ex.getMessage());
				}
			}
		});

		// 新建文件
		menuView.menuNewBtn.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				// 新建文件
				try 
				{
					// 添加代码标签页
					workspaceCtrl.addNewTab();
				} 
				catch (AWTException ex) 
				{
					logger.error(ex.getMessage());
				}
			}
		});

		// 保存文件
		menuView.menuSaveBtn.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				// 保存文件
				if (workspaceCtrl.workspaceView.workspaceModel.curTab.code.path == "") 
				{
					// 另存为文件
					saveAsFile();
				} 
				else 
				{
					try 
					{
						// 保存文件
						saveFile();
					} 
					catch (Exception ex) 
					{
						// 另存为文件
						saveAsFile();
					}
				}
			}
		});

		// 另存为文件
		menuView.menuSaveAsBtn.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				// 另存文件
				saveAsFile();
			}
		});

		// 打开例子程序
		menuView.menuExampleBtn.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{

				// 打开例子
				if (menuView.expWindow.isShowing()) 
				{
					menuView.expWindow.close();
				}

				menuView.expWindow = new BDExampleWindow();
				BDExampleWindowCtrl exampleWindowCtrl = new BDExampleWindowCtrl(menuView.expWindow, workspaceCtrl);

				// 添加代码标签页
				menuView.expWindow.show();
				
				if(BDParameters.os.equals("Mac OS X"))
				{
					return;
				}
				
				// 弹出子窗口与主窗口居中
				showInTheMiddle(menuView.expWindow);
			}
		});

		// 恢复
		menuView.menuUndoBtn.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				// 恢复
				//workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.undoLastAction();
				workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.undo();
			}
		});

		// 重做
		menuView.menuRedoBtn.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				// 重做
				//workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.redoLastAction();
				workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.redo();
			}
		});

		// 搜索
		menuView.menuSearchBtn.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				// 搜索
				//BDSearchWindowCtrl serchWindowCtrl = new BDSearchWindowCtrl(menuView.searchWindow, workspaceCtrl);
				// 显示搜索窗口
				//menuView.searchWindow.show();
				
				workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.search();
			}
		});

		// 添加库
		menuView.menuLibBtn.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				// 添加库
				if (menuView.libWindow.isShowing()) 
				{
					menuView.libWindow.close();
				}

				menuView.libWindow = new BDLibWindow();
				
				BDLibWindowCtrl libWindowCtrl = new BDLibWindowCtrl(menuView.libWindow, workspaceCtrl);

				// 显示添加库窗口
				menuView.libWindow.show();
				
				if(BDParameters.os.equals("Mac OS X"))
				{
					return;
				}
				
				// 弹出子窗口与主窗口居中
				showInTheMiddle(menuView.libWindow);
			}
		});

		// 串口通讯
		menuView.menuComBtn.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				BDParameters.serialports = BDSerial.list();

				if (BDParameters.serialports.isEmpty() || menuView.lbCom.getText().equals("当前串口：未连接")) {
					menuView.lbCom.setText("当前串口：未连接");

					// 弹出对话框提示用户未接入开发板
					menuView.hintDialogWindow.show();
					
					if(BDParameters.os.equals("Mac OS X"))
					{
						return;
					}
					
					// 弹出子窗口与主窗口居中
					showInTheMiddle(menuView.hintDialogWindow);

					// 如果未连接则返回
					return;
				}

				// 串口通讯
				BDComWindowCtrl comWindowCtrl = new BDComWindowCtrl(menuView.comWindow);

				// 显示串口通讯窗口
				menuView.comWindow.show();
				
				if(BDParameters.os.equals("Mac OS X"))
				{
					return;
				}
				
				// 弹出子窗口与主窗口居中
				showInTheMiddle(menuView.comWindow);
			}
		});
		
		// 关闭窗口中止编译操作
		menuView.consoleWindow.setOnHiding(new EventHandler<WindowEvent>() 
		{
	         @Override
	         public void handle(WindowEvent event) 
	         {
	        	 if(compileThread != null)
	        	 {
	        		 if(!compileThread.isAlive())
		        	 {
		        		 return;
		        	 }
	        		 
	        		 // 终止编译
	        		 compileThread.stop();
	        		 
	        		 System.out.println("");
	        		 System.out.println("*********************************************************");
	        		 System.out.println("");
	        		 System.out.println("Buddy++ : Compiler processing has been stoped.");
	        		 System.out.println("");
	        		 System.out.println("*********************************************************");
	        		 System.out.println("");
	        	 }
	        	 
	        	 if(uploadThread != null)
	        	 {
	        		 if(!uploadThread.isAlive())
		        	 {
		        		 return;
		        	 }
	        		 
	        		 // 终止上传
	        		 uploadThread.stop();
	        		 
	        		 System.out.println("");
	        		 System.out.println("*********************************************************");
	        		 System.out.println("");
	        		 System.out.println("Buddy++ : Upload processing has been stoped.");
	        		 System.out.println("");
	        		 System.out.println("*********************************************************");
	        		 System.out.println("");
	        	 }
	         }
	     });

		// 编译
		menuView.menuVerifyBtn.setOnAction(new EventHandler<ActionEvent>() 
		{
			@SuppressWarnings("deprecation")
			@Override
			public void handle(ActionEvent event) 
			{
				BDConsoleWindowCtrl consoleWindowCtrl = new BDConsoleWindowCtrl(menuView.consoleWindow);
				// consoleWindowCtrl.setUploadProgressVisable(false);

				// 显示编译信息窗口
				menuView.consoleWindow.show();
				
				if(!BDParameters.os.equals("Mac OS X"))
				{
					// 弹出子窗口与主窗口居中
					showInTheMiddle(menuView.consoleWindow);
				}

				// 清除控制台信息
				menuView.consloeArea.clear();
				
				// 获取分割位置信息
	        	double pos = menuView.splitPanel.getDividers().get(0).getPosition();
	        	
	        	// 如果控制台未展开
	        	if(pos > 0.99)
	        	{
	        		// 滑出控制台
					menuView.splitPanel.setDividerPosition(0, 0.5);
	        	}

				Task<Void> progressTask = new Task<Void>() 
				{
					@Override
					protected Void call() throws Exception 
					{
						// 编译
						compile(consoleWindowCtrl);
						throw new UnsupportedOperationException("Not supported yet.");
					}
				};

				// 开始编译任务
				compileThread = new Thread(progressTask);
				
				compileThread.start();
				//compileThread.stop();
				//compileThread.interrupt();
			}
		});

		// 烧录
		menuView.menuUploadBtn.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				BDParameters.serialports = BDSerial.list();

				if (BDParameters.serialports.isEmpty() || menuView.lbCom.getText().equals("当前串口：未连接")) 
				{
					menuView.lbCom.setText("当前串口：未连接");

					// 弹出对话框提示用户未接入开发板
					menuView.hintDialogWindow.show();
					
					if(!BDParameters.os.equals("Mac OS X"))
					{
						// 弹出子窗口与主窗口居中
						showInTheMiddle(menuView.hintDialogWindow);
					}

					// 如果未连接则返回
					return;
				}
				
				try
				{
					menuView.consoleWindow.contain.getChildren().remove(menuView.consoleWindow.bottomPanel);
					menuView.consoleWindow.contain.getChildren().add(menuView.consoleWindow.progressBarDebug);
				}
				catch(Exception ex){}

				BDConsoleWindowCtrl consoleWindowCtrl = new BDConsoleWindowCtrl(menuView.consoleWindow);				

				// 烧录
				menuView.consoleWindow.show();
				
				if(!BDParameters.os.equals("Mac OS X"))
				{
					// 弹出子窗口与主窗口居中
					showInTheMiddle(menuView.consoleWindow);
				}

				// 清除控制台信息
				menuView.consloeArea.clear();
				
				// 获取分割位置信息
	        	double pos = menuView.splitPanel.getDividers().get(0).getPosition();
	        	
	        	// 如果控制台未展开
	        	if(pos > 0.99)
	        	{
	        		// 滑出控制台
					menuView.splitPanel.setDividerPosition(0, 0.5);
	        	}

				Task<Void> progressTask = new Task<Void>() 
				{
					@Override
					protected Void call() throws Exception 
					{
						// 编译上传
						if (upload(consoleWindowCtrl))
						{
							Platform.runLater(new Runnable() 
							{
								@Override
								public void run() 
								{
									consoleWindowCtrl.getView().lbl.setText("恭喜你，烧录已完成！");
								}
							});
						}
						throw new UnsupportedOperationException("Not supported yet.");
					}
				};

				// 开始编译上传任务
				uploadThread = new Thread(progressTask);
				
				uploadThread.start();
			}
		});

		// 设置
		menuView.menuSettingBtn.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				BDPluginWindowCtrl pluginWindowVtrl = new BDPluginWindowCtrl(menuView.pluginWindow, workspaceCtrl);

				// 设置
				menuView.pluginWindow.show();
				
				if(BDParameters.os.equals("Mac OS X"))
				{
					return;
				}
				
				// 弹出子窗口与主窗口居中
				showInTheMiddle(menuView.pluginWindow);
			}
		});

		// 关于我们
		menuView.menuAboutBtn.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				// 弹出关于我们的窗口
				menuView.aboutWindow.show();
				
				if(BDParameters.os.equals("Mac OS X"))
				{
					return;
				}
				
				// 弹出子窗口与主窗口居中
				showInTheMiddle(menuView.aboutWindow);
			}
		});

		// 预设值（设置板型和串口）
		menuView.menuPreSetBtn.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				// 设置板型和串口
				BDPreSetWindowCtrl preSetWindowCtrl;
				
				try 
				{
					menuView.psw.ReflashPort();
					
					preSetWindowCtrl = new BDPreSetWindowCtrl(menuView);
	
					menuView.psw.show();
					
					if(BDParameters.os.equals("Mac OS X"))
					{
						return;
					}
					
					// 弹出子窗口与主窗口居中
					showInTheMiddle(menuView.psw);
				} 
				catch (Exception ex) 
				{
					logger.error(this.toString(), ex);
				}
			}
		});
	}

	// 保存文件
	private void saveFile() 
	{
		//String code = workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getText();
		//workspaceCtrl.workspaceView.workspaceModel.curTab.code.setCodeText(code);
		
		String code = workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.getCode();
		workspaceCtrl.workspaceView.workspaceModel.curTab.code.setCodeText(code);
		
		try 
		{
			// 写入文件
			BDCodeWriter.fileWriter(workspaceCtrl.workspaceView.workspaceModel.curTab.code.path, code);
			
			// 更改保存状态
			workspaceCtrl.workspaceView.workspaceModel.curTab.code.isSaved = true;

		} 
		catch (IOException ex) 
		{
			logger.error(this.toString(), ex);
		}
	}

	// 另存为文件
	@SuppressWarnings("null")
	private void saveAsFile() 
	{
		try 
		{
			File file = null;
			FileChooser fileChooser = new FileChooser();
	
			// Set extension filter
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

			try
			{
				// Show open file dialog
				file = fileChooser.showSaveDialog(null);
			}
			catch(Exception ex){}

			if(file == null)
			{
				return;
			}
		
			// Write file
			BDCodeWriter.fileWriter(file.getPath(),
				//workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getText());
				workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.getCode());
				// Update the file name.
				workspaceCtrl.workspaceView.workspaceModel.curTab.code.path = file.getPath();
				// Update the tab name.
				workspaceCtrl.workspaceView.workspaceModel.curTab.tab.setText(file.getName());
				// Update the tab state
				workspaceCtrl.workspaceView.workspaceModel.curTab.code.isSaved = true;
		} 
		catch (IOException ex) 
		{
			logger.error(this.toString(), ex);
		}
	}

	private void compile(BDConsoleWindowCtrl consoleWindowCtrl) 
	{
		//BDPreprocessor preprocessor = new BDPreprocessor();

		BDCodeModel code = workspaceCtrl.workspaceView.workspaceModel.curTab.code;
		
		if (!code.isSaved)
			//saveFile();

		if (code.getClassName().isEmpty())
			code.setClassName(workspaceCtrl.workspaceView.workspaceModel.curTab.tab.getText()); // 编译类名
		
		String classname = code.getClassName();
		
		// 临时文件路径
		String compilepath = System.getProperty("java.io.tmpdir") + classname;
		
		// 清屏
		//menuView.consloeArea.clear();
		
		// 输出编译初始化信息
		System.out.println("");
		System.out.println("*********************************************************");
		System.out.println("");
		System.out.println("Buddy++ : Compiler processing will start.");
		System.out.println("");
		System.out.println("*********************************************************");
		System.out.println("");
		
		// 编译后源码路径
		if (!BDCompiler.clearBuildPath(compilepath))
			return;

		try 
		{
			classname = code.preprocess(compilepath);
			code.setClassName(classname);
		} 
		catch (BDRunnerException ex) 
		{
			logger.error(this.toString(), ex);
		}

		code.compilePath = compilepath;

		int len = workspaceCtrl.workspaceView.workspaceModel.tabList.size();

		// 设置所有代码标签的编译状态为"未编译"
		for (int i = 0; i < len; i++) 
		{
			workspaceCtrl.workspaceView.workspaceModel.tabList.get(i).code.isCompiled = false;
		}

		try 
		{
			BDCompiler compiler = new BDCompiler();
			
			compiler.addProgressStatusListener(consoleWindowCtrl); // 进度条消息处理
			//consoleWindowCtrl.clearText();
			
			// 编译
			if (compiler.compile(compilepath, compilepath, classname, code.getImportedLibraries(), true)) 
			{
				// 编译成功
				code.isCompiled = true;
				
				// 输出编译成功信息
				System.out.println("");
				System.out.println("*********************************************************");
				System.out.println("");
				System.out.println("Buddy++ : Compiler processing complete.");
				System.out.println("");
				System.out.println("*********************************************************");
				System.out.println("");
			} 
			else 
			{
				// 编译失败
				Platform.runLater(new Runnable() 
				{
					@Override
					public void run()
					{
						consoleWindowCtrl.getView().lbl.setText("很遗憾，编译失败！");
						
						// 更新编译进度对话框，隐藏进度条显示操作按钮
						consoleWindowCtrl.addBtns();
						
						menuView.consloeArea.appendText("\n\n");
						menuView.consloeArea.appendText("*********************************************************");
						menuView.consloeArea.appendText("\n\n");
						menuView.consloeArea.appendText("Buddy++ : Compiler processing Uusuccessful.");
						menuView.consloeArea.appendText("\n\n");
						menuView.consloeArea.appendText("*********************************************************");
						menuView.consloeArea.appendText("\n\n");
					}
				});
			}
		} 
		catch (Exception ex) 
		{
			code.isCompiled = false;
			logger.error(this.toString(), ex);
			// System.out.println(ex.getMessage());
			
			// 编译失败
			Platform.runLater(new Runnable() 
			{
				@Override
				public void run() 
				{
					consoleWindowCtrl.getView().lbl.setText("很遗憾，编译失败！");
	
					// 更新编译进度对话框，隐藏进度条显示操作按钮
					consoleWindowCtrl.addBtns();
					
					menuView.consloeArea.appendText("\n\n");
					menuView.consloeArea.appendText("*********************************************************");
					menuView.consloeArea.appendText("\n\n");
					menuView.consloeArea.appendText("Buddy++ : Compiler processing Uusuccessful.");
					menuView.consloeArea.appendText("\n\n");
					menuView.consloeArea.appendText("*********************************************************");
					menuView.consloeArea.appendText("\n\n");
				}
			});
		}
	}

	private boolean upload(BDConsoleWindowCtrl consoleWindowCtrl) 
	{
		BDUploader uploader;
		BDCodeModel code = workspaceCtrl.workspaceView.workspaceModel.curTab.code;
		//consoleWindowCtrl.clearText();
		if (!code.isCompiled)
			compile(consoleWindowCtrl);

		consoleWindowCtrl.setUploadStyle();
		uploader = new BDAvrdudeUploader();
		uploader.addProgressStatusListener(consoleWindowCtrl);
		
		try 
		{
			Platform.runLater(new Runnable() 
			{
				@Override
				public void run() 
				{
					consoleWindowCtrl.getView().lbl.setText("正在烧录...");
					
					// 更新烧录进度对话框，恢复进度条显示
					consoleWindowCtrl.removeBtns();
					
					// 输出烧录初始化信息
					System.out.println("");
					System.out.println("*********************************************************");
					System.out.println("");
					System.out.println("Buddy++ : Upload processing will start.");
					System.out.println("");
					System.out.println("*********************************************************");
					System.out.println("");
				}
			});

			return uploader.uploadUsingPreferences(code.compilePath, code.getCppName(), false); // 上传
		} 
		catch (Exception ex) 
		{
			logger.error(this.toString(), ex);
			// System.out.println(e.getMessage());

			Platform.runLater(new Runnable() 
			{
				@Override
				public void run() 
				{
					consoleWindowCtrl.getView().lbl.setText("很遗憾，烧录失败！");
					
					// 更新编译进度对话框，隐藏进度条显示操作按钮
					consoleWindowCtrl.addBtns();
					
					// 输出烧录失败信息
					System.out.println("");
					System.out.println("*********************************************************");
					System.out.println("");
					System.out.println("Buddy++ : Upload processing Uusuccessful.");
					System.out.println("");
					System.out.println("*********************************************************");
					System.out.println("");
				}
			});

			return false;
		}
	}
	
	// 实现子窗体居中于主窗体
	private void showInTheMiddle(BDWindow win)
	{
		double posX = menuView.primaryStage.getX() + (menuView.primaryStage.getWidth() - win.getWidth()) / 2;
		double posY = menuView.primaryStage.getY() + (menuView.primaryStage.getHeight() - win.getHeight()) / 2;
		
		win.setX(posX);
		win.setY(posY);
	}
}
