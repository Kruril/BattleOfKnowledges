package connection.gestion.server;

import application.Main;
import enumeration.Settings;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import utils.user.Player;
import view.game.ChoiceThemeBP;

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
    private Button btnStart;

    public ConnectionServer(Socket socket, int id, HashMap<Integer, Label> hashMap, Button btnStart) {
        this.socket = socket;
        this.id = id;
        this.hashMap = hashMap;
        this.btnStart = btnStart;

        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            listener = new EventListenerServer();
        }catch(IOException e) {
            e.printStackTrace();
        }
        btnStart.pressedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                Settings.CONTINUE_AFTER_4.setContinueGame(true);

                try {
                    out.writeObject("Start Game");
                    out.flush();
                    Main.switchScene(new ChoiceThemeBP());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void run() {
        running = true;

        while(running) {
            try {
                Object data = in.readObject();
                listener.received(data, this);
            } catch (IOException ignored) {

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

    public void sendInfo() throws IOException {
        if (socket == null) return;
        out.writeObject(Player.getUser());
        out.flush();
    }

    public HashMap<Integer, Label> getHashMap() {
        return hashMap;
    }
}
