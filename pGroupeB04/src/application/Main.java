package application;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
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

        //Main menu page
        MainPageSP menuPage = new MainPageSP();
        scene = new Scene(menuPage, 1280.,720.);
        scene.getStylesheets().addAll("styles/btnStyles.css", "styles/labelStyles.css", "styles/BoxStyles.css");
        
        //Main Game Page
        GamePageBP mainGame = new GamePageBP();

        primaryStage.setScene(scene);
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

    }


    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getStage() {
        return stage;
    }
}
