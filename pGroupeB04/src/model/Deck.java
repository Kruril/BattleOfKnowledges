package model;

import serialisation.Serialization;
import utils.Path;
import utils.controler.JsonManager;

import java.io.File;
import java.util.List;

public class Deck extends Pack {
	
	
	public Deck() {
		super();
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

	public void checkTheme(String theme) {
		questions.removeIf(quest -> !quest.getTheme().equalsIgnoreCase(theme));
	}

}


