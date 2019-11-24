package org.itstep.msk.app.remote;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;

public class Protocol {
    String clientName;
    SSL ssl;
    String public_key;
    DataInputStream ins;
    DataOutputStream ous;
    Protocol() throws IOException {
        throw new IOException("Invalid constructor");
    }
    Protocol(DataInputStream in, DataOutputStream out){
        this.ins=in;
        this.ous=out;

    }

    Boolean authorize(String name) throws IOException,ConnectionException{
        Boolean result=false;
        this.clientName=name;
        String public_key_s;
        public_key_s = ins.readUTF();
        this.public_key = public_key_s;
        this.ssl=new SSL(this.public_key);
        //sql
//        SqlAction search = new SqlAction();
        ArrayList<String> res = new ArrayList<>();//=  search.select("select * from `devices` where `public_key`='"+public_key_s+"'","hostname");//!!!!!!!!!!!!!!!!!1
        res.add("sklt10001");//!!!!!!!!!!!1
        if(name.equals(res.get(0))){
             System.out.println(name+" was connected.(init starting...)");
             result=true;
             try{
                 send("pass");//!
                 //ous.flush();
             }catch (IOException e){
                 System.out.println("Cant send seccess.");
                 throw new ConnectionException("Cant send pass");
             }
            //Application.clients.add(this);
            res=null;
            //init();
        }else{
            try{
                send("fail");
                //ous.flush();
            }catch (IOException e){
                System.out.println("Cant send fail.");
                throw new ConnectionException("Cant send fail");
            }
            result=false;
            res=null;
            System.out.println(name+" was not connected. Entered key is invalid");
            //destroy();
            }
    return result;
    }
    String getName() throws IOException{
        String rs=null;
        rs=ins.readUTF();//log
        return rs;
    }
    void pair(HashMap<String,String> sensors, HashMap<String, String> actuators)throws ConnectionException{
        System.out.println("getting list devs of "+this.clientName);
        //loadDeviceList(sensors,actuators);
        int countSen=0;
        try {
            countSen = Integer.parseInt(get());
            //System.out.println(countSen);
            //System.out.println(tst);
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Getting count of sensors failed!!! Reconnect...");
            throw new ConnectionException("Somthing wrong with sensors count");
        }
        for(int u=0;u<countSen;u++){
            try {
                String name = get();
                String st=get();
                String stat = st;//CHANGE!
                if(name.equals("OK")||st.equals("OK")){
                    throw new ConnectionException("Miss some list members.");}
                sensors.put(name,stat);
            }catch (IOException e){
                System.out.println("Could not get list of sensors. Reconnect");
                throw new ConnectionException("Sensors list broken");
            }

        }
        int countAct=0;
        try {
            countAct = Integer.parseInt(get());
        }catch (IOException e){
            System.out.println("Miss count of actuators.");
            throw new ConnectionException("Miss count of acts.");
        }
        for(int y=0;y<countAct;y++){
            try {
                String name = get();
                String st=get();
                String stat = st;
                if(name.equals("OK")||st.equals("OK")){
                    throw new ConnectionException("Miss act list member.");
                }
                actuators.put(name, stat);
            }catch (IOException e){
                System.out.println("Error getting acts");
                throw new ConnectionException("Error geting actuators");
            }
        }
    }


    void loadDeviceList(HashMap<String,String> sensors, HashMap<String, String> actuators)throws ConnectionException{//load from controller lists of sensors and actuators
        getSensors(sensors);
        getActuators(actuators);


    }

    void getSensors(HashMap<String,String> sensors)throws ConnectionException{
        try {
            send("get sensors");
            ous.flush();
        } catch (IOException e) {
            System.out.println("Sending messege failed");
            throw new ConnectionException("Sending messege failed");
        }
        int countSen=0;
        try {
            countSen = Integer.parseInt(get());
        }catch (IOException e){
            System.out.println("Getting count of sensors failed!!! Reconnect...");
            throw new ConnectionException("Somthing wrong with sensors count");
        }
        for(int u=0;u<countSen;u++){
            try {
                String name = get();
                String st=get();
                String stat = st;
                if(name.equals("done")||st.equals("done"))
                    throw new ConnectionException("Miss some list members.");
                sensors.put(name,stat);
            }catch (IOException e){
                System.out.println("Could not get list of sensors. Reconnect");
                throw new ConnectionException("Sensors list broken");
            }

        }
    }
    void getSensorState(String name, HashMap<String,String> sensors)throws ConnectionException{
        try {
            send("sensor "+name+" get state");
            ous.flush();
            String curStat=get();
            sensors.put(name,curStat);
            //return ;
        } catch (IOException e) {
            System.out.println("Error command get state");
            e.printStackTrace();
            throw new ConnectionException("Error command get state");

        }


    }
    void getActuators(HashMap<String,String> actuators) throws ConnectionException{
        try {
            send("get actuators");
        }catch (IOException e){
            System.out.println("Requesting of actuators info failed");
            throw new ConnectionException("Request of act info failed.");
        }
        int countAct=0;
        try {
            countAct = Integer.parseInt(get());
        }catch (IOException e){
            System.out.println("Miss count of actuators.");
            throw new ConnectionException("Miss count of acts.");
        }

        for(int y=0;y<countAct;y++){
            try {
                String name = get();
                String st=get();
                String stat = st;
                if(name.equals("OK")||st.equals("OK")){
                    throw new ConnectionException("Miss act list member.");
                }
                actuators.put(name, stat);
            }catch (IOException e){
                System.out.println("Error getting acts");
                throw new ConnectionException("Error geting actuators");
            }
        }
   }
    void getActuatorState(String name, HashMap<String,String> actuators){
        String message="";
        try {
            send("actuator "+name+" get state");
            ous.flush();
            message = get();

        } catch (IOException e) {
            e.printStackTrace();
        }
        actuators.put(name,message);
        //System.out.println(message);
        //return message;
    }
    void setActuator(String name, String stat, HashMap<String,String> actuators){
        try {
            send("actuator "+name+" set stat "+stat);
            getActuatorState(name,actuators);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    ArrayList getWarns(){
        try {
            send("get warnings");
            if(get().equals("none")){

                }else{
                ArrayList<String> arr = new ArrayList<>();
                    for(int i=0;i<Integer.parseInt(get());i++){
                        arr.add(get());
                    }
                    return arr;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    return null;
    }
    void send(String s)throws IOException{
        try {
            //byte[] ss = ;
            //String rem = ssl.byte2Hex(ssl.encrypt(s,ssl.restorePublic()));
            ous.writeUTF(ssl.byte2Hex(ssl.encrypt(s,ssl.restorePublic())));
            ous.flush();
            //sending ok-message
            ous.writeUTF(ssl.byte2Hex(ssl.encrypt("OK",ssl.restorePublic())));
            ous.flush();


        } catch (NoSuchAlgorithmException e) {
            System.out.println("SSL error!");
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            System.out.println("SSL error here!");
            e.printStackTrace();
        }

        //return s;
    }
    String get()throws IOException{
        String s="";
        try {
            String confirm="";
            s=ssl.decrypt(ssl.hex2Byte(ins.readUTF()),ssl.restorePrivate());
            confirm=ssl.decrypt(ssl.hex2Byte(ins.readUTF()),ssl.restorePrivate());
            if(confirm.equals("OK")){
                //System.out.println("All getted");
                return s;
            }else{
                System.out.println("Error...");
                return s;
            }
        } catch (NoSuchAlgorithmException e) {
            //System.out.println("SSL error get!");
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            //System.out.println("SSL error! here get");
            e.printStackTrace();
        }
        //System.out.println(" end getting");
        return s;
    }
    class ConnectionException extends Exception{


        public ConnectionException(String s) {

        }
    }
}
