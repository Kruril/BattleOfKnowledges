package utils.audio;

import application.Main;
import enumeration.Path;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URISyntaxException;

public class AudioPlayer {

    private static MediaPlayer player;
    private static String uri;

    public static void loadFrom(Path path) throws URISyntaxException {
        uri = Main.class.getResource(path.getPath()).toURI().toString();

        if (uri != null) {
            player = new MediaPlayer(new Media(uri));
        }
    }

    public static void play() throws URISyntaxException {
        if (player == null) {
            // load default music
            loadFrom(Path.MAIN_SOUND);
        }
        player.play();
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.setMute(true);
    }

    public static void stop() {
        if (player != null) player.stop();
    }

    public static void volume(double value) {
        player.setVolume(value);
    }

    public static MediaPlayer getPlayer() {
        return player;
    }
}
