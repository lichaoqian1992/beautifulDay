package com.manji.messageserver.requestParam;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.Serializable;
/**
 * Created by pudding on 2016-12-12.
 */
public abstract class BaseRequestParam implements Serializable {
    protected static Validator validator;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    /**
     * 参数校验
     * @param <T>
     * @return
     */
    public abstract <T> T click();
}
