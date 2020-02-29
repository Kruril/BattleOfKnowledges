package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.BackGroundLoader;

public class MainPageSP extends StackPane {

    private static Button btnSetting, btnSolo;
    private Button btnOff, btnMulti;
    private ImageView ivTitle;

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

        getBtnOff().setOnAction(event -> ((Stage)(((Button)event.getSource()).getScene().getWindow())).close());
    }

    public static Button getBtnSetting() {
        if (btnSetting == null) {
            ImageView setting = new ImageView(new Image("images/icon/settings.png", 50,
                    50,true,true));
            btnSetting = new Button("" , setting);
            btnSetting.getStyleClass().add("buttonTransparence");
        }
        return btnSetting;
    }

    public Button getBtnOff() {
        if (btnOff == null) {
            ImageView powerOff = new ImageView(new Image("images/icon/turn-off.png",50,
                    50,true,true));
            btnOff = new Button("", powerOff);
            btnOff.getStyleClass().add("buttonTransparence");
        }
        return btnOff;
    }

    public static Button getBtnSolo() {
        if (btnSolo == null) {
            btnSolo = new Button("SoloPlayer");
            btnSolo.getStyleClass().add("buttonBasic");
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
}
