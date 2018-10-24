package model;

public class BDBoardModel
{
	private String name 	= "";
	private String tool 	= "";
	private String speed 	= "";
	private String mcu 		= "";
	private String board 	= "";
	private String core 	= "";
	private String protocol = "";
	private String fqbn		= "";
	
	public BDBoardModel(String name, String tool, String speed, 
			            String mcu, String board, String core, 
			            String protocol, String fqbn)
	{
		this.setName(name);
		this.setTool(tool);
		this.setSpeed(speed);
		this.setMcu(mcu);
		this.setBoard(board);
		this.setCore(core);
		this.setProtocol(protocol);
		this.setFqbn(fqbn);
	}
	
	public BDBoardModel(String name)
	{
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTool() {
		return tool;
	}

	public void setTool(String tool) {
		this.tool = tool;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getMcu() {
		return mcu;
	}

	public void setMcu(String mcu) {
		this.mcu = mcu;
	}

	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public String getCore() {
		return core;
	}

	public void setCore(String core) {
		this.core = core;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getFqbn() {
		return fqbn;
	}

	public void setFqbn(String fqbn) {
		this.fqbn = fqbn;
	}

}
