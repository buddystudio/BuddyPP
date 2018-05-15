/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 *
 * @author gsh
 */
public class BDParaProc {
	private static final Logger logger = LogManager.getLogger(BDParaProc.class);

	/**
	 * 以行为单位读取文件
	 */
	public static String readPara(String fileName) {
		File file = new File(fileName);

		BufferedReader reader = null;

		String codeTxt = "";

		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));

			String tempString = null;

			int line = 1;

			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				System.out.println("line " + line + ": " + tempString);

				codeTxt += (tempString + "\n");

				if (line == 0) {

				} else if (line == 1) {

				} else if (line == 2) {

				} else if (line == 3) {

				}

				line++;
			}

			reader.close();
		} catch (IOException e) {
			logger.error("", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException ex) {
					logger.error("", ex);
				}
			}
		}

		return codeTxt;
	}
}
