package view.admin;

import application.Main;
import exceptions.UserUnknown;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import utils.BackgroundLoader;
import utils.controler.Connection;
import view.MainPageSP;
import view.admin.AdminChoiceSP;

public class AdminLoginSP extends StackPane {

    private ImageView imgTitre;

    private Label lblLogin;
    private Label lblPassword;

    private TextField txtLogin;
    private PasswordField pwfPassword;

    private Button btnConnection;
    private Button btnBack;

    public AdminLoginSP() {

        //BACKGROUND
        this.setBackground(BackgroundLoader.builderBackGround());

        //Top
        StackPane.setAlignment(getImgTitre(), Pos.TOP_CENTER);
        StackPane.setMargin(getImgTitre(), new Insets(20));

        VBox vContainer = new VBox();

        HBox hbLogin = new HBox();
        hbLogin.setSpacing(20.);
        hbLogin.getChildren().addAll(getLblLogin(), getTxtLogin());

        HBox hbPassword = new HBox();
        hbPassword.setSpacing(20.);
        hbPassword.getChildren().addAll(getLblPassword(),getPwfPassword());

        vContainer.getChildren().addAll(hbLogin,hbPassword);
        vContainer.setSpacing(50);
        vContainer.setMaxSize(500,200);

        StackPane.setAlignment(vContainer, Pos.CENTER);


        StackPane.setAlignment(getBtnConnection(), Pos.BOTTOM_CENTER);
        StackPane.setMargin(getBtnConnection(), new Insets(10,450,50,0));

        StackPane.setAlignment(getBtnBack(), Pos.BOTTOM_CENTER);
        StackPane.setMargin(getBtnBack(), new Insets(0,0,50,450));

        this.getChildren().addAll(getImgTitre(),vContainer,getBtnConnection(),getBtnBack());

        this.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
					connection();
				} catch (UserUnknown e) {
                    clearAllEntries();
                    promptTextSet("User Exists");
                    System.err.println(e.getMessage());
				}
            }
        });
    }

    /**
     * Connection to the database
     * @throws UserUnknown The user does not exist
     */
    public void connection() throws UserUnknown {
        if (!getTxtLogin().getText().equals("") && !getPwfPassword().getText().equals("")) {
            if (Connection.connectionGame(txtLogin.getText(), pwfPassword.getText(), this.getClass())) {
                Main.switchScene(new AdminChoiceSP());
            } else {
                pwfPassword.setText("");
                txtLogin.setText("");
                txtLogin.setPromptText("Invalid");
                pwfPassword.setPromptText("Invalid");
            }
        }
    }

    public ImageView getImgTitre() {
        if (imgTitre == null) {
            imgTitre = new ImageView(new Image("asset/images/title/Admin.png"));
        }
        return imgTitre;
    }

    public Label getLblLogin() {
        if (lblLogin == null) {
            lblLogin = new Label("Login : ");
            lblLogin.setAlignment(Pos.CENTER_RIGHT);
            lblLogin.getStyleClass().add("labelBasique");
        }
        return lblLogin;
    }
    public Label getLblPassword() {
        if (lblPassword == null) {
            lblPassword = new Label("Password :");
            lblPassword.getStyleClass().add("labelBasique");
        }
        return lblPassword;
    }
    public TextField getTxtLogin() {
        if (txtLogin == null) {
            txtLogin = new TextField();
            txtLogin.getStyleClass().add("textBox");
            txtLogin.setPrefSize(300,50);
        }
        return txtLogin;
    }
    public PasswordField getPwfPassword() {
        if (pwfPassword == null) {
            pwfPassword = new PasswordField();
            pwfPassword.getStyleClass().add("textBox");
        }
        return pwfPassword;
    }
    
    public Button getBtnConnection() {
        if (btnConnection == null) {
            btnConnection = new Button("Connection");
            btnConnection.setBackground(BackgroundLoader.buildBtnBackGround());
            btnConnection.getStyleClass().addAll("buttonBasic");
            btnConnection.setId("big-button");
            
            btnConnection.setOnAction(event -> {
				try {
					connection();
				} catch (UserUnknown e) {
                    clearAllEntries();
                    promptTextSet("User Exists");
                    System.err.println(e.getMessage());
				}
			});
        }
        return btnConnection;
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

    public void promptTextSet(String value) {
        txtLogin.setPromptText(value);
        pwfPassword.setPromptText(value);
    }

    public void clearAllEntries() {
        pwfPassword.setText("");
        txtLogin.setText("");
    }
}
