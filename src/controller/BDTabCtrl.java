/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import util.io.BDCodeReader;
import util.io.BDCodeWriter;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import model.BDCodeModel;
import model.BDCodeTabModel;

import view.BDDialogWindow;
import view.BDWorkspaceView;

/**
 *
 * @author gsh
 */
public class BDTabCtrl 
{
	public BDWorkspaceView workspaceView;
	private BDCodeTabModel tab;
	private static final Logger logger = LogManager.getLogger(BDTabCtrl.class);

	public BDTabCtrl(BDCodeTabModel newTab, BDWorkspaceCtrl workspaceCtrl) 
	{
		this.tab = (BDCodeTabModel)newTab;

		if (!newTab.tab.isSelected()) {
			newTab.tab.setGraphic(newTab.hlink2);
		}

		newTab.hlink1.setOnMouseClicked(new EventHandler<javafx.event.Event>() 
		{
			@Override
			public void handle(javafx.event.Event e) 
			{
				// 删除标签页
				try {
					removeTab();
				} catch (Exception ex) {
					logger.error(ex.getStackTrace());
				}
			}
		});

		newTab.hlink2.setOnMouseClicked(new EventHandler<javafx.event.Event>() 
		{
			@Override
			public void handle(javafx.event.Event e) 
			{
				// 删除标签页
				try 
				{
					removeTab();
				} 
				catch (Exception ex) 
				{
					logger.error(ex.getStackTrace());
				}
			}
		});

		// 选择标签页时触发
		newTab.tab.setOnSelectionChanged(new EventHandler<Event>() 
		{
			@Override
			public void handle(Event t) 
			{
				if (newTab.tab.isSelected()) 
				{
					// 设定当前激活的标签
					workspaceView.workspaceModel.curTab = newTab;

					newTab.tab.setGraphic(newTab.hlink1);
				} 
				else 
				{
					newTab.tab.setGraphic(newTab.hlink2);
				}
			}
		});

		BDCodeTabModel tab = (BDCodeTabModel)newTab;
		
		tab.tab.getTabPane().setOnDragOver(new EventHandler<DragEvent>() 
		{
			@Override
			public void handle(DragEvent event) 
			{
				//if (event.getGestureSource() != m_imageView) 
				{
					event.acceptTransferModes(TransferMode.ANY);
				}
			}
		});
		
		tab.tab.getTabPane().setOnDragDropped(new EventHandler<DragEvent>() 
		{
			@Override
			public void handle(DragEvent event) 
			{
				Dragboard dragboard = event.getDragboard();
				
				List<File> files = dragboard.getFiles();
				
				for(int i = 0; i < files.size(); i++)
				//if(files.size() > 0)
				{
					File file = files.get(i);

					// 重新打开
					BDCodeModel code = new BDCodeModel();

					try 
					{
						// 写入文件路径
						code.setName(file.getName());
						code.setPath(file.getPath());
						
						code.setCodeText(BDCodeReader.readFileByLines(file.getPath()));
						
						//code.setCodeText(code.getCodeText().replaceAll("\"","\\\\\""));

						// 添加新标签页
						workspaceCtrl.addTab(code);
					} 
					catch (Exception ex) 
					{
						logger.error(ex.getStackTrace());
					}
				}
			}
		});
	}

	private BDDialogWindow dialogWindow;

	private void removeTab() 
	{
		// 判断文件是否已经保存
		if (workspaceView.workspaceModel.curTab.code.isSaved) 
		{
			// 文件已保存之前关闭标签页
			workspaceView.workspaceModel.tabList.remove(tab);

			workspaceView.getTabs().remove(tab.tab);

			// 是否最后一个标签
			if (workspaceView.workspaceModel.tabList.isEmpty()) 
			{
				// 关闭窗口
				System.exit(0);
			}
		} 
		else 
		{
			dialogWindow = new BDDialogWindow("  保存", "     是否保存对" + tab.tab.getText() + "文件的修改？");

			// 显示提示窗口
			dialogWindow.show();
		}

		// 点击确定按钮
		dialogWindow.okBtn.setOnMouseClicked(new EventHandler<Event>() 
		{
			@Override
			public void handle(Event t) 
			{
				// 关闭提示窗口
				dialogWindow.close();

				// 保存文件
				if (workspaceView.workspaceModel.curTab.code.path == "") 
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

				// 删除标签页
				workspaceView.workspaceModel.tabList.remove(tab);

				workspaceView.getTabs().remove(tab.tab);

				// 是否最后一个标签
				if (workspaceView.workspaceModel.tabList.isEmpty()) 
				{
					// 关闭窗口
					System.exit(0);
				}
			}
		});

		// 点击放弃按钮
		dialogWindow.giveupBtn.setOnMouseClicked(new EventHandler<Event>() 
		{
			@Override
			public void handle(Event t) 
			{
				// 关闭提示窗口
				dialogWindow.close();

				// 删除标签页
				workspaceView.workspaceModel.tabList.remove(tab);

				workspaceView.getTabs().remove(tab.tab);

				// 是否最后一个标签
				if (workspaceView.workspaceModel.tabList.isEmpty()) 
				{
					// 关闭窗口
					System.exit(0);
				}
			}
		});

		// 点击取消按钮
		dialogWindow.cancleBtn.setOnMouseClicked(new EventHandler<Event>() 
		{
			@Override
			public void handle(Event t) 
			{
				// 关闭提示窗口
				dialogWindow.close();
			}
		});
	}

	// 保存文件
	private void saveFile() 
	{
		try 
		{
			String code = workspaceView.workspaceModel.curTab.editorCtrl.getCode();

			// 写入文件
			BDCodeWriter.fileWriter(workspaceView.workspaceModel.curTab.code.path, code);

			workspaceView.workspaceModel.curTab.code.setCodeText(code);

			// 更改保存状态
			workspaceView.workspaceModel.curTab.code.isSaved = true;

		} 
		catch (IOException ex) 
		{
			logger.error("", ex);
		}
	}

	// 另存为文件
	private void saveAsFile() 
	{
		File file;

		FileChooser fileChooser = new FileChooser();

		// 设置文件类型过滤
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

		// 显示文件选择器
		file = fileChooser.showSaveDialog(null);

		try 
		{
			// 写入文件
			BDCodeWriter.fileWriter(file.getPath(), workspaceView.workspaceModel.curTab.editorCtrl.getCode());

			// 更新文件路径
			workspaceView.workspaceModel.curTab.code.path = file.getPath();

			// 更新标签名
			workspaceView.workspaceModel.curTab.tab.setText(file.getName());

			// 更改保存状态
			workspaceView.workspaceModel.curTab.code.isSaved = true;
		} 
		catch (IOException ex) 
		{
			logger.error("", ex);
		}
	}
}
