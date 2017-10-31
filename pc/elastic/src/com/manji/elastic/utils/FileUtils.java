package com.manji.elastic.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class FileUtils {

	public static String readFileContent(String path) {

		String res ="";
		
		String paathUrl = "D:/15.txt";

		File f = new File(paathUrl);

		StringBuilder result = new StringBuilder();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "GBK"));
			String s = null;
			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				result.append(System.lineSeparator() + s);
			}
			br.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 构造一个BufferedReader类来读取文件
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			try {
				
				res =new String(result.toString().getBytes("utf-8"));
				System.out.println(res);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return res;

	}

	public static void main(String[] args) {
		readFileContent("");
	}

}
