package com.manji.messageserver.requestParam;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotNull;
import java.util.Set;
/**
 * Created by pudding on 2016-12-13.
 */
@Data
public class RegisterRequestParam extends BaseRequestParam {

    @NotEmpty(message = "userName不能为空字符串")
    @NotNull(message = "userName不能为空")
    private String userName;
    @NotEmpty(message = "password不能为空字符串")
    @NotNull(message = "password不能为空")
    private String password;

    @Override
    public <T> T click() {
        Set<ConstraintViolation<RegisterRequestParam>> validate = validator.validate(this);
        return (T) validate;
    }
}
