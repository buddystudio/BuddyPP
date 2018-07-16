package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import view.BDMusicNote;
import view.BDMusicWindow;

public class BDMusicWindowCtrl 
{
	BDMusicWindow rootWindow;
	
	public BDMusicWindowCtrl (BDMusicWindow musicWindow, BDWorkspaceCtrl workspaceCtrl)
	{
		this.rootWindow = musicWindow;
		
		// Make the code.
		musicWindow.submitBtn.setOnMousePressed(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	// 获取词位
                int tabCount = workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.getCurColumn() - 1;
                 
                String tabTxt = "";
                
                // 添加缩进
                for(int i = 0; i < tabCount; i++)
                {
                    tabTxt += " ";
                }
                
                String code2  = tabTxt + "int tune[] = {";
                String code1  = "float durt[] = {";
                 
                String result1 = "";
                String result2 = "";
                
                // Traversing the noteList.
                for(int i = 0; i < musicWindow.noteList.size(); i++)
                {
                	 // Set the durt.
                	 if(musicWindow.noteList.get(i).note.curdurt == 1)
                	 {
                		 result1 += "1";
                	 }
                	 else
                	 {
                		 result1 += "0.5";
                	 }
                	 
                	 int tune = musicWindow.noteList.get(i).note.curTune;
                	 
                	 // Set the tune.
                	 if(tune == 0)
                	 {
                		 result2 += "NTD0";
                	 }
                	 else if(tune == -1)
                	 {
                		 result2 += "NTDL1";
                	 }
                	 else if(tune == -2)
                	 {
                		 result2 += "NTDL2";
                	 }
                	 else if(tune == -3)
                	 {
                		 result2 += "NTDL3";
                	 }
                	 else if(tune == -4)
                	 {
                		 result2 += "NTDL4";
                	 }
                	 else if(tune == -5)
                	 {
                		 result2 += "NTDL5";
                	 }
                	 else if(tune == -6)
                	 {
                		 result2 += "NTDL6";
                	 }
                	 else if(tune == -7)
                	 {
                		 result2 += "NTDL7";
                	 }
                	 if(tune == 1)
                	 {
                		 result2 += "NTD1";
                	 }
                	 if(tune == 2)
                	 {
                		 result2 += "NTD2";
                	 }
                	 if(tune == 3)
                	 {
                		 result2 += "NTD3";
                	 }
                	 if(tune == 4)
                	 {
                		 result2 += "NTD4";
                	 }
                	 if(tune == 5)
                	 {
                		 result2 += "NTD5";
                	 }
                	 if(tune == 6)
                	 {
                		 result2 += "NTD6";
                	 }
                	 if(tune == 7)
                	 {
                		 result2 += "NTD7";
                	 }
                	 if(tune == 8)
                	 {
                		 result2 += "NTDH1";
                	 }
                	 if(tune == 9)
                	 {
                		 result2 += "NTDH2";
                	 }
                	 if(tune == 10)
                	 {
                		 result2 += "NTDH3";
                	 }
                	 if(tune == 11)
                	 {
                		 result2 += "NTDH4";
                	 }
                	 if(tune == 12)
                	 {
                		 result2 += "NTDH5";
                	 }
                	 if(tune == 13)
                	 {
                		 result2 += "NTDH6";
                	 }
                	 if(tune == 14)
                	 {
                		 result2 += "NTDH7";
                	 }
                	 
                	 if(i != (musicWindow.noteList.size() - 1))
                	 {
                		 result1 += ", ";
                		 result2 += ", ";
                	 }
                 }
                
                 // End the code.
                 code1 = code1 + result1 + "};";
                 code2 = code2 + result2 + "};";
                 
                 // Insert the code.
                 //codeAgent.insert(code2 + "\n" + code1);
                 
                 
                 
                 // 插入语句
                 code1 = code1.replaceAll("\"","\\\\\"");
                 code2 = code2.replaceAll("\"","\\\\\"");
                 
                 workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.insert(code2 + "\\n" + code1);
                 
                 // Close the window.
                 rootWindow.close();
            }
        });
		
		// Discard all notes.
		musicWindow.cleanBtn.setOnMousePressed(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	// Clear all notes.
            	rootWindow.noteList.clear();
            	
            	// Update all notes.
            	updateNotes();
            }
        });
		
		// Delete the last note.
		musicWindow.deleteBtn.setOnMousePressed(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	// Delete the last note.
            	if(rootWindow.noteList.size() > 0)
            	{
            		rootWindow.noteList.remove(rootWindow.noteList.size() - 1);
            	
            		// Update all notes.
                	updateNotes();
            	}
            }
        });
		
		// 0
		musicWindow.msc_0_btn.setOnMousePressed(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_0_btn.setImage(musicWindow.msc_0_press_img);
            }
        });
		
		musicWindow.msc_0_btn.setOnMouseReleased(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_0_btn.setImage(musicWindow.msc_0_normal_img);
            	
            	// Add one note.
            	addNote(0, 1);
            	
            	// Update all notes.
            	updateNotes();
            }
        });
		
		// -1
		musicWindow.msc_1L_btn.setOnMousePressed(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_1L_btn.setImage(musicWindow.msc_1L_press_img);
            }
        });
		
		musicWindow.msc_1L_btn.setOnMouseReleased(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_1L_btn.setImage(musicWindow.msc_1L_normal_img);
            	
            	// Add one note.
            	addNote(-1, 1);
            	
            	// Update all notes.
            	updateNotes();
            }
        });
		
		// -2
		musicWindow.msc_2L_btn.setOnMousePressed(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_2L_btn.setImage(musicWindow.msc_2L_press_img);
            }
        });
		
		musicWindow.msc_2L_btn.setOnMouseReleased(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_2L_btn.setImage(musicWindow.msc_2L_normal_img);
            	
            	// Add one note.
            	addNote(-2, 1);
            	
            	// Update all notes.
            	updateNotes();
            }
        });	
		
		// -3
		musicWindow.msc_3L_btn.setOnMousePressed(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_3L_btn.setImage(musicWindow.msc_3L_press_img);
            }
        });
		
		musicWindow.msc_3L_btn.setOnMouseReleased(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_3L_btn.setImage(musicWindow.msc_3L_normal_img);
            	
            	// Add one note.
            	addNote(-3, 1);
            	
            	// Update all notes.
            	updateNotes();
            }
        });
		
		// -4
		musicWindow.msc_4L_btn.setOnMousePressed(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_4L_btn.setImage(musicWindow.msc_4L_press_img);
            }
        });
		
		musicWindow.msc_4L_btn.setOnMouseReleased(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_4L_btn.setImage(musicWindow.msc_4L_normal_img);
            	
            	// Add one note.
            	addNote(-4, 1);
            	
            	// Update all notes.
            	updateNotes();
            }
        });
		
		// -5
		musicWindow.msc_5L_btn.setOnMousePressed(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_5L_btn.setImage(musicWindow.msc_5L_press_img);
            }
        });
		
		musicWindow.msc_5L_btn.setOnMouseReleased(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_5L_btn.setImage(musicWindow.msc_5L_normal_img);
            	
            	// Add one note.
            	addNote(-5, 1);
            	
            	// Update all notes.
            	updateNotes();
            }
        });
		
		// -6
		musicWindow.msc_6L_btn.setOnMousePressed(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_6L_btn.setImage(musicWindow.msc_6L_press_img);
            }
        });
		
		musicWindow.msc_6L_btn.setOnMouseReleased(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_6L_btn.setImage(musicWindow.msc_6L_normal_img);
            	
            	// Add one note.
            	addNote(-6, 1);
            	
            	// Update all notes.
            	updateNotes();
            }
        });
		
		// -7
		musicWindow.msc_7L_btn.setOnMousePressed(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_7L_btn.setImage(musicWindow.msc_7L_press_img);
            }
        });
		
		musicWindow.msc_7L_btn.setOnMouseReleased(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_7L_btn.setImage(musicWindow.msc_7L_normal_img);
            	
            	// Add one note.
            	addNote(-7, 1);
            	
            	// Update all notes.
            	updateNotes();
            }
        });
		
		// 1
		musicWindow.msc_1_btn.setOnMousePressed(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_1_btn.setImage(musicWindow.msc_1_press_img);
            }
        });
		
		musicWindow.msc_1_btn.setOnMouseReleased(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_1_btn.setImage(musicWindow.msc_1_normal_img);
            	
            	// Add one note.
            	addNote(1, 1);
            	
            	// Update all notes.
            	updateNotes();
            }
        });
		
		// 2
		musicWindow.msc_2_btn.setOnMousePressed(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_2_btn.setImage(musicWindow.msc_2_press_img);
            }
        });
		
		musicWindow.msc_2_btn.setOnMouseReleased(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_2_btn.setImage(musicWindow.msc_2_normal_img);
            	
            	// Add one note.
            	addNote(2, 1);
            	
            	// Update all notes.
            	updateNotes();
            }
        });
		
		// 3
		musicWindow.msc_3_btn.setOnMousePressed(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_3_btn.setImage(musicWindow.msc_3_press_img);
            }
        });
		
		musicWindow.msc_3_btn.setOnMouseReleased(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_3_btn.setImage(musicWindow.msc_3_normal_img);
            	
            	// Add one note.
            	addNote(3, 1);
            	
            	// Update all notes.
            	updateNotes();
            }
        });
		
		// 4
		musicWindow.msc_4_btn.setOnMousePressed(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_4_btn.setImage(musicWindow.msc_4_press_img);
            }
        });
		
		musicWindow.msc_4_btn.setOnMouseReleased(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_4_btn.setImage(musicWindow.msc_4_normal_img);
            	
            	// Add one note.
            	addNote(4, 1);
            	
            	// Update all notes.
            	updateNotes();
            }
        });
		
		// 5
		musicWindow.msc_5_btn.setOnMousePressed(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_5_btn.setImage(musicWindow.msc_5_press_img);
            }
        });
		
		musicWindow.msc_5_btn.setOnMouseReleased(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_5_btn.setImage(musicWindow.msc_5_normal_img);
            	
            	// Add one note.
            	addNote(5, 1);
            	
            	// Update all notes.
            	updateNotes();
            }
        });
		
		// 6
		musicWindow.msc_6_btn.setOnMousePressed(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_6_btn.setImage(musicWindow.msc_6_press_img);
            }
        });
		
		musicWindow.msc_6_btn.setOnMouseReleased(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_6_btn.setImage(musicWindow.msc_6_normal_img);
            	
            	// Add one note.
            	addNote(6, 1);
            	
            	// Update all notes.
            	updateNotes();
            }
        });
		
		// 7
		musicWindow.msc_7_btn.setOnMousePressed(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_7_btn.setImage(musicWindow.msc_7_press_img);
            }
        });
		
		musicWindow.msc_7_btn.setOnMouseReleased(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_7_btn.setImage(musicWindow.msc_7_normal_img);
            	
            	// Add one note.
            	addNote(7, 1);
            	
            	// Update all notes.
            	updateNotes();
            }
        });

		// 8
		musicWindow.msc_1H_btn.setOnMousePressed(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_1H_btn.setImage(musicWindow.msc_1H_press_img);
            }
        });
		
		musicWindow.msc_1H_btn.setOnMouseReleased(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_1H_btn.setImage(musicWindow.msc_1H_normal_img);
            	
            	// Add one note.
            	addNote(8, 1);
            	
            	// Update all notes.
            	updateNotes();
            }
        });
		
		// 9
		musicWindow.msc_2H_btn.setOnMousePressed(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_2H_btn.setImage(musicWindow.msc_2H_press_img);
            }
        });
		
		musicWindow.msc_2H_btn.setOnMouseReleased(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_2H_btn.setImage(musicWindow.msc_2H_normal_img);
            	
            	// Add one note.
            	addNote(9, 1);
            	
            	// Update all notes.
            	updateNotes();
            }
        });
		
		// 10
		musicWindow.msc_3H_btn.setOnMousePressed(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_3H_btn.setImage(musicWindow.msc_3H_press_img);
            }
        });
		
		musicWindow.msc_3H_btn.setOnMouseReleased(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_3H_btn.setImage(musicWindow.msc_3H_normal_img);
            	
            	// Add one note.
            	addNote(10, 1);
            	
            	// Update all notes.
            	updateNotes();
            }
        });
		
		// 11
		musicWindow.msc_4H_btn.setOnMousePressed(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_4H_btn.setImage(musicWindow.msc_4H_press_img);
            }
        });
		
		musicWindow.msc_4H_btn.setOnMouseReleased(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_4H_btn.setImage(musicWindow.msc_4H_normal_img);
            	
            	// Add one note.
            	addNote(11, 1);
            	
            	// Update all notes.
            	updateNotes();
            }
        });
		
		// 12
		musicWindow.msc_5H_btn.setOnMousePressed(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_5H_btn.setImage(musicWindow.msc_5H_press_img);
            }
        });
		
		musicWindow.msc_5H_btn.setOnMouseReleased(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_5H_btn.setImage(musicWindow.msc_5H_normal_img);
            	
            	// Add one note.
            	addNote(12, 1);
            	
            	// Update all notes.
            	updateNotes();
            }
        });
		
		// 13
		musicWindow.msc_6H_btn.setOnMousePressed(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_6H_btn.setImage(musicWindow.msc_6H_press_img);
            }
        });
		
		musicWindow.msc_6H_btn.setOnMouseReleased(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_6H_btn.setImage(musicWindow.msc_6H_normal_img);
            	
            	// Add one note.
            	addNote(13, 1);
            	
            	// Update all notes.
            	updateNotes();
            }
        });
		
		// 14
		musicWindow.msc_7H_btn.setOnMousePressed(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_7H_btn.setImage(musicWindow.msc_7H_press_img);
            }
        });
		
		musicWindow.msc_7H_btn.setOnMouseReleased(new EventHandler<MouseEvent>() 
        {    
            @Override
            public void handle(MouseEvent event) 
            {
            	musicWindow.msc_7H_btn.setImage(musicWindow.msc_7H_normal_img);
            	
            	// Add one note.
            	addNote(14, 1);
            	
            	// Update all notes.
            	updateNotes();
            }
        });
		

	}
	
	public void addNote(int tune, int durt)
	{
		// Max = 80.
		if(this.rootWindow.noteList.size() >= 80)
		{
			return;
			
		}
		
		BDMusicNote mscNote = new BDMusicNote(this.rootWindow);
        BDMusicNoteCtrl mscNoteCtrl = new BDMusicNoteCtrl(mscNote);
        
        mscNoteCtrl.setTune(tune);
        mscNoteCtrl.setDurt(durt);
        
        this.rootWindow.noteList.add(mscNoteCtrl);
		
	}
	
	public void updateNotes()
	{
		// Clear all notes.
		this.rootWindow.book.getChildren().clear();
		
		// Update all notes.
		for (BDMusicNoteCtrl noteCtrl:this.rootWindow.noteList) 
        {   
			this.rootWindow.book.getChildren().add(noteCtrl.note);
        }  
		
	}
}
