package view;

import application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import utils.BackgroundLoader;

public class AdminChoiceSP extends StackPane{

	private ImageView imgTitre;
	
	private Button btnModifQuestion;
    private Button btnModifTime;
    private Button btnBack;
    
    public AdminChoiceSP() {
    	
    	//BACKGROUND
        this.setBackground(BackgroundLoader.builderBackGround());

        //Top
        StackPane.setAlignment(getImgTitre(), Pos.TOP_CENTER);
        StackPane.setMargin(getImgTitre(), new Insets(20));
    	
        VBox vContainer = new VBox();
        
        vContainer.getChildren().addAll(getBtnModifQuestion(),getBtnModifTime(), getBtnBack());
        vContainer.setSpacing(50);
        vContainer.setAlignment(Pos.CENTER);
        vContainer.setMaxSize(550,300);
        
        StackPane.setAlignment(vContainer, Pos.CENTER);
        
        this.getChildren().addAll(getImgTitre(),vContainer);
    }

	public ImageView getImgTitre() {
		if (imgTitre == null) {
			imgTitre = new ImageView(new Image("images/title/Choose.png"));
		}
		return imgTitre;
	}

	public Button getBtnModifQuestion() {
		if (btnModifQuestion == null) {
			btnModifQuestion = new Button("Modify Json");
			btnModifQuestion.getStyleClass().addAll("buttonBasic");
			btnModifQuestion.setBackground(BackgroundLoader.buildBtnBackGround());
			btnModifQuestion.setId("big-button");
			
			btnModifQuestion.setOnAction(event -> Main.switchScene(new TableViewBP()));
		}
		return btnModifQuestion;
	}

	public Button getBtnModifTime() {
		if (btnModifTime == null) {
			btnModifTime = new Button("Modify time");
			btnModifTime.getStyleClass().addAll("buttonBasic");
			btnModifTime.setBackground(BackgroundLoader.buildBtnBackGround());
			btnModifTime.setId("big-button");
			
			btnModifTime.setOnAction(event -> Main.switchScene(new ModifTimeSP()));
		}
		return btnModifTime;
	}

	public Button getBtnBack() {
		if (btnBack == null) {
			btnBack = new Button("Back");
			btnBack.getStyleClass().addAll("buttonBasic");
			btnBack.setBackground(BackgroundLoader.buildBtnBackGround());
			btnBack.setId("big-button");
			
			btnBack.setOnAction(event -> Main.switchScene(new AdminLoginSP()));
		}
		return btnBack;
	}
    
    
	
}
