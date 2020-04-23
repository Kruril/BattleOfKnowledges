package testunitaire;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Deck;
import model.Question;
import test.Explorateur;

public class TestDeck {
	
	
	static Question quest;
	static Deck deck;
	static List<Question>question;
	static String author="Author",theme="theme",answer="answer";
	static String clue1="I'm a clue",clue2="I'm a second clue",clue3="I'm the last clue";
	static List<String>clues= new ArrayList<>();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		deck=new Deck();
		question= (List<Question>) Explorateur.getField(deck, "questions");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testModifyQuestion() {
		clues.add(clue1);
		clues.add(clue2);
		clues.add(clue3);
		System.out.println(clues);
		quest=new Question(author,theme,clues,answer);
		question.add(quest);
		Question quest2=new Question("NewAuthor","NewTheme",clues,"NewAnwser");
		deck.modifyQuestion(quest, quest2);
		assertTrue(question.contains(quest2) && !(question.contains(quest)));
	}
	
	@Test
	public void testModifyQuestionWithException() {
		clues.add(clue1);
		clues.add(clue2);
		clues.add(clue3);
		System.out.println(clues);
		quest=new Question(author,theme,clues,answer);
		question.add(quest);
		Question quest2=new Question("NewAuthor","NewTheme",clues,"NewAnwser");
		deck.modifyQuestion(quest2, quest);
		assertTrue(question.contains(quest) && !(question.contains(quest2)));
	}
	

	@Test
	public void testFindQuestion() {
		int index=0;
		clues.add(clue1);
		clues.add(clue2);
		clues.add(clue3);
		quest=new Question(author,theme,clues,answer);
		question.add(quest);
		deck.findQuestion(quest);
		assertTrue(question.get(index).equals(quest));	
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
