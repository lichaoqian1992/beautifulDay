package com.manji.sheet.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import com.jfinal.plugin.activerecord.Record;
import com.manji.sheet.model.reqParam.WorkOrderParam;
import com.manji.sheet.service.ManageService;


/**
 * 导出excel工具类
 * @author Administrator
 *
 */
public class ExcelUtils {
	private String url = "/manji/uploads/sheet/";//正式上传地址
    //private String url = "E://";//测试上传地址

	private ManageService manageService = new ManageService();
	/**
	 * 导出第三方信息
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String toExcelList(WorkOrderParam workOrder){
		
	//第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
    //第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("工单列表信息");
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
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("序号");
        cell.setCellStyle(style);
        sheet.setColumnWidth(0,20 * 128);//设置单元格的宽度

        cell = row.createCell((short) 1);
        cell.setCellValue("单号");
        cell.setCellStyle(style);
        sheet.setColumnWidth(1,20 * 512);

        cell = row.createCell((short) 2);
        cell.setCellValue("发起时间");
        cell.setCellStyle(style);
        sheet.setColumnWidth(2,20 * 512);

        cell = row.createCell((short) 3);
        cell.setCellValue("工单类型");
        cell.setCellStyle(style);
        sheet.setColumnWidth(3,20 * 256);

        cell = row.createCell((short) 4);
        cell.setCellValue("发起人");
        cell.setCellStyle(style);
        sheet.setColumnWidth(4,20 * 256);

        cell = row.createCell((short) 5);
        cell.setCellValue("信息来源");
        cell.setCellStyle(style);
        sheet.setColumnWidth(5,20 * 256);

        cell = row.createCell((short) 6);
        cell.setCellValue("处理状态");
        cell.setCellStyle(style);
        sheet.setColumnWidth(6,20 * 256);

        cell = row.createCell((short) 7);
        cell.setCellValue("处理人");
        cell.setCellStyle(style);
        sheet.setColumnWidth(7,20 * 256);

        cell = row.createCell((short) 8);
        cell.setCellValue("执行部门");
        cell.setCellStyle(style);
        sheet.setColumnWidth(8,20 * 256);

        //查询数据，写入数据
        List<Record> list = manageService.toExcel(workOrder);
        if(list!=null){
        	for(int i=0;i<list.size();i++){
        		String status="";
        		switch (list.get(i).get("status").toString()) {
                case "1":
                    status = "待受理";
                    break;
                case "2":
                    status = "不受理";
                    break;
                case "3":
                    status = "处理中";
                    break;
                case "4":
                    status = "已处理";
                    break;
                case "5":
                    status = "已完成";
                    break;
                case "6":
                    status = "已解决";
                    break;
                default :
                    status = "暂无数据";
            }
            	row = sheet.createRow( i+3 );//因为有标题，所以这里+2
            	row.createCell((short) 0).setCellValue((i+1));
            	row.createCell((short) 1).setCellValue(String.valueOf(list.get(i).get("sheet_no").toString()));
            	row.createCell((short) 2).setCellValue(String.valueOf(list.get(i).get("start_time").toString()));
            	row.createCell((short) 3).setCellValue(String.valueOf(list.get(i).get("typeCode").toString()));
            	row.createCell((short) 4).setCellValue(String.valueOf(list.get(i).get("sponsor").toString()));
            	row.createCell((short) 5).setCellValue(String.valueOf(list.get(i).get("sourceCode").toString()));
            	row.createCell((short) 6).setCellValue(String.valueOf(status));
            	row.createCell((short) 7).setCellValue(String.valueOf(list.get(i).get("receiver").toString()));
            	row.createCell((short) 8).setCellValue(String.valueOf(list.get(i).get("exe_dept").toString()));
            }
        }
        
        HSSFRow row1 = sheet.createRow(0);

        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell1 = row1.createCell((short) 0);
        cell1.setCellValue("工单列表信息");
        cell1.setCellStyle(style1);

        HSSFRow row2 = sheet.createRow(1);
        sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell2 = row2.createCell((short) 0);
        cell2.setCellValue("统计时间:"+workOrder.getStartTime()+"-"+workOrder.getEndTime());
        cell2.setCellStyle(style2);
   //保存文件
        String path=url+"工单列表信息"+new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+".xls";
        ExcelReadUtil.excelWrite(path,wb);
        return path;
	}
}
