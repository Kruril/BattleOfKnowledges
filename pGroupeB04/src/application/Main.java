package application;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import utils.Resolution;
import view.*;

public class Main extends Application {

    private static Stage stage;

    public static void switchScene(Parent switchScene) {
        stage.getScene().setRoot(switchScene);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;
        Scene scene;
        Resolution resolution = new Resolution();
        resolution.fromJson();

        //Main menu page
        MainPageSP menuPage = new MainPageSP();
        scene = new Scene(menuPage);
        scene.getStylesheets().addAll("styles/btnStyles.css", "styles/labelStyles.css", "styles/BoxStyles.css");
        
        //Main Game Page
        GamePageBP mainGame = new GamePageBP();

        primaryStage.setScene(scene);

        loadSizeScreen(resolution);

        primaryStage.setMinWidth(1080.);
        primaryStage.setMinHeight(720.);
        primaryStage.setTitle("Battle of knowledges");
        primaryStage.getIcons().add(new Image("images/base/euro.png"));

        primaryStage.show();

        ChoiceThemeBP.getValidate().setOnAction(event -> primaryStage.getScene().setRoot(mainGame));

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F12) {
                primaryStage.setFullScreen(true);
            }
        });

        primaryStage.setOnCloseRequest(event -> resolution.toJson(primaryStage.getWidth(),primaryStage.getHeight()));

    }


    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getStage() {
        return stage;
    }

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
