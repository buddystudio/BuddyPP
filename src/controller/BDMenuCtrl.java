/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.AWTException;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import util.base.BDDrawUtil;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import util.base.Preferences;
import util.debug.BDSerial;
import util.io.BDCodeWriter;
import util.io.BDFileProc;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import model.BDCodeModel;
import model.BDLang;
import model.BDParameters;
import model.BDSerialManager2;
import model.BDWindowsManager;
import view.BDCompileAndUploadWindow;
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
	
	public Thread compileThread 	= null;
	public Thread uploadThread 		= null;
	public Thread subUploadThread 	= null;
	
	private BDMenuView menuView;
	
	//private static final Logger logger = LogManager.getLogger(BDCompiler.class);

	public void setHotKey() 
	{
		KeyCombination ctrlS = KeyCodeCombination.keyCombination("Ctrl+S");

		workspaceCtrl.workspaceView.setOnKeyPressed(event -> 
		{
			if (ctrlS.match(event)) 
			{
				// Ctrl + S 保存文件
				if (workspaceCtrl.workspaceView.workspaceModel.curTab.code.path == "") 
				{
					// 另存为文件
					BDFileProc.saveAsFile(workspaceCtrl);
				} 
				else 
				{
					try 
					{
						// 保存文件
						BDFileProc.saveFile(workspaceCtrl);
					} 
					catch (Exception ex) 
					{
						// 另存为文件
						BDFileProc.saveAsFile(workspaceCtrl);
					}
				}
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
				// 读取源码文件
				BDCodeModel code = BDFileProc.openFile();

				try 
				{
					if(code != null)
					{
						
						// 添加代码标签页
						workspaceCtrl.addTab(code, code.type);
					}
				} 
				catch (AWTException ex) 
				{
					//logger.error(ex.getMessage());
				}
			}
		});

		// 新建文件INO
		menuView.menuNewBtn.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				// 新建文件
				try 
				{
					// 添加代码标签页
					workspaceCtrl.addNewTab("INO");
				} 
				catch (AWTException ex) 
				{
					//logger.error(ex.getMessage());
				}
			}
		});
		
		// 新建文件Python
		menuView.menuNewPyBtn.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				// 新建文件
				try 
				{
					// 添加代码标签页
					workspaceCtrl.addNewTab("PY");
				} 
				catch (AWTException ex) 
				{
					//logger.error(ex.getMessage());
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
					BDFileProc.saveAsFile(workspaceCtrl);
				} 
				else 
				{
					try 
					{
						// 保存文件
						BDFileProc.saveFile(workspaceCtrl);
					} 
					catch (Exception ex) 
					{
						// 另存为文件
						BDFileProc.saveAsFile(workspaceCtrl);
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
				BDFileProc.saveAsFile(workspaceCtrl);
			}
		});

		// 打开例子程序
		menuView.menuExampleBtn.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				
				BDWindowsManager.expWindow = new BDExampleWindow();
				
				new BDExampleWindowCtrl(BDWindowsManager.expWindow, workspaceCtrl);
				
				// 弹出子窗口与主窗口居中
				BDDrawUtil.showInTheMiddle(BDWindowsManager.expWindow);
				
				// 打开例程窗口
				BDWindowsManager.expWindow.show();
			}
		});

		// 恢复
		menuView.menuUndoBtn.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				// 恢复
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
				BDWindowsManager.libWindow = new BDLibWindow();
				
				BDLibWindowCtrl libWindowCtrl = new BDLibWindowCtrl(BDWindowsManager.libWindow, workspaceCtrl);

				// 弹出子窗口与主窗口居中
				BDDrawUtil.showInTheMiddle(BDWindowsManager.libWindow);
				
				// 显示添加库窗口
				BDWindowsManager.libWindow.show();
			}
		});

		// 串口通讯
		menuView.menuComBtn.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				/*new BDSerialHelperWindow().show();

				// 启用新的串口调试助手
				if(true)
				{
					return;
				}*/
				
				// ============================================================
				
				BDParameters.serialports = BDSerial.list();

				// 串口通讯
				BDComWindowCtrl comWindowCtrl = new BDComWindowCtrl(BDWindowsManager.comWindow);

				// 弹出子窗口与主窗口居中
				BDDrawUtil.showInTheMiddle(BDWindowsManager.comWindow);
				
				// 显示串口通讯窗口
				BDWindowsManager.comWindow.show();
			}
		});

		BDCompileAndUploadWindow cauwView = new BDCompileAndUploadWindow();
		BDCompileAndUploadCtrl cauwCtrl = new BDCompileAndUploadCtrl(cauwView);

		// 编译
		menuView.menuVerifyBtn.setOnAction(new EventHandler<ActionEvent>() 
		{
			@SuppressWarnings("deprecation")
			@Override
			public void handle(ActionEvent event) 
			{
				// 显示编译功能窗口
				cauwView.show();
				
				cauwView.getSerialListCombox().setItems(new BDSerialManager2().getPortList());
				
				if(cauwView.getSerialListCombox().getItems().get(0).equals(BDLang.rb.getString("未连接")))
				{
					cauwView.getSerialListCombox().getSelectionModel().select(0);
				}
				else
				{
					cauwView.getSerialListCombox().getSelectionModel().select(cauwView.getSerialListCombox().getItems().size() - 1);
				}
				
				// 更新串口号
				cauwCtrl.updateSerialPorts();
				
				// 获取当前串口号
				Preferences.set("serial.port", cauwView.getSerialListCombox().getSelectionModel().getSelectedItem().toString());
				BDParameters.connectCom = cauwView.getSerialListCombox().getSelectionModel().getSelectedItem().toString();
				
				// 获取临时文件
				//String tempPath = System.getProperty("java.io.tmpdir") + "BDTmpPath";
				String tempPath = BDParameters.tempPath + "BDTmpPath";
				String builtPath = tempPath + File.separator + "Built";
				String codePath = tempPath + File.separator + "Code";

				//System.out.println("builtPath: " + builtPath);
				//System.out.println("codePath: " + codePath);
				
				// 保存临时源码文件
				String code = workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.getCode();
				workspaceCtrl.workspaceView.workspaceModel.curTab.code.setCodeText(code);

				cauwCtrl.openFileFromCode(builtPath + File.separator, codePath + File.separator);
				
				try 
				{
					// 清除已经存在的目录
					BDFileProc.deleteDir(tempPath);
					BDFileProc.deleteDir(builtPath);
					BDFileProc.deleteDir(codePath);
					
					// 创建源码临时目录
					File file01 = new File(tempPath);
					File file02 = new File(builtPath);
					File file03 = new File(codePath);

			        // Create temporary directory.
			        file01.mkdir();
			        file02.mkdir();
			        file03.mkdir();
			        
			        //System.out.println(codePath + "Code.ino");
			        
					// 写入文件
					BDCodeWriter.fileWriter(codePath + File.separator + "Code.ino", code);
					
					// 更改保存状态
					//workspaceCtrl.workspaceView.workspaceModel.curTab.code.isSaved = true;
				} 
				catch (IOException ex) 
				{
					//logger.error(this.toString(), ex);
				}
			}
		});

		// 烧录
		menuView.menuUploadBtn.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				BDDrawUtil.showInTheMiddle(cauwView);
				
				// 显示编译功能窗口
				cauwView.show();
				
				cauwView.getSerialListCombox().setItems(new BDSerialManager2().getPortList());
				
				if(cauwView.getSerialListCombox().getItems().get(0).equals(BDLang.rb.getString("未连接")))
				{
					// 如果当前列表选项为“未连接”则选中默认选项
					cauwView.getSerialListCombox().getSelectionModel().select(0);
				}
				else
				{
					// 如果当前串口列表不为空则选择最后一项
					cauwView.getSerialListCombox().getSelectionModel().select(cauwView.getSerialListCombox().getItems().size() - 1);
				}
				
				// 更新串口号
				cauwCtrl.updateSerialPorts();
				
				// 获取当前串口号
				Preferences.set("serial.port", cauwView.getSerialListCombox().getSelectionModel().getSelectedItem().toString());
				BDParameters.connectCom = cauwView.getSerialListCombox().getSelectionModel().getSelectedItem().toString();
				
				// 获取临时文件
				String tempPath = System.getProperty("java.io.tmpdir") + "BDTmpPath";
				String builtPath = tempPath + File.separator + "Built";
				String codePath = tempPath + File.separator + "Code";

				//System.out.println("builtPath: " + builtPath);
				//System.out.println("codePath: " + codePath);
				
				// 保存临时源码文件
				String code = workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.getCode();
				workspaceCtrl.workspaceView.workspaceModel.curTab.code.setCodeText(code);

				// 如果当前为Python工程
				//if(workspaceCtrl.workspaceView.workspaceModel.curTab.code.type.equals("PY"))
				if(workspaceCtrl.workspaceView.workspaceModel.curTab.code.getSuffix().equals("py"))
				{
					cauwCtrl.openFileFromPyCode(builtPath + File.separator, codePath + File.separator);
					
					return;
				}
				
				cauwCtrl.openFileFromCode(builtPath + File.separator, codePath + File.separator);
				
				try 
				{
					// 清除已经存在的目录
					BDFileProc.deleteDir(tempPath);
					BDFileProc.deleteDir(builtPath);
					BDFileProc.deleteDir(codePath);
					
					// 创建源码临时目录
					File file01 = new File(tempPath);
					File file02 = new File(builtPath);
					File file03 = new File(codePath);

			        // Create temporary directory.
			        file01.mkdir();
			        file02.mkdir();
			        file03.mkdir();
			        
			        //System.out.println(codePath + "Code.ino");
			        
					// 写入文件
					BDCodeWriter.fileWriter(codePath + File.separator + "Code.ino", code);
					
					// 更改保存状态
					//workspaceCtrl.workspaceView.workspaceModel.curTab.code.isSaved = true;
				} 
				catch (IOException ex) 
				{
					//logger.error(this.toString(), ex);
				}
			}
		});

		// 设置
		menuView.menuSettingBtn.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				BDPluginWindowCtrl pluginWindowCtrl = new BDPluginWindowCtrl(BDWindowsManager.pluginWindow, workspaceCtrl);

				// 弹出子窗口与主窗口居中
				BDDrawUtil.showInTheMiddle(BDWindowsManager.pluginWindow);
				
				// 设置
				BDWindowsManager.pluginWindow.show();
			}
		});

		// 关于我们
		menuView.menuAboutBtn.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				// 弹出关于我们的窗口
				BDWindowsManager.aboutWindow.show();
				
				// 弹出子窗口与主窗口居中
				BDDrawUtil.showInTheMiddle(BDWindowsManager.aboutWindow);
			}
		});
		
		// 查看帮助文档
		menuView.menuHelpBtn.setOnAction((ActionEvent e) -> 
        {  
        	// 临时跳转网站
        	this.browserUrl("http://buddy.mongcj.com/buddypp.html");
        }); 
	}
	
	public void browserUrl(String url)
    {
        // 判断是否支持Desktop扩展,如果支持则进行下一步
        if (Desktop.isDesktopSupported())
        {
            try 
            {
                URI uri = new URI(url);
                
                Desktop desktop = Desktop.getDesktop(); //创建desktop对象
                
                // 调用默认浏览器打开指定URL
                desktop.browse(uri);
 
            } 
            catch (URISyntaxException e) 
            {
                e.printStackTrace();
 
            } 
            catch (IOException e) 
            {
                // 如果没有默认浏览器时，将引发下列异常
                e.printStackTrace();
            }
        }
    }
}
