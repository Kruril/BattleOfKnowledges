package utils;

import model.Deck;
import model.Question;

import java.util.ArrayList;
import java.util.List;

public class JsonManager {

    /**
     * path to different json
     */
    public static final String FILE_THEME = "json/theme/theme.json";
    public static final String FILE_RESOLUTION = "json/resolution/resolution.json";

    private static Deck deck;

    public static final List<String> THEMES = new ArrayList<>();


    private JsonManager(){
        deck = new Deck();
        deck.fromJson();
    }

    /**
     * find the different themes contained in the json
     */
    public static void themeFromDeck() {
        if (deck == null) {
            new JsonManager();

            for (Question quest : deck.getListe()) {
                if (!(THEMES.contains(quest.getTheme()))) {
                    THEMES.add(quest.getTheme());
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
