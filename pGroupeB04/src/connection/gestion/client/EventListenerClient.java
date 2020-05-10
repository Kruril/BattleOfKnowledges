package connection.gestion.client;


import java.util.Iterator;

import application.Main;
import connection.Handler.ConnectionHandlerClient;
import connection.gestion.packets.AddConnectionPacket;
import connection.gestion.packets.RemoveConnectionPacket;
import enumeration.Settings;
import enumeration.Time;
import javafx.application.Platform;
import javafx.scene.control.Label;
import model.User;
import utils.GamePage.Points;
import utils.controler.ParameterHost;
import view.game.GamePageSP;
import view.multiplayer.EndGameMulti;
import view.multiplayer.MultiPlayerRoomClient;

public class EventListenerClient {

    public void received(Object p) {
        System.out.println(p.getClass());
        if (p instanceof AddConnectionPacket) {
            AddConnectionPacket packet = (AddConnectionPacket) p;
            ConnectionHandlerClient.connections.put(packet.id, new ConnectionClient(packet.id));
            System.out.println(packet.id + " has connected");
        } 
        else if (p instanceof RemoveConnectionPacket) {
            RemoveConnectionPacket packet = (RemoveConnectionPacket) p;
            System.out.println(packet.id + " has disconnected");
            ConnectionHandlerClient.connections.remove(packet.id);
        } 
        else if (p instanceof String) {
            String value = (String) p;
            if (value.equals("connected")) {
                Main.switchScene(new MultiPlayerRoomClient());
            }
        } 
        else if (p instanceof User) {
            User user = (User) p;
            Platform.runLater(() -> {
                if (Main.getClient().getHashMap().get(1).getText().equals("Waiting player ..."))
                    Main.getClient().getHashMap().get(1).setText(user.getPseudo());
                else if (Main.getClient().getHashMap().get(2).getText().equals("Waiting player ..."))
                    Main.getClient().getHashMap().get(2).setText(user.getPseudo());
                else if (Main.getClient().getHashMap().get(3).getText().equals("Waiting player ..."))
                    Main.getClient().getHashMap().get(3).setText(user.getPseudo());
            });
        } 
        else if (p instanceof ParameterHost) {
            ParameterHost pHost = (ParameterHost) p;
            Time.TIMER_TIME.setValue(pHost.getTime());
            Time.INTERVAL.setValue(pHost.getInterval());
            Settings.CONTINUE_AFTER_4.setContinueGame(pHost.isCONTINUE());
            Platform.runLater(() -> Main.switchScene(new GamePageSP(pHost.getQuestions())));

        } 
        else if (p instanceof Points) {
			Points points = (Points) p;
			Platform.runLater(() -> {
				if (Main.getStage().getScene().getRoot() instanceof EndGameMulti) {
					EndGameMulti endGame = (EndGameMulti) Main.getStage().getScene().getRoot();
					Iterator it = endGame.getListLbl().iterator();
					while (it.hasNext()) {
						Label label = (Label) it.next();
						if (label.getText().equals("Waiting player...")) {
							label.setText(points.getNameUser() + " " + points.getPoints());
							break;
						}
					}
				}
			});
		}
    }

}
