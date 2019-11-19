package org.itstep.msk.app.controller.front;

import org.itstep.msk.app.entity.User;
import org.itstep.msk.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/admin")
    public String admin() {
        return "admin/admin";
    }

    @GetMapping("/user/{id}")
    public ModelAndView users(@PathVariable Integer id) {
        ModelAndView model = new ModelAndView("admin/user");
            Map<String, Object> mod = model.getModel();
            User user = userRepository.findById(id).orElse(new User());
            mod.put("user", user);
        return model;
    }
    @PostMapping("/users")
    public String userAdd(@ModelAttribute User user) {
        ModelAndView model = new ModelAndView("admin/user");
        userRepository.save(user);
        userRepository.flush();

        return "redirect:/user/{"+user.getId()+"}";
    }
    @GetMapping("/users")
    public ModelAndView usersAll() {
        ModelAndView model = new ModelAndView("admin/users");
        Map<String, Object> mod = model.getModel();
        List<User> list = userRepository.findAll();
        mod.put("list", list);
        return model;
    }

    @GetMapping("/controllers")
    public String controllers() {
        return "admin";
    }

    @GetMapping("/monitoring")
    public String monitoring() {
        return "admin";
    }

    @GetMapping("/settings")
    public String settings() {
        return "admin";
    }

    @GetMapping("/mailsend")
    public String mail() {
        return "admin";
    }

}
