package threads;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import main.ServerPrincipal;

public class ServerMsgListener implements Runnable {
	
	Socket clientSocket;
	String user;
	DataOutputStream out;
	DataInputStream in;
	
	public ServerMsgListener(Socket socket, String user) throws IOException{
		clientSocket = socket;
		this.user = user;
		
		out = new DataOutputStream(socket.getOutputStream());
		in = new DataInputStream(socket.getInputStream());
	}
	
	@Override
	public void run() {
		while(true){
			try {
				ServerPrincipal.printMsg(user, in.readUTF());
			} catch (IOException e) {
				System.out.println(user + " desconectou!");
				break;
			}
		}
	}
	
	public void sendMsg(String structuredMsg){
		try {
			out.writeUTF(structuredMsg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
