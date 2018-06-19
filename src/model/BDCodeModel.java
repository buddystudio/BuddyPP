/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import util.debug.BDPreprocessor;
import util.debug.BDRunnerException;
import util.debug.BDInoCode;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author gsh
 */
public class BDCodeModel 
{
	//private static final Logger logger = LogManager.getLogger();
	
    public boolean   isSaved = false;         // 文件保存状态（是否已保存）
    public boolean   isCompiled = false;      // 程序编译状态
	public String    path = ""; 			  // 文件保存路径
	public String    compilePath = ""; 		  // 编译文件路径
    public BDInoCode code;

    public BDCodeModel() 
    {    	
    	code = new BDInoCode();
    	
        // 设置新建文件的命名
    	setName("sketch_" + BDParameters.codeIdCount);
    	
    	String baseCode = "\\nvoid setup()\\n{\\n\\t// 初始化代码\\n\\n}\\n\\nvoid loop()\\n{\\n\\t// 主程序代码\\n\\n}\\n";
    	String code = createCodeNotes() + baseCode;
    	
    	// 新建文件默认内容
    	//setCodeText("\nvoid setup()\n{\n\t// 初始化代码\n\n}\n\nvoid loop()\n{\n\t// 主程序代码\n\n}\n");
    	setCodeText(code);  
    }
    
    // 添加源码页首注释
    public String createCodeNotes()
    {
    	Date day = new Date();    

    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 

    	String date 	= df.format(day);
    	String filename = code.getFileName();
    	String notes 	= "";
    	
    	notes += "\\n";
    	notes += "/**\\n";
    	notes += " ************************************************\\n";
    	notes += " * \\n";
    	notes += " * @file    : " + filename + ".ino\\n";
    	notes += " * @brief   :  \\n";
    	notes += " * @author  :  \\n";
    	notes += " * @version : 1.0.0 \\n";
    	notes += " * @date    : " + date + "\\n";
    	notes += " * \\n";
    	notes += " ************************************************\\n";
    	notes += "*/\\n";
    	
    	return notes;
    }

    public String preprocess(String buildPath) throws BDRunnerException 
    {
        return code.preprocess(buildPath, new BDPreprocessor());
    }
    
    // 程序代码源文件名称
    public String getName()
    {
    	return code.name;
    }
    
    public void setName(String name)
    {
    	code.name = name;
    }
    
    // 编译的类名
    public String getClassName()
    {
    	return code.className;
    }
    
    public String getCppName()
    {
    	return code.className + ".cpp";
    }
    
    public void setClassName(String cname)
    {
    	int p=cname.lastIndexOf(".");
    	
    	if(p>0)
    	{
    		code.className=cname.substring(0, p);
    	}
    	else
    		code.className=cname;
    }	
    // 程序代码文本
    public String getCodeText()
    {
    	return code.getCodeText();
    }    
    
    public void setCodeText(String text)
    {
    	code.setCodeText(text);
    }

	public List<File> getImportedLibraries() 
	{
		return code.getImportedLibraries();
	}
}
