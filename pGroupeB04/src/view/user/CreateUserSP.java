package view.user;

import application.Main;
import exceptions.UserAlreadyExist;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import utils.utility.BackgroundLoader;
import utils.controler.Connection;
import view.MainPageSP;


public class CreateUserSP extends StackPane{

	private ImageView imgCreateUser;
	
	private Label lblLogin;
    private Label lblPassword;
    private Label lblEmail;

    private TextField txtLogin;
    private PasswordField pwfPassword;
    private TextField txtEmail;

    private Button btnValidate;
    private Button btnBack;

    private CheckBox cbNewLetter;
	
    public CreateUserSP() {

        //BACKGROUND
        this.setBackground(BackgroundLoader.builderBackGround());

        //Top
        StackPane.setAlignment(getImgCreateUser(), Pos.TOP_CENTER);
        StackPane.setMargin(getImgCreateUser(), new Insets(20));

        VBox vContainer = new VBox();

        HBox hbLogin = new HBox();
        hbLogin.setSpacing(20.);
        hbLogin.getChildren().addAll(getLblLogin(), getTxtLogin());

        HBox hbPassword = new HBox();
        hbPassword.setSpacing(25.);
        hbPassword.getChildren().addAll(getLblPassword(),getPwfPassword());
        
        HBox hbEmail = new HBox();
        hbEmail.setSpacing(20.);
        hbEmail.getChildren().addAll(getLblEmail(), getTxtEmail());

        vContainer.getChildren().addAll(hbLogin,hbPassword, hbEmail, getCbNewLetter());
        vContainer.setSpacing(50);
        vContainer.setAlignment(Pos.CENTER);
        vContainer.setMaxSize(500,200);

        StackPane.setAlignment(vContainer, Pos.CENTER);


        StackPane.setAlignment(getBtnValidate(), Pos.BOTTOM_CENTER);
        StackPane.setMargin(getBtnValidate(), new Insets(10,450,50,0));

        StackPane.setAlignment(getBtnBack(), Pos.BOTTOM_CENTER);
        StackPane.setMargin(getBtnBack(), new Insets(0,0,50,450));

        this.getChildren().addAll(getImgCreateUser(),vContainer,getBtnValidate(),getBtnBack());

        this.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
					connection();
				} catch (UserAlreadyExist e) {
                    clearAllEntries();
                    promptTextSet("User Exists");
                    System.err.println(e.getMessage());
				}
            }
        });
    }

    public void connection() throws UserAlreadyExist{
        if (!getPwfPassword().getText().equals("") && !getTxtLogin().getText().equals("")) {
            if (Connection.createUser(getTxtLogin().getText(), getPwfPassword().getText(), getTxtEmail().getText())) {
                Main.switchScene(new MainPageSP());
            }
            else {
                clearAllEntries();
                promptTextSet("Invalid");
            }

        } else {
            clearAllEntries();
            promptTextSet("Please fill all the blanks");
        }
    }

    public ImageView getImgCreateUser() {
        if (imgCreateUser == null) {
        	imgCreateUser = new ImageView(new Image("asset/images/title/Create_User.png"));
        }
        return imgCreateUser;
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
    public Label getLblEmail() {
        if (lblEmail == null) {
            lblEmail = new Label("Email :");
            lblEmail.setAlignment(Pos.CENTER_RIGHT);
            lblEmail.getStyleClass().add("labelBasique");
        }
        return lblEmail;
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
    public TextField getTxtEmail() {
    	if (txtEmail == null) {
    		txtEmail = new TextField();
    		txtEmail.getStyleClass().add("textBox");
    		txtEmail.setPrefSize(300, 50);
    	}
    	return txtEmail;
    }
    public Button getBtnValidate() {
        if (btnValidate == null) {
        	btnValidate = new Button("Validate");
        	btnValidate.setBackground(BackgroundLoader.buildBtnBackGround());
        	btnValidate.getStyleClass().addAll("buttonBasic");
        	btnValidate.setId("big-button");
        	
        	btnValidate.setOnAction(event -> {
				try {
					connection();
				} catch (UserAlreadyExist e) {
                    clearAllEntries();
                    promptTextSet("User Exists");
                    System.err.println(e.getMessage());
				}
			});
        }
        return btnValidate;
    }

    public void promptTextSet(String value) {
        txtLogin.setPromptText(value);
        pwfPassword.setPromptText(value);
        txtEmail.setPromptText(value);
    }

    public void clearAllEntries() {
        pwfPassword.setText("");
        txtLogin.setText("");
        txtEmail.setText("");
    }

    public Button getBtnBack() {
        if (btnBack == null) {
            btnBack = new Button("Back");
            btnBack.setBackground(BackgroundLoader.buildBtnBackGround());
            btnBack.getStyleClass().add("buttonBasic");
            btnBack.setId("big-button");
            btnBack.setOnAction(event -> Main.switchScene(new UserLoginSP()));
        }
        return btnBack;
    }

    public CheckBox getCbNewLetter() {
        if (cbNewLetter == null) {
            cbNewLetter = new CheckBox("Subscribe to the newsletter");
            cbNewLetter.getStyleClass().add("labelSmall");
        }
        return cbNewLetter;
    }
}
