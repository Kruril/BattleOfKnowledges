package model;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Screen;

public abstract class BackgroundLoader {

        public static Background builderBackGround() {
            return new Background(new BackgroundImage(new Image("images/base/Background.png"), BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT , BackgroundPosition.DEFAULT,
                    new BackgroundSize(Screen.getPrimary().getVisualBounds().getWidth(),
                            Screen.getPrimary().getVisualBounds().getHeight(),false,false,
                            true,true)));
    }
}
