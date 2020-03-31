package view;

import application.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.BackgroundLoader;
import utils.JsonManager;

import java.util.Locale;

public class TableViewBP extends BorderPane {

    private TableView<String> tvQuestions;

    private Label lblTitle;

    private TextField txtAddTheme;

    private Button btnBack, btnAddTheme;

    public TableViewBP() {
        this.setBackground(BackgroundLoader.builderBackGround());

        this.setPadding(new Insets(10.));
        setAlignment(getLblTitle(), Pos.CENTER);
        this.setTop(getLblTitle());

        setAlignment(getTvQuestions(), Pos.CENTER);
        this.setCenter(getTvQuestions());

        HBox hbAddTheme = new HBox(getTxtAddTheme(), getBtnAddTheme());
        hbAddTheme.setPadding(new Insets(10.));
        hbAddTheme.setSpacing(10.);
        hbAddTheme.setAlignment(Pos.CENTER);
        VBox vbBottom = new VBox(hbAddTheme, getBtnBack());
        vbBottom.setSpacing(10.);
        vbBottom.setPadding(new Insets(10.));
        vbBottom.setAlignment(Pos.CENTER);
        this.setBottom(vbBottom);
    }


    public TableView<String> getTvQuestions() {
        if (tvQuestions == null) {
            tvQuestions = new TableView<>();
            tvQuestions.setMaxWidth(250.);
            changeHeight();
            Main.getStage().heightProperty().addListener(observable -> changeHeight());

            TableColumn<String, String> tcTheme = new TableColumn<>("theme");
            tcTheme.setMinWidth(248.);

            tcTheme.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));


            tvQuestions.getColumns().add(tcTheme);
            tvQuestions.setItems(FXCollections.observableArrayList(JsonManager.getThemes()));

            tvQuestions.setOnMouseClicked(event -> {
                String element = tvQuestions.getSelectionModel().getSelectedItem();
                if (JsonManager.getThemes().contains(element)) {
                    Main.switchScene(new TableViewThemeBP(element));
                }
            });
        }
        return tvQuestions;
    }

    public void changeHeight() {
        getTvQuestions().setMaxHeight(Main.getStage().getHeight() - 290);
    }


    public Label getLblTitle() {
        if (lblTitle == null) {
            lblTitle = new Label("Admin");
            lblTitle.getStyleClass().add("labelTitle");
        }
        return lblTitle;
    }


    public Button getBtnBack() {
        if (btnBack == null) {
            btnBack = new Button("Back");
            btnBack.getStyleClass().add("buttonBasic");
            btnBack.setId("big-button");
            btnBack.setOnAction(event -> Main.switchScene(new MainPageSP()));
        }
        return btnBack;
    }

    public TextField getTxtAddTheme() {
        if (txtAddTheme == null) {
            txtAddTheme = new TextField();
            txtAddTheme.setPromptText("Enter new theme");

        }
        return txtAddTheme;
    }

    public Button getBtnAddTheme() {
        if (btnAddTheme == null) {
            btnAddTheme = new Button("", new ImageView(new Image("images/icon/validation.png",
                    30, 30, true, true)));
            btnAddTheme.getStyleClass().add("round");
            btnAddTheme.setOnAction(event -> {
                String newTheme = upperFirstLetter(getTxtAddTheme().getText());;
                if (!newTheme.equals("")) {
                    if (!JsonManager.getThemes().contains(newTheme)) {
                        JsonManager.getThemes().add(newTheme);
                        getTvQuestions().getItems().add(newTheme);
                    }
                    else {
                        getTxtAddTheme().setPromptText("Theme already present");
                    }
                    getTxtAddTheme().setText("");
                }
            });
        }
        return btnAddTheme;
    }

    public String upperFirstLetter(String element) {
        char[] tmp = element.toCharArray();
        if (tmp.length <= 0) return "";
        tmp[0] = Character.toUpperCase(tmp[0]);
        return new String(tmp);
    }
}
