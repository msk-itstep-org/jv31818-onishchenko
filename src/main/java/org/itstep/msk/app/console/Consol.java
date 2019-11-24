package org.itstep.msk.app.console;

import org.itstep.msk.app.AppApplication;
import org.itstep.msk.app.remote.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Consol implements Runnable {
    @Override
    public void run() {
        while(AppApplication.running){
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("type the name of controller...");
            try{
                String controllername=reader.readLine();
                System.out.println("get sensors" +
                        "get actuators" +
                        "actuator|sensor set|get name stat|true(false)");
                commandDev(controllername,reader.readLine());//reader.readLine(),reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }






    }
    public Client getCliByName(String name){
        Client client=null;
        if(AppApplication.clients.isEmpty()){
            System.out.println("List of clients is empty!");
            return null;
        }
        for(Client cli: AppApplication.clients){
            if(cli.name.equals(name)){
                client=cli;
                return cli;
            }
        }
        if(client==null){
            System.out.println("there is no such client");
        }
        return client;
    }
    public void commandDev(String name, String cmd){
        Client client=getCliByName(name);
        client.commands.add(cmd);
        System.out.println("For "+client.name+"was added cmd: "+cmd);

    }
}
