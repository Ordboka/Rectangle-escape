package ord.boka.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class HostThread extends Thread{
	private Socket socket = null;
	public HostThread(Socket socket) {
		//Tror denne starter tråden fordi den kaller konstruktøren i Thread klassen
		//Gir tråden samme navn som klassen
		super("HostThread");
		this.socket = socket;
	}
	@Override
	public void run() {
		//Lager en try blokk. Når den kjører lager den en bufferedreader som automatisk lukkes når blokken er ferdig.
		//Ganske magisk.
		String input = null;
		try( BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));){
			while((input = in.readLine())!=null){
				System.out.println(input);
				if(input.equals("end")){
					break;
				}
			}
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
