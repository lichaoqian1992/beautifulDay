package com.manji.messageserver.requestParam;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotNull;
import java.util.Set;
/**
 * Created by Administrator on 2016/12/15.
 */
@Data
public class removeBlackUserRequestParam extends BaseRequestParam{
    @NotNull(message = "ownerUserName不能为空")
    @NotEmpty(message = "ownerUserName不能为空字符串")
    private String ownerUserName;

    @NotNull(message = "blockedUserName不能为空")
    @NotEmpty(message = "blockedUserName不能为空字符串")
    private String blockedUserName;

    @Override
    public <T> T click() {
        Set<ConstraintViolation<removeBlackUserRequestParam>> validate = validator.validate(this);
        return (T) validate;
    }
}
