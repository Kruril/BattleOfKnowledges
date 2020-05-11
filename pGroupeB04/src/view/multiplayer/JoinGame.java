package view.multiplayer;

import application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import utils.user.Player;
import utils.utility.BackgroundLoader;

public class JoinGame  extends StackPane {

    private ImageView ivTitle;
    private Label lblHost, lblNamePlayer;

    private TextField txtHost, txtNamePLayer;

    private Button btnBack, btnJoin;
    private final Insets insets = new Insets(10.);

    public JoinGame() {

        this.setBackground(BackgroundLoader.builderBackGround());

        setAlignment(getIvTitle(), Pos.TOP_CENTER);

        VBox vbContainer = new VBox(getLblHost(), getTxtHost(), getLblNamePlayer(), getTxtNamePLayer());
        vbContainer.setMaxSize(720., 360.);
        vbContainer.setAlignment(Pos.CENTER);
        vbContainer.setSpacing(20.);
        setAlignment(vbContainer, Pos.CENTER);

        setAlignment(getBtnBack(), Pos.BOTTOM_LEFT);
        setAlignment(getBtnJoin(), Pos.BOTTOM_CENTER);

        this.setPadding(new Insets(20.));
        this.getChildren().addAll(getIvTitle(), vbContainer, getBtnBack(), getBtnJoin());

    }

    public ImageView getIvTitle() {
        if (ivTitle == null) {
            ivTitle = new javafx.scene.image.ImageView(new Image("asset/images/title/Join_Server.png"));
        }
        return ivTitle;
    }

    public Label getLblHost() {
        if (lblHost == null) {
            lblHost = new Label("Host");
            lblHost.getStyleClass().add("labelBasique");
            lblHost.setPadding(insets);
        }
        return lblHost;
    }

    public Label getLblNamePlayer() {
        if (lblNamePlayer == null) {
            lblNamePlayer = new Label("Player's Name");
            lblNamePlayer.getStyleClass().add("labelBasique");
            lblNamePlayer.setPadding(insets);
        }
        return lblNamePlayer;
    }

    public TextField getTxtHost() {
        if (txtHost == null) {
            txtHost = new TextField("localhost");
            txtHost.getStyleClass().add("waitingPlayer");
            txtHost.setPadding(insets);
        }
        return txtHost;
    }

    public TextField getTxtNamePLayer() {
        if (txtNamePLayer == null) {
            txtNamePLayer = new TextField(Player.getUser().getPseudo());
            txtNamePLayer.getStyleClass().add("waitingPlayer");
            txtNamePLayer.setPadding(insets);
        }
        return txtNamePLayer;
    }

    public Button getBtnBack() {
        if (btnBack == null) {
            btnBack = new Button("Back");
            btnBack.setId("small-button");
            btnBack.setBackground(BackgroundLoader.buildBtnBackGround());
            btnBack.getStyleClass().add("buttonBasic");
            btnBack.setOnAction(event -> Main.switchScene(new MultiplayerBP()));
        }
        return btnBack;
    }

    public Button getBtnJoin() {
        if (btnJoin == null) {
            btnJoin = new Button("Join");
            btnJoin.setBackground(BackgroundLoader.buildBtnBackGround());
            btnJoin.setId("big-button");
            btnJoin.getStyleClass().add("buttonBasic");
            btnJoin.setOnAction(event -> {
                Main.getClient().addHost(getTxtHost().getText());
                Player.setName(getTxtNamePLayer().getText());
                Main.getClient().connect();
            });
        }
        return btnJoin;
    }
}
