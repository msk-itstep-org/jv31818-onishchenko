package org.itstep.msk.app.remote;

import org.itstep.msk.app.AppApplication;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.ServerSocket;

public class SocketServerController implements Runnable{
    private ServerSocket ss;
    private int port;
    public SocketServerController() throws IOException{
        port = 7777;//Integer.parseInt(AppApplication.settings.getProperty("socketport")); // случайный порт (может быть любое число от 1025 до 65535)
        ss = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту
    }


    public void run()    {



            System.out.println("Socket server was started on port: "+port);
            System.out.println("Waiting for a client...");
            try {
                while(AppApplication.running){
                    try {
                        //Socket connection = ss.accept();

                        Client cli = new Client(ss.accept());
                        System.out.println("Client connected.");
                        new Thread(cli).start();
                    }catch (IOException r){
                        System.err.print("Client socket was not closed.");

                    }
                }
                ss.close();
            }catch (IOException e) {
                System.err.print("Server socket was not closed.");
                try {
                    ss.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }finally {
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


    }
}