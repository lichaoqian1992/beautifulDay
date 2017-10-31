package com.manji.financesystem.service.impl;

import com.manji.financesystem.enums.*;
import com.manji.financesystem.primaryDomain.entiity.UserWithdrawalsDO;
import com.manji.financesystem.primaryDomain.entiity.extra.DayOrMonthDetailDO;
import com.manji.financesystem.primaryDomain.entiity.extra.OrderAndShopInfoDO;
import com.manji.financesystem.primaryDomain.repository.DailySheetRepository;
import com.manji.financesystem.primaryDomain.repository.ShopInfoRepository;
import com.manji.financesystem.primaryDomain.repository.UserInsideRechargeRepository;
import com.manji.financesystem.primaryDomain.repository.UserWithdrawalsRepository;
import com.manji.financesystem.requestParam.InteriorRechargeRequestParam;
import com.manji.financesystem.requestParam.ShopInfoParam;
import com.manji.financesystem.requestParam.WithDrawalsParam;
import com.manji.financesystem.service.ExcelUtilService;
import com.manji.financesystem.utils.FileUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/25.
 */
@Service
public class ExcelUtilServiceImpl implements ExcelUtilService{

    @Autowired
    private UserInsideRechargeRepository userInsideRechargeRepository;
    @Autowired
    private UserWithdrawalsRepository userWithdrawalsRepository;
    @Autowired
    private ShopInfoRepository shopInfoRepository;
    @Autowired
    private DailySheetRepository dailySheetRepository;


    @Override
    public String excelInside(InteriorRechargeRequestParam param) {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("充值记录(内部)");

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("编号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 1);
        cell.setCellValue("充值订单号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 2);
        cell.setCellValue("充值单号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 3);
        cell.setCellValue("excel名称");
        cell.setCellStyle(style);

        cell = row.createCell((short) 4);
        cell.setCellValue("用户ID");
        cell.setCellStyle(style);

        cell = row.createCell((short) 5);
        cell.setCellValue("用户名称");
        cell.setCellStyle(style);

        cell = row.createCell((short) 6);
        cell.setCellValue("角色类型");
        cell.setCellStyle(style);

        cell = row.createCell((short) 7);
        cell.setCellValue("充值金额");
        cell.setCellStyle(style);

        cell = row.createCell((short) 8);
        cell.setCellValue("发布人");
        cell.setCellStyle(style);

        cell = row.createCell((short) 9);
        cell.setCellValue("审批人");
        cell.setCellStyle(style);

        cell = row.createCell((short) 10);
        cell.setCellValue("充值类型");
        cell.setCellStyle(style);

        cell = row.createCell((short) 11);
        cell.setCellValue("充值状态");
        cell.setCellStyle(style);

        cell = row.createCell((short) 12);
        cell.setCellValue("审批状态");
        cell.setCellStyle(style);

        cell = row.createCell((short) 13);
        cell.setCellValue("创建时间");
        cell.setCellStyle(style);

        cell = row.createCell((short) 14);
        cell.setCellValue("报批时间");
        cell.setCellStyle(style);

        cell = row.createCell((short) 15);
        cell.setCellValue("审批时间");
        cell.setCellStyle(style);

        cell = row.createCell((short) 16);
        cell.setCellValue("备注");
        cell.setCellStyle(style);

        sheet.setColumnWidth(1,20 * 512);
        sheet.setColumnWidth(2,20 * 256);
        sheet.setColumnWidth(3,20 * 256);
        sheet.setColumnWidth(4,20 * 256);
        sheet.setColumnWidth(5,20 * 256);
        sheet.setColumnWidth(6,20 * 256);
        sheet.setColumnWidth(7,20 * 256);
        sheet.setColumnWidth(8,20 * 512);
        sheet.setColumnWidth(9,20 * 256);
        sheet.setColumnWidth(10,20 * 256);
        sheet.setColumnWidth(11,20 * 256);
        sheet.setColumnWidth(12,20 * 256);
        sheet.setColumnWidth(13,20 * 256);
        sheet.setColumnWidth(14,20 * 256);
        sheet.setColumnWidth(15,20 * 256);
        sheet.setColumnWidth(16,20 * 256);
        sheet.setColumnWidth(17,20 * 256);
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
        List<Map<String, Object>> maps = userInsideRechargeRepository.getUserInsideRechargeByParam(param);


        for (int i = 0; i < maps.size(); i++)
        {
            row = sheet.createRow((int) i + 1);
            // 第四步，创建单元格，并设置值
            row.createCell((short) 0).setCellValue(i);
            row.createCell((short) 1).setCellValue(String.valueOf(maps.get(i).get("recharge_order_no")));
            row.createCell((short) 2).setCellValue(String.valueOf(maps.get(i).get("excel_no")));
            row.createCell((short) 3).setCellValue(String.valueOf(maps.get(i).get("excel_name")));
            row.createCell((short) 4).setCellValue(String.valueOf(maps.get(i).get("user_id")));
            row.createCell((short) 5).setCellValue(String.valueOf(maps.get(i).get("user_key")));
            row.createCell((short) 6).setCellValue(String.valueOf(UserRoleTypeEnums.getMsgByCode(String.valueOf(maps.get(i).get("role_type")))));
            row.createCell((short) 7).setCellValue(String.valueOf(maps.get(i).get("recharge_money")));
            row.createCell((short) 8).setCellValue(String.valueOf(maps.get(i).get("person_release")));
            row.createCell((short) 9).setCellValue(String.valueOf(maps.get(i).get("person_approving")));
            row.createCell((short) 10).setCellValue(String.valueOf(RechargeTypeEnums.getMessageByCode(String.valueOf(maps.get(i).get("recharge_type")))));
            row.createCell((short) 11).setCellValue(String.valueOf(AllStatusEnum.getMessageByCode(String.valueOf(maps.get(i).get("status")))));
            row.createCell((short) 12).setCellValue(String.valueOf(CheckStatusEnums.getMessageByCode(String.valueOf(maps.get(i).get("check_status")))));
            row.createCell((short) 13).setCellValue(String.valueOf(maps.get(i).get("create_time")));
            row.createCell((short) 14).setCellValue(String.valueOf(maps.get(i).get("approval_time")));
            row.createCell((short) 15).setCellValue(String.valueOf(maps.get(i).get("check_time")));
            row.createCell((short) 16).setCellValue(String.valueOf(maps.get(i).get("remark")));

        }

        String path="E:/用户充值记录(内部)"+new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+".xls";
        System.out.printf(path);
        FileUtils.excelWrite(path,wb);
        return path;
    }

    @Override
    public String excelWithDraw(WithDrawalsParam param) {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("订单详情");

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

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
        cell.setCellValue("订单标题");
        cell.setCellStyle(style);

        cell = row.createCell((short) 4);
        cell.setCellValue("用户ID");
        cell.setCellStyle(style);

        cell = row.createCell((short) 5);
        cell.setCellValue("用户类型");
        cell.setCellStyle(style);

        cell = row.createCell((short) 6);
        cell.setCellValue("买家ID");
        cell.setCellStyle(style);

        cell = row.createCell((short) 7);
        cell.setCellValue("买家类型");
        cell.setCellStyle(style);

        cell = row.createCell((short) 8);
        cell.setCellValue("物流方式");
        cell.setCellStyle(style);

        cell = row.createCell((short) 9);
        cell.setCellValue("物流费用");
        cell.setCellStyle(style);

        cell = row.createCell((short) 10);
        cell.setCellValue("订单留言");
        cell.setCellStyle(style);

        cell = row.createCell((short) 11);
        cell.setCellValue("订单备注");
        cell.setCellStyle(style);

        cell = row.createCell((short) 12);
        cell.setCellValue("是否需要发票");
        cell.setCellStyle(style);

        cell = row.createCell((short) 13);
        cell.setCellValue("发票抬头");
        cell.setCellStyle(style);

        cell = row.createCell((short) 14);
        cell.setCellValue("订单金额");
        cell.setCellStyle(style);

        cell = row.createCell((short) 15);
        cell.setCellValue("订单代金券");
        cell.setCellStyle(style);

        cell = row.createCell((short) 16);
        cell.setCellValue("应付总金额");
        cell.setCellStyle(style);

        cell = row.createCell((short) 17);
        cell.setCellValue("实付总金额");
        cell.setCellStyle(style);

        cell = row.createCell((short) 18);
        cell.setCellValue("订单状态");
        cell.setCellStyle(style);

        cell = row.createCell((short) 19);
        cell.setCellValue("下单时间");
        cell.setCellStyle(style);

        cell = row.createCell((short) 20);
        cell.setCellValue("提交时间");
        cell.setCellStyle(style);

        cell = row.createCell((short) 21);
        cell.setCellValue("失效时间");
        cell.setCellStyle(style);

        cell = row.createCell((short) 22);
        cell.setCellValue("完成时间");
        cell.setCellStyle(style);

        cell = row.createCell((short) 23);
        cell.setCellValue("支付方式");
        cell.setCellStyle(style);

        cell = row.createCell((short) 24);
        cell.setCellValue("支付手续费");
        cell.setCellStyle(style);

        cell = row.createCell((short) 25);
        cell.setCellValue("支付状态");
        cell.setCellStyle(style);

        cell = row.createCell((short) 26);
        cell.setCellValue("支付时间");
        cell.setCellStyle(style);

        cell = row.createCell((short) 27);
        cell.setCellValue("结算状态");
        cell.setCellStyle(style);

        cell = row.createCell((short) 28);
        cell.setCellValue("结算时间");
        cell.setCellStyle(style);

        cell = row.createCell((short) 29);
        cell.setCellValue("是否删除");
        cell.setCellStyle(style);

        cell = row.createCell((short) 30);
        cell.setCellValue("拒单原因");
        cell.setCellStyle(style);

        sheet.setColumnWidth(1,20 * 512);
        sheet.setColumnWidth(2,20 * 256);
        sheet.setColumnWidth(3,20 * 256);
        sheet.setColumnWidth(4,20 * 256);
        sheet.setColumnWidth(5,20 * 256);
        sheet.setColumnWidth(6,20 * 256);
        sheet.setColumnWidth(7,20 * 256);
        sheet.setColumnWidth(8,20 * 512);
        sheet.setColumnWidth(9,20 * 256);
        sheet.setColumnWidth(10,20 * 256);
        sheet.setColumnWidth(11,20 * 256);
        sheet.setColumnWidth(12,20 * 256);
        sheet.setColumnWidth(13,20 * 256);
        sheet.setColumnWidth(14,20 * 256);
        sheet.setColumnWidth(15,20 * 256);
        sheet.setColumnWidth(16,20 * 256);
        sheet.setColumnWidth(17,20 * 256);
        sheet.setColumnWidth(18,20 * 256);
        sheet.setColumnWidth(19,20 * 256);
        sheet.setColumnWidth(20,20 * 256);
        sheet.setColumnWidth(21,20 * 512);
        sheet.setColumnWidth(22,20 * 256);
        sheet.setColumnWidth(23,20 * 256);
        sheet.setColumnWidth(24,20 * 256);
        sheet.setColumnWidth(25,20 * 256);
        sheet.setColumnWidth(26,20 * 256);
        sheet.setColumnWidth(27,20 * 256);
        sheet.setColumnWidth(28,20 * 256);
        sheet.setColumnWidth(29,20 * 256);
        sheet.setColumnWidth(30,20 * 256);
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
        List<Map<String, Object>> maps = userWithdrawalsRepository.getOrderDetails2(param);


        for (int i = 0; i < maps.size(); i++)
        {
            row = sheet.createRow((int) i + 1);
            // 第四步，创建单元格，并设置值
            row.createCell((short) 0).setCellValue(i);
            row.createCell((short) 1).setCellValue(String.valueOf(maps.get(i).get("order_no")));
            row.createCell((short) 2).setCellValue(String.valueOf(maps.get(i).get("order_type")));
            row.createCell((short) 3).setCellValue(String.valueOf(maps.get(i).get("order_title")));
            row.createCell((short) 4).setCellValue(String.valueOf(maps.get(i).get("shop_user_id")));
            row.createCell((short) 5).setCellValue(String.valueOf(UserRoleTypeEnums.getMsgByCode(String.valueOf(maps.get(i).get("shop_user_role_type")))));
            row.createCell((short) 6).setCellValue(String.valueOf(maps.get(i).get("user_id")));
            row.createCell((short) 7).setCellValue(String.valueOf(UserRoleTypeEnums.getMsgByCode(String.valueOf(maps.get(i).get("user_role_type")))));
            row.createCell((short) 8).setCellValue(String.valueOf(maps.get(i).get("express_type")));
            row.createCell((short) 9).setCellValue(String.valueOf(maps.get(i).get("express_fee")));
            row.createCell((short) 10).setCellValue(String.valueOf(maps.get(i).get("message")));
            row.createCell((short) 11).setCellValue(String.valueOf(maps.get(i).get("remark")));
            row.createCell((short) 12).setCellValue(String.valueOf(maps.get(i).get("is_invoice")));
            row.createCell((short) 13).setCellValue(String.valueOf(maps.get(i).get("invoice_title")));
            row.createCell((short) 14).setCellValue(String.valueOf(maps.get(i).get("order_amount")));
            row.createCell((short) 15).setCellValue(String.valueOf(maps.get(i).get("voucher")));
            row.createCell((short) 16).setCellValue(String.valueOf(maps.get(i).get("payable_amount")));
            row.createCell((short) 17).setCellValue(String.valueOf(maps.get(i).get("real_amount")));
            row.createCell((short) 18).setCellValue(String.valueOf(maps.get(i).get("status")));
            row.createCell((short) 19).setCellValue(String.valueOf(maps.get(i).get("add_time")));
            row.createCell((short) 20).setCellValue(String.valueOf(maps.get(i).get("confirm_time")));
            row.createCell((short) 21).setCellValue(String.valueOf(maps.get(i).get("invalid_time")));
            row.createCell((short) 22).setCellValue(String.valueOf(maps.get(i).get("complete_time")));
            row.createCell((short) 23).setCellValue(String.valueOf(maps.get(i).get("payment_id")));
            row.createCell((short) 24).setCellValue(String.valueOf(maps.get(i).get("payment_fee")));
            row.createCell((short) 25).setCellValue(String.valueOf(maps.get(i).get("payment_status")));
            row.createCell((short) 26).setCellValue(String.valueOf(maps.get(i).get("payment_time")));
            row.createCell((short) 27).setCellValue(String.valueOf(maps.get(i).get("settlement_status")));
            row.createCell((short) 28).setCellValue(String.valueOf(maps.get(i).get("settlement_time")));
            row.createCell((short) 29).setCellValue(String.valueOf(maps.get(i).get("is_del")));
            row.createCell((short) 30).setCellValue(String.valueOf(maps.get(i).get("reject_remark")));

        }

        String path="E:/提现订单详情"+new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+".xls";
        System.out.printf(path);
        FileUtils.excelWrite(path,wb);
        return path;
    }

    @Override
    public String excelOrders(ShopInfoParam param) {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("商家订单详情");

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("编号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 1);
        cell.setCellValue("商家ID");
        cell.setCellStyle(style);

        cell = row.createCell((short) 2);
        cell.setCellValue("商家名称");
        cell.setCellStyle(style);

        cell = row.createCell((short) 3);
        cell.setCellValue("所在区域");
        cell.setCellStyle(style);

        cell = row.createCell((short) 4);
        cell.setCellValue("联系电话");
        cell.setCellStyle(style);

        cell = row.createCell((short) 5);
        cell.setCellValue("提点比例");
        cell.setCellStyle(style);

        cell = row.createCell((short) 6);
        cell.setCellValue("订单编号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 7);
        cell.setCellValue("订单类型");
        cell.setCellStyle(style);

        cell = row.createCell((short) 8);
        cell.setCellValue("订单标题");
        cell.setCellStyle(style);

        cell = row.createCell((short) 9);
        cell.setCellValue("订单金额");
        cell.setCellStyle(style);

        cell = row.createCell((short) 10);
        cell.setCellValue("应付总金额");
        cell.setCellStyle(style);

        cell = row.createCell((short) 11);
        cell.setCellValue("实付总金额");
        cell.setCellStyle(style);

        cell = row.createCell((short) 12);
        cell.setCellValue("订单状态");
        cell.setCellStyle(style);

        cell = row.createCell((short) 13);
        cell.setCellValue("支付状态");
        cell.setCellStyle(style);

        cell = row.createCell((short) 14);
        cell.setCellValue("结算状态");
        cell.setCellStyle(style);

        cell = row.createCell((short) 15);
        cell.setCellValue("结算时间");
        cell.setCellStyle(style);

        sheet.setColumnWidth(1,20 * 512);
        sheet.setColumnWidth(2,20 * 256);
        sheet.setColumnWidth(3,20 * 256);
        sheet.setColumnWidth(4,20 * 256);
        sheet.setColumnWidth(5,20 * 256);
        sheet.setColumnWidth(6,20 * 256);
        sheet.setColumnWidth(7,20 * 256);
        sheet.setColumnWidth(8,20 * 512);
        sheet.setColumnWidth(9,20 * 256);
        sheet.setColumnWidth(10,20 * 256);
        sheet.setColumnWidth(11,20 * 256);
        sheet.setColumnWidth(12,20 * 256);
        sheet.setColumnWidth(13,20 * 256);
        sheet.setColumnWidth(14,20 * 256);
        sheet.setColumnWidth(15,20 * 256);
        sheet.setColumnWidth(16,20 * 256);

        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
        List<Map<String, Object>> maps = shopInfoRepository.getOrderDetails3(param);
        for(int i=0;i<maps.size();i++){
            row = sheet.createRow((int) i + 1);
            // 第四步，创建单元格，并设置值
            OrderAndShopInfoDO odo = new OrderAndShopInfoDO();

            row.createCell((short) 0).setCellValue(i+1);
            row.createCell((short) 1).setCellValue(String.valueOf(maps.get(i).get("shop_user_id")));
            row.createCell((short) 2).setCellValue(String.valueOf(maps.get(i).get("name")));
            row.createCell((short) 3).setCellValue(String.valueOf(maps.get(i).get("mobile")));
            row.createCell((short) 4).setCellValue(String.valueOf(maps.get(i).get("area")));
            row.createCell((short) 5).setCellValue(String.valueOf(maps.get(i).get("percentage")));
            row.createCell((short) 6).setCellValue(String.valueOf(maps.get(i).get("order_no")));
            row.createCell((short) 7).setCellValue(String.valueOf(maps.get(i).get("order_type")));
            row.createCell((short) 8).setCellValue(String.valueOf(maps.get(i).get("order_title")));
            row.createCell((short) 9).setCellValue(String.valueOf(maps.get(i).get("order_amount")));
            row.createCell((short) 10).setCellValue(String.valueOf(maps.get(i).get("payable_amount")));
            row.createCell((short) 11).setCellValue(String.valueOf(maps.get(i).get("real_amount")));
            row.createCell((short) 12).setCellValue(String.valueOf(OrderStatusEnums.getMsgByCode(String.valueOf(maps.get(i).get("status")))));
            row.createCell((short) 13).setCellValue(String.valueOf(PayStatusEnums.getMsgByCode(String.valueOf(maps.get(i).get("payment_status")))));
            row.createCell((short) 14).setCellValue(String.valueOf(SettleMentStatusEnums.getMsgByCode(String.valueOf(maps.get(i).get("settlement_status")))));
            row.createCell((short) 15).setCellValue(String.valueOf(maps.get(i).get("settlement_time")));
        }
        String path="E:/商家订单详情"+new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+".xls";
        System.out.printf(path);
        FileUtils.excelWrite(path,wb);
        return path;
    }

    @Override
    public String excelPendingprocessing() {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("待处理提现明细");

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式


        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("用户类型");
        cell.setCellStyle(style);
        sheet.setColumnWidth(0,20 * 256);

        cell = row.createCell((short) 1);
        cell.setCellValue("提现订单号");
        cell.setCellStyle(style);
        sheet.setColumnWidth(1,20 * 512);

        cell = row.createCell((short) 2);
        cell.setCellValue("银行卡名称");
        cell.setCellStyle(style);
        sheet.setColumnWidth(2,20 * 256);

        cell = row.createCell((short) 3);
        cell.setCellValue("银行卡卡号");
        cell.setCellStyle(style);
        sheet.setColumnWidth(3,20 * 512);

        cell = row.createCell((short) 4);
        cell.setCellValue("银行卡地区");
        cell.setCellStyle(style);
        sheet.setColumnWidth(4,20 * 512);

        cell = row.createCell((short) 5);
        cell.setCellValue("详细开户地址");
        cell.setCellStyle(style);
        sheet.setColumnWidth(5,20 * 512);

        cell = row.createCell((short) 6);
        cell.setCellValue("提现金额");
        cell.setCellStyle(style);

        cell = row.createCell((short) 7);
        cell.setCellValue("提现手续费");
        cell.setCellStyle(style);

        cell = row.createCell((short) 8);
        cell.setCellValue("提现满意券");
        cell.setCellStyle(style);

        cell = row.createCell((short) 9);
        cell.setCellValue("提现总金额");
        cell.setCellStyle(style);

        cell = row.createCell((short) 10);
        cell.setCellValue("开户姓名");
        cell.setCellStyle(style);

        cell = row.createCell((short) 11);
        cell.setCellValue("生成时间");
        cell.setCellStyle(style);
        sheet.setColumnWidth(11,20 * 512);




        List<UserWithdrawalsDO> list=userWithdrawalsRepository.findWithdrawalsinfo();
        for (int i=0;i<list.size();i++){
            row = sheet.createRow( i+1 );
            row.createCell((short) 0).setCellValue(String.valueOf(list.get(i).getUserRoleTyoe()));
            row.createCell((short) 1).setCellValue(String.valueOf(list.get(i).getWithdrawalsNo()));
            row.createCell((short) 2).setCellValue(String.valueOf(list.get(i).getBankName()));
            row.createCell((short) 3).setCellValue(String.valueOf(list.get(i).getBankCard()));
            row.createCell((short) 4).setCellValue(String.valueOf(list.get(i).getBankArea()));
            row.createCell((short) 5).setCellValue(String.valueOf(list.get(i).getBankAddress()));
            row.createCell((short) 6).setCellValue(String.valueOf(list.get(i).getAmount()));
            row.createCell((short) 7).setCellValue(String.valueOf(list.get(i).getCommission()));
            row.createCell((short) 8).setCellValue(String.valueOf(list.get(i).getVoucher()));
            row.createCell((short) 9).setCellValue(String.valueOf(list.get(i).getTotalMoney()));
            row.createCell((short) 10).setCellValue(String.valueOf(list.get(i).getBank_user()));
            row.createCell((short) 11).setCellValue(String.valueOf(list.get(i).getAdd_time()));

        }
        String path="E:/待处理提现明细"+new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+".xls";
        FileUtils.excelWrite(path,wb);
        return path;
    }

    /**
     * 导出的是当日或者当月的收支交易明细
     * @param startTime
     * @return
     */
    @Override
    public String excelDayOrMonthDetail(String startTime,String excelType) throws ParseException {

        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("待处理提现明细");

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("日期");
        cell.setCellStyle(style);
        sheet.setColumnWidth(0,20 * 256);

        cell = row.createCell((short) 1);
        cell.setCellValue("交易时间");
        cell.setCellStyle(style);
        sheet.setColumnWidth(1,20 * 512);

        cell = row.createCell((short) 2);
        cell.setCellValue("订单号");
        cell.setCellStyle(style);
        sheet.setColumnWidth(2,20 * 256);

        cell = row.createCell((short) 3);
        cell.setCellValue("商家名称/用户名称");
        cell.setCellStyle(style);
        sheet.setColumnWidth(3,20 * 512);

        cell = row.createCell((short) 4);
        cell.setCellValue("主营业务");
        cell.setCellStyle(style);
        sheet.setColumnWidth(4,20 * 512);

        cell = row.createCell((short) 5);
        cell.setCellValue("提点百分比");
        cell.setCellStyle(style);
        sheet.setColumnWidth(5,20 * 512);

        cell = row.createCell((short) 6);
        cell.setCellValue("会员账号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 7);
        cell.setCellValue("交易类型");
        cell.setCellStyle(style);

        cell = row.createCell((short) 8);
        cell.setCellValue("支付类型");
        cell.setCellStyle(style);

        /*cell = row.createCell((short) 9);
        cell.setCellValue("账户名");
        cell.setCellStyle(style);*/

        cell = row.createCell((short) 10);
        cell.setCellValue("交易状态");
        cell.setCellStyle(style);

        cell = row.createCell((short) 11);
        cell.setCellValue("订单金额");
        cell.setCellStyle(style);
        sheet.setColumnWidth(11,20 * 512);

        cell = row.createCell((short) 12);
        cell.setCellValue("实付金额");
        cell.setCellStyle(style);
        sheet.setColumnWidth(11,20 * 512);

        cell = row.createCell((short) 13);
        cell.setCellValue("满意券");
        cell.setCellStyle(style);
        sheet.setColumnWidth(11,20 * 512);

        cell = row.createCell((short) 14);
        cell.setCellValue("手续费");
        cell.setCellStyle(style);
        sheet.setColumnWidth(11,20 * 512);

        cell = row.createCell((short) 15);
        cell.setCellValue("退款单号");
        cell.setCellStyle(style);
        sheet.setColumnWidth(11,20 * 512);

        cell = row.createCell((short) 16);
        cell.setCellValue("退款金额");
        cell.setCellStyle(style);
        sheet.setColumnWidth(11,20 * 512);

        cell = row.createCell((short) 17);
        cell.setCellValue("退款状态");
        cell.setCellStyle(style);
        sheet.setColumnWidth(11,20 * 512);

        cell = row.createCell((short) 18);
        cell.setCellValue("备注");
        cell.setCellStyle(style);
        sheet.setColumnWidth(11,20 * 512);

        String message = "";
        List<DayOrMonthDetailDO> list = dailySheetRepository.queryDayOrMonthDetail(startTime,excelType);
        for(int i=0;i<list.size();i++){
            row = sheet.createRow( i+1 );
            row.createCell((short) 0).setCellValue(String.valueOf(list.get(i).getDate()));
            row.createCell((short) 1).setCellValue(String.valueOf(list.get(i).getJyTime()));
            row.createCell((short) 2).setCellValue(String.valueOf(list.get(i).getOrderNo()));
            row.createCell((short) 3).setCellValue(String.valueOf(list.get(i).getShopName()));
            row.createCell((short) 4).setCellValue(String.valueOf(list.get(i).getGoodType()));
            row.createCell((short) 5).setCellValue(String.valueOf(list.get(i).getPercent()));
            row.createCell((short) 6).setCellValue(String.valueOf(list.get(i).getUserName()));
            row.createCell((short) 7).setCellValue(String.valueOf(list.get(i).getOrderType()));
            row.createCell((short) 8).setCellValue(String.valueOf(list.get(i).getPayType()));
            //row.createCell((short) 9).setCellValue(String.valueOf(list.get(i).getBankName()));
            row.createCell((short) 10).setCellValue(String.valueOf(list.get(i).getOrderStatus()));
            row.createCell((short) 11).setCellValue(String.valueOf(list.get(i).getOrderMoney()));
            row.createCell((short) 12).setCellValue(String.valueOf(list.get(i).getRealMoney()));
            row.createCell((short) 13).setCellValue(String.valueOf(list.get(i).getVoucher()));
            row.createCell((short) 14).setCellValue(String.valueOf(list.get(i).getCommission()));
            row.createCell((short) 15).setCellValue(String.valueOf(list.get(i).getBackOrderNo()));
            row.createCell((short) 16).setCellValue(String.valueOf(list.get(i).getBackMoney()));
            row.createCell((short) 17).setCellValue(String.valueOf(list.get(i).getBackStatus()));
            row.createCell((short) 18).setCellValue(String.valueOf(list.get(i).getRemark()));
        }
        if(excelType.equals("day")){
            message = "日报表明细";
        }else{
            message = "月报表明细";
        }
        String path="E:/"+message+new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+".xls";
        FileUtils.excelWrite(path,wb);
        return path;
    }

}
