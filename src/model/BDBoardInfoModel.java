package model;

import java.util.ArrayList;

public class BDBoardInfoModel
{
	private ArrayList<String>hardwaveList = new ArrayList<String>();
	private ArrayList<String>toolsList = new ArrayList<String>();
	private ArrayList<String>prefsList = new ArrayList<String>();
	
	private String boardName 			= "";
	private String built_in_libraries 	= "";
	private String libraries 			= "";
	private String fqbn 				= "";
	private String ide_version 			= "";
	
	private String built_path			= "";
	private String code_path			= "";
	private String cache_path			= "";
	
	private String config_path			= "";
	private String v					= "";
	private String p					= "";
	private String c					= "";
	private String b					= "";
	private String d					= "";
	private String u					= "";
	
	// 修改后的部分
	//private String boardName 			= "";
	private String dump 			= "";
	private String compile 			= "";
	private String upload 			= "";
	
	public BDBoardInfoModel()
	{

	}

	public ArrayList<String> getHardwaveList() {
		return hardwaveList;
	}

	public void setHardwaveList(ArrayList<String> hardwaveList) {
		this.hardwaveList = hardwaveList;
	}

	public ArrayList<String> getToolsList() {
		return toolsList;
	}

	public void setToolsList(ArrayList<String> toolsList) {
		this.toolsList = toolsList;
	}

	public String getBuilt_in_libraries() {
		return built_in_libraries;
	}

	public void setBuilt_in_libraries(String built_in_libraries) {
		this.built_in_libraries = built_in_libraries;
	}

	public String getLibraries() {
		return libraries;
	}

	public void setLibraries(String libraries) {
		this.libraries = libraries;
	}

	public String getFqbn() {
		return fqbn;
	}

	public void setFqbn(String fqbn) {
		this.fqbn = fqbn;
	}

	public String getIde_version() {
		return ide_version;
	}

	public void setIde_version(String ide_version) {
		this.ide_version = ide_version;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public String getBuilt_path() {
		return built_path;
	}

	public void setBuilt_path(String built_path) {
		this.built_path = built_path;
	}

	public String getCode_path() {
		return code_path;
	}

	public void setCode_path(String code_path) {
		this.code_path = code_path;
	}

	public String getCache_path() {
		return cache_path;
	}

	public void setCache_path(String cache_path) {
		this.cache_path = cache_path;
	}

	public ArrayList<String> getPrefsList() {
		return prefsList;
	}

	public void setPrefsList(ArrayList<String> prefsList) {
		this.prefsList = prefsList;
	}

	public String getConfig_path() {
		return config_path;
	}

	public void setConfig_path(String config_path) {
		this.config_path = config_path;
	}

	public String getV() {
		return v;
	}

	public void setV(String v) {
		this.v = v;
	}

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public String getU() {
		return u;
	}

	public void setU(String u) {
		this.u = u;
	}

	public String getDump() {
		return dump;
	}

	public void setDump(String dump) {
		this.dump = dump;
	}

	public String getCompile() {
		return compile;
	}

	public void setCompile(String compile) {
		this.compile = compile;
	}

	public String getUpload() {
		return upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}
}
