package model.dialog;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.Question;
import utils.BackgroundLoader;
import utils.JsonManager;
import view.TableViewThemeBP;

import java.util.ArrayList;
import java.util.Arrays;


public class EditableQuestion extends StackPane {

    private Button btnEdit, btnCancel;
    private Question oldQuestion, editQuestion;
    private TextField author = new TextField(),
            clue1 = new TextField(),
            clue2 = new TextField(),
            clue3 = new TextField(),
            answer = new TextField();

    public EditableQuestion(Question question) {
        oldQuestion = question.clone();
        author.setText(question.getAuthor());
        clue1.setText(question.getClue(0));
        clue2.setText(question.getClue(1));
        clue3.setText(question.getClue(2));
        answer.setText(question.getAnswer());

        HBox hbUp = new HBox(author, answer);
        hbUp.setAlignment(Pos.CENTER);
        hbUp.setSpacing(10.);
        HBox hbDown = new HBox(clue1, clue2, clue3);
        hbDown.setAlignment(Pos.CENTER);
        hbDown.setSpacing(10.);
        VBox vbContainer = new VBox(hbUp, hbDown);
        vbContainer.setAlignment(Pos.CENTER);
        vbContainer.setSpacing(50.);

        HBox hbBottom = new HBox(getBtnEdit(), getBtnCancel());
        hbBottom.setSpacing(20.);
        hbBottom.setAlignment(Pos.BOTTOM_CENTER);
        setAlignment(hbBottom, Pos.BOTTOM_CENTER);
        setMargin(hbBottom, new Insets(0.,0.,50.,0.));
        hbBottom.setMaxHeight(35.);

        this.getChildren().addAll(vbContainer, hbBottom);

        this.setMaxSize(550.,400.);
        this.setVisible(false);
        this.setBackground(BackgroundLoader.buildDialogBackGround(550.,400.));

    }


    public Button getBtnEdit() {
        if (btnEdit == null ) {
            btnEdit = new Button("Edit");
            btnEdit.setMinSize(80.,25.);
            btnEdit.getStyleClass().addAll("buttonBasic","dialog-button");
        }
        return btnEdit;
    }

    public Button getBtnCancel() {
        if (btnCancel == null) {
            btnCancel = new Button("Cancel");
            btnCancel.setMinSize(80.,25.);
            btnCancel.getStyleClass().addAll("buttonBasic","dialog-button");
            btnCancel.setOnAction(event -> setVisible(false));
        }
        return btnCancel;
    }

    public Question getQuestion() {
        if (editQuestion == null) {
            editQuestion = new Question(author.getText(),oldQuestion.getTheme(),
                    new ArrayList<>(Arrays.asList(clue1.getText(), clue2.getText(), clue3.getText())),answer.getText());
        }
        return editQuestion;
    }
}
