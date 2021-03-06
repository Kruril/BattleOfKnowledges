package application;

import connection.Client;
import connection.Server;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import utils.audio.AudioPlayer;
import utils.audio.Sound;
import utils.controler.JsonManager;
import utils.utility.Resolution;
import utils.controler.TrashManager;
import utils.user.Player;
import view.user.UserLoginSP;

/**
 * Battle of knowledge is a game based on the principle of 4 in a row the famous game show Question for a champion.
 *
 * @author Charlier Guillaume, Gailliez Valentin, Trempont Mathieu
 * @version 1.0
 */
public class Main extends Application {

    private static Stage stage;
    private static Server server;
    private static Client client;

    /**
     * method that changes the displayed scene
     * @param switchScene new scene to display
     */
    public static void switchScene(Parent switchScene) {
        stage.getScene().setRoot(switchScene);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        JsonManager.themeFromDeck();
        TrashManager.buildTrash();
        stage = primaryStage;
        Scene scene;
        Resolution resolution = new Resolution();
        resolution.fromJson();
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        
        Sound sound=new Sound();
        sound.fromJson();

        scene = new Scene(new UserLoginSP());
        scene.getStylesheets().addAll("styles/btnStyles.css", "styles/labelStyles.css", "styles/BoxStyles.css",
                "styles/TableStyles.css", "styles/dialogStyle.css");

        primaryStage.setScene(scene);

        loadSizeScreen(resolution);

        primaryStage.setMinWidth(1080.);
        primaryStage.setMinHeight(720.);
        primaryStage.setTitle("Battle of knowledge");
        primaryStage.getIcons().add(new Image("asset/images/icon/euro.png"));

        primaryStage.show();

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F12 && event.isControlDown()) {
                primaryStage.setFullScreen(!primaryStage.isFullScreen());
            }
        });

        primaryStage.setOnCloseRequest(event -> {
        	
        	resolution.toJson(primaryStage.getWidth(),primaryStage.getHeight());
             
            JsonManager.getDeck().toJson();
            if (scene.getRoot().getClass() != UserLoginSP.class ) Player.getUser().toJson();

        });

        AudioPlayer.play();
        AudioPlayer.volume(sound.getVolume());
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getStage() {
        return stage;
    }

    public static Server getServer() {
        if (server == null) {
            server = new Server(1234);
        }
        return server;
    }

    public static Client getClient() {
        if (client == null) {
            client = new Client(1234);
        }
        return client;
    }

    /**
     * method that will configure the size of the scene when loading the game
     * @param resolution object coming from the json containing the size of previous loading
     */
    public void loadSizeScreen(Resolution resolution) {
        if (resolution.isFullscreen())
            stage.setFullScreen(true);
        else if (resolution.isMaximize())
            stage.setMaximized(true);
        else {
            stage.setWidth(resolution.getWidth());
            stage.setHeight(resolution.getHeight());
        }
        stage.setX(resolution.getX());
        stage.setY(resolution.getY());
    }
}
