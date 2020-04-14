package utils.GamePage;

import enumeration.Time;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Timer {

    private static Timeline timeTimer;
    private static Integer timeSecond;


    /**
     * launch the timer in a specific label
     * @param lblTimer Label where there is the timer
     */
    public static void startTimer(Label lblTimer) {
        if (timeTimer != null) {
            timeTimer.stop();
        }
        timeSecond = Time.TIMER_TIME.getValue();

        lblTimer.setText(timeSecond.toString());
        timeTimer = new Timeline();
        timeTimer.setCycleCount(Timeline.INDEFINITE);
        timeTimer.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1.),
                        event -> {
                            timeSecond--;
                            lblTimer.setText(timeSecond.toString());

                            if (timeSecond <= 0) timeTimer.stop();
                        })
        );
        timeTimer.playFromStart();
    }

    public static Timeline getTimeTimer() {
        return timeTimer;
    }
}
