package utils.GamePage;

import java.util.List;
import java.util.Random;

import model.Question;
import utils.controler.JsonManager;

public class RandomTheme {
	
	private static int randomNumber;
	private static String theme;
	private static List<Question> questions;
	
	public static List<Question> randomTheme() {
		if(questions==null) {
			do {
				randomNumber=new Random().nextInt(JsonManager.getThemes().size());
				theme=JsonManager.getThemes().get(randomNumber);
				questions=JsonManager.choiceTheme(theme);
			}while(questions.size()<10);
		}
		return questions;
	}
}
