package connection.gestion.server;

import java.io.IOException;
import java.util.Iterator;

import application.Main;
import connection.Handler.ConnectionHandlerServer;
import connection.gestion.packets.AddConnectionPacket;
import connection.gestion.packets.RemoveConnectionPacket;
import javafx.application.Platform;
import javafx.scene.control.Label;
import model.User;
import model.UserBuilder;
import utils.GamePage.Points;
import view.multiplayer.EndGameMulti;

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
			User user=new UserBuilder()
					.pseudo(value)
					.build();
			for(ConnectionServer c:ConnectionHandlerServer.connections.values()) {
				if(c!=connection) {
					c.sendObject(user);
				}
			}
		}
		else if (p instanceof Points) {
			Points points = (Points) p;
			ConnectionHandlerServer.connections.forEach((key,connectionServer)->{
				if(connection!=connectionServer) {
					try {
						connectionServer.sendObject(points);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
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
