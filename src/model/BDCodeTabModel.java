/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import controller.BDAssistantMenuCtrl;
import controller.BDWorkspaceCtrl;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import view.BDAssistantMenuView;

/**
 *
 * @author gsh
 */
public class BDCodeTabModel
{
	private static final Logger logger=LogManager.getLogger();
    public Tab tab = new Tab();
    public BDCodeModel code = new BDCodeModel();
    
    public RSyntaxTextArea textArea;
    public RTextScrollPane spp = new RTextScrollPane(textArea);
    
    public Hyperlink hlink1 = new Hyperlink();
    public Hyperlink hlink2 = new Hyperlink();
    
    private Image image1    = new Image("images/iconTabClose1.png");    // 标签页关闭按钮
    private Image image2    = new Image("images/iconTabClose2.png");
    private ImageView iv1   = new ImageView(image1);
    private ImageView iv2   = new ImageView(image2);
    
    public SwingNode sn = new SwingNode();
    
    // 右键菜单中添加代码助手功能
    public BDAssistantMenuView assistantMenu;
                
    // 创建菜单控制器
    public BDAssistantMenuCtrl assistantMenuCtrl;
    
    public BDCodeTabModel(BDCodeModel code, BDWorkspaceCtrl workspaceCtrl) throws AWTException
    {
        //spp = new RTextScrollPane(textArea);
        
        assistantMenu = new BDAssistantMenuView("代码助手", workspaceCtrl);
                
        // 创建菜单控制器
        assistantMenuCtrl = new BDAssistantMenuCtrl(assistantMenu);
    
        this.code = code;

        // 设置标签标题
        tab.setText(this.code.getName());

        hlink1.setGraphic(iv1);
        hlink1.setFocusTraversable(false);
        hlink1.setCursor(Cursor.DEFAULT);
        
        hlink2.setGraphic(iv2);
        hlink2.setFocusTraversable(false);
        hlink2.setCursor(Cursor.DEFAULT);
        //hlink1.setMouseTransparent(true);

        //tab.setGraphic(iv1);
        tab.setGraphic(hlink1);
        
        tab.setClosable(false);
        
        //SwingNode sn = new SwingNode();
        
        //sn.setBackground(java.awt.Color.RED);
        
        tab.setContent(sn);

        //sn.setStyle("-fx-background-color: #ffffff;"); 
        
        //SwingUtilities.invokeAndWait(null);
        
        //Platform.runLater(new Runnable() {
        //SwingUtilities.invokeLater(new Runnable() 
//        Platform.runLater(new Runnable()
//        {
//            @Override
//            public void run() 
//            {
//                try {
//                    this.wait(1000);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(BDCodeTabModel.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                
//                
//            }
//       });
        
       {//----
           //tab.getContent().setFocusTraversable(false);
                //swingNode.setContent(new JButton("Click me!"));
                
                //RSyntaxTextArea textArea = new RSyntaxTextArea(20, 60);
                textArea = new RSyntaxTextArea(20, 60);
                spp = new RTextScrollPane(textArea);
                
                // 代码规则选用Arduino
                textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_ARDUINO);
                //textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
                
                // 设置代码编辑区文本字体与大小
                textArea.setFont(new Font(BDParameters.txtAreafont, Font.PLAIN, BDParameters.txtAreafontSize));
                
                // 代码可折叠
                textArea.setCodeFoldingEnabled(true);
                
                // 抗锯齿
                textArea.setAntiAliasingEnabled(true);
                      
                // 设定制表符的长度
                textArea.setTabSize(4);

                textArea.getPopupMenu().add(assistantMenu);
                
                //System.out.println(textArea.getPopupMenu());
                
                spp = new RTextScrollPane(textArea);

                try
                {
                     textArea.setText(code.getCodeText());
                }
                catch(Exception e)
                {
                	logger.error("",e);
                }

                spp.setBorder(javax.swing.BorderFactory.createEmptyBorder());
                
                spp.setVisible(true);
                
                //spp.setBackground(java.awt.Color.RED);
                
                sn.setContent(spp);
                sn.getContent().setBackground(Color.red);

                /*
                Robot robby = new Robot();

                try
                {
                    
                    robby.delay(1000);
                    robby.keyPress(9);
                    robby.keyRelease(9);
                }
                catch(Exception e)
                {
                
                }
                    */
                
                // 文本内容监控（插入/删除/改变）时触发
                textArea.getDocument().addDocumentListener(new DocumentListener() 
                {
                    @Override
                    public void insertUpdate(DocumentEvent e) 
                    {
                        //System.out.println(tab.getText() + " insert");
                        
                        code.isSaved = false;
                        code.isCompiled=false;
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) 
                    {
                        //System.out.println(tab.getText() + " remove");
                        
                        code.isSaved = false;
                         code.isCompiled=false;
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) 
                    {
                        //System.out.println(tab.getText() + " changed");
                        
                        code.isSaved = false;
                         code.isCompiled=false;
                    }
                });

                
                textArea.addFocusListener(new FocusAdapter() 
                {
                    @Override
                    public void focusGained(final FocusEvent arg0) 
                    {
                        //System.out.println(workspaceCtrl.workspaceView.workspaceModel.curTab.tab.getText() + " foucs!!!!!");
                        //textArea.requestFocus();
                        
                        spp.updateUI(); 
                    }

                    @Override
                    public void focusLost(FocusEvent e) 
                    {
                        //System.out.println(workspaceCtrl.workspaceView.workspaceModel.curTab.tab.getText() + " lost foucs!!!!!");
                        
                        if(tab.equals(workspaceCtrl.workspaceView.workspaceModel.curTab.tab))
                        {
                            //workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.requestFocusInWindow();
                            
                            // 延时获得焦点
                            final Timeline animation = new Timeline(
                                        new KeyFrame(Duration.millis(25),
                                        new EventHandler<ActionEvent>() 
                                        {
                                            @Override
                                            public void handle(ActionEvent actionEvent) 
                                            {
                                                Platform.runLater(new Runnable() 
                                                {
                                                    @Override
                                                    public void run() 
                                                    {
                                                        tab.getContent().requestFocus();
                                                        
                                                        spp.updateUI();
                                                    }
                                                });
                                            }
                                        }));
                                animation.setCycleCount(1);
                                animation.play();
                            }
                    }
                });

                textArea.addComponentListener(new ComponentAdapter() 
                {
                        @Override
                        public void componentResized(ComponentEvent e) 
                        {
                            //textArea.updateUI();
                            spp.updateUI();
                        }    
                });
                
                spp.addComponentListener(new ComponentAdapter() 
                {
                        @Override
                        public void componentResized(ComponentEvent e) 
                        {
                            workspaceCtrl.workspaceView.workspaceModel.curTab.spp.updateUI();
                            //spp.repaint();
                            //textArea.requestFocus();
                            spp.updateUI();
                        }
                });
       }//----

    }

    /*
    public void setOnSelectionChanged(EventHandler<Event> eventHandler) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */
    
}
