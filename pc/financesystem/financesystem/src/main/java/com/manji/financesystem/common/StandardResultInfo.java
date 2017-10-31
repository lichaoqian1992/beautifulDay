package com.manji.financesystem.common;


import lombok.Data;

import java.io.Serializable;

/**
 * Created by luobairan on 2016/7/26.
 */
@Data
public class StandardResultInfo implements Serializable {

    protected StatusEnum statusEnum;
    protected String code;
    protected String description;
    protected String errorMessage;








}
