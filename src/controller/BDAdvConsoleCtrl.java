package controller;

import view.BDAdvConsoleView;

public class BDAdvConsoleCtrl
{
	private BDAdvConsoleView acv = null;
	private int lineCount = 1;
	private boolean isCounting = true;
	private String html = "";
	private String htmlHead = "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"></head><body onload='window.scrollTo(0,document.body.scrollHeight);' style='margin:0;overflow-x:hidden;'><table onselectstart='return false' onselect='document.selection.empty()' border='0' cellpadding='10' cellspacing='0' style='overflow-y:hidden;'>";
	private String htmlFoot = "</table></body></html>";

	public BDAdvConsoleCtrl(BDAdvConsoleView acv)
	{
		this.acv = acv;
	}
	
	public void updateMessageByLine(String msg)
	{
		String script = "onmouseover=\"this.style.backgroundColor='" + this.acv.getColorOnMouseOver() + "'\"  onmouseout=\"this.style.backgroundColor='" + this.acv.getColorOnMouseOut() + "'\" ";
        //String css = "style = 'font-size:18px;line-height:18px;color:blue;visibility:hidden;'";
        String css = "style = 'color:" + this.acv.getColorMsgFont()+ ";'";
        
        // ������ʽ
		//html += "<tr><td  width='20px' align='middle' bgcolor='DDDDDD'><font color='#656565'><i>" + lineCount + "</i></td><td " + script + css + "><pre>" + msg + "</pre></td></tr>";
        
        // ������ʽ���Զ����У�
        //html += "<tr><td  width='20px' align='middle' bgcolor='DDDDDD'><font color='#656565'><i>" + lineCount + "</i></td><td " + script + css + "><pre style = 'white-space:pre-wrap;'>" + msg + "</pre></td></tr>";
		
        // ������ʽ�����Զ����У�
        html += "<tr style='line-height:" + this.acv.getMsgLineHight() + "px;'><td width='20px' align='middle' bgcolor='" + this.acv.getColorLineBlockBg() + "'><font color='" + this.acv.getColorLineFont() + "'><i>" + lineCount + "</i></td><td " + script + css + "><pre style = 'font-size:" + this.acv.getMsgFontSize() + "px;'>" + msg + "</pre></td></tr>";
		
        this.acv.getHtmlEditor().setHtmlText(htmlHead + html + htmlFoot);
		
		this.acv.getWebEngine().loadContent(this.acv.getHtmlEditor().getHtmlText());
		
		if(this.isCounting == true)
		{
			lineCount++;
		}
	}
	
	public void updateMessage()
	{
		this.acv.getHtmlEditor().setHtmlText(html);
		this.acv.getWebEngine().loadContent(this.acv.getHtmlEditor().getHtmlText());
		
		if(this.isCounting == true)
		{
			//lineCount++;
		}
	}
	
	public void clear()
	{
		this.setHtml("");
		
		this.updateMessage();
	}

	public BDAdvConsoleView getAcv() {
		return acv;
	}

	public void setAcv(BDAdvConsoleView acv) {
		this.acv = acv;
	}

	public int getLineCount() {
		return lineCount;
	}

	public void setLineCount(int lineCount) {
		this.lineCount = lineCount;
	}

	public boolean isCounting() {
		return isCounting;
	}

	public void setCounting(boolean isCounting) {
		this.isCounting = isCounting;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

}
