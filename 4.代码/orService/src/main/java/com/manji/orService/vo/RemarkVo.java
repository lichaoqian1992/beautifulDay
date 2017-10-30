package com.manji.orService.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2017/8/30.
 */

@Data
public class RemarkVo {

    @NotNull(message = "id不能为空")
    private String id;
    @NotNull(message = "content不能为空")
    private String content;

    private String pics;
}
