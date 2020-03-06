package view;

import application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.BackGroundLoader;

public class SettingSP extends StackPane {

    private ImageView ivTitle;
    private Label lblSound, lblResolution, lblAvatar;
    private Slider slSound;
    private ComboBox<String> cmResolusion;
    private ComboBox<ImageView> cmAvatar;
    private Button btnAdminConnect;
    private Button btnBack;

    public SettingSP() {

        //BACKGROUND
        this.setBackground(BackGroundLoader.builderBackGround());

        //Title
        StackPane.setAlignment(getIvTitle(), Pos.TOP_CENTER);
        StackPane.setMargin(getIvTitle(), new Insets(20));

        VBox vContainer = new VBox();

        //Sound
        HBox hbSound = new HBox();
        hbSound.setSpacing(20);
        hbSound.getChildren().addAll(getLblSound(), getSlSound());

        //Resolution
        HBox hbResolution = new HBox();
        hbResolution.setSpacing(20);
        hbResolution.getChildren().addAll(getLblResolution(), getCmResolusion());

        //Avatar
        HBox hbAvatar = new HBox();
        hbAvatar.setSpacing(20);
        hbAvatar.getChildren().addAll(getLblAvatar(), getCmAvatar());

        vContainer.getChildren().addAll(hbSound,hbResolution,hbAvatar);
        vContainer.setSpacing(50);
        vContainer.setMaxSize(600,350);
        StackPane.setAlignment(vContainer, Pos.CENTER);

        //Button back
        StackPane.setAlignment(getBtnBack(), Pos.BOTTOM_CENTER);
        StackPane.setMargin(getBtnBack(), new Insets(0,0,50,450));

        //Button connection admin
        StackPane.setAlignment(getBtnAdminConnect(),Pos.BOTTOM_CENTER);
        StackPane.setMargin(getBtnAdminConnect(), new Insets(10,450,50,0));

        this.getChildren().addAll(getIvTitle(), vContainer, getBtnBack(), getBtnAdminConnect());

    }

    public ImageView getIvTitle() {
        if (ivTitle == null) {
            ivTitle = new ImageView(new Image("images/base/Settings.png"));
        }
        return ivTitle;
    }

    public Label getLblSound() {
        if (lblSound == null) {
            lblSound = new Label("       Sound :");
            lblSound.getStyleClass().add("labelBasique");
        }
        return lblSound;
    }

    public Label getLblResolution() {
        if (lblResolution == null) {
            lblResolution = new Label("Resolution :");
            lblResolution.getStyleClass().add("labelBasique");
        }
        return lblResolution;
    }

    public Label getLblAvatar() {
        if (lblAvatar == null) {
            lblAvatar = new Label("       Avatar :");
            lblAvatar.getStyleClass().add("labelBasique");
        }
        return lblAvatar;
    }

    public Slider getSlSound() {
        if (slSound == null) {
            slSound = new Slider();
            slSound.getStyleClass().add("textBox");
        }
        return slSound;
    }

    public ComboBox<String> getCmResolusion() {
        if (cmResolusion == null) {
            cmResolusion = new ComboBox<>();
            cmResolusion.setPromptText("1280*720");
            cmResolusion.getStyleClass().add("textBox");
            cmResolusion.getItems().addAll("1080*720","1280*720","1280*800","1280*1024", "1920*1080",
                    "FullScreen");

            getCmResolusion().setOnAction(event -> {
                String choice = getCmResolusion().getSelectionModel().getSelectedItem();

                if (choice.equals("FullScreen")) {
                    Main.getStage().setFullScreen(true);
                }
                else {
                    String width = choice.substring(0,4);
                    String height = choice.substring(5);
                    Main.getStage().setFullScreen(false);
                    getScene().getWindow().setWidth(Double.parseDouble(width));
                    getScene().getWindow().setHeight(Double.parseDouble(height));
                    getScene().getWindow().centerOnScreen();
                }
            });
        }
        return cmResolusion;
    }

    public ComboBox<ImageView> getCmAvatar() {
        if (cmAvatar == null) {
            cmAvatar = new ComboBox<>();
            cmAvatar.setPromptText("select avatar");
            cmAvatar.getStyleClass().add("textBox");

            ImageView im1 = new ImageView(new Image("images/avatar/arbre.png", 50,50,
                    true,true));

            ImageView im2 = new ImageView(new Image("images/avatar/alien.png",50,50,
                    true,true));

            ImageView im3 = new ImageView(new Image("images/avatar/batter.png",50,50,
                    true,true));

            cmAvatar.getItems().addAll(im1,im2, im3);
        }
        return cmAvatar;
    }

    public Button getBtnAdminConnect() {
        if (btnAdminConnect == null) {
            btnAdminConnect = new Button("Admin");
            btnAdminConnect.getStyleClass().add("buttonBasic");
            btnAdminConnect.setOnAction(event -> Main.switchScene(new AdminLoginSP()));
        }
        return btnAdminConnect;
    }

    public Button getBtnBack() {
        if (btnBack == null) {
            btnBack = new Button("Back");
            btnBack.getStyleClass().add("buttonBasic");
            btnBack.setOnAction(event -> Main.switchScene(new MainPageSP()));
        }
        return btnBack;
    }
}
