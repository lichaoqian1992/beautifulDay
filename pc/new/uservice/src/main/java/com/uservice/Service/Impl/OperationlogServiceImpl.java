package com.uservice.Service.Impl;

import com.uservice.Dto.Operationlog;
import com.uservice.Mapper.OperationlogMapper;
import com.uservice.Service.IOperationlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by pudding on 2017-9-5.(YYR)
 */
@Service
public class OperationlogServiceImpl implements IOperationlogService{

    @Autowired
    private OperationlogMapper operationlogMapper;


    /**
     * 添加日志记录
     * @param op_type
     * @param content
     * @param user_id
     * @param add_time
     * @param important
     * @return
     */
    @Override
    public boolean insertOperationlog(int op_type, String content, int user_id, Date add_time, String important) {
        Operationlog operationlog=new Operationlog();
        operationlog.setOp_type(op_type);
        operationlog.setContent(content);
        operationlog.setUser_id(user_id);
        operationlog.setAdd_time(add_time);
        operationlog.setImportant(important);
        Boolean insertlogisok=operationlogMapper.insertOperationlog(operationlog);//添加日志
        return insertlogisok;
    }
}
