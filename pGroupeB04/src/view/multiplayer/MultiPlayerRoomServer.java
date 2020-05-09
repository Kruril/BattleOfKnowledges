package view.multiplayer;

import application.Main;
import connection.Server;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import utils.user.Player;
import utils.utility.BackgroundLoader;

import java.io.IOException;
import java.util.HashMap;


public class MultiPlayerRoomServer extends StackPane {

    private Label playerWait0, playerWait1, playerWait2, playerWait3;
    private ImageView ivTitle;

    private Button btnBack, btnStart;

    private Insets insets = new Insets(10.);

    private HashMap<Integer, Label> playerWaits;

    public MultiPlayerRoomServer() {
    	
        playerWaits = new HashMap<>();

        this.setBackground(BackgroundLoader.builderBackGround());

        setAlignment(getIvTitle(), Pos.TOP_CENTER);

        VBox vbWaitingPlayer = new VBox(getPlayerWait0(), getPlayerWait1(), getPlayerWait2(), getPlayerWait3());
        vbWaitingPlayer.setMaxSize(720.,360.);
        vbWaitingPlayer.setAlignment(Pos.CENTER);
        vbWaitingPlayer.setSpacing(20.);
        setAlignment(vbWaitingPlayer, Pos.CENTER);

        setAlignment(getBtnBack(), Pos.BOTTOM_LEFT);
        setAlignment(getBtnStart(), Pos.BOTTOM_CENTER);

        this.setPadding(new Insets(20.));
        this.getChildren().addAll(getIvTitle(), vbWaitingPlayer, getBtnBack(), getBtnStart());

        Main.getServer().setProperty(playerWaits, getBtnStart());
    }

    public Label getPlayerWait0() {
        if (playerWait0 == null) {
            playerWait0 = new Label(Player.getName());
            playerWait0.getStyleClass().add("waitingPlayer");
            playerWait0.setPadding(insets);
        }
        return playerWait0;
    }
    public Label getPlayerWait1() {
        if (playerWait1 == null) {
            playerWait1 = new Label("Waiting player ...");
            playerWait1.getStyleClass().add("waitingPlayer");
            playerWait1.setPadding(insets);
            playerWaits.put(1, playerWait1);
        }
        return playerWait1;
    }

    public Label getPlayerWait2() {
        if (playerWait2 == null) {
            playerWait2 = new Label("Waiting player ...");
            playerWait2.getStyleClass().add("waitingPlayer");
            playerWait2.setPadding(insets);
            playerWaits.put(2, playerWait2);
        }
        return playerWait2;
    }

    public Label getPlayerWait3() {
        if (playerWait3 == null) {
            playerWait3 = new Label("Waiting player ...");
            playerWait3.getStyleClass().add("waitingPlayer");
            playerWait3.setPadding(insets);
            playerWaits.put(3, playerWait3);
        }
        return playerWait3;
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
                Main.getServer().shutdown();
                Main.switchScene(new MultiplayerBP());
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
            btnStart.setOnAction(event -> {
            	Main.getServer().launchGame();
            });
        }
        return btnStart;
    }
}
