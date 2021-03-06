package view.user;

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
import model.UserBuilder;
import utils.utility.BackgroundLoader;
import utils.user.Player;
import utils.controler.Connection;
import view.MainPageSP;

public class UserLoginSP extends StackPane {

    private ImageView imgTitre;

    private Label lblLogin;
    private Label lblPassword;

    private TextField txtLogin;
    private PasswordField pwfPassword;

	private Label lblCreateUser;

    private Button btnConnection;
    private Button btnInvite;

    public UserLoginSP() {

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
        
        VBox vbCreateUser = new VBox();
        vbCreateUser.getChildren().add(getLblCreateUser());
        vbCreateUser.setAlignment(Pos.CENTER);
        vbCreateUser.setPadding(new Insets(0,0,50,0));

        vContainer.getChildren().addAll(hbLogin,hbPassword, vbCreateUser);
        vContainer.setSpacing(50);
        vContainer.setMaxSize(500,200);

        StackPane.setAlignment(vContainer, Pos.CENTER);

        
        StackPane.setAlignment(getBtnConnection(), Pos.BOTTOM_CENTER);
        StackPane.setMargin(getBtnConnection(), new Insets(10,450,50,0));

        StackPane.setAlignment(getBtnInvite(), Pos.BOTTOM_CENTER);
        StackPane.setMargin(getBtnInvite(), new Insets(0,0,50,450));	
        
        this.getChildren().addAll(getImgTitre(),vContainer,getBtnConnection(), getBtnInvite());

        this.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
					connection();
				} catch (UserUnknown e) {
                    clearAllEntries();
                    promptTextSet("unknown user");
                    System.err.println(e.getMessage());
				}
            }
        });
    }

    /**
     * Connect a User to the Main Page
     * @throws UserUnknown if user doesn't exist throw exception
     */
    public void connection() throws UserUnknown {
        if (!getTxtLogin().getText().equals("") && !getPwfPassword().getText().equals("")) {
            if (Connection.connectionGame(txtLogin.getText(), pwfPassword.getText(), this.getClass())) {
                Main.switchScene(new MainPageSP());
            }
            else {
                pwfPassword.setText("");
                txtLogin.setText("");
                txtLogin.setPromptText("Invalid");
                pwfPassword.setPromptText("Invalid");
            }
        }
    }

    public ImageView getImgTitre() {
        if (imgTitre == null) {
            imgTitre = new ImageView(new Image("asset/images/title/LogIn.png"));
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
	
	public Label getLblCreateUser() {
		if (lblCreateUser == null) {
			lblCreateUser = new Label("Click here to create an account");
			
			lblCreateUser.getStyleClass().add("labelSmall");
			
			lblCreateUser.setOnMouseClicked(event -> Main.switchScene(new CreateUserSP()));
		}
		return lblCreateUser;
	}
    
    public Button getBtnConnection() {
        if (btnConnection == null) {
            btnConnection = new Button("Connection");
            btnConnection.setBackground(BackgroundLoader.buildBtnBackGround());
            btnConnection.getStyleClass().add("buttonBasic");
            btnConnection.setId("big-button");
            
            
            btnConnection.setOnAction(event -> {
				try {
					connection();
				} catch (UserUnknown e) {
                    clearAllEntries();
                    promptTextSet("unknown user");
                    System.err.println(e.getMessage());
				}
			});
        }
        return btnConnection;
    }

	public Button getBtnInvite() {
		if (btnInvite == null) {
			btnInvite = new Button("Play as invited");
			btnInvite.setBackground(BackgroundLoader.buildBtnBackGround());
			btnInvite.getStyleClass().add("buttonBasic");
			btnInvite.setId("big-button");
			
			btnInvite.setOnAction(event -> {
			    Player.setUser(new UserBuilder()
                        .login("default")
                        .pseudo("default")
                        .build());
			    Main.switchScene(new MainPageSP());
            });

		}
		return btnInvite;
	}

    public void promptTextSet(String value) {
        txtLogin.setPromptText(value);
        pwfPassword.setPromptText(value);
    }

    /**
     * Clear all TextField and PasswordField
     */
    public void clearAllEntries() {
        pwfPassword.setText("");
        txtLogin.setText("");
    }

}
