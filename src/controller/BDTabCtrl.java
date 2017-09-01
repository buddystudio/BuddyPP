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

import com.mongcj.util.io.BDCodeReader;
import com.mongcj.util.io.BDCodeWriter;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import model.BDCodeModel;
import model.BDCodeTabModel;
import view.BDDialogWindow;
import view.BDWorkspaceView;

/**
 *
 * @author gsh
 */
public class BDTabCtrl {
	public BDWorkspaceView workspaceView;
	private BDCodeTabModel tab;
	private static final Logger logger = LogManager.getLogger(BDTabCtrl.class);

	public BDTabCtrl(BDCodeTabModel tab, BDWorkspaceCtrl workspaceCtrl) {
		this.tab = tab;

		if (!tab.tab.isSelected()) {
			tab.tab.setGraphic(tab.hlink2);
		}

		tab.hlink1.setOnMouseClicked(new EventHandler<javafx.event.Event>() {
			@Override
			public void handle(javafx.event.Event e) {

				// 删除标签页
				try {
					removeTab();
				} catch (Exception ex) {
					logger.error(ex.getStackTrace());
				}

				// System.out.println("close click 1");
			}
		});

		tab.hlink2.setOnMouseClicked(new EventHandler<javafx.event.Event>() {
			@Override
			public void handle(javafx.event.Event e) {
				// 删除标签页
				try {
					removeTab();
				} catch (Exception ex) {
					logger.error(ex.getStackTrace());
				}
				// System.out.println("close click 2");
			}
		});

		// 选择标签页时触发
		tab.tab.setOnSelectionChanged(new EventHandler<Event>() {
			@Override
			public void handle(Event t) {
				// tab.textArea.repaint();
				// tab.spp.repaint();

				if (tab.tab.isSelected()) {
					// System.out.println(tab.tab.getText() + " select");

					// 设定当前激活的标签
					workspaceView.workspaceModel.curTab = tab;

					// System.out.println("");

					tab.tab.setGraphic(tab.hlink1);

					if (!tab.textArea.hasFocus()) {
						// tab.textArea.requestFocusInWindow();
					}

					// 延时获得焦点
					final Timeline animation = new Timeline(
							new KeyFrame(Duration.millis(25), new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent actionEvent) {
									Platform.runLater(new Runnable() {
										@Override
										public void run() {
											tab.tab.getContent().requestFocus();
										}
									});
								}
							}));
					animation.setCycleCount(1);
					animation.play();

					/*
					 * try { Robot robby = new Robot();
					 * 
					 * //robby.delay(1000); robby.keyPress(9);
					 * robby.keyRelease(9);
					 * 
					 * } catch (AWTException ex) {
					 * Logger.getLogger(BDTabCtrl.class.getName()).log(Level.
					 * SEVERE, null, ex); }
					 */

					// System.out.println(workspaceView.workspaceModel.curTab.tab.getText()
					// + " repaint!!!!!");
					// workspaceView.workspaceModel.curTab.spp.updateUI();

					/*
					 * PointerInfo a = MouseInfo.getPointerInfo(); Point b =
					 * a.getLocation();
					 * 
					 * Robot robby;
					 * 
					 * try { robby = new Robot();
					 * 
					 * robby.delay(150);
					 * 
					 * robby.mouseMove(b.x, b.y + 20);
					 * 
					 * robby.mousePress(InputEvent.BUTTON1_DOWN_MASK);
					 * robby.mouseRelease(InputEvent.BUTTON1_MASK);
					 * 
					 * robby.mouseMove(b.x, b.y);
					 * 
					 * } catch (AWTException ex) {
					 * Logger.getLogger(BDTabCtrl.class.getName()).log(Level.
					 * SEVERE, null, ex); }
					 */
				} else {
					// System.out.println(tab.tab.getText() + " out");

					tab.tab.setGraphic(tab.hlink2);

					// tab.spp.repaint();
				}
			}
		});

		// 文件拖入
		tab.sn.setOnDragOver(new EventHandler<DragEvent>() {
			// node添加拖入文件事件
			public void handle(DragEvent event) {
				Dragboard dragboard = event.getDragboard();

				if (dragboard.hasFiles()) {
					File file = dragboard.getFiles().get(0);

					if (file.getAbsolutePath().endsWith(".ino") || file.getAbsolutePath().endsWith(".txt")
							|| file.getAbsolutePath().endsWith(".cpp") || file.getAbsolutePath().endsWith(".c")
							|| file.getAbsolutePath().endsWith(".h")) {
						// 用来过滤拖入类型
						event.acceptTransferModes(TransferMode.COPY);// 接受拖入文件
					}
				}

			}
		});

		tab.sn.setOnDragDropped(new EventHandler<DragEvent>() {
			// 拖入后松开鼠标触发的事件
			public void handle(DragEvent event) {
				// get drag enter file
				Dragboard dragboard = event.getDragboard();

				if (event.isAccepted()) {
					File file = dragboard.getFiles().get(0); // 获取拖入的文件

					// 重新打开
					BDCodeModel code = new BDCodeModel();

					code.setName(file.getName());

					try {
						// code.codeTex =
						// BDCodeReader.readFileByLines(file.getPath());
						code.setCodeText(BDCodeReader.readFileByLines2(file.getPath()));

						// 写入文件路径
						code.path = file.getPath();
					} catch (FileNotFoundException ex) {
						logger.error(ex.getStackTrace());
						// Logger.getLogger(BDMenuCtrl.class.getName()).log(Level.SEVERE,
						// null, ex);
					} catch (IOException ex) {
						logger.error(ex.getStackTrace());
						// Logger.getLogger(BDMenuCtrl.class.getName()).log(Level.SEVERE,
						// null, ex);
					}

					try {
						// 添加新标签页
						workspaceCtrl.addTab(code);
					} catch (AWTException ex) {
						logger.error(ex.getStackTrace());
						// Logger.getLogger(BDTabCtrl.class.getName()).log(Level.SEVERE,
						// null, ex);
					}

					/*
					 * try {
					 * 
					 * // 直接打开
					 * 
					 * String tmpCode =
					 * BDCodeReader.readFileByLines2(file.getPath());
					 * 
					 * tab.textArea.setText(tmpCode);
					 * 
					 * } catch (FileNotFoundException ex) {
					 * Logger.getLogger(BDTabCtrl.class.getName()).log(Level.
					 * SEVERE, null, ex); } catch (IOException ex) {
					 * Logger.getLogger(BDTabCtrl.class.getName()).log(Level.
					 * SEVERE, null, ex); }
					 */
				}
			}
		});

	}

	// private BDDialogWindow dialogWindow = new BDDialogWindow("保存", " 是否保存对" +
	// tab.tab.getText() + "的修改？");
	private BDDialogWindow dialogWindow;

	private void removeTab() {
		// 判断文件是否已经保存
		if (workspaceView.workspaceModel.curTab.code.isSaved) {
			// 文件已保存之前关闭标签页
			workspaceView.workspaceModel.tabList.remove(tab);

			workspaceView.getTabs().remove(tab.tab);

			// 是否最后一个标签
			if (workspaceView.workspaceModel.tabList.isEmpty()) {
				// 关闭窗口
				System.exit(0);
			}
		} else {
			// dialogWindow = new BDDialogWindow("保存", " 是否保存对" +
			// tab.tab.getText() + "文件的修改？");
			dialogWindow = new BDDialogWindow("  保存", "     是否保存对" + tab.tab.getText() + "文件的修改？");

			// 显示提示窗口
			dialogWindow.show();
		}

		// 点击确定按钮
		dialogWindow.okBtn.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event t) {
				// 关闭提示窗口
				dialogWindow.close();

				// 保存文件
				if (workspaceView.workspaceModel.curTab.code.path == "") {
					// 另存为文件
					saveAsFile();
				} else {
					try {
						// 保存文件
						saveFile();
					} catch (Exception ex) {
						// 另存为文件
						saveAsFile();
					}
				}

				// 删除标签页
				workspaceView.workspaceModel.tabList.remove(tab);

				workspaceView.getTabs().remove(tab.tab);

				// 是否最后一个标签
				if (workspaceView.workspaceModel.tabList.isEmpty()) {
					// 关闭窗口
					System.exit(0);
				}
			}
		});

		// 点击放弃按钮
		dialogWindow.giveupBtn.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event t) {
				// 关闭提示窗口
				dialogWindow.close();

				// 删除标签页
				workspaceView.workspaceModel.tabList.remove(tab);

				workspaceView.getTabs().remove(tab.tab);

				// 是否最后一个标签
				if (workspaceView.workspaceModel.tabList.isEmpty()) {
					// 关闭窗口
					System.exit(0);
				}
			}
		});

		// 点击取消按钮
		dialogWindow.cancleBtn.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event t) {
				// 关闭提示窗口
				dialogWindow.close();
			}
		});
	}

	// 保存文件
	private void saveFile() {
		try {
			String code = workspaceView.workspaceModel.curTab.textArea.getText();

			// 写入文件
			BDCodeWriter.fileWriter(workspaceView.workspaceModel.curTab.code.path, code);

			workspaceView.workspaceModel.curTab.code.setCodeText(code);

			// 更改保存状态
			workspaceView.workspaceModel.curTab.code.isSaved = true;

		} catch (IOException ex) {
			logger.error("", ex);
			// Logger.getLogger(BDMenuCtrl.class.getName()).log(Level.SEVERE,
			// null, ex);
		}
	}

	// 另存为文件
	private void saveAsFile() {
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

		try {
			// 写入文件
			BDCodeWriter.fileWriter(file.getPath(), workspaceView.workspaceModel.curTab.textArea.getText());

			// 更新文件路径
			workspaceView.workspaceModel.curTab.code.path = file.getPath();

			// 更新标签名
			workspaceView.workspaceModel.curTab.tab.setText(file.getName());

			// 更改保存状态
			workspaceView.workspaceModel.curTab.code.isSaved = true;
		} catch (IOException ex) {
			logger.error("", ex);
			// Logger.getLogger(BDMenuCtrl.class.getName()).log(Level.SEVERE,
			// null, ex);
		}
	}
}
