package testunitaire;

import model.Deck;
import model.IteratorQuestion;
import model.Question;
import model.Trash;

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
	private Trash trash;
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
        trash = null;
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
        //deck.createIterator(JsonManager.choiceTheme(theme));
    }

    @Test
    public void checkTheme() {
    	//assertTrue(questions.removeIf(quest -> !quest.getTheme().equalsIgnoreCase(theme)));
    }

    @Test
    public void testToString() {
    }

	/**
	 * Test of the addQuestion method
	 */

	@Test
	public void testAddQuestion() {
    	Question question = new Question(author, theme, Arrays.asList(clue1,clue2,clue3), answer);
		deck.addQuestion(question);
		assertTrue(questions.contains(question) && questions.size()==1);
	}

	@Test
	public void testAddQuestionAlreadyAdded() {
		Question question = new Question(author, theme, Arrays.asList(clue1,clue2,clue3), answer);
		questions.add(question);
		deck.addQuestion(question);
		assertTrue(questions.contains(question) && questions.size()==1);
	}
	

	@Test
	public void testAddQuestionWithError() {
		Question question = new Question(author, theme, Arrays.asList(clue1,clue2,clue3), answer);
		deck.addQuestion(question);
		assertTrue(question.checkQuestion()==false);
	}
	
	/**
	 * test of removeQuestion method
	 */
	
	@Test
	public void testRemoveQuestion() {
		Question question = new Question(author, theme, Arrays.asList(clue1,clue2,clue3), answer);
		questions.add(question);
		deck.removeQuestion(question);
		assertTrue(!(questions.contains(question)) && questions.size()==0);
	}

	@Test
	public void testRemoveQuestionNotContained() {
		Question question = new Question(author, theme, Arrays.asList(clue1,clue2,clue3), answer);
		deck.removeQuestion(question);
		assertTrue(!(questions.contains(question)) && questions.size()==0);
	}
    
    @Test
    public void testCreateIterator() {
    	deck.createIterator();   
    }
    
    /**
	 * Test of the isEmpty method
	 */

	@Test
	public void testIsEmptyNull() {
		questions=null;
		deck.isEmpty();
		assertTrue(questions==null);
	}
	
	@Test
	public void testIsNotEmpty() {
		Question question = new Question(author, theme, Arrays.asList(clue1,clue2,clue3), answer);
		questions.add(question);
		deck.isEmpty();
		assertTrue(questions.size()!=0);
	}
	
	@Test
	public void testIsEmpty() {
		deck.isEmpty();
		assertTrue(questions.size()==0);
	}
}