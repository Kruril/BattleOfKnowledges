package utils.controler;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

import enumeration.Time;
import model.Question;

public class ParameterHost implements Serializable{
	private int time;
	private int interval;
	private int randomNumber;
	private String theme;
	private List<Question> questions;
	private final boolean CONTINUE=true;
	
	
	public ParameterHost() {
		this.time=Time.TIMER_TIME.getValue();
		this.interval=Time.INTERVAL.getValue();
		do {
			this.randomNumber=new Random().nextInt(JsonManager.getThemes().size());
			this.theme=JsonManager.getThemes().get(randomNumber);
			this.questions=JsonManager.choiceTheme(theme);
		}while(this.questions.size()<10);
		
		
	}


	public int getTime() {
		return time;
	}


	public int getInterval() {
		return interval;
	}


	public int getRandomNumber() {
		return randomNumber;
	}


	public String getTheme() {
		return theme;
	}


	public List<Question> getQuestions() {
		return questions;
	}


	public boolean isCONTINUE() {
		return CONTINUE;
	}
	
	
}
