package com.manji.messageserver.responseResult;

import lombok.Data;
import java.util.List;
/**
 * Created by pudding on 2016-12-12.
 */
@Data
public class PagedQueryResult<T> extends BaseQueryResult<T> {

    private long totalCount = 0;

    private int page = 1;

    public static <T> PagedQueryResult<T> successResult(List<T> resultList, int page,
                                                        long totalCount) {
        PagedQueryResult<T> successResult = successResult(page, totalCount);
        successResult.setResultList(resultList);
        return successResult;
    }

    public static <T> PagedQueryResult<T> successResult(int page, long totalCount) {
        PagedQueryResult<T> pagedQueryResult = new PagedQueryResult<T>();
        pagedQueryResult.setPage(page);
        pagedQueryResult.setTotalCount(totalCount);
        pagedQueryResult.setSuccessResult("查询成功");
        return pagedQueryResult;
    }

    public static <T> PagedQueryResult<T> emptyResult() {
        PagedQueryResult<T> pagedQueryResult = new PagedQueryResult<T>();
        pagedQueryResult.setPage(1);
        pagedQueryResult.setTotalCount(0);
        pagedQueryResult.setSuccessResult("查询成功，结果为空");
        return pagedQueryResult;
    }
}
