package view;

import java.util.List;

import application.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import model.Question;
import utils.BackgroundLoader;
import utils.JsonManager;

public class TableViewThemeBP extends BorderPane{
	private Label lblTheme;
	
	private TableView<Question> tvQuestions;

	private String theme;
	private List<Question> questions;
	
	private Button btnBack;
	
	public TableViewThemeBP(String theme) {
		this.theme=theme;
		this.questions=JsonManager.choiceTheme(theme);
		this.setBackground(BackgroundLoader.builderBackGround());
		
		HBox hLabel=new HBox();
		hLabel.getChildren().add(getLblTheme());
		hLabel.setAlignment(Pos.CENTER);
		this.setTop(hLabel);
		
		
		HBox hTable= new HBox();
		hTable.getChildren().add(getTvQuestions());
		hTable.setAlignment(Pos.CENTER);
		this.setCenter(hTable);
		
		HBox hBtn= new HBox();
		hBtn.getChildren().add(getBtnBack());
		hBtn.setAlignment(Pos.CENTER);
		this.setBottom(hBtn);
	}
	
	
	
	public TableView<Question> getTvQuestions() {
		if(tvQuestions==null) {
			tvQuestions=new TableView<>();
			
			TableColumn<Question, String> tcAuthor = new TableColumn<>("Author");
			TableColumn<Question, String> tcClues = new TableColumn<>("Clues");
			TableColumn<Question, String> tcAnswer = new TableColumn<>("Answer");
			TableColumn<Question, String> tcClues1 = new TableColumn<>("Clues_1");
			TableColumn<Question, String> tcClues2 = new TableColumn<>("Clues_2");
			TableColumn<Question, String> tcClues3 = new TableColumn<>("Clues_3");
            
			tcAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
	        tcClues1.setCellValueFactory(new Factory(0));
	        tcClues2.setCellValueFactory(new Factory(1));
	        tcClues3.setCellValueFactory(new Factory(2));
	        tcAnswer.setCellValueFactory(new PropertyValueFactory<>("answer"));
			
			tcClues.getColumns().addAll(tcClues1,tcClues2,tcClues3);
            tvQuestions.getColumns().addAll(tcAuthor,tcClues,tcAnswer);
            tvQuestions.setItems(FXCollections.observableArrayList(questions));
            
		}
		return tvQuestions;
	}
	
	public Label getLblTheme() {
		if(lblTheme==null) {
			lblTheme=new Label(theme);
            lblTheme.getStyleClass().add("labelTitle");
		}
		return lblTheme;
	}


	public Button getBtnBack() {
		if(btnBack==null) {
			btnBack=new Button("Back");
			btnBack.getStyleClass().add("buttonBasic");
            btnBack.setId("big-button");
            btnBack.setOnAction(event -> Main.switchScene(new TableViewBP()));
		}
		return btnBack;
			
	}


	/**
     * Factory class is used to obtain
     * the 3 Clues separately
     */
    public static class Factory implements Callback<TableColumn.CellDataFeatures<Question, String>, ObservableValue<String>> {
        int index;

        public Factory(int index) {
            this.index = index;
        }

        public ObservableValue<String> call(CellDataFeatures<Question, String> p) {
            return new SimpleStringProperty(p.getValue().getClues().get(index));
        }
    }
}
