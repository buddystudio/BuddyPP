package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import view.BDColorWindow;

public class BDColorWindowCtrl 
{
	public BDColorWindowCtrl(BDColorWindow colorWindow, BDWorkspaceCtrl workspaceCtrl)
	{
		colorWindow.submitBtn.setOnAction(new EventHandler<ActionEvent>() 
        {    
            @Override
            public void handle(ActionEvent event) 
            {
            	// 获取词位
                int tabCount = workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.getCurColumn() - 1;
                
                String code  = "String color = \"";

                // Get color value without alpha.
                code += colorWindow.colorPicker.getValue().toString().substring(0, 7);
                
                //code += "};\\n";
                code += "\";";

                // 插入语句
                code = code.replaceAll("\"","\\\\\"");
                
                workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.insert(code);
                
                // Close the window.
                colorWindow.close();
            }
        });
		
		colorWindow.submitBtn2.setOnAction(new EventHandler<ActionEvent>() 
        {    
            @Override
            public void handle(ActionEvent event) 
            {
            	// 获取词位
                int tabCount = workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.getCurColumn() - 1;
                
                String code  = "";
                
                String r = String.valueOf((int)(255 * colorWindow.colorPicker.getValue().getRed()));
                String g = String.valueOf((int)(255 * colorWindow.colorPicker.getValue().getGreen()));
                String b = String.valueOf((int)(255 * colorWindow.colorPicker.getValue().getBlue()));
                
                code += ("int rColor = " + r + ";\\n");
                
                // 添加缩进
                for(int i = 0; i < tabCount; i++)
                {
                    code += " ";
                }
                
                code += ("int gColor = " + g + ";\\n");
                
                // 添加缩进
                for(int i = 0; i < tabCount; i++)
                {
                    code += " ";
                }
                
                code += ("int bColor = " + b + ";");

                // 插入语句
                code = code.replaceAll("\"","\\\\\"");
                
                workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.insert(code);
                workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.focus();
                
                // Close the window.
                colorWindow.close();
            }
        });
	}
}
