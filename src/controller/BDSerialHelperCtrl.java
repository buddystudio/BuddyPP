package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class BDSerialHelperCtrl implements Initializable 
{
    @FXML ChoiceBox<String> serPort;
    @FXML ChoiceBox<String> serPortSpeed;
    @FXML ChoiceBox<String> serPortCheckBit;
    @FXML ChoiceBox<String> serPortDataBit;
    @FXML ChoiceBox<String> serPortStopBit;
    @FXML Button serPortOpenBtn;
    @FXML CheckBox recvShowHex;
    @FXML CheckBox recvShowTime;
    @FXML CheckBox recvStopShow;
    @FXML Button recvClear;
    @FXML CheckBox sendHex;
    @FXML CheckBox sendCycle;
    @FXML TextField sendCycleRap;
    @FXML Button sendClear;
    @FXML Label sendCount;
    @FXML Label recvCount;
    @FXML Button CountReset;
    @FXML TextArea sendTextAear;
    @FXML TextArea recvTextAear;
    @FXML Button sendBtn;

    private static SerialPort serialPort = null;
    
    Timer t ;
    
    String[] ports = SerialPortList.getPortNames();

    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
        
        ports = SerialPortList.getPortNames();
        
        if(ports.length!=0) 
        {
            for (String s : ports) 
            {
                serPort.getItems().add(s);
            }
            
            serPort.setValue(ports[0]);
        }
        else
        {
        	serPort.getItems().add("未连接");
        	
        	serPort.setValue("未连接");
        }
        
        String[] speeds = new String[]{"100",
        							   "300",
        							   "600",
        							   "1200",
        							   "2400",
        							   "4800",
        							   "9600",
        							   "14400",
        							   "19200",
        							   "38400",
        							   "56000",
        							   "57600",
        							   "115200",
        							   "128000",
        							   "256000"
        };
        
        for (String s:speeds) 
        {
            serPortSpeed.getItems().add(s);
        }
        
        serPortSpeed.setValue("9600");

        
        String[] checks = new String[]{"NONE","ODD","EVEN","MARK","SPACE"};
        
        for (String s:checks) 
        {
            serPortCheckBit.getItems().add(s);
        }
        
        serPortCheckBit.setValue("NONE");

        String[] databits = new String[]
        		{
                "5","6","7","8"
        };
        
        for (String s:databits) 
        {
            serPortDataBit.getItems().add(s);
        }
        
        serPortDataBit.setValue("8");

        String[] stopbits = new String[]{"1","2"};
        
        for (String s:stopbits) 
        {
            serPortStopBit.getItems().add(s);
        }
        
        serPortStopBit.setValue("1");

        serPortOpenBtn.setOnAction((ActionEvent event) -> 
        {
        	ports = SerialPortList.getPortNames();
            
            if(ports.length != 0) 
            {
                for (String s : ports) 
                {
                    serPort.getItems().add(s);
                }
                
                serPort.setValue(ports[0]);
            }
            else
            {
            	//serPort.getItems().add("未连接");
            	
            	serPort.setValue("未连接");
            	
            	return;
            }
        	
            if( serialPort != null && serialPort.isOpened()) try 
            {
                serialPort.closePort();
                serPortOpenBtn.setText("打开");
                serPort.setDisable(false);
                serPortSpeed.setDisable(false);
                serPortCheckBit.setDisable(false);
                serPortDataBit.setDisable(false);
                serPortStopBit.setDisable(false);
                
                return;
                
            } 
            catch (SerialPortException e) 
            {
                new AlertBox().display("关闭串口错误", e.getMessage());
            }
            
            serialPort = new SerialPort((String) serPort.getValue());
            
            try 
            {
                serialPort.openPort();
                serialPort.setParams(
                        new Integer((String)serPortSpeed.getValue()) ,
                        new Integer((String)serPortDataBit.getValue()),
                        new Integer((String)serPortStopBit.getValue()),
                        serPortSpeed.getValue().equals("NONE")? 0: serPortSpeed.getValue().equals("ODD")?1 :
                                serPortSpeed.getValue().equals("EVEN")?2: serPortSpeed.getValue().equals("SPACE")?3: 0);
                serialPort.purgePort(SerialPort.PURGE_RXCLEAR);
                serialPort.purgePort(SerialPort.PURGE_TXCLEAR);
                serialPort.setEventsMask(SerialPort.MASK_RXCHAR);
                UsartRXEven();
                serPortOpenBtn.setText("关闭");
                serPort.setDisable(true);
                serPortSpeed.setDisable(true);
                serPortCheckBit.setDisable(true);
                serPortDataBit.setDisable(true);
                serPortStopBit.setDisable(true);

            } 
            catch (SerialPortException e) 
            {
                new AlertBox().display("打开串口错误", e.getMessage());
            }
        });

        sendBtn.setOnAction(event -> 
        {
            if(null==serialPort||(!serialPort.isOpened())){ new AlertBox().display("错误","请先打开串口");return;}
            
            try 
            {
                if(sendHex.isSelected())
                {
                    serialPort.writeBytes(hexStringToBytes(sendTextAear.getText()));
                    sendCount.setText(String.valueOf((Integer.parseInt(sendCount.getText())+hexStringToBytes(sendTextAear.getText()).length)));
                }
                else
                {
                    serialPort.writeBytes(sendTextAear.getText().getBytes());
                    sendCount.setText(String.valueOf((Integer.parseInt(sendCount.getText())+sendTextAear.getText().getBytes().length)));
                }

            } 
            catch (Exception e) 
            {
                new AlertBox().display("发送信息错误",e.getMessage());
            }
        });

        recvClear.setOnAction(event -> 
        {
            recvTextAear.setText("");
        });
        
        sendHex.setOnAction(event -> 
        {
            if (!sendHex.isSelected())
                try {
                    sendTextAear.setText(new String(hexStringToBytes(sendTextAear.getText())));
                } catch (Exception e) {
                    new AlertBox().display("非法十六进制字符",e.getMessage());
                }
            else
                sendTextAear.setText(bytesToHexString(sendTextAear.getText().getBytes()));
        });
        
        sendClear.setOnAction(event -> 
        {
            sendTextAear.setText("");
        });
        
        CountReset.setOnAction(event -> 
        {
            sendCount.setText("0");
            recvCount.setText("0");
        });
        
        sendCycle.setOnAction(event -> 
        {
            if(null==serialPort||(!serialPort.isOpened())){
                new AlertBox().display("错误","请先打开串口");sendCycle.setSelected(false);return;}
            try 
            {
                if(sendCycle.isSelected()) 
                {
                    sendBtn.setDisable(true);
                    sendCycleRap.setDisable(true);
                    
                    t = new Timer();

                    byte[] sendData = sendHex.isSelected()?hexStringToBytes(sendTextAear.getText()):sendTextAear.getText().getBytes();

                    TimerTask task = new TimerTask() 
                    {
                        public void run() 
                        {
                            // task to run goes here
                            //System.out.println("Hello !!!");
                            try 
                            {
                                serialPort.writeBytes(sendData);
                                
                                Platform.runLater(()-> 
                                {
                                    sendCount.setText(String.valueOf((Integer.parseInt(sendCount.getText()) + sendData.length)));
                                });
                            } 
                            catch (SerialPortException e) 
                            {
                                new AlertBox().display("寰幆鍙戦�侀敊璇�",e.getMessage());
                            }
                        }
                    };
                    
                    t.schedule(task, 0, new Long(sendCycleRap.getText()));
                }
                else
                {
                    t.cancel();
                    sendBtn.setDisable(false);
                    sendCycleRap.setDisable(false);
                }
            } 
            catch (Exception e) 
            {
                new AlertBox().display("寰幆鍙戦�侀敊璇�",e.getMessage());
            }
        });
    }
    public void UsartRXEven()
    {
        try 
        {
            serialPort.addEventListener(serialPortEvent -> 
            {
                try 
                {
                    if(recvStopShow.isSelected()) 
                    {
                    	serialPort.readHexString();
                    	return;
                    }
                    
                    byte[] bytes=serialPort.readBytes();
                    
                    if(bytes!=null) 
                    {
                        String str = recvShowHex.isSelected()? bytesToHexString(bytes):(new String((bytes)));
                        
                        if(recvShowTime.isSelected()) str=new Date().toString()+": "+str;
                        
                        String newStr = recvTextAear.getText().isEmpty() ? ("" + str) : (recvTextAear.getText() + "\n" + str);
                        
                        recvTextAear.setText(newStr);
                        recvTextAear.setScrollTop(recvTextAear.getMaxHeight());
                        
                        Platform.runLater(()->
                        {
                            recvCount.setText(String.valueOf((Integer.parseInt(recvCount.getText())+bytes.length)));
                        });
                    }
                } 
                catch (SerialPortException e) 
                {
                    e.printStackTrace();
                }
            });
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    public static byte[] hexStringToBytes(String hexString) throws Exception 
    {
        if (hexString == null || hexString.equals("")) 
        {
            return null;
        }
        
        hexString=hexString.replace(" ","");
        
        if(hexString.length()%2!=0)hexString="0"+hexString;
        hexString = hexString.toUpperCase();
        
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        
        for (int i = 0; i < length; i++) 
        {
            int pos = i * 2;
            
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        
        return d;
    }

    /**
     * Convert char to byte
     * @param c char
     * @return byte
     */
    private static byte charToByte(char c) throws Exception 
    {
        int i = "0123456789ABCDEF".indexOf(c);
        if(i==-1) throw new Exception("非法的十六进制字符");
        return (byte) i;
    }

    public static String bytesToHexString(byte[] bArray) 
    {
        if(bArray==null) return null;
        
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        
        for (int i = 0; i < bArray.length; i++) 
        {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            
            if (sTemp.length() < 2)
                sb.append(0);
            
            sb.append(sTemp.toUpperCase());
        }
        
        return sb.toString();
    }
}
