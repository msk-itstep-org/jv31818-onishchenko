package org.itstep.msk.app.remote;

import java.io.*;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;

public class Client implements Runnable{
    ArrayList<String> warnings;
    public ArrayDeque<String> commands;
    Protocol protocol;
    HashMap<String,String> remoteSensors;
    HashMap<String,String> remoteActuators;
    Socket connection;
    DataOutputStream ous;
    DataInputStream ins;
    boolean auth;
    public String name;
    static volatile Deque<String> command_list;//list of commands
    Client(String name){
        this.name=name;
    }
    Client(){
        ins=null;
        ous=null;
        connection=null;
        auth=false;
        try {
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Error in constructing. Never use this const");
    }
    Client(Socket connection) throws IOException {
        // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту.
        InputStream sin = connection.getInputStream();
        OutputStream sout = connection.getOutputStream();
        this.connection=connection;

        // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
        ins = new DataInputStream(sin);
        ous = new DataOutputStream(sout);
        //Далее необходима авторизация
        auth=false;
        //Создаем протокол
        this.protocol=new Protocol(ins,ous);
        //cmd=new ArrayList<String>();
        remoteSensors=new HashMap<>();
        remoteActuators=new HashMap<>();

        warnings=new ArrayList<>();
        commands=new ArrayDeque<>();
        //Application.clients.add(this);
        /*command_list=new Deque<String>(){
            @Override
            public void clear() {

            }

            @Override
            public String poll() {
                return null;
            }
        };*/
    }
    @Override
    public void run(){//authorize client and connect or destroy
       try{
           this.name=protocol.getName();
       }catch (IOException e){
           System.out.println("Could not get name of client! Reconnect");
           closeConnection();
           return;
       }
       try {
           Boolean auth=false;
           auth=protocol.authorize(this.name);
           if(auth){
               this.auth=auth;
//               Application.clients.add(this);
               System.out.println("Auth is success");
           }else{
               this.auth=false;
               System.out.println("Authorization failed. Try again.");
           }
       }catch (IOException e){
           System.out.println("Authorization process invalid! Reconnect.");
           closeConnection();
           return;
       }catch (Protocol.ConnectionException e){

       }
       listen();
    }
    void listen(){
        try {
            protocol.pair(remoteSensors, remoteActuators);

        }catch (Protocol.ConnectionException e){
            System.out.println(e.toString());
            System.out.println("Pair was broken! Reconnect");
            closeConnection();
        }
       /* while(!connection.isClosed()){
            //Мониторинг сенсоров.
            for(Map.Entry<String,Boolean> pair : remoteSensors.entrySet()){
                Boolean stored_state=pair.getValue();
                String state="";
                try{
                    state=protocol.getSensorState(pair.getKey()).toString();
                }catch (Protocol.ConnectionException e){
                    System.out.println("Failed get state.");
                }
                if(state.equals("")){
                    continue;
                }else{
                    if(Boolean.parseBoolean(state)==stored_state){
                        continue;
                    }else{
                        remoteSensors.put(pair.getKey(),!stored_state);
                        System.out.println("State for dev "+this.name+", sensor "+pair.getKey()+" was changed to "+!stored_state);
                    }
                }
            }*/
        while(!connection.isClosed()){
            //обработка команд
            if(!this.commands.isEmpty()){
                String cmd=this.commands.pollFirst();
                execute(cmd);
            }
            //warnings=protocol.getWarns();

        }
        System.out.println("Con closed!!!");
        auth=false;
        closeConnection();
    }
    void closeConnection(){
        try {
            connection.close();
//            Application.clients.remove(this);
            //destroy();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


public void execute(String s){
    if(s.equals("get sensors")){//simple comands!!!
        try {
            protocol.getSensors(remoteSensors);
        } catch (Protocol.ConnectionException e) {
            System.out.println(e);
        }

    }else if(s.equals("get actuators")){
        try {
            protocol.getActuators(remoteActuators);
        } catch (Protocol.ConnectionException e) {
            System.out.println(e);
        }

    }else if(s.substring(0,6).equals("sensor")){//sensors!!!
        String[] comand = new String[4];
        comand=s.split(" ");
        String name_sensor=comand[1];//name
        String action = comand[2];//set\get
        String param = comand[3];//parametr to or get name\stat
        if(action.equals("get")){
            try {
                protocol.getSensorState(name_sensor,this.remoteSensors);
            } catch (Protocol.ConnectionException e) {
                e.printStackTrace();
            }
        }else if(action.equals("set")){
            System.out.println("Operation is not possible");
        }

    }else if(s.substring(0,6).equals("actuat")){//actuators!!!
        String[] comand = new String[5];
        comand=s.split(" ");
        String name_act=comand[1];//name
        String action = comand[2];//set\get
        String param = comand[3];//param name\stat
        String value = comand[4];//value
        //OutRelay obj = getOutObj(name_act);
        if(action.equals("get")){
            if(param.equals("name")){
               System.out.println("realy???)))");
            }else if(param.equals("stat")){
                protocol.getActuatorState(name_act,remoteActuators);
            }
        }else if(action.equals("set")){
            if(param.equals("name")){
                System.out.println("Invalid operation");
            }else if(param.equals("stat")){
                protocol.setActuator(name_act, value,remoteActuators);
            }
        }
    }

    }
    /*InRelay getInObj(String name){
        for(Map.Entry<InRelay,Boolean> pair : App.inRelay.entrySet()){
            InRelay in=pair.getKey();
            if(in.getName().equals(name)){
                return in;
            }
        }
        return null;
    }
    OutRelay getOutObj(String name){
        for(Map.Entry<OutRelay,Boolean> pair : App.outRelay.entrySet()){
            OutRelay out=pair.getKey();
            if(out.getName().equals(name)){
                return out;
            }
        }
        return null;
    }*/
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
}