package enumeration;

public enum TypeGame {
	
    /**
     * Enum containing the type of the game
     */

	TYPEGAME("SOLO");
	
    private String value;

    TypeGame(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

	public void setValue(String value) {
		this.value = value;
	}

}
