package utils;

import model.Deck;
import model.Question;

import java.util.ArrayList;
import java.util.List;

public class JsonManager implements Path {

    private static Deck deck;

    private static List<String> themes = new ArrayList<>();


    private JsonManager(){
        deck = new Deck();
        deck.fromJson();
    }

    public static List<String> getThemes() {
        return themes;
    }

    /**
     * find the different themes contained in the json
     */
    public static void themeFromDeck() {
        if (deck == null) {
            new JsonManager();

            for (Question quest : deck.getListe()) {
                if (!(themes.contains(quest.getTheme()))) {
                    themes.add(quest.getTheme());
                }
            }
        }
    }

    /**
     * Create a list on the chosen theme
     * @param theme chosen theme
     * @return a list contained the questions of chosen theme
     */
    public static List<Question> choiceTheme(String theme){
        List<Question> questions = new ArrayList<>();
        for (Question question : deck.getListe()) {
            if (question.getTheme().equals(theme)) {
                questions.add(question);
            }
        }
        return questions;
    }
}
