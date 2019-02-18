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
 * @date 2018.11.4
 *
 *************************************************************************
 */
package buddy;

import java.awt.GraphicsEnvironment;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import controller.BDGUICtrl;
import javafx.application.Application;
import javafx.stage.Stage;
import model.BDLang;
import model.BDParameters;
import util.base.Preferences;
import view.BDGUIView;

/**
 *
 * @author gsh
 */
public class BuddyPP extends Application
{ 
	//private static final Logger logger = LogManager.getLogger(BuddyPP.class);
	
	/**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args); 
    }
	
    @Override
    public void start(Stage primaryStage) 
    {
    	// 获取配置信息
    	BDParameters.getProfile();
    	
    	BDParameters.primaryStage = primaryStage;
    	
    	// 初始化基本配置参数
        Preferences.init(null);
    	
    	// 获取系统信息
    	this.getSysInfo();
    	
    	// 设置窗体风格
        //this.setStyle();

    	// 设定界面语言
    	this.setLanguage();

        // 初始化界面视图
        BDGUIView gui = new BDGUIView(primaryStage);
        
        // 初始化界面控制器
        BDGUICtrl guiCtrl = new BDGUICtrl(gui);
    }
    
    private void getSysInfo()
    {
    	// 判断系统类型
        String arch = System.getProperty("os.arch");
        String os   = System.getProperty("os.name");
        
        // 获取当前系统类型（Windows / Mac / Linux）
        BDParameters.os = os;
        
        // 获取操作系统指令长度（32bit/64bit）
        BDParameters.arch = arch;

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        
        BDParameters.gds = ge.getScreenDevices();
    }
    
    private void setLanguage()
    {
    	// 返回Java所支持的全部国家和语言的数组
    	Locale[] localeList = Locale.getAvailableLocales();

    	//遍历数组的每个元素，依次获取所支持的国家和语言
    	for (int i = 0; i < localeList.length ; i++ )
    	{
    		//打印出所支持的国家和语言
    		//System.out.println(localeList[i].getDisplayCountry() + "=" + localeList[i].getCountry()+ " " + localeList[i].getDisplayLanguage() + "=" + localeList[i].getLanguage());
    	}
    	
    	// 取得系统默认的国家/语言环境
    	//Locale myLocale = Locale.getDefault();

    	BDLang.locale = new Locale("zh", "CN");
    	//BDLang.locale = new Locale("en", "US");

    	if(BDParameters.langues.equals("简体中文"))
    	{
    		BDLang.locale = new Locale("zh", "CN");
    	}
    	else if(BDParameters.langues.equals("繁體中文"))
    	{
    		BDLang.locale = new Locale("zh", "HK");
    	}
    	else if(BDParameters.langues.equals("English"))
    	{
    		BDLang.locale = new Locale("en", "US");
    	}

    	// 绑定语言资源
    	BDLang.rb = ResourceBundle.getBundle("resources.lang.lang", BDLang.locale);
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
            //logger.error(this.getClass().toString(),ex);
        }
    }
}