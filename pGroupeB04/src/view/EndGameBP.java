package view;

import application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.BackgroundLoader;

public class EndGameBP extends BorderPane{
	
	private ImageView imgCongrats;
	
	private Label lblPoints1;
	private Label lblPoints2;
	private Label lblPoints3;
	
	private ImageView imgPlayAgain;
	private Button btnPlay;
	private Button btnMenu;

	private int pointWon;
	
	
	public EndGameBP() {
        //BACKGROUND
        this.setBackground(BackgroundLoader.builderBackGround());
        
        //TOP
        HBox hbImage = new HBox();
        hbImage.getChildren().addAll(getImgCongrats());
        hbImage.setAlignment(Pos.CENTER);
        hbImage.setPadding(new Insets(20));
        this.setTop(hbImage);
        
        //CENTER
        HBox hbPoints = new HBox();
        hbPoints.getChildren().addAll(getLblPoints1(), getLblPoints2(), getLblPoints3());
        hbPoints.setAlignment(Pos.CENTER);
        this.setCenter(hbPoints);
        
        //BOTTOM
        VBox vbBottom = new VBox();
        HBox hbImgPlayAgain = new HBox();
        hbImgPlayAgain.getChildren().addAll(getImgPlayAgain());
        hbImgPlayAgain.setAlignment(Pos.CENTER);
        hbImgPlayAgain.setPadding(new Insets(20));
        HBox hbPlayAgain = new HBox();
        hbPlayAgain.getChildren().addAll(getBtnPlay(), getBtnMenu());
        hbPlayAgain.setAlignment(Pos.CENTER);
        hbPlayAgain.setSpacing(60.);
        vbBottom.getChildren().addAll(hbImgPlayAgain, hbPlayAgain);
        vbBottom.setSpacing(20.);
        vbBottom.setAlignment(Pos.CENTER);
        vbBottom.setPadding(new Insets(20));
        this.setBottom(vbBottom);
        
	}
	
	public ImageView getImgCongrats() {
		if (imgCongrats == null) {
			imgCongrats = new ImageView(new Image("images/base/Congratulations.png"));
		}
		return imgCongrats;
	}

	public Label getLblPoints1() {
		if (lblPoints1 == null) {
			lblPoints1 = new Label("You obtained ");
			lblPoints1.getStyleClass().addAll("labelBasique");
		}
		return lblPoints1;
	}

	public Label getLblPoints2() {
		if (lblPoints2 == null) {
			lblPoints2 = new Label("INSERT POINTS");
			lblPoints2.getStyleClass().addAll("labelBasique");
		}
		return lblPoints2;
	}


	public Label getLblPoints3() {
		if (lblPoints3 == null) {
			lblPoints3 = new Label(" points !");
			lblPoints3.getStyleClass().addAll("labelBasique");
		}
		return lblPoints3;
	}

	public ImageView getImgPlayAgain() {
		if (imgPlayAgain == null) {
			imgPlayAgain = new ImageView(new Image("images/base/PlayAgain.png"));
		}
		return imgPlayAgain;
	}

	public Button getBtnPlay() {
		if (btnPlay == null) {
			btnPlay = new Button("Yes");
			btnPlay.getStyleClass().add("buttonBasic");
            btnPlay.setId("small-button");
			
			btnPlay.setOnAction(event -> Main.switchScene(new ChoiceThemeBP()));
		}
		return btnPlay;
	}

	public Button getBtnMenu() {
		if (btnMenu == null) {
			btnMenu = new Button("No");
			btnMenu.getStyleClass().add("buttonBasic");
            btnMenu.setId("small-button");
			
			btnMenu.setOnAction(event -> Main.switchScene(new MainPageSP()));
		}
		return btnMenu;
	}
		
}
