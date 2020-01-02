package model;

public class BDCmdGenerator
{
	private BDBoardInfoModel board;
	private String root = System.getProperty("user.dir");
	
	public BDCmdGenerator(BDBoardInfoModel board)
	{
		this.board = board;
	}
	
	public String genDumpCmd()
	{
		String cmd = "";
		
		cmd = this.board.getDump();
		
		cmd = cmd.replace("$root$", root);
		cmd = cmd.replace("$cache$", board.getCache_path());
		cmd = cmd.replace("$build$", board.getBuilt_path());
		cmd = cmd.replace("$temp$", board.getCode_path());
		
		return cmd;
	}
	
	public String genCompileCmd()
	{
		String cmd = "";
		
		cmd = this.board.getCompile();
		
		cmd = cmd.replace("$root$", root);
		cmd = cmd.replace("$cache$", board.getCache_path());
		cmd = cmd.replace("$build$", board.getBuilt_path());
		cmd = cmd.replace("$temp$", board.getCode_path());
		
		return cmd;
	}
	
	public String genUploadCmd(String com, String hexPath)
	{
		String cmd = "";
		
		cmd = this.board.getUpload();
		
		cmd = cmd.replace("$root$", root);
		cmd = cmd.replace("$cache$", board.getCache_path());
		cmd = cmd.replace("$build$", board.getBuilt_path());
		cmd = cmd.replace("$temp$", board.getCode_path());
		cmd = cmd.replace("$com$", com);
		
		return cmd;
	}

}
