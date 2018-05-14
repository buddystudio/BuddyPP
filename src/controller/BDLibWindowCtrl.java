/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.BDLibsModel;
import model.BDParameters;
import view.BDLibWindow;

/**
 *
 * @author gsh
 */
public class BDLibWindowCtrl{
	private static final Logger logger=LogManager.getLogger();
    public BDLibWindowCtrl(BDLibWindow libWindow, BDWorkspaceCtrl workspaceCtrl)
    {
        BDParameters.libsList.clear();
        
        // 获得相对路径
        String path = System.getProperty("user.dir") + File.separator + "libraries";
        
        // 测试目录
        File file = new File(path);
       
        File[] tempList = file.listFiles();
        
        try
        {
            // 遍历库
            for (int i = 0; i < tempList.length; i++) 
            {
                if (tempList[i].isDirectory()) 
                {
                    //System.out.println("文件夹："+tempList[i]);
                    libWindow.strList.add(tempList[i].getName());

                    BDLibsModel lib = new BDLibsModel(tempList[i].getName());

                    BDParameters.libsList.add(lib);

                    //System.out.println(tempList[i].getName());

                    File libFile = new File(path + File.separator + tempList[i].getName());

                    File[] libFileList = libFile.listFiles();

                    for (int j = 0; j < libFileList.length; j++) 
                    {
                        //if(libFileList[j].getName().indexOf(".h") != -1)
                        if(libFileList[j].getName().contains(".h"))
                        {
                            //System.out.println(" " + libFileList[j].getName());

                            lib.libsList.add(libFileList[j].getName());
                        }
                    }
                }
            }
        }
        catch(Exception ex)
        {
        	logger.error("",ex);
        }
       
       libWindow.listView.setItems(libWindow.strList);
       
       libWindow.importBtn.setOnAction(new EventHandler<ActionEvent>() 
       {    
            @Override
            public void handle(ActionEvent event) 
            {
                int index = libWindow.listView.getSelectionModel().getSelectedIndex();

                // 生成语句
                
                int len = BDParameters.libsList.get(index).libsList.size();
                
                String code = "";
                
                for(int i = 0; i < len; i++)
                {
                    code += "#include <" + BDParameters.libsList.get(index).libsList.get(i) + ">\\n";
                }

                // 插入语句
                code = code.replaceAll("\"","\\\\\"");
                
                workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.moveCursorTo(0, 0);
                workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.insert(code);

                // 关闭窗口
                libWindow.close();
            }
        });
    }
}
