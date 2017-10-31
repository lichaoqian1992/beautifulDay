package com.manji.data.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.manji.data.repository.BusinessRespository;
import com.manji.data.repository.OperateRespository;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import com.jfinal.plugin.activerecord.Record;
import com.manji.data.model.request.service.GoodsPageVo;
import com.manji.data.model.request.service.OrderPageVo;
import com.manji.data.repository.ShopInfoCenterRespository;
import com.manji.data.service.ShopInfoCenterService;

import net.sf.json.JSONArray;

/**
 * 导出excel工具类
 * @author Administrator
 *
 */
public class ExcelUtils {

	/**引入相关的dao*/

    private BusinessRespository Brespository=new BusinessRespository();

    private OperateRespository Orespository=new OperateRespository();

    private final String url = AllConfig.url;//测试上传地址

	/**
	 * 导出商品信息
	 * @return
	 */
	
	@SuppressWarnings("deprecation")
	public String goodsExcel(GoodsPageVo orderParam){
		
	//第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
    //第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("商品审核信息");
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
        cell.setCellValue("商品名称");
        cell.setCellStyle(style);

        cell = row.createCell((short) 2);
        cell.setCellValue("行业类别");
        cell.setCellStyle(style);

        cell = row.createCell((short) 3);
        cell.setCellValue("商家名称");
        cell.setCellStyle(style);
        
        cell = row.createCell((short) 4);
        cell.setCellValue("店铺名称");
        cell.setCellStyle(style);

        cell = row.createCell((short) 5);
        cell.setCellValue("状态");
        cell.setCellStyle(style);

        cell = row.createCell((short) 6);
        cell.setCellValue("修改记录");
        cell.setCellStyle(style);

        cell = row.createCell((short) 7);
        cell.setCellValue("申请时间");
        cell.setCellStyle(style);
        
        cell = row.createCell((short) 8);
        cell.setCellValue("更新时间");
        cell.setCellStyle(style);

        cell = row.createCell((short) 9);
        cell.setCellValue("录入人员");
        cell.setCellStyle(style);
        
        cell = row.createCell((short) 10);
        cell.setCellValue("审核人员");
        cell.setCellStyle(style);
        

        sheet.setColumnWidth(0,20 * 128);
        sheet.setColumnWidth(1,20 * 512);
        sheet.setColumnWidth(2,20 * 256);
        sheet.setColumnWidth(3,20 * 256);
        sheet.setColumnWidth(4,20 * 256);
        sheet.setColumnWidth(5,20 * 256);
        sheet.setColumnWidth(6,20 * 1024);
        sheet.setColumnWidth(7,20 * 256);
        sheet.setColumnWidth(8,20 * 256);
        sheet.setColumnWidth(9,20 * 256);
        sheet.setColumnWidth(10,20 * 256);
        //查询数据，写入数据
        List<Record> list = new ShopInfoCenterRespository().outToExcel(orderParam);
        //System.out.println(list);
        if(list!=null){
        	
        	String state = "";
        	for(int i=0;i<list.size();i++){
        		
        		String remarkStr = list.get(i).get("remark").toString();
        		//remarkStr = "".equals(remarkStr) ? "-" : remarkStr;
        		if("".equals(remarkStr.trim())){
        			remarkStr = "-";
        		}
        		String auditStr = "-";
        		if("[".equals(remarkStr.substring(0,1))){
        			JSONArray  jsonArr = JSONArray.fromObject(remarkStr);
        			auditStr = jsonArr.getJSONObject(jsonArr.size()-1).get("Audit").toString();
        			if("[".equals(auditStr.substring(0,1))){
        				auditStr = auditStr.substring(5);
        			}
        		}
        		if (list.get(i).get("status") != null) {
        			
	        		switch(Integer.parseInt(list.get(i).get("status").toString())){
	        			
		        		case 0:
							state = "正常";
							break;
						case 1:
							state = "未审核";
							break;
						case 2:
							state = "锁定";
							break;
						case 3:
							state = "拒绝";
							break;
						case 4:
							state = "草稿";
							break;
						default:
							state = "";
	        		}
        		}
        		
        		String class_list = list.get(i).get("class_list");
        		String title = "";
        		if(class_list != null && !"".equals(class_list)){
        			
        			class_list = class_list.substring(1, class_list.length()-1);
        			String [] classArr = class_list.split(",");
        			for(String yy:classArr){
        				
        				String catTitle = new ShopInfoCenterService().getCategory(yy);
        				title += catTitle + ">";
        			}
        			title = title.substring(0, title.length()-1);
        		}
        		
        		list.get(i).set("title", title);
        		
        		
        		row = sheet.createRow( i+3 );//因为有标题，所以这里+2
            	row.createCell((short) 0).setCellValue((i+1));
            	row.createCell((short) 1).setCellValue(list.get(i).get("article_title").toString());
            	row.createCell((short) 2).setCellValue(list.get(i).get("title").toString());
            	row.createCell((short) 3).setCellValue(list.get(i).get("user_name").toString());
            	row.createCell((short) 4).setCellValue(list.get(i).get("name").toString());
            	row.createCell((short) 5).setCellValue(state);
            	row.createCell((short) 6).setCellValue(list.get(i).get("remark").toString());
            	row.createCell((short) 7).setCellValue(list.get(i).get("add_time").toString());
            	row.createCell((short) 8).setCellValue(list.get(i).get("update_time").toString());
            	row.createCell((short) 9).setCellValue(list.get(i).get("input_name").toString());
            	row.createCell((short) 10).setCellValue(auditStr);
            }
        }
        
        HSSFRow row1 = sheet.createRow(0);

        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell1 = row1.createCell((short) 0);
        cell1.setCellValue("商品信息");
        cell1.setCellStyle(style1);

        HSSFRow row2 = sheet.createRow(1);
        sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell2 = row2.createCell((short) 0);
    /*    cell2.setCellValue("商品名称："+orderParam.getArticle_title());
        cell2.setCellValue("商家名称："+orderParam.getUser_name());
        cell2.setCellValue("行业类别："+orderParam.getTitle());
        cell2.setCellValue("录入员："+orderParam.getInput_name());
        cell2.setCellValue("审核员："+orderParam.getAudit_name());*/
        cell2.setCellValue("统计时间："+orderParam.getStart_time()+"至"+orderParam.getEnd_time());
        cell2.setCellStyle(style2);
   //保存文件
        String path=url+"商品信息"+new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+".xls";
        ExcelReadUtil.excelWrite(path,wb);
        return path;
	}

    /**
     * 导出业务员日志信息
     * @return
     */
    @SuppressWarnings("deprecation")
    public String toBusinessLogExcel(String businessName,String startTime,String endTime){

        //第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        //第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("业务员日志信息");
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
        cell.setCellValue("业务员姓名");
        cell.setCellStyle(style);
        sheet.setColumnWidth(1,20 * 300);

        cell = row.createCell((short) 2);
        cell.setCellValue("连续签到次数");
        cell.setCellStyle(style);
        sheet.setColumnWidth(2,20 * 300);

        cell = row.createCell((short) 3);
        cell.setCellValue("签到地址");
        cell.setCellStyle(style);
        sheet.setColumnWidth(3,20 * 912);

        cell = row.createCell((short) 4);
        cell.setCellValue("签到时间");
        cell.setCellStyle(style);
        sheet.setColumnWidth(4,20 * 256);

		cell = row.createCell((short) 5);
        cell.setCellValue("时间段");
        cell.setCellStyle(style);
        sheet.setColumnWidth(5,20 * 256);

        //查询数据，写入数据
        List<Record> list = Brespository.toBusinessLogExcel(businessName,startTime,endTime);
        if(list!=null){
            for(int i=0;i<list.size();i++){

				String timeSlot="";
                if(Integer.parseInt(String.valueOf(list.get(i).get("timeSlot").toString()))<12){
                    timeSlot="上午";
                }else if(12<=Integer.parseInt(String.valueOf(list.get(i).get("timeSlot").toString()))){
                    timeSlot="下午";
                }


                row = sheet.createRow( i+3 );//因为有标题，所以这里+2
                row.createCell((short) 0).setCellValue((i+1));
                row.createCell((short) 1).setCellValue(String.valueOf(list.get(i).get("person_name").toString()));
                row.createCell((short) 2).setCellValue(String.valueOf(list.get(i).get("continue_times").toString()));
                row.createCell((short) 3).setCellValue(String.valueOf(list.get(i).get("address").toString()));
                row.createCell((short) 4).setCellValue(String.valueOf(list.get(i).get("add_time").toString()));
				row.createCell((short) 5).setCellValue(timeSlot);
            }
        }

        HSSFRow row1 = sheet.createRow(0);

        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell1 = row1.createCell((short) 0);
        cell1.setCellValue("业务员日志信息");
        cell1.setCellStyle(style1);

        HSSFRow row2 = sheet.createRow(1);
        sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell2 = row2.createCell((short) 0);
        if(!startTime.equals(endTime)){
            cell2.setCellValue("统计时间:"+startTime+"-"+endTime);
        }else{
            cell2.setCellValue("统计时间:"+startTime);
        }
        cell2.setCellStyle(style2);
        //保存文件
        String path=url+"业务员日志信息"+new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+".xls";
        ExcelReadUtil.excelWrite(path,wb);
        return path;
    }

    /**
     * 导出统计所有的三级分类商品有数量
     * @return
     */
    @SuppressWarnings("deprecation")
    public String toArticleCategoryExcel(int levelOne,int levelTow,int levelThere){

        //第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        //第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("商品查询");
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
        cell.setCellValue("一级分类");
        cell.setCellStyle(style);
        sheet.setColumnWidth(1,20 * 512);

        cell = row.createCell((short) 2);
        cell.setCellValue("二级分类");
        cell.setCellStyle(style);
        sheet.setColumnWidth(2,20 * 512);

        cell = row.createCell((short) 3);
        cell.setCellValue("三级分类");
        cell.setCellStyle(style);
        sheet.setColumnWidth(3,20 * 512);

        cell = row.createCell((short) 4);
        cell.setCellValue("商品数量");
        cell.setCellStyle(style);
        sheet.setColumnWidth(4,20 * 256);

        //查询数据，写入数据
        List<Record> list = Brespository.toArticleCategoryExcel(levelOne,levelTow,levelThere);
        if(list!=null){
            for(int i=0;i<list.size();i++){
                row = sheet.createRow( i+3 );//因为有标题，所以这里+2
                row.createCell((short) 0).setCellValue((i+1));
                row.createCell((short) 1).setCellValue(String.valueOf(list.get(i).get("oneTitle").toString()));
                row.createCell((short) 2).setCellValue(String.valueOf(list.get(i).get("TowTitle").toString()));
                row.createCell((short) 3).setCellValue(String.valueOf(list.get(i).get("thereTitle").toString()));
                row.createCell((short) 4).setCellValue(String.valueOf(list.get(i).get("count").toString()));
            }
        }

        HSSFRow row1 = sheet.createRow(0);

        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell1 = row1.createCell((short) 0);
        cell1.setCellValue("业务员日志信息");
        cell1.setCellStyle(style1);

        HSSFRow row2 = sheet.createRow(1);
        sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell2 = row2.createCell((short) 0);
        cell2.setCellStyle(style2);
        //保存文件
        String path=url+"业务员日志信息"+new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+".xls";
        ExcelReadUtil.excelWrite(path,wb);
        return path;
    }
	
	
	/**
	 * 导出订单信息
	 * @return
	 */
	
	@SuppressWarnings("deprecation")
	public String orderExcel(OrderPageVo orderParam){
		
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
        cell.setCellValue("下单人");
        cell.setCellStyle(style);

        cell = row.createCell((short) 2);
        cell.setCellValue("商家名称");
        cell.setCellStyle(style);

        cell = row.createCell((short) 3);
        cell.setCellValue("订单号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 4);
        cell.setCellValue("订单金额");
        cell.setCellStyle(style);

        cell = row.createCell((short) 5);
        cell.setCellValue("下单时间");
        cell.setCellStyle(style);

        cell = row.createCell((short) 6);
        cell.setCellValue("商家电话");
        cell.setCellStyle(style);

        cell = row.createCell((short) 7);
        cell.setCellValue("发货状态");
        cell.setCellStyle(style);
        
        cell = row.createCell((short) 8);
        cell.setCellValue("订单状态");
        cell.setCellStyle(style);
        
        cell = row.createCell((short) 9);
        cell.setCellValue("退订状态");
        cell.setCellStyle(style);
        
        cell = row.createCell((short) 10);
        cell.setCellValue("退款状态");
        cell.setCellStyle(style);
        
        cell = row.createCell((short) 11);
        cell.setCellValue("店铺名");
        cell.setCellStyle(style);
        
        
        

        sheet.setColumnWidth(0,20 * 128);
        sheet.setColumnWidth(1,20 * 216);
        sheet.setColumnWidth(2,20 * 256);
        sheet.setColumnWidth(3,20 * 512);
        sheet.setColumnWidth(4,20 * 256);
        sheet.setColumnWidth(5,20 * 512);
        sheet.setColumnWidth(6,20 * 256);
        sheet.setColumnWidth(7,20 * 256);
        sheet.setColumnWidth(8,20 * 256);
        sheet.setColumnWidth(9,20 * 256);
        sheet.setColumnWidth(10,20 * 256);
        sheet.setColumnWidth(11,20 * 256);
        //查询数据，写入数据
        List<Record> list = new ShopInfoCenterRespository().getOrderInfo(orderParam);
        
        if(list!=null){
        	
        	String express_status = "";
        	String status = "";
        	String book_back_status = "";
        	String back_status = "";
        	for(int i=0;i<list.size();i++){
        		
        		if (list.get(i).get("express_status") != null) {
        			
	        		switch(Integer.parseInt(list.get(i).get("express_status").toString())){
	        			
		        		case 1:
							express_status = "未发货";
							break;
						case 2:
							express_status = "已发货";
							break;
						case 3:
							express_status = "自提";
							break;
						case 4:
							express_status = "待设置运费";
							break;
						default:
							express_status = "";
	        		}
        		}
        		if (list.get(i).get("status") != null) {
        			
        			switch(Integer.parseInt(list.get(i).get("status").toString())){
        			
	        			case 1:
							status = "生成订单";
							break;
						case 2:
							status = "确认订单";
							break;
						case 3:
							status = "完成订单";
							break;
						case 4:
							status = "取消订单";
							break;
						case 5:
							status = "锁定订单";
							break;
						case 6:
							status = "卖家取消订单";
							break;
						case 7:
							status = "投诉冻结订单";
							break;
						default:
							status = "";
        			}
        		}
        		if (list.get(i).get("book_back_status") != null) {
        			
        			switch(Integer.parseInt(list.get(i).get("book_back_status").toString())){
        			
	        			case 1:
							book_back_status = "未申请";
							break;
						case 2:
							book_back_status = "待确认";
							break;
						case 3:
							book_back_status = "卖家同意";
							break;
						case 4:
							book_back_status = "卖家不同意";
							break;
						case 5:
							book_back_status = "全部退单";
							break;
						case 6:
							book_back_status = "部分退单";
							break;
						default:
							book_back_status = "";
        			}
        		}
        		if (list.get(i).get("back_status") != null) {
        			
        			switch(Integer.parseInt(list.get(i).get("back_status").toString())){
        			
	        			case 1:
							back_status = "等待确认";
							break;
						case 2:
							back_status = "退款中";
							break;
						case 3:
							back_status = "退款完成";
							break;
						case 4:
							back_status = "退款失败";
							break;
						case 5:
							back_status = "不同意退款";
							break;
						case 6:
							back_status = "买家取消退款";
							break;
						case 7:
							back_status = "卖家同意退款";
							break;
						default:
							back_status = "";
        			}
        		}
        		row = sheet.createRow( i+3 );//因为有标题，所以这里+2
            	row.createCell((short) 0).setCellValue((i+1));
            	row.createCell((short) 1).setCellValue(list.get(i).get("buyer_name").toString());
            	row.createCell((short) 2).setCellValue(list.get(i).get("shop_name").toString());
            	row.createCell((short) 3).setCellValue(list.get(i).get("order_no").toString());
            	row.createCell((short) 4).setCellValue(list.get(i).get("order_amount").toString());
            	row.createCell((short) 5).setCellValue(list.get(i).get("add_time").toString());
            	row.createCell((short) 6).setCellValue(list.get(i).get("mobile").toString());
            	row.createCell((short) 7).setCellValue(express_status);
            	row.createCell((short) 8).setCellValue(status);
            	row.createCell((short) 9).setCellValue(book_back_status);
            	row.createCell((short) 10).setCellValue(back_status);
            	row.createCell((short) 11).setCellValue(list.get(i).get("name").toString());
            	
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
    /*    cell2.setCellValue("商品名称："+orderParam.getArticle_title());
        cell2.setCellValue("商家名称："+orderParam.getUser_name());
        cell2.setCellValue("行业类别："+orderParam.getTitle());
        cell2.setCellValue("录入员："+orderParam.getInput_name());
        cell2.setCellValue("审核员："+orderParam.getAudit_name());*/
        cell2.setCellValue("统计时间："+orderParam.getStart_time()+"至"+orderParam.getEnd_time());
        cell2.setCellStyle(style2);
   //保存文件
        String path=url+"订单信息"+new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+".xls";
        ExcelReadUtil.excelWrite(path,wb);
        return path;
	}


    /**
     * 导出商家列表
     * @return
     */
    @SuppressWarnings("deprecation")
    public String toShopInfoDataExcel(String userName,String nickName,String startTime,String endTime,String mobile,String shopName,String screen){

        //第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        //第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("商家列表");
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
        cell.setCellValue("业务员账号");
        cell.setCellStyle(style);
        sheet.setColumnWidth(1,20 * 512);

        cell = row.createCell((short) 2);
        cell.setCellValue("业务员实名");
        cell.setCellStyle(style);
        sheet.setColumnWidth(2,20 * 512);

        cell = row.createCell((short) 3);
        cell.setCellValue("业务员电话");
        cell.setCellStyle(style);
        sheet.setColumnWidth(3,20 * 512);

        cell = row.createCell((short) 4);
        cell.setCellValue("商家名称");
        cell.setCellStyle(style);
        sheet.setColumnWidth(4,20 * 512);

        cell = row.createCell((short) 5);
        cell.setCellValue("实体店名");
        cell.setCellStyle(style);
        sheet.setColumnWidth(5,20 * 512);

        cell = row.createCell((short) 6);
        cell.setCellValue("联系电话");
        cell.setCellStyle(style);
        sheet.setColumnWidth(6,20 * 512);

        cell = row.createCell((short) 7);
        cell.setCellValue("商家类型");
        cell.setCellStyle(style);
        sheet.setColumnWidth(7,20 * 512);

        cell = row.createCell((short) 8);
        cell.setCellValue("商品数量(总量)");
        cell.setCellStyle(style);
        sheet.setColumnWidth(8,20 * 256);

        cell = row.createCell((short) 9);
        cell.setCellValue("商品数量(待审核)");
        cell.setCellStyle(style);
        sheet.setColumnWidth(9,20 * 256);


        cell = row.createCell((short) 10);
        cell.setCellValue("商品数量(未通过)");
        cell.setCellStyle(style);
        sheet.setColumnWidth(10,20 * 256);

        cell = row.createCell((short) 11);
        cell.setCellValue("申请时间");
        cell.setCellStyle(style);
        sheet.setColumnWidth(11,20 * 512);



        //查询数据，写入数据
        List<Record> list = Brespository.toShopInfoDataExcel(userName,nickName,startTime,endTime,mobile,shopName,screen);
        if(list!=null){
            for(int i=0;i<list.size();i++){
                row = sheet.createRow( i+3 );//因为有标题，所以这里+2
                row.createCell((short) 0).setCellValue((i+1));
                row.createCell((short) 1).setCellValue(String.valueOf(list.get(i).get("user_name").toString()));
                row.createCell((short) 2).setCellValue(String.valueOf(list.get(i).get("person_name").toString()));
                row.createCell((short) 3).setCellValue(String.valueOf(list.get(i).get("mobile").toString()));
                row.createCell((short) 4).setCellValue(String.valueOf(list.get(i).get("name").toString()));
                row.createCell((short) 5).setCellValue(String.valueOf(list.get(i).get("storeName").toString()));
                row.createCell((short) 6).setCellValue(String.valueOf(list.get(i).get("msg_mobile").toString()));
                row.createCell((short) 7).setCellValue(String.valueOf(list.get(i).get("title").toString()));
                row.createCell((short) 8).setCellValue(String.valueOf(list.get(i).get("allCount").toString()));
                row.createCell((short) 9).setCellValue(String.valueOf(list.get(i).get("pendingCount").toString()));
                row.createCell((short) 10).setCellValue(String.valueOf(list.get(i).get("notPassCount").toString()));
                row.createCell((short) 11).setCellValue(String.valueOf(list.get(i).get("add_time").toString()));

            }
        }

        HSSFRow row1 = sheet.createRow(0);

        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell1 = row1.createCell((short) 0);
        cell1.setCellValue("商家列表");
        cell1.setCellStyle(style1);

        HSSFRow row2 = sheet.createRow(1);
        sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell2 = row2.createCell((short) 0);
        if(!startTime.equals(endTime)){
            cell2.setCellValue("统计时间:"+startTime+"-"+endTime);
        }else{
            cell2.setCellValue("统计时间:"+startTime);
        }
        cell2.setCellStyle(style2);
        //保存文件
        String path=url+"商家列表"+new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+".xls";
        ExcelReadUtil.excelWrite(path,wb);
        return path;
    }
	
	/**
	 * 导出审核信息
	 * @return
	 */
	
	@SuppressWarnings("deprecation")
	public String toAuditExcel(String start_time,String end_time,String audit_name,Integer type){
		
	//第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
    //第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("审核信息");
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
        cell.setCellValue("审核人");
        cell.setCellStyle(style);

        cell = row.createCell((short) 2);
        cell.setCellValue("真实姓名");
        cell.setCellStyle(style);

        cell = row.createCell((short) 3);
        cell.setCellValue("审核条数");
        cell.setCellStyle(style);

        sheet.setColumnWidth(0,20 * 256);
        sheet.setColumnWidth(1,20 * 512);
        sheet.setColumnWidth(2,20 * 512);
        sheet.setColumnWidth(3,20 * 512);
        //查询数据，写入数据
        List<Record> list = new ShopInfoCenterRespository().getAuditInfo(start_time,end_time,audit_name,type);
        
        if(list!=null){
        	
        	for(int i=0;i<list.size();i++){
        		
        		row = sheet.createRow( i+3 );//因为有标题，所以这里+2
            	row.createCell((short) 0).setCellValue((i+1));
            	row.createCell((short) 1).setCellValue(list.get(i).get("examine_name").toString());
            	row.createCell((short) 2).setCellValue(list.get(i).get("person_name").toString());
            	row.createCell((short) 3).setCellValue(list.get(i).get("total").toString());
            	
            }
        }
        
        HSSFRow row1 = sheet.createRow(0);

        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell1 = row1.createCell((short) 0);
        cell1.setCellValue("审核信息");
        cell1.setCellStyle(style1);

        HSSFRow row2 = sheet.createRow(1);
        sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell2 = row2.createCell((short) 0);
        cell2.setCellValue("统计时间："+start_time+"至"+end_time);
        cell2.setCellStyle(style2);
   //保存文件
        String path=url+"审核信息"+new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+".xls";
        ExcelReadUtil.excelWrite(path,wb);
        return path;
	}

    /**
     * 导出商家销售信息
     * @return
     */

    @SuppressWarnings("deprecation")
    public String toShopInformationExcel(String shopName, String personName, String mobile, String startTime, String endTime){

        //第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        //第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("商家销售信息");
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
        cell.setCellValue("店铺名称");
        cell.setCellStyle(style);

        cell = row.createCell((short) 2);
        cell.setCellValue("店铺地址");
        cell.setCellStyle(style);

        cell = row.createCell((short) 3);
        cell.setCellValue("入驻时间");
        cell.setCellStyle(style);

        cell = row.createCell((short) 4);
        cell.setCellValue("店铺状态");
        cell.setCellStyle(style);

        cell = row.createCell((short) 5);
        cell.setCellValue("是否删除");
        cell.setCellStyle(style);

        cell = row.createCell((short) 6);
        cell.setCellValue("是否签约");
        cell.setCellStyle(style);

        cell = row.createCell((short) 7);
        cell.setCellValue("店铺开关");
        cell.setCellStyle(style);

        cell = row.createCell((short) 8);
        cell.setCellValue("主营业务");
        cell.setCellStyle(style);

        cell = row.createCell((short) 9);
        cell.setCellValue("联系人");
        cell.setCellStyle(style);

        cell = row.createCell((short) 10);
        cell.setCellValue("联系电话");
        cell.setCellStyle(style);

        cell = row.createCell((short) 11);
        cell.setCellValue("商品数量");
        cell.setCellStyle(style);

        cell = row.createCell((short) 12);
        cell.setCellValue("开始录入商品时间");
        cell.setCellStyle(style);

        cell = row.createCell((short) 13);
        cell.setCellValue("订单数量");
        cell.setCellStyle(style);

        cell = row.createCell((short) 14);
        cell.setCellValue("订单总金额");
        cell.setCellStyle(style);

        cell = row.createCell((short) 15);
        cell.setCellValue("最近一次下单时间");
        cell.setCellStyle(style);

        sheet.setColumnWidth(0,20 * 128);
        sheet.setColumnWidth(1,20 * 512);
        sheet.setColumnWidth(2,20 * 512);
        sheet.setColumnWidth(3,20 * 258);
        sheet.setColumnWidth(4,20 * 128);
        sheet.setColumnWidth(5,20 * 128);
        sheet.setColumnWidth(6,20 * 128);
        sheet.setColumnWidth(7,20 * 128);
        sheet.setColumnWidth(8,20 * 128);
        sheet.setColumnWidth(9,20 * 128);
        sheet.setColumnWidth(10,20 * 258);
        sheet.setColumnWidth(11,20 * 128);
        sheet.setColumnWidth(12,20 * 258);
        sheet.setColumnWidth(13,20 * 128);
        sheet.setColumnWidth(14,20 * 128);
        sheet.setColumnWidth(15,20 * 258);


        //查询数据，写入数据
        List<Record> list =Orespository.toShopInformationExcel(shopName,personName,mobile,startTime, endTime);

        if(list!=null){

            for(int i=0;i<list.size();i++) {
                row = sheet.createRow(i + 3);//因为有标题，所以这里+2
                row.createCell((short) 0).setCellValue((i + 1));
                row.createCell((short) 1).setCellValue(list.get(i).get("name").toString());
                row.createCell((short) 2).setCellValue(list.get(i).get("address").toString());
                row.createCell((short) 3).setCellValue(list.get(i).get("settledTime").toString());
                row.createCell((short) 4).setCellValue(list.get(i).get("stateName").toString());
                row.createCell((short) 5).setCellValue(list.get(i).get("delName").toString());
                row.createCell((short) 6).setCellValue(list.get(i).get("signUp").toString());
                row.createCell((short) 7).setCellValue(list.get(i).get("dpkg").toString());
                row.createCell((short) 8).setCellValue(list.get(i).get("title").toString());
                row.createCell((short) 9).setCellValue(list.get(i).get("personName").toString());
                row.createCell((short) 10).setCellValue(list.get(i).get("mobile").toString());
                row.createCell((short) 11).setCellValue(list.get(i).get("shopSum").toString());
                row.createCell((short) 12).setCellValue(list.get(i).get("inputShopTime").toString());
                row.createCell((short) 13).setCellValue(list.get(i).get("orderSum").toString());
                row.createCell((short) 14).setCellValue(list.get(i).get("orderMoneySum").toString());
                row.createCell((short) 15).setCellValue(list.get(i).get("lastOrderTime").toString());

            }
        }

        HSSFRow row1 = sheet.createRow(0);

        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell1 = row1.createCell((short) 0);
        cell1.setCellValue("商家销售信息");
        cell1.setCellStyle(style1);

        HSSFRow row2 = sheet.createRow(1);
        sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell2 = row2.createCell((short) 0);
        if(!startTime.equals(endTime)){
            cell2.setCellValue("统计时间:"+startTime+"-"+endTime);
        }else{
            cell2.setCellValue("统计时间:"+startTime);
        }
        cell2.setCellStyle(style2);
        //保存文件
        String path=url+"商家销售信息"+new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+".xls";
        ExcelReadUtil.excelWrite(path,wb);
        return path;
    }



    /**
     *  导出toExcel(商家销售信息数据（未销售）)
     * @return
     */

    @SuppressWarnings("deprecation")
    public String toshopNoSaleExcel(String shopName, String personName, String mobile, String time){

        //第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        //第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("商家销售统计(未销售)");
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
        cell.setCellValue("店铺名称");
        cell.setCellStyle(style);

        cell = row.createCell((short) 2);
        cell.setCellValue("店铺地址");
        cell.setCellStyle(style);

        cell = row.createCell((short) 3);
        cell.setCellValue("入驻时间");
        cell.setCellStyle(style);

        cell = row.createCell((short) 4);
        cell.setCellValue("店铺状态");
        cell.setCellStyle(style);

        cell = row.createCell((short) 5);
        cell.setCellValue("是否删除");
        cell.setCellStyle(style);

        cell = row.createCell((short) 6);
        cell.setCellValue("是否签约");
        cell.setCellStyle(style);

        cell = row.createCell((short) 7);
        cell.setCellValue("店铺开关");
        cell.setCellStyle(style);

        cell = row.createCell((short) 8);
        cell.setCellValue("主营业务");
        cell.setCellStyle(style);

        cell = row.createCell((short) 9);
        cell.setCellValue("联系人");
        cell.setCellStyle(style);

        cell = row.createCell((short) 10);
        cell.setCellValue("联系电话");
        cell.setCellStyle(style);

        cell = row.createCell((short) 11);
        cell.setCellValue("商品数量");
        cell.setCellStyle(style);

        cell = row.createCell((short) 12);
        cell.setCellValue("开始录入商品时间");
        cell.setCellStyle(style);

        sheet.setColumnWidth(0,20 * 128);
        sheet.setColumnWidth(1,20 * 512);
        sheet.setColumnWidth(2,20 * 512);
        sheet.setColumnWidth(3,20 * 258);
        sheet.setColumnWidth(4,20 * 128);
        sheet.setColumnWidth(5,20 * 128);
        sheet.setColumnWidth(6,20 * 128);
        sheet.setColumnWidth(7,20 * 128);
        sheet.setColumnWidth(8,20 * 128);
        sheet.setColumnWidth(9,20 * 128);
        sheet.setColumnWidth(10,20 * 258);
        sheet.setColumnWidth(11,20 * 128);
        sheet.setColumnWidth(12,20 * 258);


        //查询数据，写入数据
        List<Record> list =Orespository.toshopNoSaleDataExcel(shopName,personName,mobile,time);

        if(list!=null){

            for(int i=0;i<list.size();i++) {
                row = sheet.createRow(i + 3);//因为有标题，所以这里+2
                row.createCell((short) 0).setCellValue((i + 1));
                row.createCell((short) 1).setCellValue(list.get(i).get("name").toString());
                row.createCell((short) 2).setCellValue(list.get(i).get("address").toString());
                row.createCell((short) 3).setCellValue(list.get(i).get("settledTime").toString());
                row.createCell((short) 4).setCellValue(list.get(i).get("stateName").toString());
                row.createCell((short) 5).setCellValue(list.get(i).get("delName").toString());
                row.createCell((short) 6).setCellValue(list.get(i).get("signUp").toString());
                row.createCell((short) 7).setCellValue(list.get(i).get("dpkg").toString());
                row.createCell((short) 8).setCellValue(list.get(i).get("title").toString());
                row.createCell((short) 9).setCellValue(list.get(i).get("personName").toString());
                row.createCell((short) 10).setCellValue(list.get(i).get("mobile").toString());
                row.createCell((short) 11).setCellValue(list.get(i).get("shopSum").toString());
                row.createCell((short) 12).setCellValue(list.get(i).get("inputShopTime").toString());
            }
        }

        HSSFRow row1 = sheet.createRow(0);

        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell1 = row1.createCell((short) 0);
        cell1.setCellValue("商家销售统计(未销售)");
        cell1.setCellStyle(style1);

        HSSFRow row2 = sheet.createRow(1);
        sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell2 = row2.createCell((short) 0);
        cell2.setCellValue("统计时间:"+time+" 之前");
        cell2.setCellStyle(style2);
        //保存文件
        String path=url+"商家销售统计(未销售)"+new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+".xls";
        ExcelReadUtil.excelWrite(path,wb);
        return path;
    }


    /**
     *  导出toExcel(导出商家入驻信息查询)
     * @return
     */

    @SuppressWarnings("deprecation")
    public String toShopSettledDataExcel(String shopName, String personName, String mobile, String time){

        //第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        //第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("商家入驻");
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
        cell.setCellValue("店铺名称");
        cell.setCellStyle(style);

        cell = row.createCell((short) 2);
        cell.setCellValue("店铺地址");
        cell.setCellStyle(style);

        cell = row.createCell((short) 3);
        cell.setCellValue("入驻时间");
        cell.setCellStyle(style);

        cell = row.createCell((short) 4);
        cell.setCellValue("店铺状态");
        cell.setCellStyle(style);

        cell = row.createCell((short) 5);
        cell.setCellValue("是否删除");
        cell.setCellStyle(style);

        cell = row.createCell((short) 6);
        cell.setCellValue("是否签约");
        cell.setCellStyle(style);

        cell = row.createCell((short) 7);
        cell.setCellValue("店铺开关");
        cell.setCellStyle(style);

        cell = row.createCell((short) 8);
        cell.setCellValue("主营业务");
        cell.setCellStyle(style);

        cell = row.createCell((short) 9);
        cell.setCellValue("联系人");
        cell.setCellStyle(style);

        cell = row.createCell((short) 10);
        cell.setCellValue("联系电话");
        cell.setCellStyle(style);

        cell = row.createCell((short) 11);
        cell.setCellValue("商品数量");
        cell.setCellStyle(style);

        cell = row.createCell((short) 12);
        cell.setCellValue("开始录入商品时间");
        cell.setCellStyle(style);

        sheet.setColumnWidth(0,20 * 128);
        sheet.setColumnWidth(1,20 * 512);
        sheet.setColumnWidth(2,20 * 512);
        sheet.setColumnWidth(3,20 * 258);
        sheet.setColumnWidth(4,20 * 128);
        sheet.setColumnWidth(5,20 * 128);
        sheet.setColumnWidth(6,20 * 128);
        sheet.setColumnWidth(7,20 * 128);
        sheet.setColumnWidth(8,20 * 128);
        sheet.setColumnWidth(9,20 * 128);
        sheet.setColumnWidth(10,20 * 258);
        sheet.setColumnWidth(11,20 * 128);
        sheet.setColumnWidth(12,20 * 258);


        //查询数据，写入数据
        List<Record> list =Orespository.toShopSettledDataExcel(shopName,personName,mobile,time);

        if(list!=null){

            for(int i=0;i<list.size();i++) {
                row = sheet.createRow(i + 3);//因为有标题，所以这里+2
                row.createCell((short) 0).setCellValue((i + 1));
                row.createCell((short) 1).setCellValue(list.get(i).get("name").toString());
                row.createCell((short) 2).setCellValue(list.get(i).get("address").toString());
                row.createCell((short) 3).setCellValue(list.get(i).get("settledTime").toString());
                row.createCell((short) 4).setCellValue(list.get(i).get("stateName").toString());
                row.createCell((short) 5).setCellValue(list.get(i).get("delName").toString());
                row.createCell((short) 6).setCellValue(list.get(i).get("signUp").toString());
                row.createCell((short) 7).setCellValue(list.get(i).get("dpkg").toString());
                row.createCell((short) 8).setCellValue(list.get(i).get("title").toString());
                row.createCell((short) 9).setCellValue(list.get(i).get("personName").toString());
                row.createCell((short) 10).setCellValue(list.get(i).get("mobile").toString());
                row.createCell((short) 11).setCellValue(list.get(i).get("shopSum").toString());
                row.createCell((short) 12).setCellValue(list.get(i).get("inputShopTime").toString());
            }
        }

        HSSFRow row1 = sheet.createRow(0);

        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell1 = row1.createCell((short) 0);
        cell1.setCellValue("商家入驻");
        cell1.setCellStyle(style1);

        HSSFRow row2 = sheet.createRow(1);
        sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        HSSFCell cell2 = row2.createCell((short) 0);
        cell2.setCellValue("统计时间:"+time);
        cell2.setCellStyle(style2);
        //保存文件
        String path=url+"商家入驻"+new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+".xls";
        ExcelReadUtil.excelWrite(path,wb);
        return path;
    }
}
