package org.itstep.msk.app.remote;

import java.util.ArrayList;
import java.util.List;

public class Dev {
    private String name;
    private Boolean connected;
    private Boolean disconnected;
    private Boolean sensis;
    private List<Sens> sens=new ArrayList<>();
    private Boolean actsis;
    private List<Acts> acts=new ArrayList<>();
    private List<String> scenarios = new ArrayList<>();
    private Boolean havescenario = false;
    private Boolean havenoscenario = true;
    //warnings
    private Boolean havewarns=false;
    private Boolean havenowarns=true;
    private List<String> warns=new ArrayList<>();
    public Dev(String name, Boolean con){
        this.name=name;
        this.connected=con;
        this.disconnected=true;
    }
    public Boolean getConnected() {
        return connected;
    }

    public void setConnected(Boolean connected) {
        this.connected = connected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDisconnected() {
        return disconnected;
    }

    public void setDisconnected(Boolean disconnected) {
        this.disconnected = disconnected;
    }


    public void addSens(String name, String st){
        this.sens.add(new Sens(name,st));
    }
    public void addActs(String name, String st){
        this.acts.add(new Acts(name,st));
    }

    public Boolean getSensis() {
        return sensis;
    }

    public Boolean getActsis() {
        return actsis;
    }

    public void setSensis(Boolean sensis) {
        this.sensis = sensis;
    }

    public void setActsis(Boolean actsis) {
        this.actsis = actsis;
    }

    public List<String> getScenarios() {
        return scenarios;
    }

    public void setScenarios(List<String> scenarios) {
        this.scenarios = scenarios;
    }
    public void addScenario(String scenario){
        this.scenarios.add(scenario);
    }

    public Boolean getHavescenario() {
        return havescenario;
    }

    public void setHavescenario(Boolean havescenario) {
        this.havescenario = havescenario;
    }

    public Boolean getHavenoscenario() {
        return havenoscenario;
    }

    public void setHavenoscenario(Boolean havenoscenario) {
        this.havenoscenario = havenoscenario;
    }

    public Boolean getHavewarns() {
        return havewarns;
    }

    public void setHavewarns(Boolean havewarns) {
        this.havewarns = havewarns;
    }

    public Boolean getHavenowarns() {
        return havenowarns;
    }

    public void setHavenowarns(Boolean havenowarns) {
        this.havenowarns = havenowarns;
    }

    public List<String> getWarns() {
        return warns;
    }

    public void setWarns(List<String> warns) {
        this.warns = warns;
    }
    public void addWarns(String s){
        this.warns.add(s);
    }
}
