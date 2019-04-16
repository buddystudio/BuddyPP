package model;

public class BDParaModel
{

	private String name 	= "";
	private String value 	= "";
	private String value2	 = "";
	
	public BDParaModel(String name, String value, String value2)
	{
		this.setName(name);
		this.setValue(value);
		this.setValue2(value2);
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

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

}
