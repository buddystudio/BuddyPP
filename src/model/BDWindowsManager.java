package model;

import view.BDAboutWindow;
import view.BDArWindow;
import view.BDAwWindow;
import view.BDComWindow;
import view.BDConsoleWindow;
import view.BDDefineVariableWindow;
import view.BDDelayWindow;
import view.BDDrWindow;
import view.BDDwWindow;
import view.BDExampleWindow;
import view.BDForWindow;
import view.BDHintDialogWindow;
import view.BDIfWindow;
import view.BDLEDsWindow;
import view.BDLibWindow;
import view.BDPinModeWindow;
import view.BDPluginWindow;
import view.BDPreSettingWindow;
import view.BDSearchWindow;
import view.BDSerialWindow;
import view.BDSwitchWindow;
import view.BDWhileWindow;

public class BDWindowsManager
{
	public static BDHintDialogWindow hintDialogWindow = new BDHintDialogWindow("", "");	// 提示窗口
	
	public static BDSearchWindow searchWindow 	= new BDSearchWindow();     // 搜索窗口
	public static BDLibWindow libWindow     	= new BDLibWindow();        // 添加库窗口
	public static BDComWindow comWindow     	= new BDComWindow();        // 串口通讯窗口
	public static BDPreSettingWindow psw    	= new BDPreSettingWindow(); // 预设置窗口
	public static BDExampleWindow expWindow 	= new BDExampleWindow();    // 例程窗口
	public static BDConsoleWindow consoleWindow = new BDConsoleWindow();    // 编译信息窗口
	public static BDAboutWindow aboutWindow 	= new BDAboutWindow();      // 关于我们窗口
	public static BDPluginWindow pluginWindow   = new BDPluginWindow();     // 工具插件窗口
	
	public static BDDefineVariableWindow dfvWindow = new BDDefineVariableWindow();     // 变量定义窗口
    public static BDPinModeWindow pinModeWindow    = new BDPinModeWindow();            // pinMode端口设置窗口
    public static BDDelayWindow delayWindow        = new BDDelayWindow();              // Delay延时设置窗口
    public static BDForWindow forWindow            = new BDForWindow();                // For条件循环设置窗口
    public static BDWhileWindow whileWindow        = new BDWhileWindow();              // While条件循环设置窗口
    public static BDIfWindow ifWindow              = new BDIfWindow();                 // If条件循环设置窗口
    public static BDDrWindow drWindow              = new BDDrWindow();                 // DigitalRead数字端口读取设置窗口
    public static BDDwWindow dwWindow              = new BDDwWindow();                 // DigitalWrite数字端口写入设置窗口
    public static BDArWindow arWindow              = new BDArWindow();                 // AnalogRead模拟端口读取设置窗口
    public static BDAwWindow awWindow              = new BDAwWindow();                 // AnalogWrite模拟端口写入设置窗口
    public static BDSerialWindow serialWindow      = new BDSerialWindow();             // Serial串口通讯模板设置窗口
    public static BDSwitchWindow switchWindow      = new BDSwitchWindow();             // SwitchCase条件分支设置窗口
    public static BDLEDsWindow ledsWindow          = new BDLEDsWindow();
	
	public BDWindowsManager()
	{
		// TODO Auto-generated constructor stub
	}

}
