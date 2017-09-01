/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import model.BDParameters;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author gsh
 */
public final class BDAboutWindow extends BDWindow
{
    public BDAboutWindow()
    {
        // 窗口初始化
        super.init(500, 350);

        scene.getStylesheets().add("style/settingStyle.css");
       
        // 定义无边框窗体
        //this.initStyle(StageStyle.UNDECORATED);
        
        // 总在最前方
        this.setAlwaysOnTop(true);
       
        // 只有关闭按钮的窗口
        this.initStyle(StageStyle.UTILITY);
        this.setResizable(false);
        
        String bitTxt = "";

        if(BDParameters.arch.contains("64"))
        {
        	bitTxt = "x64";
        }
        else
        {
        	bitTxt = "x86";
        }
       
        BDTitleView  titlePanel  = new BDTitleView();
       
        this.setTitle("  关于");
        this.setScene(scene);
       
        VBox contain  = new VBox();
       
        //contain.getChildren().add(titlePanel);
       
        contain.setAlignment(Pos.TOP_CENTER);
       
        Image imgAboutUs = new Image("/images/about_us.jpg");
       
        ImageView iv2 = new ImageView(imgAboutUs);
       
        contain.getChildren().add(iv2);
       
        Label copyrightTxt = new Label();
        Label versionTxt = new Label();
        Label authorTxt = new Label();
        Label licenseTxt = new Label();
        
        Hyperlink linkTxt = new Hyperlink();  
        
        linkTxt.setText("http://buddy.studio"); 
        
        linkTxt.setOnAction((ActionEvent e) -> 
        {  
        	// Visit buddyStudio webSite.
        	this.browserUrl("http://buddy.studio");
        });  
       
        //copyrightTxt.setTextFill(Color.web("#ff0000"));  
        copyrightTxt.setTextFill(Color.web("#333333"));  
        versionTxt.setTextFill(Color.web("#333333"));  
        authorTxt.setTextFill(Color.web("#333333"));
        licenseTxt.setTextFill(Color.web("#333333"));
       
        copyrightTxt.setPadding(new Insets(18, 0, 0, 0));
        authorTxt.setPadding(new Insets(8, 0, 0, 0));
        versionTxt.setPadding(new Insets(8, 0, 10, 0));
        licenseTxt.setPadding(new Insets(8, 0, 0, 0));
        //linkTxt.setPadding(new Insets(10, 0, 0, 0));
       
        copyrightTxt.setText("本软件由广州梦车间信息科技有限公司旗下 BuddyStudio 工作室");
        //copyrightTxt.setText("Copyright © 2013-2015 广州梦车间信息科技有限公司 All Rights Reserved.");
        versionTxt.setText("可以自由传播和修改。  当前版本：" + bitTxt + "-" + BDParameters.version);
        //authorTxt.setText("本软件由 李宝智、郭少豪 开发、调试及发布");
        licenseTxt.setText("开发、调试及发布。本软件遵从GPL协议开放源代码，在遵照指定的约束条件下您");
       
        contain.getChildren().add(copyrightTxt);
        //contain.getChildren().add(authorTxt);
        contain.getChildren().add(licenseTxt);
        contain.getChildren().add(versionTxt);
        contain.getChildren().add(linkTxt);
       
        rootPanel.getChildren().add(contain);
    }
    
    public void browserUrl(String url)
    {
    	 
        // 判断是否支持Desktop扩展,如果支持则进行下一步
        if (Desktop.isDesktopSupported())
        {
            try 
            {
                URI uri = new URI(url);
                
                Desktop desktop = Desktop.getDesktop(); //创建desktop对象
                
                // 调用默认浏览器打开指定URL
                desktop.browse(uri);
 
            } 
            catch (URISyntaxException e) 
            {
                e.printStackTrace();
 
            } 
            catch (IOException e) 
            {
                // 如果没有默认浏览器时，将引发下列异常
                e.printStackTrace();
            }
        }
    }
}

