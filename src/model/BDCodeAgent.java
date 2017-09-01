/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author gsh
 */
public class BDCodeAgent 
{
    BDCodeTabModel curTab;
    
    public BDCodeAgent(BDCodeTabModel curTab)
    {
        this.curTab = curTab;
    }
    
    public void insert(String code)
    {
    	try
    	{
    		// If selected text not empty, we must clear.
        	if(curTab.textArea.getSelectedText() != null)
        	{
        		// Get current selected position.
        		int start = curTab.textArea.getSelectionStart();
        		
        		// Clear current selected text.
        		curTab.textArea.replaceSelection("");
        		
        		// Insert the new code to replace the old version.
        		curTab.textArea.insert(code, start);
        		
        		// Restore cursor.
        		curTab.textArea.setCaretPosition(start);
        	}
        	else
        	{
        		curTab.textArea.insert(code, curTab.textArea.getCaretPosition());
        	}
    	}
    	catch(Exception e)
    	{}
    }
    
    public int getCaretLineNumber()
    {
        return curTab.textArea.getCaretLineNumber();
    }
    
    public int getTabCount()
    {
    	// If selected text not empty
    	if(curTab.textArea.getSelectedText() != null)
    	{
    		// Get the selected position.
    		int start = curTab.textArea.getSelectionStart();
    		int end = curTab.textArea.getSelectionEnd();
    		
    		// Get current selected text.
    		String tmpCode = curTab.textArea.getSelectedText();
    		
    		// Clear current selected text.
    		curTab.textArea.replaceSelection("");
    		
    		// Restore cursor.
    		curTab.textArea.setCaretPosition(start);

    		int count = curTab.textArea.getTokenListForLine(getCaretLineNumber()).getLexeme().length();

    		//curTab.textArea.replaceSelection(tmpCode);
    		curTab.textArea.insert(tmpCode, start);
    		
    		// Restore selected action.
    		curTab.textArea.select(start, end);
    		
    		//curTab.textArea.setCaretPosition(start);
    		
    		return count;
    	}
    	else
    	{
    		return curTab.textArea.getTokenListForLine(getCaretLineNumber()).getLexeme().length();
    	}
    	
    	
    	//return curTab.textArea.getTokenListForLine(getCaretLineNumber()).getLexeme().length();
    }
    
    public void requestFocus()
    {
        curTab.textArea.requestFocus();
    }
    
    public String getText()
    {
        return curTab.textArea.getText();
    }
    
    public void select(int end, int start)
    {
        curTab.textArea.select(end, start);
    }
    
    public void replace(String code, int start, int end)
    {
        curTab.textArea.replaceRange(code, start, end);
    }
    
    public void setText(String s)
    {
        curTab.textArea.setText(s);
    }
}
