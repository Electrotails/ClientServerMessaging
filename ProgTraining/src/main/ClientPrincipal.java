package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import threads.ClientMsgListener;

public class ClientPrincipal {

	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String msg;
		Socket clientSocket;
		DataOutputStream out;
		
		try {
			clientSocket = new Socket("127.0.0.1", 9999);
			out = new DataOutputStream(clientSocket.getOutputStream());
			new Thread(new ClientMsgListener
					(new DataInputStream(clientSocket.getInputStream()))).start();;
			System.out.println("Conectado!");
			while(true){
				msg = sc.nextLine();
				System.out.println("Você disse: " + msg);
				out.writeUTF(msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
