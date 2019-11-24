package org.itstep.msk.app.remote;

import org.itstep.msk.app.entity.ControllerWiy;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Set;

public class RemoteController {
private     ControllerWiy controllerWiy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        available = available;
    }

    private String name;
private Boolean available;

    public RemoteController(ControllerWiy controllerWiy) {
        this.controllerWiy = controllerWiy;
        this.name=controllerWiy.getName();
        try {
            this.available = InetAddress.getByAddress(this.controllerWiy.getHostname().getBytes()).isReachable(1000);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        };
    }

    //rest methods
    public Set<String> getWarns(){
        return null;
    }

}
