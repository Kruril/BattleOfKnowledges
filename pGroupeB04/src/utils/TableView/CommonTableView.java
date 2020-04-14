package utils.TableView;

import application.Main;
import javafx.scene.control.TableView;

public abstract class CommonTableView {

    public static void changeHeight(TableView tableView) {
        tableView.setMaxHeight(Main.getStage().getHeight() - 350);
    }

    public static String upperLowerText(String element) {
        char[] tmp = element.toLowerCase().toCharArray();
        if (tmp.length <= 0) return "";
        tmp[0] = Character.toUpperCase(tmp[0]);
        return new String(tmp);
    }
}
