package model;

public class BDCmdGenerator
{
	private String user_root_path = System.getProperty("user.dir");
	private String builder_path = user_root_path + "\\arduino-builder-windows\\";
	private BDBoardInfoModel board;
	
	public BDCmdGenerator(BDBoardInfoModel board)
	{
		this.board = board;
	}
	
	public String genDumpCmd()
	{
		String cmd = "";

		cmd += builder_path + "arduino-builder -dump-prefs -logger=machine";
		
		// 添加 -hardware 参数列表
		for(int i = 0; i < board.getHardwaveList().size(); i++)
		{
			cmd += " -hardware " + board.getHardwaveList().get(i);
		}
		
		// 添加 -tools 参数列表
		for(int i = 0; i < board.getToolsList().size(); i++)
		{
			cmd += " -tools " + board.getToolsList().get(i);
		}
		
		// 添加 -built-in-libraries 参数
		cmd += " -built-in-libraries " + board.getBuilt_in_libraries();
		
		// 添加 -libraries 参数
		cmd += " -libraries " + board.getLibraries();
		
		// 添加 -fqbn 参数
		cmd += " -fqbn " + board.getFqbn();
		
		// 添加 -ide-version 参数
		cmd += " -ide-version " + board.getIde_version();
		
		// 添加 -build-path 参数
		cmd += " -build-path " + board.getBuilt_path();
		
		cmd += " -warnings=none";
		
		// 添加 -build-cache 参数
		cmd += " -build-cache " + board.getCache_path();
		
		// 添加 -prefs 参数列表
		for(int i = 0; i < board.getPrefsList().size(); i++)
		{
			cmd += " -prefs" + board.getPrefsList().get(i);
		}
		
		cmd += " -verbose " + board.getCode_path() + "Code.ino";

		return cmd;
	}
	
	public String genCompileCmd()
	{
		String cmd = "";
		
		cmd += builder_path + "arduino-builder -compile -logger=machine";
		
		// 添加 -hardware 参数列表
		for(int i = 0; i < board.getHardwaveList().size(); i++)
		{
			cmd += " -hardware " + board.getHardwaveList().get(i);
		}
		
		// 添加 -tools 参数列表
		for(int i = 0; i < board.getToolsList().size(); i++)
		{
			cmd += " -tools " + board.getToolsList().get(i);
		}
		
		// 添加 -built-in-libraries 参数
		cmd += " -built-in-libraries " + board.getBuilt_in_libraries();
		
		// 添加 -libraries 参数
		cmd += " -libraries " + board.getLibraries();
		
		// 添加 -fqbn 参数
		cmd += " -fqbn " + board.getFqbn();
		
		// 添加 -ide-version 参数
		cmd += " -ide-version " + board.getIde_version();
		
		// 添加 -build-path 参数
		cmd += " -build-path " + board.getBuilt_path();
		
		cmd += " -warnings=none";
		
		// 添加 -build-cache 参数
		cmd += " -build-cache " + board.getCache_path();
		
		// 添加 -prefs 参数列表
		for(int i = 0; i < board.getPrefsList().size(); i++)
		{
			cmd += " -prefs" + board.getPrefsList().get(i);
		}
		
		cmd += " -verbose " + board.getCode_path() + "Code.ino";
		
		return cmd;
	}
	
	public String genUploadCmd()
	{
		String cmd = "";
		
		return cmd;
	}

}
