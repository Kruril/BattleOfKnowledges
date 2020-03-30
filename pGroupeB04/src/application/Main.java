package application;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import utils.JsonManager;
import utils.Resolution;
import view.*;

public class Main extends Application {

    private static Stage stage;

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
        stage = primaryStage;
        Scene scene;
        Resolution resolution = new Resolution();
        resolution.fromJson();

        //Main menu page
        MainPageSP menuPage = new MainPageSP();
        scene = new Scene(menuPage);
        scene.getStylesheets().addAll("styles/btnStyles.css", "styles/labelStyles.css", "styles/BoxStyles.css",
                "styles/TableStyles.css");

        primaryStage.setScene(scene);

        loadSizeScreen(resolution);

        primaryStage.setMinWidth(1080.);
        primaryStage.setMinHeight(720.);
        primaryStage.setTitle("Battle of knowledges");
        primaryStage.getIcons().add(new Image("images/base/euro.png"));

        primaryStage.show();

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F12) {
                primaryStage.setFullScreen(true);
            }
        });

        primaryStage.setOnCloseRequest(event -> {
            resolution.toJson(primaryStage.getWidth(),primaryStage.getHeight());
            JsonManager.getDeck().toJson();
        });

    }


    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getStage() {
        return stage;
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
    }
}
