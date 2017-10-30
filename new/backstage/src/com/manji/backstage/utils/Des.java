package com.manji.backstage.utils;

import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.Cipher;

/**
 * Des加密介绍 Des是一种对称加密算法，所谓对称加密算法即：加密和解密使用相同密钥的算法。Des加密算法出自IBM的研究，
 * 后来被美国政府正式采用，之后开始广泛流传，但是近些年使用越来越少，因为Des使用56位密钥，以现代计算能力，
 * 24小时内即可被破解。虽然如此，在某些简单应用中，我们还是可以使用Des加密算法，本文简单讲解Des的JAVA实现 。
 * 注意：Des加密和解密过程中，密钥长度都必须是8的倍数
 */
public class Des {
	  private static String strDefaultKey = "abcDEF123";  
	    private Cipher encryptCipher = null;  
	    private Cipher decryptCipher = null;  
	  
	  
	    /** 
	     * 默认构造方法，使用默认密钥 
	     * @throws Exception 
	     */  
	    public Des() throws Exception {  
	        this(strDefaultKey);  
	    }  
	  
	  
	    /** 
	     * 指定密钥构造方法 
	     * @param strKey 指定的密钥 
	     * @throws Exception 
	     */  
	    public Des(String strKey) throws Exception {  
	        Security.addProvider(new com.sun.crypto.provider.SunJCE());  
	        Key key = getKey(strKey.getBytes());  
	        encryptCipher = Cipher.getInstance("DES");  
	        encryptCipher.init(Cipher.ENCRYPT_MODE, key);  
	        decryptCipher = Cipher.getInstance("DES");  
	        decryptCipher.init(Cipher.DECRYPT_MODE, key);  
	    }  
	  
	  
	    /** 
	     * 加密字符串 
	     * @param strIn 需加密的字符串 
	     * @return 加密后的字符串 
	     * @throws Exception 
	     */  
	    public String encrypt(String strIn) throws Exception {  
	        return byteArr2HexStr(encrypt(strIn.getBytes()));  
	    }  
	      
	      
	    /** 
	     * 加密字节数组 
	     * @param arrB 需加密的字节数组 
	     * @return 加密后的字节数组 
	     * @throws Exception 
	     */  
	    public byte[] encrypt(byte[] arrB) throws Exception {  
	        return encryptCipher.doFinal(arrB);  
	    }  
	  
	      
	      
	    /** 
	     * 解密字符串 
	     * @param strIn 需解密的字符串 
	     * @return 解密后的字符串 
	     * @throws Exception 
	     */  
	    public String decrypt(String strIn) throws Exception {  
	        return new String(decrypt(hexStr2ByteArr(strIn)));  
	    }  
	      
	      
	    /** 
	     * 解密字节数组 
	     * @param arrB 需解密的字节数组 
	     * @return 解密后的字节数组 
	     * @throws Exception 
	     */  
	    public byte[] decrypt(byte[] arrB) throws Exception {  
	        return decryptCipher.doFinal(arrB);  
	    }  
	  
	  
	      
	    /** 
	     * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 
	     * 不足8位时后面补0，超出8位只取前8位 
	     * @param arrBTmp 构成该字符串的字节数组 
	     * @return 生成的密钥 
	     * @throws java.lang.Exception 
	     */  
	    private Key getKey(byte[] arrBTmp) throws Exception {  
	        byte[] arrB = new byte[8];  
	        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {  
	            arrB[i] = arrBTmp[i];  
	        }  
	        Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");  
	        return key;  
	    }  
	  
	      
	      
	    /** 
	     * 将byte数组转换为表示16进制值的字符串， 
	     * 如：byte[]{8,18}转换为：0813， 
	     * 和public static byte[] hexStr2ByteArr(String strIn) 
	     * 互为可逆的转换过程 
	     * @param arrB 需要转换的byte数组 
	     * @return 转换后的字符串 
	     * @throws Exception 本方法不处理任何异常，所有异常全部抛出 
	     */  
	    public static String byteArr2HexStr(byte[] arrB) throws Exception {  
	        int iLen = arrB.length;  
	        StringBuffer sb = new StringBuffer(iLen * 2);  
	        for (int i = 0; i < iLen; i++) {  
	            int intTmp = arrB[i];  
	            while (intTmp < 0) {  
	                intTmp = intTmp + 256;  
	            }  
	            if (intTmp < 16) {  
	                sb.append("0");  
	            }  
	            sb.append(Integer.toString(intTmp, 16));  
	        }  
	        return sb.toString();  
	    }  
	  
	      
	  
	    /** 
	     * 将表示16进制值的字符串转换为byte数组， 
	     * 和public static String byteArr2HexStr(byte[] arrB) 
	     * 互为可逆的转换过程 
	     * @param strIn 需要转换的字符串 
	     * @return 转换后的byte数组 
	     * @throws Exception 本方法不处理任何异常，所有异常全部抛出 
	     */  
	    public static byte[] hexStr2ByteArr(String strIn) throws Exception {  
	        byte[] arrB = strIn.getBytes();  
	        int iLen = arrB.length;  
	        byte[] arrOut = new byte[iLen / 2];  
	        for (int i = 0; i < iLen; i = i + 2) {  
	            String strTmp = new String(arrB, i, 2);  
	            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);  
	        }  
	        return arrOut;  
	    }  
	
	
}
