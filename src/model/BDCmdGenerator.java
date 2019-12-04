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
		
		String root = System.getProperty("user.dir");
		
		cmd = this.board.getDump();
		
		cmd = cmd.replace("$root$", root);
		cmd = cmd.replace("$cache$", board.getCache_path());
		cmd = cmd.replace("$build$", board.getBuilt_path());
		cmd = cmd.replace("$temp$", board.getCode_path());
		
		/*

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
		
		*/

		return cmd;
	}
	
	public String genCompileCmd()
	{
		String cmd = "";
		
		String root = System.getProperty("user.dir");
		
		cmd = this.board.getCompile();
		
		cmd = cmd.replace("$root$", root);
		cmd = cmd.replace("$cache$", board.getCache_path());
		cmd = cmd.replace("$build$", board.getBuilt_path());
		cmd = cmd.replace("$temp$", board.getCode_path());
		
		/*
		
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
		*/
		
		return cmd;
	}
	
	public String genUploadCmd(String com, String hexPath)
	{
		String cmd = "";
		
		// 生成烧录程序的命令
		cmd += builder_path + "hardware\\tools\\avr\\bin\\avrdude";
		cmd += " -C" + board.getConfig_path();
		cmd += " -v" + board.getV();
		cmd += " -p" + board.getP();
		cmd += " -c" + board.getC();
		cmd += " -P" + com;
		cmd += " -b" + board.getB();
		cmd += " -D" + board.getD();
		cmd += " -U" + board.getU() + hexPath + ":i";
		
		return cmd;
	}

}
