package com.manji.desystem.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Administrator on 2017/8/30.
 */

@Data
public class DeptVo {

    private String id;

    private String deptname;

}
