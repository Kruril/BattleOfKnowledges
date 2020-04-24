package testunitaire;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Question;
import model.Trash;
import test.Explorateur;

public class TestTrash {
	private Trash trash;
	private List<Question> questions;
	private String author="Author",theme="theme",answer="answer";
	private String clue1="I'm a clue",clue2="I'm a second clue",clue3="I'm the last clue";
	private List<String> clues=new ArrayList<>();
	private Question quest;
	@Before
	public void setUp() throws Exception {
		trash = new Trash();
		questions = (List<Question>) Explorateur.getField(trash, "questions");
	}

	@After
	public void tearDown() throws Exception {
		clues=null;
		questions=null;
	}

	

	/**
	 * Test of the isEmpty method
	 */


	@Test
	public void testIsEmptyNull() {
		questions=null;
		trash.isEmpty();
		assertTrue(questions==null);
	}
	
	@Test
	public void testIsNotEmpty() {
		clues.add(clue1);
		clues.add(clue2);
		clues.add(clue3);
		quest=new Question(author,theme,clues,answer);
		questions.add(quest);
		trash.isEmpty();
		assertTrue(questions.size()!=0);
	}
	
	@Test
	public void testIsEmpty() {
		trash.isEmpty();
		assertTrue(questions.size()==0);
	}

	
	/**
	 * Test of the addQuestion method
	 */

	@Test
	public void testAddQuestion() {
		clues.add(clue1);
		clues.add(clue2);
		clues.add(clue3);
		quest=new Question(author,theme,clues,answer);
		trash.addQuestion(quest);
		assertTrue(questions.contains(quest) && questions.size()==1);
	}

	@Test
	public void testAddQuestionAlreadyAdded() {
		clues.add(clue1);
		clues.add(clue2);
		clues.add(clue3);
		quest=new Question(author,theme,clues,answer);
		questions.add(quest);
		trash.addQuestion(quest);
		assertTrue(questions.contains(quest) && questions.size()==1);
	}
	

	@Test
	public void testAddQuestionError() {
		clues.add(clue1);
		clues.add(clue2);
		quest=new Question(author,theme,clues,answer);
		trash.addQuestion(quest);
		assertTrue(quest.checkQuestion()==false);
	}
	
	
	
	/**
	 * test of removeQuestion method
	 */
	@Test
	public void testRemoveQuestion() {
		clues.add(clue1);
		clues.add(clue2);
		clues.add(clue3);
		quest=new Question(author,theme,clues,answer);
		questions.add(quest);
		trash.removeQuestion(quest);
		assertTrue(!(questions.contains(quest)) && questions.size()==0);
	}

	@Test
	public void testRemoveQuestionnotContained() {
		clues.add(clue1);
		clues.add(clue2);
		clues.add(clue3);
		quest=new Question(author,theme,clues,answer);
		trash.removeQuestion(quest);
		assertTrue(!(questions.contains(quest)) && questions.size()==0);
	}
	
	
}
