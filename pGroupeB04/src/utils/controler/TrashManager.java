package utils.controler;

import model.IteratorQuestion;
import model.Question;
import model.Trash;

import java.util.ArrayList;
import java.util.List;

public class TrashManager {

    private static List<String> themes;
    private static Trash trash;

    private TrashManager() {
        trash = new Trash();
        themes = new ArrayList<>();
    }

    public static Trash getTrash() {
        return trash;
    }

    public static List<String> getThemes() {
        buildTheme();
        return themes;
    }

    public static void buildTheme() {
        if (trash == null) buildTrash();

        for (Question question : trash.getList()) {
            if (!themes.contains(question.getTheme())) {
                themes.add(question.getTheme());
            }
        }
    }

    /**
     * Create the TrashManager by creating the list of themes that compose it
     * as well as the trash can that contains the questions
     */
    public static void buildTrash() {
        new TrashManager();
    }

    /**
     * Will reload the last question deleted in relation to the theme we want
     * @param theme the theme of the question removed
     * @return a question if question exists
     */
    public static Question reloadLastDeleted(String theme) {
        Question reload = null;
        IteratorQuestion itTrash = trash.createIterator();
        itTrash.setLastIndex();
        while (itTrash.hasPrevious()) {
            if (itTrash.item().getTheme().equals(theme)) {
                reload = itTrash.item();
                break;
            }
            itTrash.previous();
        }
        trash.removeQuestion(reload);
        return reload;
    }
}
