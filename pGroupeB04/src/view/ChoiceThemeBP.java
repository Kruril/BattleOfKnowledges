package view;

import application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.BackgroundLoader;

public class ChoiceThemeBP extends BorderPane {
    private ImageView ivTitle;
    private Button btnThm1, btnThm2, btnThm3, btnThm4;
    private Button btnBack;

    public ChoiceThemeBP() {
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
        vLeft.setAlignment(Pos.CENTER_RIGHT);
        vLeft.setSpacing(100.);

        VBox vRight = new VBox();
        vRight.getChildren().addAll(getBtnThm2(), getBtnThm4());
        vRight.setAlignment(Pos.CENTER_LEFT);
        vRight.setSpacing(100.);

        HBox hTheme = new HBox();
        hTheme.getChildren().addAll(vLeft, vRight);
        hTheme.setSpacing(100);
        hTheme.setAlignment(Pos.CENTER);
        this.setCenter(hTheme);
    }

    public Button getBtnThm1() {
        if (btnThm1 == null) {
            btnThm1 = new Button("Theme 1");
            btnThm1.getStyleClass().add("buttonBasic");
        }
        return btnThm1;
    }

    public Button getBtnThm2() {
        if (btnThm2 == null) {
            btnThm2 = new Button("Theme 2");
            btnThm2.getStyleClass().add("buttonBasic");
        }
        return btnThm2;
    }

    public Button getBtnThm3() {
        if (btnThm3 == null) {
            btnThm3 = new Button("Theme 3");
            btnThm3.getStyleClass().add("buttonBasic");
        }
        return btnThm3;
    }

    public Button getBtnThm4() {
        if (btnThm4 == null) {
            btnThm4 = new Button("Theme 4");
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

}
