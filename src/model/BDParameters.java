/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

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
    
    static public String version         = "1.1.2";  		// 软件版本号
    static public String langues         = "zh-cn";  		// 语言
    static public String organization	= "BuddyStudio";	// 开发组织
    static public String author			= "李宝智、郭少豪";		// 开发者署名
    
    static public String boardType       = "";       		// 开发板型号
    static public String connectCom      = "";       		// 串口序号
    static public String curComRate      = "";       		// 串口通讯波特率
    
    static public String sketchBook      = "";
    
    //static public String txtAreafont     = "Courier New";   // Consolas、Courier New、Courier
    //static public String txtAreafont     = "宋体";
    static public String txtAreafont     = "等线";
    static public int    txtAreafontSize = 15;       		// 编辑区字体大小
    static public boolean guiIsSimple    = false;    		// 是否简约界面模式
    
    static public double minWidth		 = 940;
    static public double minHeight	     = 660;
    
    static public double curWidth		 = 940;
    static public double curHeight	     = 660;
    
    static public java.util.List<String> serialports;
    
    static public ObservableList<BDLibsModel> libsList = FXCollections.observableArrayList(); // 到入库列表
    
    static public String editorTheme = "";
    static public String editorFontSize = "";
    
    static public String editorProfilePath = "profile/editor.txt";
    
    static public void setEditorProfile(String theme, String fontSize)
    {
    	// 去除字符串中的空格
    	editorTheme = theme;
    	editorFontSize = fontSize.replace(" ", "");
    	
    	// 创建一个FileWriter对象
        FileWriter fw;
        
		try 
		{
			fw = new FileWriter(editorProfilePath);
			
			String codeTxt = "";
			
			String line1 = "theme=" + editorTheme + "\n";
			String line2 = "size=" + editorFontSize + "\n";
			
			codeTxt = line1 + line2;
	        
	        fw.write(codeTxt);
	        
	        // 刷新缓冲区
	        fw.flush();     
	        
	        // 关闭文件流对象
	        fw.close();
	        
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    static public void getEditorProfile()
    {
    	// 读取编辑器配置文件
    	try 
		{
			BufferedReader br;
			
			br = new BufferedReader(new InputStreamReader(new FileInputStream(editorProfilePath),"UTF-8"));
			
			String line = null;
			
			int count = 0;

	        while ((line = br.readLine()) != null) 
	        {   
	            String codeTxt = line;
	            
	            if(count == 0)
	            {
	            	editorTheme = codeTxt.substring(6, codeTxt.length());
	            }
	            else if(count == 1)
	            {
	            	editorFontSize = codeTxt.substring(5, codeTxt.length() - 2);
	            }
	            
	            count++;
	        }

	        br.close();
		} 
		catch (UnsupportedEncodingException | FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
}
