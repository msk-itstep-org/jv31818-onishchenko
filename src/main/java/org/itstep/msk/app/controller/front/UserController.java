package org.itstep.msk.app.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @RequestMapping("/login")
    public String login(){

        return "login";
    }
    @RequestMapping("/logout")
    public String logout(
//            @RequestParam(name="name") String name
    ){
//        sessionHandler.remove(name);
        return "logout";
    }
}
