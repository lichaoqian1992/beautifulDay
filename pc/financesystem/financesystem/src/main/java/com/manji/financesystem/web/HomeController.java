package com.manji.financesystem.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Created by pudding on 2017-1-12.
 */
@RequestMapping("/home")
@Controller
public class HomeController {

    @RequestMapping(value = "/login.html")
    public String loginSystem() {
        return "login";
    }

    @RequestMapping("/main.html")
    public String main() {
        return "layout/main";
    }

    @RequestMapping("/top.html")
    public String top() {
        return "layout/top";
    }

    @RequestMapping("left.html")
    public String left() {
        return "layout/left";
    }

    @RequestMapping("index.html")
    public String index() {
        return "layout/index";
    }


    @RequestMapping("/403")
    public String unauthorizedRole() {
        return "error/403";
    }


}
