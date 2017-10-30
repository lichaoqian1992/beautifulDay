package com.manji.data.service.operate;


import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.data.repository.OperateRespository;

/**
 * 业务
 */
public class OperateService {
    private OperateRespository operateRespository=new OperateRespository();

    public Page<Record> shopInformationData(int pageNumber, int pageSize, String shopName, String personName, String mobile, String startTime, String endTime){
        return operateRespository.shopInformationData(pageNumber,pageSize,shopName,personName,mobile,startTime,endTime);
    }

    public Page<Record> shopNoSaleData(int pageNumber, int pageSize, String shopName, String personName, String mobile, String time){
        return operateRespository.shopNoSaleData(pageNumber,pageSize,shopName,personName,mobile,time);
    }

    public Page<Record> shopSettledData(int pageNumber, int pageSize, String shopName, String personName, String mobile, String time){
        return operateRespository.shopSettledData(pageNumber,pageSize,shopName,personName,mobile,time);
    }
}
