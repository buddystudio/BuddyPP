package model;

public class BoardMap {
	String name;
	String type;

	public BoardMap(String n, String t) {
		name = n;
		type = t;
	}

	@Override
	public String toString() {
		return name;
	}
	
	public String getType(){
		return type;
	}
	
}
