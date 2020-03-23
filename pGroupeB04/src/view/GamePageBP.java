package view;

import application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.BackgroundLoader;
import model.Question;
import utils.JsonManager;
import utils.Timer;

import java.util.HashMap;
import java.util.List;

public class GamePageBP extends BorderPane {

    private Label lblTitle;

    private Label lblClue1;
    private Label lblClue2;
    private Label lblClue3;

    private Label lblPoint0;
    private Label lblPoint1;
    private Label lblPoint2;
    private Label lblPoint3;
    private Label lblPoint4;

    private Label lblTimer;

    private Button btnSkip;
    private TextField txtAnswer;
    private Button btnOk;

    private String theme;
    private List<Question> questions;
    private int waitingClues = 0;
    private int waitingQuestions = 0;
    private long time;
    private int pointWon = 0;
    private int pointCons;
    private HashMap<String,Label> pointsFromLbl;

    public GamePageBP(String theme) {
        this.theme = theme;
        this.questions = JsonManager.choiceTheme(theme);
        //BACKGROUND
        this.setBackground(BackgroundLoader.builderBackGround());

        //TOP
        VBox vbTop = new VBox();
        vbTop.getChildren().add(getLblTitle());
        vbTop.setAlignment(Pos.CENTER);
        vbTop.setPadding(new Insets(20, 0, 0, 0));
        this.setTop(vbTop);

        //CENTER
        HBox hbCenter = new HBox();
        VBox vbPoints = new VBox();
        vbPoints.getChildren().addAll(getLblPoint4(), getLblPoint3(), getLblPoint2(), getLblPoint1(), getLblPoint0());
        vbPoints.setAlignment(Pos.CENTER);
        vbPoints.getStyleClass().add("vboxPoints");
        vbPoints.setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
        vbPoints.setSpacing(10);

        VBox vbClues = new VBox();
        vbClues.getChildren().addAll(getLblClue1(), getLblClue2(), getLblClue3());
        vbClues.setAlignment(Pos.CENTER);
        vbClues.setSpacing(15);

        VBox vbTimer = new VBox();
        vbTimer.getChildren().addAll(getLblTimer());
        vbTimer.setAlignment(Pos.CENTER);
        vbTimer.setSpacing(15);

        hbCenter.getChildren().addAll(vbPoints, vbClues, vbTimer);
        hbCenter.setPadding(new Insets(10));
        hbCenter.setAlignment(Pos.CENTER);
        hbCenter.setSpacing(50);
        this.setCenter(hbCenter);

        //BOTTOM
        HBox hbBottom = new HBox();
        hbBottom.getChildren().addAll(getBtnSkip(), getTxtAnswer(), getBtnOk());
        hbBottom.setSpacing(10);
        hbBottom.setAlignment(Pos.CENTER);
        hbBottom.setPadding(new Insets(0, 30, 30, 0));
        this.setBottom(hbBottom);
        time = System.currentTimeMillis();
        Timer.startTimer(getLblTimer());
    }

    public Label getLblTitle() {
        if (lblTitle == null) {
            lblTitle = new Label(theme);
            lblTitle.getStyleClass().add("labelTitle");
        }
        return lblTitle;
    }

    public Label getLblClue1() {
        if (lblClue1 == null) {
            lblClue1 = new Label();
            lblClue1.setWrapText(true);
            lblClue1.getStyleClass().addAll("labelBasique", "labelClue");
        }
        return lblClue1;
    }

    public Label getLblClue2() {
        if (lblClue2 == null) {
            lblClue2 = new Label();
            lblClue2.setWrapText(true);
            lblClue2.getStyleClass().addAll("labelBasique", "labelClue");
        }
        return lblClue2;
    }

    public Label getLblClue3() {
        if (lblClue3 == null) {
            lblClue3 = new Label();
            lblClue3.setWrapText(true);
            lblClue3.getStyleClass().addAll("labelBasique", "labelClue");
        }
        return lblClue3;
    }

    public Label getLblPoint0() {
        if (lblPoint0 == null) {
            lblPoint0 = new Label("0");
            lblPoint0.getStyleClass().addAll("labelBasique", "labelPoints", "point-gagne", "point-consecutif");
            getPointsFromLbl().put(lblPoint0.getText(), lblPoint0);
        }
        return lblPoint0;
    }

    public Label getLblPoint1() {
        if (lblPoint1 == null) {
            lblPoint1 = new Label("1");
            lblPoint1.getStyleClass().addAll("labelBasique", "labelPoints");
            getPointsFromLbl().put(lblPoint1.getText(), lblPoint1);
        }
        return lblPoint1;
    }

    public Label getLblPoint2() {
        if (lblPoint2 == null) {
            lblPoint2 = new Label("2");
            lblPoint2.getStyleClass().addAll("labelBasique", "labelPoints");
            getPointsFromLbl().put(lblPoint2.getText(), lblPoint2);
        }
        return lblPoint2;
    }

    public Label getLblPoint3() {
        if (lblPoint3 == null) {
            lblPoint3 = new Label("3");
            lblPoint3.getStyleClass().addAll("labelBasique", "labelPoints");
            getPointsFromLbl().put(lblPoint3.getText(), lblPoint3);
        }
        return lblPoint3;
    }

    public Label getLblPoint4() {
        if (lblPoint4 == null) {
            lblPoint4 = new Label("4");
            lblPoint4.getStyleClass().addAll("labelBasique", "labelPoints");
            getPointsFromLbl().put(lblPoint4.getText(), lblPoint4);
        }
        return lblPoint4;
    }

    public Label getLblTimer() {
        if (lblTimer == null) {
            lblTimer = new Label("90");
            lblTimer.getStyleClass().addAll("labelBasique", "labelPoints");
            lblTimer.textProperty().addListener((observable, oldValue, newValue) -> {

                gameOver(newValue);
                seeClues();

            });
        }
        return lblTimer;
    }

    /**
     * show clues after 3 seconds
     */
    public void seeClues() {
        if (waitingClues < 3) {
            if (waitingClues == 0){
                getLblClue1().setText(questions.get(waitingQuestions).getClues().get(waitingClues));
                waitingClues++;
            }
            if (waitingClues == 1 && System.currentTimeMillis() - time >= 3000) {
                getLblClue2().setText(questions.get(waitingQuestions).getClues().get(waitingClues));
                waitingClues++;
            }
            if (waitingClues == 2 && System.currentTimeMillis() - time >= 6000){
                getLblClue3().setText(questions.get(waitingQuestions).getClues().get(waitingClues));
                waitingClues++;
            }
        }
    }

    /**
     * Check if game is over with time equals 0 or pointWon is 4
     * @param newValue new value of label
     */
    public void gameOver(String newValue) {
        if (newValue.equals("0") || pointWon == 4) {
            Main.switchScene(new EndGameBP(pointWon));
            Timer.getTimeTimer().stop();
            pointWon++;
        }
    }

    public Button getBtnSkip() {
        if (btnSkip == null) {
            btnSkip = new Button("Skip");
            btnSkip.getStyleClass().add("buttonBasic");
            btnSkip.setId("medium-button");
            btnSkip.setOnAction(event -> {
                choiceQuestion();
                updateScore(false);
                getTxtAnswer().setPromptText("");
            });
        }
        return btnSkip;
    }

    public TextField getTxtAnswer() {
        if (txtAnswer == null) {
            txtAnswer = new TextField();
            txtAnswer.getStyleClass().add("textField");
        }
        return txtAnswer;
    }

    public Button getBtnOk() {
        if (btnOk == null) {
            btnOk = new Button("Ok");
            btnOk.getStyleClass().add("buttonBasic");
            btnOk.setId("medium-button");

            btnOk.setOnAction(event -> {
                updateScore(isAddPoint());
                choiceQuestion();
                getTxtAnswer().setText("");
            });
        }
        return btnOk;
    }

    /**
     * Check if it's necessary add a point or not
     * @return true or false if add point
     */
    public boolean isAddPoint() {
        boolean addPoint;
        if (isCorrect(getTxtAnswer().getText())) {
            addPoint = true;
            questions.remove(waitingQuestions);
            getTxtAnswer().setPromptText("Correct Answer");
        }
        else {
            addPoint = false;
            getTxtAnswer().setPromptText("Wrong Answer");
        }
        return addPoint;
    }

    public HashMap<String, Label> getPointsFromLbl() {
        if (pointsFromLbl == null) {
            pointsFromLbl = new HashMap<>();
        }
        return pointsFromLbl;
    }

    /**
     * Check if it's end of the list
     */
    public void choiceQuestion(){
        if (waitingQuestions >= questions.size()) waitingQuestions = -1;
        returnStartQuestion();
    }

    /**
     * set the basic display for start a question
     */
    public void returnStartQuestion() {
        waitingQuestions++;
        getLblClue1().setText("");
        getLblClue2().setText("");
        getLblClue3().setText("");
        waitingClues = 0;
        time = System.currentTimeMillis();
    }

    /**
     * Check if the answer of question is correct
     * @param text answer of question
     * @return true or false
     */
    public boolean isCorrect(String text) {
        return questions.get(waitingQuestions).getAnswer().equalsIgnoreCase((text));
    }

    /**
     * Update of score add point or return to zero
     * @param addPoint true or false if add point
     */
    public void updateScore(boolean addPoint) {
        if (addPoint){
            addPointScore();
        }
        else {
            removePointScore();
        }
    }

    /**
     * add point in total and update of label for point in user display
     */
    public void addPointScore() {
        pointCons++;
        pointsFromLbl.forEach((key, label) -> {
            if (pointCons == Integer.parseInt(key)) {
                label.getStyleClass().addAll("point-gagne","point-consecutif");
                pointFinal();
            }
            if (pointCons-1 == Integer.parseInt(key)) {
                label.getStyleClass().remove("point-consecutif");
            }
        });
    }

    /**
     * update point in zero value
     */
    public void removePointScore() {
        pointsFromLbl.forEach((key, label) -> {
            if (pointCons == Integer.parseInt(key)) {
                label.getStyleClass().remove("point-consecutif");
                pointFinal();
                pointCons = 0;
            }
            if (key.equals("0")) {
                label.getStyleClass().add("point-consecutif");
            }
        });
    }

    /**
     * Point final for EndGameBP, retains the highest point
     */
    public void pointFinal(){
        if (pointWon < pointCons) {
            pointWon = pointCons;
        }
    }
}
