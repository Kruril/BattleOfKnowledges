package connection.gestion.server;

import javafx.application.Platform;
import javafx.scene.control.Label;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;

public class ConnectionServer implements Runnable {

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public int id;
    private EventListenerServer listener;
    private boolean running = false;
    private HashMap<Integer, Label> hashMap;

    public ConnectionServer(Socket socket, int id, HashMap<Integer, Label> hashMap) {
        this.socket = socket;
        this.id = id;
        this.hashMap = hashMap;

        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            listener = new EventListenerServer();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        running = true;

        while(running) {
            try {
                Object data = in.readObject();
                if (data instanceof String) {
                    Platform.runLater(() -> {
                        hashMap.get(id + 1).setText(data.toString());
                    });
                }
                listener.received(data, this);
            } catch (IOException e) {

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
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

    public void sendObject(Object packet) throws IOException {
        out.writeObject(packet);
        out.flush();
    }
}
