package com.manji.messageserver.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
/**
 * Created by Administrator on 2016/12/17.
 */
public class UnZipUtil {
    private static final int BUFFER = 1024;
    private static FileInputStream inputFile;
    private static FileOutputStream outputFile;
    private static GZIPInputStream gZipInputStream;
    /**
     * 解压文件
     * @param zipPath 压缩文件路径
     * @param unzipPath 解压后的文件路径
     */
    public static void unZipFile(String zipPath , String unzipPath){
        try {
            inputFile = new FileInputStream(zipPath);
            outputFile = new FileOutputStream(unzipPath);
            gZipInputStream = new GZIPInputStream(inputFile);
            int count;
            byte data[] = new byte[BUFFER];
            while ((count = gZipInputStream.read(data, 0, BUFFER)) != -1) {
                outputFile.write(data, 0, count);
            }
            gZipInputStream.close();
            System.out.println("解压完成");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                gZipInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String [] args){
        unZipFile("D:\\b\\2016121513.gz","D:\\b\\2016121513");
    }
}
