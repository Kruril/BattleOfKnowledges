package enumeration;

public enum Difficulty {

    /**
     * Enum containing the different difficulty of the game.
     */

    EASY(4),
    NORMAL(3),
    MEDIUM(2),
    HARD(1);
    
    private int value;

    Difficulty(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
