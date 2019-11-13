package org.itstep.msk.app.controller.front;

import org.itstep.msk.app.entity.User;
import org.itstep.msk.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@PreAuthorize("hasRole('ADMIN')")
@Controller
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/admin/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/admin/users/{id}")
    public ModelAndView users(@PathVariable Integer id){
        ModelAndView model = new ModelAndView("admin/users");
        if(id!=null&&id!=0) {
            Map<String, Object> mod = model.getModel();
            User user = userRepository.findById(id).orElse(new User());
            mod.put("user", user);;
        }else {
            Map<String, Object> mod = model.getModel();
            List<User> list = userRepository.findAll();
            mod.put("list",list);
        }
        return model;
    }
    @GetMapping("/admin/controllers")
    public String controllers(){
        return "admin";
    }
    @GetMapping("/admin/monitoring")
    public String monitoring(){
        return "admin";
    }
    @GetMapping("/admin/settings")
    public String settings(){
        return "admin";
    }
    @GetMapping("/admin/mailsend")
    public String mail(){
        return "admin";
    }

}