package util.debug;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import util.base.Base;
import util.base.Preferences;

/**
 *
 * @author Boniz
 */
public class BDCompiler implements BDMessageConsumer 
{
	private static final Logger logger = LogManager.getLogger(BDCompiler.class);

	String buildPath; 							// 生成路径
	String primaryClassName;

	boolean verbose; 							// 错误内容是否有动
	boolean sketchIsCompiled; 					// 是否已经编译
	BDRunnerException exception;
	BDProgressStatusListener progressListener; // 进度侦听

	public BDCompiler() {}

	public void addProgressStatusListener(BDProgressStatusListener listener) 
	{
		progressListener = listener;
	}

	@Override
	public void message(String s) 
	{
		int i;

		// remove the build path so people only see the filename
		// can't use replaceAll() because the path may have characters in it
		// which
		// have meaning in a regular expression.
		// if (!verbose) {
		// String buildPath = Base.getBoardPreferences().get("build.path");
		while ((i = s.indexOf(buildPath + File.separator)) != -1) {
			s = s.substring(0, i) + s.substring(i + (buildPath + File.separator).length());
		}
		// }

		// look for error line, which contains file name, line number,
		// and at least the first line of the error message
		String errorFormat = "([\\w\\d_]+.\\w+):(\\d+):\\s*error:\\s*(.*)\\s*";
		String[] pieces = s.split(errorFormat);

		// if (pieces != null && exception == null) {
		// exception = sketch.placeException(pieces[3], pieces[1],
		// PApplet.parseInt(pieces[2]) - 1);
		// if (exception != null) exception.hideStackTrace();
		// }
		/*
		 * if (pieces != null) { String error = pieces[3], msg = "";
		 * 
		 * if (pieces[3].trim().equals("SPI.h: No such file or directory")) {
		 * error =
		 * "Please import the SPI library from the Sketch > Import Library menu."
		 * ; msg =
		 * "\nAs of Arduino 0019, the Ethernet library depends on the SPI library."
		 * +
		 * "\nYou appear to be using it or another library that depends on the SPI library.\n\n"
		 * ; }
		 * 
		 * if (pieces[3].trim().equals("'BYTE' was not declared in this scope"))
		 * { error = "The 'BYTE' keyword is no longer supported."; msg =
		 * "\nAs of Arduino 1.0, the 'BYTE' keyword is no longer supported." +
		 * "\nPlease use Serial.write() instead.\n\n"; }
		 * 
		 * if (pieces[3].trim().equals(
		 * "no matching function for call to 'Server::Server(int)'")) { error =
		 * "The Server class has been renamed EthernetServer."; msg =
		 * "\nAs of Arduino 1.0, the Server class in the Ethernet library " +
		 * "has been renamed to EthernetServer.\n\n"; }
		 * 
		 * if (pieces[3].trim().equals(
		 * "no matching function for call to 'Client::Client(byte [4], int)'"))
		 * { error ="The Client class has been renamed EthernetClient."; msg =
		 * "\nAs of Arduino 1.0, the Client class in the Ethernet library " +
		 * "has been renamed to EthernetClient.\n\n"; }
		 * 
		 * if (pieces[3].trim().equals("'Udp' was not declared in this scope"))
		 * { error ="The Udp class has been renamed EthernetUdp."; msg =
		 * "\nAs of Arduino 1.0, the Udp class in the Ethernet library " +
		 * "has been renamed to EthernetUdp.\n\n"; }
		 * 
		 * if (pieces[3].trim().equals(
		 * "'class TwoWire' has no member named 'send'")) { error =
		 * "Wire.send() has been renamed Wire.write()."; msg =
		 * "\nAs of Arduino 1.0, the Wire.send() function was renamed " +
		 * "to Wire.write() for consistency with other libraries.\n\n"; }
		 * 
		 * if (pieces[3].trim().equals(
		 * "'class TwoWire' has no member named 'receive'")) { error =
		 * "Wire.receive() has been renamed Wire.read()."; msg =
		 * "\nAs of Arduino 1.0, the Wire.receive() function was renamed " +
		 * "to Wire.read() for consistency with other libraries.\n\n"; }
		 * 
		 * if (pieces[3].trim().equals("'Mouse' was not declared in this scope"
		 * )) { error = "'Mouse' only supported on the Arduino Leonardo"; //msg
		 * = _(
		 * "\nThe 'Mouse' class is only supported on the Arduino Leonardo.\n\n"
		 * ); }
		 * 
		 * if (pieces[3].trim().equals(
		 * "'Keyboard' was not declared in this scope")) { error =
		 * "'Keyboard' only supported on the Arduino Leonardo"; //msg = _(
		 * "\nThe 'Keyboard' class is only supported on the Arduino Leonardo.\n\n"
		 * ); }
		 * 
		 * RunnerException e = null; if (!sketchIsCompiled) { // Place errors
		 * when compiling the sketch, but never while compiling libraries // or
		 * the core. The user's sketch might contain the same filename! e = new
		 * RunnerException(error); }
		 * 
		 * // replace full file path with the name of the sketch tab (unless
		 * we're // in verbose mode, in which case don't modify the compiler
		 * output) if (e != null && !verbose) { //SketchCode code =
		 * sketch.getCode(e.getCodeIndex()); //String fileName =
		 * (code.isExtension("ino") || code.isExtension("pde")) ?
		 * code.getPrettyName() : code.getFileName(); int lineNum =
		 * e.getCodeLine() + 1; s = primaryClassName + ":" + lineNum +
		 * ": error: " + pieces[3] + msg; }
		 * 
		 * if (exception == null && e != null) { exception = e;
		 * exception.hideStackTrace(); } }
		 * 
		 * if (s.contains("undefined reference to `SPIClass::begin()'") &&
		 * s.contains("libraries/Robot_Control")) { String error =
		 * "Please import the SPI library from the Sketch > Import Library menu."
		 * ; exception = new RunnerException(error); }
		 * 
		 * if (s.contains("undefined reference to `Wire'") &&
		 * s.contains("libraries/Robot_Control")) { String error =
		 * "Please import the Wire library from the Sketch > Import Library menu."
		 * ; exception = new RunnerException(error); }
		 */
		System.err.print(s);
	}

	public void notifyProgressEvent(int progressNumber) {
		if (progressListener == null)
			return;

		progressListener.ProgressEventHandler(new BDProgressStatusEvent(this, BDProgressType.Compile, progressNumber));
	}

	/**
	 * Compile with avr-gcc.
	 *
	 * @param sketch
	 *            Sketch object to be compiled.
	 * @param buildPath
	 *            Where the temporary files live and will be built from.
	 * @param primaryClassName
	 *            the name of the combined sketch file w/ extension
	 * @return true if successful.
	 * @throws BDRunnerException
	 *             Only if there's a problem. Only then.
	 */
	public boolean compile(String sourcePath, String buildPath, String primaryClassName, List<File> importedLibraries,
			boolean verbose) throws BDRunnerException {

		this.buildPath = buildPath;
		this.primaryClassName = primaryClassName;
		this.verbose = verbose;
		this.sketchIsCompiled = false;

		String avrBasePath = Base.getAvrBasePath();

		Map<String, String> boardPreferences = Base.getBoardPreferences();
		String core = boardPreferences.get("build.core");

		if (core == null) {
			BDRunnerException re = new BDRunnerException(
					"No board selected; please choose a board from the Tools > Board menu.");

			re.hideStackTrace();
			throw re;
		}

		String corePath;
		notifyProgressEvent(10);

		if (core.indexOf(':') == -1) {
			BDTarget t = Base.getTarget();
			File coreFolder = new File(new File(t.getFolder(), "cores"), core);
			corePath = coreFolder.getAbsolutePath();
		} else {
			BDTarget t = Base.targetsTable.get(core.substring(0, core.indexOf(':')));
			File coreFolder = new File(t.getFolder(), "cores");
			coreFolder = new File(coreFolder, core.substring(core.indexOf(':') + 1));
			corePath = coreFolder.getAbsolutePath();
		}

		String variant = boardPreferences.get("build.variant");
		String variantPath = null;

		if (variant != null) {
			if (variant.indexOf(':') == -1) {
				BDTarget t = Base.getTarget();
				File variantFolder = new File(new File(t.getFolder(), "variants"), variant);
				variantPath = variantFolder.getAbsolutePath();
			} else {
				BDTarget t = Base.targetsTable.get(variant.substring(0, variant.indexOf(':')));
				File variantFolder = new File(t.getFolder(), "variants");
				variantFolder = new File(variantFolder, variant.substring(variant.indexOf(':') + 1));
				variantPath = variantFolder.getAbsolutePath();
			}
		}

		notifyProgressEvent(20);

		List<File> objectFiles = new ArrayList<File>();
		List<String> includePaths = new ArrayList<String>();

		includePaths.add(corePath);
		if (variantPath != null) {
			includePaths.add(variantPath);
		}

		for (File file : importedLibraries) {
			includePaths.add(file.getPath());
		}

		// 1编译
		notifyProgressEvent(30);

		objectFiles.addAll(compileFiles(avrBasePath, buildPath, includePaths, findFilesInPath(buildPath, "S", false),
				findFilesInPath(buildPath, "c", true), findFilesInPath(buildPath, "cpp", false), boardPreferences));

		notifyProgressEvent(40);

		for (File libraryFolder : importedLibraries) {
			File outputFolder = new File(buildPath, libraryFolder.getName());
			File utilityFolder = new File(libraryFolder, "utility");
			createFolder(outputFolder);

			// this library can use includes in its utility/ folder
			includePaths.add(utilityFolder.getAbsolutePath());
			objectFiles.addAll(compileFiles(avrBasePath, outputFolder.getAbsolutePath(), includePaths,
					findFilesInFolder(libraryFolder, "S", false), findFilesInFolder(libraryFolder, "c", false),
					findFilesInFolder(libraryFolder, "cpp", false), boardPreferences));
			outputFolder = new File(outputFolder, "utility");
			createFolder(outputFolder);
			objectFiles.addAll(compileFiles(avrBasePath, outputFolder.getAbsolutePath(), includePaths,
					findFilesInFolder(utilityFolder, "S", false), findFilesInFolder(utilityFolder, "c", false),
					findFilesInFolder(utilityFolder, "cpp", false), boardPreferences));
			// other libraries should not see this library's utility/ folder
			includePaths.remove(includePaths.size() - 1);
		}

		// 2链接
		notifyProgressEvent(50);

		includePaths.clear();
		includePaths.add(corePath); // include path for core only

		if (variantPath != null) {
			includePaths.add(variantPath);
		}

		List<File> coreObjectFiles = compileFiles(avrBasePath, buildPath, includePaths,
				findFilesInPath(corePath, "S", true), findFilesInPath(corePath, "c", true),
				findFilesInPath(corePath, "cpp", true), boardPreferences);

		String runtimeLibraryName = buildPath + File.separator + "core.a";
		List<String> baseCommandAR = new ArrayList<String>(
				Arrays.asList(new String[] { avrBasePath + "avr-ar", "rcs", runtimeLibraryName }));

		notifyProgressEvent(60);

		for (File file : coreObjectFiles) {
			List<String> commandAR = new ArrayList<String>(baseCommandAR);

			commandAR.add(file.getAbsolutePath());
			execAsynchronously(commandAR);
		}

		// 4. link it all together into the .elf file
		// For atmega2560, need --relax linker option to link larger
		// programs correctly.
		String optRelax = "";
		String atmega2560 = new String("atmega2560");

		if (atmega2560.equals(boardPreferences.get("build.mcu"))) {
			optRelax = new String(",--relax");
		}

		notifyProgressEvent(70);

		List<String> baseCommandLinker = new ArrayList<String>(Arrays.asList(new String[] { avrBasePath + "avr-gcc",
				"-Os", "-Wl,--gc-sections" + optRelax, "-mmcu=" + boardPreferences.get("build.mcu"), "-o",
				buildPath + File.separator + primaryClassName + ".elf" }));

		for (File file : objectFiles) {
			baseCommandLinker.add(file.getAbsolutePath());
		}

		baseCommandLinker.add(runtimeLibraryName);
		baseCommandLinker.add("-L" + buildPath);
		baseCommandLinker.add("-lm");

		execAsynchronously(baseCommandLinker);

		List<String> baseCommandObjcopy = new ArrayList<String>(
				Arrays.asList(new String[] { avrBasePath + "avr-objcopy", "-O", "-R", }));

		List<String> commandObjcopy = new ArrayList<String>(baseCommandObjcopy);
		;

		// 5. extract EEPROM data (from EEMEM directive) to .eep file.
		notifyProgressEvent(80);

		commandObjcopy.add(2, "ihex");
		commandObjcopy.set(3, "-j");
		commandObjcopy.add(".eeprom");
		commandObjcopy.add("--set-section-flags=.eeprom=alloc,load");
		commandObjcopy.add("--no-change-warnings");
		commandObjcopy.add("--change-section-lma");
		commandObjcopy.add(".eeprom=0");
		commandObjcopy.add(buildPath + File.separator + primaryClassName + ".elf");
		commandObjcopy.add(buildPath + File.separator + primaryClassName + ".eep");
		execAsynchronously(commandObjcopy);

		// 6. build the .hex file
		notifyProgressEvent(90);

		commandObjcopy = new ArrayList<String>(baseCommandObjcopy);
		commandObjcopy.add(2, "ihex");
		commandObjcopy.add(".eeprom"); // remove eeprom data
		commandObjcopy.add(buildPath + File.separator + primaryClassName + ".elf");
		commandObjcopy.add(buildPath + File.separator + primaryClassName + ".hex");
		execAsynchronously(commandObjcopy);

		notifyProgressEvent(100);

		return true;
	}

	static private List<String> getCommandCompilerCPP(String avrBasePath, List<String> includePaths, String sourceName,
			String objectName, Map<String, String> boardPreferences) {

		List<String> baseCommandCompilerCPP = new ArrayList<String>(
				Arrays.asList(new String[] { avrBasePath + "avr-g++", "-c", // compile,don't
																			// link
						"-g", // include debugging info (so errors include line
								// numbers)
						"-Os", // optimize for size
						"-Wall", Preferences.getBoolean("build.verbose") ? "-Wall" : "-w",
						// show warnings if verbose
						"-fno-exceptions", "-ffunction-sections", // place each
																	// function
																	// in its
																	// own
																	// section
						"-fdata-sections", "-mmcu=" + boardPreferences.get("build.mcu"),
						"-DF_CPU=" + boardPreferences.get("build.f_cpu"), "-MMD", // output
																					// dependancy
																					// info
						"-DUSB_VID=" + boardPreferences.get("build.vid"),
						"-DUSB_PID=" + boardPreferences.get("build.pid"), "-DARDUINO=" + Base.REVISION }));

		for (int i = 0; i < includePaths.size(); i++) {
			baseCommandCompilerCPP.add("-I" + (String) includePaths.get(i));
		}

		baseCommandCompilerCPP.add(sourceName);
		baseCommandCompilerCPP.add("-o");
		baseCommandCompilerCPP.add(objectName);

		return baseCommandCompilerCPP;
	}

	static private List<String> getCommandCompilerS(String avrBasePath, List includePaths, String sourceName,
			String objectName, Map<String, String> boardPreferences) {
		List<String> baseCommandCompiler = new ArrayList<String>(Arrays.asList(new String[] { avrBasePath + "avr-gcc",
				"-c", // compile,don't link // link
				"-g", // include debugging info (so errors include line numbers)
				"-x", "-mmcu=" + boardPreferences.get("build.mcu"), "-DF_CPU=" + boardPreferences.get("build.f_cpu"),
				"-DARDUINO=" + Base.REVISION, "-DUSB_VID=" + boardPreferences.get("build.vid"),
				"-DUSB_PID=" + boardPreferences.get("build.pid"), }));

		for (int i = 0; i < includePaths.size(); i++) {
			baseCommandCompiler.add("-I" + (String) includePaths.get(i));
		}

		baseCommandCompiler.add(sourceName);
		baseCommandCompiler.add("-o " + objectName);

		return baseCommandCompiler;
	}

	static private List<String> getCommandCompilerC(String avrBasePath, List<String> includePaths, String sourceName,
			String objectName, Map<String, String> boardPreferences) {

		List<String> baseCommandCompiler = new ArrayList<String>(
				Arrays.asList(new String[] { avrBasePath + "avr-gcc", "-c", // compile,
																			// don't
																			// link
						"-g", // include debugging info (so errors include line
								// numbers)
						"-Os", // optimize for size
						Preferences.getBoolean("build.verbose") ? "-Wall" : "-w", // show
																					// warnings
																					// if
																					// verbose
						"-ffunction-sections", // place each function in its own
												// section
						"-fdata-sections", "-mmcu=" + boardPreferences.get("build.mcu"),
						"-DF_CPU=" + boardPreferences.get("build.f_cpu"), "-MMD", // output
																					// dependancy
																					// info
						"-DUSB_VID=" + boardPreferences.get("build.vid"),
						"-DUSB_PID=" + boardPreferences.get("build.pid"), "-DARDUINO=" + Base.REVISION }));

		for (int i = 0; i < includePaths.size(); i++) {
			baseCommandCompiler.add("-I" + (String) includePaths.get(i));
		}

		baseCommandCompiler.add(sourceName);
		baseCommandCompiler.add("-o");
		baseCommandCompiler.add(objectName);

		return baseCommandCompiler;
	}

	/**
	 * Either succeeds or throws a RunnerException fit for public consumption.
	 */
	private void execAsynchronously(List<String> commandList) throws BDRunnerException {
		String[] command = new String[commandList.size()];

		commandList.toArray(command);
		int result = 0;

		// boolean firstErrorFound = false; // haven't found any errors yet
		// boolean secondErrorFound = false;
		Process process;

		try {
			System.out.println(getStr(command));
			process = Runtime.getRuntime().exec(command);

		} catch (IOException e) {
			BDRunnerException re = new BDRunnerException(e.getMessage());

			re.hideStackTrace();
			throw re;
		}

		BDMessageSiphon in = new BDMessageSiphon(process.getInputStream(), this);
		BDMessageSiphon err = new BDMessageSiphon(process.getErrorStream(), this);

		// wait for the process to finish. if interrupted
		// before waitFor returns, continue waiting
		boolean compiling = true;

		while (compiling) {
			try {
				in.join();
				err.join();
				result = process.waitFor();
				compiling = false;
			} catch (InterruptedException ignored) {
			}
		}

		// an error was queued up by message(), barf this back to compile(),
		// which will barf it back to Editor. if you're having trouble
		// discerning the imagery, consider how cows regurgitate their food
		// to digest it, and the fact that they have five stomaches.
		//
		// System.out.println("throwing up " + exception);
		if (exception != null) {
			throw exception;
		}

		if (result > 1) {
			// a failure in the tool (e.g. unable to locate a sub-executable)
			System.err.println(String.format("{0} returned {1}", command[0], result));
		}

		if (result != 0) {
			BDRunnerException re = new BDRunnerException("Error compiling.");

			re.hideStackTrace();
			throw re;
		}
	}

	private List<File> compileFiles(String avrBasePath, String buildPath, List<String> includePaths,
			List<File> sSources, List<File> cSources, List<File> cppSources, Map<String, String> boardPreferences)
			throws BDRunnerException {

		List<File> objectPaths = new ArrayList<File>();

		for (File file : sSources) {
			String objectPath = buildPath + File.separator + file.getName() + ".o";
			objectPaths.add(new File(objectPath));
			execAsynchronously(getCommandCompilerS(avrBasePath, includePaths, file.getAbsolutePath(), objectPath,
					boardPreferences));
		}

		for (File file : cSources) {
			String objectPath = buildPath + File.separator + file.getName() + ".o";
			String dependPath = buildPath + File.separator + file.getName() + ".d";
			File objectFile = new File(objectPath);
			File dependFile = new File(dependPath);
			objectPaths.add(objectFile);
			/*
			 * if (is_already_compiled(file, objectFile, dependFile,
			 * boardPreferences)) { continue; }
			 */
			execAsynchronously(getCommandCompilerC(avrBasePath, includePaths, file.getAbsolutePath(), objectPath,
					boardPreferences));
		}

		for (File file : cppSources) {
			String objectPath = buildPath + File.separator + file.getName() + ".o";
			String dependPath = buildPath + File.separator + file.getName() + ".d";
			File objectFile = new File(objectPath);
			File dependFile = new File(dependPath);
			objectPaths.add(objectFile);
			/*
			 * if (is_already_compiled(file, objectFile, dependFile,
			 * boardPreferences)) { continue; }
			 */
			execAsynchronously(getCommandCompilerCPP(avrBasePath, includePaths, file.getAbsolutePath(), objectPath,
					boardPreferences));
		}

		return objectPaths;
	}

	/*
	 * private List<File> compileFiles(String avrBasePath, String buildPath,
	 * List<String> includePaths, List<File> cSources, List<File> cppSources,
	 * Map<String, String> boardPreferences) throws BDRunnerException {
	 * List<File> objectPaths = new ArrayList<File>();
	 * 
	 * for (File file : cSources) { String objectPath = buildPath +
	 * File.separator + file.getName() + ".o"; String dependPath = buildPath +
	 * File.separator + file.getName() + ".d"; File objectFile = new
	 * File(objectPath); File dependFile = new File(dependPath);
	 * 
	 * objectPaths.add(objectFile); if (is_already_compiled(file, objectFile,
	 * dependFile, boardPreferences)) { continue; }
	 * execAsynchronously(getCommandCompilerC(avrBasePath, includePaths,
	 * file.getAbsolutePath(), objectPath, boardPreferences)); }
	 * 
	 * for (File file : cppSources) { String objectPath = buildPath +
	 * File.separator + file.getName() + ".o"; String dependPath = buildPath +
	 * File.separator + file.getName() + ".d"; File objectFile = new
	 * File(objectPath); File dependFile = new File(dependPath);
	 * 
	 * objectPaths.add(objectFile); if (is_already_compiled(file, objectFile,
	 * dependFile, boardPreferences)) { continue; }
	 * execAsynchronously(getCommandCompilerCPP(avrBasePath, includePaths,
	 * file.getAbsolutePath(), objectPath, boardPreferences)); }
	 * 
	 * return objectPaths; }
	 */

	static public ArrayList<File> findFilesInPath(String path, String extension, boolean recurse) {
		return findFilesInFolder(new File(path), extension, recurse);
	}

	static public ArrayList<File> findFilesInFolder(File folder, String extension, boolean recurse) {
		ArrayList<File> files = new ArrayList<File>();

		if (folder.listFiles() == null) {
			return files;
		}

		for (File file : folder.listFiles()) {
			if (file.getName().startsWith(".")) {
				continue;
			} // skip hidden files

			if (file.getName().endsWith("." + extension)) {
				files.add(file);
			}

			if (recurse && file.isDirectory()) {
				files.addAll(findFilesInFolder(file, extension, true));
			}
		}
		return files;
	}

	private boolean is_already_compiled(File src, File obj, File dep, Map<String, String> prefs) {
		boolean ret = true;

		try {
			// System.out.println("\n is_already_compiled: begin checks: " +
			// obj.getPath());
			if (!obj.exists()) {
				return false;
			} // object file (.o) does not exist
			if (!dep.exists()) {
				return false;
			} // dep file (.d) does not exist
			long src_modified = src.lastModified();
			long obj_modified = obj.lastModified();

			if (src_modified >= obj_modified) {
				return false;
			} // source modified since object compiled
			if (src_modified >= dep.lastModified()) {
				return false;
			} // src modified since dep compiled
			BufferedReader reader = new BufferedReader(new FileReader(dep.getPath()));
			String line;
			boolean need_obj_parse = true;

			while ((line = reader.readLine()) != null) {
				if (line.endsWith("\\")) {
					line = line.substring(0, line.length() - 1);
				}
				line = line.trim();
				if (line.length() == 0) {
					continue;
				} // ignore blank lines
				if (need_obj_parse) {
					// line is supposed to be the object file - make sure it
					// really is!
					if (line.endsWith(":")) {
						line = line.substring(0, line.length() - 1);
						String objpath = obj.getCanonicalPath();
						File linefile = new File(line);
						String linepath = linefile.getCanonicalPath();
						
						if (objpath.compareTo(linepath) == 0) {
							need_obj_parse = false;
							continue;
						} else {
							ret = false; // object named inside .d file is not
											// the correct file!
							break;
						}
					} else {
						ret = false; // object file supposed to end with ':',
										// but didn't
						break;
					}
				} else {
					// line is a prerequisite file
					File prereq = new File(line);

					if (!prereq.exists()) {
						ret = false; // prerequisite file did not exist
						break;
					}
					if (prereq.lastModified() >= obj_modified) {
						ret = false; // prerequisite modified since object was
										// compiled
						break;
					}
					// System.out.println(" is_already_compiled: prerequisite
					// ok");
				}
			}
			reader.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false; // any error reading dep file = recompile it
		}
		if (ret && (verbose || Preferences.getBoolean("build.verbose"))) {
			System.out.println("Using previously compiled: " + obj.getPath());
		}
		return ret;
	}

	public String getStr(String[] args) {
		String str = "";

		for (int i = 0; i < args.length; i++) {
			str += (String) args[i] + " ";
		}
		return str;
	}

	static private void createFolder(File folder) throws BDRunnerException {
		if (folder.isDirectory()) {
			return;
		}
		if (!folder.mkdir()) {
			throw new BDRunnerException("Couldn't create: " + folder);
		}
	}

	static public String[] headerListFromIncludePath(String path) throws IOException {
		FilenameFilter onlyHFiles = (File dir, String name) -> name.endsWith(".h");
		String[] list = (new File(path)).list(onlyHFiles);
		if (list == null) {
			throw new IOException();
		}
		return list;
	}
	
	// 创建编译目录，并清理原有内容。buildPath为编译文件存放路径	
	static public boolean clearBuildPath(String buildPath) {
		if (buildPath == null || buildPath == "")
			return false;
		
		File cpath = new File(buildPath);
		if (!cpath.exists()) {
			if (!cpath.mkdirs()) {				
				return false;
			}
		}

		try {
			File buildDir = new File(buildPath);
			for (File file : buildDir.listFiles()) {
				file.delete();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return true;
	}
}
