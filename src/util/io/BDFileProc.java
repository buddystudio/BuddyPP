package util.io;

import java.io.File;

public class BDFileProc
{
	public static boolean deleteDir(String path)
	{
		File file = new File(path);
		
		if(!file.exists())
		{
			//判断是否待删除目录是否存在
			System.err.println("The dir are not exists!");
			
			return false;
		}
		
		String[] content = file.list();//取得当前目录下所有文件和文件夹
		
		for(String name : content)
		{
			File temp = new File(path, name);
			
			if(temp.isDirectory())
			{
				//判断是否是目录
				deleteDir(temp.getAbsolutePath());//递归调用，删除目录里的内容
				
				temp.delete();//删除空目录
			}
			else
			{
				if(!temp.delete())
				{
					//直接删除文件
					System.err.println("Failed to delete " + name);
				}
			}
		}
		return true;
	}
	
	public BDFileProc()
	{
		
	}

}
