package connection;


import connection.gestion.client.EventListenerClient;
import connection.gestion.client.packets.AddConnectionPacket;
import connection.gestion.client.packets.RemoveConnectionPacket;
import utils.user.Player;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;

public class Client implements Runnable{

	private String host;
	private int port;

	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	private boolean running = false;
	private EventListenerClient listener;

	public Client(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void connect() {
		try {
			socket = new Socket(host,port);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
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

	public void sendObject(Object packet) throws IOException {
		out.writeObject(packet);
		out.flush();
	}

	@Override
	public void run() {
		running = true;

		while(running) {
			try {
				Object data = in.readObject();
				listener.received(data);
			} catch (IOException e) {

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public void sendInfo() throws IOException {
		if (socket == null) return;
		out.writeObject(Player.getName());
		out.flush();
	}
}
