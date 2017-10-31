package com.manji.messageserver.responseResult;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by pudding on 2016-12-12.
 */
public class BaseQueryResult<T> extends BaseResult {
    private static final long serialVersionUID = -3070998253698490496L;
    private List<T> resultList = new ArrayList<T>();

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    public static <T> BaseQueryResult<T> emptyResult() {
        BaseQueryResult<T> result = new BaseQueryResult<T>();
        result.setSuccessResult("查询成功，结果为空");
        return result;
    }

    public static <T> BaseQueryResult<T> failResult(String errorCode, String errorMessage) {
        BaseQueryResult<T> result = new BaseQueryResult<T>();
        result.setFailResult(errorCode, errorMessage);
        return result;
    }


    public static <T> BaseQueryResult<T> successResult(List<T> list) {
        BaseQueryResult<T> result = new BaseQueryResult<T>();
        result.setSuccessResult("查询成功");
        result.setResultList(list);
        return result;
    }

    public static <T> BaseQueryResult<T> successResult() {
        BaseQueryResult<T> result = new BaseQueryResult<T>();
        result.setSuccessResult("查询成功");
        return result;
    }

    public void addResult(T obj) {
        resultList.add(obj);
    }

    public List<T> getResultList() {
        return resultList;
    }
}
