package view;

import application.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import utils.BackgroundLoader;
import utils.JsonManager;

public class TableViewBP extends BorderPane {

    private TableView<String> tvQuestions;

    private Label lblTitle;

    private Button btnBack;

    public TableViewBP() {
        this.setBackground(BackgroundLoader.builderBackGround());

        this.setPadding(new Insets(10.));
        setAlignment(getLblTitle(), Pos.CENTER);
        this.setTop(getLblTitle());

        setAlignment(getTvQuestions(), Pos.CENTER);
        this.setCenter(getTvQuestions());

        setAlignment(getBtnBack(), Pos.CENTER);
        this.setBottom(getBtnBack());
    }


    public TableView getTvQuestions() {
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

  

}
