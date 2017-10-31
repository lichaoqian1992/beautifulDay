package com.manji.messageserver.requestParam;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotNull;
import java.util.Set;
/**
 * Created by pudding on 2016-12-12.
 */
@Data
public class TestRequestParam extends BaseRequestParam{
    @NotNull(message = "userName不能为空")
    @NotEmpty(message = "userName不能为空字符串")
    private String userName;

    @Override
    public <T> T click() {
        Set<ConstraintViolation<TestRequestParam>> validate = validator.validate(this);
        return (T) validate;
    }
}
