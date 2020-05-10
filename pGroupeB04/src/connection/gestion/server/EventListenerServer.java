package connection.gestion.server;

import connection.Handler.ConnectionHandlerServer;
import connection.gestion.packets.AddConnectionPacket;
import connection.gestion.packets.RemoveConnectionPacket;
import javafx.application.Platform;
import javafx.scene.control.Label;
import utils.GamePage.Points;
import view.multiplayer.EndGameMulti;

import java.io.IOException;
import java.util.Iterator;

import application.Main;

public class EventListenerServer {
	
	public void received(Object p, ConnectionServer connection) throws IOException {
		if(p instanceof AddConnectionPacket) {
			AddConnectionPacket packet = (AddConnectionPacket) p;
			packet.id = connection.id;
			System.out.println("Connection: " + packet.id + " has connected");
			connection.sendObject("connected");
			connection.sendInfo();
		}
		else if(p instanceof RemoveConnectionPacket) {
			RemoveConnectionPacket packet = (RemoveConnectionPacket)p;
			System.out.println("Connection: " + packet.id + " has disconnected");

			Platform.runLater(() -> {
				connection.getHashMap().get(connection.id + 1).setText("Waiting player ...");
				connection.getHashMap().forEach((integer, label) ->  {
					if (integer > connection.id + 1 ) {
						String value = connection.getHashMap().get(integer).getText();
						connection.getHashMap().get(integer -1).setText(value);
						connection.getHashMap().get(integer).setText("Waiting player ...");
					}
				});
			});
			ConnectionHandlerServer.connections.get(packet.id).close();
			ConnectionHandlerServer.connections.remove(packet.id);

		}
		else if (p instanceof String) {
			String value = (String) p;
			Platform.runLater(() -> connection.getHashMap().get(connection.id + 1).setText(value));
		}
		else if (p instanceof Points) {
			Points points = (Points) p;
			for (ConnectionServer connectionServer : ConnectionHandlerServer.connections.values()) {
				if(connectionServer != connection) {
					connectionServer.sendObject(points);
				}
			}
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
