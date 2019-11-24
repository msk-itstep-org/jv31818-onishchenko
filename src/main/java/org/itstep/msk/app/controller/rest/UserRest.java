package org.itstep.msk.app.controller.rest;

import org.itstep.msk.app.entity.ControllerWiy;
import org.itstep.msk.app.remote.RemoteController;
import org.itstep.msk.app.repository.ControllerWiyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRest {

    @Autowired
    private ControllerWiyRepository controllerWiyRepository;

    @GetMapping("/controller/getwarns/{id}")
    @ResponseBody
    public String getWarns(@PathVariable Integer idController){
        //Создание коннекта с ссл и т.д.
        ControllerWiy controller =controllerWiyRepository.findById(idController).orElse(null);
        return new RemoteController(controller).getWarns().toString();
    }
}
