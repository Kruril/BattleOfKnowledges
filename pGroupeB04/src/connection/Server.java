package connection;

import application.Main;
import model.User;
import view.multiplayer.MultiplayerBP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {

	private final int MAX_CONNECTION = 4;
	private ServerSocket serverSocket;
	private Socket socket;
	private int nb_Connection;

//	public static void main(String[] args) {
//		ServerSocket serverSocket;
//		Socket socket;
//
//		try {
//
//			/**
//			 * Create a server and add client
//			 */
//			serverSocket = new ServerSocket(1342);
//
//			serverSocket.setSoTimeout(30000);
//
//			socket=serverSocket.accept();
//
//			System.out.println(socket.isConnected());
//			System.out.println(socket.getInetAddress());
//
//			PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
//			printWriter.println(new User("test", "test", "", 123));
//			printWriter.flush();
//
//
//			socket.close();
//			serverSocket.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//
//	}


	public Server()  {
		System.out.println("I'm server");
		try {
			serverSocket = new ServerSocket(1234);
		} catch (IOException e) {
			e.printStackTrace();
		}

		nb_Connection = 0;

	}

	public void create() throws IOException {
		if (socket != null || nb_Connection > MAX_CONNECTION) return;

		System.out.println("create");
		serverSocket.setSoTimeout(60000);
		socket = serverSocket.accept();
	}

	public void close() throws IOException {
		if (serverSocket != null) serverSocket.close();
	}

	public String PlayerConnected() throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		return bufferedReader.readLine();
	}
}
