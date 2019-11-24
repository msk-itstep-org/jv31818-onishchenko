package org.itstep.msk.app.controller.rest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminRest {

    @DeleteMapping("/users/delete")
    @ResponseBody
    public String deleteUser(){
        return "";
    }

    @PostMapping("/users/block")
    @ResponseBody
    public String blockUser(){
        return "";
    }

}
