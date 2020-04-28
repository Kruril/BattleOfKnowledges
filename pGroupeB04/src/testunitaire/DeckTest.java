package testunitaire;

import model.Deck;
import model.IteratorQuestion;
import model.Question;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import test.Explorateur;
import utils.controler.JsonManager;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class DeckTest {

    private Deck deck;
    private List<Question> questions;
    private String author="Author",theme="theme",answer="answer";
    private String clue1="I'm a clue",clue2="I'm a second clue",clue3="I'm the last clue";

    @Before
    public void setUp() throws Exception {
        deck = new Deck();
        questions = (List<Question>) Explorateur.getField(deck, "questions");
    }

    @After
    public void tearDown() throws Exception {
        deck = null;
        questions = null;
    }

    @Test
    public void modifyQuestion() {
        Question question = new Question(author, theme, Arrays.asList(clue1,clue2,clue3), answer);
        questions.add(question);
        int index = deck.findQuestion(question);
        assertTrue("Condition != -1" , index != -1);
        Question newQuestion = new Question(author, answer, Arrays.asList(clue1,clue2,clue3), theme);
        deck.modifyQuestion(question, newQuestion);
    }

    @Test
    public void findQuestion() {
        Question question = new Question(author, theme, Arrays.asList(clue1,clue2,clue3), answer);
        questions.add(question);
        deck.findQuestion(question);
    }

    @Test
    public void createIterator() {
    	//IteratorQuestion iterator = new IteratorQuestion(JsonManager.choiceTheme(theme));
        //deck.createIterator(theme);
    }

    @Test
    public void checkTheme() {
    	//assertTrue(questions.removeIf(quest -> !quest.getTheme().equalsIgnoreCase(theme)));
    }

    @Test
    public void testToString() {
    }

    @Test
    public void addQuestion() {
    	Question question = new Question(author, theme, Arrays.asList(clue1,clue2,clue3), answer);
    	questions.add(question);
    	assertTrue(questions.contains(question) || !question.checkQuestion());
    	questions.add(question);
    }   
    
    @Test
    public void addQuestionAlreadyExist() {
    	Question question = new Question(author, theme, Arrays.asList(clue1,clue2,clue3), answer);
    	questions.add(question);
    	assertTrue(questions.contains(question));
    	questions.add(question);
    } 
    
    @Test
    public void addQuestionChecked() {
    	Question question = new Question(author, theme, Arrays.asList(clue1,clue2,clue3), answer);
    	//questions.add(question);
    	assertTrue(question.checkQuestion());
    	questions.add(question);
    }

    @Test
    public void removeQuestion() {
    	Question question = new Question(author, theme, Arrays.asList(clue1,clue2,clue3), answer);
    	assertFalse(questions.contains(question));
    	questions.add(question);
    	questions.remove(question);
    	assertFalse(questions.contains(question));
    }

    @Test
    public void testCreateIterator() {
    	deck.createIterator();   
    }
    
    @Test
    public void isEmpty() {
    	assertFalse(questions == null);
    	deck.isEmpty();
    }
}