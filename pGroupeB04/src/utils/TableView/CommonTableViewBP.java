package utils.TableView;

import application.Main;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class CommonTableViewBP extends BorderPane {

    public void changeHeight(TableView tableView) {
        tableView.setMaxHeight(Main.getStage().getHeight() - 290);
    }
}
