package com.manji.messageserver.responseResult;

import com.manji.messageserver.common.enums.StatusEnum;
import lombok.Data;
import java.io.Serializable;
/**
 * Created by pudding on 2016-12-12.
 */
@Data
public class StandardResultInfo implements Serializable {

    protected StatusEnum statusEnum;
    protected String code;
    protected String description;
    protected String errorMessage;

    @Override
    public String toString() {
        return "StandardResultInfo{" +
                "statusEnum=" + statusEnum +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
