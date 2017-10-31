package com.manji.backstage.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.manji.backstage.service.SqlService;
import com.manji.backstage.service.impl.SqlServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * 导出excel工具类
 * @author Administrator
 *
 */
public class ExcelUtils {

	/**引入相关的dao*/
	SqlService sqlService=new SqlServiceImpl();

	/**
	 * 导出sql查询内容
	 * @return
	 */
	public static String refoundDetailExcel(String sql) throws SQLException {

        //第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        //第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("sql查询数据");
        //第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        //1.设置第一行的样式
        HSSFCellStyle style1 = wb.createCellStyle();
        HSSFFont font1 = wb.createFont();
        font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  // 字体加粗
        font1.setFontHeightInPoints((short)18);   //--->设置字体大小
        style1.setFont(font1);
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        style1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直局中
        //设置第二行样式
        HSSFCellStyle style2 = wb.createCellStyle();
        HSSFFont font2 = wb.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  // 字体加粗
        font2.setFontHeightInPoints((short)10);   //--->设置字体大小
        style2.setFont(font2);
        style2.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 创建一个右对齐格式
        //设置第三行样式
        HSSFCellStyle style = wb.createCellStyle();
        HSSFFont font3 = wb.createFont();
        font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  // 字体加粗
        font3.setFontHeightInPoints((short)10);   //--->设置字体大小
        style.setFont(font3);
        // 第四步，创建单元格，
        HSSFRow row = sheet.createRow((int) 2);


     //查询列
     ResultSet rs=SQLUtils.executeQuery(sql);
     ResultSetMetaData resultsetmetadata = rs.getMetaData();
     String columnName="";
     List<String> columnnameList=new ArrayList<String>();
        //循环创建excl列
     for(int i =0;i<resultsetmetadata.getColumnCount();i++) {
            columnName = resultsetmetadata.getColumnName(i + 1);
            columnnameList.add(columnName);
             HSSFCell cell = row.createCell((short) i);
             cell.setCellValue(columnName);
             cell.setCellStyle(style);

             sheet.setColumnWidth(i,20 * 256);

    }
        //循环查询数据，写入数据
        int k=0;
        while (rs.next())
        {
            row = sheet.createRow(k+3);
            for (int j=0;j<columnnameList.size();j++){
                row.createCell((short) j).setCellValue(String.valueOf(rs.getString(columnnameList.get(j))));
            }
            k++;
        }

        
        HSSFRow row1 = sheet.createRow(0);
        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell1 = row1.createCell((short) 0);
        cell1.setCellValue("Sql查询结果集");
        cell1.setCellStyle(style1);

         //保存文件
        String path="/manji/uploads/backstage/Sql查询结果集"+new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+".xls";
        ExcelReadUtil.excelWrite(path,wb);
        return path;
	}
	

	

	

	
	
	

}
