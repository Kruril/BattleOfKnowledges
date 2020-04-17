package view;

import application.Main;
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
import utils.Connection;

import java.sql.SQLException;

public class UserLoginSP extends StackPane {

    private ImageView imgTitre;

    private Label lblLogin;
    private Label lblPassword;

    private TextField txtLogin;
    private PasswordField pwfPassword;
    
    private Label lblNew;
	private Label lblCreateUser;

    private Button btnConnection;

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
        hbPassword.getChildren().addAll(getLblPassword(),getPwfPassword(),getLblNew());
        
        VBox vbCreateUser = new VBox();
        vbCreateUser.getChildren().addAll(getLblNew(),getLblCreateUser());
        vbCreateUser.setAlignment(Pos.CENTER);
        vbCreateUser.setPadding(new Insets(0,0,50,0));

        vContainer.getChildren().addAll(hbLogin,hbPassword, vbCreateUser);
        vContainer.setSpacing(50);
        vContainer.setMaxSize(500,200);

        StackPane.setAlignment(vContainer, Pos.CENTER);


        StackPane.setAlignment(getBtnConnection(), Pos.BOTTOM_CENTER);
        StackPane.setMargin(getBtnConnection(), new Insets(10,0,50,0));

        this.getChildren().addAll(getImgTitre(),vContainer,getBtnConnection());

        this.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (!getTxtLogin().equals("") && !getPwfPassword().equals("")) {
                    try {
                        if (!Connection.connection(txtLogin, pwfPassword, this.getClass())){

                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
    }

    public ImageView getImgTitre() {
        if (imgTitre == null) {
            imgTitre = new ImageView(new Image("images/title/LogIn.png"));
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
    
    public Label getLblNew() {
		if (lblNew == null) {
			lblNew = new Label("New here ?");
			
			lblNew.getStyleClass().add("labelBasique");
		}
		return lblNew;
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
                    Connection.connection(txtLogin, pwfPassword, this.getClass());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            });
        }
        return btnConnection;
    }

}
