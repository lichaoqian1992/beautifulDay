package com.manji.cusystem.dao.common;

import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/9/5.
 */
@Data
public class Menu {

    private String path;//链接地址

    private String title;//页面名称

    private String typeTitle;//页面描述

    private String sort;//排序

    private String hierarchy;//层级

    private String css;//图标

    private List<Menu> menus;
}
