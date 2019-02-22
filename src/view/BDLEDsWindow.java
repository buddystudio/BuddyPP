/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import model.BDLang;

/**
 *
 * @author gsh
 */
public class BDLEDsWindow extends BDSubWindow
{
    public Button submitBtn = new Button(BDLang.rb.getString("生成"));
    public Button cleanBtn = new Button(BDLang.rb.getString("清空"));
    
    public ObservableList<ImageView> btnList = FXCollections.observableArrayList();
    
    public Image ledOn = new Image("resources/images/ledOn.png");
    public Image ledOff = new Image("resources/images/ledOff.png");
    
    public BDLEDsWindow()
    {
        // 窗口初始化
        super.init(340, 380 + 30 + 20);
        
        // 总在最前方
        this.setAlwaysOnTop(true);
       
        // 只有关闭按钮的窗口
        //this.initStyle(StageStyle.UTILITY);
        this.setResizable(false);
        this.setTitle("  " + BDLang.rb.getString("LED阵列工具"));
        this.setScene(scene);
        
        this.setNewTitle(BDLang.rb.getString("LED阵列工具"));
       
        GridPane grid = new GridPane();
       
        VBox contain  = new VBox();
       
        contain.setPadding(new Insets(0,0,15,0));  // 设置边距
        contain.setSpacing(5);                    // 设置间距
       
        contain.setAlignment(Pos.CENTER);
       
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(15, 10, 10, 10));
       
        for(int i = 0; i < 8; i++)
        {
        	for(int j = 0; j < 8; j++)
            {
                ImageView ledBtn = new ImageView(ledOff);

                grid.add(ledBtn, j, i);
                
                btnList.add(ledBtn);

                ledBtn.setOnMouseClicked(new EventHandler<MouseEvent>() 
                {    
                    @Override
                    public void handle(MouseEvent event) 
                    {
                        if(ledBtn.getImage().equals(ledOn))
                        {
                            ledBtn.setImage(ledOff);
                        }
                        else
                        {
                            ledBtn.setImage(ledOn);
                        }
                    }
                });
            }
       }

       submitBtn.setPrefSize(100, 30);
       cleanBtn.setPrefSize(100, 30);
       
       cleanBtn.setStyle("-fx-background-radius: 0, 0;");
       submitBtn.setStyle("-fx-background-radius: 0, 0;");
       
       HBox btnsPanel = new HBox();
       
       btnsPanel.setAlignment(Pos.CENTER);
       btnsPanel.setSpacing(10);
       
       btnsPanel.getChildren().add(cleanBtn);
       btnsPanel.getChildren().add(submitBtn);

       contain.getChildren().add(grid);
       contain.getChildren().add(btnsPanel);

       rootPanel.getChildren().add(contain);
    }
}
