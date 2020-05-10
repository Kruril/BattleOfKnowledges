package utils.GamePage;

import java.io.Serializable;

public class Points implements Serializable{
	
	private int points;
	private String nameUser;
	
	public Points(int ptsReceived, String nameUser) {	
		this.points = ptsReceived;
		this.nameUser = nameUser;
	}

	public int getPoints() {
		return points;
	}

	public String getNameUser() {
		return nameUser;
	}
	
}
