package ord.boka.game;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class Host {
	public static final int PORT = 27182;
	ArrayList<InetAddress> clients = new ArrayList<>();
	
	public void addClient(InetAddress ip){
		byte[] sendData = new byte[1024];
	    byte[] receiveData = new byte[1024];
		//Setter opp en socket og binder den til porten
		DatagramSocket clientSocket = new DatagramSocket(serverPort);
		//Setter timeout
		clientSocket.setSoTimeout(timeOut);
	}
}
