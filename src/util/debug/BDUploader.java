package util.debug;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;

public abstract class BDUploader implements BDMessageConsumer 
{
    BDRunnerException exception;
    // PdePreferences preferences;

    // Serial serialPort;
    static InputStream serialInput;
    static OutputStream serialOutput;
    // int serial; // last byte of data received
	  
    boolean verbose;	
    boolean firstErrorFound;
    boolean secondErrorFound;
    boolean notFoundError;
    
    BDProgressStatusListener listener;
    
    public BDUploader() {}
    
    public void addProgressStatusListener(BDProgressStatusListener listener) {
    	this.listener = listener;		
	}

    @Override
    public void message(String s) 
    {      
        if (s.indexOf("Connecting to programmer:") != -1
                || s.indexOf("Found programmer: Id = \"CATERIN\"; type = S")
                        != -1
                        || s.indexOf(
                                "Software Version = 1.0; No Hardware Version given.")
                                        != -1
                                        || s.indexOf(
                                                "Programmer supports auto addr increment.")
                                                        != -1
                                                        || s.indexOf(
                                                                "Programmer supports buffered memory access with buffersize=128 bytes.")
                                                                        != -1
                                                                        || s.indexOf(
                                                                                "Programmer supports the following devices:")
                                                                                        != -1
                                                                                        || s.indexOf(
                                                                                                "Device code: 0x44")
                                                                                                        != -1) {
            s = "";
        }	
		    
        System.err.print(s);

        // ignore cautions
        if (s.indexOf("Error") != -1) 
        {
            // exception = new RunnerException(s+" Check the serial port selected or your Board is connected");
            // System.out.println(s);
            notFoundError = true;
            return;
        }
        
        if (notFoundError) 
        {
            // System.out.println("throwing something");
            exception = new BDRunnerException(
                    String.format(
                            "the selected serial port {0} does not exist or your board is not connected",
                            s));
            return;
        }
        
        if (s.indexOf("Device is not responding") != -1) 
        {
            exception = new BDRunnerException(
                    "Device is not responding, check the right serial port is selected or RESET the board right before exporting");
            return;
        }
        
        if (s.indexOf("Programmer is not responding") != -1
                || s.indexOf("programmer is not responding") != -1
                || s.indexOf("protocol error") != -1
                || s.indexOf("avrdude: ser_open(): can't open device") != -1
                || s.indexOf("avrdude: ser_drain(): read error") != -1
                || s.indexOf("avrdude: ser_send(): write error") != -1
                || s.indexOf(
                        "avrdude: error: buffered memory access not supported.")
                                != -1) {
            exception = new BDRunnerException(
                    "Problem uploading to board.  See http://www.arduino.cc/en/Guide/Troubleshooting#upload for suggestions.");
            return;
        }
        
        if (s.indexOf("Expected signature") != -1) 
        {
            exception = new BDRunnerException(
                    "Wrong microcontroller found.  Did you select the right board from the Tools > Board menu?");
            return;
        }
    }
	
    public abstract boolean uploadUsingPreferences(String buildPath, String className, boolean usingProgrammer)
        throws BDRunnerException, Exception;
	
    protected void flushSerialBuffer() throws BDRunnerException, Exception 
    {
        // Cleanup the serial buffer
        try 
        {
            BDSerial serialPort = new BDSerial();
            //byte[] readBuffer;

            while (serialPort.available() > 0) 
            {
            	//byte[] readBuffer = serialPort.readBytes();
            	serialPort.readBytes();
            	
                try 
                {
                    Thread.sleep(100);
                } 
                catch (InterruptedException e) {}                
            }
            
            serialPort.setDTR(false);
            serialPort.setRTS(false);

            try 
            {
                Thread.sleep(100);
            } 
            catch (InterruptedException e) {}

            serialPort.setDTR(true);
            serialPort.setRTS(true);
	      
            serialPort.dispose();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            
            throw new BDRunnerException(e.getMessage());
        }
    }

    protected boolean executeUploadCommand(Collection<String> commandDownloader) 
        throws BDRunnerException 
    {
        firstErrorFound = false; // haven't found any errors yet
        secondErrorFound = false;
        notFoundError = false;
        
        int result = 0; // pre-initialized to quiet a bogus warning from jikes
		    
        //String userdir = System.getProperty("user.dir") + File.separator;

        try 
        {
            String[] commandArray = new String[commandDownloader.size()];

            commandDownloader.toArray(commandArray);
		      
            if (verbose) 
            {
                for (int i = 0; i < commandArray.length; i++) 
                {
                    System.out.print(commandArray[i] + " ");
                }
                
                System.out.println();
            }
            
            Process process = Runtime.getRuntime().exec(commandArray);
            
            BDMessageSiphon messageInput = new BDMessageSiphon(process.getInputStream(), this);
            messageInput.addProgressStatusListener(listener);
            BDMessageSiphon messageError = new BDMessageSiphon(process.getErrorStream(), this);
            messageError.addProgressStatusListener(listener);
            // wait for the process to finish.  if interrupted
            // before waitFor returns, continue waiting
            //
            boolean compiling = true;

            while (compiling) 
            {
                try 
                {
                    result = process.waitFor();
                    compiling = false;
                } 
                catch (InterruptedException intExc) {}
            } 
            
            if (exception != null) 
            {
                exception.hideStackTrace();
                throw exception;   
            }
            
            if (result != 0) 
            {
                return false;
            }
        } 
        catch (Exception e) 
        {
            String msg = e.getMessage();

            if ((msg != null) && (msg.indexOf("uisp: not found") != -1)
                    && (msg.indexOf("avrdude: not found") != -1)) 
            {
                // System.err.println("uisp is missing");
                // JOptionPane.showMessageDialog(editor.base,
                // "Could not find the compiler.\n" +
                // "uisp is missing from your PATH,\n" +
                // "see readme.txt for help.",
                // "Compiler error",
                // JOptionPane.ERROR_MESSAGE);
                return false;
            } 
            else 
            {
                e.printStackTrace();
                
                result = -1;
            }
        }
        // System.out.println("result2 is "+result);
        // if the result isn't a known, expected value it means that something
        // is fairly wrong, one possibility is that jikes has crashed.
        //
        if (exception != null) 
        {
            throw exception;
        }

        if ((result != 0) && (result != 1)) 
        {
            exception = new BDRunnerException("Compiler error");
        }

        return (result == 0); // ? true : false;      

    }
}
