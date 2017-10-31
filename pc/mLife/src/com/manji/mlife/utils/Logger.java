package com.manji.mlife.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Logger {

    private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
	
	public void aa(){
		 TimerTask task = new TimerTask() {
			@Override
			public void run() {
		        System.out.println("已启动");  

		        SimpleDateFormat simpleDateFormat=null;  
		        SimpleDateFormat simpleDateFormat1=null;  
		        String[] a= new String[10];
		        simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");  
		        simpleDateFormat1=new SimpleDateFormat("yyyy-MM-dd-HH-mm"); 
		        String str = simpleDateFormat1.format(new Date());
		        System.out.println("当前的系统时间为："+str);  
		        a = str.split("-");
		        //判断当前时间 0《x《1
		       if(Integer.parseInt(a[3]) == 0){
		    	  if(Integer.parseInt(a[4]) > 0 && Integer.parseInt(a[4]) < 59){
//		    		   System.out.println("当前时间是:"+a[3]+"时"+a[4]+"分");
				        Date date =new Date();
				        Calendar calendar = Calendar.getInstance();
				        calendar.add(Calendar.DATE, -1); //得到前一天
				        date = calendar.getTime();
//				        System.out.println("当前的系统时间为："+simpleDateFormat.format(date));  
				        
//				        System.out.println(simpleDateFormat.format(date));

				        String dateStr =simpleDateFormat.format(date);
				        System.out.println(dateStr);
				        ZipFile(dateStr);
		    	   }
		      }
				
			}
			
	        };
	        
	        Timer timer =new Timer("test");//定时器
	        timer.schedule(task, 1000, 60*60*1000);
	        
	}
	
	public static void ZipFile(String dateStr){
		String path ="E:/java/logs/";
		File sourceFile =new File(path+"log.log");//目标文件
		File destanceFile =new File(path+dateStr+".log");
		File zipFile =new File(path+dateStr+".zip");
		
		try {
		if(!destanceFile.exists()){//如果文件不存在，就创建文件
			
			destanceFile.createNewFile();
		}
		//复制文件
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(sourceFile), "GBK"));
		String line ="";
		FileWriter fw =new FileWriter(destanceFile, false);
		while ((line = br.readLine()) != null) {//如果文件的下一行有内容
//			line = new String(line.getBytes("ISO-8859-1"),"UTF-8");
//			System.out.println("文件内容: " + line);
			fw.write(line+"\r\n");//写入文件并且换行
			fw.flush();//保证所有的内容全部复制了
		}
		br.close();
		//压缩文件
		InputStream input = new FileInputStream(destanceFile);
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
        zipOut.putNextEntry(new ZipEntry(destanceFile.getName()));
        int temp = 0;
        while((temp = input.read()) != -1){
            zipOut.write(temp);//压缩文件
        }
        input.close();
        zipOut.close();
        
        System.out.println("操作完成！");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}
	
	public static void main(String args[]){
		System.out.println("0");
		Logger t =new Logger();
		t.aa();
//		this.task.cancle();
	}
	
}
