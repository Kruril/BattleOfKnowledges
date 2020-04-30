package connection.gestion.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionServer implements Runnable{

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public int id;
    private EventListenerServer listener;
    private boolean running = false;

    public ConnectionServer(Socket socket, int id) {
        this.socket = socket;
        this.id = id;

        try {
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            listener = new EventListenerServer();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        running = true;

        while(running) {
            Object data = in.lines();
            listener.received(data, this);
        }
    }

    public void close() {
        try {
            running = false;
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
}
