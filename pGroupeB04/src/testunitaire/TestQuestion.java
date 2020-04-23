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
import test.Explorateur;

public class TestQuestion {
	private static String author="test",theme="ThemeTeste",answer="Answer";
	private static List<String> clue,listClues;
	private static String clue1="I'm a clue",clue2="I'm a second clue",clue3="I'm the last clue";
	private static Question question;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		clue=(List<String>) Explorateur.getField(question,"clues");
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
	public void testCheckQuestion() {
		clue.add(clue1);
		clue.add(clue2);
		clue.add(clue3);
		question=new Question(author,theme,clue,answer);
		question.checkQuestion();
		assertTrue(clue.size()==3 && !(clue.contains("") && clue.contains(null)));
	}
		
}
