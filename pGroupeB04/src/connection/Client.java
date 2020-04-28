package connection;

import view.user.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	private Socket socket;

//	public static void main(String[] args) {
//		try {
//
//			Socket socket = new Socket(InetAddress.getLocalHost().getHostAddress(),1342);
//			System.out.println(socket);
//
//			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//			System.out.println(bufferedReader.readLine());
//
//
//			socket.close();
//			bufferedReader.close();
//
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}


	public Client() {
	}

	public void connection() throws IOException {
		if (socket != null) return;
		System.out.println("I'm client");
		socket = new Socket(InetAddress.getLocalHost().getHostAddress(), 1234);
		System.out.println("connected");

	}

	public void sendInfo() throws IOException {
		if (socket == null) return;
		PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
		printWriter.println(Player.getName());
		printWriter.flush();
	}

	public void close() throws IOException {
		socket.close();
	}
}
