package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import view.WindowMainPage;

public class MainGame extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            WindowMainPage fenetre = new WindowMainPage();
            Scene scene = new Scene(fenetre, 1280,720);
            scene.getStylesheets().add("styles/btnStyles.css");
            primaryStage.setScene(scene);
            primaryStage.setTitle("Going for gold");
            primaryStage.getIcons().add(new Image("images/euro.png"));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
