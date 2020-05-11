package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import enumeration.Path;
import serialisation.Serialization;
import utils.controler.JsonManager;

public class Deck implements Pack {

	List<Question> questions;

	public Deck() {
		questions = new ArrayList<>();
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	/**
	 * Modify a question by giving the old question
	 * to modify as well as the new one
	 * @param questionOld question we want to change
	 * @param questionNew question with new values
	 */
	public void modifyQuestion(Question questionOld, Question questionNew) {
		int index = findQuestion(questionOld);
		if (index == -1) return;
		questions.set(index, questionNew);
	}

	/**
	 * Find the index of a question passed to it in parameter
	 * @param questionFinding question we want to find
	 * @return the index of the question if it exists otherwise -1
	 */
	public int findQuestion(Question questionFinding) {
		return questions.indexOf(questionFinding);
	}

	/**
	 * Save the desired deck to a json file.
	 * The path to this file is defined by default
	 */
	public void toJson(){
		Serialization.writeJson(Path.FILE_THEME.getPath(),this);
	}

	/**
	 * Load a deck from a json file.
	 * The file path is defined by default
	 */
	public void fromJson(){
		this.setQuestions(Serialization.readJson(Path.FILE_THEME.getPath(), this.getClass()).getList());
	}

	/**
	 * Load a deck from a json file.
	 * The file must be specified
	 * @param file File that you want to load.
	 *                Must be in json format
	 */
	public void fromJson(File file) {
		this.setQuestions(Serialization.readJson(file, this.getClass()).getList());
	}

	/**
	 * create a question iterator via a chosen theme.
	 * Will return an iterator containing the list of questions
	 * on the chosen theme
	 * @param theme theme we want
	 * @return Returns an iterator containing the questions with the chosen theme
	 */
	public IteratorQuestion createIterator(String theme) {
		return new IteratorQuestion(JsonManager.choiceTheme(theme));
	}

	/**
	 * Check the theme of each question in the deck
	 * if he finds a question that contains a different theme
	 * then he will delete the question
	 * @param theme the reference theme
	 */
	public void checkTheme(String theme) {
		questions.removeIf(quest -> !quest.getTheme().equalsIgnoreCase(theme));
	}

	@Override
	public String toString() {
		StringBuilder tpm = new StringBuilder();
		for (Question q : questions) {
			tpm.append(q.toString()).append("\n");
		}
		return tpm.toString();
	}

	@Override
	public List<Question> getList() {
		return questions;
	}

	@Override
	public boolean addQuestion(Question questionAdd) {
		if (questions.contains(questionAdd) || !questionAdd.checkQuestion()) return false;
		questions.add(questionAdd);
		return true;
	}

	@Override
	public boolean removeQuestion(Question questionRemove) {
		if (!questions.contains(questionRemove)) return false;
		questions.remove(questionRemove);
		return true;
	}

	@Override
	public IteratorQuestion createIterator(){
		return new IteratorQuestion(questions);
	}

	@Override
	public boolean isEmpty() {
		if (questions == null) return true;
		return questions.size() == 0;
	}

}


