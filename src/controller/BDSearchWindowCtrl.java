/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.BDCodeAgent;
import view.BDSearchWindow;

/**
 *
 * @author gsh
 */
public class BDSearchWindowCtrl 
{
    int startIndex = 0;
    String searchTxt = "";
    
    public BDSearchWindowCtrl(BDSearchWindow searchWindow, BDWorkspaceCtrl workspaceCtrl)
    {
        BDCodeAgent codeAgent = new BDCodeAgent(workspaceCtrl.workspaceView.workspaceModel.curTab);
        
        // 清空内容
        searchWindow.searchTxt.setText("");
        searchWindow.replaceTxt.setText("");
        
        searchWindow.distChkBox.setSelected(false);
        searchWindow.reverseChkBox.setSelected(false);
        
        // 搜索
        searchWindow.searchBtn.setOnAction(new EventHandler<ActionEvent>() 
        {    
            @Override
            public void handle(ActionEvent event) 
            {
                // 重获焦点
                //workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.requestFocus();
                codeAgent.requestFocus();
                
                // 获取搜索文本
                searchTxt = searchWindow.searchTxt.getText();
                
                if(searchTxt.isEmpty())
                {
                    return;
                }
                
                Matcher Variables;

                // 寻找匹配字串
                if(searchWindow.distChkBox.isSelected())
                {
                    // 区分大小写匹配
                    //Variables = Pattern.compile(searchTxt).matcher(workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getText());
                    Variables = Pattern.compile(searchTxt).matcher(codeAgent.getText());
                }
                else
                {
                    // 不区分大小写匹配
                    //Variables = Pattern.compile(searchTxt, Pattern.CASE_INSENSITIVE).matcher(workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getText());
                    Variables = Pattern.compile(searchTxt, Pattern.CASE_INSENSITIVE).matcher(codeAgent.getText());
                }
                
                if(Variables.find(startIndex) == true)
                {
                    //System.out.println(Variables.group(0));
                    //System.out.println(Variables.start());

                    startIndex = Variables.start() + Variables.group(0).length();
                    
                    //System.out.println("start:" + (startIndex - Variables.group(0).length()));
                    //System.out.println("end:" + startIndex);
                    
                    //workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.requestFocus();
                    //workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.select(startIndex - Variables.group(0).length(), startIndex);
                    codeAgent.select(startIndex - Variables.group(0).length(), startIndex);
                }
                else
                {
                    startIndex = 0;
                }
            }
        });
        
        // 替换
        searchWindow.replaceBtn.setOnAction(new EventHandler<ActionEvent>() 
        {    
            @Override
            public void handle(ActionEvent event) 
            {
                // 获取搜索文本
                String searchTxt = searchWindow.searchTxt.getText();
                
                // 获取替代文本
                String replaceTxt = searchWindow.replaceTxt.getText();
                
                if(searchTxt.isEmpty())
                {
                    return;
                }
                
                int s = startIndex - searchTxt.length();
                int e = startIndex;
                
                // 进行替换
                //workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.replaceRange(replaceTxt, startIndex - searchTxt.length(), startIndex);
                codeAgent.replace(replaceTxt, startIndex - searchTxt.length(), startIndex);
                
                // 替换后选择
                // ...
                
                Matcher Variables;

                // 寻找匹配字串
                if(searchWindow.distChkBox.isSelected())
                {
                    // 区分大小写匹配
                    //Variables = Pattern.compile(searchTxt).matcher(workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getText());
                    Variables = Pattern.compile(searchTxt).matcher(codeAgent.getText());
                }
                else
                {
                    // 不区分大小写匹配
                    //Variables = Pattern.compile(searchTxt, Pattern.CASE_INSENSITIVE).matcher(workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getText());
                    Variables = Pattern.compile(searchTxt, Pattern.CASE_INSENSITIVE).matcher(codeAgent.getText());
                }
                
                if(Variables.find(startIndex) == true)
                {
                    System.out.println(Variables.group(0));
                    //System.out.println(Variables.start());

                    startIndex = Variables.start() + Variables.group(0).length();
                    
                    System.out.println("start:" + (startIndex - Variables.group(0).length()));
                    System.out.println("end:" + startIndex);
                    
                    //workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.requestFocus();
                    //workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.select(startIndex - Variables.group(0).length(), startIndex);
                    codeAgent.select(startIndex - Variables.group(0).length(), startIndex);
                }
                // 重新选择
                //workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.select(s, e - replaceTxt.length() + 1);
                
                //startIndex = e - replaceTxt.length() + 1;
            }
        });
        
        // 全部替代
        searchWindow.repAllBtn.setOnAction(new EventHandler<ActionEvent>() 
        {    
            @Override
            public void handle(ActionEvent event) 
            {
                // 获取搜索文本
                searchTxt = searchWindow.searchTxt.getText();
                
                // 获取替代文本
                String replaceTxt = searchWindow.replaceTxt.getText();
                
                if(searchTxt.isEmpty())
                {
                    return;
                }
                
                //String tmpTxt = workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getText();
                String tmpTxt = codeAgent.getText();
                
                //workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.setText(tmpTxt.replaceAll(searchTxt, replaceTxt));
                codeAgent.setText(tmpTxt.replaceAll(searchTxt, replaceTxt));
            }
        });
    }
}
