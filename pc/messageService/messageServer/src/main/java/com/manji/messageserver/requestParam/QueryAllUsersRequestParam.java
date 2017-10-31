package com.manji.messageserver.requestParam;


import lombok.Data;
import javax.validation.ConstraintViolation;
import java.util.Set;
/**
 * Created by Administrator on 2016/12/14.
 */
@Data
public class QueryAllUsersRequestParam extends BaseRequestParam{
    private String cursor;

    @Override
    public <T> T click() {
        Set<ConstraintViolation<QueryAllUsersRequestParam>> validate = validator.validate(this);
        return (T) validate;
    }
}
