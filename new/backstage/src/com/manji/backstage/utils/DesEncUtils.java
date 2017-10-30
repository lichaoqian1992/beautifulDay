package com.manji.backstage.utils;

public class DesEncUtils {
	 /** 
     * 使用默认密钥进行DES加密 
     */  
    public static String encrypt(String plainText) {  
        try {  
            return new Des().encrypt(plainText);  
        } catch (Exception e) {  
            return null;  
        }  
    }  
  
      
    /** 
     * 使用指定密钥进行DES解密 
     */  
    public static String encrypt(String plainText, String key) {  
        try {  
            return new Des(key).encrypt(plainText);  
        } catch (Exception e) {  
            return null;  
        }  
    }  
      
  
    /** 
     * 使用默认密钥进行DES解密 
     */  
    public static String decrypt(String plainText) {  
        try {  
            return new Des().decrypt(plainText);  
        } catch (Exception e) {  
            return null;  
        }  
    }  
  
      
    /** 
     * 使用指定密钥进行DES解密 
     */  
    public static String decrypt(String plainText, String key) {  
        try {  
            return new Des(key).decrypt(plainText);  
        } catch (Exception e) {  
            return null;  
        }  
    }  
    
    
    public static void main(String[] args){
    	String str = "huanghan";  
        String t = "";  
        String pa="TZ4V62";
        System.out.println("加密后：" + (t = DesEncUtils.encrypt(str,pa).toUpperCase()));  
        System.out.println("解密后：" + DesEncUtils.decrypt(str,pa));  
    }
}
