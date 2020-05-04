package view.admin;

import application.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.IteratorQuestion;
import model.Question;
import utils.utility.BackgroundLoader;
import utils.controler.JsonManager;
import utils.TableView.CommonTableView;

import java.util.List;

public class TableViewBP extends BorderPane {

    private TableView<String> tvThemes;

    private Label lblTitle;

    private TextField txtAddTheme;

    private Button btnBack, btnAddTheme;

    public TableViewBP() {
        this.setBackground(BackgroundLoader.builderBackGround());

        this.setPadding(new Insets(10.));
        setAlignment(getLblTitle(), Pos.CENTER);
        this.setTop(getLblTitle());

        setAlignment(getTvThemes(), Pos.CENTER);
        this.setCenter(getTvThemes());

        HBox hbAddTheme = new HBox(getTxtAddTheme(), getBtnAddTheme());
        hbAddTheme.setPadding(new Insets(10.));
        hbAddTheme.setSpacing(10.);
        hbAddTheme.setAlignment(Pos.CENTER);
        VBox vbBottom = new VBox(hbAddTheme, getBtnBack());
        vbBottom.setSpacing(10.);
        vbBottom.setPadding(new Insets(10.));
        vbBottom.setAlignment(Pos.CENTER);
        this.setBottom(vbBottom);
        contexMenu();
    }


    public TableView<String> getTvThemes() {
        if (tvThemes == null) {
            tvThemes = new TableView<>();
            tvThemes.setMaxWidth(250.);
            CommonTableView.changeHeight(tvThemes);
            Main.getStage().heightProperty().addListener(observable -> CommonTableView.changeHeight(tvThemes));

            TableColumn<String, String> tcTheme = new TableColumn<>("theme");
            tcTheme.setMinWidth(248.);

            tcTheme.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));


            tvThemes.getColumns().add(tcTheme);
            tvThemes.setItems(FXCollections.observableArrayList(JsonManager.getThemes()));

            tvThemes.setOnMouseClicked(event -> {
                String element = tvThemes.getSelectionModel().getSelectedItem();
                if (JsonManager.getThemes().contains(element)) {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        Main.switchScene(new TableViewThemeSP(element));
                    }
                }
            });
        }
        return tvThemes;
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
            btnBack.setBackground(BackgroundLoader.buildBtnBackGround());
            btnBack.getStyleClass().add("buttonBasic");
            btnBack.setId("big-button");
            btnBack.setOnAction(event -> Main.switchScene(new AdminChoiceSP()));
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
            btnAddTheme = new Button("", new ImageView(new Image("asset/images/icon/validation.png",
                    30, 30, true, true)));
            btnAddTheme.getStyleClass().add("round");
            btnAddTheme.setOnAction(event -> {
                String newTheme = CommonTableView.upperLowerText(getTxtAddTheme().getText());
                if (!newTheme.equals("")) {
                    if (!JsonManager.getThemes().contains(newTheme)) {
                        JsonManager.getThemes().add(newTheme);
                        getTvThemes().getItems().add(newTheme);
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

    public void contexMenu() {
        ContextMenu menu = new ContextMenu();

        MenuItem remove = new MenuItem("Remove");
        remove.setOnAction(event -> {
            String sRemoved = getTvThemes().getSelectionModel().getSelectedItem();
            if (JsonManager.getThemes().remove(sRemoved)) {
                getTvThemes().getItems().remove(sRemoved);
                List<Question> qRemoved = JsonManager.choiceTheme(sRemoved);
                JsonManager.getDeck().getList().removeAll(qRemoved);
            }
        });

        menu.getItems().add(remove);
        getTvThemes().setContextMenu(menu);
    }
}
