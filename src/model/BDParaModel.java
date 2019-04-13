package model;

public class BDParaModel
{

	private String name = "";
	private String value = "";
	
	public BDParaModel(String name, String value)
	{
		this.setName(name);
		this.setValue(value);
	}
	
	public BDParaModel()
	{

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
