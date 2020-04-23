package testunitaire;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Question;

public class TestQuestion {

	private static String author="test",theme="ThemeTeste",answer="Answer";
	private static List<String>listClues;
	private static String clue1="I'm a clue",clue2="I'm a second clue",clue3="I'm the last clue";
	private static Question question;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		listClues=new ArrayList<>();
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

	/**
	 * test checkQuestion method
	 */
	@Test
	public void testCheckQuestion() {
		listClues.add(clue1);
		listClues.add(clue2);
		listClues.add(clue3);
		question=new Question(author,theme,listClues,answer);
		question.checkQuestion();
		assertTrue(listClues.size()==3 && !(listClues.contains("") || listClues.contains(null)) && !(question.getAuthor().equalsIgnoreCase("")));
	}
	
	@Test
	public void testCheckQuestionWith2Clues() {
		listClues.add(clue1);
		listClues.add(clue2);
		question=new Question(author,theme,listClues,answer);
		question.checkQuestion();
		assertTrue(listClues.size()!=3);
	}
	@Test
	public void testCheckQuestionWithoutAuthor() {
		listClues.add(clue1);
		listClues.add(clue2);
		listClues.add(clue3);
		author="";
		question=new Question(author,theme,listClues,answer);
		question.checkQuestion();
		assertTrue(author.equalsIgnoreCase("") && listClues.size()==3);
	}

	@Test
	public void testCheckQuestionWithToLongClue() {
		listClues.add(clue1);
		listClues.add(clue2);
		clue3="hello. I am the third, last and longest index of the test but the maximum size of characters to be validated is one hundred and fifty. Anyway, I’m a clue.";
		listClues.add(clue3);
		question=new Question(author,theme,listClues,answer);
		question.checkQuestion();
		assertTrue(listClues.size()!=2 && author.equals(""));
	}

	
	
	/**
	 * Test getClue method
	 */
	@Test
	public void testGetClue() {
		listClues.add(clue1);
		listClues.add(clue2);
		listClues.add(clue3);
		question=new Question(author,theme,listClues,answer);
		int index=0;
		String clue=question.getClue(index);
		assertTrue(clue1.equals(clue));
	}
}
