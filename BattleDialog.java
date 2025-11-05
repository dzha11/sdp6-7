package gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BattleDialog {

    public static void showPreBattleDialog(String heroName, String enemyName) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Pre-Battle Dialog");
        alert.setHeaderText("Prepare for battle!");
        alert.setContentText(heroName + " is ready to fight " + enemyName + "!");
        alert.showAndWait();
    }
}
