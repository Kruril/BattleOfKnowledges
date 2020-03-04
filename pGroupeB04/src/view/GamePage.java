package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.BackGroundLoader;

public class GamePage extends BorderPane{
	
	private Label lblTitle;
	
	private Label lblClue1;
	private Label lblClue2;
	private Label lblClue3;
	
	private Label lblPoint0;
	private Label lblPoint1;
	private Label lblPoint2;
	private Label lblPoint3;
	private Label lblPoint4;
	private Label lblPoints;
	
	private Label lblTimer;
	
	private Button btnSkip;
	private TextField txtAnswer;
	private Button btnOk;
	
	public GamePage() {
        //BACKGROUND
        this.setBackground(BackGroundLoader.builderBackGround());
		
		//TOP
		HBox hbTop = new HBox();
		hbTop.getChildren().addAll(getLblTitle());
		hbTop.setAlignment(Pos.CENTER);
		hbTop.setPadding(new Insets(20,0,0,0));
		this.setTop(hbTop);
		
		//CENTER
		HBox hbCenter = new HBox();
		VBox vbPoints = new VBox();
		vbPoints.getChildren().addAll(getLblPoint4(), getLblPoint3(), getLblPoint2(), getLblPoint1(), getLblPoint0());
		vbPoints.setAlignment(Pos.CENTER);
		vbPoints.getStyleClass().add("vboxPoints");
		vbPoints.setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
		vbPoints.setSpacing(10);
		
		VBox vbClues = new VBox();
		vbClues.getChildren().addAll(getLblClue1(), getLblClue2(), getLblClue3());
		vbClues.setAlignment(Pos.CENTER);
		vbClues.setSpacing(15);
		
		VBox vbTimer = new VBox();
		vbTimer.getChildren().addAll(getLblTimer());
		vbTimer.setAlignment(Pos.CENTER);
		vbTimer.setSpacing(15);
		
		hbCenter.getChildren().addAll(vbPoints, vbClues, vbTimer);
		hbCenter.setAlignment(Pos.CENTER_LEFT);
		hbCenter.setSpacing(50);
		hbCenter.setPadding(new Insets(0,0,0,40));
		this.setCenter(hbCenter);
		
		//BOTTOM
		HBox hbBottom = new HBox();
		hbBottom.getChildren().addAll(getBtnSkip(), getTxtAnswer(), getBtnOk());
		hbBottom.setSpacing(10);
		hbBottom.setAlignment(Pos.CENTER);
		hbBottom.setPadding(new Insets(0,0,30,0));
		this.setBottom(hbBottom);
	}

	public Label getLblTitle() {
		if (lblTitle == null) {
			lblTitle = new Label("Ici le thème");
			lblTitle.getStyleClass().addAll("labelBasique","labelTitle");
		}
		return lblTitle;
	}

	public Label getLblClue1() {
		if (lblClue1 == null) {
			lblClue1 = new Label("Ici Clue 1");
			lblClue1.getStyleClass().addAll("labelBasique","labelClue");
		}
		return lblClue1;
	}

	public Label getLblClue2() {
		if (lblClue2 == null) {
			lblClue2 = new Label("Ici Clue 2");
			lblClue2.getStyleClass().addAll("labelBasique","labelClue");
		}
		return lblClue2;
	}

	public Label getLblClue3() {
		if (lblClue3 == null) {
			lblClue3 = new Label("Ici Clue 3");
			lblClue3.getStyleClass().addAll("labelBasique","labelClue");
		}
		return lblClue3;
	}

	public Label getLblPoint() {
		if (lblPoints == null) {
			lblPoints = new Label();
		}
		return lblPoints;
	}
	
	public Label getLblPoint0() {
		if (lblPoint0 == null) {
			lblPoint0 = new Label("0");
			lblPoint0.getStyleClass().addAll("labelBasique","labelPoints");
		}
		return lblPoint0;
	}

	public Label getLblPoint1() {
		if (lblPoint1 == null) {
			lblPoint1 = new Label("1");
			lblPoint1.getStyleClass().addAll("labelBasique","labelPoints");

		}
		return lblPoint1;
	}

	public Label getLblPoint2() {
		if (lblPoint2 == null) {
			lblPoint2 = new Label("2");
			lblPoint2.getStyleClass().addAll("labelBasique","labelPoints");

		}
		return lblPoint2;
	}

	public Label getLblPoint3() {
		if (lblPoint3 == null) {
			lblPoint3 = new Label("3");
			lblPoint3.getStyleClass().addAll("labelBasique","labelPoints");
		}
		return lblPoint3;
	}

	public Label getLblPoint4() {
		if (lblPoint4 == null) {
			lblPoint4 = new Label("4");
			lblPoint4.getStyleClass().addAll("labelBasique","labelPoints");
		}
		return lblPoint4;
	}

	public Label getLblTimer() {
		if (lblTimer == null) {
			lblTimer = new Label("Ici Timer");
			lblTimer.getStyleClass().addAll("labelBasique","labelPoints");
		}
		return lblTimer;
	}

	public Button getBtnSkip() {
		if (btnSkip == null) {
			btnSkip = new Button("Skip"); 
			btnSkip.getStyleClass().add("buttonBasic");
		}
		return btnSkip;
	}

	public TextField getTxtAnswer() {
		if (txtAnswer == null) {
			txtAnswer = new TextField();
			txtAnswer.getStyleClass().add("textField");
		}
		return txtAnswer;
	}

	public Button getBtnOk() {
		if (btnOk == null) {
			btnOk = new Button("Ok");
			btnOk.getStyleClass().addAll("buttonBasic","buttonTextField");
		}
		return btnOk;
	}
	
	
}
