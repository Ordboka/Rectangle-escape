package ord.boka.game;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	Socket socket;
	
	public Client(String hostName, int port) {
		try {
			//Setter opp en socket med angitt port og ip(hostName)
			socket = new Socket(hostName, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void updatePositionToHost(int x, int y){
		
	}
}
