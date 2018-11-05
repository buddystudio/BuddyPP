package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import model.BDLang;
import model.BDMessage;
import model.BDParameters;
import model.BDSerialManager2;
import view.BDCompileAndUploadWindow;

public class BDCompileAndUploadCtrl
{
	private String bd_root_path 				= "C:\\Software\\Develop\\Arduino\\arduino-1.8.5-test\\";
	private String bd_built_path 				= "D:\\Temp\\arduino_build";
	private String bd_code_path 				= "D:\\Hello\\Hello.ino";

	/*private String bd_hardware_path 			= bd_root_path + "hardware";
	private String bd_tools01_path 				= bd_root_path + "tools-builder";
	private String bd_tools02_path 				= bd_root_path + "hardware\\tools\\avr";
	private String bd_built_in_libraries_path 	= bd_root_path + "libraries";*/
	private String bd_hardware_path 			= "";
	private String bd_tools01_path 				= "";
	private String bd_tools02_path 				= "";
	private String bd_built_in_libraries_path 	= "";
	private String bd_libraries_path 			= "C:\\Users\\gsh\\Documents\\Arduino\\libraries";
	//private String bd_fqbn 						= "arduino:avr:uno";
	private String bd_fqbn 						= "arduino:avr:uno";
	private String bd_ide_version 				= "10805";
	
	// Get the filename from path.
	private File tempFile = new File(bd_code_path.trim());

	private String bd_hex_path = bd_built_path + "\\" + tempFile.getName() + ".hex";
	
	/*private String bd_avrdude_path 	= bd_root_path + "hardware\\tools\\avr\\bin\\";
	private String bd_avrdude_conf 	= bd_root_path + "hardware\\tools\\avr\\etc\\avrdude.conf";*/
	private String bd_avrdude_path 	= "";
	private String bd_avrdude_conf 	= "";
	private String bd_cpu 			= "atmega328p";
	private String bd_platform 		= "arduino";
	//private String bd_com 			= "COM3";
	private String bd_com 			= "";
	private String bd_rate 			= "115200";
	
	private int timeOut				= 30000;		// 默认操作30秒超时
	private String title			= "  " + BDLang.rb.getString("编译与上传");	// 窗口标题
	private double upLoadProgress 	= 0;			// 上传进度
	
	private enum WorkMode 
	{
		FROM_CODE, FROM_INO, FROM_HEX
	}
	
	private WorkMode workMode = WorkMode.FROM_CODE;
	
	private enum CompileMode
	{
		COMPILE, UPLOAD, COMPILE_AND_UPLOAD;
	}
	
	private CompileMode compileMode = CompileMode.COMPILE_AND_UPLOAD;
	
	@SuppressWarnings("unused")
	private BDCompileAndUploadWindow compileAndUploadWindow = null;
	
	public void openFileFromCode(String builtPath, String codePath)
	{
		workMode = WorkMode.FROM_CODE;
		
		this.bd_built_path 				= builtPath;
		this.bd_code_path 				= codePath;
		this.bd_hex_path				= builtPath + "\\Code.ino.hex";
		
		// 导入源码文件，全部功能可用。
		compileAndUploadWindow.getCompileBtn().setDisable(false);
		compileAndUploadWindow.getUploadBtn().setDisable(false);
		compileAndUploadWindow.getCompileAndUploadBtn().setDisable(false);
		compileAndUploadWindow.getStopBtn().setDisable(false);
		
		//System.out.println("this.bd_built_path: " + this.bd_built_path);
		//System.out.println("this.bd_code_path: " + this.bd_code_path);
		
		if(compileAndUploadWindow.getSerialListCombox().getValue().equals(BDLang.rb.getString("未连接")))
		{
			// 如果串口未连接屏蔽上传功能（编译且上传）
			compileAndUploadWindow.getUploadBtn().setDisable(true);
			compileAndUploadWindow.getCompileAndUploadBtn().setDisable(true);
		}
		else
		{
			// 恢复上传功能（编译且上传）
			compileAndUploadWindow.getUploadBtn().setDisable(false);
			compileAndUploadWindow.getCompileAndUploadBtn().setDisable(false);
		}
	}

	public BDCompileAndUploadCtrl(BDCompileAndUploadWindow compileAndUploadWindow)
	{
		this.compileAndUploadWindow = compileAndUploadWindow;

		// 在选定文件之前功能按钮不能使用
		this.compileAndUploadWindow.getCompileBtn().setDisable(true);
		this.compileAndUploadWindow.getUploadBtn().setDisable(true);
		this.compileAndUploadWindow.getCompileAndUploadBtn().setDisable(true);
		this.compileAndUploadWindow.getStopBtn().setDisable(true);

		String user_root_path = System.getProperty("user.dir") + "\\";
		
		bd_root_path = user_root_path + "\\arduino-builder-windows\\";
		
		bd_hardware_path 			= bd_root_path + "hardware";
		bd_tools01_path 			= bd_root_path + "tools-builder";
		bd_tools02_path 			= bd_root_path + "hardware\\tools\\avr";
		bd_built_in_libraries_path 	= user_root_path + "libraries";
		
		bd_avrdude_path 			= bd_root_path + "hardware\\tools\\avr\\bin\\";
		bd_avrdude_conf 			= bd_root_path + "hardware\\tools\\avr\\etc\\avrdude.conf";
		
		//System.out.println("你当前的工作目录为 :" + bd_root_path);
		
		try
        {
			// update serial port info.
			compileAndUploadWindow.getSerialListCombox().setItems(new BDSerialManager2().getPortList());
            compileAndUploadWindow.getSerialListCombox().getSelectionModel().select(0);

            // open *.ino file.
	        this.compileAndUploadWindow.getInoFileMenuItem().setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override
				public void handle(ActionEvent event) 
				{
					System.out.println("Import *.ino file.");
					
					File file = null;
					
					FileChooser fileChooser = new FileChooser();
					
					// Set file Filter.
					fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arduino  (*.ino)", "*.ino"));
					
					// Show open file dialog
					file = fileChooser.showOpenDialog(null);
					
					if(file == null)
					{
						return;
					}
					
					System.out.println("Ino File ： " + file.getPath());
					
					// get the *.ino file's path.
					bd_code_path = file.getPath();
					
					// 打开*.ino源码文件，全部功能可用。
					compileAndUploadWindow.getCompileBtn().setDisable(false);
					compileAndUploadWindow.getUploadBtn().setDisable(false);
					compileAndUploadWindow.getCompileAndUploadBtn().setDisable(false);
					compileAndUploadWindow.getStopBtn().setDisable(false);
					
					workMode = WorkMode.FROM_INO;
				}
			});
	        
	        // open *.hex file.
	        this.compileAndUploadWindow.getHexFileMenuItem().setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override
				public void handle(ActionEvent event) 
				{
					System.out.println("Import *.hex file.");
					
					File file = null;
					
					FileChooser fileChooser = new FileChooser();
					
					// Set file Filter.
					fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Hex  (*.hex)", "*.hex"));
					
					// Show open file dialog
					file = fileChooser.showOpenDialog(null);
					
					if(file == null)
					{
						return;
					}
					
					System.out.println("Hex File ： " + file.getPath());
					
					// get the *.ino file's path.
					//bd_code_path = file.getPath();
					bd_hex_path = file.getPath();
					
					// 打开*.hex文件，仅能使用上传功能。
					compileAndUploadWindow.getCompileBtn().setDisable(true);
					compileAndUploadWindow.getUploadBtn().setDisable(false);
					compileAndUploadWindow.getCompileAndUploadBtn().setDisable(true);
					compileAndUploadWindow.getStopBtn().setDisable(false);
					
					workMode = WorkMode.FROM_HEX;
				}
			});
	        
	        // display parameters.
	        this.compileAndUploadWindow.getDisplayMenuItem().setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override
				public void handle(ActionEvent event) 
				{
					compileAndUploadWindow.getAcvCtrl().clear();
					compileAndUploadWindow.getAcvCtrl().setLineCount(1);
					
					compileAndUploadWindow.getAcvView().setColorMsgFont("#000000");
            		compileAndUploadWindow.getAcvCtrl().updateMessageByLine("");
            		compileAndUploadWindow.getAcvCtrl().updateMessageByLine(">>>>>>> ==========================================================================");
                	compileAndUploadWindow.getAcvCtrl().updateMessageByLine("<b>bd_root_path: </b>" + bd_root_path);
                	compileAndUploadWindow.getAcvCtrl().updateMessageByLine("<b>bd_built_path: </b" + bd_built_path);
                	compileAndUploadWindow.getAcvCtrl().updateMessageByLine("<b>bd_code_path: </b" + bd_code_path);
                	compileAndUploadWindow.getAcvCtrl().updateMessageByLine("<b>bd_tools01_path: </b>" + bd_tools01_path);
                	compileAndUploadWindow.getAcvCtrl().updateMessageByLine("<b>bd_tools02_path: </b>" + bd_tools02_path);
                	compileAndUploadWindow.getAcvCtrl().updateMessageByLine("<b>bd_built_in_libraries_path: </b>" + bd_built_in_libraries_path);
                	compileAndUploadWindow.getAcvCtrl().updateMessageByLine("<b>bd_hardware_path: </b>" + bd_hardware_path);
                	compileAndUploadWindow.getAcvCtrl().updateMessageByLine("<b>bd_built_in_libraries_path: </b>" + bd_built_in_libraries_path);
                	compileAndUploadWindow.getAcvCtrl().updateMessageByLine("<b>bd_libraries_path: </b>" + bd_libraries_path);
                	compileAndUploadWindow.getAcvCtrl().updateMessageByLine("<b>bd_fqbn: </b>" + bd_fqbn);
                	compileAndUploadWindow.getAcvCtrl().updateMessageByLine("<b>bd_ide_version: </b>" + bd_ide_version);

                	compileAndUploadWindow.getAcvCtrl().updateMessageByLine("<b>bd_hex_path: </b>" + bd_hex_path);
                	compileAndUploadWindow.getAcvCtrl().updateMessageByLine("<b>bd_avrdude_conf: </b>" + bd_avrdude_conf);
                	compileAndUploadWindow.getAcvCtrl().updateMessageByLine("<b>bd_avrdude_path: </b>" + bd_avrdude_path);
                	compileAndUploadWindow.getAcvCtrl().updateMessageByLine("<b>bd_avrdude_conf : </b>" + bd_avrdude_conf );
                	compileAndUploadWindow.getAcvCtrl().updateMessageByLine("<b>bd_cpu: </b>" + bd_cpu);
                	compileAndUploadWindow.getAcvCtrl().updateMessageByLine("<b>bd_platform: </b>" + bd_platform);
                	compileAndUploadWindow.getAcvCtrl().updateMessageByLine("<b>bd_com: </b>" + bd_com);
                	compileAndUploadWindow.getAcvCtrl().updateMessageByLine("<b>bd_rate: </b>" + bd_rate);
                	
                	compileAndUploadWindow.getAcvCtrl().updateMessageByLine(">>>>>>> ==========================================================================");
                	compileAndUploadWindow.getAcvCtrl().updateMessageByLine("");
				}
			});

	        // update.
	        this.compileAndUploadWindow.getUpdateBtn().setOnAction(new EventHandler<ActionEvent>() 
			{
		    	@Override
		        public void handle(ActionEvent event) 
		        {
		        	/*compileAndUploadWindow.getSerialListCombox().setItems(new BDSerialManager2().getPortList());
		            compileAndUploadWindow.getSerialListCombox().getSelectionModel().select(0);*/
		    		
		    		// 更新串口列表
		    		updateSerialPorts();
		        }
		    });
	        
			// compile.
	        this.compileAndUploadWindow.getCompileBtn().setOnAction(new EventHandler<ActionEvent>() 
			{
	            @Override
	            public void handle(ActionEvent event) 
	            {
	            	// Stop all threads.
					stopAllThreads();
					
	            	compileAndUploadWindow.getAcvCtrl().setLineCount(1);
	            	compileAndUploadWindow.getAcvCtrl().clear();
	            	
	            	compileMode = CompileMode.COMPILE;
	            	
	            	compileAndUpload();
	            }
	        });
			
			// upload.
	        this.compileAndUploadWindow.getUploadBtn().setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override
				public void handle(ActionEvent event) 
				{
					// Stop all threads.
					stopAllThreads();
					
					compileAndUploadWindow.getSerialListCombox().setItems(new BDSerialManager2().getPortList());
    	            compileAndUploadWindow.getSerialListCombox().getSelectionModel().select(0);
    	            
    	            bd_com = compileAndUploadWindow.getSerialListCombox().getSelectionModel().getSelectedItem();
    	            
					compileAndUploadWindow.getAcvCtrl().setLineCount(1);
	            	compileAndUploadWindow.getAcvCtrl().clear();
	            	
					compileMode = CompileMode.UPLOAD;
					
					compileAndUpload();
				}
			});
			
			// compile and upload.
	        this.compileAndUploadWindow.getCompileAndUploadBtn().setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override
				public void handle(ActionEvent event) 
				{
					// Stop all threads.
					stopAllThreads();
					
					compileAndUploadWindow.getSerialListCombox().setItems(new BDSerialManager2().getPortList());
    	            compileAndUploadWindow.getSerialListCombox().getSelectionModel().select(0);
    	            
    	            bd_com = compileAndUploadWindow.getSerialListCombox().getSelectionModel().getSelectedItem();
    	            
					compileAndUploadWindow.getAcvCtrl().setLineCount(1);
					compileAndUploadWindow.getAcvCtrl().clear();
					
					compileMode = CompileMode.COMPILE_AND_UPLOAD;
					
					compileAndUpload();
				}
			});
			
			// stop.
	        this.compileAndUploadWindow.getStopBtn().setOnAction(new EventHandler<ActionEvent>() 
			{
	            @SuppressWarnings("deprecation")
				@Override
	            public void handle(ActionEvent event) 
	            {
	            	// Stop all threads.
					stopAllThreads();
					
					// 恢复功能按钮
            		setFuncEnable(true);
	            }
	        });
			
			// clear.
	        this.compileAndUploadWindow.getClearBtn().setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override
				public void handle(ActionEvent event) 
				{
					compileAndUploadWindow.getAcvCtrl().setLineCount(1);
					compileAndUploadWindow.getAcvCtrl().clear();
					compileAndUploadWindow.getProgressBar().setProgress(0);
					
					compileAndUploadWindow.setTitle(title);
				}
			});
			
			// open
	        this.compileAndUploadWindow.getOpenBtn().setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override
				public void handle(ActionEvent event) 
				{		
				}
			});
        } 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		this.addMessageListener();
	}

	public void executeCommand(String command)
	{
		Task<String> task = new Task<String>() 
		{
            @Override
            protected String call() throws Exception 
            {
            	Runtime run = Runtime.getRuntime();
            	
            	String message = null;

                try 
                {
                    Process process = run.exec(command);
                    
                    InputStream in = process.getInputStream();
                    InputStreamReader reader = new InputStreamReader(in);
                    
                    BufferedReader br = new BufferedReader(reader);
                    StringBuffer sb = new StringBuffer();
                    
                    while((message = br.readLine()) != null) 
                    {
                        //sb.append(message + "\n");
                        System.out.println(message);
                        //html += "<p>" + sb.toString() + "</p>";
                        //htmlEditor.setHtmlText(html);
                        
                        //  设置延时
                        Thread.sleep(80);
                        
                        updateMessage(message);
                    }
                    //System.out.println(sb);
                } 
                catch (IOException e) 
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
                return message;
            }
		};
		
		compileThread = new Thread(task);
		
		compileThread.start();

		task.messageProperty().addListener(new ChangeListener<String>() 
		{
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                //System.out.println(newValue);

            	compileAndUploadWindow.getAcvView().setColorMsgFont("#0000FF");
            	compileAndUploadWindow.getAcvCtrl().updateMessageByLine(newValue);
                
                //webEngine.executeScript("window.scrollTo(0, document.body.scrollHeight);");
                //webEngine.executeScript();
            }
        });
	}
	
	public void executeUploadCommand(String command)
	{
		Task<String> task = new Task<String>() 
		{
            @Override
            protected String call() throws Exception 
            {
            	Runtime run = Runtime.getRuntime();
            	
            	String message2 = null;

                try
                {
                    Process process = run.exec(command);
                    
                    InputStream errorInStream = new BufferedInputStream(process.getErrorStream());
                    InputStreamReader errorReader = new InputStreamReader(errorInStream);
                    BufferedReader br2 = new BufferedReader(errorReader);
                    
                    while((message2 = br2.readLine()) != null) 
                    {
                    	System.out.println(message2);

                    	//  设置延时
                        Thread.sleep(80);
                        
                        updateMessage(message2);
                        //updateProgress(1, 0);
                    }
                    
                } 
                catch (IOException e) 
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
                return message2;
            }
		};
		
		uploadThread = new Thread(task);
		
		uploadThread.start();
		
		task.messageProperty().addListener(new ChangeListener<String>() 
		{
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
            	compileAndUploadWindow.getAcvView().setColorMsgFont("#E34C00");
            	compileAndUploadWindow.getAcvCtrl().updateMessageByLine(newValue);
            }
        });
	}
	
	public void executeCommandByLine(String command)
	{
		Process process;
		
        try 
        {
	        process = Runtime.getRuntime().exec(command);
	        
	        InputStreamReader r = new InputStreamReader(process.getInputStream());
	        LineNumberReader returnData = new LineNumberReader(r);
	
	        String returnMsg = "";
	        String line = "";
	        
	        while ((line = returnData.readLine()) != null) 
	        {
		        System.out.println(returnData.getLineNumber()+" "+line);
		        returnMsg += line;
	        }
        } 
        catch (IOException e) 
        {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        }
	}

	public void compile()
	{
		
	}
	
	public void upload()
	{	
		
	}
	
	private Thread dumpThread 		= null;
	private Thread compileThread 	= null;
	private Thread uploadThread 	= null;
	
	private Timer timer 			= new Timer();
	
	private BDMessage dumpMessage 		= new BDMessage();
	private BDMessage compileMessage 	= new BDMessage();
	private BDMessage uploadMessage 	= new BDMessage();

	public void compileAndUpload()
	{
		// 屏蔽功能按钮
		this.setFuncEnable(false);
		
		bd_com = this.compileAndUploadWindow.getSerialListCombox().getSelectionModel().getSelectedItem();
		
		String boardName = this.compileAndUploadWindow.getBoardListCombox().getSelectionModel().getSelectedItem();
		
		for(int i = 0; i < this.compileAndUploadWindow.getBoardManager().getBoards().size(); i++)
		{
			if(boardName.equals(this.compileAndUploadWindow.getBoardManager().getBoards().get(i).getName()))
			{
				bd_fqbn = this.compileAndUploadWindow.getBoardManager().getBoards().get(i).getFqbn();
				bd_cpu = this.compileAndUploadWindow.getBoardManager().getBoards().get(i).getMcu();
				bd_rate = this.compileAndUploadWindow.getBoardManager().getBoards().get(i).getSpeed();
				bd_platform = this.compileAndUploadWindow.getBoardManager().getBoards().get(i).getProtocol();
				
				break;
			}
		}

		String dumpCmd 		= bd_root_path + "arduino-builder -dump-prefs -logger=machine -hardware " + bd_hardware_path +" -tools " + bd_tools01_path + " -tools " + bd_tools02_path + " -built-in-libraries " + bd_built_in_libraries_path + " -libraries " + bd_libraries_path + " -fqbn=" + bd_fqbn + " -ide-version=" + bd_ide_version + " -build-path " + bd_built_path +" -warnings=none -prefs=build.warn_data_percentage=75 -prefs=runtime.tools.avrdude.path=C:\\Software\\Develop\\Arduino\\arduino-1.8.5-windows\\hardware\\tools\\avr -prefs=runtime.tools.avr-gcc.path=C:\\Software\\Develop\\Arduino\\arduino-1.8.5-windows\\hardware\\tools\\avr -prefs=runtime.tools.arduinoOTA.path=C:\\Software\\Develop\\Arduino\\arduino-1.8.5-windows\\hardware\\tools\\avr -verbose " + bd_code_path;
        String compileCmd 	= bd_root_path + "arduino-builder -compile -logger=machine -hardware " + bd_hardware_path + " -tools " + bd_tools01_path + " -tools " + bd_tools02_path + " -built-in-libraries " + bd_built_in_libraries_path + " -libraries " + bd_libraries_path + " -fqbn=" + bd_fqbn + " -ide-version=" + bd_ide_version + " -build-path " + bd_built_path + " -warnings=none -prefs=build.warn_data_percentage=75 -prefs=runtime.tools.avrdude.path=" + bd_tools02_path + " -prefs=runtime.tools.avr-gcc.path=" + bd_tools02_path + " -prefs=runtime.tools.arduinoOTA.path=" + bd_tools02_path + " -verbose " + bd_code_path;
        String uploadCmd 	= bd_avrdude_path + "avrdude -C" + bd_avrdude_conf + " -v -p" + bd_cpu + " -c" + bd_platform + " -P" + bd_com + " -b" + bd_rate + " -D -Uflash:w:" + bd_hex_path + ":i";
        
        System.out.println("compileCmd: " + compileCmd);
        System.out.println("uploadCmd: " + uploadCmd);
        
        File file = new File(bd_built_path);
        
        // Create temporary directory.
        file.mkdir();
        
        // Dump prefs.
        //executeCommand(cmdStr03);
        
        // Compile
        //executeCommand(cmdStr04);

        // Upload
        //executeUploadCommand(cmdStr05);
		
		Task<String> dumpTask = new Task<String>() 
		{
            @Override
            protected String call() throws Exception 
            {
            	Runtime run = Runtime.getRuntime();
            	
            	String message = null;

                try 
                {
                	dumpMessage.setMessage("msg_" + "");
                	dumpMessage.setMessage("msg_" + ">>>>>>> Buddy++：" + BDLang.rb.getString("初始化操作即将开始") + "...");
                	dumpMessage.setMessage("msg_" + ">>>>>>> ===================================================================");
                	dumpMessage.setMessage("msg_" + "");
            		
                	// display command.
                	dumpMessage.setMessage("cmd_" + dumpCmd);
                	
                    Process process = run.exec(dumpCmd);
                    
                    InputStream in = process.getInputStream();
                    InputStreamReader reader = new InputStreamReader(in);
                    
                    BufferedReader br = new BufferedReader(reader);
                    
                    while((message = br.readLine()) != null) 
                    {
                        System.out.println(message);
                        
                        if(message.indexOf("===info ||| Progress {0} ||| ") != -1)
                        {
                        	// Get the progress
                        	int s = message.indexOf("[") + 1;
                        	int e = message.indexOf("]");
                        	
                        	String value = message.substring(s, e);
                        	
                        	double progress = Double.parseDouble(value) / 100;
                        	
                        	compileAndUploadWindow.getProgressBar().setProgress(progress);
                        	
                        	Platform.runLater(new Runnable() 
                        	{
                        	    @Override
                        	    public void run() 
                        	    {
                        	    	compileAndUploadWindow.setTitle(title + "   " + BDLang.rb.getString("初始化") + "：" + value + "%");
                        	    }
                        	});
                        }
                        
                        dumpMessage.setMessage(message);

                        //  设置延时
                        //Thread.sleep(50);
                        
                        //updateMessage(message);
                    }
                } 
                catch (IOException e) 
                {
                    e.printStackTrace();
                    
                    dumpMessage.setMessage("ERROR");
                    //updateMessage("ERROR");
                }
                
                dumpMessage.setMessage("COMPLETE");
                //updateMessage("COMPLETE");
                
                return "SUCCESS";
            }
		};
		
		Task<String> compileTask = new Task<String>() 
		{
            @Override
            protected String call() throws Exception 
            {
            	Runtime run = Runtime.getRuntime();
            	
            	String message = null;
            	String isSuccess = "SUCCESS";

                try 
                {
                	//compileMessage.setMessage("inf_" + "");
                	compileMessage.setMessage("inf_" + ">>>>>>> Buddy++：" + BDLang.rb.getString("编译操作即将开始") + "...");
                	compileMessage.setMessage("inf_" + ">>>>>>> ===================================================================");
                	compileMessage.setMessage("inf_" + "");
                	
                	// display command.
                	compileMessage.setMessage("cmd_" + compileCmd);
                	
                    Process process = run.exec(compileCmd);
                    
                    InputStream in = process.getInputStream();
                    InputStreamReader reader = new InputStreamReader(in);
                    
                    BufferedReader br = new BufferedReader(reader);
                    
                    while((message = br.readLine()) != null) 
                    {
                        System.out.println(message);
                        
                        if(message.indexOf("===info ||| Progress {0} ||| ") != -1)
                        {
                        	// Get the progress
                        	int s = message.indexOf("[") + 1;
                        	int e = message.indexOf("]");
                        	
                        	String value = message.substring(s, e);
                        	
                        	double progress = Double.parseDouble(value) / 100;
                        	
                        	compileAndUploadWindow.getProgressBar().setProgress(progress);
                        	
                        	Platform.runLater(new Runnable() 
                        	{
                        	    @Override
                        	    public void run() 
                        	    {
                        	    	if(progress == 1)
                        	    	{
                        	    		compileAndUploadWindow.setTitle(title + "   " + BDLang.rb.getString("编译操作已完成"));
                        	    	}
                        	    	else
                        	    	{
                        	    		compileAndUploadWindow.setTitle(title + "   " + BDLang.rb.getString("编译进度") + "：" + value + "%");
                        	    	}
                        	    }
                        	});
                        }
                        
                        compileMessage.setMessage("msg_" + message);


                        //  设置延时
                        //Thread.sleep(50);
                        
                        //updateMessage("msg_" + message);
                    }
                    
                    InputStream errorInStream = new BufferedInputStream(process.getErrorStream());
                    InputStreamReader errorReader = new InputStreamReader(errorInStream);
                    BufferedReader br2 = new BufferedReader(errorReader);
                    
                    while((message = br2.readLine()) != null) 
                    {
                    	System.out.println(message);

                    	//  设置延时
                        //Thread.sleep(50);
                        
                        compileMessage.setMessage("err_" + message);
                        //updateMessage("err_" + message);
                        //updateProgress(1, 0);
                        
                        isSuccess = "ERROR";
                    }
                } 
                catch (IOException e) 
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    
                    compileMessage.setMessage("ERROR");
                    //updateMessage("ERROR");
                    
                    Platform.runLater(new Runnable() 
                	{
                	    @Override
                	    public void run() 
                	    {
                	    	compileAndUploadWindow.setTitle(title + "   " + BDLang.rb.getString("编译过程出现错误") +"！");
                	    }
                	});
                }
                
                if(isSuccess.equals("SUCCESS"))
                {
                	compileMessage.setMessage("COMPLETE");
                	//updateMessage("COMPLETE");
                }
                else
                {
                	compileMessage.setMessage("ERROR");
                	//updateMessage("ERROR");
                }

                return isSuccess;
            }
		};
		
		Task<String> uploadTask = new Task<String>() 
		{
            @Override
            protected String call() throws Exception 
            {
            	// 上传前更新并验证串口连接
            	if(bd_com.equals("") || bd_com.equals(BDLang.rb.getString("未连接")))
            	{
            		Platform.runLater(new Runnable() 
            		{
            		    @Override
            		    public void run() 
            		    {
            	            if(bd_com.equals("") || bd_com.equals(BDLang.rb.getString("未连接")))
                        	{
	            	            compileAndUploadWindow.getAcvView().setColorMsgFont("#FF0000");
			            		compileAndUploadWindow.getAcvCtrl().updateMessageByLine("");
			                	compileAndUploadWindow.getAcvCtrl().updateMessageByLine("<b>>>>>>>> Buddy++：" + BDLang.rb.getString("请确认主控板已经连接计算机并选择了正确的串口序号") + "！</b>");
			                	compileAndUploadWindow.getAcvCtrl().updateMessageByLine("<b>>>>>>>> ===================================================================</b>");
			                	compileAndUploadWindow.getAcvCtrl().updateMessageByLine("");
                        	
			                	// 恢复功能按钮
			                	setFuncEnable(true);
                        	}
                        }
            		});
            		
            		return null;
            	}

            	if(!workMode.equals(WorkMode.FROM_HEX))
            	{
            		tempFile = new File(bd_code_path.trim());

            		bd_hex_path = bd_built_path + "\\" + tempFile.getName() + ".hex";
            	}

            	Runtime run = Runtime.getRuntime();
            	
            	String message = null;
            	
            	String uploadCmd_new = uploadCmd;

            	uploadMessage.setMessage("msg_" + "");
        		uploadMessage.setMessage("msg_" + ">>>>>>> Buddy++：" + BDLang.rb.getString("上传操作即将开始") + "...");
        		uploadMessage.setMessage("msg_" + ">>>>>>> ===================================================================");
        		uploadMessage.setMessage("msg_" + "");
        		
        		// 开始计时（超时后关闭操作）
        		timer.schedule(new TimerTask()
               	{   
                   	public void run()
                   	{   
                        	//System.out.println("上传操作超时...");

                        	// 中断上传操作
                        	uploadThread.stop();
                        	
                        	Platform.runLater(new Runnable() 
                    		{
                    		    @Override
                    		    public void run() 
                    		    {
        	            	    	compileAndUploadWindow.getAcvView().setColorMsgFont("#FF0000");
        			            	compileAndUploadWindow.getAcvCtrl().updateMessageByLine("");
        			            	compileAndUploadWindow.getAcvCtrl().updateMessageByLine("<b>>>>>>>> Buddy++：" + BDLang.rb.getString("上传操作已超时，所有操作已终止") + "！</b>");
        			            	compileAndUploadWindow.getAcvCtrl().updateMessageByLine("<b>>>>>>>> ===================================================================</b>");
        			            	compileAndUploadWindow.getAcvCtrl().updateMessageByLine("");
                                }
                    		});
                        	
                        	// 恢复功能按钮
	                		setFuncEnable(true);

                        	this.cancel();
                        }
                   	
               	}, timeOut);
        		
                try 
                {
                	if(boardName.equals("Arduino Leonardo") || 
                	   boardName.equals("Arduino/Genuino Micro") || 
                	   boardName.equals("Arduino Leonardo ETH") ||
                	   boardName.equals("Arduino Esplora") ||
                	   boardName.equals("LilyPad Arduino USB") ||
                	   boardName.equals("Arduino Yún") ||
                	   boardName.equals("Arduino Yún Mini"))
                	{
                		uploadMessage.setMessage("msg_" + "");
                		uploadMessage.setMessage("msg_" + ">>>>>>> Buddy++：" + BDLang.rb.getString("准备重置串口") + "...");
                		
                		// Reboot Arduino Leonardo.
                    	BDSerialManager2 serialmangeer = new BDSerialManager2();
                    	
                    	serialmangeer.serialPortOpen(bd_com);
                    	Thread.sleep(1000);
                    	uploadMessage.setMessage("msg_" + ">>>>>>> Buddy++：" + BDLang.rb.getString("正在重置串口") + "...");
                    	serialmangeer.serialPortClose();
                    	Thread.sleep(1000);
                    	
                    	serialmangeer = new BDSerialManager2();
                    	
                    	ObservableList<String> list = serialmangeer.getPortList();
                    	
                    	uploadMessage.setMessage("msg_" + ">>>>>>> Buddy++：" + BDLang.rb.getString("串口从") + " <b>" + bd_com + "</b> " + BDLang.rb.getString("重置为") + " <b>" + list.get(0) + "</b>");
                    	uploadMessage.setMessage("msg_" + ">>>>>>> ===================================================================");
                    	uploadMessage.setMessage("msg_" + "");
                    	
                    	bd_com = list.get(0);
                    	
                    	compileAndUploadWindow.getProgressBar().setProgress(0.1);
                    	
                    	//System.out.println("Buddy++:正在重置串口... ");

                    	/*for (String s : list) 
                        {
                            System.out.println(s);
                        }*/
                    	
                    	uploadCmd_new = bd_avrdude_path + "avrdude -C" + bd_avrdude_conf + " -v -p" + bd_cpu + " -c" + bd_platform + " -P" + bd_com + " -b" + bd_rate + " -D -Uflash:w:" + bd_hex_path + ":i";
                	}

                	// display command.
                	uploadMessage.setMessage("cmd_" + uploadCmd_new);
                	
                	Process process = run.exec(uploadCmd_new);
                	
                	InputStream in = process.getInputStream();
                    InputStreamReader reader = new InputStreamReader(in);
                    
                    BufferedReader br = new BufferedReader(reader);
                    
                    while((message = br.readLine()) != null) 
                    {
                        System.out.println(message);
                        
                        uploadMessage.setMessage(message);

                        //  设置延时
                        //Thread.sleep(50);
                        
                        //updateMessage("msg_" + message);
                    }
                    
                    InputStream errorInStream = new BufferedInputStream(process.getErrorStream());
                    InputStreamReader errorReader = new InputStreamReader(errorInStream);
                    BufferedReader br2 = new BufferedReader(errorReader);
                    
                    upLoadProgress = 0;
                    
                    while((message = br2.readLine()) != null) 
                    {
                    	System.out.println(message);
                    	
                    	uploadMessage.setMessage(message);

                    	// get the process info.
			    		if(message.indexOf("avrdude: Version") != -1)
			    		{
			    			upLoadProgress = 0.2;
			    			
			    			compileAndUploadWindow.getProgressBar().setProgress(upLoadProgress);
			    		}
			    		else if(message.indexOf("Connecting to programmer") != -1)
			    		{
			    			upLoadProgress = 0.3;
			    			
			    			compileAndUploadWindow.getProgressBar().setProgress(upLoadProgress);	
			    		}
			    		else if(message.indexOf("avrdude: AVR device initialized and ready to accept instructions") != -1)
			    		{
			    			upLoadProgress = 0.4;
			    			
			    			compileAndUploadWindow.getProgressBar().setProgress(upLoadProgress);
			    		}
			    		else if(message.indexOf("avrdude: Device signature") != -1)
			    		{
			    			upLoadProgress = 0.5;
			    			
			    			compileAndUploadWindow.getProgressBar().setProgress(upLoadProgress);
			    		}
			    		else if(message.indexOf("avrdude: writing flash") != -1)
			    		{
			    			upLoadProgress = 0.7;
			    			
			    			compileAndUploadWindow.getProgressBar().setProgress(upLoadProgress);
			    		}
			    		else if(message.indexOf("avrdude: verifying flash memory against") != -1)
			    		{
			    			upLoadProgress = 0.8;
			    			
			    			compileAndUploadWindow.getProgressBar().setProgress(upLoadProgress);
			    		}
			    		else if(message.indexOf("avrdude: verifying ...") != -1)
			    		{
			    			upLoadProgress = 0.9;
			    			
			    			compileAndUploadWindow.getProgressBar().setProgress(upLoadProgress);
			    		}
			    		else if(message.indexOf("avrdude done.  Thank you.") != -1)
			    		{
			    			upLoadProgress = 1;
			    			
			    			compileAndUploadWindow.getProgressBar().setProgress(upLoadProgress);
			    			
			    			uploadMessage.setMessage("msg_" + "");
	                		uploadMessage.setMessage("msg_" + ">>>>>>> Buddy++：" + BDLang.rb.getString("上传操作已经结束") + "！");
	                		uploadMessage.setMessage("msg_" + ">>>>>>> ===================================================================");
			    		
	                		// 恢复功能按钮
	                		setFuncEnable(true);
	                		
	                		// 关闭计时器
	                		timer.cancel();
			    		}
			    		
			    		Platform.runLater(new Runnable() 
                    	{
                    	    @Override
                    	    public void run() 
                    	    {
                    	    	if(upLoadProgress == 1)
                    	    	{
                    	    		compileAndUploadWindow.setTitle(title + "   " + BDLang.rb.getString("上传操作已完成"));
                    	    	}
                    	    	else
                    	    	{
                    	    		compileAndUploadWindow.setTitle(title + "   " + BDLang.rb.getString("上传进度") + "：" + (upLoadProgress * 100) + "%");
                    	    	}
                    	    }
                    	});

                    	//  设置延时
                        Thread.sleep(25);
                        
                        //updateMessage(message);
                        //updateProgress(1, 0);
                    }
                } 
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    
                    //updateMessage("ERROR");
                    
                    Platform.runLater(new Runnable() 
                	{
                	    @Override
                	    public void run() 
                	    {
                	    	compileAndUploadWindow.setTitle(title + "   " + BDLang.rb.getString("上传过程出现错误") + "！");
                	    }
                	});
                }
                
                //updateMessage("COMPLETE");
                
                return "SUCCESS";
            }
		};
		
		timer = new Timer();
		
		dumpThread 		= new Thread(dumpTask);
		compileThread 	= new Thread(compileTask);
		uploadThread 	= new Thread(uploadTask);
		
		// 开始设置参数（设置->编译->上传）
		dumpThread.start();
	}
	
	public void addMessageListener()
	{
		dumpMessage.addPropertyChangeListener(new PropertyChangeListener() 
		{
			@Override
			public void propertyChange(PropertyChangeEvent evt) 
			{ 
				String message = evt.getNewValue().toString();

				//System.out.println("################### " + message);

				Platform.runLater(new Runnable() 
				{
				    @Override
				    public void run() 
				    {
				    	if(message.equals("COMPLETE"))
		                {
				    		// 只上传不编译
				    		if(compileMode.equals(CompileMode.UPLOAD))
				    		{
				    			uploadThread.start();
				    			
				    			return;
				    		}
				    		
				    		compileAndUploadWindow.getAcvView().setColorMsgFont("#000000");
				    		compileAndUploadWindow.getAcvCtrl().updateMessageByLine("");
		                	compileAndUploadWindow.getAcvCtrl().updateMessageByLine(">>>>>>> Buddy++：" + BDLang.rb.getString("初始化操作已结束") + "！");
		                	compileAndUploadWindow.getAcvCtrl().updateMessageByLine(">>>>>>> ===================================================================");
		                	compileAndUploadWindow.getAcvCtrl().updateMessageByLine("");
		                	
		                	compileThread.start();
		                	
		                	return;
		                }
				    	else if(message.substring(0, 4).equals("msg_"))
		            	{
				    		compileAndUploadWindow.getAcvView().setColorMsgFont("#000000");
		            		compileAndUploadWindow.getAcvCtrl().updateMessageByLine(message.substring(4));
		            	}
				    	else if(message.substring(0, 4).equals("cmd_"))
		            	{
				    		compileAndUploadWindow.getAcvView().setColorMsgFont("#FF00FF");
		            		compileAndUploadWindow.getAcvCtrl().updateMessageByLine("<b>" + message.substring(4) + "</b>");
		            	}
		            	else
		            	{
		            		compileAndUploadWindow.getAcvView().setColorMsgFont("#FF00FF");
		                    compileAndUploadWindow.getAcvCtrl().updateMessageByLine(message);
		            	}
				    }
				});
			}
		});
		
		compileMessage.addPropertyChangeListener(new PropertyChangeListener() 
		{
			@Override
			public void propertyChange(PropertyChangeEvent evt) 
			{ 
				String message = evt.getNewValue().toString();

				//System.out.println("################### " + message);

				Platform.runLater(new Runnable() 
				{
				    @Override
				    public void run() 
				    {
				    	if(message.equals("COMPLETE"))
		                {
				    		compileAndUploadWindow.getAcvView().setColorMsgFont("#000000");
				    		compileAndUploadWindow.getAcvCtrl().updateMessageByLine("");
		                	compileAndUploadWindow.getAcvCtrl().updateMessageByLine(">>>>>>> Buddy++：" + BDLang.rb.getString("编译操作已结束") + "！");
		                	compileAndUploadWindow.getAcvCtrl().updateMessageByLine(">>>>>>> ===================================================================");
		                	//compileAndUploadWindow.getAcvCtrl().updateMessageByLine("");
		                	
		                	// 只编译不上传
		                	if(compileMode.equals(CompileMode.COMPILE))
		                	{
		                		// 恢复功能按钮
		                		setFuncEnable(true);
		                		
		                		return;
		                	}
		                	
		                	uploadThread.start();

		                	return;
		                }
		            	else if(message.equals("ERROR"))
		            	{
		            		compileAndUploadWindow.getAcvView().setColorMsgFont("#FF0000");
		            		compileAndUploadWindow.getAcvCtrl().updateMessageByLine("");
		            		compileAndUploadWindow.getAcvCtrl().updateMessageByLine(">>>>>>> Buddy++：" + BDLang.rb.getString("编译过程出现错误") + "！");
		            		compileAndUploadWindow.getAcvCtrl().updateMessageByLine(">>>>>>> ===================================================================");
		            		//compileAndUploadWindow.getAcvCtrl().updateMessageByLine("");
		            		
		            		return;
		            	}
		            	else if(message.substring(0, 4).equals("inf_"))
		            	{
		            		compileAndUploadWindow.getAcvView().setColorMsgFont("#000000");
		            		compileAndUploadWindow.getAcvCtrl().updateMessageByLine(message.substring(4));
		            	}
		            	else if(message.substring(0, 4).equals("cmd_"))
		            	{
				    		compileAndUploadWindow.getAcvView().setColorMsgFont("#0000FF");
		            		compileAndUploadWindow.getAcvCtrl().updateMessageByLine("<b>" + message.substring(4) + "</b>");
		            	}
		            	else if(message.substring(0, 4).equals("err_"))
		            	{
		            		compileAndUploadWindow.getAcvView().setColorMsgFont("#FF0000");
		            		compileAndUploadWindow.getAcvCtrl().updateMessageByLine("<b>" + message.substring(4) + "</b>");
		            	}
		            	else
		            	{
		            		compileAndUploadWindow.getAcvView().setColorMsgFont("#0000FF");
		            		compileAndUploadWindow.getAcvCtrl().updateMessageByLine(message.substring(4));
		            		//acvCtrl.updateMessageByLine(newValue);
		            	}
				    }
				});
			}
			
		});
		
		uploadMessage.addPropertyChangeListener(new PropertyChangeListener() 
		{
			@Override
			public void propertyChange(PropertyChangeEvent evt) 
			{ 
				String message = evt.getNewValue().toString();

				Platform.runLater(new Runnable() 
				{
				    @Override
				    public void run() 
				    {
				    	if(message.equals("COMPLETE"))
		                {
		                	//compileAndUploadWindow.getAcvCtrl().updateMessageByLine("***** 上传操作已结束！");
		                	
		                	return;
		                }
				    	else if(message.length() > 3 && message.substring(0, 4).equals("msg_"))
		            	{
				    		
				    		compileAndUploadWindow.getAcvView().setColorMsgFont("#000000");
			            	compileAndUploadWindow.getAcvCtrl().updateMessageByLine(message.substring(4));
		            	}
				    	else if(message.length() > 3 && message.substring(0, 4).equals("cmd_"))
		            	{
				    		compileAndUploadWindow.getAcvView().setColorMsgFont("#E34C00");
		            		compileAndUploadWindow.getAcvCtrl().updateMessageByLine("<b>" + message.substring(4) + "</b>");
		            	}
				    	else if(message.length() > 3 && message.substring(0, 4).equals("err_"))
		            	{
				    		compileAndUploadWindow.getAcvView().setColorMsgFont("#FF0000");
		            		compileAndUploadWindow.getAcvCtrl().updateMessageByLine("<b>" + message.substring(4) + "</b>");
		            	}
				    	else if(message.indexOf("avrdude: ser_open(): can't open device") != -1)
				    	{
				    		compileAndUploadWindow.getAcvCtrl().updateMessageByLine(message);
				    		compileAndUploadWindow.getAcvView().setColorMsgFont("#FF0000");
				    		compileAndUploadWindow.getAcvCtrl().updateMessageByLine("");
		            		compileAndUploadWindow.getAcvCtrl().updateMessageByLine("<b>" + ">>>>>>> Buddy++：" + BDLang.rb.getString("请确认主控板已经连接计算机并选择了正确的串口序号") + "！" + "</b>");
				    	}
				    	else
				    	{
				    		compileAndUploadWindow.getAcvView().setColorMsgFont("#E34C00");
					    	compileAndUploadWindow.getAcvCtrl().updateMessageByLine(message);
				    	}
				    }
				});
			}
		});
	}
	
	public void setFuncEnable(boolean isEnable)
	{
		if(isEnable == true)
		{
			compileAndUploadWindow.getCompileBtn().setDisable(false);
			compileAndUploadWindow.getUploadBtn().setDisable(false);
			compileAndUploadWindow.getCompileAndUploadBtn().setDisable(false);
			compileAndUploadWindow.getUpdateBtn().setDisable(false);
			compileAndUploadWindow.getSerialListCombox().setDisable(false);
			compileAndUploadWindow.getBoardListCombox().setDisable(false);
		}
		else
		{
			compileAndUploadWindow.getCompileBtn().setDisable(true);
			compileAndUploadWindow.getUploadBtn().setDisable(true);
			compileAndUploadWindow.getCompileAndUploadBtn().setDisable(true);
			compileAndUploadWindow.getUpdateBtn().setDisable(true);
			compileAndUploadWindow.getSerialListCombox().setDisable(true);
			compileAndUploadWindow.getBoardListCombox().setDisable(true);
		}
		
	}
	
	public void updateSerialPorts()
	{
		compileAndUploadWindow.getSerialListCombox().setItems(new BDSerialManager2().getPortList());
        compileAndUploadWindow.getSerialListCombox().getSelectionModel().select(0);
        
        BDParameters.connectCom = this.compileAndUploadWindow.getSerialListCombox().getSelectionModel().getSelectedItem().toString();
        
        //System.out.println(compileAndUploadWindow.getSerialListCombox().getValue());

		if(compileAndUploadWindow.getSerialListCombox().getValue().equals(BDLang.rb.getString("未连接")))
		{
			// 如果串口未连接屏蔽上传功能（编译且上传）
			compileAndUploadWindow.getUploadBtn().setDisable(true);
			compileAndUploadWindow.getCompileAndUploadBtn().setDisable(true);
		}
		else
		{
			// 恢复上传功能（编译且上传）
			compileAndUploadWindow.getUploadBtn().setDisable(false);
			compileAndUploadWindow.getCompileAndUploadBtn().setDisable(false);
		}
	}
	
	public void stopAllThreads()
	{
		try
    	{
    		boolean isAlive = false;
    		
    		if(dumpThread != null && dumpThread.isAlive())
        	{
        		dumpThread.stop();
        		
        		isAlive = true;
        	}
        	
        	if(compileThread != null && compileThread.isAlive())
        	{
        		compileThread.stop();
        		
        		isAlive = true;
        	}
        	
        	if(uploadThread != null && uploadThread.isAlive())
        	{
        		uploadThread.stop();
        		
        		isAlive = true;
        	}
        	
        	// 当有操作被终止的情况下才显示以下信息
        	if(isAlive == true)
        	{
        		compileAndUploadWindow.getAcvView().setColorMsgFont("#FF0000");
        		compileAndUploadWindow.getAcvCtrl().updateMessageByLine("");
            	compileAndUploadWindow.getAcvCtrl().updateMessageByLine("<b>>>>>>>> Buddy++：" + BDLang.rb.getString("所有操作已经终止") + "！</b>");
            	compileAndUploadWindow.getAcvCtrl().updateMessageByLine("<b>>>>>>>> ===================================================================</b>");
            	
        		// 关闭计时器
        		timer.cancel();
        		
        		Platform.runLater(new Runnable() 
            	{
            	    @Override
            	    public void run() 
            	    {
            	    	compileAndUploadWindow.setTitle(title + "   " + BDLang.rb.getString("用户已终止所有操作") + "！");
            	    }
            	});
        	}
    	}
    	catch(Exception ex){}
    }
	
	public String getBd_built_path() 
	{
		return bd_built_path;
	}

	public void setBd_built_path(String bd_built_path) 
	{
		this.bd_built_path = bd_built_path;
	}

	public String getBd_code_path() 
	{
		return bd_code_path;
	}

	public void setBd_code_path(String bd_code_path) 
	{
		this.bd_code_path = bd_code_path;
	}
}
