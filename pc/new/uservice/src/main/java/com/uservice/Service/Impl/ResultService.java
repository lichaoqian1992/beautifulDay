package com.uservice.Service.Impl;

import com.uservice.Dto.Result;
import com.uservice.Mapper.ResultMapper;
import com.uservice.Service.IResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pudding on 2017-8-25.(YYR)
 */
@Service
public class ResultService implements IResultService {

    @Autowired
    private ResultMapper resultMapper;

    @Override
    public Result findResultByid(String id) {
        Result result=resultMapper.findResultByid(id);
        return result;
    }
}
