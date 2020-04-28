package connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		ServerSocket serverSocket;
		Socket socket;
		try {
			
			/**
			 * Create a server and add client
			 */
			serverSocket=new ServerSocket(1342);
			socket=serverSocket.accept();
			
			
			socket.close();
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
