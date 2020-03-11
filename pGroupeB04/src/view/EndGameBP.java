package view;

import javafx.scene.layout.BorderPane;
import model.BackgroundLoader;

public class EndGameBP extends BorderPane{

	public EndGameBP() {
        //BACKGROUND
        this.setBackground(BackgroundLoader.builderBackGround());
	}
	
}
