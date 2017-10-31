package com.manji.finance.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.manji.finance.withdrawals.Param.WithDrawalsParam;
import com.manji.finance.withdrawals.Withdrawals.WithdrawalsRepository;
import com.manji.finance.withdrawals.Withdrawals.WithdrawalsService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import com.jfinal.plugin.activerecord.Record;
import com.manji.finance.home.PaymentDetailsRepository;
import com.manji.finance.home.model.ScreenDo;
import com.manji.finance.order.OrderRepository;
import com.manji.finance.order.requestParams.OrderParam;
import com.manji.finance.recharge.RechargeRepository;
import com.manji.finance.recharge.enums.AllStatusEnum;
import com.manji.finance.recharge.enums.RechargeTypeEnums;
import com.manji.finance.recharge.requestParams.RechargeParams;

/**
 * 导出excel工具类
 * @author Administrator
 *
 */
public class ExcelUtils {

	/**引入相关的dao*/
	RechargeRepository rechargeRepository = new RechargeRepository();
	
    //private String url="/manji/uploads/finance_test/";//测试上传地址
	private String url = "/manji/uploads/finance/";//正式上传地址
    //private String url = "E://";//本地上传地址


	/**
	 * 导出excel公共接口
	 * @param param      查询条件
	 * @param title      excel表格的标题，将要显示的字段
	 * @param excelName  excel表格的名称
	 * @param content    每一个表格中的值
	 * @return
	 */
	public String excel(RechargeParams param,String[] title,String excelName,String[][] content){
		
		//第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
    //第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(excelName+"表");
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
        
        HSSFRow row1 = sheet.createRow(0);//第一行      设置标题
        HSSFCell cell1 = row1.createCell((short) 0);
        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 10));//合并单元格，开始行，开始列，结束行，结束列  
        cell1.setCellValue(excelName);
        cell1.setCellStyle(style1);
        
        HSSFRow row2 = sheet.createRow(1);//第二行      设置时间
        HSSFCell cell2 = row2.createCell((short) 0);
        sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        cell2.setCellValue("统计时间："+param.getStartTime()+"-"+param.getEndTime());
        cell2.setCellStyle(style2);
        
        HSSFRow row = sheet.createRow((int) 2);//第三行   设置标题

    // 第四步，创建单元格
        for(int i=0;i<title.length;i++){
        	HSSFCell cell = row.createCell((short) i);
        	cell.setCellValue(title[i]);
            cell.setCellStyle(style);
            sheet.setColumnWidth(i,20 * 256);//设置单元格的宽度
        }
   //赋值
        for(int m=0;m<content.length;m++){
        	String arr[] = content[m];
        	row = sheet.createRow( m+3 );//因为有标题，所以这里+2

        	for(int n=0;n<arr.length;n++){
            	row.createCell((short) n).setCellValue(arr[n]);
        	}
        } 
        
  //保存文件
        String path=url+excelName+new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+".xls";
        ExcelReadUtil.excelWrite(path,wb);
        return path;
	}
	
	
	
	
	/**
	 * 导出充值记录 信息
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings("deprecation")
	public String excelRecRecord(RechargeParams p) throws ParseException{
		
	//第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
    //第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("充值记录明细表");
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
        cell.setCellValue("编号");
        cell.setCellStyle(style);
        sheet.setColumnWidth(0,20 * 128);//设置单元格的宽度

        cell = row.createCell((short) 1);
        cell.setCellValue("交易流水号");
        cell.setCellStyle(style);
        sheet.setColumnWidth(1,20 * 512);

        cell = row.createCell((short) 2);
        cell.setCellValue("充值单号");
        cell.setCellStyle(style);
        sheet.setColumnWidth(2,20 * 512);

        cell = row.createCell((short) 3);
        cell.setCellValue("充值方式");
        cell.setCellStyle(style);
        sheet.setColumnWidth(3,20 * 256);

        cell = row.createCell((short) 4);
        cell.setCellValue("充值类型");
        cell.setCellStyle(style);
        sheet.setColumnWidth(4,20 * 256);

        cell = row.createCell((short) 5);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);
        sheet.setColumnWidth(5,20 * 256);

        cell = row.createCell((short) 6);
        cell.setCellValue("充值账户");
        cell.setCellStyle(style);
        sheet.setColumnWidth(6,20 * 256);

        cell = row.createCell((short) 7);
        cell.setCellValue("充值金额（元）");
        cell.setCellStyle(style);
        sheet.setColumnWidth(7,20 * 256);

        cell = row.createCell((short) 8);
        cell.setCellValue("到账金额（元）");
        cell.setCellStyle(style);
        sheet.setColumnWidth(8,20 * 256);

        cell = row.createCell((short) 9);
        cell.setCellValue("状态");
        cell.setCellStyle(style);
        sheet.setColumnWidth(9,20 * 256);

        cell = row.createCell((short) 10);
        cell.setCellValue("到账时间");
        cell.setCellStyle(style);
        sheet.setColumnWidth(10,20 * 512);
   //查询数据，写入数据
        StringBuffer sql = new StringBuffer("select * from t_interior_recharge_detail where 1=1");
        if(p.getOrderNo() != null && !"".equals(p.getOrderNo())){
        	sql.append(" and RECHARGE_ORDER_NO='"+p.getOrderNo()+"'");
        }
        if(p.getAccountName() != null && !"".equals(p.getAccountName())){
        	sql.append(" and USER_KEY='"+p.getAccountName()+"'");
        }
        if(p.getStatus() != null && !"".equals(p.getStatus())){
        	sql.append(" and STATUS='"+p.getStatus()+"'");
        }else{
        	sql.append(" and STATUS in ('6','5')");
        }
        if(p.getRechargeWay() != null && !"".equals(p.getRechargeWay())){
        	sql.append(" and RECHARGE_WAY='"+p.getRechargeWay()+"'");
        }
        if(p.getStartTime() != null && !"".equals(p.getStartTime()) && p.getEndTime() != null && !"".equals(p.getEndTime())){
        	String a = new SimpleDateFormat("yyyy-MM-dd").format(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(p.getEndTime()).getTime()+24*60*60*1000));
        	sql.append(" and ARRIVAL_TIME>='"+p.getStartTime()+"' and ARRIVAL_TIME<'"+a+"' ");
        }
        
        //System.out.println(sql.toString());
        List<Record> list = rechargeRepository.findByCondition(sql.toString());
        for(int i=0;i<list.size();i++){
        	row = sheet.createRow( i+3 );//因为有标题，所以这里+2
        	row.createCell((short) 0).setCellValue((i+1));
        	row.createCell((short) 1).setCellValue(String.valueOf(list.get(i).get("tranSN").toString()));
        	row.createCell((short) 2).setCellValue(String.valueOf(list.get(i).get("RECHARGE_ORDER_NO").toString()));
        	row.createCell((short) 3).setCellValue(String.valueOf(list.get(i).get("RECHARGE_WAY").toString()));
        	row.createCell((short) 4).setCellValue(String.valueOf(RechargeTypeEnums.getMessageByCode(list.get(i).get("RECHARGE_TYPE").toString())));
        	row.createCell((short) 5).setCellValue(String.valueOf(list.get(i).get("USER_NAME").toString()));
        	row.createCell((short) 6).setCellValue(String.valueOf(list.get(i).get("USER_KEY").toString()));
        	row.createCell((short) 7).setCellValue(String.valueOf(list.get(i).get("RECHARGE_MONEY").toString()));
        	row.createCell((short) 8).setCellValue(String.valueOf(list.get(i).get("ARRIVAL_MONEY").toString()));
        	row.createCell((short) 9).setCellValue(String.valueOf(AllStatusEnum.getMessageByCode(list.get(i).get("STATUS").toString())));
        	row.createCell((short) 10).setCellValue(String.valueOf(list.get(i).get("ARRIVAL_TIME").toString()));
        }
        
        HSSFRow row1 = sheet.createRow(0);

        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell1 = row1.createCell((short) 0);
        cell1.setCellValue("充值记录明细");
        cell1.setCellStyle(style1);

        HSSFRow row2 = sheet.createRow(1);
        sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell2 = row2.createCell((short) 0);
        cell2.setCellValue("统计时间："+p.getStartTime()+"-"+p.getEndTime());
        cell2.setCellStyle(style2);
   //保存文件
        String path=url+"充值记录明细"+new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+".xls";
        ExcelReadUtil.excelWrite(path,wb);
        return path;
	}
	
	/**
	 * 导出第三方信息
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String excelThirdPay(ScreenDo scree,String type){
		
	//第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
    //第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("第三方支出明细");
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
        cell.setCellValue("编号");
        cell.setCellStyle(style);
        sheet.setColumnWidth(0,20 * 128);//设置单元格的宽度

        cell = row.createCell((short) 1);
        cell.setCellValue("交易时间");
        cell.setCellStyle(style);
        sheet.setColumnWidth(1,20 * 512);

        cell = row.createCell((short) 2);
        cell.setCellValue("订单号");
        cell.setCellStyle(style);
        sheet.setColumnWidth(2,20 * 512);

        cell = row.createCell((short) 3);
        cell.setCellValue("订单类型");
        cell.setCellStyle(style);
        sheet.setColumnWidth(3,20 * 256);

        cell = row.createCell((short) 4);
        cell.setCellValue("商家");
        cell.setCellStyle(style);
        sheet.setColumnWidth(4,20 * 256);

        cell = row.createCell((short) 5);
        cell.setCellValue("会员账户");
        cell.setCellStyle(style);
        sheet.setColumnWidth(5,20 * 256);

        cell = row.createCell((short) 6);
        cell.setCellValue("支付方式");
        cell.setCellStyle(style);
        sheet.setColumnWidth(6,20 * 256);

        cell = row.createCell((short) 7);
        cell.setCellValue("支付状态");
        cell.setCellStyle(style);
        sheet.setColumnWidth(7,20 * 256);

        cell = row.createCell((short) 8);
        cell.setCellValue("订单金额");
        cell.setCellStyle(style);
        sheet.setColumnWidth(8,20 * 256);

        cell = row.createCell((short) 9);
        cell.setCellValue("实付金额");
        cell.setCellStyle(style);
        sheet.setColumnWidth(9,20 * 256);

        cell = row.createCell((short) 10);
        cell.setCellValue("补贴满意卷");
        cell.setCellStyle(style);
        sheet.setColumnWidth(10,20 * 512);
        //查询数据，写入数据
        List<Record> list = new PaymentDetailsRepository().ThirdPayDetailExcel(scree,type);
        if(list!=null){
        	for(int i=0;i<list.size();i++){
            	row = sheet.createRow( i+3 );//因为有标题，所以这里+2
            	row.createCell((short) 0).setCellValue((i+1));
            	row.createCell((short) 1).setCellValue(String.valueOf(list.get(i).get("complete_time").toString()));
            	row.createCell((short) 2).setCellValue(String.valueOf(list.get(i).get("order_no").toString()));
            	row.createCell((short) 3).setCellValue(String.valueOf(list.get(i).get("order_title").toString()));
            	row.createCell((short) 4).setCellValue(String.valueOf(list.get(i).get("gname").toString()));
            	row.createCell((short) 5).setCellValue(String.valueOf(list.get(i).get("uname").toString()));
            	if(type.equals("weixin")){
            		row.createCell((short) 6).setCellValue(String.valueOf("微信"));
            	}else if(type.equals("zhifubao")){
            		row.createCell((short) 6).setCellValue(String.valueOf("支付宝"));
            	}else if(type.equals("card")){
            		row.createCell((short) 6).setCellValue(String.valueOf("银行卡"));
            	}
            	row.createCell((short) 7).setCellValue(String.valueOf(list.get(i).get("status").toString()));
            	row.createCell((short) 8).setCellValue(String.valueOf(list.get(i).get("order_amount").toString()));
            	row.createCell((short) 9).setCellValue(String.valueOf(list.get(i).get("real_amount").toString()));
            	row.createCell((short) 10).setCellValue(String.valueOf(list.get(i).get("voucher").toString()));
            }
        }
        
        HSSFRow row1 = sheet.createRow(0);

        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell1 = row1.createCell((short) 0);
        cell1.setCellValue("第三方支出明细");
        cell1.setCellStyle(style1);

        HSSFRow row2 = sheet.createRow(1);
        sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell2 = row2.createCell((short) 0);
        cell2.setCellValue("统计时间："+scree.getStartTime()+"-"+scree.getEndTime());
        cell2.setCellStyle(style2);
   //保存文件
        String path=url+"第三方支出明细"+new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+".xls";
        ExcelReadUtil.excelWrite(path,wb);
        return path;
	}
	
	/**
	 * 导出余额支付订单
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String excelBalance(ScreenDo scree,String type){
		
	//第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
    //第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("余额支出明细");
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
        cell.setCellValue("编号");
        cell.setCellStyle(style);
        sheet.setColumnWidth(0,20 * 128);//设置单元格的宽度

        cell = row.createCell((short) 1);
        cell.setCellValue("交易时间");
        cell.setCellStyle(style);
        sheet.setColumnWidth(1,20 * 512);

        cell = row.createCell((short) 2);
        cell.setCellValue("订单号");
        cell.setCellStyle(style);
        sheet.setColumnWidth(2,20 * 512);

        cell = row.createCell((short) 3);
        cell.setCellValue("订单类型");
        cell.setCellStyle(style);
        sheet.setColumnWidth(3,20 * 256);

        cell = row.createCell((short) 4);
        cell.setCellValue("商家");
        cell.setCellStyle(style);
        sheet.setColumnWidth(4,20 * 256);

        cell = row.createCell((short) 5);
        cell.setCellValue("会员账户");
        cell.setCellStyle(style);
        sheet.setColumnWidth(5,20 * 256);

        cell = row.createCell((short) 6);
        cell.setCellValue("支付方式");
        cell.setCellStyle(style);
        sheet.setColumnWidth(6,20 * 256);

        cell = row.createCell((short) 7);
        cell.setCellValue("支付状态");
        cell.setCellStyle(style);
        sheet.setColumnWidth(7,20 * 256);

        cell = row.createCell((short) 8);
        cell.setCellValue("订单金额");
        cell.setCellStyle(style);
        sheet.setColumnWidth(8,20 * 256);

        cell = row.createCell((short) 9);
        cell.setCellValue("实付金额");
        cell.setCellStyle(style);
        sheet.setColumnWidth(9,20 * 256);

        cell = row.createCell((short) 10);
        cell.setCellValue("补贴满意卷");
        cell.setCellStyle(style);
        sheet.setColumnWidth(10,20 * 512);
        //查询数据，写入数据
        List<Record> list = new PaymentDetailsRepository().BalanceExcel(scree,type);
        if(list!=null){
        	for(int i=0;i<list.size();i++){
            	row = sheet.createRow( i+3 );//因为有标题，所以这里+2
            	row.createCell((short) 0).setCellValue((i+1));
            	row.createCell((short) 1).setCellValue(String.valueOf(list.get(i).get("complete_time").toString()));
            	row.createCell((short) 2).setCellValue(String.valueOf(list.get(i).get("order_no").toString()));
            	row.createCell((short) 3).setCellValue(String.valueOf(list.get(i).get("order_title").toString()));
            	row.createCell((short) 4).setCellValue(String.valueOf(list.get(i).get("gname").toString()));
            	row.createCell((short) 5).setCellValue(String.valueOf(list.get(i).get("uname").toString()));
            	row.createCell((short) 6).setCellValue(String.valueOf("余额支付"));
            	row.createCell((short) 7).setCellValue(String.valueOf("已付款"));
            	row.createCell((short) 8).setCellValue(String.valueOf(list.get(i).get("order_amount").toString()));
            	row.createCell((short) 9).setCellValue(String.valueOf(list.get(i).get("real_amount").toString()));
            	row.createCell((short) 10).setCellValue(String.valueOf(list.get(i).get("voucher").toString()));
            }
        }
        
        HSSFRow row1 = sheet.createRow(0);

        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell1 = row1.createCell((short) 0);
        cell1.setCellValue("余额支出明细");
        cell1.setCellStyle(style1);

        HSSFRow row2 = sheet.createRow(1);
        sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell2 = row2.createCell((short) 0);
        cell2.setCellValue("统计时间："+scree.getStartTime()+"-"+scree.getEndTime());
        cell2.setCellStyle(style2);
   //保存文件
        String path=url+"余额支出明细"+new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+".xls";
        ExcelReadUtil.excelWrite(path,wb);
        return path;
	}
	
	/**
	 * 导出余额支付订单
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String techServiceDetail(ScreenDo scree){
		
	//第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
    //第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("技术服务费");
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
     // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFRow row = sheet.createRow((int) 2);
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("编号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 1);
        cell.setCellValue("结算时间");
        cell.setCellStyle(style);
        sheet.setColumnWidth(1,20 * 512);

        cell = row.createCell((short) 2);
        cell.setCellValue("订单号");
        cell.setCellStyle(style);
        sheet.setColumnWidth(2,20 * 512);

        cell = row.createCell((short) 3);
        cell.setCellValue("商家名称");
        cell.setCellStyle(style);
        sheet.setColumnWidth(3,20 * 256);

        cell = row.createCell((short) 4);
        cell.setCellValue("帐户名");
        cell.setCellStyle(style);
        sheet.setColumnWidth(4,20 * 256);

        cell = row.createCell((short) 5);
        cell.setCellValue("主营业务");
        cell.setCellStyle(style);
        sheet.setColumnWidth(5,20 * 256);

        cell = row.createCell((short) 6);
        cell.setCellValue("会员账号");
        cell.setCellStyle(style);
        sheet.setColumnWidth(6,20 * 256);

        cell = row.createCell((short) 7);
        cell.setCellValue("支付类型");
        cell.setCellStyle(style);
        sheet.setColumnWidth(7,20 * 256);

        cell = row.createCell((short) 8);
        cell.setCellValue("用户实付金额");
        cell.setCellStyle(style);
        sheet.setColumnWidth(8,20 * 256);

        cell = row.createCell((short) 9);
        cell.setCellValue("满意券");
        cell.setCellStyle(style);
        sheet.setColumnWidth(9,20 * 256);

        cell = row.createCell((short) 10);
        cell.setCellValue("订单金额");
        cell.setCellStyle(style);
        sheet.setColumnWidth(10,20 * 256);

        cell = row.createCell((short) 11);
        cell.setCellValue("提现比列");
        cell.setCellStyle(style);
        sheet.setColumnWidth(11,20 * 256);

        cell = row.createCell((short) 12);
        cell.setCellValue("技术服务费");
        cell.setCellStyle(style);
        sheet.setColumnWidth(12,20 * 256);

        cell = row.createCell((short) 13);
        cell.setCellValue("商家应收金额");
        cell.setCellStyle(style);
        sheet.setColumnWidth(13,20 * 256);
        
        //查询数据，写入数据
        List<Record> list = new PaymentDetailsRepository().techServiceDetailExcel(scree);
        if(list!=null){
        	for(int i=0;i<list.size();i++){
            	row = sheet.createRow( i+3 );//因为有标题，所以这里+2
            	row.createCell((short) 0).setCellValue((i+1));
            	row.createCell((short) 1).setCellValue(String.valueOf(list.get(i).get("confirm_time").toString()));
            	row.createCell((short) 2).setCellValue(String.valueOf(list.get(i).get("order_no").toString()));
            	row.createCell((short) 3).setCellValue(String.valueOf(list.get(i).get("gnick").toString()));
            	row.createCell((short) 4).setCellValue(String.valueOf(list.get(i).get("gname").toString()));
            	row.createCell((short) 5).setCellValue(String.valueOf(list.get(i).get("TAG").toString()));
            	row.createCell((short) 6).setCellValue(String.valueOf(list.get(i).get("uname").toString()));
            	row.createCell((short) 7).setCellValue(String.valueOf(list.get(i).get("title").toString()));
            	row.createCell((short) 8).setCellValue(String.valueOf(list.get(i).get("real_amount").toString()));
            	row.createCell((short) 9).setCellValue(String.valueOf(list.get(i).get("voucher").toString()));
            	row.createCell((short) 10).setCellValue(String.valueOf(list.get(i).get("order_amount").toString()));
            	row.createCell((short) 11).setCellValue(String.valueOf(list.get(i).get("user_percentage").toString()));
            	row.createCell((short) 12).setCellValue(String.valueOf(list.get(i).get("technical").toString()));
            	row.createCell((short) 13).setCellValue(String.valueOf(list.get(i).get("real_shop_money").toString()));
            }
        }
        
        HSSFRow row1 = sheet.createRow(0);

        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell1 = row1.createCell((short) 0);
        cell1.setCellValue("技术服务费");
        cell1.setCellStyle(style1);

        HSSFRow row2 = sheet.createRow(1);
        sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell2 = row2.createCell((short) 0);
        cell2.setCellValue("统计时间："+scree.getStartTime()+"-"+scree.getEndTime());
        cell2.setCellStyle(style2);
   //保存文件
        String path=url+"技术服务费"+new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+".xls";
        ExcelReadUtil.excelWrite(path,wb);
        return path;
	}
	
	
	/**
	 * 金融服务费
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String excelFinance(ScreenDo scree){
		
	//第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
    //第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("金融服务费");
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
        cell.setCellValue("编号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 1);
        cell.setCellValue("交易时间");
        cell.setCellStyle(style);

        cell = row.createCell((short) 2);
        cell.setCellValue("订单号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 3);
        cell.setCellValue("订单类型");
        cell.setCellStyle(style);

        cell = row.createCell((short) 4);
        cell.setCellValue("商家名称");
        cell.setCellStyle(style);

        cell = row.createCell((short) 5);
        cell.setCellValue("会员账号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 6);
        cell.setCellValue("支付类型");
        cell.setCellStyle(style);

        cell = row.createCell((short) 7);
        cell.setCellValue("订单金额");
        cell.setCellStyle(style);

        cell = row.createCell((short) 8);
        cell.setCellValue("用户实付金额");
        cell.setCellStyle(style);

        cell = row.createCell((short) 9);
        cell.setCellValue("补贴满意券");
        cell.setCellStyle(style);

        sheet.setColumnWidth(0,20 * 128);
        sheet.setColumnWidth(1,20 * 312);
        sheet.setColumnWidth(2,20 * 512);
        sheet.setColumnWidth(3,20 * 128);
        sheet.setColumnWidth(4,20 * 328);
        sheet.setColumnWidth(5,20 * 228);
        sheet.setColumnWidth(6,20 * 128);
        sheet.setColumnWidth(7,20 * 228);
        sheet.setColumnWidth(8,20 * 228);
        sheet.setColumnWidth(9,20 * 228);
   //查询数据，写入数据
        List<Record> list = new PaymentDetailsRepository().financeDetailExcel(scree);
        for(int i=0;i<list.size();i++){
        	row = sheet.createRow( i+3 );//因为有标题，所以这里+2
        	row.createCell((short) 0).setCellValue((i+1));
        	row.createCell((short) 1).setCellValue(String.valueOf(list.get(i).get("complete_time").toString()));
        	row.createCell((short) 2).setCellValue(String.valueOf(list.get(i).get("withdrawals_no").toString()));
        	row.createCell((short) 3).setCellValue(String.valueOf(list.get(i).get("nick_name").toString()));
        	row.createCell((short) 4).setCellValue(String.valueOf(list.get(i).get("bank_user").toString()));
        	row.createCell((short) 5).setCellValue(String.valueOf(list.get(i).get("bank_name").toString()));
        	row.createCell((short) 6).setCellValue(String.valueOf(list.get(i).get("bank_card").toString()));
        	row.createCell((short) 7).setCellValue(String.valueOf(list.get(i).get("total_money").toString()));
        	row.createCell((short) 8).setCellValue(String.valueOf(list.get(i).get("commission").toString()));
        	row.createCell((short) 9).setCellValue(String.valueOf(list.get(i).get("arrival").toString()));
        }
        
        HSSFRow row1 = sheet.createRow(0);

        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell1 = row1.createCell((short) 0);
        cell1.setCellValue("金融服务费");
        cell1.setCellStyle(style1);

        HSSFRow row2 = sheet.createRow(1);
        sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell2 = row2.createCell((short) 0);
        cell2.setCellValue("统计时间："+scree.getStartTime()+"-"+scree.getEndTime());
        cell2.setCellStyle(style2);
   //保存文件
        String path=url+"金融服务费"+new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+".xls";
        ExcelReadUtil.excelWrite(path,wb);
        return path;
	}

	/**
	 * 满意卷支出
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String voucherDetailExcel(ScreenDo scree){
		
	//第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
    //第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("满意卷支出");
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
        cell.setCellValue("编号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 1);
        cell.setCellValue("交易时间");
        cell.setCellStyle(style);

        cell = row.createCell((short) 2);
        cell.setCellValue("订单号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 3);
        cell.setCellValue("订单类型");
        cell.setCellStyle(style);

        cell = row.createCell((short) 4);
        cell.setCellValue("商家名称");
        cell.setCellStyle(style);

        cell = row.createCell((short) 5);
        cell.setCellValue("会员账号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 6);
        cell.setCellValue("支付类型");
        cell.setCellStyle(style);

        cell = row.createCell((short) 7);
        cell.setCellValue("订单金额");
        cell.setCellStyle(style);

        cell = row.createCell((short) 8);
        cell.setCellValue("用户实付金额");
        cell.setCellStyle(style);

        cell = row.createCell((short) 9);
        cell.setCellValue("补贴满意券");
        cell.setCellStyle(style);

        sheet.setColumnWidth(0,20 * 128);
        sheet.setColumnWidth(1,20 * 312);
        sheet.setColumnWidth(2,20 * 512);
        sheet.setColumnWidth(3,20 * 128);
        sheet.setColumnWidth(4,20 * 328);
        sheet.setColumnWidth(5,20 * 228);
        sheet.setColumnWidth(6,20 * 128);
        sheet.setColumnWidth(7,20 * 228);
        sheet.setColumnWidth(8,20 * 228);
        sheet.setColumnWidth(9,20 * 228);
   //查询数据，写入数据
        List<Record> list = new PaymentDetailsRepository().voucherDetailExcel(scree);
        for(int i=0;i<list.size();i++){
        	row = sheet.createRow( i+3 );//因为有标题，所以这里+2
        	row.createCell((short) 0).setCellValue((i+1));
        	row.createCell((short) 1).setCellValue(String.valueOf(list.get(i).get("complete_time").toString()));
        	row.createCell((short) 2).setCellValue(String.valueOf(list.get(i).get("order_no").toString()));
        	row.createCell((short) 3).setCellValue(String.valueOf(list.get(i).get("order_title").toString()));
        	row.createCell((short) 4).setCellValue(String.valueOf(list.get(i).get("gname").toString()));
        	row.createCell((short) 5).setCellValue(String.valueOf(list.get(i).get("uname").toString()));
        	row.createCell((short) 6).setCellValue(String.valueOf(list.get(i).get("title").toString()));
        	row.createCell((short) 7).setCellValue(String.valueOf(list.get(i).get("order_amount").toString()));
        	row.createCell((short) 8).setCellValue(String.valueOf(list.get(i).get("real_amount").toString()));
        	row.createCell((short) 9).setCellValue(String.valueOf(list.get(i).get("voucher").toString()));
        }
        
        HSSFRow row1 = sheet.createRow(0);

        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell1 = row1.createCell((short) 0);
        cell1.setCellValue("满意卷支出");
        cell1.setCellStyle(style1);

        HSSFRow row2 = sheet.createRow(1);
        sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell2 = row2.createCell((short) 0);
        cell2.setCellValue("统计时间："+scree.getStartTime()+"-"+scree.getEndTime());
        cell2.setCellStyle(style2);
   //保存文件
        String path=url+"满意卷支出"+new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+".xls";
        ExcelReadUtil.excelWrite(path,wb);
        return path;
	}
	
	/**
	 * 售后退款
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String refoundDetailExcel(ScreenDo scree){
		
	//第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
    //第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("售后退款");
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
        cell.setCellValue("编号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 1);
        cell.setCellValue("退款时间");
        cell.setCellStyle(style);

        cell = row.createCell((short) 2);
        cell.setCellValue("退单号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 3);
        cell.setCellValue("对应订单号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 4);
        cell.setCellValue("订单类型");
        cell.setCellStyle(style);

        cell = row.createCell((short) 5);
        cell.setCellValue("商家");
        cell.setCellStyle(style);

        cell = row.createCell((short) 6);
        cell.setCellValue("会员账号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 7);
        cell.setCellValue("支付方式");
        cell.setCellStyle(style);

        cell = row.createCell((short) 8);
        cell.setCellValue("退款金额");
        cell.setCellStyle(style);

        cell = row.createCell((short) 9);
        cell.setCellValue("退满意券");
        cell.setCellStyle(style);

        cell = row.createCell((short) 10);
        cell.setCellValue("退现金");
        cell.setCellStyle(style);

        sheet.setColumnWidth(0,20 * 128);
        sheet.setColumnWidth(1,20 * 512);
        sheet.setColumnWidth(2,20 * 512);
        sheet.setColumnWidth(3,20 * 512);
        sheet.setColumnWidth(4,20 * 128);
        sheet.setColumnWidth(5,20 * 228);
        sheet.setColumnWidth(6,20 * 228);
        sheet.setColumnWidth(7,20 * 128);
        sheet.setColumnWidth(8,20 * 128);
        sheet.setColumnWidth(9,20 * 128);
        sheet.setColumnWidth(10,20 * 128);
   //查询数据，写入数据
        List<Record> list = new PaymentDetailsRepository().refoundDetailExcel(scree);
        for(int i=0;i<list.size();i++){
        	row = sheet.createRow( i+3 );//因为有标题，所以这里+2
        	row.createCell((short) 0).setCellValue((i+1));
        	row.createCell((short) 1).setCellValue(String.valueOf(list.get(i).get("add_time").toString()));
        	row.createCell((short) 2).setCellValue(String.valueOf(list.get(i).get("back_return_no").toString()));
        	row.createCell((short) 3).setCellValue(String.valueOf(list.get(i).get("order_no").toString()));
        	row.createCell((short) 4).setCellValue(String.valueOf(list.get(i).get("order_title").toString()));
        	row.createCell((short) 5).setCellValue(String.valueOf(list.get(i).get("gname").toString()));
        	row.createCell((short) 6).setCellValue(String.valueOf(list.get(i).get("uname").toString()));
        	row.createCell((short) 7).setCellValue(String.valueOf(list.get(i).get("title").toString()));
        	row.createCell((short) 8).setCellValue(String.valueOf(list.get(i).get("alltk").toString()));
        	row.createCell((short) 9).setCellValue(String.valueOf(list.get(i).get("tuikuandj").toString()));
        	row.createCell((short) 10).setCellValue(String.valueOf(list.get(i).get("tkxianjin").toString()));
        }
        
        HSSFRow row1 = sheet.createRow(0);

        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell1 = row1.createCell((short) 0);
        cell1.setCellValue("售后退款");
        cell1.setCellStyle(style1);

        HSSFRow row2 = sheet.createRow(1);
        sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell2 = row2.createCell((short) 0);
        cell2.setCellValue("统计时间："+scree.getStartTime()+"-"+scree.getEndTime());
        cell2.setCellStyle(style2);
   //保存文件
        String path=url+"售后退款"+new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+".xls";
        ExcelReadUtil.excelWrite(path,wb);
        return path;
	}
	
	/**
	 * 导出商家信息
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String shopDetailExcel(ScreenDo scree){
		
	//第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
    //第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("商家账户余额");
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
        cell.setCellValue("编号");
	    cell.setCellStyle(style);
	
	    cell = row.createCell((short) 1);
	    cell.setCellValue("商家名称");
	    cell.setCellStyle(style);
	
	    cell = row.createCell((short) 2);
	    cell.setCellValue("商家账号");
	    cell.setCellStyle(style);
	
	    cell = row.createCell((short) 3);
	    cell.setCellValue("商家区域");
	    cell.setCellStyle(style);
	
	    cell = row.createCell((short) 4);
	    cell.setCellValue("商家电话");
	    cell.setCellStyle(style);
	
	    cell = row.createCell((short) 5);
	    cell.setCellValue("商家业务");
	    cell.setCellStyle(style);
	
	    cell = row.createCell((short) 6);
	    cell.setCellValue("账户状态");
	    cell.setCellStyle(style);
	
	    cell = row.createCell((short) 7);
	    cell.setCellValue("余额");
	    cell.setCellStyle(style);
	
	    cell = row.createCell((short) 8);
	    cell.setCellValue("可提现余额");
	    cell.setCellStyle(style);
    
        sheet.setColumnWidth(0,20 * 128);
        sheet.setColumnWidth(1,20 * 212);
        sheet.setColumnWidth(2,20 * 512);
        sheet.setColumnWidth(3,20 * 512);
        sheet.setColumnWidth(4,20 * 512);
        sheet.setColumnWidth(5,20 * 512);
        sheet.setColumnWidth(6,20 * 128);
        sheet.setColumnWidth(7,20 * 128);
        sheet.setColumnWidth(8,20 * 128);
        //查询数据，写入数据
        List<Record> list = new PaymentDetailsRepository().shopDetailEXCEL(scree);
        if(list!=null){
        	for(int i=0;i<list.size();i++){
            	row = sheet.createRow( i+3 );//因为有标题，所以这里+2
            	row.createCell((short) 0).setCellValue((i+1));
            	row.createCell((short) 1).setCellValue(String.valueOf(list.get(i).get("name").toString()));
            	row.createCell((short) 2).setCellValue(String.valueOf(list.get(i).get("user_name").toString()));
            	row.createCell((short) 3).setCellValue(String.valueOf(list.get(i).get("area").toString()));
            	row.createCell((short) 4).setCellValue(String.valueOf(list.get(i).get("mobile").toString()));
            	row.createCell((short) 5).setCellValue(String.valueOf(list.get(i).get("TAG").toString()));
            	row.createCell((short) 6).setCellValue(String.valueOf(list.get(i).get("state").toString()));
            	row.createCell((short) 7).setCellValue(String.valueOf(list.get(i).get("amount").toString()));
            	row.createCell((short) 8).setCellValue(String.valueOf(list.get(i).get("allow_amount").toString()));
            }
        }
        
        HSSFRow row1 = sheet.createRow(0);

        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell1 = row1.createCell((short) 0);
        cell1.setCellValue("商家账户余额");
        cell1.setCellStyle(style1);

        HSSFRow row2 = sheet.createRow(1);
        sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell2 = row2.createCell((short) 0);
        cell2.setCellValue("统计时间："+scree.getStartTime()+"-"+scree.getEndTime());
        cell2.setCellStyle(style2);
   //保存文件
        String path=url+"商家账户余额"+new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+".xls";
        ExcelReadUtil.excelWrite(path,wb);
        return path;
	}
	
	/**
	 * 导出用户信息
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String userDetailExcel(ScreenDo scree){
		
	//第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
    //第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("用户账单余额");
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
        cell.setCellValue("编号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 1);
        cell.setCellValue("用户名");
        cell.setCellStyle(style);

        cell = row.createCell((short) 2);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);

        cell = row.createCell((short) 3);
        cell.setCellValue("账户状态");
        cell.setCellStyle(style);

        cell = row.createCell((short) 4);
        cell.setCellValue("余额");
        cell.setCellStyle(style);

        cell = row.createCell((short) 5);
        cell.setCellValue("可提现余额");
        cell.setCellStyle(style);
    
        sheet.setColumnWidth(0,20 * 128);
        sheet.setColumnWidth(1,20 * 212);
        sheet.setColumnWidth(2,20 * 512);
        sheet.setColumnWidth(3,20 * 512);
        sheet.setColumnWidth(4,20 * 512);
        sheet.setColumnWidth(5,20 * 512);
        //查询数据，写入数据
        List<Record> list = new PaymentDetailsRepository().userDetailEXCEL(scree);
        if(list!=null){
        	for(int i=0;i<list.size();i++){
            	row = sheet.createRow( i+3 );//因为有标题，所以这里+2
            	row.createCell((short) 0).setCellValue((i+1));
            	row.createCell((short) 1).setCellValue(String.valueOf(list.get(i).get("user_name").toString()));
            	row.createCell((short) 2).setCellValue(String.valueOf(list.get(i).get("person_name").toString()));
            	row.createCell((short) 3).setCellValue(String.valueOf(list.get(i).get("state").toString()));
            	row.createCell((short) 4).setCellValue(String.valueOf(list.get(i).get("amount").toString()));
            	row.createCell((short) 5).setCellValue(String.valueOf(list.get(i).get("allow_amount").toString()));
            }
        }
        
        HSSFRow row1 = sheet.createRow(0);

        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell1 = row1.createCell((short) 0);
        cell1.setCellValue("用户账单余额");
        cell1.setCellStyle(style1);

        HSSFRow row2 = sheet.createRow(1);
        sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell2 = row2.createCell((short) 0);
        cell2.setCellValue("统计时间："+scree.getStartTime()+"-"+scree.getEndTime());
        cell2.setCellStyle(style2);
   //保存文件
        String path=url+"用户账单余额"+new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+".xls";
        ExcelReadUtil.excelWrite(path,wb);
        return path;
	}
	
	/**
	 * 导出用户/商家资金流水
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String userDetailExcel(ScreenDo scree,String userId,String type){
		
	//第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
    //第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("资金流水明细");
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
        cell.setCellValue("编号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 1);
        cell.setCellValue("变动时间");
        cell.setCellStyle(style);

        cell = row.createCell((short) 2);
        cell.setCellValue("期初值");
        cell.setCellStyle(style);

        cell = row.createCell((short) 3);
        cell.setCellValue("变动值");
        cell.setCellStyle(style);

        cell = row.createCell((short) 4);
        cell.setCellValue("期末值");
        cell.setCellStyle(style);

        cell = row.createCell((short) 5);
        cell.setCellValue("类型");
        cell.setCellStyle(style);

        cell = row.createCell((short) 6);
        cell.setCellValue("单号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 7);
        cell.setCellValue("备注");
        cell.setCellStyle(style);


        sheet.setColumnWidth(0,20 * 128);
        sheet.setColumnWidth(1,20 * 512);
        sheet.setColumnWidth(2,20 * 256);
        sheet.setColumnWidth(3,20 * 256);
        sheet.setColumnWidth(4,20 * 256);
        sheet.setColumnWidth(5,20 * 128);
        sheet.setColumnWidth(6,20 * 512);
        sheet.setColumnWidth(7,20 * 312);
        //查询数据，写入数据
        List<Record> list = new PaymentDetailsRepository().flowDetailsEXCEL(scree,Integer.parseInt(userId),type);
        if(list!=null){
        	for(int i=0;i<list.size();i++){
            	row = sheet.createRow( i+3 );//因为有标题，所以这里+2
            	row.createCell((short) 0).setCellValue((i+1));
            	row.createCell((short) 1).setCellValue(String.valueOf(list.get(i).get("add_time").toString()));
            	row.createCell((short) 2).setCellValue(String.valueOf(list.get(i).get("old_value").toString()));
            	row.createCell((short) 3).setCellValue(String.valueOf(list.get(i).get("value").toString()));
            	row.createCell((short) 4).setCellValue(String.valueOf(list.get(i).get("new_value").toString()));
            	row.createCell((short) 5).setCellValue(String.valueOf(list.get(i).get("type").toString()));
            	row.createCell((short) 6).setCellValue(String.valueOf(list.get(i).get("order_no").toString()));
            	row.createCell((short) 7).setCellValue(String.valueOf(list.get(i).get("remark").toString()));
            }
        }
        
        HSSFRow row1 = sheet.createRow(0);

        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell1 = row1.createCell((short) 0);
        cell1.setCellValue("资金流水明细");
        cell1.setCellStyle(style1);

        HSSFRow row2 = sheet.createRow(1);
        sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell2 = row2.createCell((short) 0);
        cell2.setCellValue("统计时间："+scree.getStartTime()+"-"+scree.getEndTime());
        cell2.setCellStyle(style2);
   //保存文件
        String path=url+"资金流水明细"+new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+".xls";
        ExcelReadUtil.excelWrite(path,wb);
        return path;
	}


    WithdrawalsService withdrawalsService=new WithdrawalsService();
    /**
     * 导出提现记录
     * @return
     */
    @SuppressWarnings("deprecation")
    public String userWithdrawalsExcel(WithDrawalsParam withDrawalsParam){

        //第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        //第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("提现记录");
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
        cell.setCellValue("编号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 1);
        cell.setCellValue("商家名称");
        cell.setCellStyle(style);

        cell = row.createCell((short) 2);
        cell.setCellValue("商家电话");
        cell.setCellStyle(style);

        cell = row.createCell((short) 3);
        cell.setCellValue("商家类别");
        cell.setCellStyle(style);

        cell = row.createCell((short) 4);
        cell.setCellValue("商家区域");
        cell.setCellStyle(style);

        cell = row.createCell((short) 5);
        cell.setCellValue("订单号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 6);
        cell.setCellValue("交易日期");
        cell.setCellStyle(style);

        cell = row.createCell((short) 7);
        cell.setCellValue("订单金额");
        cell.setCellStyle(style);

        cell = row.createCell((short) 8);
        cell.setCellValue("技术服务费比例");
        cell.setCellStyle(style);

        cell = row.createCell((short) 9);
        cell.setCellValue("技术服务费");
        cell.setCellStyle(style);


        cell = row.createCell((short) 10);
        cell.setCellValue("账户提现");
        cell.setCellStyle(style);


        cell = row.createCell((short) 11);
        cell.setCellValue("满意券");
        cell.setCellStyle(style);

        cell = row.createCell((short) 12);
        cell.setCellValue("累计可提现金额（元）");
        cell.setCellStyle(style);

        cell = row.createCell((short) 13);
        cell.setCellValue("提现日期");
        cell.setCellStyle(style);

        cell = row.createCell((short) 14);
        cell.setCellValue("账户提现");
        cell.setCellStyle(style);

        cell = row.createCell((short) 15);
        cell.setCellValue("提现手续费");
        cell.setCellStyle(style);

        cell = row.createCell((short) 16);
        cell.setCellValue("满意卷");
        cell.setCellStyle(style);

        cell = row.createCell((short) 17);
        cell.setCellValue("合计");
        cell.setCellStyle(style);

        cell = row.createCell((short) 18);
        cell.setCellValue("账户余额（元）不含满意券");
        cell.setCellStyle(style);

        cell = row.createCell((short) 19);
        cell.setCellValue("开户名");
        cell.setCellStyle(style);


        cell = row.createCell((short) 20);
        cell.setCellValue("开户行");
        cell.setCellStyle(style);

        cell = row.createCell((short) 21);
        cell.setCellValue("所属支行");
        cell.setCellStyle(style);

        cell = row.createCell((short) 22);
        cell.setCellValue("银行账号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 23);
        cell.setCellValue("是否提现");
        cell.setCellStyle(style);

        cell = row.createCell((short) 24);
        cell.setCellValue("备注");
        cell.setCellStyle(style);

        cell = row.createCell((short) 25);
        cell.setCellValue("到账金额");
        cell.setCellStyle(style);



















        sheet.setColumnWidth(0,20 * 64);
        sheet.setColumnWidth(1,20 * 256);
        sheet.setColumnWidth(2,20 * 190);
        sheet.setColumnWidth(3,20 * 123);
        sheet.setColumnWidth(4,20 * 256);
        sheet.setColumnWidth(5,20 * 322);
        sheet.setColumnWidth(6,20 * 256);
        sheet.setColumnWidth(7,20 * 128);
        sheet.setColumnWidth(8,20 * 128);
        sheet.setColumnWidth(9,20 * 128);
        sheet.setColumnWidth(10,20 * 128);
        sheet.setColumnWidth(11,20 * 128);
        sheet.setColumnWidth(12,20 * 128);
        sheet.setColumnWidth(13,20 * 128);
        sheet.setColumnWidth(14,20 * 128);
        sheet.setColumnWidth(15,20 * 128);
        sheet.setColumnWidth(16,20 * 128);
        sheet.setColumnWidth(17,20 * 128);
        sheet.setColumnWidth(18,20 * 128);
        sheet.setColumnWidth(19,20 * 128);
        sheet.setColumnWidth(20,20 * 128);
        sheet.setColumnWidth(21,20 * 322);
        sheet.setColumnWidth(22,20 * 128);
        sheet.setColumnWidth(23,20 * 128);
        sheet.setColumnWidth(24,20 * 128);
        sheet.setColumnWidth(25,20 * 128);




        //查询数据，写入数据
        List<Record> list=withdrawalsService.getAllWithdrawals(withDrawalsParam);
        if(list!=null){
            for(int i=0;i<list.size();i++){
                row = sheet.createRow( i+3 );//因为有标题，所以这里+2
                row.createCell((short) 0).setCellValue((i+1));
                if(list.get(i).get("shopInfoname") != null) {
                    row.createCell((short) 1).setCellValue(String.valueOf(list.get(i).get("shopInfoname").toString()));
                }
                if(list.get(i).get("shopmoble") != null) {
                    row.createCell((short) 2).setCellValue(String.valueOf(list.get(i).get("shopmoble").toString()));
                }
                if(list.get(i).get("TAG") != null) {
                    row.createCell((short) 3).setCellValue(String.valueOf(list.get(i).get("TAG").toString()));
                }
                if(list.get(i).get("area") != null) {
                    row.createCell((short) 4).setCellValue(String.valueOf(list.get(i).get("area").toString()));
                }
                if(list.get(i).get("orders_no") != null){
                    row.createCell((short) 5).setCellValue(String.valueOf(list.get(i).get("orders_no").toString()));
                }
                row.createCell((short) 6).setCellValue(String.valueOf(list.get(i).get("complete_time").toString()));
                if(list.get(i).get("real_amount") != null){
                    row.createCell((short) 7).setCellValue(String.valueOf(list.get(i).get("real_amount").toString()));
                }
                if(list.get(i).get("user_percentage") != null){
                    row.createCell((short) 8).setCellValue(String.valueOf(list.get(i).get("user_percentage").toString())+"%");
                }
                if(list.get(i).get("sxf") != null){
                    row.createCell((short) 9).setCellValue(String.valueOf(list.get(i).get("sxf").toString()));
                }
                if(list.get(i).get("balance_amount") != null){
                    row.createCell((short) 10).setCellValue(String.valueOf(list.get(i).get("balance_amount").toString()));
                }
                if(list.get(i).get("ordervoucher") != null){
                    row.createCell((short) 11).setCellValue(String.valueOf(list.get(i).get("ordervoucher").toString()));
                }
                if(list.get(i).get("zhje") != null){
                    row.createCell((short) 12).setCellValue(String.valueOf(list.get(i).get("zhje").toString()));
                }
                if(list.get(i).get("add_time") != null){
                    row.createCell((short) 13).setCellValue(String.valueOf(list.get(i).get("add_time").toString()));
                }
                if(list.get(i).get("amount") != null){
                    row.createCell((short) 14).setCellValue(String.valueOf(list.get(i).get("amount").toString()));
                }
                if(list.get(i).get("commission") != null){
                    row.createCell((short) 15).setCellValue(String.valueOf(list.get(i).get("commission").toString()));
                }
                if(list.get(i).get("voucher") != null){
                    row.createCell((short) 16).setCellValue(String.valueOf(list.get(i).get("voucher").toString()));
                }
                if(list.get(i).get("total_money") != null){
                    row.createCell((short) 17).setCellValue(String.valueOf(list.get(i).get("total_money").toString()));
                }
                row.createCell((short) 18).setCellValue(String.valueOf(list.get(i).get("zhye").toString()));
                row.createCell((short) 19).setCellValue(String.valueOf(list.get(i).get("bank_user").toString()));
                row.createCell((short) 20).setCellValue(String.valueOf(list.get(i).get("bank_name").toString()));
                row.createCell((short) 21).setCellValue(String.valueOf(list.get(i).get("bank_address").toString()));
                row.createCell((short) 22).setCellValue(String.valueOf(list.get(i).get("bank_card").toString()));
                row.createCell((short) 23).setCellValue(String.valueOf("是"));
                row.createCell((short) 24).setCellValue(String.valueOf(""));
                if(list.get(i).get("dzje") != null){
                    row.createCell((short) 25).setCellValue(String.valueOf(list.get(i).get("dzje").toString()));
                }
            }
        }

        //保存文件
        String path=url+"提现记录"+new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+".xls";
        ExcelReadUtil.excelWrite(path,wb);
        return path;
    }



	
	/**
	 * 导出订单 信息
	 * @return
	 */
	
	@SuppressWarnings("deprecation")
	public String userOrdersExcel(OrderParam orderParam){
		
	//第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
    //第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("订单信息");
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
        cell.setCellValue("编号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 1);
        cell.setCellValue("商家名称");
        cell.setCellStyle(style);

        cell = row.createCell((short) 2);
        cell.setCellValue("商家区域");
        cell.setCellStyle(style);

        cell = row.createCell((short) 3);
        cell.setCellValue("商家电话");
        cell.setCellStyle(style);

        cell = row.createCell((short) 4);
        cell.setCellValue("会员账号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 5);
        cell.setCellValue("订单编号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 6);
        cell.setCellValue("订单类型");
        cell.setCellStyle(style);

        cell = row.createCell((short) 7);
        cell.setCellValue("支付方式");
        cell.setCellStyle(style);
        
        cell = row.createCell((short) 8);
        cell.setCellValue("用户实付金额");
        cell.setCellStyle(style);
        
        cell = row.createCell((short) 9);
        cell.setCellValue("补贴满意券");
        cell.setCellStyle(style);
        
        cell = row.createCell((short) 10);
        cell.setCellValue("订单金额");
        cell.setCellStyle(style);
        
        cell = row.createCell((short) 11);
        cell.setCellValue("提点比例");
        cell.setCellStyle(style);
        
        cell = row.createCell((short) 12);
        cell.setCellValue("技术服务费");
        cell.setCellStyle(style);
        
        cell = row.createCell((short) 13);
        cell.setCellValue("商家应收总金额");
        cell.setCellStyle(style);
        
        cell = row.createCell((short) 14);
        cell.setCellValue("订单状态");
        cell.setCellStyle(style);
        
        cell = row.createCell((short) 15);
        cell.setCellValue("下单时间");
        cell.setCellStyle(style);
        
        cell = row.createCell((short) 16);
        cell.setCellValue("交易完成时间");
        cell.setCellStyle(style);
        
        cell = row.createCell((short) 17);
        cell.setCellValue("结算时间");
        cell.setCellStyle(style);


        sheet.setColumnWidth(0,20 * 128);
        sheet.setColumnWidth(1,20 * 512);
        sheet.setColumnWidth(2,20 * 512);
        sheet.setColumnWidth(3,20 * 256);
        sheet.setColumnWidth(4,20 * 256);
        sheet.setColumnWidth(5,20 * 256);
        sheet.setColumnWidth(6,20 * 256);
        sheet.setColumnWidth(7,20 * 256);
        sheet.setColumnWidth(8,20 * 128);
        sheet.setColumnWidth(9,20 * 312);
        sheet.setColumnWidth(10,20 * 312);
        sheet.setColumnWidth(11,20 * 312);
        sheet.setColumnWidth(12,20 * 312);
        sheet.setColumnWidth(13,20 * 312);
        sheet.setColumnWidth(14,20 * 312);
        sheet.setColumnWidth(15,20 * 312);
        sheet.setColumnWidth(16,20 * 312);
        sheet.setColumnWidth(17,20 * 312);
        //查询数据，写入数据
        List<Record> list = new OrderRepository().ordersEXCEL(orderParam);
        DecimalFormat df   = new DecimalFormat("######0.00");
        if(list!=null){
            Double a;
            Double b;
        	Double c;
        	for(int i=0;i<list.size();i++){
        		 a = Double.parseDouble(list.get(i).get("order_amount").toString());
        		 b = Double.parseDouble(list.get(i).get("user_percentage").toString());
        		 c = a*b;

        		
            	row = sheet.createRow( i+3 );//因为有标题，所以这里+2
            	row.createCell((short) 0).setCellValue((i+1));
            	row.createCell((short) 1).setCellValue(String.valueOf(list.get(i).get("name").toString()));
            	row.createCell((short) 2).setCellValue(String.valueOf(list.get(i).get("area").toString()));
            	row.createCell((short) 3).setCellValue(String.valueOf(list.get(i).getStr("mobile")));
            	row.createCell((short) 4).setCellValue(String.valueOf(list.get(i).get("user_name").toString()));
            	row.createCell((short) 5).setCellValue(String.valueOf(list.get(i).get("order_no").toString()));
            	row.createCell((short) 6).setCellValue(String.valueOf(list.get(i).get("order_type").toString()));
            	row.createCell((short) 7).setCellValue(String.valueOf(list.get(i).get("title").toString()));
            	row.createCell((short) 8).setCellValue(String.valueOf(list.get(i).get("real_amount").toString()));
            	row.createCell((short) 9).setCellValue(String.valueOf(list.get(i).get("voucher").toString()));
            	row.createCell((short) 10).setCellValue(String.valueOf(list.get(i).get("order_amount").toString()));
            	row.createCell((short) 11).setCellValue(String.valueOf(list.get(i).get("user_percentage").toString()));
            	row.createCell((short) 12).setCellValue(String.valueOf(df.format(c)));
            	row.createCell((short) 13).setCellValue(a-c);
            	row.createCell((short) 14).setCellValue(String.valueOf(list.get(i).get("balance_state").toString()));
            	row.createCell((short) 15).setCellValue(String.valueOf(list.get(i).get("add_time").toString()));
            	row.createCell((short) 16).setCellValue(String.valueOf(list.get(i).get("complete_time").toString()));
            	row.createCell((short) 17).setCellValue(String.valueOf(list.get(i).get("real_balance_date").toString()));
            }
        }
        
        HSSFRow row1 = sheet.createRow(0);

        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell1 = row1.createCell((short) 0);
        cell1.setCellValue("订单信息");
        cell1.setCellStyle(style1);

        HSSFRow row2 = sheet.createRow(1);
        sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell2 = row2.createCell((short) 0);
        cell2.setCellValue("统计时间："+orderParam.getStartTime()+"-"+orderParam.getEndTime());
        cell2.setCellStyle(style2);
        //保存文件
        String path=url+"订单信息"+new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+".xls";
        ExcelReadUtil.excelWrite(path,wb);
        return path;
	}
	
	
	
	/**
	 * 导出资金流水信息
	 * @return
	 */
	
	@SuppressWarnings("deprecation")
	public String toAmountExcel(OrderParam orderParam){
		
		//第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		//第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("资金流水信息");
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
		cell.setCellValue("变动时间");
		cell.setCellStyle(style);
		
		cell = row.createCell((short) 1);
		cell.setCellValue("初期值");
		cell.setCellStyle(style);
		
		cell = row.createCell((short) 2);
		cell.setCellValue("变动值");
		cell.setCellStyle(style);
		
		cell = row.createCell((short) 3);
		cell.setCellValue("期末值");
		cell.setCellStyle(style);
		
		cell = row.createCell((short) 4);
		cell.setCellValue("类型");
		cell.setCellStyle(style);
		
		cell = row.createCell((short) 5);
		cell.setCellValue("订单号");
		cell.setCellStyle(style);
		
		cell = row.createCell((short) 6);
		cell.setCellValue("备注");
		cell.setCellStyle(style);
		
		
		
		sheet.setColumnWidth(0,20 * 128);
		sheet.setColumnWidth(1,20 * 512);
		sheet.setColumnWidth(2,20 * 512);
		sheet.setColumnWidth(3,20 * 256);
		sheet.setColumnWidth(4,20 * 256);
		sheet.setColumnWidth(5,20 * 256);
		sheet.setColumnWidth(6,20 * 256);
		
		//查询数据，写入数据
		List<Record> list = new OrderRepository().toAmountEXCEL(orderParam);
		if(list!=null){
			
			for(int i=0;i<list.size();i++){
				
				row = sheet.createRow( i+3 );//因为有标题，所以这里+2
				row.createCell((short) 0).setCellValue(String.valueOf(list.get(i).get("add_time").toString()));
				row.createCell((short) 1).setCellValue(String.valueOf(list.get(i).get("old_value").toString()));
				row.createCell((short) 2).setCellValue(String.valueOf(list.get(i).get("value").toString()));
				row.createCell((short) 3).setCellValue(String.valueOf(list.get(i).get("new_value").toString()));
				row.createCell((short) 4).setCellValue(String.valueOf(list.get(i).get("type").toString()));
				row.createCell((short) 5).setCellValue(String.valueOf(list.get(i).get("order_no").toString()));
				row.createCell((short) 6).setCellValue(String.valueOf(list.get(i).get("remark").toString()));
			}
		}
		
		HSSFRow row1 = sheet.createRow(0);
		
		sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
		HSSFCell cell1 = row1.createCell((short) 0);
		cell1.setCellValue("资金流水信息");
		cell1.setCellStyle(style1);
		
		HSSFRow row2 = sheet.createRow(1);
		sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
		HSSFCell cell2 = row2.createCell((short) 0);
		cell2.setCellValue("统计时间："+orderParam.getStartTime()+"-"+orderParam.getEndTime());
		cell2.setCellStyle(style2);
		//保存文件
		String path=url+"订单信息"+new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+".xls";
		ExcelReadUtil.excelWrite(path,wb);
		return path;
	}
	
	
	/**
	 * 导出结算订单信息
	 * @return
	 */
	
	@SuppressWarnings("deprecation")
	public String AccountExcel(OrderParam orderParam){
		
		//第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		//第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("结算订单信息");
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
		cell.setCellValue("编号");
		cell.setCellStyle(style);
		
		cell = row.createCell((short) 1);
		cell.setCellValue("订单号");
		cell.setCellStyle(style);
		
		cell = row.createCell((short) 2);
		cell.setCellValue("订单类型");
		cell.setCellStyle(style);
		
		cell = row.createCell((short) 3);
		cell.setCellValue("支付方式");
		cell.setCellStyle(style);
		
		cell = row.createCell((short) 4);
		cell.setCellValue("用户实付总金额");
		cell.setCellStyle(style);
		
		cell = row.createCell((short) 5);
		cell.setCellValue("补贴满意券");
		cell.setCellStyle(style);
		
		cell = row.createCell((short) 6);
		cell.setCellValue("订单金额");
		cell.setCellStyle(style);
		
		cell = row.createCell((short) 7);
		cell.setCellValue("提点比例");
		cell.setCellStyle(style);
		
		cell = row.createCell((short) 8);
		cell.setCellValue("技术服务费");
		cell.setCellStyle(style);
		
		cell = row.createCell((short) 9);
		cell.setCellValue("商家应收总金额");
		cell.setCellStyle(style);
		
		cell = row.createCell((short) 10);
		cell.setCellValue("结算金额");
		cell.setCellStyle(style);
		
		cell = row.createCell((short) 11);
		cell.setCellValue("结算状态");
		cell.setCellStyle(style);
		
		cell = row.createCell((short) 12);
		cell.setCellValue("交易完成时间");
		cell.setCellStyle(style);
		
		cell = row.createCell((short) 13);
		cell.setCellValue("结算时间");
		cell.setCellStyle(style);
		
		sheet.setColumnWidth(0,20 * 128);
		sheet.setColumnWidth(1,20 * 512);
		sheet.setColumnWidth(2,20 * 512);
		sheet.setColumnWidth(3,20 * 256);
		sheet.setColumnWidth(4,20 * 256);
		sheet.setColumnWidth(5,20 * 256);
		sheet.setColumnWidth(6,20 * 256);
		sheet.setColumnWidth(7,20 * 256);
		sheet.setColumnWidth(8,20 * 256);
		sheet.setColumnWidth(9,20 * 256);
		sheet.setColumnWidth(10,20 * 256);
		sheet.setColumnWidth(11,20 * 256);
		sheet.setColumnWidth(12,20 * 256);
		sheet.setColumnWidth(13,20 * 256);
		
		//查询数据，写入数据
		List<Record> list = new OrderRepository().toAccountEXCEL(orderParam);
		if(list!=null){
			double a;
        	double b;
        	double c;
        	String statusStr = "";
			for(int i=0;i<list.size();i++){
				 a = Double.parseDouble(list.get(i).get("order_amount").toString());
        		 b = Double.parseDouble(list.get(i).get("user_percentage").toString());
        		 c = a*b;
        		 
        		 switch(list.get(i).get("settlement_status").toString()){
					case "0":
						statusStr = "待结算";
						break;
					case "1":
						statusStr = "已结算";
						break;
					default:
						statusStr = "";
				}
				row = sheet.createRow( i+3 );//因为有标题，所以这里+2
				row.createCell((short) 0).setCellValue((i+1));
				row.createCell((short) 1).setCellValue(String.valueOf(list.get(i).get("order_no").toString()));
				row.createCell((short) 2).setCellValue(String.valueOf(list.get(i).get("order_type").toString()));
				row.createCell((short) 3).setCellValue(String.valueOf(list.get(i).get("title").toString()));
				row.createCell((short) 4).setCellValue(String.valueOf(list.get(i).get("real_amount").toString()));
				row.createCell((short) 5).setCellValue(String.valueOf(list.get(i).get("voucher").toString()));
				row.createCell((short) 6).setCellValue(String.valueOf(list.get(i).get("order_amount").toString()));
				row.createCell((short) 7).setCellValue(String.valueOf(list.get(i).get("user_percentage").toString()));
				row.createCell((short) 8).setCellValue(String.valueOf(String.valueOf(c)));
				row.createCell((short) 9).setCellValue(String.valueOf(a-c));
				row.createCell((short) 10).setCellValue((statusStr == "已结算" ? String.valueOf((a-c)) : "--"));
				row.createCell((short) 11).setCellValue(String.valueOf(statusStr));
				row.createCell((short) 12).setCellValue(String.valueOf(list.get(i).get("complete_time").toString()));
				row.createCell((short) 13).setCellValue(String.valueOf(list.get(i).get("settlement_time").toString()));
			}
		}
		
		HSSFRow row1 = sheet.createRow(0);
		
		sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
		HSSFCell cell1 = row1.createCell((short) 0);
		cell1.setCellValue("结算订单信息");
		cell1.setCellStyle(style1);
		
		HSSFRow row2 = sheet.createRow(1);
		sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
		HSSFCell cell2 = row2.createCell((short) 0);
		cell2.setCellValue("统计时间："+orderParam.getStartTime()+"-"+orderParam.getEndTime());
		cell2.setCellStyle(style2);
		//保存文件
		String path=url+"订单信息"+new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+".xls";
		ExcelReadUtil.excelWrite(path,wb);
		return path;
	}
	
	
	/**
	 * 导出用户/商家资金流水
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String riyueselectExcel(ScreenDo scree){
		
	//第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
    //第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("详情资金流水查询(日月报表)");
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
        cell.setCellValue("编号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 1);
        cell.setCellValue("交易时间");
        cell.setCellStyle(style);

        cell = row.createCell((short) 2);
        cell.setCellValue("支付时间");
        cell.setCellStyle(style);

        cell = row.createCell((short) 3);
        cell.setCellValue("订单号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 4);
        cell.setCellValue("订单类型");
        cell.setCellStyle(style);

        cell = row.createCell((short) 5);
        cell.setCellValue("商家名称");
        cell.setCellStyle(style);

        cell = row.createCell((short) 6);
        cell.setCellValue("会员账户");
        cell.setCellStyle(style);

        cell = row.createCell((short) 7);
        cell.setCellValue("订单金额");
        cell.setCellStyle(style);
        
        cell = row.createCell((short) 8);
        cell.setCellValue("支付方式");
        cell.setCellStyle(style);
        
        cell = row.createCell((short) 9);
        cell.setCellValue("提现比例");
        cell.setCellStyle(style);
        
        cell = row.createCell((short) 10);
        cell.setCellValue("技术服务费");
        cell.setCellStyle(style);
        
        cell = row.createCell((short) 11);
        cell.setCellValue("满意卷");
        cell.setCellStyle(style);
        
        cell = row.createCell((short) 12);
        cell.setCellValue("实付金额");
        cell.setCellStyle(style);
        
        cell = row.createCell((short) 13);
        cell.setCellValue("手续费");
        cell.setCellStyle(style);
        
        cell = row.createCell((short) 14);
        cell.setCellValue("商家收款金额");
        cell.setCellStyle(style);


        sheet.setColumnWidth(0,20 * 128);
        sheet.setColumnWidth(1,20 * 512);
        sheet.setColumnWidth(2,20 * 512);
        sheet.setColumnWidth(3,20 * 512);
        sheet.setColumnWidth(4,20 * 256);
        sheet.setColumnWidth(5,20 * 512);
        sheet.setColumnWidth(6,20 * 512);
        sheet.setColumnWidth(7,20 * 312);
        sheet.setColumnWidth(8,20 * 256);
        sheet.setColumnWidth(9,20 * 256);
        sheet.setColumnWidth(10,20 * 256);
        sheet.setColumnWidth(11,20 * 256);
        sheet.setColumnWidth(12,20 * 256);
        sheet.setColumnWidth(13,20 * 256);
        sheet.setColumnWidth(14,20 * 256);
        //查询数据，写入数据
        List<Record> list = new PaymentDetailsRepository().riyueselectEXCEL(scree);
        if(list!=null){
        	for(int i=0;i<list.size();i++){
            	row = sheet.createRow( i+3 );//因为有标题，所以这里+2
            	row.createCell((short) 0).setCellValue((i+1));
            	row.createCell((short) 1).setCellValue(String.valueOf(list.get(i).get("complete_time").toString()));
            	row.createCell((short) 2).setCellValue(String.valueOf(list.get(i).get("payment_time").toString()));
            	row.createCell((short) 3).setCellValue(String.valueOf(list.get(i).get("order_no").toString()));
            	row.createCell((short) 4).setCellValue(String.valueOf(list.get(i).get("order_title").toString()));
            	row.createCell((short) 5).setCellValue(String.valueOf(list.get(i).get("name").toString()));
            	row.createCell((short) 6).setCellValue(String.valueOf(list.get(i).get("user_name").toString()));
            	row.createCell((short) 7).setCellValue(String.valueOf(list.get(i).get("order_amount").toString()));
            	row.createCell((short) 8).setCellValue(String.valueOf(list.get(i).get("payment_name").toString()));
            	row.createCell((short) 9).setCellValue(String.valueOf(list.get(i).get("user_percentage").toString()));
            	row.createCell((short) 10).setCellValue(String.valueOf(list.get(i).get("technical").toString()));
            	row.createCell((short) 11).setCellValue(String.valueOf(list.get(i).get("voucher").toString()));
            	row.createCell((short) 12).setCellValue(String.valueOf(list.get(i).get("real_amount").toString()));
            	row.createCell((short) 13).setCellValue(String.valueOf(list.get(i).get("commission").toString()));
            	row.createCell((short) 14).setCellValue(String.valueOf(list.get(i).get("real_shop_money").toString()));
            }
        }
        
        HSSFRow row1 = sheet.createRow(0);

        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell1 = row1.createCell((short) 0);
        cell1.setCellValue("详情资金流水查询(日月报表)");
        cell1.setCellStyle(style1);

        HSSFRow row2 = sheet.createRow(1);
        sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell2 = row2.createCell((short) 0);
        cell2.setCellValue("统计时间："+scree.getStartTime()+"-"+scree.getEndTime());
        cell2.setCellStyle(style2);
   //保存文件
        String path=url+"详情资金流水查询(日月报表)"+new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+".xls";
        ExcelReadUtil.excelWrite(path,wb);
        return path;
	}

    /**
     * 导出已支出总额汇总
     * @return
     */
    @SuppressWarnings("deprecation")
    public String actualDetailExcel(ScreenDo scree,String activeType,String allcount){

        //第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        //第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("已支出总额");
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
        cell.setCellValue("编号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 1);
        cell.setCellValue("交易时间");
        cell.setCellStyle(style);

        cell = row.createCell((short) 2);
        cell.setCellValue("订单号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 3);
        cell.setCellValue("订单类型");
        cell.setCellStyle(style);

        cell = row.createCell((short) 4);
        cell.setCellValue("商家名称");
        cell.setCellStyle(style);

        cell = row.createCell((short) 5);
        cell.setCellValue("会员账号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 6);
        cell.setCellValue("支付类型");
        cell.setCellStyle(style);

        cell = row.createCell((short) 7);
        cell.setCellValue("订单金额");
        cell.setCellStyle(style);

        cell = row.createCell((short) 8);
        cell.setCellValue("实付金额");
        cell.setCellStyle(style);

        cell = row.createCell((short) 9);
        cell.setCellValue("活动类型");
        cell.setCellStyle(style);

        cell = row.createCell((short) 10);
        cell.setCellValue("补贴优惠券");
        cell.setCellStyle(style);


        sheet.setColumnWidth(0,20 * 128);
        sheet.setColumnWidth(1,20 * 512);
        sheet.setColumnWidth(2,20 * 512);
        sheet.setColumnWidth(3,20 * 512);
        sheet.setColumnWidth(4,20 * 256);
        sheet.setColumnWidth(5,20 * 512);
        sheet.setColumnWidth(6,20 * 512);
        sheet.setColumnWidth(7,20 * 312);
        sheet.setColumnWidth(8,20 * 256);
        sheet.setColumnWidth(9,20 * 256);
        sheet.setColumnWidth(10,20 * 256);
        //查询数据，写入数据
        List<Record> list = new PaymentDetailsRepository().actualDetailExcel(scree,activeType,allcount);
        if(list!=null){
            for(int i=0;i<list.size();i++){
                row = sheet.createRow( i+3 );//因为有标题，所以这里+2
                row.createCell((short) 0).setCellValue((i+1));
                row.createCell((short) 1).setCellValue(String.valueOf(list.get(i).get("useTime").toString()));
                row.createCell((short) 2).setCellValue(String.valueOf(list.get(i).get("orderNo").toString()));
                row.createCell((short) 3).setCellValue(String.valueOf(list.get(i).get("orderType").toString()));
                row.createCell((short) 4).setCellValue(String.valueOf(list.get(i).get("shopName").toString()));
                row.createCell((short) 5).setCellValue(String.valueOf(list.get(i).get("userName").toString()));
                row.createCell((short) 6).setCellValue(String.valueOf(list.get(i).get("payType").toString()));
                row.createCell((short) 7).setCellValue(String.valueOf(list.get(i).get("orderAmount").toString()));
                row.createCell((short) 8).setCellValue(String.valueOf(list.get(i).get("realAmount").toString()));
                row.createCell((short) 9).setCellValue(String.valueOf(list.get(i).get("activeType").toString()));
                row.createCell((short) 10).setCellValue(String.valueOf(list.get(i).get("subsidyMoney").toString()));
            }
        }

        HSSFRow row1 = sheet.createRow(0);

        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell1 = row1.createCell((short) 0);
        cell1.setCellValue("已支出总额");
        cell1.setCellStyle(style1);

        HSSFRow row2 = sheet.createRow(1);
        sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell2 = row2.createCell((short) 0);
        cell2.setCellValue("统计时间："+scree.getStartTime()+"-"+scree.getEndTime());
        cell2.setCellStyle(style2);
        //保存文件
        String path=url+"已支出总额"+new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+".xls";
        ExcelReadUtil.excelWrite(path,wb);
        return path;
    }

    /**
     * 导出预支出总额汇总
     * @return
     */
    @SuppressWarnings("deprecation")
    public String advanceDetailExcel(ScreenDo scree,String activeType,String allcount){

        //第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        //第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("预支出总额");
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
        cell.setCellValue("编号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 1);
        cell.setCellValue("交易时间");
        cell.setCellStyle(style);

        cell = row.createCell((short) 2);
        cell.setCellValue("会员账号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 3);
        cell.setCellValue("活动类型");
        cell.setCellStyle(style);

        cell = row.createCell((short) 4);
        cell.setCellValue("预补贴金额");
        cell.setCellStyle(style);


        sheet.setColumnWidth(0,20 * 128);
        sheet.setColumnWidth(1,20 * 512);
        sheet.setColumnWidth(2,20 * 512);
        sheet.setColumnWidth(3,20 * 512);
        sheet.setColumnWidth(4,20 * 256);

        //查询数据，写入数据
        List<Record> list = new PaymentDetailsRepository().advanceDetailExcel(scree,activeType,allcount);
        if(list!=null){
            for(int i=0;i<list.size();i++){
                row = sheet.createRow( i+3 );//因为有标题，所以这里+2
                row.createCell((short) 0).setCellValue((i+1));
                row.createCell((short) 1).setCellValue(String.valueOf(list.get(i).get("useTime").toString()));
                row.createCell((short) 2).setCellValue(String.valueOf(list.get(i).get("orderNo").toString()));
                row.createCell((short) 3).setCellValue(String.valueOf(list.get(i).get("orderType").toString()));
                row.createCell((short) 4).setCellValue(String.valueOf(list.get(i).get("shopName").toString()));
                row.createCell((short) 5).setCellValue(String.valueOf(list.get(i).get("userName").toString()));
                row.createCell((short) 6).setCellValue(String.valueOf(list.get(i).get("payType").toString()));
                row.createCell((short) 7).setCellValue(String.valueOf(list.get(i).get("orderAmount").toString()));
                row.createCell((short) 8).setCellValue(String.valueOf(list.get(i).get("realAmount").toString()));
                row.createCell((short) 9).setCellValue(String.valueOf(list.get(i).get("activeType").toString()));
                row.createCell((short) 10).setCellValue(String.valueOf(list.get(i).get("subsidyMoney").toString()));
            }
        }

        HSSFRow row1 = sheet.createRow(0);

        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell1 = row1.createCell((short) 0);
        cell1.setCellValue("预支出总额");
        cell1.setCellStyle(style1);

        HSSFRow row2 = sheet.createRow(1);
        sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell2 = row2.createCell((short) 0);
        cell2.setCellValue("统计时间："+scree.getStartTime()+"-"+scree.getEndTime());
        cell2.setCellStyle(style2);
        //保存文件
        String path=url+"预支出总额"+new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+".xls";
        ExcelReadUtil.excelWrite(path,wb);
        return path;
    }
}



