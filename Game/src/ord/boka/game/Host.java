package ord.boka.game;

import java.io.IOException;
import java.net.ServerSocket;

public class Host {
	public static final int PORT = 27182;
	
	public Host(){
		int count = 0;
		//Setter opp en socket som lytter etter nye klienter som pr�ver � koble seg opp
		try(ServerSocket serverSocket = new ServerSocket(PORT)){
			while(count!=2){
				//Lager en ny tr�d som h�ndterer den nye klienten.
				new HostThread(serverSocket.accept()).start();
				System.out.println("It works");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
