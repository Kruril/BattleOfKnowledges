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

	private String author,theme,answer;
	private List<String>listClues=new ArrayList<>();
	private String clue1,clue2,clue3;
	private Question question;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		author="test";
		theme="theme";
		answer="Answer";
		clue1="I'm a clue";
		clue2="I'm a second clue";
		clue3="I'm the last clue";
	}

	@After
	public void tearDown() throws Exception {
		listClues.clear();
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
	}
	
	@Test
	public void testCheckQuestionWith2Clues() {
		listClues.add(clue1);
		listClues.add(clue2);
		question=new Question(author,theme,listClues,answer);
		question.checkQuestion();
	}
	@Test
	public void testCheckQuestionWithoutAuthor() {
		listClues.add(clue1);
		listClues.add(clue2);
		listClues.add(clue3);
		author="";
		question=new Question(author,theme,listClues,answer);
		question.checkQuestion();
	}

	@Test
	public void testCheckQuestionWithEmptyClue() {
		listClues.add(clue1);
		listClues.add(clue2);
		listClues.add("");
		question=new Question(author,theme,listClues,answer);
		question.checkQuestion();
	}


	@Test
	public void testCheckQuestionWithNullClue() {
		listClues.add(clue1);
		listClues.add(clue2);
		listClues.add(null);
		question=new Question(author,theme,listClues,answer);
		question.checkQuestion();
	}
	
	@Test
	public void testCheckQuestionWithToLongClue() {
		listClues.add(clue1);
		listClues.add(clue2);
		clue3="Hello I am the third last and longest index of the unit test but the maximum size of an index is one hundred and fifty characters . Anyway I am a clue.";
		listClues.add(clue3);
		question=new Question(author,theme,listClues,answer);
		question.checkQuestion();
		
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
