package utils.GamePage;

import java.io.Serializable;

public class Points implements Serializable{

	/**
	 * Point of a partie it use in MultiPlayer
	 */
	
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
