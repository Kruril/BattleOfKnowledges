package view;

import application.Main;
import enumeration.TypeGame;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import utils.utility.BackgroundLoader;
import utils.controler.JsonManager;
import utils.utility.Resolution;
import view.game.ChoiceThemeBP;
import view.multiplayer.MultiplayerBP;
import utils.user.Player;
import view.user.UserLoginSP;

public class MainPageSP extends StackPane {

    private Button btnOff, btnMulti,btnSetting,btnSolo;
    private ImageView ivTitle;

    public MainPageSP() {
        //BACKGROUND
        this.setBackground(BackgroundLoader.builderBackGround());

        StackPane.setAlignment(getBtnSetting(), Pos.TOP_LEFT);
        StackPane.setMargin(getBtnSetting(), new Insets(10));

        StackPane.setAlignment(getBtnOff(), Pos.TOP_RIGHT);
        StackPane.setMargin(getBtnOff(), new Insets(10));

        StackPane.setAlignment(getIvTitle(),Pos.CENTER);
        StackPane.setMargin(getIvTitle(), new Insets(-300,0,0,0));

        StackPane.setAlignment(getBtnSolo(),Pos.CENTER);
        StackPane.setMargin(getBtnSolo(), new Insets(100,0,0,0));

        StackPane.setAlignment(getBtnMulti(),Pos.CENTER);
        StackPane.setMargin(getBtnMulti(), new Insets(500,0,0,0));

        this.getChildren().addAll(getBtnSetting(), getBtnOff(),getIvTitle(), getBtnSolo(),getBtnMulti());

    }

    public Button getBtnSetting() {
        if (btnSetting == null) {
            ImageView setting = new ImageView(new Image("asset/images/icon/settings.png", 50,
                    50,true,true));
            btnSetting = new Button("" , setting);
            btnSetting.getStyleClass().add("buttonTransparence");

            btnSetting.setOnAction(event -> Main.switchScene(new SettingSP()));
        }
        return btnSetting;
    }

    public Button getBtnOff() {
        if (btnOff == null) {
            ImageView powerOff = new ImageView(new Image("asset/images/icon/turn-off.png",50,
                    50,true,true));
            btnOff = new Button("", powerOff);
            btnOff.getStyleClass().add("buttonTransparence");
            btnOff.setOnAction(event -> {
                new Resolution().toJson(Main.getStage().getWidth(),Main.getStage().getHeight());
                Main.getStage().close();
                JsonManager.getDeck().toJson();
                Player.getUser().toJson();
            });
        }
        return btnOff;
    }

    public Button getBtnSolo() {
        if (btnSolo == null) {
            btnSolo = new Button("SinglePlayer");
            btnSolo.setBackground(BackgroundLoader.buildBtnBackGround());
            btnSolo.getStyleClass().add("buttonBasic");
            btnSolo.setId("big-button");
            btnSolo.setOnAction(event -> {
            	Main.switchScene(new ChoiceThemeBP());
            	TypeGame.TYPEGAME.setValue("SOLO");
            });
        }
        return btnSolo;
    }

    public Button getBtnMulti() {
        if (btnMulti == null) {
            btnMulti = new Button("MultiPlayer");
            btnMulti.setBackground(BackgroundLoader.buildBtnBackGround());
            btnMulti.getStyleClass().add("buttonBasic");
            btnMulti.setId("big-button");
            btnMulti.setOnAction(event -> Main.switchScene(new MultiplayerBP()));
        }
        return btnMulti;
    }

    public ImageView getIvTitle() {
        if (ivTitle == null) {
            ivTitle = new ImageView(new Image("asset/images/title/Title.png"));
        }
        return ivTitle;
    }
}
