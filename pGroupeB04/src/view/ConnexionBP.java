package view;

import application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import utils.BackgroundLoader;

public class ConnexionBP extends BorderPane{

	private ImageView imgTitle;
	
	private Button btnUser;
	private Button btnAdmin;
	
	private Label lblNew;
	
	private Label lblCreateUser;
	
	public ConnexionBP() {
		
		this.setBackground(BackgroundLoader.builderBackGround());
		
		this.setTop(getImgTitle());
		this.setAlignment(getImgTitle(), Pos.CENTER);
		this.setPadding(new Insets(20));
		
		VBox vbCenter = new VBox();
		vbCenter.getChildren().addAll(getBtnUser(), getBtnAdmin());
		vbCenter.setAlignment(Pos.CENTER);
		vbCenter.setSpacing(40.);
		this.setCenter(vbCenter);
		
		VBox vbBottom = new VBox();
		vbBottom.getChildren().addAll(getLblNew(),getLblCreateUser());
		vbBottom.setAlignment(Pos.CENTER);
		vbBottom.setPadding(new Insets(0,0,50,0));
		this.setBottom(vbBottom);

	}

	public ImageView getImgTitle() {
        if (imgTitle == null) {
        	imgTitle = new ImageView(new Image("images/base/Connexion.png"));
        }
        return imgTitle;
    }

	public Button getBtnUser() {
		if (btnUser == null) {
			btnUser = new Button("User");
			
			btnUser.getStyleClass().add("buttonBasic");
            btnUser.setId("big-button");
            
            btnUser.setOnAction(event -> Main.switchScene(new UserLoginSP()));
		}
		return btnUser;
	}

	public Button getBtnAdmin() {
		if (btnAdmin == null) {
			btnAdmin = new Button("Admin");
			
			btnAdmin.getStyleClass().add("buttonBasic");
			btnAdmin.setId("big-button");
			
			btnAdmin.setOnAction(event -> Main.switchScene(new AdminLoginSP()));
		}
		return btnAdmin;
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
			lblCreateUser = new Label("Create user");
			
			lblCreateUser.getStyleClass().add("labelSmall");
			
			lblCreateUser.setOnMouseClicked(event -> Main.switchScene(new CreateUserSP()));
		}
		return lblCreateUser;
	}
	
}
