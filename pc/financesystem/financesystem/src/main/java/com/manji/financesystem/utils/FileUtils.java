package com.manji.financesystem.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;

/**
 * Created by pudding on 2017-2-5.
 */
public class FileUtils {

    /**
     * Excel写入
     * @param path
     * @param wb
     */
    public static void excelWrite(String path, HSSFWorkbook wb){
        try{
            FileOutputStream fout1 = new FileOutputStream(path);
            wb.write(fout1);
            fout1.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
