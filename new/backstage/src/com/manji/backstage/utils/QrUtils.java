package com.manji.backstage.utils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Hashtable;

import org.apache.commons.codec.binary.Base64;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.QRCode;
  
public class QrUtils {

	
	
	public static void main(String[] args){
		String text = "http://www.baidu.com";
        int width = 300;   
        int height = 300;   
        String format = "jpg";
        //设置默认编码格式和容错率
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		//设置二维码图片的存放目录、文件名以及图片格式
		String filePath = "D:\\";
		String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()) + ".jpg";
		Path path = FileSystems.getDefault().getPath(filePath, fileName);
		//开始生成二维码图片
		BitMatrix matrix;
		try {
			matrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
			MatrixToImageWriter.writeToPath(matrix, format, path);
	        System.out.println("path=" + path.toString());
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
