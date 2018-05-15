package util.debug;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import util.base.Base;

public class BDInoCode 
{
	private static final Logger logger = LogManager.getLogger();

	public String codeText 	= ""; 				// 程序代码文本
	public String name 		= ""; 				// 程序代码源文件名称
	public String className = ""; 				// 类名
	private ArrayList<File> importedLibraries; 	// 导入库列表

	public BDInoCode(){}
	
	public BDInoCode(String code)
	{
		codeText=code;
	}
	
	public void setCodeText(String code)
	{
		codeText = code;
	}
	
	public String getCodeText()
	{
		return codeText;
	}
	
	public String preprocess(String buildPath, BDPreprocessor preprocessor) throws BDRunnerException {
		// make sure the user didn't hide the sketch folder
		// ensureExistence();

		String[] codeFolderPackages = null;		
		//StringBuffer bigCode = new StringBuffer();
		
		int headerOffset = 0;
		// PdePreprocessor preprocessor = new PdePreprocessor();
		try 
		{
			headerOffset = preprocessor.writePrefix(codeText, buildPath, className, codeFolderPackages);
		} 
		catch (FileNotFoundException fnfe) 
		{
			logger.error("", fnfe);
			String msg = "Build folder disappeared or could not be written";
			throw new BDRunnerException(msg);
		}

		// 2. run preproc on that code using the sugg class name
		// to create a single .java file and write to buildpath
		String primaryClassName = null;

		try 
		{			
			String className = preprocessor.write();
			
			if (className == null) 
			{
				throw new BDRunnerException("Could not find main class");
			}

			// store this for the compiler and the runtime
			primaryClassName = className + ".cpp";

		} 
		catch (FileNotFoundException fnfe) 
		{
			logger.error("", fnfe);
			String msg = "Build folder disappeared or could not be written";
			throw new BDRunnerException(msg);
		} 
		catch (BDRunnerException pe) 
		{			
			throw pe;
		} 
		catch (Exception ex) 
		{			
			logger.error("", ex);
			throw new BDRunnerException(ex.toString());
		}

		// grab the imports from the code just preproc'd
		importedLibraries = new ArrayList<File>();

		for (String item : preprocessor.getExtraImports()) 
		{
			File libFolder = (File) Base.importToLibraryTable.get(item);

			if (libFolder != null && !importedLibraries.contains(libFolder)) 
			{
				importedLibraries.add(libFolder);				
			}
		}
		
		return primaryClassName;
	}

	public ArrayList<File> getImportedLibraries() 
	{
		return importedLibraries;
	}

	public List<String> getImportedLibrariesPath() 
	{
		List<String> includePaths = new ArrayList<>();
		
		getImportedLibraries().stream().forEach((file) -> {
			includePaths.add(file.getPath());
		});
		
		return includePaths;
	}
}
