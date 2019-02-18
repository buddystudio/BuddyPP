package util.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import controller.BDWorkspaceCtrl;
import javafx.stage.FileChooser;
import model.BDCodeModel;

public class BDFileProc
{
	public static BDCodeModel openFile()
	{
		BDCodeModel code = null;
		
		// 打开文件
		File file;
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilterTXT = new FileChooser.ExtensionFilter("Text  (*.txt)", "*.txt");
		FileChooser.ExtensionFilter extFilterINO = new FileChooser.ExtensionFilter("Arduino  (*.ino)", "*.ino");
		FileChooser.ExtensionFilter extFilterCPP = new FileChooser.ExtensionFilter("C++  (*.cpp)", "*.cpp");
		FileChooser.ExtensionFilter extFilterC = new FileChooser.ExtensionFilter("C  (*.c)", "*.c");
		FileChooser.ExtensionFilter extFilterH = new FileChooser.ExtensionFilter("Head Files  (*.h)", "*.h");
		FileChooser.ExtensionFilter extFilterPY = new FileChooser.ExtensionFilter("Python  (*.py)", "*.py");
		FileChooser.ExtensionFilter extFilterAll = new FileChooser.ExtensionFilter("All Files  (*.*)", "*.*");

		fileChooser.getExtensionFilters().add(extFilterINO);
		fileChooser.getExtensionFilters().add(extFilterTXT);
		fileChooser.getExtensionFilters().add(extFilterCPP);
		fileChooser.getExtensionFilters().add(extFilterC);
		fileChooser.getExtensionFilters().add(extFilterH);
		fileChooser.getExtensionFilters().add(extFilterPY);
		fileChooser.getExtensionFilters().add(extFilterAll);

		// Show open file dialog
		file = fileChooser.showOpenDialog(null);
		
		if(file == null)
		{
			return code;
		}
		
		String suffix = file.getName().substring(file.getName().lastIndexOf(".") + 1);
		
		if(suffix.equals("ino"))
		{
			code = new BDCodeModel("INO");
		}
		else if(suffix.equals("py"))
		{
			code = new BDCodeModel("PY");
		}

		code.setName(file.getName());

		try 
		{
			//code.setCodeText(BDCodeReader.readFileByLines2(file.getPath()));
			code.setCodeText(BDCodeReader.readFileByLines(file.getPath()));

			// 写入文件路径
			code.path = file.getPath();
		} 
		catch (FileNotFoundException ex) 
		{
			//logger.error(ex.getMessage());
		} 
		catch (IOException ex) 
		{
			//logger.error(ex.getMessage());
		}
		
		return code;
	}
	
	// 保存文件
	public static boolean saveFile(BDWorkspaceCtrl workspaceCtrl) 
	{
		String code = workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.getCode();
		workspaceCtrl.workspaceView.workspaceModel.curTab.code.setCodeText(code);
		
		try 
		{
			// 写入文件
			BDCodeWriter.fileWriter(workspaceCtrl.workspaceView.workspaceModel.curTab.code.path, code);
			
			// 更改保存状态
			workspaceCtrl.workspaceView.workspaceModel.curTab.code.isSaved = true;

		} 
		catch (IOException ex) 
		{
			//logger.error(this.toString(), ex);
			return false;
		}
		
		return true;
	}
	
	public static boolean saveAsFile(BDWorkspaceCtrl workspaceCtrl) 
	{
		try 
		{
			File file = null;
			FileChooser fileChooser = new FileChooser();
	
			// Set extension filter
			FileChooser.ExtensionFilter extFilterTXT 	= new FileChooser.ExtensionFilter("Text  (*.txt)", "*.txt");
			FileChooser.ExtensionFilter extFilterINO 	= new FileChooser.ExtensionFilter("Arduino  (*.ino)", "*.ino");
			FileChooser.ExtensionFilter extFilterCPP 	= new FileChooser.ExtensionFilter("C++  (*.cpp)", "*.cpp");
			FileChooser.ExtensionFilter extFilterC 		= new FileChooser.ExtensionFilter("C  (*.c)", "*.c");
			FileChooser.ExtensionFilter extFilterPY 	= new FileChooser.ExtensionFilter("Python  (*.py)", "*.py");
			FileChooser.ExtensionFilter extFilterH 		= new FileChooser.ExtensionFilter("Head Files (*.h)", "*.h");
	
			fileChooser.getExtensionFilters().add(extFilterINO);
			fileChooser.getExtensionFilters().add(extFilterTXT);
			fileChooser.getExtensionFilters().add(extFilterCPP);
			fileChooser.getExtensionFilters().add(extFilterC);
			fileChooser.getExtensionFilters().add(extFilterH);
			fileChooser.getExtensionFilters().add(extFilterPY);
			
			fileChooser.setInitialFileName(workspaceCtrl.workspaceView.workspaceModel.curTab.code.getName());
			
			// 文件类型选PY
			if(workspaceCtrl.workspaceView.workspaceModel.curTab.code.type.equals("PY"))
			{
				fileChooser.setSelectedExtensionFilter(extFilterPY);
			}

			try
			{
				// Show open file dialog
				file = fileChooser.showSaveDialog(null);
			}
			catch(Exception ex){}

			if(file == null)
			{
				return false;
			}

			// Write file
			BDCodeWriter.fileWriter(file.getPath(),
				//workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getText());
				workspaceCtrl.workspaceView.workspaceModel.curTab.editorCtrl.getCode());
				// Update the file name.
				workspaceCtrl.workspaceView.workspaceModel.curTab.code.path = file.getPath();
				// Update the tab name.
				workspaceCtrl.workspaceView.workspaceModel.curTab.tab.setText(file.getName());
				// Update the tab state
				workspaceCtrl.workspaceView.workspaceModel.curTab.code.isSaved = true;
		} 
		catch (IOException ex) 
		{
			//logger.error(this.toString(), ex);
		}
		
		return true;
	}
	
	public static boolean deleteDir(String path)
	{
		File file = new File(path);
		
		if(!file.exists())
		{
			//判断是否待删除目录是否存在
			//System.err.println("The dir are not exists!");
			
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
