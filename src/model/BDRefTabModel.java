package model;

import javafx.scene.layout.BorderPane;

public class BDRefTabModel extends BDTabModel 
{
	public BDRefTabModel(BorderPane root)
    {
        // 设置标签标题
        tab.setText("帮助文档");

        tab.setClosable(false);
        
		tab.setContent(root);	
    } 
}
