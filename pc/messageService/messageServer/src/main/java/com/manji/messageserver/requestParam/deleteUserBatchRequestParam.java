package com.manji.messageserver.requestParam;

import lombok.Data;
import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotNull;
import java.util.Set;
/**
 * Created by Administrator on 2016/12/16.
 */
@Data
public class deleteUserBatchRequestParam extends BaseRequestParam{

    @NotNull(message = "limit不能为空")
    private Integer limit;

    @Override
    public <T> T click() {
        Set<ConstraintViolation<deleteUserBatchRequestParam>> validate = validator.validate(this);
        return (T) validate;
    }
}
