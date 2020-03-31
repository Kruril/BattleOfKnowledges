package view;

import java.util.Arrays;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
	
	private Button btnBack, btnValidation;

	private TextField txtAuthor, txtClues1, txtClues2, txtClues3, txtAnswer;
	
	public TableViewThemeBP(String theme) {
		this.theme=theme;
		this.questions=JsonManager.choiceTheme(theme);
		this.setBackground(BackgroundLoader.builderBackGround());
		Insets insets = new Insets(10.);

		this.setPadding(insets);

		setAlignment(getLblTheme(), Pos.CENTER);
		this.setTop(getLblTheme());

		setAlignment(getTvQuestions(), Pos.CENTER);
		this.setCenter(getTvQuestions());

		setAlignment(getBtnBack(), Pos.CENTER);
		HBox hbNewQuestion = new HBox(getTxtAuthor(),getTxtClues1(),getTxtClues2(),getTxtClues3(),getTxtAnswer(),getBtnValidation());
		hbNewQuestion.setAlignment(Pos.CENTER);
		hbNewQuestion.setSpacing(10.);
		hbNewQuestion.setPadding(insets);

		VBox vbBottom = new VBox(hbNewQuestion, getBtnBack());
		vbBottom.setAlignment(Pos.CENTER);
		vbBottom.setPadding(insets);
		vbBottom.setSpacing(10.);
		this.setBottom(vbBottom);
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
				double val = ((tvQuestions.getWidth() - tcAuthor.getWidth() - tcAnswer.getWidth()) / 3) - 5;
				tcClues1.setCellFactory(getValue(val));
				tcClues2.setCellFactory(getValue(val));
				tcClues3.setCellFactory(getValue(val));
				tcClues1.setPrefWidth(val);
				tcClues2.setPrefWidth(val);
				tcClues3.setPrefWidth(val);
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


	public Button getBtnValidation() {
		if (btnValidation == null) {
			btnValidation = new Button("", new ImageView(new Image("images/icon/validation.png",
					30, 30, true, true)));
			btnValidation.getStyleClass().add("round");
			btnValidation.setOnAction(event -> {
				if (!getTxtAuthor().getText().equals("") && !getTxtClues1().getText().equals("") &&
						!getTxtClues2().getText().equals("") && !getTxtClues3().getText().equals("") &&
						!getTxtAnswer().getText().equals("")) {
					Question question = new Question(getTxtAuthor().getText(), theme,
							Arrays.asList(getTxtClues1().getText(),getTxtClues2().getText(),getTxtClues3().getText()),
							getTxtAnswer().getText());
					if (JsonManager.getDeck().addQuestion(question)) {
						getTvQuestions().getItems().add(question);
						setTxtPromptText("Enter new");
					}
					else {
						setTxtPromptText("Invalid");
					}
					getTxtAuthor().setText("");
					getTxtClues1().setText("");
					getTxtClues2().setText("");
					getTxtClues3().setText("");
					getTxtAnswer().setText("");
				}
			});
		}
		return btnValidation;
	}

	private void setTxtPromptText(String value) {
		getTxtAuthor().setPromptText(value +" Author");
		getTxtClues1().setPromptText(value +" Clue 1");
		getTxtClues2().setPromptText(value +" Clue 2");
		getTxtClues3().setPromptText(value +" Clue 3");
		getTxtAnswer().setPromptText(value +" Answer");
	}

	public TextField getTxtAuthor() {
		if (txtAuthor == null) {
			txtAuthor = new TextField();
			txtAuthor.setPromptText("Enter Author");
		}
		return txtAuthor;
	}

	public TextField getTxtClues1() {
		if (txtClues1 == null) {
			txtClues1 = new TextField();
			txtClues1.setPromptText("Enter Clue 1");
		}
		return txtClues1;
	}

	public TextField getTxtClues2() {
		if (txtClues2 == null) {
			txtClues2 = new TextField();
			txtClues2.setPromptText("Enter Clue 2");
		}
		return txtClues2;
	}

	public TextField getTxtClues3() {
		if (txtClues3 == null) {
			txtClues3 = new TextField();
			txtClues3.setPromptText("Enter Clue 3");
		}
		return txtClues3;
	}

	public TextField getTxtAnswer() {
		if (txtAnswer == null) {
			txtAnswer = new TextField();
			txtAnswer.setPromptText("Enter Answer");
		}
		return txtAnswer;
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
