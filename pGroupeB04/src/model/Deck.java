package model;

import serialisation.LectureEcriture;
import utils.JsonManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Deck {

	private List<Question> questions;
	
	
	public Deck() {
		questions = new ArrayList<>();
	}
	
	public List<Question> getListe() {
		return new ArrayList<>(questions);
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public boolean addQuestion(Question questionAdd) {
		if (questions.contains(questionAdd)) return false;
		questions.add(questionAdd);
        return true;
    }
	
	public boolean removeQuestion(Question questionASuprimer) {
		if (!questions.contains(questionASuprimer)) return false;
		questions.remove(questionASuprimer);
		return true;
	}
	
	public void modifyQuestion(Question questionOld, Question questionNew) {
		int index = questions.indexOf(questionOld);
		if (index == -1) return;
		questions.set(index, questionNew);
	}
	
	public Question findQuestion(Question questionFinding) {
		int index = questions.indexOf(questionFinding);
		if (index == -1) return null;
		return questions.get(index);
	}
	
    @Override
	public String toString(){
    	StringBuilder tpm = new StringBuilder();
        for (Question q : questions) {
        	tpm.append(q.toString()).append("\n");
        }
        return tpm.toString();
    }

	public void toJson(){
		LectureEcriture.writeStringDeck(this);
	}

	public void fromJson(){
		this.setQuestions(LectureEcriture.readStringDeck().getListe());
	}

	public void fromJson(File file) {
		this.setQuestions(LectureEcriture.readStringDeck(file).getListe());
	}

	public static IteratorQuestion createIterator(String theme) {
		return new IteratorQuestion(JsonManager.choiceTheme(theme));
	}

	public void checkTheme(String theme) {
		questions.removeIf(quest -> !quest.getTheme().equals(theme));
	}

}


