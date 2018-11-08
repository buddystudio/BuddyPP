/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import util.base.Preferences;
import util.debug.BDMessageConsumer;
import util.debug.BDSerial;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import model.BDLang;
import model.BDParameters;
import view.BDComWindow;

/**
 *
 * @author gsh
 */
public class BDComWindowCtrl implements BDMessageConsumer 
{
	private BDSerial serial;
	private String serialPort;
	private String curComRate;
	private BDComWindow bdComWindow;
	
	//private final static Logger logger = LogManager.getLogger();

	public BDComWindowCtrl(BDComWindow comWindow) 
	{
		bdComWindow = comWindow;
		
		// 更新串口信息
		reflashPort();
		
		// 设定波特率
		curComRate = comWindow.rateComoBox.getValue().toString();

		// 设定串口号
		serialPort = comWindow.portComoBox.getValue().toString();
		
		// 发送按钮默认不可用
		bdComWindow.sendMsgBtn.setDisable(true);

		bdComWindow.setOnCloseRequest((WindowEvent event) -> 
		{
			if (serial != null) 
			{
				serial.dispose();
			}
			
			bdComWindow.ctrlBtn.setText(BDLang.rb.getString("开始"));
			bdComWindow.recMsgtxt.setText("");
			bdComWindow.close();			
		});

		// 发送信息
		bdComWindow.sendMsgBtn.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				// 发送信息
				String s = comWindow.sendMsgTxt.getText();
				
				message(String.format("Send:%s\n", comWindow.sendMsgTxt.getText()));
				s = s + "\0";
				send(s);
			}
		});
		
		// 刷新
		bdComWindow.updateBtn.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				// 更新串口信息
				reflashPort();
			}
		});
		
		// 清屏
		bdComWindow.clearBtn.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				// 清空屏幕信息
				bdComWindow.recMsgtxt.clear();
			}
		});
		
		// 开始接收信息（开启串口）
		bdComWindow.ctrlBtn.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				if(bdComWindow.ctrlBtn.getText().equals(BDLang.rb.getString("开始")))
				{
					// 设定波特率
					curComRate = comWindow.rateComoBox.getValue().toString();

					// 设定串口号
					serialPort = comWindow.portComoBox.getValue().toString();
					
					if(serialPort == null || serialPort.equals(BDLang.rb.getString("未连接")))
					{
						return;
					}
					
					bdComWindow.ctrlBtn.setText(BDLang.rb.getString("暂停"));
					
					stopSerialPort();
					openSerialPort();
					
					bdComWindow.portComoBox.setDisable(true);
					bdComWindow.rateComoBox.setDisable(true);
					bdComWindow.updateBtn.setDisable(true);
					bdComWindow.sendMsgBtn.setDisable(false);
				}
				else
				{
					bdComWindow.ctrlBtn.setText(BDLang.rb.getString("开始"));
					
					stopSerialPort();
					
					bdComWindow.portComoBox.setDisable(false);
					bdComWindow.rateComoBox.setDisable(false);
					bdComWindow.updateBtn.setDisable(false);
					bdComWindow.sendMsgBtn.setDisable(true);
				}
			}
		});

		// 选择波特率
		bdComWindow.rateComoBox.valueProperty().addListener(new ChangeListener<String>() 
		{
			@Override
			public void changed(ObservableValue<? extends String> ov, String t, String t1) 
			{	
				BDParameters.curComRate = t1;
			}
		});

		// 是否显示显示时间
		bdComWindow.timeChkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
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
	}

	// 开始（打开端口通讯）
	private void openSerialPort() 
	{
		if (serial != null) 
		{
			return;
		}
		try 
		{
			//serial = new BDSerial(serialPort, Integer.valueOf(BDParameters.curComRate));
			serial = new BDSerial(serialPort, Integer.valueOf(curComRate));
		} 
		catch (Exception e) 
		{
			//logger.error("", e);
		}
		
		// 设置监听，收到信息后由message方法处理
		serial.addListener(this);
	}
	
	// 暂停(并且关闭串口通讯)
	private void stopSerialPort() 
	{
		if (serial != null) 
		{
			serial.dispose();
			serial = null;
		}
	}

	// 发送信息
	private void send(String s) 
	{
		if (serial != null) 
		{
			serial.write(s);
			
			// 清空发送文本框的信息
			bdComWindow.sendMsgTxt.clear();
		}
	}

	@Override
	public void message(String s) 
	{
		Platform.runLater(new Runnable() 
		{
			@Override
			public void run() 
			{
				String time = "2018-10-28 12:52:33  >";
				
				if(bdComWindow.timeChkBox.isSelected())
				{
					bdComWindow.recMsgtxt.appendText(time + s);
				}
				else
				{
					bdComWindow.recMsgtxt.appendText(s);
				}
			}
		});
	}
	
	public void reflashPort()
	{
		// 更新串口信息
		bdComWindow.portComoBox.getItems().clear();
					
		List<String> ports = BDSerial.list();
					
		if (ports.size() > 0) 
		{
			bdComWindow.portComoBox.getItems().addAll(ports);
			bdComWindow.portComoBox.getSelectionModel().select(0);
		}
		else
		{
			bdComWindow.portComoBox.setValue(BDLang.rb.getString("未连接"));
		}
	}
}
