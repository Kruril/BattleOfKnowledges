package utils.audio;

import application.Main;
import enumeration.Path;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URISyntaxException;

public class AudioPlayer {

    private static MediaPlayer player;
    private static String uri;

    /**
     * loads the music to listen to receives the song path as a parameter.
     * @param path path to music
     * @throws URISyntaxException Throws an exception if it cannot convert the received path
     */
    public static void loadFrom(Path path) throws URISyntaxException {
        uri = Main.class.getResource(path.getPath()).toURI().toString();

        if (uri != null) {
            player = new MediaPlayer(new Media(uri));
        }
    }

    /**
     * plays the selected music if no music has been loaded previously
     * then it loads the music by default.
     * @throws URISyntaxException Throws an exception if it cannot convert the received path
     */
    public static void play() throws URISyntaxException {
        if (player == null) {
            // load default music
            loadFrom(Path.MAIN_SOUND);
        }
        player.play();
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.setMute(false);
    }

    /**
     * stop the music that is playing
     */
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
