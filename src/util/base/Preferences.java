package util.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public class Preferences 
{
	static final String PREFS_FILE = "preferences.txt";
	static Hashtable<String, String> defaults;
	static Hashtable<String, String> table = new Hashtable<String, String>();
	static File preferencesFile;

	//private static final Logger logger = LogManager.getLogger(Preferences.class);

	static public String[] loadStrings(InputStream input) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));

			String lines[] = new String[100];
			int lineCount = 0;
			String line = null;

			while ((line = reader.readLine()) != null) {
				if (lineCount == lines.length) {
					String temp[] = new String[lineCount << 1];

					System.arraycopy(lines, 0, temp, 0, lineCount);

					lines = temp;
				}

				lines[lineCount++] = line;
			}
			reader.close();

			if (lineCount == lines.length) {
				return lines;
			}

			// resize array to appropriate amount for these lines
			String output[] = new String[lineCount];

			System.arraycopy(lines, 0, output, 0, lineCount);
			return output;

		} 
		catch (IOException e) 
		{
			//logger.error("", e);
			// e.printStackTrace();
			// throw new RuntimeException("Error inside loadStrings()");
		}

		return null;
	}

	static protected void load(InputStream input) throws IOException {
		load(input, table);
	}

	static public void load(InputStream input, Map<String, String> table) throws IOException {
		String[] lines = loadStrings(input); // Reads as UTF-8

		for (String line : lines) {
			if ((line.length() == 0) || (line.charAt(0) == '#')) {
				continue;
			}

			// this won't properly handle = signs being in the text
			int equals = line.indexOf('=');

			if (equals != -1) {
				String key = line.substring(0, equals).trim();
				String value = line.substring(equals + 1).trim();

				table.put(key, value);
			}
		}
	}

	static public String get(String attribute/* , String defaultValue */) {
		return (String) table.get(attribute);
	}

	static public void save() {

		if (preferencesFile == null) {
			return;
		}

		// Fix for 0163 to properly use Unicode when writing preferences.txt
		PrintWriter writer;
		try {
			writer = new PrintWriter(preferencesFile);

			String[] keys = (String[]) table.keySet().toArray(new String[0]);

			Arrays.sort(keys);
			for (String key : keys) {
				writer.println(key + "=" + ((String) table.get(key)));
			}

			writer.flush();
			writer.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}

	static public void set(String attribute, String value) {
		table.put(attribute, value);
	}

	@SuppressWarnings("unchecked")
	public static void init(String commandLinePrefs) {

		// start by loading the defaults, in case something
		// important was deleted from the user prefs
		try {
			load(Base.getLibStream("preferences.txt"));
		} catch (Exception e) {
			Base.showError(null, "Could not read default settings.\n" + "You'll need to reinstall Arduino.", e);
		}

		// check for platform-specific properties in the defaults
		// String platformExt = "." + "windows";
		String platformExt = Base.getPlatformName();
		int platformExtLength = platformExt.length();
		Enumeration<String> e = table.keys();

		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();

			if (key.endsWith(platformExt)) {
				// this is a key specific to a particular platform
				String actualKey = key.substring(0, key.length() - platformExtLength);
				String value = get(key);

				table.put(actualKey, value);
			}
		}

		// clone the hash table
		defaults = (Hashtable<String, String>) table.clone();

		// Load a prefs file if specified on the command line
		if (commandLinePrefs != null) {
			try {
				load(new FileInputStream(commandLinePrefs));

			} catch (Exception poe) {
				Base.showError("Error", String.format("Could not read preferences from {0}", commandLinePrefs), poe);
			}
		} else if (!Base.isCommandLine()) {
			// next load user preferences file
			preferencesFile = Base.getSettingsFile(PREFS_FILE);
			if (!preferencesFile.exists()) {
				// create a new preferences file if none exists
				// saves the defaults out to the file
				try {
					save();
				} catch (Exception ex) {
					Base.showError("Error writing preferences",
							String.format("Error reading the preferences file. " + "Please delete (or move)\n"
									+ "{0} and restart Arduino.", preferencesFile.getAbsolutePath()),
							ex);
				}

			} else {
				// load the previous preferences file

				try {
					load(new FileInputStream(preferencesFile));

				} catch (Exception ex) {
					Base.showError("Error reading preferences",
							String.format("Error reading the preferences file. " + "Please delete (or move)\n"
									+ "{0} and restart Arduino.", preferencesFile.getAbsolutePath()),
							ex);
				}
			}
		}
	}

	static public boolean getBoolean(String attribute) {
		String value = get(attribute); // , null);

		return (new Boolean(value)).booleanValue();
	}

	static public int getInteger(String attribute) {
		return Integer.parseInt(get(attribute));
	}
}
