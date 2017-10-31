package com.manji.messageserver.requestParam;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotNull;
import java.util.Set;
/**
 * Created by pudding on 2016-12-14.
 */
@Data
public class AddFriendsRequestParam extends BaseRequestParam {

    @NotEmpty(message = "ownerUserName不能为空字符串")
    @NotNull(message = "ownerUserName不能为空")
    private String ownerUserName;

    @NotEmpty(message = "friendUserName不能为空字符串")
    @NotNull(message = "friendUserName不能为空")
    private String friendUserName;

    @Override
    public <T> T click() {
        Set<ConstraintViolation<AddFriendsRequestParam>> validate = validator.validate(this);
        return (T) validate;
    }
}
