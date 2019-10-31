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
    @RequestMapping("/admin")
    public String admin(){

        return "admin";
    }
    @RequestMapping("/login")
    public String login( Map<String, Object> model){
        model.put("username","username");
        model.put("password","password");
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
