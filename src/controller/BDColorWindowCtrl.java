package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.BDCodeAgent;
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
            	BDCodeAgent codeAgent = new BDCodeAgent(workspaceCtrl.workspaceView.workspaceModel.curTab);
                
                // Get caret line number.
                int lineNum = codeAgent.getCaretLineNumber();
                
                // Get tab count.
                int tabCount = 0;
                
                try
                {
                    tabCount = codeAgent.getTabCount();
                }
                catch(Exception e)
                {
                    tabCount = 0;
                }
                
                String code  = "String color = \"";

                // Get color value without alpha.
                code += colorWindow.colorPicker.getValue().toString().substring(0, 7);
                
                //code += "};\n";
                code += "\";";

                // Insert the code.
                codeAgent.insert(code);
                
                // Close the window.
                colorWindow.close();
            }
        });
		
		colorWindow.submitBtn2.setOnAction(new EventHandler<ActionEvent>() 
        {    
            @Override
            public void handle(ActionEvent event) 
            {
            	BDCodeAgent codeAgent = new BDCodeAgent(workspaceCtrl.workspaceView.workspaceModel.curTab);
                
                // Get caret line number.
                int lineNum = codeAgent.getCaretLineNumber();
                
                // Get tab count.
                int tabCount = 0;
                
                try
                {
                    tabCount = codeAgent.getTabCount();
                }
                catch(Exception e)
                {
                    tabCount = 0;
                }
                
                String code  = "";
                
                String r = String.valueOf((int)(255 * colorWindow.colorPicker.getValue().getRed()));
                String g = String.valueOf((int)(255 * colorWindow.colorPicker.getValue().getGreen()));
                String b = String.valueOf((int)(255 * colorWindow.colorPicker.getValue().getBlue()));
                
                code += ("int rColor = " + r + ";\n");
                
                // 添加缩进
                for(int i = 0; i < tabCount; i++)
                {
                    code += "\t";
                }
                
                code += ("int gColor = " + g + ";\n");
                
                // 添加缩进
                for(int i = 0; i < tabCount; i++)
                {
                    code += "\t";
                }
                
                code += ("int bColor = " + b + ";");

                // Insert the code.
                codeAgent.insert(code);
                
                // Close the window.
                colorWindow.close();
            }
        });
	}
}
