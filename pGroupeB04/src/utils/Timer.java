package utils;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Timer {

    private static final int STARTTIME = 90;
    private static Timeline timeline;
    private static Integer timeSeconds;

    public static void startTime(Label lblTimer) {
        if (timeline != null) {
            timeline.stop();
        }

        timeSeconds = STARTTIME;
        lblTimer.setText(timeSeconds.toString());
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1.),
                        event -> {
                            timeSeconds--;
                            lblTimer.setText(timeSeconds.toString());
                            if (timeSeconds <= 0) {
                                timeline.stop();
                            }
                        })
        );
        timeline.playFromStart();
    }
}
