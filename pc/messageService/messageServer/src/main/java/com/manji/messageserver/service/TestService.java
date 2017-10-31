package com.manji.messageserver.service;

import com.manji.messageserver.requestParam.TestRequestParam;
import com.manji.messageserver.responseResult.BaseResult;
/**
 * Created by pudding on 2016-12-12.
 */
public interface TestService {
    public BaseResult test(TestRequestParam param);
}
