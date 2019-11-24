package org.itstep.msk.app.remote;

public class Acts {
    private String name;
    private String status;
    public Acts(String name, String st) {
        this.name=name;
        this.status=st;
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
