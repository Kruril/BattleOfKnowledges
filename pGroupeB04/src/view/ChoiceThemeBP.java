package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.BackgroundLoader;
import model.Deck;
import model.Question;


public class ChoiceThemeBP extends BorderPane {
    private ImageView ivTitle;
    private Button btnThm1, btnThm2, btnThm3, btnThm4,btnBack;
    
    private List<Button> buttons;
    private List<String> listThemes=new ArrayList<String>();
    private List<String> Themes=new ArrayList<String>();
    
    public ChoiceThemeBP() {
        AttributeTheme();
    	
    	this.setBackground(BackgroundLoader.builderBackGround());

        HBox hTop = new HBox();
        hTop.getChildren().addAll(getIvTitle());
        hTop.setAlignment(Pos.CENTER);
        hTop.setPadding(new Insets(50, 5, 10, 5));
        hTop.setPrefSize(150., 50.);
        this.setTop(hTop);

        HBox hBottom = new HBox();
        hBottom.getChildren().addAll(getBtnBack());
        hBottom.setAlignment(Pos.CENTER);
        hBottom.setPadding(new Insets(10, 5, 50, 5));
        hBottom.setPrefSize(150., 25.);
        this.setBottom(hBottom);

        VBox vLeft = new VBox();
        vLeft.getChildren().addAll(getBtnThm1(), getBtnThm3());
        vLeft.setSpacing(100.);

        VBox vRight = new VBox();
        vRight.getChildren().addAll(getBtnThm2(), getBtnThm4());
        vRight.setSpacing(100.);

        HBox hTheme = new HBox();
        hTheme.getChildren().addAll(vLeft, vRight);
        hTheme.setSpacing(100);
        hTheme.setAlignment(Pos.CENTER);
        this.setCenter(hTheme);
    }

    public Button getBtnThm1() {
        if (btnThm1 == null) {
            btnThm1 = new Button(Themes.get(0));
            btnThm1.getStyleClass().add("buttonBasic");
        }
        return btnThm1;
    }

    public Button getBtnThm2() {
        if (btnThm2 == null) {
            btnThm2 = new Button(Themes.get(1));
            btnThm2.getStyleClass().add("buttonBasic");
        }
        return btnThm2;
    }

    public Button getBtnThm3() {
        if (btnThm3 == null) {
            btnThm3 = new Button(Themes.get(2));
            btnThm3.getStyleClass().add("buttonBasic");
        }
        return btnThm3;
    }

    public Button getBtnThm4() {
        if (btnThm4 == null) {
            btnThm4 = new Button(Themes.get(3));
            btnThm4.getStyleClass().add("buttonBasic");
        }
        return btnThm4;
    }

    public Button getBtnBack() {
        if (btnBack == null) {
            btnBack = new Button("Back");
            btnBack.getStyleClass().add("buttonGame");
            btnBack.setOnAction(event -> Main.switchScene(new MainPageSP()));
        }
        return btnBack;
    }

    public ImageView getIvTitle() {
        if (ivTitle == null) {
            ivTitle = new ImageView("images/base/ChooseThemeLabel.png");
        }
        return ivTitle;
    }
    
    public void AttributeTheme() {
    	Deck deck=new Deck();
    	
    	deck.fromJson("pGroupeB04/src/json/theme/theme.json");
    	
    	for(Question quest:deck.getListe()) {
    		if(!(listThemes.contains(quest.getTheme()))) {
    			listThemes.add(quest.getTheme());
    		}
    	}
    	
    	RandomTheme();
    	
    }
    
    
    public void RandomTheme() {
    	int index,themeAttributed,nbTheme=0;
    	Random rand=new Random();
    	String theme;
    	boolean isAdded=false;
    	for(String thm:listThemes) {
    		nbTheme++;
    	}
    	
    	
    	for(index=1;index<=4;index++) {
    		while(isAdded==false) {
        		themeAttributed=rand.nextInt(nbTheme);
        		theme= listThemes.get(themeAttributed);
        		
        		if(!(Themes.contains(theme))) {
        			Themes.add(theme);
        			isAdded=true;
        		}
    		}
    		isAdded=false;
    	}
    }
    
    
	public List<Button> getButtons() {
		if(buttons==null) {
			buttons=new ArrayList<Button>();
			buttons.add(btnThm1);
			buttons.add(btnThm2);
			buttons.add(btnThm3);
			buttons.add(btnThm4);
		}
		return buttons;
		
	}
}
