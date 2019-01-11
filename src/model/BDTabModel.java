package model;

import javafx.scene.control.Hyperlink;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BDTabModel 
{
	public Tab tab = new Tab();
    public BDCodeModel code = new BDCodeModel("INO");
    
    public Hyperlink hlink1 = new Hyperlink();
    public Hyperlink hlink2 = new Hyperlink();
    
    // 标签页关闭按钮
    protected Image image1  = new Image("resources/images/iconTabClose1.png");
    protected Image image2  = new Image("resources/images/iconTabClose2.png");
    
    protected ImageView iv1 = new ImageView(image1);
    protected ImageView iv2 = new ImageView(image2);
}
