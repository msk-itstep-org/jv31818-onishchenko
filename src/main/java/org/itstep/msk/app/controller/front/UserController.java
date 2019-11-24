package org.itstep.msk.app.controller.front;

import org.itstep.msk.app.entity.ControllerWiy;
import org.itstep.msk.app.entity.User;
import org.itstep.msk.app.remote.RemoteController;
import org.itstep.msk.app.repository.ControllerWiyRepository;
import org.itstep.msk.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ControllerWiyRepository controllerWiyRepository;

    @RequestMapping("/")
    public String info(){
        return "redirect:/info";
    }

    @RequestMapping("/info")
    public String infoDef(Model model,Principal principal){
        if(principal!=null) {
            String username = principal.getName();
            User user = userRepository.findByUsername(username).orElse(new User());
            model.addAttribute("userid", user.getId());
        }
        return "info";
    }

    @RequestMapping("/demo")
    public String demo (){
        return "demo";
    }


    @GetMapping("/profile/{id}")
    public String getProfile(Model model, @PathVariable Integer id, Principal principal){
        if(principal!=null){
            String username = principal.getName();
            User checkUser= userRepository.findByUsername(username).orElse(new User());
            if(checkUser.getId()!=id){
                return "redirect:/error_403";
            }
            model.addAttribute("userid", checkUser.getId());
            model.addAttribute("user",checkUser);
            Set<ControllerWiy> controlsEnt = controllerWiyRepository.findByOwner(id);
            Set<RemoteController> controls = new HashSet<>();
            for(ControllerWiy cont : controlsEnt){
                RemoteController rcontroller = new RemoteController(cont);
                controls.add(rcontroller);
            }
            model.addAttribute("controls",controls);

        }


        return "profile";
    }
}
