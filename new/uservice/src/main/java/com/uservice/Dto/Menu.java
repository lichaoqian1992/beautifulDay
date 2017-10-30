package com.uservice.Dto;

import com.uservice.MybatisUtil.Invisible;
import lombok.Data;

/**
 * Created by pudding on 2017-8-30.(YYR)
 */
@Data
public class Menu {

    int id;

    String path;

    String title;

    String hierarchy;

    String sort;

    String type_id;

    String pjt_code;
}
