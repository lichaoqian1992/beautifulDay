package com.manji.financesystem.service.impl;

import com.manji.financesystem.common.BaseResult;
import com.manji.financesystem.common.ObjectBaseResult;
import com.manji.financesystem.enums.RechargeStatus;
import com.manji.financesystem.primaryDomain.entiity.UserAccountInfoDO;
import com.manji.financesystem.primaryDomain.repository.UserNormalRechargeRepository;
import com.manji.financesystem.requestParam.UserQueryNormalRechargeListParam;
import com.manji.financesystem.responseData.UserNormalRechargeStatisticsResult;
import com.manji.financesystem.secondaryDomain.entity.InteriorRechargeDetailDO;
import com.manji.financesystem.secondaryDomain.entity.RechargeConfigDO;
import com.manji.financesystem.secondaryDomain.entity.Systemlog;
import com.manji.financesystem.secondaryDomain.repository.InteriorRechargeRepository;
import com.manji.financesystem.secondaryDomain.repository.SystemLogUtilsRepository;
import com.manji.financesystem.secondaryDomain.repository.SystemlogRepository;
import com.manji.financesystem.service.UserNormalRechargeService;
import com.manji.financesystem.utils.DateUtils;
import com.manji.financesystem.utils.ExcelReadUtil;
import com.manji.financesystem.utils.FileUtils;
import com.manji.financesystem.vo.ExcelVO;
import org.apache.poi.hssf.usermodel.*;
import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by pudding on 2017-2-4.
 */
@Service
public class UserNormalRechargeServiceImpl implements UserNormalRechargeService {
    private static final Logger logger = LoggerFactory.getLogger(UserNormalRechargeService.class);

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

    @Autowired
    private UserNormalRechargeRepository userNormalRechargeRepository;

    @Autowired
    private InteriorRechargeRepository interiorRechargeRepository;

    @Autowired
    private SystemLogUtilsRepository systemlogRepository;


    @Override
    public BaseResult statisticsSumAmount() {
        ObjectBaseResult objectBaseResult = new ObjectBaseResult();
        UserNormalRechargeStatisticsResult result = new UserNormalRechargeStatisticsResult();
        String toDay = dateFormat.format(new Date());
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 1);
        String toMorrow = dateFormat.format(cal.getTime());
        Double toDayAmount = userNormalRechargeRepository.getTodaySumAmount(toDay, toMorrow);
        String currentMonthFirstDayTime = DateUtils.getCurrentMonthFirstDayTime();
        String currentMonthLastDayTime = DateUtils.getCurrentMonthLastDayTime();
        Double toMorrowAmount = userNormalRechargeRepository.getToMorrowSumAmount(currentMonthFirstDayTime, currentMonthLastDayTime);
        result.setToDayAmount(toDayAmount == null ? 0 : toDayAmount);
        result.setTheCurrentMonthAmount(toMorrowAmount == null ? 0 : toMorrowAmount);
        objectBaseResult.setObj(result);
        objectBaseResult.setSuccessResult("查询成功");
        return objectBaseResult;
    }

    @Override
    public String excel(UserQueryNormalRechargeListParam param) {

        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("充值记录(正常)");

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("编号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("第三方单号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("支付方式");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("付款金额");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("付款用户");
        cell.setCellStyle(style);
        cell = row.createCell((short) 5);
        cell.setCellValue("状态");
        cell.setCellStyle(style);
        cell = row.createCell((short) 6);
        cell.setCellValue("付款时间");
        cell.setCellStyle(style);
        cell = row.createCell((short) 7);
        cell.setCellValue("通知到账时间");
        cell.setCellStyle(style);
        cell = row.createCell((short) 8);
        cell.setCellValue("备注");
        cell.setCellStyle(style);
        sheet.setColumnWidth(1, 20 * 512);
        sheet.setColumnWidth(2, 20 * 256);
        sheet.setColumnWidth(3, 20 * 256);
        sheet.setColumnWidth(4, 20 * 256);
        sheet.setColumnWidth(5, 20 * 256);
        sheet.setColumnWidth(6, 20 * 256);
        sheet.setColumnWidth(7, 20 * 256);
        sheet.setColumnWidth(8, 20 * 256);
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
        List<Map<String, Object>> maps = userNormalRechargeRepository.queryUserNormalRechargeAllRecord(param);


        for (int i = 0; i < maps.size(); i++) {
            row = sheet.createRow((int) i + 1);
            // 第四步，创建单元格，并设置值
            row.createCell((short) 0).setCellValue(i);
            row.createCell((short) 1).setCellValue(String.valueOf(maps.get(i).get("transaction_no")));
            row.createCell((short) 2).setCellValue(String.valueOf(maps.get(i).get("payment_name")));
            row.createCell((short) 3).setCellValue(String.valueOf(maps.get(i).get("transaction_money")));
            row.createCell((short) 4).setCellValue(String.valueOf(maps.get(i).get("user_name")));
            String code = RechargeStatus.getMsgByCode(String.valueOf(maps.get(i).get("status")));
            row.createCell((short) 5).setCellValue(String.valueOf(code));
            row.createCell((short) 6).setCellValue(String.valueOf(maps.get(i).get("add_time")));
            row.createCell((short) 7).setCellValue(String.valueOf(maps.get(i).get("notify_time")));
            row.createCell((short) 8).setCellValue(String.valueOf(maps.get(i).get("remark")));

        }

        String path = "E:/用户充值记录(正常)" + new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date()) + ".xls";
        System.out.printf(path);
        FileUtils.excelWrite(path, wb);
        return path;
    }

    @Override
    public String recharge(int oaNo,String excelPath, String excelNo, String rechargeType, String excelName , String personRelease) {
        String  status="";
        final int[] userId2 = new int[1];
        String content = "";
        String date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        try {
            String czStatus = "";//充值状态
            String userKeyStr = "";//用户标识
            int userId=0;//用户ID
            int roleValue=0;//角色值
            String userName = "";//用户名称
            String roleType = "";//角色类型
            String remark = "";//备注
            String personApproving = "";//审核人
            double singleMoney = 0;
            List<InteriorRechargeDetailDO> interiorRechargeDetailDOList = new ArrayList<InteriorRechargeDetailDO>();
            //读取excel的内容，充值金额+用户标识；根据用户标识查询用户的用户ID和用户角色类型
            List<ExcelVO> vos = ExcelReadUtil.readExcel(excelPath);
            System.out.println(vos.size());
            for(int i=0;i<vos.size();i++){
                userKeyStr = vos.get(i).getUserKey();
                singleMoney = vos.get(i).getMoney();
                //获得充值用户的ID+角色类型
                List<UserAccountInfoDO> list = userNormalRechargeRepository.queryUserInfoByUserKey(userKeyStr);
                //生成充值订单
                if(list != null && list.size()>0){
                    userId = list.get(0).getUserId();
                    roleType = list.get(0).getRoleType();
                    roleValue = list.get(0).getRoleValue();
                    //userName = list.get(0).getUserName();
                    //查询该充值单对应的审核人
                    List<RechargeConfigDO> rechargeConfigDOList = interiorRechargeRepository.queryRechargeConfig(singleMoney);
                    personApproving = rechargeConfigDOList.get(0).getUserName();
                    czStatus = "3";
                    remark = "OA批量充值";
                }else{
                    czStatus = "2";
                    remark = "充值用户不存在";
                }
                InteriorRechargeDetailDO detailDO = new InteriorRechargeDetailDO();
                Date date = new Date();
                detailDO.setOrderNo("MJ"+format.format(date)+i);
                detailDO.setExcelNo(excelNo);
                detailDO.setTranSN(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(date)+i);
                detailDO.setOaNo(oaNo);
                detailDO.setExcelName(excelName);
                detailDO.setUserKey(vos.get(i).getUserKey());
                detailDO.setRechargeMoney(vos.get(i).getMoney());
                detailDO.setUserId(userId);
                detailDO.setRoleType(roleType);
                detailDO.setRoleValue(roleValue);
                detailDO.setRechargeType("4");//充值类型
                detailDO.setStatus(czStatus);//充值状态
                detailDO.setPersonRelease(personRelease);//发布人
                detailDO.setPersonApproving(personApproving);//审核人
                detailDO.setPersonMakeSure("分管会计");//确认人
                detailDO.setCheckStatus("0");
                detailDO.setCreateTime(dateFormat2.format(date));
                detailDO.setApprovalTime(dateFormat2.format(date));
                detailDO.setCheckTime(dateFormat2.format(date));
                detailDO.setRemark(remark);
                interiorRechargeDetailDOList.add(detailDO);
            }
            interiorRechargeRepository.addInteriorRechargeDetail(interiorRechargeDetailDOList);
            status = "SUCCESS";
            if(status.equals("SUCCESS")){
                //记录日志信息
                content = "用户"+personRelease+"在"+date2+"发布充值信息,批次号:"+excelNo;
                systemlogRepository.addSystemLogs(personRelease , content);
            }
        } catch (FileNotFoundException e) {
            logger.error("读取Excel错误 errorMsg={}", e.getMessage());
            return "NOT FOUND";
        } catch (FileFormatException e) {
            logger.error("读取Excel错误 errorMsg={}", e.getMessage());
            return "ERROR";
        }
        return status;
    }
}
