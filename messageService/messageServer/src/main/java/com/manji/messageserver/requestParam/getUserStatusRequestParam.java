package com.manji.messageserver.requestParam;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotNull;
import java.util.Set;
/**
 * Created by Administrator on 2016/12/16.
 */
@Data
public class getUserStatusRequestParam extends BaseRequestParam{
    @NotEmpty(message = "userName不能为空")
    @NotNull(message = "userName不能为空字符串")
    private String userName;

    @Override
    public <T> T click() {
        Set<ConstraintViolation<getUserStatusRequestParam>> validate = validator.validate(this);
        return (T) validate;
    }
}
