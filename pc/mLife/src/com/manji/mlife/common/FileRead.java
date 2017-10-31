package com.manji.mlife.common;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class FileRead {
	private static final Logger logger =Logger.getLogger(FileRead.class);
	
	/**
	 * 以行为单位读取文件为字符串
	 * @param path
	 * @param fileName
	 * @return
	 */
	public static String getFileContent(String path, String fileName){
		if (StringUtil.isNullOrEmpty(path) || StringUtil.isNullOrEmpty(fileName)){
			logger.warn("Parameter path or fileName is null or empty string.");
			return null;
		}
		
		// File file = new File(filePath);
		String filePath = path.replace('\\', '/')+"WEB-INF/html/"+fileName+".html";
		
		StringBuilder result = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"UTF-8"));

			String strLine ="";
            // 使用readLine方法，一次读一行
            while((strLine = reader.readLine())!=null){ 
                result.append(System.lineSeparator() + strLine);
            }
            reader.close();  
		} catch (IOException e1) {
			logger.error(e1.getMessage(), e1);
		}  catch (Exception e2) {
			logger.error(e2.getMessage(), e2);
		} finally {
			if (reader != null){
				try {
					reader.close();
				} catch (IOException e3) {
					logger.error(e3.getMessage(), e3);
				}
			}
		}
		
		return result.toString();
	}
}
