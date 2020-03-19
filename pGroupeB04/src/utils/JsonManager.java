package utils;

import model.Deck;
import model.Question;

import java.util.ArrayList;
import java.util.List;

public class JsonManager {

    public static final String FILE_THEME = "json/theme/theme.json";
    public static final String FILE_RESOLUTION = "json/resolution/resolution.json";

    private static Deck deck;

    public static final List<String> THEMES = new ArrayList<>();


    private JsonManager(){
        deck = new Deck();
        deck.fromJson();
    }

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

    public static List<Question> choiceTheme(String theme){
        List<Question> questions = new ArrayList<>();
        for (Question question : deck.getListe()) {
            if (question.getTheme().equals(theme)) {
                questions.add(question);
            }
        }
        return questions;
    }

    public static void main(String[] args) {
        themeFromDeck();
//        System.out.println(deck);
//        System.out.println(THEMES);
        choiceTheme("Animal");
    }
}
