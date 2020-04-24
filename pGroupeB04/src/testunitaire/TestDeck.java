package testunitaire;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Pack;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Deck;
import model.Question;
import test.Explorateur;

public class TestDeck {

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
	public void testModifyQuestion() {
	}

	@Test
	public void testFindQuestion() {
		Question question = new Question(author, theme, Arrays.asList(clue1,clue2,clue3), answer);
		System.out.println(question);
		questions.add(question);
		System.out.println(questions);
		System.out.println(deck.getList());
		deck.findQuestion(question);
		System.out.println(deck.findQuestion(question));
	}

	@Test
	public void testToJson() {
		fail();
	}

	@Test
	public void testFromJson() {
		fail();
	}

	@Test
	public void testFromJsonFile() {
		fail();
	}

	@Test
	public void testCreateIteratorString() {
		fail();
	}

	@Test
	public void testCheckTheme() {
		fail();
	}

}
