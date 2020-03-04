package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import model.BackGroundLoader;

public class ChoiceThemeGP extends GridPane{
	private Label theme;
	private Button thm1,thm2,thm3,thm4;
	private static Button validate;
	public ChoiceThemeGP() {
        //BACKGROUND
        this.setBackground(BackGroundLoader.builderBackGround());
        
        this.add(getTheme(), 1, 1, 5, 1);
        this.add(getThm1(),0,3);
        this.add(getThm2(),6,3);
        this.add(getThm3(),0,10);
        this.add(getThm4(),6,10);
        this.add(getValidate(), 6, 15);
	}
	public Label getTheme() {
		if(theme==null) {
			theme=new Label("Choose Your Theme");
			theme.getStyleClass().add("labelBasique");
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
