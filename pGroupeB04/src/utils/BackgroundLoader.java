package utils;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Screen;

public abstract class BackgroundLoader {

    /**
     *
     * @return e
     */
    public static Background builderBackGround() {
        return new Background(new BackgroundImage(new Image("images/background/Background.png"), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(Screen.getPrimary().getVisualBounds().getWidth(),
                        Screen.getPrimary().getVisualBounds().getHeight(), false, false,
                        true, true)));
    }

    public static Background buildBtnBackGround() {
        return new Background(new BackgroundImage(new Image("images/background/button/button.png"), BackgroundRepeat.SPACE,
                BackgroundRepeat.SPACE, BackgroundPosition.DEFAULT,
                new BackgroundSize(350, 90, false, false, false,
                        false)));
    }

    public static Background buildBookGame() {
        return new Background(new BackgroundImage(new Image("images/background/mainGame/book.png"), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(780,450,false,false,false,
                        false)));
    }

    public static Background buildDialogBackGround(double wigth, double height) {
        return new Background(new BackgroundImage(new Image("images/element/ExitMenu.png"), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(wigth,height,false,false,false,
                        false)));
    }

    public static Background buildTimerBackGround() {
        return new Background(new BackgroundImage(new Image("images/element/PointBoard.png"), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(100.,109.,false,false,false,
                        true)));
    }
}
