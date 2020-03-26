package view;

import java.util.ArrayList;
import java.util.List;

import application.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.util.Callback;
import utils.BackgroundLoader;
import model.Question;
import utils.JsonManager;

public class TableViewBP extends BorderPane {

    private TableView<Question> tvQuestions;

    private Label lblTitle;

    private Button btnBack;

    List<Question> questions = new ArrayList<>();


    public TableViewBP() {
        this.setBackground(BackgroundLoader.builderBackGround());

        getQuestions();

        HBox hbTop = new HBox();
        hbTop.getChildren().add(getLblTitle());
        hbTop.setAlignment(Pos.TOP_CENTER);
        this.setTop(hbTop);

        HBox hbCenter = new HBox();
        hbCenter.getChildren().add(getTvQuestions());
        hbCenter.setAlignment(Pos.CENTER);
        hbCenter.setPadding(new Insets(10, 10, 10, 10));
        this.setCenter(hbCenter);

        HBox hbBottom = new HBox();
        hbBottom.getChildren().add(getBtnBack());
        hbBottom.setAlignment(Pos.BOTTOM_CENTER);
        this.setBottom(hbBottom);
    }


    public TableView getTvQuestions() {
        if (tvQuestions == null) {
            tvQuestions = new TableView<>();
            
            TableColumn<Question, String> tcTheme = new TableColumn<>("theme");
            
            tcTheme.setCellValueFactory(new PropertyValueFactory<>("theme"));
            
            
            tvQuestions.getColumns().addAll(tcTheme);
            tvQuestions.setItems(FXCollections.observableArrayList(questions));
            
            tvQuestions.setOnMouseClicked(event -> Main.switchScene(new TableViewThemeBP(tvQuestions.getSelectionModel().getSelectedItem().getTheme())));
        }
        return tvQuestions;
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


    /**
     * To generate all questions of Json file
     */

    public void getQuestions() {
        for (String themes : JsonManager.THEMES) {
            questions.add(JsonManager.choiceTheme(themes).get(0));
        }
    }

  

}
