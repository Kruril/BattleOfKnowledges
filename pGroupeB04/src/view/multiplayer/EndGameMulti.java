package view.multiplayer;

import application.Main;
import enumeration.TypeGame;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import utils.user.Player;
import utils.utility.BackgroundLoader;
import view.MainPageSP;

public class EndGameMulti extends StackPane{

	private int point;

	private Label lblCurrentUser, lblUser2, lblUser3, lblUser4;

	private ImageView ivTitle;

	private Button btnExit;
	
	public EndGameMulti(int point) {
		this.point = point;
		this.setBackground(BackgroundLoader.builderBackGround());

		setAlignment(getIvTitle() , Pos.TOP_CENTER);

		VBox vbContainerScore = new VBox(getLblCurrentUser(), getLblUser2(), getLblUser3(), getLblUser4());
		vbContainerScore.setAlignment(Pos.CENTER);
		vbContainerScore.setSpacing(10.);

		setAlignment(vbContainerScore, Pos.CENTER);

		setAlignment(getBtnExit(), Pos.BOTTOM_CENTER);

		getChildren().addAll(getIvTitle(),vbContainerScore, getBtnExit());
	}

	public ImageView getIvTitle() {
		if (ivTitle == null) {
			ivTitle = new javafx.scene.image.ImageView(new Image("asset/images/title/Scores.png"));
		}
		return ivTitle;
	}
	public Label getLblCurrentUser() {
		if (lblCurrentUser == null) {
			lblCurrentUser = new Label(Player.getName() + " " + point);
			lblCurrentUser.getStyleClass().add("waitingPlayer");
		}
		return lblCurrentUser;
	}

	public Label getLblUser2() {
		if (lblUser2 == null) {
			lblUser2 = new Label("waiting player...");
			lblUser2.getStyleClass().add("waitingPlayer");
		}
		return lblUser2;
	}

	public Label getLblUser3() {
		if (lblUser3 == null) {
			lblUser3 = new Label("waiting player...");
			lblUser3.getStyleClass().add("waitingPlayer");
		}
		return lblUser3;
	}

	public Label getLblUser4() {
		if (lblUser4 == null) {
			lblUser4 = new Label("waiting player...");
			lblUser4.getStyleClass().add("waitingPlayer");
		}
		return lblUser4;
	}

	public Button getBtnExit() {
		if (btnExit == null) {
			btnExit = new Button("Return to menu");
			btnExit.setBackground(BackgroundLoader.buildBtnBackGround());
			btnExit.setId("big-button");
			btnExit.getStyleClass().add("buttonBasic");
			btnExit.setOnAction(event -> {
				switch (TypeGame.TYPEGAME.getValue()) {
					case "CLIENT":
						Main.getClient().close();
						break;
					case "SERVER":
						Main.getServer().shutdown();
						break;
				}
				Main.switchScene(new MainPageSP());
			});
		}
		return btnExit;
	}
}
