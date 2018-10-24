package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class BDSerialManager2
{
	private static SerialPort serialPort = null;
	
	public BDSerialManager2()
	{
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unchecked")
	public ObservableList<String> getPortList() 
    {   
        ObservableList<String> serialList = FXCollections.observableArrayList();
        
        serialList.clear();
        
        String[] ports = SerialPortList.getPortNames();
        
        if(ports.length != 0) 
        {
            for (String s : ports) 
            {
                //serPort.getItems().add(s);
            	serialList.add(s);
            }
            
            //serPort.setValue(ports[0]);
        }
        else
        {
        	serialList = FXCollections.observableArrayList("未连接");
        }
        
        return serialList;
    }
	
	public void serialPortOpen(String port)
	{
		if( serialPort != null && serialPort.isOpened()) try 
        {
            serialPort.closePort();
            
            return;
            
        } 
        catch (SerialPortException e) 
        {
            //new AlertBox().display("关闭串口错误", e.getMessage());
        	System.out.println("关闭串口错误");
        }
		
		serialPort = new SerialPort(port);
		
		try
		{
			//serialPort.closePort();
			serialPort.openPort();
			serialPort.setParams(1200, 8, 1, 0);
			
			serialPort.purgePort(SerialPort.PURGE_RXCLEAR);
	        serialPort.purgePort(SerialPort.PURGE_TXCLEAR);
	        serialPort.setEventsMask(SerialPort.MASK_RXCHAR);
	        
	        //UsartRXEven();
		} 
		catch (SerialPortException e)
		{
			//new AlertBox().display("打开串口错误", e.getMessage());
			System.out.println("打开串口错误");
		}
        
	}
	
	public void serialPortClose()
	{
		if(serialPort != null && serialPort.isOpened()) try 
        {
            serialPort.closePort();
            
            return;
            
        } 
        catch (SerialPortException e) 
        {
            //new AlertBox().display("关闭串口错误", e.getMessage());
        	System.out.println("关闭串口错误");
        }
	}
	
	public void UsartRXEven()
    {
        try 
        {
            serialPort.addEventListener(serialPortEvent -> 
            {
                
            });
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

}
