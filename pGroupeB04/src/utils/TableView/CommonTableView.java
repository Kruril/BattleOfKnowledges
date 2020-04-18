package utils.TableView;

import application.Main;
import javafx.scene.control.TableView;

public abstract class CommonTableView {

    /**
     * Change the height of a tableview according to the size of the window
     * @param tableView that we want to modify
     */
    public static void changeHeight(TableView tableView) {
        tableView.setMaxHeight(Main.getStage().getHeight() - 350);
    }

    /**
     * Get a String element to put its first letter in upper case and the rest in lower case
     * @param element Character chain that we want to change
     * @return the changed element
     */
    public static String upperLowerText(String element) {
        char[] tmp = element.toLowerCase().toCharArray();
        if (tmp.length <= 0) return "";
        tmp[0] = Character.toUpperCase(tmp[0]);
        return new String(tmp);
    }
}
