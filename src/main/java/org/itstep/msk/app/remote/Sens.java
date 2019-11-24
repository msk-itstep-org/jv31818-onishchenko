package org.itstep.msk.app.remote;

public class Sens {
    private String name;
    private String status;
    public Sens(String name, String status){
        this.name=name;
        this.status=status;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
