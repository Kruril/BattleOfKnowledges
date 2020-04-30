package connection.gestion.server;

import connection.Handler.ConnectionHandlerServer;
import connection.gestion.server.packets.AddConnectionPacket;
import connection.gestion.server.packets.RemoveConnectionPacket;

public class EventListenerServer {
	
	public void received(Object p, ConnectionServer connection) {
		if(p instanceof AddConnectionPacket) {
			AddConnectionPacket packet = (AddConnectionPacket)p;
			packet.id = connection.id;
			for(int i = 0; i< ConnectionHandlerServer.connections.size(); i++) {
				ConnectionServer c = ConnectionHandlerServer.connections.get(i);
				if(c != connection) {
					c.sendObject(packet);
				}
			}
			
		}else if(p instanceof RemoveConnectionPacket) {
			RemoveConnectionPacket packet = (RemoveConnectionPacket)p;
			System.out.println("Connection: " + packet.id + " has disconnected");
			ConnectionHandlerServer.connections.get(packet.id).close();
			ConnectionHandlerServer.connections.remove(packet.id);
		}
	}

}
