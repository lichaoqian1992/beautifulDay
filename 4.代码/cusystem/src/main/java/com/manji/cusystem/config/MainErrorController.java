package com.manji.cusystem.config;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Administrator on 2017/8/31.
 */
@Controller
public class MainErrorController implements ErrorController{

    private static final String ERROR_PATH = "/error";

    @GetMapping(value = "/404")
    public String notFound(){

        return "error/404";
    }

    @GetMapping(value = "/500")
    public String servletError(){

        return "error/500";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
