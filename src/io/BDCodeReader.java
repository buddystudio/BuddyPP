/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gsh
 */
public class BDCodeReader
{
    public BDCodeReader()
    {
    
    }
    
    /**
     * Read file by lines.s
     */
    public static String readFileByLines(String fileName) throws UnsupportedEncodingException, FileNotFoundException, IOException 
    {
        String CodeTxt = "";
        
        List<String> lines = new ArrayList<String>();  

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"UTF-8"));  

        String line = null;  

        while ((line = br.readLine()) != null) 
        {  
            lines.add(line);
            
            CodeTxt += (line + "\\n");
        }  

        br.close();  

        return CodeTxt;
    }

}
