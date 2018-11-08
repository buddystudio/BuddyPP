/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.base;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JDialog;
import javax.swing.JFileChooser;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import util.debug.BDCompiler;
import util.debug.BDTarget;


/**
 *
 * @author Boniz
 */
public class Base 
{

    static private boolean commandLine;
    static File buildFolder;
    static private File examplesFolder;
    static private File librariesFolder;
    static private File toolsFolder;

    static HashSet<File> libraries;
    static public HashMap<String, BDTarget> targetsTable;
    static public HashMap<String, File> importToLibraryTable;
    static public String REVISION="1040";

    //private static final Logger logger = LogManager.getLogger(Base.class);
    
    public Base(String[] args) {
        // platform.init(this);

        // Get paths for the libraries and examples in the Processing folder
        // String workingDirectory = System.getProperty("user.dir");
        examplesFolder = getContentFile("examples");
        librariesFolder = getContentFile("libraries");
        toolsFolder = getContentFile("tools");

        // Get the sketchbook path, and make sure it's set properly
        String sketchbookPath = Preferences.get("sketchbook.path");

        // If a value is at least set, first check to see if the folder exists.
        // If it doesn't, warn the user that the sketchbook folder is being reset.
        if (sketchbookPath != null) {
            File skechbookFolder = new File(sketchbookPath);

            if (!skechbookFolder.exists()) {
                Base.showWarning("Sketchbook folder disappeared",
                        "The sketchbook folder no longer exists.\n"
                        + "Arduino will switch to the default sketchbook\n"
                        + "location, and create a new sketchbook folder if\n"
                        + "necessary. Arduino will then stop talking about\n"
                        + "himself in the third person.",
                        null);
                sketchbookPath = null;
            }
        }

        // If no path is set, get the default sketchbook folder for this platform
        if (sketchbookPath == null) {
            File defaultFolder = getDefaultSketchbookFolder();

            Preferences.set("sketchbook.path", defaultFolder.getAbsolutePath());
            if (!defaultFolder.exists()) {
                defaultFolder.mkdirs();
            }
        }

        targetsTable = new HashMap<String, BDTarget>();
        loadHardware(getHardwareFolder());
        loadHardware(getSketchbookHardwareFolder());

        // Check if there were previously opened sketches to be restored
        // boolean opened = restoreSketches();
        // Check if any files were passed in on the command line
        for (int i = 0; args != null && i < args.length; i++) {
            // String path = args[i];

            // Fix a problem with systems that use a non-ASCII languages. Paths are
            // being passed in with 8.3 syntax, which makes the sketch loader code
            // unhappy, since the sketch folder naming doesn't match up correctly.
            // http://dev.processing.org/bugs/show_bug.cgi?id=1089
            if (isWindows()) 
            {
                try 
                {
                    File file = new File(args[i]);

                    file.getCanonicalPath();
                } 
                catch (IOException e) 
                {
                	//logger.error(this.getClass().toString(),e);                   
                }
            }
            // if (handleOpen(path) != null) {
            // opened = true;
            // }
        }

        //添加函数�?
        libraries = new HashSet<File>();
        
        importToLibraryTable=new HashMap<String, File>();
        
        try 
        {
            File sketchbookLibraries = getSketchbookLibrariesFolder();
            
            addLibraries(librariesFolder);
            addLibraries(sketchbookLibraries);
            
        } catch (IOException ex) 
        {
        	//logger.error(this.getClass().toString(),ex);         	
        }
    }

    /**
     * 获取程序根路径下，某文件夹的文件对象�?
     *
     * @param name文件夹名�?
     */
    static public File getContentFile(String name) {
        String path = System.getProperty("user.dir");
        File working = new File(path);

        return new File(working, name);
    }

    static public File getHardwareFolder() {
        return getContentFile("hardware");
    }

    static public String getHardwarePath() {
        return getHardwareFolder().getAbsolutePath();
    }

    static public String getAvrBasePath() {
        String path = getHardwarePath() + File.separator + "tools"
                + File.separator + "avr" + File.separator + "bin"
                + File.separator;

        return path;
    }

    static public String getExamplesPath() {
        return examplesFolder.getAbsolutePath();
    }

    static public String getLibrariesPath() {
        return librariesFolder.getAbsolutePath();
    }

    static public File getToolsFolder() {
        return toolsFolder;
    }

    static public String getToolsPath() {
        return toolsFolder.getAbsolutePath();
    }

    static public boolean isWindows() {
        // return PApplet.platform == PConstants.WINDOWS;
        return System.getProperty("os.name").indexOf("Windows") != -1;
    }

    static public boolean isLinux() {
        // return PApplet.platform == PConstants.LINUX;
        return System.getProperty("os.name").indexOf("Linux") != -1;
    }

    static public boolean isMacOS() {
        // return PApplet.platform == PConstants.MACOSX;
        return System.getProperty("os.name").indexOf("Mac") != -1;
    }

    static public InputStream getLibStream(String filename) throws IOException {
        //return new FileInputStream(new File(getContentFile("lib"), filename));
    	return new FileInputStream(new File(getContentFile("profile"), filename));
    }

    protected void loadHardware(File folder) {
        if (!folder.isDirectory()) {
            return;
        }

        String list[] = folder.list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                // skip .DS_Store files, .svn folders, etc
                if (name.charAt(0) == '.') {
                    return false;
                }
                if (name.equals("CVS")) {
                    return false;
                }
                return (new File(dir, name).isDirectory());
            }
        });

        // if a bad folder or something like that, this might come back null
        if (list == null) {
            return;
        }

        // alphabetize list, since it's not always alpha order
        // replaced hella slow bubble sort with this feller for 0093
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);

        for (String target : list) {
            File subfolder = new File(folder, target);

            targetsTable.put(target, new BDTarget(target, subfolder));
        }
    }

    static public String getSketchbookLibrariesPath() {
        return getSketchbookLibrariesFolder().getAbsolutePath();
    }

    static public File getSketchbookHardwareFolder() {
        return new File(getSketchbookFolder(), "hardware");
    }

    protected File getDefaultSketchbookFolder() {

        File sketchbookFolder = promptSketchbookLocation();

        // create the folder if it doesn't exist already
        boolean result = true;

        if (!sketchbookFolder.exists()) {
            result = sketchbookFolder.mkdirs();
        }

        if (!result) {
            showError("You forgot your sketchbook",
                    "Arduino cannot run because it could not\n"
                    + "create a folder to store your sketchbook.",
                    null);
        }

        return sketchbookFolder;
    }

    static public File getSketchbookFolder() {
        return new File(Preferences.get("sketchbook.path"));
    }

    static public File getSketchbookLibrariesFolder() {
        File libdir = new File(getSketchbookFolder(), "libraries");

        if (!libdir.exists()) 
        {
            try 
            {
                libdir.mkdirs();
                
                File readme = new File(libdir, "readme.txt");
                FileWriter freadme = new FileWriter(readme);

                freadme.write(
                        "For information on installing libraries, see: "
                        + "http://arduino.cc/en/Guide/Libraries\n");
                
                freadme.close();
            } 
            catch (Exception e) 
            {
            	//logger.error("",e);
            }
        }
        
        return libdir;
    }

    /**
     * Show an error message that's actually fatal to the program. This is an
     * error that can't be recovered. Use showWarning() for errors that allow P5
     * to continue running.
     */
    static public void showError(String title, String message, Throwable e) 
    {
        if (title == null) 
        {
            title = "Error";
        }

        if (commandLine) 
        {
            System.err.println(title + ": " + message);
        } 
        else 
        {
        	// JOptionPane.showMessageDialog(new Frame(), message, title,JOptionPane.ERROR_MESSAGE);
        }
        
        if (e != null) 
        {
            e.printStackTrace();
        }
        
        System.exit(1);
    }

    /**
     * Non-fatal error message with optional stack trace side dish.
     */
    static public void showWarning(String title, String message, Exception e) 
    {
        if (title == null) 
        {
            title = "Warning";
        }

        if (commandLine) 
        {
            System.out.println(title + ": " + message);
        } 
        else 
        {
        	// JOptionPane.showMessageDialog(new Frame(), message, title,JOptionPane.WARNING_MESSAGE);
        }
        if (e != null) 
        {
           //logger.error("",e);
        }
    }

    static protected boolean isCommandLine() 
    {
        return commandLine;
    }

    static public File getSettingsFolder() 
    {
        File settingsFolder = null;

        String preferencesPath = Preferences.get("settings.path");

        if (preferencesPath != null) 
        {
            settingsFolder = new File(preferencesPath);

        } 
        else 
        {
            try 
            {
                File home = new File(System.getProperty("user.home"));

                settingsFolder = new File(home, ".arduino");
            } 
            catch (Exception e) 
            {
            	//logger.error("Problem getting data folder",
                        //"Error getting the Arduino data folder.".toString(),e);                
            }
        }

        // create the folder if it doesn't exist already
        if (!settingsFolder.exists()) 
        {
            if (!settingsFolder.mkdirs()) 
            {                
                /*logger.info("Settings issues",
                        "Arduino cannot run because it could not\n"
                        + "create a folder to store your settings.");*/
                
            }
        }
        return settingsFolder;
    }

    /**
     * Convenience method to get a File object for the specified filename inside
     * the settings folder. For now, only used by Preferences to get the
     * preferences.txt file.
     *
     * @param filename A file inside the settings folder.
     * @return filename wrapped as a File object inside the settings folder
     */
    static public File getSettingsFile(String filename) {
        return new File(getSettingsFolder(), filename);
    }

    static public BDTarget getTarget() {
        return Base.targetsTable.get(Preferences.get("target"));
    }

    static protected File promptSketchbookLocation() {
        File folder = null;

        folder = new File(System.getProperty("user.home"), "sketchbook");
        if (!folder.exists()) {
            folder.mkdirs();
            return folder;
        }
        /*
         String prompt = "Select (or create new) folder for sketches...";

         folder = Base.selectFolder(prompt, null, null);
         if (folder == null) {
         System.exit(0);
         }
         */
        return folder;
    }

    static public File selectFolder(String prompt, File folder, Frame frame) {
        if (Base.isMacOS()) {
            if (frame == null) {
                frame = new Frame();
            } // .pack();
            FileDialog fd = new FileDialog(frame, prompt, FileDialog.LOAD);

            if (folder != null) {
                fd.setDirectory(folder.getParent());
                // fd.setFile(folder.getName());
            }
            System.setProperty("apple.awt.fileDialogForDirectories", "true");
            fd.setVisible(true);
            System.setProperty("apple.awt.fileDialogForDirectories", "false");
            if (fd.getFile() == null) {
                return null;
            }
            return new File(fd.getDirectory(), fd.getFile());

        } else {
            JFileChooser fc = new JFileChooser();

            fc.setDialogTitle(prompt);
            if (folder != null) {
                fc.setSelectedFile(folder);
            }
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int returned = fc.showOpenDialog(new JDialog());

            if (returned == JFileChooser.APPROVE_OPTION) {
                return fc.getSelectedFile();
            }
        }
        return null;
    }
    
    static public Map<String, Map<String, String>>getMapFile(){
    	BDTarget target = getTarget();

        if (target == null) {
            return null;
        }
        return target.getBoards();
    	
    }

    static public Map<String, String> getBoardPreferences() {
        /*
    	BDTarget target = getTarget();

        if (target == null) {
            return new LinkedHashMap<String, String>();
        }
        Map<String, Map<String, String>> mapfile = target.getBoards();
		*/
    	Map<String, Map<String, String>> mapfile=getMapFile();
        if (mapfile == null) {
            return new LinkedHashMap<String, String>();
        }
        Map<String, String> map = (Map<String, String>) mapfile.get(
                Preferences.get("board"));

        if (map == null) {
            return new LinkedHashMap<String, String>();
        }
        return map;
    }

    static public String getPlatformName() {
        String platformName = ".windows";
        if (isLinux()) {
            platformName = ".linux";
        } else {
            platformName = ".macosx";
        }
        return platformName;
    }

    static public File createTempFolder(String name) {
        try {
            File folder = File.createTempFile(name, null);
            //String tempPath = ignored.getParent();
            //return new File(tempPath);
            folder.delete();
            folder.mkdirs();
            return folder;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected boolean addLibraries(File folder) throws IOException {
        if (!folder.isDirectory()) {
            return false;
        }

        String list[] = folder.list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                // skip .DS_Store files, .svn folders, etc
                if (name.charAt(0) == '.') {
                    return false;
                }
                if (name.equals("CVS")) {
                    return false;
                }
                return (new File(dir, name).isDirectory());
            }
        });
        // if a bad folder or something like that, this might come back null
        if (list == null) {
            return false;
        }

        // alphabetize list, since it's not always alpha order
        // replaced hella slow bubble sort with this feller for 0093
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);

        boolean ifound = false;

        for (String potentialName : list) {
            File subfolder = new File(folder, potentialName);
//      File libraryFolder = new File(subfolder, "library");
//      File libraryJar = new File(libraryFolder, potentialName + ".jar");
//      // If a .jar file of the same prefix as the folder exists
//      // inside the 'library' subfolder of the sketch
//      if (libraryJar.exists()) {
            String sanityCheck = sanitizeName(potentialName);
            if (!sanityCheck.equals(potentialName)) {
                String mess = String.format(
                        "The library \"{0}\" cannot be used.\n"
                        + "Library names must contain only basic letters and numbers.\n"
                        + "(ASCII only and no spaces, and it cannot start with a number)",
                        potentialName
                );

                continue;
            }

//        // get the path for all .jar files in this code folder
//        String libraryClassPath =
//          Compiler.contentsToClassPath(libraryFolder);
//        // grab all jars and classes from this folder,
//        // and append them to the library classpath
//        librariesClassPath +=
//          File.pathSeparatorChar + libraryClassPath;
//        // need to associate each import with a library folder
//        String packages[] =
//          Compiler.packageListFromClassPath(libraryClassPath);
            libraries.add(subfolder);
            try {
                String packages[]
                        = BDCompiler.headerListFromIncludePath(subfolder.getAbsolutePath());
                for (String pkg : packages) {
                    importToLibraryTable.put(pkg, subfolder);
                }
            } catch (Exception e) {
                showWarning("Error", String.format("Unable to list header files in {0}", subfolder), e);
            }
            ifound = true;

        }
        return ifound;
    }

    static public Set<File> getLibraries() {
        return libraries;
    }

    static public String sanitizeName(String origName) {
        char c[] = origName.toCharArray();
        StringBuffer buffer = new StringBuffer();

        // can't lead with a digit, so start with an underscore
        if ((c[0] >= '0') && (c[0] <= '9')) {
            buffer.append('_');
        }
        for (int i = 0; i < c.length; i++) {
            if (((c[i] >= '0') && (c[i] <= '9'))
                    || ((c[i] >= 'a') && (c[i] <= 'z'))
                    || ((c[i] >= 'A') && (c[i] <= 'Z'))) {
                buffer.append(c[i]);

            } else {
                buffer.append('_');
            }
        }
    // let's not be ridiculous about the length of filenames.
        // in fact, Mac OS 9 can handle 255 chars, though it can't really
        // deal with filenames longer than 31 chars in the Finder.
        // but limiting to that for sketches would mean setting the
        // upper-bound on the character limit here to 25 characters
        // (to handle the base name + ".class")
        if (buffer.length() > 63) {
            buffer.setLength(63);
        }
        return buffer.toString();
    }
}
