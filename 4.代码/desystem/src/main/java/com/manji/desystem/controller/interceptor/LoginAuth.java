package com.manji.desystem.controller.interceptor;

import java.lang.annotation.*;

/**
 * 访问接口的注解---是否登录等等
 *
 * @author ShyMe
 */
@Documented
@Inherited
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginAuth {

}
