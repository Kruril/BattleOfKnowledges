package view;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import application.Main;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import model.Deck;
import model.Question;
import utils.BackgroundLoader;
import utils.JsonManager;
import utils.TableView.CommonTableView;

public class TableViewThemeBP extends BorderPane {
	private Label lblTheme;
	
	private TableView<Question> tvQuestions;

	private String theme;
	private List<Question> questions;
	
	private Button btnBack, btnValidation, btnAddFile;

	private TextField txtAuthor, txtClue1, txtClue2, txtClue3, txtAnswer;
	
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
		tvQuestions.setEditable(true);
		
		setAlignment(getBtnBack(), Pos.CENTER);
		HBox hbNewQuestion = new HBox(getBtnAddFile(),getTxtAuthor(), getTxtClue1(), getTxtClue2(), getTxtClue3(),getTxtAnswer(),getBtnValidation());
		hbNewQuestion.setAlignment(Pos.CENTER);
		hbNewQuestion.setSpacing(10.);
		hbNewQuestion.setPadding(insets);

		VBox vbBottom = new VBox(hbNewQuestion, getBtnBack());
		vbBottom.setAlignment(Pos.CENTER);
		vbBottom.setPadding(insets);
		vbBottom.setSpacing(10.);
		this.setBottom(vbBottom);
		contexMenu();
	}
	
	
	
	public TableView<Question> getTvQuestions() {
		if(tvQuestions==null) {
			tvQuestions=new TableView<>();
			CommonTableView.changeHeight(tvQuestions);
			Main.getStage().heightProperty().addListener(observable -> CommonTableView.changeHeight(tvQuestions));

			TableColumn<Question, String>
					tcAuthor = new TableColumn<>("Author"),
					tcAnswer = new TableColumn<>("Answer"),
					tcClue1 = new TableColumn<>("Clue_1"),
					tcClue2 = new TableColumn<>("Clue_2"),
					tcClue3 = new TableColumn<>("Clue_3");

			tvQuestions.widthProperty().addListener(observable -> {
				double val = ((tvQuestions.getWidth() - tcAuthor.getWidth() - tcAnswer.getWidth()) / 3) - 5;
				tcClue1.setCellFactory(getValue(val));
				tcClue2.setCellFactory(getValue(val));
				tcClue3.setCellFactory(getValue(val));
				tcClue1.setPrefWidth(val);
				tcClue2.setPrefWidth(val);
				tcClue3.setPrefWidth(val);
			});

			tcAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));

			tcClue1.setCellValueFactory(new Factory(0));
	        tcClue2.setCellValueFactory(new Factory(1));
	        tcClue3.setCellValueFactory(new Factory(2));
	        tcAnswer.setCellValueFactory(new PropertyValueFactory<>("answer"));
			
	        

			 /**
	         * Allow the Cell editing
	         */
	        tcAuthor.setCellFactory(TextFieldTableCell.forTableColumn());
	        tcClue1.setCellFactory(TextFieldTableCell.forTableColumn());
	        tcClue2.setCellFactory(TextFieldTableCell.forTableColumn());
	        tcClue3.setCellFactory(TextFieldTableCell.forTableColumn());
	        tcAnswer.setCellFactory(TextFieldTableCell.forTableColumn());
	        
	        /**
	         * When we change the value of one cell
	         * we change also automaticaly the value in the json file
	         */
			modifyAuthor(tcAuthor);
			modifyAnswer(tcAnswer);
			modifyClues(0, tcClue1);
			modifyClues(1, tcClue2);
			modifyClues(2, tcClue3);

            tvQuestions.getColumns().addAll(tcAuthor,tcClue1,tcClue2,tcClue3,tcAnswer);
            tvQuestions.setItems(FXCollections.observableArrayList(questions));

		}
		return tvQuestions;
	}

	public void modifyAuthor(TableColumn<Question, String> tcAuthor) {
		tcAuthor.setOnEditCommit((CellEditEvent<Question,String> t)->{
			Question oldQuestion = getTvQuestions().getSelectionModel().getSelectedItem().clone();
			t.getTableView().getItems().get(
					 t.getTablePosition().getRow()).setAuthor(t.getNewValue());
			editQuestion(oldQuestion,getTvQuestions().getSelectionModel().getSelectedItem());
		});
	}

	public void modifyAnswer(TableColumn<Question, String> tcAnswer) {
		tcAnswer.setOnEditCommit((CellEditEvent<Question,String> t)->{
			Question oldQuestion = getTvQuestions().getSelectionModel().getSelectedItem().clone();
			t.getTableView().getItems().get(
					t.getTablePosition().getRow()).setAnswer(t.getNewValue());

			editQuestion(oldQuestion,getTvQuestions().getSelectionModel().getSelectedItem());
		});
	}
	public void modifyClues(int index, TableColumn<Question, String> tcClue) {
		tcClue.setOnEditCommit((CellEditEvent<Question,String> t)->{
			Question oldQuestion = getTvQuestions().getSelectionModel().getSelectedItem().clone();
			t.getTableView().getItems().get(
					t.getTablePosition().getRow()).setClue(index, t.getNewValue());

			editQuestion(oldQuestion,getTvQuestions().getSelectionModel().getSelectedItem());
		});
	}

	public void contexMenu() {
		ContextMenu menu = new ContextMenu();

		MenuItem remove = new MenuItem("Remove");
		remove.setOnAction(event -> {
			Question qRemoved = getTvQuestions().getSelectionModel().getSelectedItem();
			if (JsonManager.getDeck().removeQuestion(qRemoved)) {
				getTvQuestions().getItems().remove(qRemoved);
			}
		});

		menu.getItems().add(remove);
		getTvQuestions().setContextMenu(menu);
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
			btnValidation.setOnAction(event -> validation());
		}
		return btnValidation;
	}

	public void validation() {
		if (!getTxtAuthor().getText().equals("") && !getTxtClue1().getText().equals("") &&
				!getTxtClue2().getText().equals("") && !getTxtClue3().getText().equals("") &&
				!getTxtAnswer().getText().equals("")) {
			Question question = new Question(getTxtAuthor().getText(), theme,
					Arrays.asList(getTxtClue1().getText(), getTxtClue2().getText(), getTxtClue3().getText()),
					getTxtAnswer().getText());
			if (JsonManager.getDeck().addQuestion(question)) {
				getTvQuestions().getItems().add(question);
				setTxtPromptText("Enter new");
			}
			else {
				setTxtPromptText("Invalid");
			}
			getTxtAuthor().setText("");
			getTxtClue1().setText("");
			getTxtClue2().setText("");
			getTxtClue3().setText("");
			getTxtAnswer().setText("");
		}
	}

	private void setTxtPromptText(String value) {
		getTxtAuthor().setPromptText(value +" Author");
		getTxtClue1().setPromptText(value +" Clue 1");
		getTxtClue2().setPromptText(value +" Clue 2");
		getTxtClue3().setPromptText(value +" Clue 3");
		getTxtAnswer().setPromptText(value +" Answer");
	}

	public Button getBtnAddFile() {
		if (btnAddFile == null) {
			btnAddFile = new Button("", new ImageView(new Image("images/icon/openFile.png",
					25, 25, true, true)));
			btnAddFile.getStyleClass().add("round");
			btnAddFile.setOnAction(event -> importFile());
		}
		return btnAddFile;
	}

	public void importFile() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setDialogTitle("Select a theme from json");
		jfc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON", "json");
		jfc.addChoosableFileFilter(filter);

		int returnValue = jfc.showDialog(null, "Add theme");

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			System.out.println(selectedFile.getAbsolutePath());
			Deck deck = new Deck();
			deck.fromJson(selectedFile);
			deck.checkTheme(theme);

			deck.getListe().forEach(question -> {
				question.setTheme(CommonTableView.upperLowerText(question.getTheme()));
				if (JsonManager.getDeck().addQuestion(question)) {
					getTvQuestions().getItems().add(question);
				}
			});
		}
	}

	public TextField getTxtAuthor() {
		if (txtAuthor == null) {
			txtAuthor = new TextField();
			txtAuthor.setPromptText("Enter Author");
		}
		return txtAuthor;
	}

	public TextField getTxtClue1() {
		if (txtClue1 == null) {
			txtClue1 = new TextField();
			txtClue1.setPromptText("Enter Clue 1");
		}
		return txtClue1;
	}

	public TextField getTxtClue2() {
		if (txtClue2 == null) {
			txtClue2 = new TextField();
			txtClue2.setPromptText("Enter Clue 2");
		}
		return txtClue2;
	}

	public TextField getTxtClue3() {
		if (txtClue3 == null) {
			txtClue3 = new TextField();
			txtClue3.setPromptText("Enter Clue 3");
		}
		return txtClue3;
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

    /**
     * to edit question
     */
    
    public void editQuestion(Question oldQuestion,Question editQuestion) {
    	JsonManager.getDeck().modifyQuestion(oldQuestion, editQuestion);
    }
}
