package com.manji.finance.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DecriptUtil {

	/**
     * SHA1加密
     *
     * @param decript
     * @return
     */
    public static String SHA1(String decript) {
        try {
            MessageDigest digest = MessageDigest
                    .getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static String SHA256(String decript) {
        try {
            MessageDigest digest = MessageDigest
                    .getInstance("SHA-256");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        String sha1 = SHA256("BUSDAT=Jmx0O3htbCZndDsNCgkgICAgCSZsdDttZXJjaF9kYXRlJmd0OzIwMTcwNjA2Jmx0Oy9tZXJjaF9kYXRlJmd0Ow0KCSAgICAJJmx0O21lcmNoX3Byb2QmZ3Q75LiT6L2mJmx0Oy9tZXJjaF9wcm9kJmd0Ow0KCSAgICAJJmx0O21lcmNoX3NlcmlhbCZndDtvbGQxMjM0NTY3ODkwMTIzNDU2Nzg5Jmx0Oy9tZXJjaF9zZXJpYWwmZ3Q7DQoJICAgICZsdDsveG1sJmd0Ow==&COMMID=001&NTBNBR=N0004949&SIGTIM=201706061501570666&TRSCOD=CMDZ&ACC8bh7D36241E3r");
        System.out.println(sha1);
    }
}
