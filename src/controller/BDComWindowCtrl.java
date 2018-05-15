/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import util.debug.BDMessageConsumer;
import util.debug.BDSerial;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

import model.BDParameters;
import view.BDComWindow;

/**
 *
 * @author gsh
 */
public class BDComWindowCtrl implements BDMessageConsumer {
	BDSerial serial;
	String serialPort;
	BDComWindow bdComWindow;
	private final static Logger logger = LogManager.getLogger();

	public BDComWindowCtrl(BDComWindow comWindow) 
	{
		// 设定波特率
		BDParameters.curComRate = comWindow.rateComoBox.getValue().toString();

		serialPort = BDParameters.connectCom;
		bdComWindow = comWindow;

		if (serialPort == null || serialPort.isEmpty())
			return;

		bdComWindow.setOnCloseRequest((WindowEvent event) -> {
			serial.dispose();
			bdComWindow.recMsgtxt.setText("");
			bdComWindow.close();			
		});

		// 发送信息
		bdComWindow.sendMsgBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// 发送信息
				String s = comWindow.sendMsgTxt.getText();
				message(String.format("Send:%s\n", comWindow.sendMsgTxt.getText()));
				s = s + "\0";
				send(s);

			}
		});

		// 选择波特率
		bdComWindow.rateComoBox.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> ov, String t, String t1) {
				closeSerialPort();
				BDParameters.curComRate = t1;
				openSerialPort();
			}
		});

		// 是否自动滚屏
		bdComWindow.isAutoScroll.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
				// System.out.println(comWindow.isAutoScroll.isSelected());
			}
		});

		// 是否自动换行
		bdComWindow.lineChkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
				// System.out.println(comWindow.lineChkBox.isSelected());
			}
		});

		closeSerialPort();
		openSerialPort();
	}

	// 打开端口
	private void openSerialPort() {
		if (serial != null) {
			return;
		}
		try {
			serial = new BDSerial(serialPort, Integer.valueOf(BDParameters.curComRate));
		} catch (Exception e) {
			logger.error("", e);
		}
		// 设置监听，收到信息后由message方法处理
		serial.addListener(this);
	}

	// 关闭端口
	private void closeSerialPort() {
		if (serial != null) {
			bdComWindow.recMsgtxt.setText("");
			serial.dispose();
			serial = null;
		}
	}

	// 发送信息
	private void send(String s) {
		if (serial != null) {
			serial.write(s);
		}
	}

	@Override
	public void message(String s) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				bdComWindow.recMsgtxt.appendText(s);
			}
		});
	}
}
