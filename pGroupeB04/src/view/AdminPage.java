package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

public class AdminPage extends BorderPane {

	private ImageView imgTitre;
	
	private Label lblLogin;
	private Label lblPassword;
	
	private TextField txtLogin;
	private PasswordField pwfPassword;
	
	private Button btnConnection;
	private Button btnBack;
	
	public AdminPage() {
		
		//BACKGROUND
        BackgroundImage bgiAdminPage = new BackgroundImage(new Image("images/Background.png"),BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT , BackgroundPosition.DEFAULT,
                new BackgroundSize(Screen.getPrimary().getVisualBounds().getWidth(),
                Screen.getPrimary().getVisualBounds().getHeight(),false,false,
                true,true));

        this.setBackground(new Background(bgiAdminPage));
        
        //Top
        HBox hbTitre = new HBox();
        hbTitre.getChildren().add(getImgTitre());
        hbTitre.setAlignment(Pos.CENTER);
        hbTitre.setPadding(new Insets(100,50,50,50));
        this.setTop(hbTitre);
        
        //Center 
        VBox vbLogin = new VBox();
        
        HBox hbLogin = new HBox();
        hbLogin.getChildren().addAll(getLblLogin(), getTxtLogin());
        hbLogin.setAlignment(Pos.CENTER);
        
        HBox hbPassword = new HBox();
        hbPassword.getChildren().addAll(getLblPassword(), getPwfPassword());
        hbPassword.setAlignment(Pos.CENTER);
        
        vbLogin.getChildren().addAll(hbLogin, hbPassword);
        vbLogin.setSpacing(30);
        vbLogin.setAlignment(Pos.CENTER);
        
        this.setCenter(vbLogin);
        
        //Bottom
        HBox hbButton = new HBox();
        hbButton.getChildren().addAll(getBtnConnection(), getBtnBack());
        hbButton.setAlignment(Pos.BOTTOM_CENTER);
        hbButton.setSpacing(100);
        hbButton.setPadding(new Insets(10,10,20,10));	 
        
        this.setBottom(hbButton);
	}
	
	public ImageView getImgTitre() {
		if (imgTitre == null) {
			imgTitre = new ImageView(new Image("images/Admin.png"));
		}
		return imgTitre;
	}
	
	public Label getLblLogin() {
		if (lblLogin == null) {
			lblLogin = new Label("Login ");
			lblLogin.getStyleClass().add("label");
			lblLogin.setTextFill(Color.web("#FFFFFF"));
		}
		return lblLogin;
	}
	public Label getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new Label("Password ");
			lblPassword.setTextFill(Color.web("#FFFFFF"));
		}
		return lblPassword;
	}
	public TextField getTxtLogin() {
		if (txtLogin == null) {
			txtLogin = new TextField();
			txtLogin.getStyleClass().add("textBox");
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
		}
		return btnConnection;
	}
	public Button getBtnBack() {
		if (btnBack == null) {
			btnBack = new Button("Back");
		}
		return btnBack;
	}
	
	
	
}
