package connection.gestion.client;


import application.Main;
import connection.Client;
import connection.Handler.ConnectionHandlerClient;
import connection.gestion.packets.AddConnectionPacket;
import connection.gestion.packets.RemoveConnectionPacket;
import javafx.application.Platform;
import model.User;
import view.game.ChoiceThemeBP;
import view.multiplayer.MultiPlayerRoomClient;

public class EventListenerClient {
	
	public void received(Object p, Client client) {
		if(p instanceof AddConnectionPacket) {
			AddConnectionPacket packet = (AddConnectionPacket)p;
			ConnectionHandlerClient.connections.put(packet.id,new ConnectionClient(packet.id));
			System.out.println(packet.id + " has connected");
		}
		else if(p instanceof RemoveConnectionPacket) {
			RemoveConnectionPacket packet = (RemoveConnectionPacket)p;
			System.out.println(packet.id + " has disconnected");
			ConnectionHandlerClient.connections.remove(packet.id);
		}
		else if (p instanceof String) {
			String value = (String) p;
			if (value.equals("connected")) {
				Main.switchScene(new MultiPlayerRoomClient(client));
			}
			else if (value.equals("Start Game")) {
				Main.switchScene(new ChoiceThemeBP());
			}
		}
		else if (p instanceof User) {
			User user = (User) p;
			Platform.runLater(() -> {
				if (client.getHashMap().get(1).getText().equals("Waiting player ..."))
					client.getHashMap().get(1).setText(user.getPseudo());
				else if (client.getHashMap().get(2).getText().equals("Waiting player ..."))
					client.getHashMap().get(2).setText(user.getPseudo());
				else if (client.getHashMap().get(3).getText().equals("Waiting player ..."))
					client.getHashMap().get(3).setText(user.getPseudo());
			});
		}
	}

}
