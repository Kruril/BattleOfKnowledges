package enumeration;

public enum Time {

    /**
     * parameter for the time interval between the indices
     * and the total time of a game
     */

    TIMER_TIME(90),
    INTERVAL(3000);

    private int value;

    Time(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
