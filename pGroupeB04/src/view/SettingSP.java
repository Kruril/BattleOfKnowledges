package view;

import application.Main;
import enumeration.AvatarPlayer;
import enumeration.SizeScreen;
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
import utils.BackgroundLoader;

public class SettingSP extends StackPane {

    private ImageView ivTitle;
    private Label lblSound, lblResolution, lblAvatar;
    private Slider slSound;
    private ComboBox<String> cmResolusion;
    private ComboBox<ImageView> cmAvatar;
    private Button btnBack;
    private Button btnAdmin;

    public SettingSP() {

        //BACKGROUND
        this.setBackground(BackgroundLoader.builderBackGround());

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
        hbResolution.getChildren().addAll(getLblResolution(), getCmResolution());

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
        
        //Button Admin
        StackPane.setAlignment(getBtnAdmin(), Pos.BOTTOM_CENTER);
        StackPane.setMargin(getBtnAdmin(), new Insets(10,450,50,0));

        this.getChildren().addAll(getIvTitle(), vContainer,getBtnBack(), getBtnAdmin());

    }

    public ImageView getIvTitle() {
        if (ivTitle == null) {
            ivTitle = new ImageView(new Image("images/title/Settings.png"));
        }
        return ivTitle;
    }

    public Label getLblSound() {
        if (lblSound == null) {
            lblSound = new Label("Sound :");
            lblSound.setAlignment(Pos.CENTER_RIGHT);
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
            lblAvatar = new Label("Avatar :");
            lblAvatar.setAlignment(Pos.CENTER_RIGHT);
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

    public ComboBox<String> getCmResolution() {
        if (cmResolusion == null) {
            cmResolusion = new ComboBox<>();
            cmResolusion.getStyleClass().add("textBox");
            promptTextValue();

            for (SizeScreen size: SizeScreen.values())
                cmResolusion.getItems().add(size.getValeur());

            getCmResolution().setOnAction(event -> {
                String choice = getCmResolution().getSelectionModel().getSelectedItem();
                if (choice.equals("Fullscreen without border"))
                    fullscreenMode();
                else if (choice.equals("Fullscreen with border"))
                    mazimizedMode();
                else
                    sizeScreen(choice);
            });
        }
        return cmResolusion;
    }

    public ComboBox<ImageView> getCmAvatar() {
        if (cmAvatar == null) {
            cmAvatar = new ComboBox<>();
            cmAvatar.setPromptText("select avatar");
            cmAvatar.getStyleClass().add("textBox");

            for (AvatarPlayer avatar: AvatarPlayer.values())
                cmAvatar.getItems().add(avatar.getValeur());
        }
        return cmAvatar;
    }

    public Button getBtnBack() {
        if (btnBack == null) {
            btnBack = new Button("Back");
            btnBack.setBackground(BackgroundLoader.buildBtnBackGround());
            btnBack.getStyleClass().add("buttonBasic");
            btnBack.setId("big-button");
            btnBack.setOnAction(event -> Main.switchScene(new MainPageSP()));
        }
        return btnBack;
    }
    
    public Button getBtnAdmin() {
        if (btnAdmin == null) {
        	btnAdmin = new Button("Admin");
        	btnAdmin.setBackground(BackgroundLoader.buildBtnBackGround());
        	btnAdmin.getStyleClass().add("buttonBasic");
        	btnAdmin.setId("big-button");
        	btnAdmin.setOnAction(event -> Main.switchScene(new AdminLoginSP()));
        }
        return btnAdmin;
    }

    /**
     * method that will set the size of the window via width and height
     * @param choice Element selected in the combobox
     */
    public void sizeScreen(String choice) {
        String width = choice.substring(0,4);
        String height = choice.substring(5);

        Main.getStage().setFullScreen(false);
        Main.getStage().setMaximized(false);

        Main.getStage().setWidth(Double.parseDouble(width));
        Main.getStage().setHeight(Double.parseDouble(height));
        getScene().getWindow().centerOnScreen();
    }

    /**
     * setup the window in maximized
     */
    public void mazimizedMode() {
        if (Main.getStage().isFullScreen())
            Main.getStage().setFullScreen(false);

        Main.getStage().setMaximized(true);
    }

    /**
     * setup the window in full screen
     */
    public void fullscreenMode() {
        if (Main.getStage().isMaximized())
            Main.getStage().setMaximized(false);

        Main.getStage().setFullScreen(true);
    }

    /**
     * displays the size of the window in the combobox
     * when you get to the parameters page.
     */
    private void promptTextValue() {
        String resolution;
        if (Main.getStage().isFullScreen())
            resolution = SizeScreen.FULLSCREENWOUTBORDER.getValeur();
        else if (Main.getStage().isMaximized())
            resolution = SizeScreen.FULLSCREENWBORDER.getValeur();
        else
            resolution = (int)Main.getStage().getWidth()+"*"+(int)Main.getStage().getHeight();

        cmResolusion.setPromptText(resolution);
    }
}
