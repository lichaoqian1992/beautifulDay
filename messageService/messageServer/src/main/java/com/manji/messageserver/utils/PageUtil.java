package com.manji.messageserver.utils;

import com.manji.messageserver.requestParam.BaseRequestQueryParam;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
/**
 * Created by luobairan on 2016/7/29.
 */
public class PageUtil {
    /**
     * 根据查询Order获取Pageable实例
     * @param queryOrder
     * @return
     */
    public static Pageable getPageable(BaseRequestQueryParam queryOrder) {
        // JPA pageable的page从0开始
        return new PageRequest(queryOrder.getPage() - 1, queryOrder.getPageSize());
    }
    /**
     * 根据查询Order获取Pageable实例，并设置排序
     * @param queryOrder
     * @param sort
     * @return
     */
    public static Pageable getPageable(BaseRequestQueryParam queryOrder, Sort sort) {
        // JPA pageable的page从0开始
        return new PageRequest(queryOrder.getPage() - 1, queryOrder.getPageSize(), sort);
    }
    public static Pageable getPageable(Integer page,Integer pageSize) {
        // JPA pageable的page从0开始
        return new PageRequest(page - 1, pageSize);
    }
}
