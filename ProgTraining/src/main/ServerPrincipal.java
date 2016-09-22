package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import threads.ServerMsgListener;

public class ServerPrincipal {
	
	static ArrayList<ServerMsgListener> clients;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		clients = new ArrayList<ServerMsgListener>();
		
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(9999);
			while(true){
				ServerMsgListener client = new ServerMsgListener(serverSocket.accept(), "Test");
				new Thread(client).start();
				clients.add(client);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void printMsg(String user, String msg){
		System.out.println("DEBUG: " + user + " disse: " + msg);
		for(ServerMsgListener client : clients) {
			if(client.getUser() != user) {
				client.sendMsg(user + " disse: " + msg);		
			}
		}
	}
}
