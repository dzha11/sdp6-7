package gui;

import hero.Hero;
import hero.Difficulty;
import hero.HeroFactory;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import strategy.*;

public class HeroSelectionScreen extends Application {

    private Difficulty selectedDifficulty = Difficulty.MEDIUM;
    private Hero selectedHero;
    private Label battleLog;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Choose Your Hero ⚔️");

        Label title = new Label("Choose Your Hero and Difficulty");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        battleLog = new Label("Press 1–5 to select hero, 6–8 to set difficulty, ENTER to start battle");
        battleLog.setStyle("-fx-text-fill: #2c3e50; -fx-font-size: 14px;");


        Button warriorBtn = new Button("🗡 Warrior");
        Button mageBtn = new Button("🪄 Mage");
        Button archerBtn = new Button("🏹 Archer");
        Button fireMageBtn = new Button("🔥 Fire Mage");
        Button tankWarriorBtn = new Button("🛡 Tank Warrior");

        Button easyBtn = new Button("Easy");
        Button mediumBtn = new Button("Medium");
        Button hardBtn = new Button("Hard");

        Button startBattleBtn = new Button("Start Battle");
        startBattleBtn.setVisible(false);

        warriorBtn.setOnAction(e -> selectHero("Warrior"));
        mageBtn.setOnAction(e -> selectHero("Mage"));
        archerBtn.setOnAction(e -> selectHero("Archer"));
        fireMageBtn.setOnAction(e -> selectHero("FireMage"));
        tankWarriorBtn.setOnAction(e -> selectHero("TankWarrior"));

        easyBtn.setOnAction(e -> setDifficulty(Difficulty.EASY));
        mediumBtn.setOnAction(e -> setDifficulty(Difficulty.MEDIUM));
        hardBtn.setOnAction(e -> setDifficulty(Difficulty.HARD));

        startBattleBtn.setOnAction(e -> startBattle(primaryStage));

        VBox root = new VBox(15,
                title,
                warriorBtn, mageBtn, archerBtn, fireMageBtn, tankWarriorBtn,
                easyBtn, mediumBtn, hardBtn,
                battleLog, startBattleBtn);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #f0f8ff, #cce0ff);");

        Scene scene = new Scene(root, 700, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case DIGIT1 -> selectHero("Warrior");
                case DIGIT2 -> selectHero("Mage");
                case DIGIT3 -> selectHero("Archer");
                case DIGIT4 -> selectHero("FireMage");
                case DIGIT5 -> selectHero("TankWarrior");

                case DIGIT6 -> setDifficulty(Difficulty.EASY);
                case DIGIT7 -> setDifficulty(Difficulty.MEDIUM);
                case DIGIT8 -> setDifficulty(Difficulty.HARD);

                case ENTER -> {
                    if (selectedHero != null) startBattle(primaryStage);
                }
                default -> {}
            }
            startBattleBtn.setVisible(selectedHero != null);
        });
    }

    private void selectHero(String heroName) {
        switch (heroName) {
            case "Warrior" -> selectedHero = new Hero("Warrior", new MeleeAttack(), selectedDifficulty);
            case "Mage" -> selectedHero = new Hero("Mage", new MagicAttack(), selectedDifficulty);
            case "Archer" -> selectedHero = new Hero("Archer", new RangedAttack(), selectedDifficulty);
            case "FireMage" -> selectedHero = new Hero("FireMage", new MagicAttack(), selectedDifficulty);
            case "TankWarrior" -> selectedHero = new Hero("TankWarrior", new MeleeAttack(), selectedDifficulty);
        }
        battleLog.setText("Selected Hero: " + selectedHero.getName());
    }

    private void setDifficulty(Difficulty difficulty) {
        selectedDifficulty = difficulty;
        battleLog.setText("Difficulty set to: " + difficulty);
    }

    private void startBattle(Stage primaryStage) {
        Hero enemy;
        // босс только если выбрали танка
        if (selectedHero.getName().equals("TankWarrior")) {
            enemy = HeroFactory.createHero("FinalBoss", selectedDifficulty);
        } else {
            enemy = HeroFactory.createHero("Mage", selectedDifficulty);
        }

        HeroBattleGameGUI game = new HeroBattleGameGUI(selectedHero, enemy);
        game.startBattleScene(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
