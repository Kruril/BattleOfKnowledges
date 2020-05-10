package connection.gestion.server;

import application.Main;
import enumeration.Settings;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import utils.GamePage.RandomTheme;
import utils.controler.ParameterHost;
import utils.user.Player;
import view.game.GamePageSP;

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
                ParameterHost pHost=new ParameterHost();
                pHost.loadQuestions(RandomTheme.randomTheme());
                try {
                    sendObject(pHost);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Main.switchScene(new GamePageSP(pHost.getQuestions()));
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

    /**
     * Close all elements of the connection
     */
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

    /**
     * sends an object passed in parameter to the server
     * to which it is connected.
     * @param packet object that you will send
     * @throws IOException throws an exception if the object is
     * not serializable or if it has an error in sending
     */
    public void sendObject(Object packet) throws IOException {
        out.writeObject(packet);
        out.flush();
    }

    /**
     * send user information
     * @throws IOException throws an exception if the object is
     * not serializable or if it has an error in sending
     */
    public void sendInfo() throws IOException {
        if (socket == null) return;
        out.writeObject(Player.getUser());
        out.flush();
    }

    public HashMap<Integer, Label> getHashMap() {
        return hashMap;
    }
}
