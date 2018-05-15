package util.debug;

public class BDRunnerException extends Exception 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7201469765662916385L;
	protected String message;
	  protected int codeIndex;
	  protected int codeLine;
	  protected int codeColumn;
	  protected boolean showStackTrace;

	  
	  public BDRunnerException(String message) 
	  {
		  this(message, true);
	  }

	  public BDRunnerException(String message, boolean showStackTrace) 
	  {
		  this(message, -1, -1, -1, showStackTrace);
	  }

	  public BDRunnerException(String message, int file, int line) 
	  {
		  this(message, file, line, -1, true);
	  }

	  
	  public BDRunnerException(String message, int file, int line, int column) 
	  {
		  this(message, file, line, column, true);
	  }
	  
	  
	  public BDRunnerException(String message, int file, int line, int column, 
	                         boolean showStackTrace) 
	  {
	    this.message = message;
	    this.codeIndex = file;
	    this.codeLine = line;
	    this.codeColumn = column;
	    this.showStackTrace = showStackTrace;
	  }
	  
	  /** 
	   * Override getMessage() in Throwable, so that I can set 
	   * the message text outside the constructor.
	   */
	  public String getMessage() {
	    return message;
	  }
	  
	  
	  public void setMessage(String message) {
	    this.message = message;
	  }
	  
	  
	  public int getCodeIndex() {
	    return codeIndex;
	  }
	  
	  
	  public void setCodeIndex(int index) {
	    codeIndex = index;
	  }
	  
	  
	  public boolean hasCodeIndex() {
	    return codeIndex != -1;
	  }
	  
	  
	  public int getCodeLine() {
	    return codeLine;
	  }
	  
	  
	  public void setCodeLine(int line) {
	    this.codeLine = line;
	  }
	  
	  
	  public boolean hasCodeLine() {
	    return codeLine != -1;
	  }
	  
	  
	  public void setCodeColumn(int column) {
	    this.codeColumn = column;
	  }
	  
	  
	  public int getCodeColumn() {
	    return codeColumn;
	  }

	  
	  public void showStackTrace() {
	    showStackTrace = true;
	  }
	  
	  
	  public void hideStackTrace() {
	    showStackTrace = false;
	  }
	  

	  /**
	   * Nix the java.lang crap out of an exception message
	   * because it scares the children.
	   * <P>
	   * This function must be static to be used with super()
	   * in each of the constructors above.
	   */	  


	  public void printStackTrace() {
	    if (showStackTrace) {
	      super.printStackTrace();
	    }
	  }
}
