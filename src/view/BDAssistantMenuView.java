/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import controller.BDWorkspaceCtrl;

/**
 *
 * @author gsh
 */
public class BDAssistantMenuView extends JMenu
{
    public BDWorkspaceCtrl workspaceCtrl;
    
    public BDDefineVariableWindow dfvWindow = new BDDefineVariableWindow();     // 变量定义窗口
    public BDIfWindow ifWindow              = new BDIfWindow();                 // If条件分支窗口
    public BDForWindow forWindow            = new BDForWindow();                // For条件循环窗口
    public BDWhileWindow whileWindow        = new BDWhileWindow();              // While条件循环窗口
    public BDSwitchWindow switchWindow      = new BDSwitchWindow();             // Switch-Case条件分支窗口
    
    public BDPinModeWindow pinModeWindow    = new BDPinModeWindow();            // PinMode端口设置窗口
    public BDDelayWindow delayWindow        = new BDDelayWindow();              // Delay延时设置窗口
    public BDDrWindow drWindow              = new BDDrWindow();                 // DigitalRead数字端口读取窗口
    public BDDwWindow dwWindow              = new BDDwWindow();                 // DigitalWrite数字端口写入窗口
    public BDArWindow arWindow              = new BDArWindow();                 // AnalogRead模拟端口读取窗口
    public BDAwWindow awWindow              = new BDAwWindow();                 // AnalogWrite模拟端口写入窗口
    public BDSerialWindow serialWindow      = new BDSerialWindow();             // Serial串口通讯模板窗口
    
    JMenu baseSent = new JMenu("基础语句"); 
    JMenu normalFunc = new JMenu("常用函数"); 
    JMenu baseFunc = new JMenu("基础函数"); 
    
    JMenu baseIOFunc = new JMenu("I/O"); 
         public JMenuItem IOpinModeItem = new JMenuItem("pinMode()");
         public JMenuItem IOdigitalWriteItem = new JMenuItem("digitalWrite()");
         public JMenuItem IOdigitalReadItem = new JMenuItem("digitalRead()");
         public JMenuItem IOanalogReferenceItem = new JMenuItem("analogReference()");
         public JMenuItem IOanalogWriteItem = new JMenuItem("analogWrite()");
         public JMenuItem IOanalogReadItem = new JMenuItem("analogRead()");
    JMenu baseAdvancedIOFunc = new JMenu("Advanced I/O"); 
         public JMenuItem AIOtoneItem = new JMenuItem("tone()");
         public JMenuItem AIOnoToneItem = new JMenuItem("noTone()");
         public JMenuItem AIOshiftOutItem = new JMenuItem("shiftOut()");
         public JMenuItem AIOshiftInItem = new JMenuItem("shiftIn()");
         public JMenuItem AIOpulseInItem = new JMenuItem("pulseIn()");
    JMenu baseTimeFunc = new JMenu("Time");
         public JMenuItem TIMEmillisItem = new JMenuItem("millis()");
         public JMenuItem TIMEmicrosItem = new JMenuItem("micros()");
         public JMenuItem TIMEdelayItem = new JMenuItem("delay()");
         public JMenuItem TIMEdelayMicrosecondsItem = new JMenuItem("delayMicroseconds()");
    JMenu baseMathFunc = new JMenu("Math"); 
         public JMenuItem MATHminItem = new JMenuItem("min()");
         public JMenuItem MATHmaxItem = new JMenuItem("max()");
         public JMenuItem MATHabsItem = new JMenuItem("abs()");
         public JMenuItem MATHconstrainItem = new JMenuItem("constrain()");
         public JMenuItem MATHmapItem = new JMenuItem("map()");
         public JMenuItem MATHpowItem = new JMenuItem("pow()");
         public JMenuItem MATHsqrtItem = new JMenuItem("sqrt()");
         public JMenuItem MATHsinItem = new JMenuItem("sin()");
         public JMenuItem MATHcosItem = new JMenuItem("cos()");
         public JMenuItem MATHtanItem = new JMenuItem("tan()");
    JMenu baseRandomFunc = new JMenu("Random Numbers"); 
         public JMenuItem RNDrandomSeedItem = new JMenuItem("randomSeed()");
         public JMenuItem RNDrandomItem = new JMenuItem("random()");
    JMenu baseProcFunc = new JMenu("Bits and Bytes"); 
         public JMenuItem PROClowByteItem = new JMenuItem("lowByte()");
         public JMenuItem PROChighByteItem = new JMenuItem("highByte()");
         public JMenuItem PROCbitReadItem = new JMenuItem("bitRead()");
         public JMenuItem PROCbitWriteItem = new JMenuItem("bitWrite()");
         public JMenuItem PROCbitSetItem = new JMenuItem("bitSet()");
         public JMenuItem PROCbitClearItem = new JMenuItem("bitClear()");
         public JMenuItem PROCbitItem = new JMenuItem("bit()");
    JMenu baseInterruptsFunc = new JMenu("Interrupts"); 
         public JMenuItem ITRinterruptsItem = new JMenuItem("interrupts()");
         public JMenuItem ITRnoInterruptsItem = new JMenuItem("noInterrupts()");
         public JMenuItem ITRattachInterruptItem = new JMenuItem("attachInterrupt()");
         public JMenuItem ITRdetachInterruptItem = new JMenuItem("detachInterrupt()");
    JMenu baseCommunicationFunc = new JMenu("Communication"); 
        JMenu baseComSerialFunc = new JMenu("Serial"); 
            public JMenuItem Serial_availableItem = new JMenuItem("Serialav.available()");
            public JMenuItem Serial_beginItem = new JMenuItem("Serialav.begin()");
            public JMenuItem Serial_endItem = new JMenuItem("Serialav.end()");
            public JMenuItem Serial_findItem = new JMenuItem("Serialav.find()");
            public JMenuItem Serial_findUntilItem = new JMenuItem("Serialav.findUntil()");
            public JMenuItem Serial_flushItem = new JMenuItem("Serialav.flush()");
            public JMenuItem Serial_parseFloatItem = new JMenuItem("Serialav.parseFloat()");
            public JMenuItem Serial_parseIntItem = new JMenuItem("Serialav.parseInt()");
            public JMenuItem Serial_peekItem = new JMenuItem("Serialav.peek()");
            public JMenuItem Serial_printItem = new JMenuItem("Serialav.print()");
            public JMenuItem Serial_printlnItem = new JMenuItem("Serialav.println()");
            public JMenuItem Serial_readItem = new JMenuItem("Serialav.read()");
            public JMenuItem Serial_readBytesItem = new JMenuItem("Serialav.readBytes()");
            public JMenuItem Serial_readBytesUntilItem = new JMenuItem("Serialav.readBytesUntil()");
            public JMenuItem Serial_setTimeoutItem = new JMenuItem("Serialav.setTimeout()");
            public JMenuItem Serial_writeItem = new JMenuItem("Serialav.write()");
            public JMenuItem Serial_serialEventItem = new JMenuItem("Serialav.serialEvent()");
        JMenu baseComStreamFunc = new JMenu("Stream"); 
            public JMenuItem Stream_availableItem = new JMenuItem("Stream.available()");
            public JMenuItem Stream_readItem = new JMenuItem("Stream.read()");
            public JMenuItem Stream_flushItem = new JMenuItem("Stream.flush()");
            public JMenuItem Stream_findItem = new JMenuItem("Stream.find()");
            public JMenuItem Stream_findUntilItem = new JMenuItem("Stream.findUntil()");
            public JMenuItem Stream_peekItem = new JMenuItem("Stream.peek()");
            public JMenuItem Stream_readBytesItem = new JMenuItem("Stream.readBytes()");
            public JMenuItem Stream_readBytesUntilItem = new JMenuItem("Stream.readBytesUntil()");
            public JMenuItem Stream_readStringItem = new JMenuItem("Stream.readString()");
            public JMenuItem Stream_readStringUntilItem = new JMenuItem("Stream.readStringUntil()");
            public JMenuItem Stream_parseIntItem = new JMenuItem("Stream.parseInt()");
            public JMenuItem Stream_parsefloatItem = new JMenuItem("Stream.parseFloat()");
            public JMenuItem Stream_setTimeoutItem = new JMenuItem("Stream.setTimeout()");
    JMenu baseMouseAndKeyboardFunc = new JMenu("Mouse & Keyboard"); 
        JMenu baseMouseFunc = new JMenu("Mouse"); 
            public JMenuItem MOS_beginItem = new JMenuItem("Mouse.begin()");
            public JMenuItem MOS_clickItem = new JMenuItem("Mouse.click()");
            public JMenuItem MOS_endItem = new JMenuItem("Mouse.end()");
            public JMenuItem MOS_moveItem = new JMenuItem("Mouse.move()");
            public JMenuItem MOS_pressItem = new JMenuItem("Mouse.press()");
            public JMenuItem MOS_releaseItem = new JMenuItem("Mouse.release()");
            public JMenuItem MOS_isPressedItem = new JMenuItem("Mouse.isPressed()");
        JMenu baseKeyboardFunc = new JMenu("Keyboard"); 
            public JMenuItem KB_beginItem = new JMenuItem("Keyboard.begin()");
            public JMenuItem KB_endItem = new JMenuItem("Keyboard.end()");
            public JMenuItem KB_pressItem = new JMenuItem("Keyboard.press()");
            public JMenuItem KB_printItem = new JMenuItem("Keyboard.print()");
            public JMenuItem KB_printlnItem = new JMenuItem("Keyboard.println()");
            public JMenuItem KB_releaseItem = new JMenuItem("Keyboard.release()");
            public JMenuItem KB_releaseAllItem = new JMenuItem("Keyboard.releaseAll()");
            public JMenuItem KB_writeItem = new JMenuItem("Keyboard.write()");
    public JMenuItem defineSentItem = new JMenuItem("定义变量");
    public JMenuItem ifSentItem = new JMenuItem("if-else语句");
    public JMenuItem forSentItem = new JMenuItem("for语句"); 
    public JMenuItem whileSentItem = new JMenuItem("while语句"); 
    public JMenuItem switchSentItem = new JMenuItem("switch-case语句"); 
    
    public JMenuItem pinModeFuncItem = new JMenuItem("PinMode函数");
    public JMenuItem drFuncItem = new JMenuItem("digitalRead函数"); 
    public JMenuItem dwFuncItem = new JMenuItem("digitalWrite函数"); 
    public JMenuItem arFuncItem = new JMenuItem("analogRead函数"); 
    public JMenuItem awFuncItem = new JMenuItem("analogWrite函数"); 
    public JMenuItem delayFuncItem = new JMenuItem("delay函数"); 
    public JMenuItem serialFuncItem = new JMenuItem("串口通讯函数"); 
                
    public BDAssistantMenuView(String name, BDWorkspaceCtrl workspaceCtrl)
    {
        this.setText(name);
        this.workspaceCtrl = workspaceCtrl;
        
        baseSent.add(defineSentItem);
        baseSent.add(ifSentItem);
        baseSent.add(forSentItem);
        baseSent.add(whileSentItem);
        baseSent.add(switchSentItem);
        
        normalFunc.add(pinModeFuncItem);
        normalFunc.add(drFuncItem);
        normalFunc.add(dwFuncItem);
        normalFunc.add(arFuncItem);
        normalFunc.add(awFuncItem);
        normalFunc.add(delayFuncItem);
        normalFunc.add(serialFuncItem);
        
        baseFunc.add(baseIOFunc);
            baseIOFunc.add(IOpinModeItem);
            baseIOFunc.add(IOdigitalWriteItem);
            baseIOFunc.add(IOdigitalReadItem);
            baseIOFunc.add(IOanalogReferenceItem);
            baseIOFunc.add(IOanalogWriteItem);
            baseIOFunc.add(IOanalogReadItem);
        baseFunc.add(baseAdvancedIOFunc);
            baseAdvancedIOFunc.add(AIOtoneItem);
            baseAdvancedIOFunc.add(AIOnoToneItem);
            baseAdvancedIOFunc.add(AIOshiftOutItem);
            baseAdvancedIOFunc.add(AIOshiftInItem);
            baseAdvancedIOFunc.add(AIOpulseInItem);
        baseFunc.add(baseTimeFunc);
            baseTimeFunc.add(TIMEmillisItem);
            baseTimeFunc.add(TIMEmicrosItem);
            baseTimeFunc.add(TIMEdelayItem);
            baseTimeFunc.add(TIMEdelayMicrosecondsItem);
        baseFunc.add(baseMathFunc);
            baseMathFunc.add(MATHminItem);
            baseMathFunc.add(MATHmaxItem);
            baseMathFunc.add(MATHabsItem);
            baseMathFunc.add(MATHconstrainItem);
            baseMathFunc.add(MATHmapItem);
            baseMathFunc.add(MATHpowItem);
            baseMathFunc.add(MATHsqrtItem);
            baseMathFunc.add(MATHsinItem);
            baseMathFunc.add(MATHcosItem);
            baseMathFunc.add(MATHtanItem);
        baseFunc.add(baseRandomFunc);
            baseRandomFunc.add(RNDrandomSeedItem);
            baseRandomFunc.add(RNDrandomItem);
        baseFunc.add(baseProcFunc);
            baseProcFunc.add(PROClowByteItem);
            baseProcFunc.add(PROChighByteItem);
            baseProcFunc.add(PROCbitReadItem);
            baseProcFunc.add(PROCbitWriteItem);
            baseProcFunc.add(PROCbitSetItem);
            baseProcFunc.add(PROCbitClearItem);
            baseProcFunc.add(PROCbitItem);
        baseFunc.add(baseInterruptsFunc);
            baseInterruptsFunc.add(ITRinterruptsItem);
            baseInterruptsFunc.add(ITRnoInterruptsItem);
            baseInterruptsFunc.add(ITRattachInterruptItem);
            baseInterruptsFunc.add(ITRdetachInterruptItem);
        baseFunc.add(baseCommunicationFunc);
            baseCommunicationFunc.add(baseComSerialFunc);
                baseComSerialFunc.add(Serial_availableItem);
                baseComSerialFunc.add(Serial_beginItem);
                baseComSerialFunc.add(Serial_endItem);
                baseComSerialFunc.add(Serial_findItem);
                baseComSerialFunc.add(Serial_findUntilItem);
                baseComSerialFunc.add(Serial_flushItem);
                baseComSerialFunc.add(Serial_parseFloatItem);
                baseComSerialFunc.add(Serial_parseIntItem);
                baseComSerialFunc.add(Serial_peekItem);
                baseComSerialFunc.add(Serial_printItem);
                baseComSerialFunc.add(Serial_printlnItem);
                baseComSerialFunc.add(Serial_readItem);
                baseComSerialFunc.add(Serial_readBytesItem);
                baseComSerialFunc.add(Serial_readBytesUntilItem);
                baseComSerialFunc.add(Serial_setTimeoutItem);
            baseCommunicationFunc.add(baseComStreamFunc);
                baseComStreamFunc.add(Stream_availableItem);
                baseComStreamFunc.add(Stream_readItem);
                baseComStreamFunc.add(Stream_flushItem);
                baseComStreamFunc.add(Stream_findItem);
                baseComStreamFunc.add(Stream_findUntilItem);
                baseComStreamFunc.add(Stream_peekItem);
                baseComStreamFunc.add(Stream_readBytesItem);
                baseComStreamFunc.add(Stream_readBytesUntilItem);
                baseComStreamFunc.add(Stream_readStringItem);
                baseComStreamFunc.add(Stream_readStringUntilItem);
                baseComStreamFunc.add(Stream_parseIntItem);
                baseComStreamFunc.add(Stream_parsefloatItem);
                baseComStreamFunc.add(Stream_setTimeoutItem);
        baseFunc.add(baseMouseAndKeyboardFunc);
            baseMouseAndKeyboardFunc.add(baseMouseFunc);
                baseMouseFunc.add(MOS_beginItem);
                baseMouseFunc.add(MOS_clickItem);
                baseMouseFunc.add(MOS_endItem);
                baseMouseFunc.add(MOS_moveItem);
                baseMouseFunc.add(MOS_pressItem);
                baseMouseFunc.add(MOS_releaseItem);
                baseMouseFunc.add(MOS_isPressedItem);
            baseMouseAndKeyboardFunc.add(baseKeyboardFunc);
                baseKeyboardFunc.add(KB_beginItem);
                baseKeyboardFunc.add(KB_endItem);
                baseKeyboardFunc.add(KB_pressItem);
                baseKeyboardFunc.add(KB_printItem);
                baseKeyboardFunc.add(KB_printlnItem);
                baseKeyboardFunc.add(KB_releaseItem);
                baseKeyboardFunc.add(KB_releaseAllItem);
                baseKeyboardFunc.add(KB_writeItem);
        this.add(baseSent);
        this.add(normalFunc);
        this.add(baseFunc);
        
    }
}
