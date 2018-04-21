/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author gsh
 */
public class BDParameters
{
	static public String os			 	 = "";				// 操作系统类型
	static public String arch			 = "";				// 操作系统指令长度（32/64）
	static public int codeIdCount        = 0;        		// 代码文档序号
    static public int variableId         = 0;        		// 循环计数变量序号
    
    static public String version         = "1.0.1-beta";  	// 软件版本号
    static public String langues         = "zh-cn";  		// 语言
    static public String organization	= "BuddyStudio";		// 开发组织
    static public String author			= "李宝智、郭少豪";	// 开发者署名
    
    static public String boardType       = "";       		// 开发板型号
    static public String connectCom      = "";       		// 串口序号
    static public String curComRate      = "";       		// 串口通讯波特率
    
    static public String sketchBook      = "";
    
    //static public String txtAreafont     = "Courier New";   // Consolas、Courier New、Courier
    //static public String txtAreafont     = "宋体";
    static public String txtAreafont     = "等线";
    static public int    txtAreafontSize = 15;       		// 编辑区字体大小
    static public boolean guiIsSimple    = false;    		// 是否简约界面模式
    
    static public java.util.List<String> serialports;
    
    static public ObservableList<BDLibsModel> libsList = FXCollections.observableArrayList(); // 到入库列表
}
