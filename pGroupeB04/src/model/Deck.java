package model;

import serialisation.LectureEcriture;
import utils.controler.JsonManager;

import java.io.File;
import java.util.List;

public class Deck extends Container{
	
	
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
		LectureEcriture.writeStringDeck(this);
	}

	public void fromJson(){
		this.setQuestions(LectureEcriture.readStringDeck().getList());
	}

	public void fromJson(File file) {
		this.setQuestions(LectureEcriture.readStringDeck(file).getList());
	}

	public IteratorQuestion createIterator(String theme) {
		return new IteratorQuestion(JsonManager.choiceTheme(theme));
	}

	public void checkTheme(String theme) {
		questions.removeIf(quest -> !quest.getTheme().equalsIgnoreCase(theme));
	}

}


