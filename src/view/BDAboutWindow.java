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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import model.BDLang;
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
        super.init(500, 365);

        //scene.getStylesheets().add("resources/style/settingStyle.css");
        rootPanel.getStylesheets().clear();
       
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
       
        this.setTitle("  " + BDLang.rb.getString("关于"));
        this.setScene(scene);
       
        VBox contain  = new VBox();
       
        contain.setAlignment(Pos.TOP_CENTER);
       
        Image imgAboutUs = new Image("resources/images/about_us.jpg");
       
        ImageView iv2 = new ImageView(imgAboutUs);
       
        contain.getChildren().add(iv2);
       
        Label copyrightTxt = new Label();
        Label versionTxt = new Label();
        Label authorTxt = new Label();
        Label licenseTxt = new Label();
        
        Label line01Txt = new Label();
        Label line02Txt = new Label();
        Label line03Txt = new Label();
        
        Hyperlink linkTxt = new Hyperlink();
        Hyperlink linkTxt2 = new Hyperlink();  
        
        linkTxt.setText("http://buddy.studio");
        linkTxt2.setText("http://buddy.org.cn");
        
        linkTxt.setOnAction((ActionEvent e) -> 
        {  
        	// Visit buddyStudio webSite.
        	this.browserUrl("http://buddy.studio");
        });
        
        linkTxt2.setOnAction((ActionEvent e) -> 
        {  
        	// Visit buddyStudio webSite.
        	this.browserUrl("http://buddy.org.cn");
        });  
       
        //copyrightTxt.setTextFill(Color.web("#ff0000"));  
        copyrightTxt.setTextFill(Color.web("#333333"));  
        versionTxt.setTextFill(Color.web("#333333"));  
        authorTxt.setTextFill(Color.web("#333333"));
        licenseTxt.setTextFill(Color.web("#333333"));
        
        if(BDParameters.os.equals("Mac OS X"))
        {
        	line01Txt.setPadding(new Insets(18, 0, 0, 0));
            line02Txt.setPadding(new Insets(5, 0, 0, 0));
            line03Txt.setPadding(new Insets(5, 0, 10, 0));
            licenseTxt.setPadding(new Insets(5, 0, 0, 0));
            //linkTxt.setPadding(new Insets(10, 0, 0, 0));
        }
        else
        {
        	line01Txt.setPadding(new Insets(17, 0, 0, 0));
            line02Txt.setPadding(new Insets(8, 0, 0, 0));
            line03Txt.setPadding(new Insets(8, 0, 10, 0));
            licenseTxt.setPadding(new Insets(8, 0, 0, 0));
            //linkTxt.setPadding(new Insets(10, 0, 0, 0));
        }

        line01Txt.setText(BDLang.rb.getString("本软件由") + " " + BDParameters.organization + " " + BDLang.rb.getString("开发、调试及发布。"));
        line02Txt.setText(BDLang.rb.getString("本软件遵从GPL协议开放源代码，在遵照指定约束条件下您"));
        line03Txt.setText(BDLang.rb.getString("可以自由传播和修改。当前版本：") + bitTxt + "-" + BDParameters.version);
       
        contain.getChildren().add(line01Txt);
        //contain.getChildren().add(authorTxt);
        contain.getChildren().add(line02Txt);
        contain.getChildren().add(line03Txt);
        //contain.getChildren().add(linkTxt);
        //contain.getChildren().add(linkTxt2);
        
        HBox links = new HBox();
        
        links.setAlignment(Pos.CENTER);
        links.setSpacing(10);
        links.getChildren().addAll(linkTxt, linkTxt2);
        
        contain.getChildren().add(links);
       
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