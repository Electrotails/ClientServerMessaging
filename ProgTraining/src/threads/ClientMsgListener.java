package threads;
import java.io.DataInputStream;
import java.io.IOException;

public class ClientMsgListener implements Runnable {

	DataInputStream in;
	
	public ClientMsgListener(DataInputStream in){
		this.in = in;
	}
	
	@Override
	public void run() {
		try {
			System.out.println(in.readUTF());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
