package util.debug;

import java.util.EventObject;

/**
 *
 * @author Boniz
 */
public class BDProgressStatusEvent extends EventObject 
{
    
    private final int progressNumber;
    private final BDProgressType progressType;

    public BDProgressStatusEvent(Object source, BDProgressType type, int status) 
    {        
        super(source);
        
        progressType = type;
        progressNumber = status;
    }

    public int getProgressNumber() 
    {
        return progressNumber;
    }

    public void setSource(Object source) 
    {
        this.source = source;
    }

	public BDProgressType getProgressType() 
	{
		return progressType;
	} 
}
