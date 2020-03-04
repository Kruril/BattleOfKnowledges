/*package view;

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
	
	
}*/
package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
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

public class ChoiceThemeAP extends AnchorPane{
	private Label theme;
	private Button thm1,thm2,thm3,thm4;
	private static Button validate;
	public ChoiceThemeAP() {
        //BACKGROUND
        this.setBackground(BackGroundLoader.builderBackGround());
        
        this.getChildren().addAll(getThm1(),getThm2(),getThm3(),getThm4(),getTheme(),getValidate());
        
        //GetValidate()
        AnchorPane.setBottomAnchor(getValidate(), 10.);
        AnchorPane.setLeftAnchor(getValidate(), 100.);
        AnchorPane.setRightAnchor(getValidate(),100.);
        //GetTheme()
        AnchorPane.setTopAnchor(getTheme(), 10.);
        AnchorPane.setLeftAnchor(getTheme(), 10.);
        AnchorPane.setRightAnchor(getTheme(),10.);
        //GetThm1()
        AnchorPane.setTopAnchor(getThm1(), 200.);
        AnchorPane.setLeftAnchor(getThm1(), 100.);
        AnchorPane.setRightAnchor(getThm1(),750.);
        //GetThm2()
        AnchorPane.setTopAnchor(getThm2(), 200.);
        AnchorPane.setLeftAnchor(getThm2(), 750.);
        AnchorPane.setRightAnchor(getThm2(),100.);
        //GetThm3()
        AnchorPane.setTopAnchor(getThm3(), 400.);
        AnchorPane.setLeftAnchor(getThm3(), 100.);
        AnchorPane.setRightAnchor(getThm3(),750.);
        //GetThm4()
        AnchorPane.setTopAnchor(getThm4(), 400.);
        AnchorPane.setLeftAnchor(getThm4(), 750.);
        AnchorPane.setRightAnchor(getThm4(),100.);
        
	}
	public Label getTheme() {
		if(theme==null) {
			theme=new Label("Choose Your Theme");
			theme.getStyleClass().addAll("labelBasique", "labelTitle");
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

