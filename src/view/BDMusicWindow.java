package view;

import controller.BDMusicNoteCtrl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import model.BDLang;

public class BDMusicWindow extends BDWindow
{
	public Button submitBtn = new Button(BDLang.rb.getString("生成"));
    public Button cleanBtn = new Button(BDLang.rb.getString("清空"));
    public Button deleteBtn = new Button(BDLang.rb.getString("删除"));
    
    public ObservableList<BDMusicNoteCtrl> noteList = FXCollections.observableArrayList();
    
    public Image msc_0_normal_img = new Image("resources/images/musicBtn/music_0_btn_normal.jpg");
    public Image msc_0_press_img = new Image("resources/images/musicBtn/music_0_btn_press.jpg");
    
	public Image msc_1L_normal_img = new Image("resources/images/musicBtn/music_1L_btn_normal.jpg");
	public Image msc_2L_normal_img = new Image("resources/images/musicBtn/music_2L_btn_normal.jpg");
	public Image msc_3L_normal_img = new Image("resources/images/musicBtn/music_3L_btn_normal.jpg");
	public Image msc_4L_normal_img = new Image("resources/images/musicBtn/music_4L_btn_normal.jpg");
	public Image msc_5L_normal_img = new Image("resources/images/musicBtn/music_5L_btn_normal.jpg");
	public Image msc_6L_normal_img = new Image("resources/images/musicBtn/music_6L_btn_normal.jpg");
	public Image msc_7L_normal_img = new Image("resources/images/musicBtn/music_7L_btn_normal.jpg");
	
	public Image msc_1_normal_img = new Image("resources/images/musicBtn/music_1_btn_normal.jpg");
	public Image msc_2_normal_img = new Image("resources/images/musicBtn/music_2_btn_normal.jpg");
	public Image msc_3_normal_img = new Image("resources/images/musicBtn/music_3_btn_normal.jpg");
	public Image msc_4_normal_img = new Image("resources/images/musicBtn/music_4_btn_normal.jpg");
	public Image msc_5_normal_img = new Image("resources/images/musicBtn/music_5_btn_normal.jpg");
	public Image msc_6_normal_img = new Image("resources/images/musicBtn/music_6_btn_normal.jpg");
	public Image msc_7_normal_img = new Image("resources/images/musicBtn/music_7_btn_normal.jpg");
	
	public Image msc_1H_normal_img = new Image("resources/images/musicBtn/music_1H_btn_normal.jpg");
	public Image msc_2H_normal_img = new Image("resources/images/musicBtn/music_2H_btn_normal.jpg");
	public Image msc_3H_normal_img = new Image("resources/images/musicBtn/music_3H_btn_normal.jpg");
	public Image msc_4H_normal_img = new Image("resources/images/musicBtn/music_4H_btn_normal.jpg");
	public Image msc_5H_normal_img = new Image("resources/images/musicBtn/music_5H_btn_normal.jpg");
	public Image msc_6H_normal_img = new Image("resources/images/musicBtn/music_6H_btn_normal.jpg");
	public Image msc_7H_normal_img = new Image("resources/images/musicBtn/music_7H_btn_normal.jpg");
	
	public Image msc_1L_press_img = new Image("resources/images/musicBtn/music_1L_btn_press.jpg");
	public Image msc_2L_press_img = new Image("resources/images/musicBtn/music_2L_btn_press.jpg");
	public Image msc_3L_press_img = new Image("resources/images/musicBtn/music_3L_btn_press.jpg");
	public Image msc_4L_press_img = new Image("resources/images/musicBtn/music_4L_btn_press.jpg");
	public Image msc_5L_press_img = new Image("resources/images/musicBtn/music_5L_btn_press.jpg");
	public Image msc_6L_press_img = new Image("resources/images/musicBtn/music_6L_btn_press.jpg");
	public Image msc_7L_press_img = new Image("resources/images/musicBtn/music_7L_btn_press.jpg");
	
	public Image msc_1_press_img = new Image("resources/images/musicBtn/music_1_btn_press.jpg");
	public Image msc_2_press_img = new Image("resources/images/musicBtn/music_2_btn_press.jpg");
	public Image msc_3_press_img = new Image("resources/images/musicBtn/music_3_btn_press.jpg");
	public Image msc_4_press_img = new Image("resources/images/musicBtn/music_4_btn_press.jpg");
	public Image msc_5_press_img = new Image("resources/images/musicBtn/music_5_btn_press.jpg");
	public Image msc_6_press_img = new Image("resources/images/musicBtn/music_6_btn_press.jpg");
	public Image msc_7_press_img = new Image("resources/images/musicBtn/music_7_btn_press.jpg");
	
	public Image msc_1H_press_img = new Image("resources/images/musicBtn/music_1H_btn_press.jpg");
	public Image msc_2H_press_img = new Image("resources/images/musicBtn/music_2H_btn_press.jpg");
	public Image msc_3H_press_img = new Image("resources/images/musicBtn/music_3H_btn_press.jpg");
	public Image msc_4H_press_img = new Image("resources/images/musicBtn/music_4H_btn_press.jpg");
	public Image msc_5H_press_img = new Image("resources/images/musicBtn/music_5H_btn_press.jpg");
	public Image msc_6H_press_img = new Image("resources/images/musicBtn/music_6H_btn_press.jpg");
	public Image msc_7H_press_img = new Image("resources/images/musicBtn/music_7H_btn_press.jpg");
	
	public Image icon_1L_img = new Image("resources/images/musicBtn/icon_1L.jpg");
	public Image icon_2L_img = new Image("resources/images/musicBtn/icon_2L.jpg");
	public Image icon_3L_img = new Image("resources/images/musicBtn/icon_3L.jpg");
	public Image icon_4L_img = new Image("resources/images/musicBtn/icon_4L.jpg");
	public Image icon_5L_img = new Image("resources/images/musicBtn/icon_5L.jpg");
	public Image icon_6L_img = new Image("resources/images/musicBtn/icon_6L.jpg");
	public Image icon_7L_img = new Image("resources/images/musicBtn/icon_7L.jpg");
	
	public Image icon_0_img = new Image("resources/images/musicBtn/icon_0.jpg");
	public Image icon_1_img = new Image("resources/images/musicBtn/icon_1.jpg");
	public Image icon_2_img = new Image("resources/images/musicBtn/icon_2.jpg");
	public Image icon_3_img = new Image("resources/images/musicBtn/icon_3.jpg");
	public Image icon_4_img = new Image("resources/images/musicBtn/icon_4.jpg");
	public Image icon_5_img = new Image("resources/images/musicBtn/icon_5.jpg");
	public Image icon_6_img = new Image("resources/images/musicBtn/icon_6.jpg");
	public Image icon_7_img = new Image("resources/images/musicBtn/icon_7.jpg");
	
	public Image icon_1H_img = new Image("resources/images/musicBtn/icon_1H.jpg");
	public Image icon_2H_img = new Image("resources/images/musicBtn/icon_2H.jpg");
	public Image icon_3H_img = new Image("resources/images/musicBtn/icon_3H.jpg");
	public Image icon_4H_img = new Image("resources/images/musicBtn/icon_4H.jpg");
	public Image icon_5H_img = new Image("resources/images/musicBtn/icon_5H.jpg");
	public Image icon_6H_img = new Image("resources/images/musicBtn/icon_6H.jpg");
	public Image icon_7H_img = new Image("resources/images/musicBtn/icon_7H.jpg");
	
	public Image icon_durt_1_img = new Image("resources/images/musicBtn/msc_durt_1.jpg");
	public Image icon_durt_2_img = new Image("resources/images/musicBtn/msc_durt_2.jpg");
	
	public ImageView msc_0_btn = new ImageView(msc_0_normal_img);
	
	public ImageView msc_1L_btn = new ImageView(msc_1L_normal_img);
	public ImageView msc_2L_btn = new ImageView(msc_2L_normal_img);
	public ImageView msc_3L_btn = new ImageView(msc_3L_normal_img);
	public ImageView msc_4L_btn = new ImageView(msc_4L_normal_img);
	public ImageView msc_5L_btn = new ImageView(msc_5L_normal_img);
	public ImageView msc_6L_btn = new ImageView(msc_6L_normal_img);
	public ImageView msc_7L_btn = new ImageView(msc_7L_normal_img);
	
	public ImageView msc_1_btn = new ImageView(msc_1_normal_img);
	public ImageView msc_2_btn = new ImageView(msc_2_normal_img);
	public ImageView msc_3_btn = new ImageView(msc_3_normal_img);
	public ImageView msc_4_btn = new ImageView(msc_4_normal_img);
	public ImageView msc_5_btn = new ImageView(msc_5_normal_img);
	public ImageView msc_6_btn = new ImageView(msc_6_normal_img);
	public ImageView msc_7_btn = new ImageView(msc_7_normal_img);
	
	public ImageView msc_1H_btn = new ImageView(msc_1H_normal_img);
	public ImageView msc_2H_btn = new ImageView(msc_2H_normal_img);
	public ImageView msc_3H_btn = new ImageView(msc_3H_normal_img);
	public ImageView msc_4H_btn = new ImageView(msc_4H_normal_img);
	public ImageView msc_5H_btn = new ImageView(msc_5H_normal_img);
	public ImageView msc_6H_btn = new ImageView(msc_6H_normal_img);
	public ImageView msc_7H_btn = new ImageView(msc_7H_normal_img);
	
	public VBox contain  = new VBox();
	public HBox paino = new HBox();
	//public HBox book = new HBox();
	public FlowPane book = new FlowPane();
	public HBox btnBar = new HBox();
	
	public BDMusicWindow()
    {
		// 窗口初始化
        super.init(640, 485);
        
        // 总在最前方
        this.setAlwaysOnTop(true);
       
        // 只有关闭按钮的窗口
        this.initStyle(StageStyle.UTILITY);
       
        this.setResizable(false);
       
        this.setTitle("  " + BDLang.rb.getString("谱曲工具"));
        this.setScene(scene);

        contain.setPadding(new Insets(0, 0, 5, 0));  // 设置边距
        contain.setSpacing(5);
        
        book.setPrefSize(640, 365);
        book.setStyle("-fx-background-color:#999999;");
        book.setPadding(new Insets(15, 10, 10, 18));  // 设置边距
        
        btnBar.setSpacing(5);
        btnBar.setAlignment(Pos.CENTER);
        btnBar.setPadding(new Insets(10,0,5,0));  // 设置边距
        
        paino.setPadding(new Insets(20,0,10,0));  // 设置边距
        paino.setSpacing(3);  
        paino.setAlignment(Pos.CENTER);
        
        paino.getChildren().add(msc_0_btn);
        paino.getChildren().add(msc_1L_btn);
        paino.getChildren().add(msc_2L_btn);
        paino.getChildren().add(msc_3L_btn);
        paino.getChildren().add(msc_4L_btn);
        paino.getChildren().add(msc_5L_btn);
        paino.getChildren().add(msc_6L_btn);
        paino.getChildren().add(msc_7L_btn);
        paino.getChildren().add(msc_1_btn);
        paino.getChildren().add(msc_2_btn);
        paino.getChildren().add(msc_3_btn);
        paino.getChildren().add(msc_4_btn);
        paino.getChildren().add(msc_5_btn);
        paino.getChildren().add(msc_6_btn);
        paino.getChildren().add(msc_7_btn);
        paino.getChildren().add(msc_1H_btn);
        paino.getChildren().add(msc_2H_btn);
        paino.getChildren().add(msc_3H_btn);
        paino.getChildren().add(msc_4H_btn);
        paino.getChildren().add(msc_5H_btn);
        paino.getChildren().add(msc_6H_btn);
        paino.getChildren().add(msc_7H_btn);
        
        btnBar.getChildren().add(deleteBtn);
        btnBar.getChildren().add(cleanBtn);
        btnBar.getChildren().add(submitBtn);
        
        cleanBtn.setPrefSize(100, 30);
        submitBtn.setPrefSize(100, 30);
        deleteBtn.setPrefSize(100, 30);
        
        deleteBtn.setStyle("-fx-background-radius: 0, 0;");
        cleanBtn.setStyle("-fx-background-radius: 0, 0;");
        submitBtn.setStyle("-fx-background-radius: 0, 0;");
        
        contain.getChildren().add(paino);
        contain.getChildren().add(book);
        contain.getChildren().add(btnBar);
        
        rootPanel.getChildren().add(contain);
    }
}
