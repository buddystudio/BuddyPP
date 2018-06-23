package model;

import javafx.scene.Cursor;
import javafx.scene.layout.BorderPane;

public class BDMsgTabModel extends BDTabModel 
{
	public BDMsgTabModel(BorderPane root)
    {
        // 设置标签标题
        tab.setText("控制台");

        //hlink1.setGraphic(iv1);
        //hlink1.setFocusTraversable(false);
        //hlink1.setCursor(Cursor.DEFAULT);
        
        //hlink2.setGraphic(iv2);
        //hlink2.setFocusTraversable(false);
        //hlink2.setCursor(Cursor.DEFAULT);
        //hlink1.setMouseTransparent(true);

        //tab.setGraphic(iv1);
        //tab.setGraphic(hlink1);
        tab.setClosable(false);
        
		tab.setContent(root);	
    } 
}
