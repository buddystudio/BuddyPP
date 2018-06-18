package view;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.base.Base;
import util.base.Preferences;

//import javafx.scene.control.TextArea;


import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import javafx.application.Platform;

public class BDTextAreaConsole extends CodeArea 
{
	public BDGUIView gui;
	
	int maxLineCount;

	static File errFile;
	static File outFile;
	static File tempFolder;

	// Single static instance shared because there's only one real System.out.
	// Within the input handlers, the currentConsole variable will be used to
	// echo things to the correct location.
	static public PrintStream systemOut;
	static public PrintStream systemErr;

	static PrintStream consoleOut;
	static PrintStream consoleErr;

	static OutputStream stdoutFile;
	static OutputStream stderrFile;

	static BDTextAreaConsole currentConsole;
	
	private static final String COMMAND_PATTERN = "(\\.)*avr-(\\.)*";
	private static final String ERROR_PATTERN = "(\\.)*error(\\.)*";
	//private static final String AVRDUDE_PATTERN = "(\\.)*Send(\\.)*";
	//private static final String AVRDUDE_PATTERN = "avrdude(\\.)*";
	
	private static final Pattern PATTERN = Pattern.compile(		
			"(?<ERROR>"+ERROR_PATTERN+")"
			//+ "|(?<AVRDUDE>"+AVRDUDE_PATTERN + ")"					
			+ "|(?<COMMAND>" + COMMAND_PATTERN + ")");

	public static BDTextAreaConsole getTextAreaConsoleInstance() 
	{
		if (currentConsole == null)
			currentConsole = new BDTextAreaConsole();
		return currentConsole;
	}

	private BDTextAreaConsole() 
	{
		this.getStylesheets().add("style/compileStyle.css");
		
		maxLineCount = Preferences.getInteger("console.length");
		this.setEditable(false);
		this.setParagraphGraphicFactory(LineNumberFactory.get(this));
		this.setWrapText(true);
		this.setEditable(false);
		
		this.richChanges().filter(ch -> !ch.getInserted().equals(ch.getRemoved()))
				.subscribe(change -> {
					//this.setStyleSpans(0, computeHighlighting(this.getText()));
				});

		if (systemOut == null) 
		{
			systemOut = System.out;
			systemErr = System.err;

			tempFolder = Base.createTempFolder("console");
			tempFolder.deleteOnExit();
			
			try 
			{
				String outFileName = Preferences.get("console.output.file");
				
				if (outFileName != null) 
				{
					outFile = new File(tempFolder, outFileName);
					outFile.deleteOnExit();
					stdoutFile = new FileOutputStream(outFile);
				}

				String errFileName = Preferences.get("console.error.file");
				
				if (errFileName != null) 
				{
					errFile = new File(tempFolder, errFileName);
					errFile.deleteOnExit();
					stderrFile = new FileOutputStream(errFile);
				}
			} 
			catch (IOException e) 
			{
				Base.showWarning("Console Error",
						"A problem occurred while trying to open the\nfiles used to store the console output.", e);
			}
			
			consoleOut = new PrintStream(new EditorConsoleStream(false));
			consoleErr = new PrintStream(new EditorConsoleStream(true));

			if (Preferences.getBoolean("console")) 
			{
				try 
				{
					System.setOut(consoleOut);
					System.setErr(consoleErr);
				} 
				catch (Exception e) 
				{
					e.printStackTrace(systemOut);
				}
			}
		}

		// to fix ugliness.. normally macosx java 1.3 puts an
		// ugly white border around this object, so turn it off.
		if (Base.isMacOS()) 
		{
			setBorder(null);
		}
		
		// periodically post buffered messages to the console
        // should the interval come from the preferences file?
       // new javax.swing.Timer(250, (ActionEvent evt) -> {
            // only if new text has been added
           // if (consoleDoc.hasAppendage) {
                // insert the text that's been added in the meantime
                //consoleDoc.insertAll();
                // always move to the end of the text as it's added
                //consoleTextPane.setCaretPosition(consoleDoc.getLength());
            //}
       // }).start();

	}
	
	public static StyleSpans<Collection<String>> computeHighlighting(String text) 
	{
		Matcher matcher = PATTERN.matcher(text);
		
		int lastKwEnd 	= 0;
		int lineHead	= 0;
		int lineEnd		= 0;
		
		StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();

		while (matcher.find()) 
		{
			String styleClass =
					matcher.group("COMMAND")!=null?"command":
					//matcher.group("AVRDUDE")!=null?"avrdude":
					matcher.group("ERROR")!=null?"error":
					null;
			
			assert styleClass != null;

			lineHead = text.lastIndexOf("\n", matcher.start());
			
			if(lineHead < 0)
				lineHead = 0;
			
			lineEnd = text.indexOf("\n", matcher.end());
			
			if(lineEnd < 0)
				lineEnd = text.length() - 1;
			
			if(lineHead - lastKwEnd >= 0)
			{
				spansBuilder.add(Collections.emptyList(), lineHead - lastKwEnd);
				spansBuilder.add(Collections.singleton(styleClass), lineEnd - lineHead);
			}
			
			lastKwEnd = lineEnd;
		}
		
		spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);

		return spansBuilder.create();
	}

	public void write(byte b[], int offset, int length, boolean err) {
		// we could do some cross platform CR/LF mangling here before outputting
		// add text to output document
		message(new String(b, offset, length), err, false);
	}

	// added sync for 0091.. not sure if it helps or hinders
	synchronized public void message(String what, boolean err, boolean advance) 
	{
		if (err) 
		{
			systemErr.print(what);
			// systemErr.print("CE" + what);
		} 
		else 
		{
			systemOut.print(what);
			// systemOut.print("CO" + what);
		}

		if (advance) 
		{
			appendText("\n", err);
			
			if (err) 
			{
				systemErr.println();
			} 
			else 
			{
				systemOut.println();
			}
		}

		// to console display
		appendText(what, err);
		// moved down here since something is punting
	}
	
	synchronized private void appendText(String txt, boolean e) 
	{
		//to do : append text
       // consoleDoc.appendString(txt, e ? errStyle : stdStyle);
		//if(e){
			//setStyle("-fx-text-fill: #FF1d1d;");			
		//}	

		appendText(txt);

		try
		{
			Platform.runLater(new Runnable() 
	        {
	            @Override
	            public void run() 
	            {
	                // 更新JavaFX的主线程的代码放在此处
	            	gui.msgArea.appendText(txt);
	            }
	        });	
		}
		catch(Exception ex) {}
    }

	/*
    public void clear() {
        try {
        	//to do : remove  text
            //consoleDoc.remove(0, consoleDoc.getLength());
        	clear();
        } catch (Exception e) {
            // ignore the error otherwise this will cause an infinite loop
            // maybe not a good idea in the long run?
        }
    }
    */
	
	public void clear()
	{
		deleteText(0, getLength()-1);
	}

	private static class EditorConsoleStream extends OutputStream 
	{
		// static EditorConsole current;
		final boolean err; // whether stderr or stdout
		final byte single[] = new byte[1];

		public EditorConsoleStream(boolean err) 
		{
			this.err = err;
		}

		@Override
		public void close() {}

		@Override
		public void flush() {}

		@Override
		public void write(byte b[]) 
		{ // appears never to be used
			if (currentConsole != null) 
			{
				currentConsole.write(b, 0, b.length, err);
			} 
			else 
			{
				try 
				{
					if (err) 
					{
						systemErr.write(b);
					} 
					else 
					{
						systemOut.write(b);
					}
				} 
				catch (IOException e) {} // just ignore, where would we write?
			}

			OutputStream echo = err ? stderrFile : stdoutFile;
			
			if (echo != null) 
			{
				try 
				{
					echo.write(b);
					echo.flush();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
					echo = null;
				}
			}
		}

		@Override
		public void write(byte b[], int offset, int length) 
		{
			if (currentConsole != null) 
			{
				currentConsole.write(b, offset, length, err);
			} 
			else 
			{
				try 
				{
					if (err) 
					{
						systemErr.write(b);
					} 
					else 
					{
						systemOut.write(b);
					}
				} 
				catch (IOException e) {} // just ignore, where would we write?
			}

			OutputStream echo = err ? stderrFile : stdoutFile;
			
			if (echo != null) 
			{
				try 
				{
					echo.write(b, offset, length);
					echo.flush();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
					echo = null;
				}
			}
		}

		@Override
		public void write(int b) 
		{
			single[0] = (byte) b;
			
			if (currentConsole != null) 
			{
				currentConsole.write(single, 0, 1, err);
			} 
			else 
			{
				// redirect for all the extra handling above
				write(new byte[] { (byte) b }, 0, 1);
			}

			OutputStream echo = err ? stderrFile : stdoutFile;
			
			if (echo != null) 
			{
				try 
				{
					echo.write(b);
					echo.flush();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
					echo = null;
				}
			}
		}
	}
}
