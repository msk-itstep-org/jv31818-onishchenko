package org.itstep.msk.app.remote;

import org.itstep.msk.app.entity.ControllerWiy;

import java.util.Set;

public class RemoteController {
    ControllerWiy controllerWiy;

    public RemoteController(ControllerWiy controllerWiy) {
        this.controllerWiy = controllerWiy;
    }

    //rest methods
    public Set<String> getWarns(){
        return null;
    }
}
