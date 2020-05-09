package view.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import application.Main;
import enumeration.TypeGame;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.utility.BackgroundLoader;
import utils.controler.JsonManager;
import view.MainPageSP;
import view.multiplayer.EndGameMulti;
import view.multiplayer.MultiPlayerRoomClient;
import view.multiplayer.MultiPlayerRoomServer;


public class ChoiceThemeBP extends BorderPane {
    private ImageView ivTitle;
    private Button btnThm1, btnThm2, btnThm3, btnThm4, btnBack;

    private List<String> themes = new ArrayList<>();

    public ChoiceThemeBP() {
        randomTheme();

        this.setBackground(BackgroundLoader.builderBackGround());

        HBox hTop = new HBox();
        hTop.getChildren().addAll(getIvTitle());
        hTop.setAlignment(Pos.CENTER);
        hTop.setPadding(new Insets(50, 5, 10, 5));
        hTop.setPrefSize(150., 50.);
        this.setTop(hTop);

        HBox hBottom = new HBox();
        hBottom.getChildren().addAll(getBtnBack());
        hBottom.setAlignment(Pos.CENTER);
        hBottom.setPadding(new Insets(10, 5, 50, 5));
        hBottom.setPrefSize(150., 25.);
        this.setBottom(hBottom);

        VBox vLeft = new VBox();
        vLeft.getChildren().addAll(getBtnThm1(), getBtnThm3());
        vLeft.setSpacing(100.);
        vLeft.setAlignment(Pos.CENTER);

        VBox vRight = new VBox();
        vRight.getChildren().addAll(getBtnThm2(), getBtnThm4());
        vRight.setSpacing(100.);
        vRight.setAlignment(Pos.CENTER);

        HBox hTheme = new HBox();
        hTheme.getChildren().addAll(vLeft, vRight);
        hTheme.setSpacing(100);
        hTheme.setAlignment(Pos.CENTER);
        this.setCenter(hTheme);
    }

    public Button getBtnThm1() {
        if (btnThm1 == null) {
            btnThm1 = new Button(themes.get(0));
            btnThm1.setBackground(BackgroundLoader.buildBtnBackGround());
            btnThm1.getStyleClass().add("buttonBasic");
            btnThm1.setId("big-button");
            btnThm1.setOnAction(event -> Main.switchScene(new GamePageSP(btnThm1.getText())));
        }
        return btnThm1;
    }

    public Button getBtnThm2() {
        if (btnThm2 == null) {
            btnThm2 = new Button(themes.get(1));
            btnThm2.setBackground(BackgroundLoader.buildBtnBackGround());
            btnThm2.getStyleClass().add("buttonBasic");
            btnThm2.setId("big-button");
            
            btnThm2.setOnAction(event -> Main.switchScene(new GamePageSP(btnThm2.getText())));

        }
        return btnThm2;
    }

    public Button getBtnThm3() {
        if (btnThm3 == null) {
            btnThm3 = new Button(themes.get(2));
            btnThm3.setBackground(BackgroundLoader.buildBtnBackGround());
            btnThm3.getStyleClass().add("buttonBasic");
            btnThm3.setId("big-button");

            btnThm3.setOnAction(event -> Main.switchScene(new GamePageSP(btnThm3.getText())));
        }
        return btnThm3;
    }

    public Button getBtnThm4() {
        if (btnThm4 == null) {
            btnThm4 = new Button(themes.get(3));
            btnThm4.setBackground(BackgroundLoader.buildBtnBackGround());
            btnThm4.getStyleClass().add("buttonBasic");
            btnThm4.setId("big-button");

            btnThm4.setOnAction(event -> Main.switchScene(new GamePageSP(btnThm4.getText())));
        }
        return btnThm4;
    }

    public Button getBtnBack() {
        if (btnBack == null) {
            btnBack = new Button("Back");
            btnBack.setBackground(BackgroundLoader.buildBtnBackGround());
            btnBack.getStyleClass().add("buttonBasic");
            btnBack.setId("medium-button");
            btnBack.setOnAction(event -> {
            	switch (TypeGame.TYPEGAME.getValue()) {
            	case "SERVER" : 
            		Main.switchScene(new MultiPlayerRoomServer());
            		break;
            	case "CLIENT" :
            		Main.switchScene(new MultiPlayerRoomClient());
            		break;
            	default :
            		Main.switchScene(new MainPageSP());
            	}
            });
        }
        return btnBack;
    }

    public ImageView getIvTitle() {
        if (ivTitle == null) {
            ivTitle = new ImageView("asset/images/title/ChooseThemeLabel.png");
        }
        return ivTitle;
    }
   
    
    /**
     * every game
     * we attribute a random theme
     * to the buttons
     */
    public void randomTheme() {
        Random rand = new Random();
        String theme;
        for (int index = 1; index <= 4; index++) {
            while (true) {
                theme = JsonManager.getThemes().get(rand.nextInt(JsonManager.getThemes().size()));
                if (!(themes.contains(theme)) && (JsonManager.choiceTheme(theme).size()>=10)) {
                    themes.add(theme);
                    break;
                }
            }
        }
    }
}