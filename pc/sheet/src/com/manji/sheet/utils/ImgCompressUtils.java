package com.manji.sheet.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 压缩图片
 * @author Administrator
 *
 */
public class ImgCompressUtils {

	
	private Image img;  
    private int width;  
    private int height;  
    /*@SuppressWarnings("deprecation")  
    public static void main(String[] args) throws Exception {  
        System.out.println("开始：" + new Date().toLocaleString());  
        ImgCompressUtils imgCom = new ImgCompressUtils("C:/Users/Administrator/Desktop/111.jpg");  
        String path = imgCom.resizeFix(2000, 2000);  
        System.out.println("结束：" + new Date().toLocaleString());  
    } */ 
    /** 
     * 构造函数 
     */  
    public ImgCompressUtils(File file) throws IOException {  
        //File file = new File(fileName);// 读入文件  
        img = ImageIO.read(file);      // 构造Image对象  
        width = img.getWidth(null);    // 得到源图宽  
        height = img.getHeight(null);  // 得到源图长  
    }  
    /** 
     * 按照宽度还是高度进行压缩 
     * @param w int 最大宽度 
     * @param h int 最大高度 
     */  
    public String resizeFix(int w, int h) throws IOException {  
    	
    	String path = "";
        if (width / height > w / h) {  
            path = resizeByWidth(w);  
        } else {  
            path = resizeByHeight(h);  
        }  
        return path;
    }  
    /** 
     * 以宽度为基准，等比例放缩图片 
     * @param w int 新宽度 
     */  
    public String resizeByWidth(int w) throws IOException {  
        int h = (int) (height * w / width);  
        return resize(w, h);  
    }  
    /** 
     * 以高度为基准，等比例缩放图片 
     * @param h int 新高度 
     */  
    public String resizeByHeight(int h) throws IOException {  
        int w = (int) (width * h / height);  
        return resize(w, h);  
    }  
    /** 
     * 强制压缩/放大图片到固定的大小 
     * @param w int 新宽度 
     * @param h int 新高度 
     */  
    public String resize(int w, int h) throws IOException {  
    	
        // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢  
        BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB );   
        image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图  
        //String path = "C:/Users/Administrator/Desktop/456.jpg";//测试地址
        String path = "/manji/log/tomcat7/456.jpg";//正式地址
        File destFile = new File(path);  
        FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流  
        // 可以正常实现bmp、png、gif转jpg  
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
        encoder.encode(image); // JPEG编码  
        out.close();  
        
        return path;
    }  
}
