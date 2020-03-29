package view;

import java.util.List;

import application.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
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

		this.setPadding(new Insets(10.));

		setAlignment(getLblTheme(), Pos.CENTER);
		this.setTop(getLblTheme());

		setAlignment(getTvQuestions(), Pos.CENTER);
		this.setCenter(getTvQuestions());

		setAlignment(getBtnBack(), Pos.CENTER);
		this.setBottom(getBtnBack());
	}
	
	
	
	public TableView<Question> getTvQuestions() {
		if(tvQuestions==null) {
			tvQuestions=new TableView<>();
			changeHeight();
			Main.getStage().heightProperty().addListener(observable -> changeHeight());

			TableColumn<Question, String>
					tcAuthor = new TableColumn<>("Author"),
					tcClues = new TableColumn<>("Clues"),
					tcAnswer = new TableColumn<>("Answer"),
					tcClues1 = new TableColumn<>("Clues_1"),
					tcClues2 = new TableColumn<>("Clues_2"),
					tcClues3 = new TableColumn<>("Clues_3");

			tvQuestions.widthProperty().addListener(observable -> {
				double val = ((tvQuestions.getWidth() - tcAuthor.getWidth() - tcAnswer.getWidth()) / 3) - 10;
				tcClues1.setCellFactory(getValue(val));
				tcClues2.setCellFactory(getValue(val));
				tcClues3.setCellFactory(getValue(val));
				tcClues1.setPrefWidth(val + 5);
				tcClues2.setPrefWidth(val + 5);
				tcClues3.setPrefWidth(val + 5);
			});

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

	public Callback<TableColumn<Question, String>, TableCell<Question, String>> getValue(double size) {
		return new Callback<TableColumn<Question, String>, TableCell<Question, String>>() {
			@Override
			public TableCell<Question, String> call(TableColumn<Question, String> arg0) {
				return new TableCell<Question, String>() {

					@Override
					public void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if (!isEmpty()) {
							Text text = new Text(item);
							text.setWrappingWidth(size);
							this.setWrapText(true);
							setGraphic(text);
						}
					}
				};
			}
		};
	}

	public void changeHeight() {
		getTvQuestions().setMaxHeight(Main.getStage().getHeight() - 290);
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
