package utils.controler;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

import enumeration.Time;
import model.Question;

public class ParameterHost implements Serializable {
	private int time;
	private int interval;
	private int randomNumber;
	private String theme;
	private List<Question> questions;
	private final boolean CONTINUE=true;
	
	
	public ParameterHost() {
		this.time=Time.TIMER_TIME.getValue();
		this.interval=Time.INTERVAL.getValue();
	}


	public int getTime() {
		return time;
	}

	public int getInterval() {
		return interval;
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

	@Override
	public String toString() {
		return "ParameterHost{" +
				"time=" + time +
				", interval=" + interval +
				", randomNumber=" + randomNumber +
				", theme='" + theme + '\'' +
				", questions=" + questions +
				", CONTINUE=" + CONTINUE +
				'}';
	}
	
	public void loadQuestions(List<Question> listQuestions) {
		this.questions = listQuestions;
	}
}
