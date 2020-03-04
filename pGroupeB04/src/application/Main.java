package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import view.ChoiceThemeGP;
import view.MainGamePage;
import view.MainPageSP;
import view.AdminLoginSP;
import view.SettingSP;

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
        ChoiceThemeGP choiceTheme = new ChoiceThemeGP();
        
        //Main Game Page
        MainGamePage mainGame = new MainGamePage();

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Battle of knowledges");
        primaryStage.getIcons().add(new Image("images/base/euro.png"));
        primaryStage.show();

        MainPageSP.getBtnSetting().setOnAction(event -> primaryStage.getScene().setRoot(settingPage));
        MainPageSP.getBtnSolo().setOnAction(event -> primaryStage.getScene().setRoot(choiceTheme));

        SettingSP.getBtnBack().setOnAction(event -> primaryStage.getScene().setRoot(menuPage));

        SettingSP.getBtnAdminConnect().setOnAction(event -> primaryStage.getScene().setRoot(adminPage));

        AdminLoginSP.getBtnBack().setOnAction(event -> primaryStage.getScene().setRoot(settingPage));
        ChoiceThemeGP.getValidate().setOnAction(event -> primaryStage.getScene().setRoot(mainGame));

//        scene.setOnKeyPressed(event -> {
//            if (event.getCode() == KeyCode.F12) {
//                primaryStage.setFullScreen(true);
//            }
//
//        });
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.F12) {
                primaryStage.setFullScreen(true);
            }
        });

    }


    public static void main(String[] args) {
        launch(args);
    }

}
