/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import model.BDLang;
import view.BDSwitchWindow;

/**
 *
 * @author gsh
 */
public class BDSwitchWindowCtrl 
{
	private static final Logger logger = LogManager.getLogger();
    BDSwitchWindow switchWindow;
    BDWorkspaceCtrl workspaceCtrl;
            
    @SuppressWarnings("unchecked")
	public BDSwitchWindowCtrl(BDSwitchWindow switchWindow, BDWorkspaceCtrl workspaceCtrl)
    {
        this.switchWindow = switchWindow;
        this.workspaceCtrl = workspaceCtrl;
        
        Matcher Variables;
        
        // 临时字符串
        String tmpStr = workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.getCode();
        
        // 在字符串首加入一个换行符
        tmpStr = "/n" + tmpStr;
       
        // 匹配变量定义语句，不包含for循环内的计步变量
        Variables = Pattern.compile("[^(for)](int|unsigned int)(\\s+)([a-z0-9A-Z_ ]*)\\b").matcher(tmpStr);
            
        // 添加已识别的变量名称
        while(Variables.find())
        {
            this.switchWindow.value1CmbBox.getItems().add(Variables.group(3));
        }
        
        // 读取条件数量
        int total = Integer.parseInt(switchWindow.value2CmbBox.getValue().toString());
        
        // 根据条件数量生成选择框
        this.addValues(total);
        
        // 选择表达式数量
        switchWindow.value2CmbBox.valueProperty().addListener(new ChangeListener<String>() 
        {  
            @Override 
            public void changed(ObservableValue ov, String t, String t1) 
            {  
                int count = Integer.parseInt(t1);
                
                // 更新界面
                addValues(count);
                
                //switchWindow.setHeight(200 - 60 + (count * 33));
                switchWindow.setHeight(160 + (count * 40));

                switchWindow.submitBtn.setPrefSize(80, 30);
            }      
        });
        
        // 提交
        switchWindow.submitBtn.setOnAction(new EventHandler<ActionEvent>() 
        {
      
            @Override
            public void handle(ActionEvent event)
            {    
                try
                {
                    // 条件为空
                    if(switchWindow.value1CmbBox.getValue().equals(""))
                    {
                        switchWindow.value1CmbBox.setPromptText("请输入表达式");

                        return;
                    }

                    // 获取词位
                    int tabCount = workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.getCurColumn() - 1;

                    // 生成代码
                    String code = "";

                    // 获取表达式
                    String value = "";

                    try
                    {
                        value = switchWindow.value1CmbBox.getValue().toString();
                    }
                    catch(Exception e)
                    {
                        value = "";
                    }

                    code += "switch(" + value + ")\\n";

                    // 添加缩进
                    for(int i = 0; i < tabCount; i++)
                    {
                        code += " ";
                    }    

                    code += "{\\n";

                    boolean flag = false;

                    for(int i = 0; i < switchWindow.valuesList.size(); i++)
                    {
                        // 添加缩进
                        for(int j = 0; j < tabCount + 4; j++)
                        {
                            code += " ";
                        }

                        if(switchWindow.valuesList.get(i).getValue().equals(""))
                        {
                            flag = true;

                            //switchWindow.valuesList.get(i).setPromptText("请输入条件");
                        }

                        code += "case " + switchWindow.valuesList.get(i).getValue().toString() + ":\\n\\n";

                        // 添加缩进
                        for(int j = 0; j < tabCount + 4; j++)
                        {
                            code += " ";
                        }

                        code += "break;\\n\\n";

                        //System.out.println(switchWindow.valuesList.get(i).getValue().toString());
                    }

                    // 有缺省表达式
                    if(flag)
                    {
                        return;
                    }

                    // 添加缩进
                    for(int i = 0; i < tabCount + 4; i++)
                    {
                        code += " ";
                    }    

                    code += "default :\\n";

                    // 添加缩进
                    for(int i = 0; i < tabCount; i++)
                    {
                        code += " ";
                    } 

                    //code += "}\\n";
                    code += "}";

                    // 插入语句
                    code = code.replaceAll("\"","\\\\\"");
                    
                    workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.insert(code);
                
                    // 关闭窗口
                    switchWindow.close();
                }
                catch(Exception e)
                {
                	logger.error("",e);
                }
            }
        });
    }
    
    private void addValues(int count)
    {
        switchWindow.bottomPanel.getChildren().clear();
        switchWindow.valuesList.clear();
        
        for(int i = 0; i < count; i++)
        {
            ComboBox<String> valueCmbBox = new ComboBox<String>();
            
            valueCmbBox.setEditable(true);

            valueCmbBox.setPromptText(BDLang.rb.getString("整型表达式") + " " + (i + 1));

            Matcher Variables;
            
            // 临时字符串
            String tmpStr = workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.getCode();
            
            // 在字符串首加入一个换行符
            tmpStr = "/n" + tmpStr;
       
            // 匹配变量定义语句，不包含for循环内的计步变量
            Variables = Pattern.compile("[^(for)](int)(\\s+)([a-z0-9A-Z_ ]*)\\b").matcher(tmpStr);
            
            // 添加已识别的变量名称
            while(Variables.find())
            {
                valueCmbBox.getItems().add(Variables.group(3));
            }

            valueCmbBox.setStyle("-fx-background-radius: 0, 0; -fx-font-size: 15;");
            valueCmbBox.setPrefWidth(200);
            
            switchWindow.bottomPanel.getChildren().add(valueCmbBox);
            switchWindow.valuesList.add(valueCmbBox);
        }
    }
}
