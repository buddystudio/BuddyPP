package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BDMessage
{
	private String message = "";
	
	private PropertyChangeSupport changes = new PropertyChangeSupport(this);
	
	public BDMessage()
	{
		// TODO Auto-generated constructor stub
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) 
	{ 
		changes.addPropertyChangeListener(listener); 
	} 
	
	public void removePropertyChangeListener(PropertyChangeListener listener) 
	{ 
		changes.removePropertyChangeListener(listener); 
	}

	public String getMessage() {
		return message;
	}

	
	public void setMessage(String message) 
	{
		String OldMessage = this.message;
		
		this.message = message;
		 
		changes.firePropertyChange("name", OldMessage, message);
	}

	public PropertyChangeSupport getChanges() 
	{
		return changes;
	}

	public void setChanges(PropertyChangeSupport changes) 
	{
		this.changes = changes;
	}

}
