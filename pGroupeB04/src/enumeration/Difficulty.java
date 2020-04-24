package enumeration;

public enum Difficulty {

    /**
     * Enum containing the different difficulty of the game.
     */

    EASY(4),
    NORMAL(3),
    MEDIUM(2),
    HARD(1);
    
    private int valeur;

    Difficulty(int valeur) {
        this.valeur = valeur;
    }

    public int getValeur() {
        return valeur;
    }
}
