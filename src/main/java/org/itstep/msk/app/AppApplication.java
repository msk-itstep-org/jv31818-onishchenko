package org.itstep.msk.app;

import org.itstep.msk.app.console.Consol;
import org.itstep.msk.app.remote.Client;
import org.itstep.msk.app.remote.SocketServerController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

@SpringBootApplication
public class AppApplication {
	public static boolean running;
	public static ArrayList<Client> clients;
	public static Properties settings;
	static {
		running=true;
		clients = new ArrayList<Client>();
		try {
			settings=new Properties();
			settings.load(new FileInputStream("/etc/wiy/wiy.conf"));// TODO: move ti def settings file
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public static void main(String[] args) {
		try {
			new Thread(new SocketServerController()).start();
		} catch (IOException e) {
			System.err.println("Socket server was not started.");
			e.printStackTrace();
		}
		new Thread((new Consol())).start();
		SpringApplication.run(AppApplication.class, args);
	}
}