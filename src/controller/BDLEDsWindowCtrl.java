/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.BDCodeAgent;
import view.BDLEDsWindow; 

/**
 *
 * @author gsh
 */
public class BDLEDsWindowCtrl 
{
    public BDLEDsWindowCtrl(BDLEDsWindow ledsWindow, BDWorkspaceCtrl workspaceCtrl)
    {
        // Clear the LED state list.
        ledsWindow.cleanBtn.setOnAction(new EventHandler<ActionEvent>() 
        {    
            @Override
            public void handle(ActionEvent event) 
            {
                // Traversing the LED button list.
                for(int i = 0; i < ledsWindow.btnList.size(); i++)
                {
                    ledsWindow.btnList.get(i).setImage(ledsWindow.ledOff);
                }
            }
            
        });

        // Generate the code.
        ledsWindow.submitBtn.setOnAction(new EventHandler<ActionEvent>() 
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
                
                String code  = "byte img[] = {";
                
                int j = 0;
                
                String result = "";

                // Traversing the LED buttons list.
                for(int i = 0; i < ledsWindow.btnList.size(); i++)
                {
                    // Generate the LED state list.
                    if(ledsWindow.btnList.get(i).getImage().equals(ledsWindow.ledOn))
                    {
                        result += "1";
                    }
                    else
                    {
                        result += "0";
                    }
                    
                    if(j == 7)
                    {
                        // Convert binary to hexadecimal.
                        code += "0x" + Integer.toString (Integer.parseInt (result, 2), 16);
                        
                        result = "";
                        
                        j = 0;
                        
                        if(i < ledsWindow.btnList.size() - 1)
                        {
                            code += ", ";
                        }
                    }
                    else
                    {
                        j++;
                    }
                }
                
                //code += "};\n";
                code += "};";

                // Insert the code.
                codeAgent.insert(code);
                
                // Close the window.
                ledsWindow.close();
            }
        });
    }
}
