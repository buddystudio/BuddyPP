/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.BDLang;
import view.BDVariableWindow;

/**
 *
 * @author gsh
 */
public class BDDefineVariableWindowCtrl 
{
    public BDDefineVariableWindowCtrl(BDVariableWindow defineVariableWindow, BDWorkspaceCtrl workspaceCtrl)
    {
        // 清空输入
        defineVariableWindow.combType.getSelectionModel().select(0);
        defineVariableWindow.txtName.setText("");
        defineVariableWindow.txtValue.setText("");
        
        defineVariableWindow.submitBtn.setOnAction(new EventHandler<ActionEvent>() 
        {    
            @Override
            public void handle(ActionEvent event) 
            {
                String code;
                
                // 验证输入
                String value = defineVariableWindow.txtValue.getText();
                String type = defineVariableWindow.combType.getValue().toString();
                String name = defineVariableWindow.txtName.getText();
                
                // 如果没有输入变量名
                if(name.equals(""))
                {
                    defineVariableWindow.txtName.setText("");
                        
                    defineVariableWindow.txtName.setPromptText(BDLang.rb.getString("请输入变量名"));
                        
                    return;
                }
                else
                {
                    // 判断正确的变量名
                    if(!name.matches("[a-zA-Z_$][a-zA-Z0-9_$]*"))
                    {
                        defineVariableWindow.txtName.setText("");
                        
                        defineVariableWindow.txtName.setPromptText(BDLang.rb.getString("请输入正确的变量名"));
                        
                        return;
                    }
                }
                
                // 判断类型处理
                if(type.equals("String") || type.equals("string") || type.equals("char"))
                {
                    if(type.equals("char"))
                    {
                        try
                        {
                            if(Integer.parseInt(value) < -128 && Integer.parseInt(value) > 127 )
                            {
                                if(value.length() > 1)
                                {
                                    defineVariableWindow.txtValue.setText("");
                        
                                    defineVariableWindow.txtValue.setPromptText(BDLang.rb.getString("请输入字符"));
                        
                                    return;
                                }
                            }
                            else
                            {
                                defineVariableWindow.txtValue.setText("");

                                defineVariableWindow.txtValue.setPromptText(BDLang.rb.getString("请输入字符"));

                                return;

                            }
                        }
                        catch(Exception e)
                        {
                            if(value.length() > 1)
                            {
                                defineVariableWindow.txtValue.setText("");
                        
                                defineVariableWindow.txtValue.setPromptText(BDLang.rb.getString("请输入字符"));
                        
                                return;
                            }
                            
                            if(!value.equals(""))
                            {
                                value = "\'" + value + "\'";
                            }
                        } 
                    }
                    else
                    {
                        // 如果是字符串
                        value = "\\\"" + value + "\\\"";
                    }
                }
                else if(type.equals("boolean"))
                {
                    // 如果输入的不是布尔值
                    if(!value.equals("true") && !value.equals("false"))
                    {
                        if(!value.equals(""))
                        {
                            defineVariableWindow.txtValue.setText("");

                            defineVariableWindow.txtValue.setPromptText(BDLang.rb.getString("请输入布尔值"));

                            return;
                        }
                    }
                }
                else if(type.equals("int") || type.equals("unsigned int"))
                {
                    // 判断是否为整形
                    try
                    {
                        Integer.parseInt(value);
                        
                        if(type.equals("unsigned int"))
                        {
                            if(Integer.parseInt(value) < 0)
                            {
                                defineVariableWindow.txtValue.setText("");
                        
                                defineVariableWindow.txtValue.setPromptText(BDLang.rb.getString("请输入无字符整型"));
                        
                                return;
                            }
                        }
                    }
                    catch (NumberFormatException e)
                    {
                        if(!value.equals(""))
                        {
                            defineVariableWindow.txtValue.setText("");

                            defineVariableWindow.txtValue.setPromptText(BDLang.rb.getString("请输入整型"));

                            return;
                        }
                    }
                }
                else if(type.equals("long") || type.equals("unsigned long"))
                {
                    // 判断是否为长整型
                    try
                    {
                        Long.parseLong(value);
                        
                        if(type.equals("unsigned long"))
                        {
                            if(Integer.parseInt(value) < 0)
                            {
                                defineVariableWindow.txtValue.setText("");
                        
                                defineVariableWindow.txtValue.setPromptText(BDLang.rb.getString("请输入无字符长整型"));
                        
                                return;
                            }
                        }
                    }
                    catch (NumberFormatException e)
                    {
                        if(!value.equals(""))
                        {
                            defineVariableWindow.txtValue.setText("");

                            defineVariableWindow.txtValue.setPromptText(BDLang.rb.getString("请输入长整型"));

                            return;
                        }
                    }
                }
                else if(type.equals("float"))
                {
                    // 判断是否为浮点型
                    try
                    {
                        Float.parseFloat(value);
                    }
                    catch (NumberFormatException e)
                    {
                        if(!value.equals(""))
                        {
                            defineVariableWindow.txtValue.setText("");

                            defineVariableWindow.txtValue.setPromptText(BDLang.rb.getString("请输入浮点型"));

                            return;
                        }
                    }
                }
                else if(type.equals("double"))
                {
                    // 判断是否为双精度浮点型
                    try
                    {
                        Double.parseDouble(value);
                    }
                    catch (NumberFormatException e)
                    {
                        if(!value.equals(""))
                        {
                            defineVariableWindow.txtValue.setText("");

                            defineVariableWindow.txtValue.setPromptText(BDLang.rb.getString("请输入双精度浮点型"));

                            return;
                        }
                    }
                }
                else if(type.equals("short"))
                {
                    // 判断是否为短整型
                    try
                    {
                        Short.parseShort(value);
                    }
                    catch (NumberFormatException e)
                    {
                        if(!value.equals(""))
                        {
                            defineVariableWindow.txtValue.setText("");

                            defineVariableWindow.txtValue.setPromptText(BDLang.rb.getString("请输入短整型"));

                            return;
                        }
                    }
                }
                else if(type.equals("byte"))
                {
                    // 判断是否为byte
                    try
                    {
                        Byte.parseByte(value);
                    }
                    catch (NumberFormatException e)
                    {
                        if(!value.equals(""))
                        {
                            defineVariableWindow.txtValue.setText("");
                        
                            defineVariableWindow.txtValue.setPromptText(BDLang.rb.getString("请输入字节型"));
                        
                            return;
                        }
                        
                    }
                }
                
                // 生成语句
                if(value.equals(""))
                {
                    
                    code = type + " " + defineVariableWindow.txtName.getText() + ";";
                }
                else
                {
                    code = type + " " + defineVariableWindow.txtName.getText() + " = " + value + ";";
                }

                //workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());

                // 插入代码
                //BDCodeAgent codeAgent = new BDCodeAgent(workspaceCtrl.workspaceView.workspaceModel.curTab);
                
                //codeAgent.insert(code);
                
                workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.insert(code);
                
                // 关闭窗口
                defineVariableWindow.close();
            }
        });
    }
}
