package connection;


import connection.Handler.ConnectionHandlerServer;
import connection.gestion.server.ConnectionServer;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;

public class Server implements Runnable{

	private int port;
	private ServerSocket serverSocket;
	private boolean running = false;
	private int id = 0;
	private Socket socket;
	private HashMap<Integer, Label> hashMap;
	private Button btnstart;

	public Server(int port) {
		this.port = port;

		try {
			serverSocket = new ServerSocket(port);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void start() {
		new Thread(this).start();
	}

	@Override
	public void run() {
		running = true;
		System.out.println("Server started on port: " + port);

		while(running) {
			try {
				socket = serverSocket.accept();
				initSocket(socket);
			}catch(IOException e) {
				shutdown();
			}
		}
	}

	/**
	 * initializes the connection to the server
	 * and creates a new thread
	 * @param socket socket containing the client connection
	 */
	private void initSocket(Socket socket) {
		ConnectionServer connection = new ConnectionServer(socket,id, hashMap, btnstart);
		ConnectionHandlerServer.connections.put(id,connection);
		new Thread(connection).start();
		id++;
	}

	/**
	 * close server sockets
	 */
	public void shutdown() {
		running = false;

		try {
			if (socket != null) {
				socket.close();
			}
			serverSocket.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void setProperty(HashMap<Integer, Label> hashMap, Button button) {
		this.btnstart = button;
		this.hashMap = hashMap;
	}
	
	public Collection<ConnectionServer> getConnection() {
		return ConnectionHandlerServer.connections.values();
	}
	
}
