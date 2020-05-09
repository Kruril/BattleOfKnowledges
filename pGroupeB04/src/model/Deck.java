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
	
	public void modifyQuestion(Question questionOld, Question questionNew) {
		int index = findQuestion(questionOld);
		if (index == -1) return;
		questions.set(index, questionNew);
	}
	public int findQuestion(Question questionFinding) {
		return questions.indexOf(questionFinding);
	}

	public void toJson(){
		Serialization.writeJson(Path.FILE_THEME.getPath(),this);
	}

	public void fromJson(){
		this.setQuestions(Serialization.readJson(Path.FILE_THEME.getPath(), this.getClass()).getList());
	}

	public void fromJson(File file) {
		this.setQuestions(Serialization.readJson(file, this.getClass()).getList());
	}

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

	/**
	 * Check if list is empty return true if list empty and false if list contain questions
	 * @return boolean true (list is empty) or false (list contain questions)
	 */
	@Override
	public boolean isEmpty() {
		if (questions == null) return true;
		return questions.size() == 0;
	}

}


