package utils;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Screen;

public abstract class BackgroundLoader {

    /**
     * Will create the background of the game
     * @return a background available for all scenes
     */
    public static Background builderBackGround() {
        return new Background(new BackgroundImage(new Image("images/background/Background.png"), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(Screen.getPrimary().getVisualBounds().getWidth(),
                        Screen.getPrimary().getVisualBounds().getHeight(), false, false,
                        true, true)));
    }

    /**
     * Will create the background for button
     * @return a background available for all buttons
     */
    public static Background buildBtnBackGround() {
        return new Background(new BackgroundImage(new Image("images/background/button/button.png"), BackgroundRepeat.SPACE,
                BackgroundRepeat.SPACE, BackgroundPosition.DEFAULT,
                new BackgroundSize(350, 90, false, false, false,
                        false)));
    }

    /**
     * Will create the background for clues
     * @return a background available for clues
     */
    public static Background buildBookGame() {
        return new Background(new BackgroundImage(new Image("images/background/mainGame/book.png"), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(780,450,false,false,false,
                        false)));
    }

    /**
     * Will create the background for dialog of the game
     * @param wight wight for dialog
     * @param height height for dialog
     * @return a background with size you want available for all dialogs
     */
    public static Background buildDialogBackGround(double wight, double height) {
        return new Background(new BackgroundImage(new Image("images/element/ExitMenu.png"), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(wight,height,false,false,false,
                        false)));
    }

    /**
     * Will create the background for timer
     * @return a background available for timer
     */
    public static Background buildTimerBackGround() {
        return new Background(new BackgroundImage(new Image("images/element/PointBoard.png"), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(100.,109.,false,false,false,
                        true)));
    }
}
