package com.manji.financesystem.requestParam;

import lombok.Data;

/**
 * Created by Administrator on 2017/3/6.
 */
@Data
public class OaRequestParam {
    /**OA文件编号*/
    private String oaNo;
    /**创建人*/
    private String creater;

    private int pageNum;
}
