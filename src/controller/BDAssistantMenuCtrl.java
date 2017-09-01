/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.application.Platform;
import view.BDAssistantMenuView;

/**
 *
 * @author gsh
 */
public class BDAssistantMenuCtrl 
{
    public BDAssistantMenuCtrl(BDAssistantMenuView assistantMenu)
    {
        // 变量定义语句
        assistantMenu.defineSentItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                Platform.runLater(new Runnable() 
                {
                    @Override
                    public void run() 
                    {
                        BDDefineVariableWindowCtrl dfvWindowCtrl = new BDDefineVariableWindowCtrl(assistantMenu.dfvWindow, assistantMenu.workspaceCtrl);
                        
                        assistantMenu.dfvWindow.show();
                    }
                });
            }
        });
        
        // if-else语句
        assistantMenu.ifSentItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                Platform.runLater(new Runnable() 
                {
                    @Override
                    public void run() 
                    {
                        BDIfWindowCtrl ifWindowCtrl = new BDIfWindowCtrl(assistantMenu.ifWindow, assistantMenu.workspaceCtrl);
                        
                        assistantMenu.ifWindow.show();
                    }
                });
            }
        });
        
        // for语句
        assistantMenu.forSentItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                Platform.runLater(new Runnable() 
                {
                    @Override
                    public void run() 
                    {
                        BDForWindowCtrl forWindowCtrl = new BDForWindowCtrl(assistantMenu.forWindow, assistantMenu.workspaceCtrl);
                        
                        assistantMenu.forWindow.show();
                    }
                });
            }
        });
        
        // while语句
        assistantMenu.whileSentItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                Platform.runLater(new Runnable() 
                {
                    @Override
                    public void run() 
                    {
                        BDWhileWindowCtrl whileWindowCtrl = new BDWhileWindowCtrl(assistantMenu.whileWindow, assistantMenu.workspaceCtrl);
                        
                        assistantMenu.whileWindow.show();
                    }
                });
            }
        });
        
        // switch-case语句
        assistantMenu.switchSentItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                Platform.runLater(new Runnable() 
                {
                    @Override
                    public void run() 
                    {
                        BDSwitchWindowCtrl switchWindowCtrl = new BDSwitchWindowCtrl(assistantMenu.switchWindow, assistantMenu.workspaceCtrl);
                        
                        assistantMenu.switchWindow.show();
                    }
                });
            }
        });
        
        //----------------------------------------
        
        // pinMode函数
        assistantMenu.pinModeFuncItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                Platform.runLater(new Runnable() 
                {
                    @Override
                    public void run() 
                    {
                        BDPinModeWindowCtrl pinModeWindowCtrl = new BDPinModeWindowCtrl(assistantMenu.pinModeWindow, assistantMenu.workspaceCtrl);
                
                        // 弹出端口定义窗口
                        assistantMenu.pinModeWindow.show();
                    }
                });
            }
        });
        
        // digitalRead函数
        assistantMenu.drFuncItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                Platform.runLater(new Runnable() 
                {
                    @Override
                    public void run() 
                    {
                        BDDrWindowCtrl drWindowCtrl = new BDDrWindowCtrl(assistantMenu.drWindow, assistantMenu.workspaceCtrl);
                
                        assistantMenu.drWindow.show();
                    }
                });
            }
        });
        
        // digitalWrite函数
        assistantMenu.dwFuncItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                Platform.runLater(new Runnable() 
                {
                    @Override
                    public void run() 
                    {
                        BDDwWindowCtrl dwWindowCtrl = new BDDwWindowCtrl(assistantMenu.dwWindow, assistantMenu.workspaceCtrl);
                
                        assistantMenu.dwWindow.show();
                    }
                });
            }
        });
        
        // analogRead函数
        assistantMenu.arFuncItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                Platform.runLater(new Runnable() 
                {
                    @Override
                    public void run() 
                    {
                        BDArWindowCtrl arWindowCtrl = new BDArWindowCtrl(assistantMenu.arWindow, assistantMenu.workspaceCtrl);
                
                        assistantMenu.arWindow.show();
                    }
                });
            }
        });

        // analogWrite函数
        assistantMenu.awFuncItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                Platform.runLater(new Runnable() 
                {
                    @Override
                    public void run() 
                    {
                        BDAwWindowCtrl awWindowCtrl = new BDAwWindowCtrl(assistantMenu.awWindow, assistantMenu.workspaceCtrl);
                
                        assistantMenu.awWindow.show();
                    }
                });
            }
        });
        
        // Delay函数
        assistantMenu.delayFuncItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                Platform.runLater(new Runnable() 
                {
                    @Override
                    public void run() 
                    {
                        // 设置延时函数控制器
                        BDDelayWindowCtrl delayWindowCtrl = new BDDelayWindowCtrl(assistantMenu.delayWindow, assistantMenu.workspaceCtrl);

                        // 弹出延时设置窗口
                        assistantMenu.delayWindow.show();
                    }
                });
            }
        });
        
        // Serial串口通讯函数
        assistantMenu.serialFuncItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                Platform.runLater(new Runnable() 
                {
                    @Override
                    public void run() 
                    {
                        // 创建串口模版生成控制器
                        BDSerialWindowCtrl serialWindowCtrl = new BDSerialWindowCtrl(assistantMenu.serialWindow, assistantMenu.workspaceCtrl);

                        // 显示串口通讯模板
                        assistantMenu.serialWindow.show();
                    }
                });
            }
        });
        
        // IO pinMode函数
        assistantMenu.IOpinModeItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "pinMode(pin, mode);";
                        
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // IO digitalWrite函数
        assistantMenu.IOdigitalWriteItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "digitalWrite(pin, value);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // IO digitalRead函数
        assistantMenu.IOdigitalReadItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "digitalRead(pin);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // IO analogWrite函数
        assistantMenu.IOanalogWriteItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "analogWrite(pin, value);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // IO analogRead函数
        assistantMenu.IOanalogReadItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "analogRead(pin);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // IO analogReference函数
        assistantMenu.IOanalogReferenceItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "analogReference(type);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // AIO tone函数
        assistantMenu.AIOtoneItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "tone(pin, frequency);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // AIO noTone函数
        assistantMenu.AIOnoToneItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "noTone(pin);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // AIO shiftOut函数
        assistantMenu.AIOshiftOutItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "shiftOut(dataPin, clockPin, bitOrder, value);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // AIO shiftIn函数
        assistantMenu.AIOshiftInItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "shiftIn(dataPin, clockPin, bitOrder);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // AIO pulseIn函数
        assistantMenu.AIOpulseInItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "pulseIn(pin, value);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // TIME delay函数
        assistantMenu.TIMEdelayItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "delay(ms);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // TIME delayMicroseconds函数
        assistantMenu.TIMEdelayMicrosecondsItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "delayMicroseconds(us);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // TIME micros函数
        assistantMenu.TIMEmicrosItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "micros();";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // TIME millis函数
        assistantMenu.TIMEmillisItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "millis();";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // MATH abs函数
        assistantMenu.MATHabsItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "abs(x);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // MATH constrain函数
        assistantMenu.MATHconstrainItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "constrain(x, a, b);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // MATH cos函数
        assistantMenu.MATHcosItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "cos(rad);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // MATH map函数
        assistantMenu.MATHmapItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "map(value, fromLow, fromHigh, toLow, toHigh);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // MATH max函数
        assistantMenu.MATHmaxItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "max(x, y);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // MATH min函数
        assistantMenu.MATHminItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "min(x, y);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // MATH pow函数
        assistantMenu.MATHpowItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "pow(base, exponent);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // MATH sin函数
        assistantMenu.MATHsinItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "sin(rad);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // MATH tan函数
        assistantMenu.MATHtanItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "tan(rad);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // MATH sqrt函数
        assistantMenu.MATHsqrtItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "sqrt(x);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // RANDOM random函数
        assistantMenu.RNDrandomItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "random(seed);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // RANDOM randomSeed函数
        assistantMenu.RNDrandomSeedItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "randomSeed();";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // PROC bitClear函数
        assistantMenu.PROCbitClearItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "bitClear(x, n);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // PROC bitRead函数
        assistantMenu.PROCbitReadItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "bitRead(x, n);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // PROC bitWrite函数
        assistantMenu.PROCbitWriteItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "bitWrite(x, n, b);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // PROC highByte函数
        assistantMenu.PROChighByteItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "highByte(x);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // PROC lowByte函数
        assistantMenu.PROClowByteItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "lowByte(x);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // PROC bitSet函数
        assistantMenu.PROCbitSetItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "bitSet(x, n);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // PROC bit函数
        assistantMenu.PROCbitItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "bit(n);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // ITR attachInterrupt函数
        assistantMenu.ITRattachInterruptItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "attachInterrupt(interrupt, ISR, mode);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // ITR detachInterrupt函数
        assistantMenu.ITRdetachInterruptItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "detachInterrupt(interrupt);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // ITR interrupts函数
        assistantMenu.ITRinterruptsItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "interrupts();";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // ITR noInterrupts函数
        assistantMenu.ITRnoInterruptsItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "noInterrupts();";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // Com Serial Serial_ailable函数
        assistantMenu.Serial_availableItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Serial.available();";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // Com Serial Serial_begin函数
        assistantMenu.Serial_beginItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Serial.begin(speed);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // Com Serial Serial_end函数
        assistantMenu.Serial_endItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Serial.end();";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // Com Serial Serial_find函数
        assistantMenu.Serial_findItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Serial.find(target));";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // Com Serial Serial_findUntil函数
        assistantMenu.Serial_findUntilItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Serial.findUntil(target, terminal);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // Com Serial Serial_flushItem函数
        assistantMenu.Serial_flushItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Serial.flush();";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // Com Serial Serial_parseFloat函数
        assistantMenu.Serial_parseFloatItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Serial.parseFloat();";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // Com Serial Serial_parseInt函数
        assistantMenu.Serial_parseIntItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Serial.parseInt();";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // Com Serial Serial_peek函数
        assistantMenu.Serial_peekItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Serial.peek();";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // Com Serial Serial_print函数
        assistantMenu.Serial_printItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Serial.print(val);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // Com Serial Serial_println函数
        assistantMenu.Serial_printlnItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Serial.println(val);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // Com Serial Serial_readBytes函数
        assistantMenu.Serial_readBytesItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Serial.readBytes(buffer, length);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // Com Serial Serial_readBytesUntil函数
        assistantMenu.Serial_readBytesUntilItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Serial.readBytesUntil(character, buffer, length);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // Com Serial Serial_read函数
        assistantMenu.Serial_readItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Serial.read();";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // Com Serial Serial_serialEvent函数
        assistantMenu.Serial_serialEventItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Serial.serialEvent();";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // Com Serial Serial_setTimeout函数
        assistantMenu.Serial_setTimeoutItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Serial.setTimeout(time);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // Com Serial Serial_write函数
        assistantMenu.Serial_writeItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Serial.write(val);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // Com Stream Stream_available函数
        assistantMenu.Stream_availableItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Stream.available();";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // Com Stream Stream_find函数
        assistantMenu.Stream_findItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Stream.find(target);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // Com Stream Stream_findUntil函数
        assistantMenu.Stream_findUntilItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Stream.findUntil(target, terminal);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // Com Stream Stream_flush函数
        assistantMenu.Stream_flushItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Stream.flush();";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // Com Stream Stream_parse函数
        assistantMenu.Stream_parseIntItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Stream.parseInt(list);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // Com Stream Stream_parsefloat函数
        assistantMenu.Stream_parsefloatItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Stream.parseFloat(list);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // Com Stream Stream_peek函数
        assistantMenu.Stream_peekItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Stream.peek();";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // Com Stream Stream_readBytes函数
        assistantMenu.Stream_readBytesItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Stream.readBytes(buffer, length);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // Com Stream Stream_readBytesUntil函数
        assistantMenu.Stream_readBytesUntilItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Stream.readBytesUntil(character, buffer, length);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // Com Stream Stream_read函数
        assistantMenu.Stream_readItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Stream.read();";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // Com Stream Stream_readString函数
        assistantMenu.Stream_readStringItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Stream.readString();";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // Com Stream Stream_readStringUntil函数
        assistantMenu.Stream_readStringUntilItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Stream.readStringUntil(terminator);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // Com Stream Stream_setTimeout函数
        assistantMenu.Stream_setTimeoutItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Stream.setTimeout(time);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // MOUSE MOS_begin函数
        assistantMenu.MOS_beginItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Mouse.begin();";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // MOUSE MOS_click函数
        assistantMenu.MOS_clickItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Mouse.click(button);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // MOUSE MOS_end函数
        assistantMenu.MOS_endItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Mouse.end();";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // MOUSE MOS_isPressed函数
        assistantMenu.MOS_isPressedItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Mouse.isPressed(button);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // MOUSE MOS_move函数
        assistantMenu.MOS_moveItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Mouse.move(xVal, yPos, wheel);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // MOUSE MOS_press函数
        assistantMenu.MOS_pressItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Mouse.press(button);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // MOUSE MOS_release函数
        assistantMenu.MOS_releaseItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Mouse.release(button);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // KB KB_begin函数
        assistantMenu.KB_beginItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Keyboard.begin();";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // KB KB_end函数
        assistantMenu.KB_endItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Keyboard.end();";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // KB KB_press函数
        assistantMenu.KB_pressItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Keyboard.press(key);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // KB KB_print函数
        assistantMenu.KB_printItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Keyboard.print(character);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // KB KB_println函数
        assistantMenu.KB_printlnItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Keyboard.println(character);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // KB KB_releaseAll函数
        assistantMenu.KB_releaseAllItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Keyboard.releaseAll();";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // KB KB_release函数
        assistantMenu.KB_releaseItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Keyboard.release();";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
        
        // KB KB_write函数
        assistantMenu.KB_writeItem.addActionListener(new java.awt.event.ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
                String code = "Keyboard.write(character);";
                
                assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, assistantMenu.workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());
            }
        });
    }
}
