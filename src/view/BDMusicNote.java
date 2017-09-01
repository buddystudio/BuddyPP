package view;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class BDMusicNote extends VBox
{
	public BDMusicWindow rootWindow;
	
	public ImageView msc_icon = new ImageView();	// 乐谱显示
	public ImageView durt_icon = new ImageView();	// 节拍控制
	
	public int curTune = 0;
	public int curdurt = 1;

	public BDMusicNote(BDMusicWindow mscWindow)
    {
		rootWindow = mscWindow;
		
		msc_icon.setImage(mscWindow.icon_0_img);
		durt_icon.setImage(mscWindow.icon_durt_1_img);
		
		this.setPadding(new Insets(0, 3, 8, 0));  // 设置边距
		
		this.getChildren().add(msc_icon);
		this.getChildren().add(durt_icon);
    }
	
	
}
