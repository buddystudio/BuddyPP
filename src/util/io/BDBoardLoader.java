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
		this.parse();
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
	
	public void parse()
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
			if(!suffix.equals("json"))
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
				
				// 得到为json的数组
				JsonArray paras = object.get("paras").getAsJsonArray();
				
				ArrayList<BDParaModel> paraList = new ArrayList<BDParaModel>();
				
				// 创建参数数组
				for(int i = 0; i < paras.size(); i++)
				{
	                JsonObject subObject = paras.get(i).getAsJsonObject();

	                BDParaModel para = new BDParaModel();
	                
	                para.setName(subObject.get("name").getAsString());
	                para.setValue(this.parsePath(subObject.get("value").getAsString()));
	                
	                paraList.add(para);

	            }
				
				for(int i = 0; i < paraList.size(); i++)
				{
					System.out.println(paraList.get(i).getName() + paraList.get(i).getValue());
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
}
