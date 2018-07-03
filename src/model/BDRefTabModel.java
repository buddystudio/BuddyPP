package model;

import view.BDWebView;

public class BDRefTabModel extends BDTabModel 
{
	public BDRefTabModel(BDWebView refView)
    {
        // 设置标签标题
        tab.setText("帮助文档");
        tab.setClosable(false);
		tab.setContent(refView);	
    } 
}
