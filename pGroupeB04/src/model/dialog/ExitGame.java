package model.dialog;

import application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import utils.BackgroundLoader;
import utils.GamePage.Timer;
import view.MainPageSP;

public class ExitGame extends StackPane {

    private Label text;
    private Button btnExit, btnCancel;

    public ExitGame() {
        this.setMaxSize(400.,300.);
        this.setBackground(BackgroundLoader.buildDialogBackGround(400.,300.));
        this.getStyleClass().add("dialog");
        setAlignment(getText(), Pos.CENTER);
        setAlignment(getBtnExit(), Pos.BOTTOM_CENTER);
        setMargin(getBtnExit(), new Insets(0.,0.,50.,0.));
        setAlignment(getBtnCancel(), Pos.BOTTOM_RIGHT);
        setMargin(getBtnCancel(), new Insets(0.,60.,50.,0.));
        this.setPadding(new Insets(10.));
        this.getChildren().addAll(getText(), getBtnExit(),getBtnCancel());
    }

    public Label getText() {
        if (text == null) {
            text = new Label("Do you want to quit the game ?");
            text.getStyleClass().add("dialog-title");
        }
        return text;
    }

    public Button getBtnExit() {
        if (btnExit == null) {
            btnExit = new Button("Exit");
            btnExit.setOnAction(event -> {
                Main.switchScene(new MainPageSP());
                Timer.getTimeTimer().stop();
            });
            btnExit.setMinSize(80.,25.);
            btnExit.getStyleClass().addAll("buttonBasic","dialog-button");
        }
        return btnExit;
    }

    public Button getBtnCancel() {
        if (btnCancel == null) {
            btnCancel = new Button("Cancel");
            btnCancel.setOnAction(event -> this.setVisible(false));
            btnCancel.setMinSize(80.,25.);
            btnCancel.getStyleClass().addAll( "buttonBasic","dialog-button");
        }
        return btnCancel;
    }
}
