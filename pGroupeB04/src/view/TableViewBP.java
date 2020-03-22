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
import model.BackgroundLoader;
import model.Question;
import utils.JsonManager;

public class TableViewBP extends BorderPane{

	private TableView<Question> tvQuestions;
	
	private TableColumn<Question,String> tcTheme;
	private TableColumn<Question,String> tcAuthor;
	private TableColumn<Question,String> tcClues1;
	private TableColumn<Question,String> tcClues2;
	private TableColumn<Question,String> tcClues3;
	private TableColumn<Question,String> tcClues;
	private TableColumn<Question,String> tcAnswer;
	
	private Label lblTitle;
	
	private Button btnBack;
	
	List<Question>questions=new ArrayList<>();
	
	String login;

	

	public TableViewBP(String login) {
		this.login=login;
		this.setBackground(BackgroundLoader.builderBackGround());

		getQuestions();
		
		HBox hbTop=new HBox();
		hbTop.getChildren().add(getLblTitle());
		hbTop.setAlignment(Pos.TOP_CENTER);
		this.setTop(hbTop);
		
		HBox hbCenter=new HBox();
		hbCenter.getChildren().add(getTvQuestions());
		hbCenter.setAlignment(Pos.CENTER);
		hbCenter.setPadding(new Insets(10,10,10,10));
		this.setCenter(hbCenter);
		
		HBox hbBottom=new HBox();
		hbBottom.getChildren().add(getBtnBack());
		hbBottom.setAlignment(Pos.BOTTOM_CENTER);
		this.setBottom(hbBottom);
	}
	
	
	
	public TableView getTvQuestions() {
		if(tvQuestions==null) {
			tvQuestions=new TableView<>();
			
			tcTheme= new TableColumn<>("theme");
			tcAuthor=new TableColumn<>("author");
			tcClues1=new TableColumn<>("Clues_1");
			tcClues2=new TableColumn<>("Clues_2");
			tcClues3=new TableColumn<>("Clues_3");
			tcClues=new TableColumn<>("Clues");
			tcClues.getColumns().addAll(tcClues1,tcClues2,tcClues3);			
			tcAnswer=new TableColumn<>("Anwser");
			
			tcTheme.setCellValueFactory(new PropertyValueFactory<>("theme"));
			tcAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
			tcAnswer.setCellValueFactory(new PropertyValueFactory<>("answer"));
			tcClues1.setCellValueFactory(new Factory(0));
			tcClues2.setCellValueFactory(new Factory(1));
			tcClues3.setCellValueFactory(new Factory(2));
			
			tvQuestions.getColumns().addAll(tcTheme,tcAuthor,tcClues,tcAnswer);
			tvQuestions.setMinWidth(1900.);;
			tvQuestions.setItems(FXCollections.observableArrayList(questions));
			
			tvQuestions.setMaxWidth(Region.USE_COMPUTED_SIZE);
			tvQuestions.setMaxWidth(Region.USE_COMPUTED_SIZE);
		}
		
		return tvQuestions;
	}
	
	
	
	
	
	public Label getLblTitle() {
		if(lblTitle==null) {
			lblTitle=new Label(login);
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
	 * @return
	 */
	
	public List<Question> getQuestions() {
		JsonManager.themeFromDeck();
		List<Question>quest;
		for(String themes:JsonManager.THEMES) {
			quest=JsonManager.choiceTheme(themes);
			for(Question question:quest) {
				questions.add(question);
			}
		}
		return questions;
	}

	/**
	 * Factory class is used to obtain
	 * the 3 Clues separately
	 * @author valen
	 *
	 */
	public class Factory implements Callback<TableColumn.CellDataFeatures<Question,String>,ObservableValue<String>>
	{
		  int index;
		 
		  public Factory( int index ){
		    this.index= index;
		  }
		 
		  public ObservableValue<String> call(CellDataFeatures<Question, String> p) {
			  SimpleStringProperty sspClues=new SimpleStringProperty(p.getValue().getClues().get(index));
		     return sspClues; // Ici il te faudra peut être caster quelque chose
		  }

		
		}
	
}
