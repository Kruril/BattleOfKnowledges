package connection;


import connection.gestion.client.EventListenerClient;
import connection.gestion.packets.AddConnectionPacket;
import connection.gestion.packets.RemoveConnectionPacket;
import javafx.scene.control.Label;
import utils.user.Player;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.HashMap;

public class Client implements Runnable{

	private String host;
	private int port;

	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	private boolean running = false;
	private EventListenerClient listener;
	private HashMap<Integer, Label> hashMap;

	public Client(int port) {
		this.port = port;
		hashMap = new HashMap<>();
	}

	/**
	 * connection to the server.
	 * Initialization of the socket, creation of the outputstream and the inputstream
	 * to be able to communicate with the server.
	 * Start a new thread
	 */
	public void connect() {
		try {
			socket = new Socket(host,port);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			listener = new EventListenerClient();
			new Thread(this).start();

			AddConnectionPacket packet = new AddConnectionPacket();
			sendObject(packet);
			sendInfo();
		}catch(ConnectException e) {
			System.out.println("Unable to connect to the server");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Close the connection to the server.
	 * Will notify the server of his disconnection
	 */
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

	/**
	 * Manages the sending of information to the server.
	 * Sends any type of data.
	 * @param packet Object that we want to send
	 * @throws IOException throws an exception if the sending suffers a failure
	 */
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
			} catch (IOException ignored) {

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Send player name to server
	 * @throws IOException throws an exception if the sending suffers a failure
	 */
	public void sendInfo() throws IOException {
		if (socket == null) return;
		out.writeObject(Player.getName());
		out.flush();
	}

	public void addHashMap(HashMap hashMap) {
		this.hashMap.putAll(hashMap);
	}

	public HashMap<Integer, Label> getHashMap() {
		return hashMap;
	}

	public void addHost(String host) {
		this.host = host;
	}

	public Socket getSocket() {
		return socket;
	}
}
