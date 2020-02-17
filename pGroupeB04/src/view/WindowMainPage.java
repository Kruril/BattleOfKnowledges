package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;

public class WindowMainPage extends BorderPane {

    private Button btnAdmin, btnSolo, btnMulti;

    public WindowMainPage() {
        //BACKGROUND
        BackgroundImage bgiMainPage = new BackgroundImage(new Image("images/BGMainPage.png"),BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT , BackgroundPosition.DEFAULT,
                new BackgroundSize(Screen.getPrimary().getVisualBounds().getWidth(),
                Screen.getPrimary().getVisualBounds().getHeight(),false,false,
                true,true));

        this.setBackground(new Background(bgiMainPage));

        //TOP
        HBox hbBtn = new HBox();
        hbBtn.getChildren().add(getBtnAdmin());
        hbBtn.setPadding(new Insets(10,10,10,10));
        this.setTop(hbBtn);

        //BOTTOM
        HBox hbBottom = new HBox();
        hbBottom.getChildren().addAll(getBtnSolo(),getBtnMulti());
        hbBottom.setPadding(new Insets(10,10,20,10));
        hbBottom.setAlignment(Pos.BOTTOM_CENTER);
        hbBottom.setSpacing(100);
        this.setBottom(hbBottom);
    }

    public Button getBtnAdmin() {
        if (btnAdmin == null) {
            Image iconAdmin = new Image("images/adminIcon.png", 40,40,
                    true,true);
            ImageView viewAdmin = new ImageView(iconAdmin);

            btnAdmin = new Button("",viewAdmin );
            btnAdmin.getStyleClass().add("buttonTransparence");
        }
        return btnAdmin;
    }

    public Button getBtnSolo() {
        if (btnSolo == null) {
            btnSolo = new Button("Solo player");
            btnSolo.setStyle("-fx-border-radius: 30;");
            btnSolo.getStyleClass().add("buttonBasic");
        }
        return btnSolo;
    }

    public Button getBtnMulti() {
        if (btnMulti == null) {
                btnMulti = new Button("Multiplayer");
                btnMulti.getStyleClass().add("buttonBasic");
        }
        return btnMulti;
    }
}
