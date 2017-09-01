package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import view.BDMusicNote;

public class BDMusicNoteCtrl 
{
	public BDMusicNote note;
	
	public BDMusicNoteCtrl(BDMusicNote mscNote)
	{
		this.note = mscNote;
		
		this.note.durt_icon.setOnMouseReleased(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	if(note.curdurt == 1)
            	{
            		setDurt(2);
            	}
            	else
            	{
            		setDurt(1);
            	}
            	
            }
        });
	}
	
	public void setDurt(int val)
	{
		switch(val)
		{
			case 1:
				
				note.durt_icon.setImage(note.rootWindow.icon_durt_1_img);
				note.curdurt = 1;
			
				break;
			
			case 2:
				
				note.durt_icon.setImage(note.rootWindow.icon_durt_2_img);
				note.curdurt = 2;
				
				break;
		}
	}
	
	public void setTune(int val)
	{
		switch(val)
		{
			case 0:
				
				note.msc_icon.setImage(note.rootWindow.icon_0_img);
				note.curTune = 0;
				
				break;
			
			case -1:
				
				note.msc_icon.setImage(note.rootWindow.icon_1L_img);
				note.curTune = -1;
				
				break;
				
			case -2:
				
				note.msc_icon.setImage(note.rootWindow.icon_2L_img);
				note.curTune = -2;
				
				break;
				
			case -3:
				
				note.msc_icon.setImage(note.rootWindow.icon_3L_img);
				note.curTune = -3;
				
				break;
				
			case -4:
				
				note.msc_icon.setImage(note.rootWindow.icon_4L_img);
				note.curTune = -4;
				
				break;
				
			case -5:
				
				note.msc_icon.setImage(note.rootWindow.icon_5L_img);
				note.curTune = -5;
				
				break;
				
			case -6:
				
				note.msc_icon.setImage(note.rootWindow.icon_6L_img);
				note.curTune = -6;
			
				break;
				
			case -7:
				
				note.msc_icon.setImage(note.rootWindow.icon_7L_img);
				note.curTune = -7;
				
				break;
				
			case 1:
				
				note.msc_icon.setImage(note.rootWindow.icon_1_img);
				note.curTune = 1;
				
				break;
				
			case 2:
				
				note.msc_icon.setImage(note.rootWindow.icon_2_img);
				note.curTune = 2;
				
				break;
				
			case 3:
				
				note.msc_icon.setImage(note.rootWindow.icon_3_img);
				note.curTune = 3;
				
				break;
				
			case 4:
				
				note.msc_icon.setImage(note.rootWindow.icon_4_img);
				note.curTune = 4;
				
				break;
				
			case 5:
				
				note.msc_icon.setImage(note.rootWindow.icon_5_img);
				note.curTune = 5;
				
				break;
				
			case 6:
				
				note.msc_icon.setImage(note.rootWindow.icon_6_img);
				note.curTune = 6;
				
				break;
				
			case 7:
				
				note.msc_icon.setImage(note.rootWindow.icon_7_img);
				note.curTune = 7;
				
				break;
			
			case 8:
				
				note.msc_icon.setImage(note.rootWindow.icon_1H_img);
				note.curTune = 8;
				
				break;
				
			case 9:
				
				note.msc_icon.setImage(note.rootWindow.icon_2H_img);
				note.curTune = 9;
				
				break;
				
			case 10:
				
				note.msc_icon.setImage(note.rootWindow.icon_3H_img);
				note.curTune = 10;
				
				break;
				
			case 11:
				
				note.msc_icon.setImage(note.rootWindow.icon_4H_img);
				note.curTune = 11;
				
				break;
				
			case 12:
				
				note.msc_icon.setImage(note.rootWindow.icon_5H_img);
				note.curTune = 12;
				
				break;
				
			case 13:
				
				note.msc_icon.setImage(note.rootWindow.icon_6H_img);
				note.curTune = 13;
				
				break;
				
			case 14:
				
				note.msc_icon.setImage(note.rootWindow.icon_7H_img);
				note.curTune = 14;
				
				break;
		
		}
		
	}
}
