package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import view.*;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene;

        //Main menu page
        MainPageSP menuPage = new MainPageSP();
        scene = new Scene(menuPage, 1280.,720.);
        scene.getStylesheets().addAll("styles/btnStyles.css", "styles/labelStyles.css", "styles/BoxStyles.css");

        //Admin login Page
        AdminLoginSP adminPage = new AdminLoginSP();

        //Setting page
        SettingSP settingPage = new SettingSP();

        //Choice theme page
        ChoiceThemeBP choiceTheme = new ChoiceThemeBP();
        
        //Main Game Page
        GamePageBP mainGame = new GamePageBP();

        primaryStage.setScene(scene);
        primaryStage.setMinWidth(1080.);
        primaryStage.setMinHeight(720.);
        primaryStage.setTitle("Battle of knowledges");
        primaryStage.getIcons().add(new Image("images/base/euro.png"));

        primaryStage.show();

        MainPageSP.getBtnSetting().setOnAction(event -> primaryStage.getScene().setRoot(settingPage));
        MainPageSP.getBtnSolo().setOnAction(event -> primaryStage.getScene().setRoot(choiceTheme));

        SettingSP.getBtnBack().setOnAction(event -> primaryStage.getScene().setRoot(menuPage));

        SettingSP.getBtnAdminConnect().setOnAction(event -> primaryStage.getScene().setRoot(adminPage));

        AdminLoginSP.getBtnBack().setOnAction(event -> primaryStage.getScene().setRoot(settingPage));
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

}
