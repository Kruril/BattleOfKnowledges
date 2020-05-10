package enumeration;

public enum Settings {

    /**
     * enum to know if we continue after 4 consecutive points or not
     */

    CONTINUE_AFTER_4(false);

    boolean continueGame;

    Settings(boolean value) {
        this.continueGame = value;
    }

    public boolean isContinueGame() {
        return continueGame;
    }

    public void setContinueGame(boolean continueGame) {
        this.continueGame = continueGame;
    }
}
