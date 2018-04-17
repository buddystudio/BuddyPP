package model;

import javafx.scene.control.Hyperlink;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BDTabModel 
{
	public Tab tab = new Tab();
    public BDCodeModel code = new BDCodeModel();
    
    public Hyperlink hlink1 = new Hyperlink();
    public Hyperlink hlink2 = new Hyperlink();
    
    private Image image1    = new Image("images/iconTabClose1.png");    // 标签页关闭按钮
    private Image image2    = new Image("images/iconTabClose2.png");
    private ImageView iv1   = new ImageView(image1);
    private ImageView iv2   = new ImageView(image2);
}
