package com.manji.financesystem.web;

import com.manji.financesystem.requestParam.InteriorRechargeRequestParam;
import com.manji.financesystem.requestParam.ShopInfoParam;
import com.manji.financesystem.requestParam.WithDrawalsParam;
import com.manji.financesystem.service.ExcelUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by Administrator on 2017/2/25.
 */
@RequestMapping("/toExcel")
@Controller
public class ExcelUtilController extends BaseController{

    @Autowired
    private ExcelUtilService excelUtilService;



    /**
     * 导出内部充值记录的Excel
     * @param param
     * @return
     * @throws IOException
     */
    @RequestMapping("/excelInside")
    public ResponseEntity<InputStreamResource> excelInside(InteriorRechargeRequestParam param) throws IOException {
        String filePath = excelUtilService.excelInside(param);
        super.setPath(filePath);
        return downLoad();
    }

    /**
     * 导出提现数据的EXCEL
     * @param param
     * @return
     * @throws IOException
     */
    @RequestMapping("/excelWithDraw")
    public ResponseEntity<InputStreamResource> excelWithDraw(WithDrawalsParam param) throws IOException {
        String filePath = excelUtilService.excelWithDraw(param);
        super.setPath(filePath);
        return downLoad();
    }

    /**
     * 导出的是商家订单信息的数据
     * @param param
     * @return
     * @throws IOException
     */
    @RequestMapping("/excelOrders")
    public ResponseEntity<InputStreamResource> excelOrders(ShopInfoParam param) throws IOException {
        //获取路径
        String filePath = excelUtilService.excelOrders(param);
        super.setPath(filePath);
        return downLoad();
    }

    /**
     * 导出待处理提现明细
     * @return
     * @throws IOException
     */
    @RequestMapping("/excelPendingprocessing")
    public  ResponseEntity<InputStreamResource> excelPendingprocessing() throws IOException{
        String filePath = excelUtilService.excelPendingprocessing();
        super.setPath(filePath);
        return downLoad();
    }

    /**
     * 根据查询条件导出交易明细
     * @param startTime
     * @return
     * @throws IOException
     */
    @RequestMapping("/excelDayOrMonthDetail")
    public ResponseEntity<InputStreamResource> excelDayOrMonthDetail(String startTime,String excelType) throws IOException, ParseException {
        //获取路径
        String filePath = excelUtilService.excelDayOrMonthDetail(startTime,excelType);
        super.setPath(filePath);
        return downLoad();
    }

}
