package org.itstep.msk.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("/")
    public String info(){

        return "info.html";
    }
    @RequestMapping("/info")
    public String infoDef(){

        return "info.html";
    }
}
