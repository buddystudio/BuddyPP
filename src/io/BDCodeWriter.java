/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author gsh
 */
public class BDCodeWriter 
{
    /**
     * 写入文件
     */
    public static void fileWriter(String fileName, String codeTxt) throws IOException
    {
    	// 判断是否为例程
    	if(fileName==null
    			||fileName.isEmpty()
    			||fileName.indexOf("\\examples\\Buddy\\") != -1 
    			||fileName.indexOf("\\examples\\Modular\\") != -1 
    			|| fileName.indexOf("\\examples\\Arduino\\") != -1)
    	{    		    		
    		// 例程不能保存
    		return;    		
    	}
        
        // 创建一个FileWriter对象
        FileWriter fw = new FileWriter(fileName);
        
        fw.write(codeTxt);
        // 刷新缓冲区
        fw.flush();        
        // 关闭文件流对象
        fw.close();
    }
    
    public static void preparePath(String path)
    {
    	File savepath = new File(path);
    	
    	if(savepath.exists())
    		return ;
    	savepath.mkdirs();			
    }
}
