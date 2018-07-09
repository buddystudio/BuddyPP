package model;

import view.BDWebView;

public class BDDocTabModel extends BDTabModel 
{
	public BDDocTabModel(BDWebView refView)
    {
        // 设置标签标题
        tab.setText("使用手册");
        tab.setClosable(false);
		tab.setContent(refView);	
    } 
}