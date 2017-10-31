package com.manji.adds.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FolderUtils {

//	本地
//	private static String piclocation = "F:/pic";
//	private static String piclocation2 = "F:\\pic";
//  測試
	private static String piclocation = "D:/pic";
	private static String piclocation2 = "D:\\pic";
	
	
	
	public static void main(String args[]) {
		// searchFiles("123");

	}

	public static void makeFolder(String con_no) {

		String path = piclocation + "/" + con_no;
		File file = new File(path);
		if (!file.exists() && !file.isDirectory()) {
			System.out.println("//路径：" + path + " 不存在");
			new File(path + "/contract").mkdirs();
			new File(path + "/pic").mkdirs();
		} else {
			System.out.println("//路径：" + path + " 已存在");
		}

	}

	public static boolean makeFile(String con_no, String type, String fileName) {
		String path = piclocation + "/" + con_no + "/" + type + "/" + fileName;
		File file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
				System.out.println("文件创建成功");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("文件创建失败");
				return false;
			}
		} else {
			System.out.println("文件已存在");
		}
		return false;
	}

	public static Map<String, String> searchFiles2() {

		Map<String, String> map = new HashMap<String, String>();

		String path = piclocation2;
		File file = new File(path);
		File[] tempList = file.listFiles();
		System.out.println("该目录下对象个数：" + tempList.length);
		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile()) {
				System.out.println("文     件：" + tempList[i]);
			}
			if (tempList[i].isDirectory()) {
				System.out.println("文件夹：" + tempList[i]);
			}

		}
		return map;
	}

	public static ArrayList<String> searchFiles(String con_no) {

		ArrayList<String> list = new ArrayList<String>();

		String path = piclocation + "/" + con_no + "/pic";

		File file = new File(path);
		File[] tempList = file.listFiles();

		if (null == tempList) {
			return list;
		}

		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile()) {
				System.out.println(tempList[i]);
				list.add(tempList[i].toString());
				// System.out.println(list.get(i));
			}
		}

		return list;
	}

}
