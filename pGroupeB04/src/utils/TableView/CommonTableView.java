package utils.TableView;

import application.Main;
import javafx.scene.control.TableView;

public class CommonTableView {

    public static void changeHeight(TableView tableView) {
        tableView.setMaxHeight(Main.getStage().getHeight() - 290);
    }
}
