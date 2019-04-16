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
}
