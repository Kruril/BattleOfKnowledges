package connection;


import application.Main;
import connection.Handler.ConnectionHandlerServer;
import connection.gestion.server.ConnectionServer;
import enumeration.Settings;
import javafx.scene.control.Label;
import view.game.ChoiceThemeBP;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server implements Runnable{

	private int port;
	private ServerSocket serverSocket;
	private boolean running = false;
	private int id = 0;
	private Socket socket;
	private HashMap<Integer, Label> hashMap;

	public Server(int port, HashMap<Integer, Label> hashMap) {
		this.port = port;
		this.hashMap = hashMap;

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

	private void initSocket(Socket socket) {
		ConnectionServer connection = new ConnectionServer(socket,id, hashMap);
		ConnectionHandlerServer.connections.put(id,connection);
		new Thread(connection).start();
		id++;
	}

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

	public void startGame() throws IOException {
		Settings.CONTINUE_AFTER_4.setContinueGame(true);
//		Main.switchScene(new ChoiceThemeBP());
		ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
		out.writeObject("Start Game");
		out.flush();
	}

}
