package util.debug;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import util.base.Preferences;


public class BDTarget {
    private String name;
    private File folder;
    private Map<String, Map<String, String>> boards;
    private Map<String, Map<String, String>> programmers;
	  
    public BDTarget(String name, File folder) {
        this.name = name;
        this.folder = folder;
        this.boards = new LinkedHashMap<String, Map<String, String>>();
        this.programmers = new LinkedHashMap<String, Map<String, String>>();
	    
        File boardsFile = new File(folder, "boards.txt");

        try {
            if (boardsFile.exists()) {
                Map<String, String> boardPreferences = new LinkedHashMap<String, String>();

                Preferences.load(new FileInputStream(boardsFile),
                        boardPreferences);
                for (Object k : boardPreferences.keySet()) {
                    String key = (String) k;
                    String board = key.substring(0, key.indexOf('.'));

                    if (!boards.containsKey(board)) { 
                        boards.put(board, new HashMap<String, String>());
                    }
                    ((Map<String, String>) boards.get(board)).put(
                            key.substring(key.indexOf('.') + 1),
                            boardPreferences.get(key));
                }
            }
        } catch (Exception e) {
            System.err.println(
                    "Error loading boards from " + boardsFile + ": " + e);
        }

        File programmersFile = new File(folder, "programmers.txt");

        try {
            if (programmersFile.exists()) {
                Map<String, String> programmerPreferences = new LinkedHashMap<String, String>();

                Preferences.load(new FileInputStream(programmersFile),
                        programmerPreferences);
                for (Object k : programmerPreferences.keySet()) {
                    String key = (String) k;
                    String programmer = key.substring(0, key.indexOf('.'));

                    if (!programmers.containsKey(programmer)) {
                        programmers.put(programmer, new HashMap<String,String>());
                    }
                    ((Map<String, String>) programmers.get(programmer)).put(
                            key.substring(key.indexOf('.') + 1),
                            programmerPreferences.get(key));
                }
            }
        } catch (Exception e) {
            System.err.println(
                    "Error loading programmers from " + programmersFile + ": "
                    + e);
        }    
    }
	  
    public String getName() {
        return name;
    }

    public File getFolder() {
        return folder;
    }

    public Map<String, Map<String, String>> getBoards() {
        return boards;
    }

    public Map<String, Map<String, String>> getProgrammers() {
        return programmers;
    }
}
