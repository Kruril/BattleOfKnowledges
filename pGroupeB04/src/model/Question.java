package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Question implements Serializable{

	private String author;
	private String theme;
	private List<String> clues;
	private String answer;


	public Question(String author, String theme,String answer) {
		this.author = author;
		this.theme = theme;
		this.answer = answer;
		clues = new ArrayList<>();
	}

	public Question(String author, String theme, List<String> clues, String answer) {
		this.author = author;
		this.theme = theme;
		this.clues = clues;
		this.answer = answer;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public List<String> getClues() {
		return clues;
	}

	public void setClues(List<String> clues) {
		this.clues = clues;
	}

	public void setClue(int index, List<String> value) {
		if (index < clues.size()) clues = value;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		result = prime * result + ((theme == null) ? 0 : theme.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (answer == null) {
			if (other.answer != null)
				return false;
		} else if (!answer.equalsIgnoreCase(other.answer))
			return false;
		if (theme == null) {
			return other.theme == null;
		} else return theme.equalsIgnoreCase(other.theme);
	}


	@Override
	public String toString() {
		return "Question [author=" + author + ", theme=" + theme + ", clues=" + clues + ", answer=" + answer + "]";
	}
	
	public Question clone() {
		return new Question(author, theme, clues, answer);
	}

	public boolean checkQuestion() {
		if (clues.size() != 3) return false;
		if (author.equalsIgnoreCase("") || author.equalsIgnoreCase(null)) return false;
		if (clues.contains("") || clues.contains(null)) return false;
		if (clues.removeIf(clue -> clue.length() > 150)) return false;
		return true;
	}

	public String getClue(int index) {
		return clues.get(index);
	}

	public void set(Question question) {
		this.setClues(question.clues);
		this.setAnswer(question.answer);
		this.setAuthor(question.author);
		this.setTheme(question.theme);
	}

}
