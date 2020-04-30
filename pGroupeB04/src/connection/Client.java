package connection;


import connection.gestion.client.EventListenerClient;
import connection.gestion.client.packets.RemoveConnectionPacket;
import utils.user.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

public class Client implements Runnable{

	private String host;
	private int port;

	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;

	private boolean running = false;
	private EventListenerClient listener;

	public Client(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void connect() {
		try {
			socket = new Socket(host,port);
			out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			listener = new EventListenerClient();
			new Thread(this).start();
		}catch(ConnectException e) {
			System.out.println("Unable to connect to the server");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			running = false;
			RemoveConnectionPacket packet = new RemoveConnectionPacket();
			sendObject(packet);
			in.close();
			out.close();
			socket.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void sendObject(Object packet) {
		out.println(packet);
		out.flush();
	}

	@Override
	public void run() {
		running = true;

		while(running) {
			Object data = in.lines();
			listener.received(data);
		}
	}

	public void sendInfo() {
		if (socket == null) return;
		out.println(Player.getName());
		out.flush();
	}
}
