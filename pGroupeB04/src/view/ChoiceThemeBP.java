package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.BackGroundLoader;

public class ChoiceThemeBP extends BorderPane{
	private Label theme;
	private Button thm1,thm2,thm3,thm4;
	private static Button validate;
	public ChoiceThemeBP() {
		this.setBackground(BackGroundLoader.builderBackGround());

		HBox hTop=new HBox();
		hTop.getChildren().addAll(getTheme());
		hTop.setAlignment(Pos.CENTER);
		hTop.setPadding(new Insets(50,5,10,5));
		hTop.setPrefSize(150., 50.);
		this.setTop(hTop);

		HBox hBottom=new HBox();
		hBottom.getChildren().addAll(getValidate());
		hBottom.setAlignment(Pos.CENTER);
		hBottom.setPadding(new Insets(10,5,50,5));
		hBottom.setPrefSize(150., 25.);
		this.setBottom(hBottom);

		VBox vLeft=new VBox();
		vLeft.getChildren().addAll(getThm1(),getThm3());
		vLeft.setAlignment(Pos.CENTER_RIGHT);
		vLeft.setPadding(new Insets(10,10,10,50));
		vLeft.setSpacing(100.);
		this.setLeft(vLeft);

		VBox vRight=new VBox();
		vRight.getChildren().addAll(getThm2(),getThm4());
		vRight.setAlignment(Pos.CENTER_LEFT);
		vRight.setPadding(new Insets(10,50,10,10));
		vRight.setSpacing(100.);
		this.setRight(vRight);
	}
	public Label getTheme() {
		if(theme==null) {
			theme=new Label("Choose Your Theme");
			theme.getStyleClass().add("labelBasique");
			theme.setAlignment(Pos.CENTER);
		}
		return theme;
	}
	public Button getThm1() {
		if(thm1==null) {
			thm1=new Button("Theme 1");
			thm1.getStyleClass().add("buttonBasic");
		}return thm1;
	}
	public Button getThm2() {
		if(thm2==null) {
			thm2=new Button("Theme 2");
			thm2.getStyleClass().add("buttonBasic");
		}return thm2;
	}
	public Button getThm3() {
		if(thm3==null) {
			thm3=new Button("Theme 3");
			thm3.getStyleClass().add("buttonBasic");
		}return thm3;
	}
	public Button getThm4() {
		if(thm4==null) {
			thm4=new Button("Theme 4");
			thm4.getStyleClass().add("buttonBasic");
		}return thm4;
	}
	public static Button getValidate() {
		if(validate==null) {
			validate=new Button("Validate");
			validate.getStyleClass().add("buttonBasic");
		}return validate;
	}


}
