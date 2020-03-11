package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.BackgroundLoader;

public class ChoiceThemeBP extends BorderPane{
	private ImageView choiceThemeTitle;
	private Button thm1,thm2,thm3,thm4;
	private static Button validate;
	public ChoiceThemeBP() {
		this.setBackground(BackgroundLoader.builderBackGround());

		HBox hTop=new HBox();
		hTop.getChildren().addAll(getChoiceThemeTitle());
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
		vLeft.setSpacing(100.);

		VBox vRight=new VBox();
		vRight.getChildren().addAll(getThm2(),getThm4());
		vRight.setAlignment(Pos.CENTER_LEFT);
		vRight.setSpacing(100.);
		
		HBox hTheme=new HBox();
		hTheme.getChildren().addAll(vLeft,vRight);
		hTheme.setSpacing(100);
		hTheme.setAlignment(Pos.CENTER);
		this.setCenter(hTheme);
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
			validate.getStyleClass().add("buttonGame");
		}return validate;
	}
	public ImageView getChoiceThemeTitle() {
		if(choiceThemeTitle==null) {
			choiceThemeTitle=new ImageView("images/base/ChooseThemeLabel.png");
		}
		return choiceThemeTitle;
	}

}
