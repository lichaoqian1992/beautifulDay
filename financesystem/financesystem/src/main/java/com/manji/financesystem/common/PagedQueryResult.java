package com.manji.financesystem.common;


import java.util.List;

/**
 * Created by luobairan on 2016/7/29.
 */
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
        pagedQueryResult.statusEnum = StatusEnum.SUCCESS;
        return pagedQueryResult;
    }

    public static <T> PagedQueryResult<T> emptyResult() {
        PagedQueryResult<T> pagedQueryResult = new PagedQueryResult<T>();
        pagedQueryResult.setPage(1);
        pagedQueryResult.setTotalCount(0);
        pagedQueryResult.setSuccessResult("查询成功，结果为空");
        pagedQueryResult.statusEnum = StatusEnum.SUCCESS;
        return pagedQueryResult;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
