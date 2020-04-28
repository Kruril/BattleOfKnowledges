package view.multiplayer;

import application.Main;
import com.sun.javafx.fxml.LoadListener;
import com.sun.org.apache.bcel.internal.generic.*;
import connection.Server;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import utils.BackgroundLoader;

import java.io.IOException;

public class MultiPlayerRoom extends StackPane {

    private Label playerWait1, playerWait2, playerWait3, playerWait4;
    private ImageView ivTitle;

    private Button btnBack, btnStart;

    private Insets insets = new Insets(10.);

    private Server server;



    public MultiPlayerRoom() throws IOException {

        server = new Server();

        this.setBackground(BackgroundLoader.builderBackGround());

        setAlignment(getIvTitle(), Pos.TOP_CENTER);

        VBox vbWaitingPlayer = new VBox(getPlayerWait1(), getPlayerWait2(), getPlayerWait3(), getPlayerWait4());
        vbWaitingPlayer.setMaxSize(720.,360.);
        vbWaitingPlayer.setAlignment(Pos.CENTER);
        vbWaitingPlayer.setSpacing(20.);
        setAlignment(vbWaitingPlayer, Pos.CENTER);

        setAlignment(getBtnBack(), Pos.BOTTOM_LEFT);
        setAlignment(getBtnStart(), Pos.BOTTOM_CENTER);

        this.setPadding(new Insets(20.));
        this.getChildren().addAll(getIvTitle(), vbWaitingPlayer, getBtnBack(), getBtnStart());

        server.create();
        getPlayerWait1().setText(server.PlayerConnected());

    }

    public Label getPlayerWait1() {
        if (playerWait1 == null) {
            playerWait1 = new Label("Waiting player ...");
            playerWait1.getStyleClass().add("waitingPlayer");
            playerWait1.setPadding(insets);
        }
        return playerWait1;
    }

    public Label getPlayerWait2() {
        if (playerWait2 == null) {
            playerWait2 = new Label("Waiting player ...");
            playerWait2.getStyleClass().add("waitingPlayer");
            playerWait2.setPadding(insets);
        }
        return playerWait2;
    }

    public Label getPlayerWait3() {
        if (playerWait3 == null) {
            playerWait3 = new Label("Waiting player ...");
            playerWait3.getStyleClass().add("waitingPlayer");
            playerWait3.setPadding(insets);
        }
        return playerWait3;
    }

    public Label getPlayerWait4() {
        if (playerWait4 == null) {
            playerWait4 = new Label("Waiting player ...");
            playerWait4.getStyleClass().add("waitingPlayer");
            playerWait4.setPadding(insets);

        }
        return playerWait4;
    }

    public ImageView getIvTitle() {
        if (ivTitle == null) {
            ivTitle = new ImageView(new Image("asset/images/title/Multiplayer_Room.png"));
        }
        return ivTitle;
    }

    public Button getBtnBack() {
        if (btnBack == null) {
            btnBack = new Button("Back");
            btnBack.setBackground(BackgroundLoader.buildBtnBackGround());
            btnBack.getStyleClass().add("buttonBasic");
            btnBack.setId("small-button");
            btnBack.setOnAction(event -> {
                Main.switchScene(new MultiplayerBP());
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        return btnBack;
    }

    public Button getBtnStart() {
        if (btnStart == null) {
            btnStart = new Button("Start game");
            btnStart.setBackground(BackgroundLoader.buildBtnBackGround());
            btnStart.getStyleClass().add("buttonBasic");
            btnStart.setId("big-button");
        }
        return btnStart;
    }

}
