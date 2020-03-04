package model;

import com.google.gson.Gson;
import serialisation.LectureEcriture;

import java.util.ArrayList;
import java.util.List;

public class Deck {
	private List<Question> questions;
	
	
	public Deck() {
		questions = new ArrayList<>();
	}
	
	public List<Question> getListe(List<Question> questions) {
		List<Question> cloneListe = new ArrayList<>();
		for (Question question : questions) {
			cloneListe.add(question);
		}
		return cloneListe;
	}
	
	public void addQuestion(Question questionAdd) {
		if (questions.contains(questionAdd)) return;
		questions.add(questionAdd);
	}
	
	public void removeQuestion(Question questionASuprimer) {
		questions.remove(questionASuprimer);
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
    	String tpm = "";        
        for (Question q : questions) {
        	tpm += q.toString() + "\n";
        }
        return tpm;
    }

	public String toJson(){
		Gson gson = new Gson();
		return gson.toJson( this);
	}

	public static Deck fromJson(String fichier){
		Gson gson = new Gson();
		return LectureEcriture.readString(fichier);
	}
	
}


