package model;

import view.BDWebView;

public class BDRefTabModel extends BDTabModel 
{
	public BDRefTabModel(BDWebView refView)
    {
        // 设置标签标题
        tab.setText(BDLang.rb.getString("参考文档"));
        tab.setClosable(false);
		tab.setContent(refView);	
    } 
}
