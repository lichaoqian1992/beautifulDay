package com.manji.circlemes.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MD5Utils {

	public static String getMD5(String pw) {
		try {

			// 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			// 输入的字符串转换成字节数组
			byte[] inputByteArray = pw.getBytes();
			// inputByteArray是输入字符串转换得到的字节数组
			messageDigest.update(inputByteArray);
			// 转换并返回结果，也是字节数组，包含16个元素
			byte[] resultByteArray = messageDigest.digest();
			// 字符数组转换成字符串返回
			return byteArrayToHex(resultByteArray);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	public static String byteArrayToHex(byte[] byteArray) {

		// 首先初始化一个字符数组，用来存放每个16进制字符
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		// new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
		char[] resultCharArray = new char[byteArray.length * 2];
		// 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
		int index = 0;
		for (byte b : byteArray) {
			resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
			resultCharArray[index++] = hexDigits[b & 0xf];
		}
		// 字符数组组合成字符串返回
		return new String(resultCharArray);
	}
	
	public static String getMD5String(String a, String b, String c){
		
		String t =a+b+c;
		
		return getMD5(t);
	}
	

	public static void main(String[] args) {
		
//		String str =getMD5("aaaaa");
//		System.out.println(str);
//		String str2 =getMD5String("aa","a","aa");
//		System.out.println(str2);
		
		
		String s =getSign();
		
		System.out.println(s);

	}
	
	public static String getCurrentTime(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");  
		long times =System.currentTimeMillis(); 
		Date date =new Date(times);
		String curTime =sdf.format(date);
		return curTime;
	}
	
	
	public static String getSign(){
		
		String encryKey ="manjiwang";
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		long times =System.currentTimeMillis(); 
		Date date =new Date(times);
		
		String curTime =sdf.format(date);
		
		System.out.println(encryKey+"   "+curTime);
		
		
		
		
		return getMD5(encryKey+curTime);
	}
	
	
}
