package util.debug;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BDMessageSiphon implements Runnable 
{
	BufferedReader streamReader;
	Thread thread;
	BDMessageConsumer consumer;
	private Logger logger = LogManager.getLogger();
	BDProgressStatusListener progressListener; // 进度侦听

	public BDMessageSiphon(InputStream stream, BDMessageConsumer consumer) {
		this.streamReader = new BufferedReader(new InputStreamReader(stream));
		this.consumer = consumer;

		thread = new Thread(this);
		// don't set priority too low, otherwise exceptions won't
		// bubble up in time (i.e. compile errors have a weird delay)
		// thread.setPriority(Thread.MIN_PRIORITY);
		thread.setPriority(Thread.MAX_PRIORITY - 1);
		thread.start();
	}

	public void addProgressStatusListener(BDProgressStatusListener listener) {
		progressListener = listener;
	}

	public void run() 
	{
		try 
		{
			// process data until we hit EOF; this will happily block
			// (effectively sleeping the thread) until new data comes in.
			// when the program is finally done, null will come through.
			//
			String currentLine;
			
			while ((currentLine = streamReader.readLine()) != null) 
			{
				consumer.message(currentLine + "\n");
				
				doUploadProgress(currentLine);	// 判断upload进度
			}

			thread = null;

		} 
		catch (NullPointerException npe) 
		{
			// Fairly common exception during shutdown
			thread = null;

		} 
		catch (Exception e) 
		{
			// On Linux and sometimes on Mac OS X, a "bad file descriptor"
			// message comes up when closing an applet that's run externally.
			// That message just gets supressed here..
			String mess = e.getMessage();
			
			if ((mess != null) && (mess.indexOf("Bad file descriptor") != -1)) 
			{
				// if (e.getMessage().indexOf("Bad file descriptor") == -1) {
				// System.err.println("MessageSiphon err " + e);
				// e.printStackTrace();
			} 
			else 
			{
				logger.error("Output Message", e);
			}
			
			thread = null;
		}
	}

	// Wait until the MessageSiphon thread is complete.
	public void join() throws java.lang.InterruptedException 
	{
		// Grab a temp copy in case another thread nulls the "thread"
		// member variable
		Thread t = thread;
		
		if (t != null)
			t.join();
	}

	public Thread getThread() 
	{
		return thread;
	}

	void doUploadProgress(String source) throws InterruptedException 
	{
		if (source.indexOf("System wide configuration file") > 0) 
		{
			notifyProgressEvent(20);
			
			System.out.println("");
			System.out.println("progress of upload : 20%");
			
			return;
		}
		
		if (source.indexOf("Programmer Type") > 0) 
		{
			notifyProgressEvent(30);
			
			System.out.println("");
			System.out.println("progress of upload : 30%");
			
			return;
		}

		if (source.indexOf("AVR device initialized") > 0) 
		{
			notifyProgressEvent(50);
			
			System.out.println("");
			System.out.println("progress of upload : 50%");
			
			return;
		}

		if (source.indexOf("flash written") > 0) 
		{
			notifyProgressEvent(80);
			
			System.out.println("");
			System.out.println("progress of upload : 70%");
			
			return;
		}
		
		if (source.indexOf("reading on-chip") > 0) 
		{
			notifyProgressEvent(90);
			
			System.out.println("");
			System.out.println("progress of upload : 80%");
			
			return;
		}

		if (source.indexOf("Thank you") > 0) 
		{
			notifyProgressEvent(100);
			
			System.out.println("");
			System.out.println("progress of upload : 100%");
			
			return;
		}
	}

	void notifyProgressEvent(int progressNumber) 
	{
		if (progressListener == null)
			return;
		
		progressListener.ProgressEventHandler(new BDProgressStatusEvent(this, BDProgressType.Upload, progressNumber));
	}
}
