package utils.controler;

import enumeration.Time;
import model.Question;

import java.io.Serializable;
import java.util.List;

public class ParameterHost implements Serializable {
	private int time;
	private int interval;
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
				", questions=" + questions +
				", CONTINUE=" + CONTINUE +
				'}';
	}
	
	public void loadQuestions(List<Question> listQuestions) {
		this.questions = listQuestions;
	}
}
