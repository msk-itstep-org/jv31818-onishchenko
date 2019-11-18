package org.itstep.msk.app.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class UserController {

    @RequestMapping("/")
    public String info(){

        return "info";
    }
    @RequestMapping("/info")
    public String infoDef(){

        return "info";
    }

    @RequestMapping("/demo")
    public String demo (){
        return "demo";
    }


}
