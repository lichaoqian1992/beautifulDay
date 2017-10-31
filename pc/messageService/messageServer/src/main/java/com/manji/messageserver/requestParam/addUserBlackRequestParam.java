package com.manji.messageserver.requestParam;

import lombok.Data;
import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;
/**
 * Created by Administrator on 2016/12/15.
 */
@Data
public class addUserBlackRequestParam extends BaseRequestParam{

    @NotNull(message = "usernames不能为空")
    private List<String> usernames;

    @Override
    public <T> T click() {
        Set<ConstraintViolation<addUserBlackRequestParam>> validate = validator.validate(this);
        return (T) validate;
    }
}
