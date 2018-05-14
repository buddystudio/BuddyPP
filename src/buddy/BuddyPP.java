/**
 *************************************************************************
 *
 * @file Buddy.java
 * @brief 主程序入口
 * 
 * Buddy++主程序入口，界面初始化。
 * 
 * @author gsh
 * @version 1.0
 * @date 2015.12.1
 *
 *************************************************************************
 */
package buddy;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mongcj.util.base.Base;
import com.mongcj.util.base.Preferences;
import com.mongcj.util.debug.BDSerial;

import controller.BDGUICtrl;
import javafx.application.Application;
import javafx.stage.Stage;
import model.BDParameters;
import view.BDGUIView;

/**
 *
 * @author gsh
 */
public class BuddyPP extends Application 
{ 
	private static final Logger logger = LogManager.getLogger(BuddyPP.class);
	
    @Override
    public void start(Stage primaryStage) 
    {
        // 判断系统类型
        String arch = System.getProperty("os.arch");
        String os   = System.getProperty("os.name");
        
        // 获取当前系统类型（Windows/Mac/Linux）
        BDParameters.os = os;
        
        // 获取操作系统指令长度（32bit/64bit）
        BDParameters.arch = arch;
        
        //logger.debug("buddy启动");
        /*
        System.out.println(arch);
        System.out.println(os);
        if(arch.contains("64"))
        {System.out.println("64 bit");}
        else
        {System.out.println("32 bit");}
         */
        
        // 初始化基本配置参数
        Base base = new Base(null);
        
        Preferences.init(null);
        Preferences.set("upload.verbose", "true");
        
        // 获取所有可用的COM端口
        java.util.List<String> serialports = BDSerial.list();  
        
        BDParameters.serialports = serialports;
       
        if(!serialports.isEmpty())
        {
        	// 指定串口
            Preferences.set("serial.port", serialports.get(serialports.size() - 1)); 
            BDParameters.connectCom = Preferences.get("serial.port");
        }

        // Preferences.save();
        
        // 设置窗体风格
        this.setStyle();

        // 初始化界面视图
        BDGUIView gui = new BDGUIView(primaryStage);
        
        // 初始化界面控制器
        BDGUICtrl guiCtrl = new BDGUICtrl(gui);
    }
    
    private void setStyle()
    {
    	// 设置Swing组件保持Windows风格
        try 
        {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) 
        {
            logger.error(this.getClass().toString(),ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args); 
    }
}