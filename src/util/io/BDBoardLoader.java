package util.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import model.BDBoardInfoModel;
import model.BDParaModel;
import model.BDParameters;

public class BDBoardLoader
{	
	public BDBoardLoader()
	{
		this.parse3();
		this.parse2();
	}
	
	private String parsePath(String value)
	{
		String path = "";
		String user_root_path = System.getProperty("user.dir");
		
		path = value;
		
		if(value.length() >= 6)
		{
			if(value.substring(0, 6).equals("$root/"))
			{
				// 解析路径
				path = user_root_path + value.substring(5).replaceAll("/", Matcher.quoteReplacement(File.separator));
			}
		}
		
		return path;
	}
	
	public void parse3()
	{
		String user_root_path = System.getProperty("user.dir");
		//String path = user_root_path + "\\arduino-builder-windows\\boards\\boards_info.json";
		File file = new File(user_root_path + "\\arduino-builder-windows\\boards\\");
		
		// 遍历所有板型信息文件
		for(int j = 0; j < file.listFiles().length; j++)
		{
			String fileName = file.listFiles()[j].getName();
			
			String infoPath = user_root_path + "\\arduino-builder-windows\\boards\\" + fileName;
			
			String[] strArray = fileName.split("\\.");
			
			// 获取后缀名
			int suffixIndex = strArray.length - 1;
			
			String suffix = strArray[suffixIndex];
			
			// 如果不是board文件则忽略
			if(!suffix.equals("board"))
			{
				continue;
			}
			
			System.out.println("fileName: " + fileName);
			
			// 创建JSON解析器
			JsonParser parser = new JsonParser();
			
			try
			{
				JsonObject object = (JsonObject) parser.parse(new FileReader(infoPath));
				
				BDBoardInfoModel boardInfo = new BDBoardInfoModel();

				String board = object.get("board").getAsString();
				String dump = object.get("dump").getAsString();
				String compile = object.get("compile").getAsString();
				String upload = object.get("upload").getAsString();
				
				// 获取主控板名称，设置、编译以及上传的命令模板
				boardInfo.setBoardName(object.get("board").getAsString());
				boardInfo.setDump(object.get("dump").getAsString());
				boardInfo.setCompile(object.get("compile").getAsString());
				boardInfo.setUpload(object.get("upload").getAsString());
				
				System.out.println("board : " + board);
				System.out.println("dump : " + dump);
				System.out.println("compile : " + compile);
				System.out.println("upload : " + upload);
				
				// 添加一个板型信息
				BDParameters.exBoardsList.add(boardInfo);
			} 
			catch (JsonIOException | JsonSyntaxException | FileNotFoundException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void parse2()
	{
		String user_root_path = System.getProperty("user.dir");
		//String path = user_root_path + "\\arduino-builder-windows\\boards\\boards_info.json";
		File file = new File(user_root_path + "\\arduino-builder-windows\\boards\\");
		
		// 遍历所有板型信息文件
		for(int j = 0; j < file.listFiles().length; j++)
		{
			String fileName = file.listFiles()[j].getName();
			
			String infoPath = user_root_path + "\\arduino-builder-windows\\boards\\" + fileName;
			
			String[] strArray = fileName.split("\\.");
			
			// 获取后缀名
			int suffixIndex = strArray.length - 1;
			
			String suffix = strArray[suffixIndex];
			
			// 如果不是json文件则忽略
			if(!suffix.equals("json"))
			{
				continue;
			}
		
			//System.out.println(fileName);
			
			// 创建JSON解析器
			JsonParser parser = new JsonParser();  
			
			try
			{
				JsonObject object = (JsonObject) parser.parse(new FileReader(infoPath));
				
				// 将json数据转为为String型的数据
				//System.out.println("board = " + object.get("board").getAsString()); 
				
				// 得到为json的数组
				JsonArray paras = object.get("paras").getAsJsonArray();
				
				BDBoardInfoModel boardInfo = new BDBoardInfoModel();
				
				boardInfo.setBoardName(object.get("board").getAsString());
				
				//BDParaModel paraList[] = new BDParaModel[array.size()];
				ArrayList<BDParaModel> paraList = new ArrayList<BDParaModel>();
				
				// 创建参数数组
				for(int i = 0; i < paras.size(); i++)
				{
	                JsonObject subObject = paras.get(i).getAsJsonObject();

	                BDParaModel para = new BDParaModel();
	                
	                para.setName(subObject.get("name").getAsString());
	                para.setValue(this.parsePath(subObject.get("value").getAsString()));
	                
	                if(subObject.get("value2") != null)
	                {
	                	para.setValue2(this.parsePath(subObject.get("value2").getAsString()));
	                }

	                paraList.add(para);
	            }
				
				ArrayList<String>hardwaveList = new ArrayList<String>();
				ArrayList<String>toolsList = new ArrayList<String>();
				
				for(int i = 0; i < paraList.size(); i++)
				{
					if(paraList.get(i).getName().equals("-hardware"))
					{
						boardInfo.getHardwaveList().add(paraList.get(i).getValue());
					}
					else if(paraList.get(i).getName().equals("-tools"))
					{
						boardInfo.getToolsList().add(paraList.get(i).getValue());
					}
					else if(paraList.get(i).getName().equals("-built-in-libraries"))
					{
						boardInfo.setBuilt_in_libraries(paraList.get(i).getValue());
					}
					else if(paraList.get(i).getName().equals("-libraries"))
					{
						boardInfo.setLibraries(paraList.get(i).getValue());
					}
					else if(paraList.get(i).getName().equals("-fqbn"))
					{
						boardInfo.setFqbn(paraList.get(i).getValue());
					}
					else if(paraList.get(i).getName().equals("-ide-version"))
					{
						boardInfo.setIde_version(paraList.get(i).getValue());
					}
					else if(paraList.get(i).getName().equals("-prefs"))
					{
						String value = paraList.get(i).getValue();
						String value2 = paraList.get(i).getValue2();
						
						boardInfo.getPrefsList().add("=" + value + "=" + value2);
					}
					else if(paraList.get(i).getName().equals("-C"))
					{
						boardInfo.setConfig_path(paraList.get(i).getValue());
					}
					else if(paraList.get(i).getName().equals("-v"))
					{
						boardInfo.setV(paraList.get(i).getValue());
					}
					else if(paraList.get(i).getName().equals("-p"))
					{
						boardInfo.setP(paraList.get(i).getValue());
					}
					else if(paraList.get(i).getName().equals("-c"))
					{
						boardInfo.setC(paraList.get(i).getValue());
					}
					else if(paraList.get(i).getName().equals("-b"))
					{
						boardInfo.setB(paraList.get(i).getValue());
					}
					else if(paraList.get(i).getName().equals("-D"))
					{
						boardInfo.setD(paraList.get(i).getValue());
					}
					else if(paraList.get(i).getName().equals("-U"))
					{
						boardInfo.setU(paraList.get(i).getValue());
					}
				}
				
				for(int i = 0; i < hardwaveList.size(); i++)
				{
					System.out.println(hardwaveList.get(i));
				}
				
				for(int i = 0; i < toolsList.size(); i++)
				{
					System.out.println(toolsList.get(i));
				}
				
				// 添加一个板型信息
				BDParameters.exBoardsList.add(boardInfo);
			} 
			catch (JsonIOException | JsonSyntaxException | FileNotFoundException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void parse()
	{
		String user_root_path = System.getProperty("user.dir");
		String path = user_root_path + "\\arduino-builder-windows\\boards_test.json";
		
		JsonParser parser = new JsonParser();  // 创建JSON解析器
		
		try
		{
			JsonObject object = (JsonObject) parser.parse(new FileReader(path));
			
			// 将json数据转为为String型的数据
			System.out.println("board = "+object.get("board").getAsString()); 
			
			// 得到为json的数组
			JsonArray paras = object.get("paras").getAsJsonArray();
			JsonArray dumpCmd = object.get("dump_cmd").getAsJsonArray();
			
			//BDParaModel paraList[] = new BDParaModel[array.size()];
			ArrayList<BDParaModel> paraList = new ArrayList<BDParaModel>();
			ArrayList<BDParaModel> dumpCmdList = new ArrayList<BDParaModel>();
			
			// 创建参数数组
			for(int i = 0; i < paras.size(); i++)
			{
                JsonObject subObject = paras.get(i).getAsJsonObject();

                BDParaModel para = new BDParaModel();
                
                para.setName(subObject.get("name").getAsString());
                para.setValue(subObject.get("value").getAsString());
                
                paraList.add(para);
            }
			
			// 创建命令数组
			for(int i = 0; i < dumpCmd.size(); i++)
			{
                JsonObject subObject = dumpCmd.get(i).getAsJsonObject();

                BDParaModel para = new BDParaModel();
                
                para.setName(subObject.get("name").getAsString());
                para.setValue(subObject.get("value").getAsString());
                
                dumpCmdList.add(para);
            }
			
			String cmd = "";
			
			for(int j = 0; j < dumpCmdList.size(); j++)
			{
				String segment = "";
				
				if(dumpCmdList.get(j).getName().equals("para"))
				{
					// 获取参数
					String key = dumpCmdList.get(j).getValue();
					
					for(int i = 0; i < paraList.size(); i++)
					{
						if(paraList.get(i).getName().equals(key))
						{
							if(paraList.get(i).getValue().length() >= 2)
							{
								if(paraList.get(i).getValue().substring(0, 2).equals("./"))
								{
									// 解析路径
									segment = user_root_path + paraList.get(i).getValue().substring(1).replaceAll("/", Matcher.quoteReplacement(File.separator));
								}
								else
								{
									segment = paraList.get(i).getValue();
								}
							}
						}
					}
				}
				else if(dumpCmdList.get(j).getName().equals("cmd"))
				{
					// 获取命令
					segment = dumpCmdList.get(j).getValue();
				}
				
				// 组合命令片段
				cmd += segment;
			}
			
			System.out.println("CMD IS : " + cmd);
		} 
		catch (JsonIOException | JsonSyntaxException | FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
