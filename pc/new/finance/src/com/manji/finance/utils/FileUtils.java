package com.manji.finance.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class FileUtils {

	/**
	 * 解压xx.gz文件
	 * @param filePath    初始文件位置
	 * @param unfoldPath  解压文件位置
	 * @return
	 */
	public static String unfold(String filePath,String unfoldPath) {
		
		String ouputfile="";
		
		try {
		// 建立gzip压缩文件输入流
		FileInputStream fin = new FileInputStream(filePath);
		// 建立gzip解压工作流
		GZIPInputStream gzin = new GZIPInputStream(fin);
		// 建立解压文件输出流
		ouputfile = unfoldPath;
		
		FileOutputStream fout;
		
		fout = new FileOutputStream(ouputfile);
		

		int num;
		byte[] buf = new byte[1024];

		while ((num = gzin.read(buf, 0, buf.length)) != -1) {
			fout.write(buf, 0, num);
		}

		
		gzin.close();
		fout.close();
		fin.close();

		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return ouputfile;
	}

	
	
	public static List<JSONObject> parseFile(String filePath){
		
		List<JSONObject> list =new ArrayList<JSONObject>();
		
		try {
            String encoding="utf-8";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null &&!"".equals(lineTxt)){
                    
                	list.add(JSONObject.fromObject(lineTxt));
                	
                }
                read.close();
    }else{
        System.out.println("找不到指定的文件");
    }
    } catch (Exception e) {
        System.out.println("读取文件内容出错");
        e.printStackTrace();
    }
		
		
		
		return list;
	}
	

	public static void main(String[] args) {

/*		String pa = "D:/circlemes/download/2017041712.gz";
		String path = unfold(pa);

		System.out.println(path);*/
		
		
		
		/*String pa ="D:/circlemes/unfold/2017041712";
		List<JSONObject> list =parseFile(pa);
		
		System.out.println(JSONArray.fromObject(list).toString());*/
	}

}
