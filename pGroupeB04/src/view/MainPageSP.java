package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.BackGroundLoader;

public class MainPageSP extends StackPane {

    private Button btnOff, btnMulti,btnSetting,btnSolo;
    private ImageView ivTitle;
    private SettingSP setting;
    private ChoiceThemeBP choiceTheme;

    public MainPageSP() {
        //BACKGROUND
        this.setBackground(BackGroundLoader.builderBackGround());

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
            ImageView setting = new ImageView(new Image("images/icon/settings.png", 50,
                    50,true,true));
            btnSetting = new Button("" , setting);
            btnSetting.getStyleClass().add("buttonTransparence");

            btnSetting.setOnAction(event -> getScene().setRoot(getSetting()));
        }
        return btnSetting;
    }

    public Button getBtnOff() {
        if (btnOff == null) {
            ImageView powerOff = new ImageView(new Image("images/icon/turn-off.png",50,
                    50,true,true));
            btnOff = new Button("", powerOff);
            btnOff.getStyleClass().add("buttonTransparence");
            btnOff.setOnAction(event -> ((Stage)(((Button)event.getSource()).getScene().getWindow())).close());
        }
        return btnOff;
    }

    public Button getBtnSolo() {
        if (btnSolo == null) {
            btnSolo = new Button("SoloPlayer");
            btnSolo.getStyleClass().add("buttonBasic");
            btnSolo.setOnAction(event -> getScene().setRoot(getChoiceTheme()));
        }
        return btnSolo;
    }

    public Button getBtnMulti() {
        if (btnMulti == null) {
            btnMulti = new Button("MultiPlayer");
            btnMulti.getStyleClass().add("buttonBasic");
        }
        return btnMulti;
    }

    public ImageView getIvTitle() {
        if (ivTitle == null) {
            ivTitle = new ImageView(new Image("images/base/Title.png"));
        }
        return ivTitle;
    }

    public SettingSP getSetting() {
        if (setting == null) {
            setting = new SettingSP();
        }
        return setting;
    }

    public ChoiceThemeBP getChoiceTheme() {
        if (choiceTheme == null) {
            choiceTheme = new ChoiceThemeBP();
        }
        return choiceTheme;
    }
}
