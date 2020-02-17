package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;

public class GameMainPage extends BorderPane {

    private TextField txtAnswer;
    private Button btnSkip;
    private Button btnValidate;


	public GameMainPage() {
        //BACKGROUND
        BackgroundImage bgiMainPage = new BackgroundImage(new Image("images/gameImage.png"),BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT , BackgroundPosition.DEFAULT,
                new BackgroundSize(Screen.getPrimary().getVisualBounds().getWidth(),
                Screen.getPrimary().getVisualBounds().getHeight(),false,false,
                true,true));

        this.setBackground(new Background(bgiMainPage));
        
        //CENTER
        
        VBox vbIndices = new VBox();
        
        /*Text indice = new Text("This is a text example");
        indice.setY(50);*/

        HBox hbReponse = new HBox();
        hbReponse.getChildren().addAll(getBtnSkip(),getTxtAnswer(), getBtnValidate());
        hbReponse.setPadding(new Insets(370, 100, 10, 430));
        hbReponse.setSpacing(30);
        getTxtAnswer().setPromptText("Write your answer");
        
        vbIndices.getChildren().addAll(hbReponse);
        this.setCenter(vbIndices);
    }


	public TextField getTxtAnswer() {
		if (txtAnswer == null) {
			txtAnswer = new TextField();
		}
		return txtAnswer;
	}
    public Button getBtnSkip() {
    	if (btnSkip == null) {
    		btnSkip = new Button("Skip");
    		btnSkip.getStyleClass().add("buttonBasic");
    	}
		return btnSkip;
	}


	public Button getBtnValidate() {
		if (btnValidate == null) {
			btnValidate = new Button("Validate");
			btnValidate.getStyleClass().add("buttonBasic");
		}
		return btnValidate;
	}
}