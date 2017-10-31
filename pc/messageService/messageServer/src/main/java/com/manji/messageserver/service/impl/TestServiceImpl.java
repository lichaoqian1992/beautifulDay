package com.manji.messageserver.service.impl;

import com.manji.messageserver.requestParam.TestRequestParam;
import com.manji.messageserver.responseResult.BaseResult;
import com.manji.messageserver.service.TestService;
import org.springframework.stereotype.Service;
/**
 * Created by pudding on 2016-12-12.
 */
@Service
public class TestServiceImpl implements TestService {

    @Override
    public BaseResult test(TestRequestParam param) {
        return BaseResult.getSuccessResult("成功处理");
    }
}
