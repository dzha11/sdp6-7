package gui;

import hero.Hero;
import hero.Difficulty;
import hero.HeroFactory;
import javafx.animation.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import strategy.*;
import javafx.scene.input.KeyCode;
import java.net.URL;

public class HeroBattleGameGUI {

    private Hero player;
    private Hero enemy;
    private ProgressBar playerHealthBar;
    private ProgressBar enemyHealthBar;
    private Label battleLog;
    private ImageView playerView;
    private ImageView enemyView;
    private Button restartButton;
    private Button nextStageButton;

    public HeroBattleGameGUI(Hero player, Hero enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    public void startBattleScene(Stage primaryStage) {
        primaryStage.setTitle("Hero Battle Arena ⚔️");

        playerView = loadHeroImage(player.getName());
        enemyView = loadHeroImage(enemy.getName());

        playerView.setFitHeight(200);
        playerView.setFitWidth(200);
        enemyView.setFitHeight(200);
        enemyView.setFitWidth(200);

        // полоски хп
        playerHealthBar = new ProgressBar(1.0);
        enemyHealthBar = new ProgressBar(1.0);

        Label playerName = new Label(player.getName());
        Label enemyName = new Label(enemy.getName());
        playerName.setTextFill(Color.DARKBLUE);
        enemyName.setTextFill(Color.DARKRED);

        // баттоны
        Button attackBtn = new Button("Attack!");
        Button switchStrategyBtn = new Button("Change Strategy");

        Label controlsHint = new Label("🎮 Press 'A' to attack or use buttons below");
        controlsHint.setTextFill(Color.DARKGRAY);
        controlsHint.setStyle("-fx-font-size: 13px;");

        battleLog = new Label("Battle begins!");
        battleLog.setTextFill(Color.DARKGREEN);
        battleLog.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        attackBtn.setOnAction(e -> attack());
        switchStrategyBtn.setOnAction(e -> changeStrategy());

        restartButton = new Button("Restart");
        restartButton.setOnAction(e -> restartGame(primaryStage));
        restartButton.setVisible(false);

        nextStageButton = new Button("Next Stage");
        nextStageButton.setOnAction(e -> nextStage(primaryStage));
        nextStageButton.setVisible(false);

        // разметка
        HBox heroImages = new HBox(80, playerView, enemyView);
        heroImages.setAlignment(Pos.CENTER);

        HBox healthBars = new HBox(60,
                new VBox(5, playerName, playerHealthBar),
                new VBox(5, enemyName, enemyHealthBar)
        );
        healthBars.setAlignment(Pos.CENTER);

        HBox buttons = new HBox(20, attackBtn, switchStrategyBtn);
        buttons.setAlignment(Pos.CENTER);

        VBox root = new VBox(25, heroImages, healthBars, buttons, controlsHint, battleLog, restartButton, nextStageButton);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #eef3ff, #d3e3ff);");

        Scene scene = new Scene(root, 700, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        // управление клавишами
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.A) attack();
        });

        playBackgroundMusic();
    }

    private ImageView loadHeroImage(String name) {
        if (name == null) name = "";
        String lower = name.toLowerCase();

        String path;
        if (lower.contains("warrior") && !lower.contains("tank")) {
            path = "file:src/main/resources/warrior.png";
        } else if (lower.contains("mage") && !lower.contains("fire")) {
            path = "file:src/main/resources/mage.png";
        } else if (lower.contains("archer")) {
            path = "file:src/main/resources/archer.png";
        } else if (lower.contains("firemage") || lower.contains("fire mage")) {
            path = "file:src/main/resources/firemage.png";
        } else if (lower.contains("tankwarrior") || lower.contains("tank warrior")) {
            path = "file:src/main/resources/tankwarrior.png";
        } else if (lower.contains("finalboss") || lower.contains("final boss")) {
            path = "file:src/main/resources/finalboss.png";
        } else {

            path = "file:src/main/resources/unknown.png";
        }

        return new ImageView(new Image(path));
    }

    // атака
    private void attack() {
        playAttackAnimation(playerView);
        playAttackSound();

        if (enemy.getHealth() > 0) {
            player.attack(enemy);
            updateHealthBars(enemyHealthBar, enemy.getHealth());
            playDamageEffect(enemyView);
            playShakeEffect(enemyView);
            battleLog.setText(player.getName() + " attacks " + enemy.getName() + "!");
        }

        if (enemy.getHealth() <= 0) {
            battleLog.setText(enemy.getName() + " has been defeated! 🏆");
            victoryAnimation();
            enemyDeathAnimation(enemyView);
            nextStageButton.setVisible(true);
            showResultDialog("Victory! 🎉", "You defeated " + enemy.getName(), true);
        } else {
            enemyTurn();
        }
    }

    // атака врага
    private void enemyTurn() {
        if (player.getHealth() > 0) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1.2));
            delay.setOnFinished(event -> {
                playAttackAnimation(enemyView);
                playAttackSound();

                enemy.attack(player);
                updateHealthBars(playerHealthBar, player.getHealth());
                playDamageEffect(playerView);
                playShakeEffect(playerView);

                if (player.getHealth() <= 0) {
                    battleLog.setText(player.getName() + " has fallen... 💀");
                    playerDeathAnimation(playerView);
                    restartButton.setVisible(true);
                    showResultDialog("Defeat 💀", "You were slain by " + enemy.getName(), false);
                } else {
                    battleLog.setText(enemy.getName() + " strikes back!");
                }
            });
            delay.play();
        }
    }

    // смена стратегии
    private void changeStrategy() {
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Melee", "Melee", "Ranged", "Magic");
        dialog.setTitle("Change Strategy");
        dialog.setHeaderText("Choose a new attack type");
        dialog.setContentText("Strategy:");

        dialog.showAndWait().ifPresent(choice -> {
            switch (choice) {
                case "Melee" -> player.setAttackStrategy(new MeleeAttack());
                case "Ranged" -> player.setAttackStrategy(new RangedAttack());
                case "Magic" -> player.setAttackStrategy(new MagicAttack());
            }
            battleLog.setText(player.getName() + " switched to " + choice + " attack!");
        });
    }

    private void updateHealthBars(ProgressBar bar, int health) {
        double target = Math.max(0, health / 100.0);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.5), new KeyValue(bar.progressProperty(), target))
        );
        timeline.play();
    }

    // эффекты
    private void playAttackAnimation(ImageView attacker) {
        TranslateTransition attackMove = new TranslateTransition(Duration.millis(150), attacker);
        attackMove.setByX(attacker == playerView ? 40 : -40);
        attackMove.setAutoReverse(true);
        attackMove.setCycleCount(2);
        attackMove.play();
    }

    private void playDamageEffect(ImageView target) {
        ColorAdjust colorAdjust = new ColorAdjust();
        target.setEffect(colorAdjust);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(colorAdjust.brightnessProperty(), 0)),
                new KeyFrame(Duration.seconds(0.1), new KeyValue(colorAdjust.brightnessProperty(), 0.5)),
                new KeyFrame(Duration.seconds(0.2), new KeyValue(colorAdjust.brightnessProperty(), 0))
        );
        timeline.play();
    }

    private void playShakeEffect(ImageView target) {
        TranslateTransition shake = new TranslateTransition(Duration.millis(50), target);
        shake.setByX(10);
        shake.setCycleCount(6);
        shake.setAutoReverse(true);
        shake.play();
    }

    private void enemyDeathAnimation(ImageView enemy) {
        FadeTransition fade = new FadeTransition(Duration.seconds(1.2), enemy);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);
        fade.play();
    }

    private void playerDeathAnimation(ImageView player) {
        RotateTransition rotate = new RotateTransition(Duration.seconds(1.5), player);
        rotate.setByAngle(90);
        rotate.play();
    }


    private void playAttackSound() {
        try {
            URL soundUrl = getClass().getResource("/attack.mp3");
            if (soundUrl != null) {
                AudioClip clip = new AudioClip(soundUrl.toExternalForm());
                clip.setVolume(1.0);
                clip.play();
            }
        } catch (Exception e) {
            System.out.println("⚠️ Sound not found: " + e.getMessage());
        }
    }

    private void victoryAnimation() {
        FadeTransition fade = new FadeTransition(Duration.seconds(2), battleLog);
        fade.setFromValue(0);
        fade.setToValue(1);
        battleLog.setText("Victory!");
        fade.play();
    }

    private void playBackgroundMusic() {
        try {
            URL soundUrl = getClass().getResource("/backgroundMusic.mp3");
            if (soundUrl != null) {
                AudioClip bgMusic = new AudioClip(soundUrl.toExternalForm());
                bgMusic.setCycleCount(AudioClip.INDEFINITE);
                bgMusic.setVolume(0.3);
                bgMusic.play();
            }
        } catch (Exception e) {
            System.out.println("⚠️ Music error: " + e.getMessage());
        }
    }

    private void restartGame(Stage primaryStage) {
        HeroSelectionScreen newScreen = new HeroSelectionScreen();
        newScreen.start(primaryStage);
    }

    private void nextStage(Stage primaryStage) {
        Hero nextEnemy;
        String stageMessage;

        if (enemy.getName().equals("Mage")) {
            nextEnemy = HeroFactory.createHero("FinalBoss", Difficulty.MEDIUM);
            stageMessage = "⚔️ Stage 2 — Final Boss Appears!";
        } else {
            nextEnemy = HeroFactory.createHero("Mage", Difficulty.MEDIUM);
            stageMessage = "⚔️ Stage 1 — Battle Begins!";
        }

        Label stageLabel = new Label(stageMessage);
        stageLabel.setTextFill(Color.GOLD);
        stageLabel.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");
        stageLabel.setOpacity(0);

        StackPane overlay = new StackPane(stageLabel);
        overlay.setPrefSize(700, 600);
        overlay.setAlignment(Pos.CENTER);
        ((Pane) primaryStage.getScene().getRoot()).getChildren().add(overlay);

        FadeTransition show = new FadeTransition(Duration.seconds(1.2), stageLabel);
        show.setFromValue(0);
        show.setToValue(1);

        FadeTransition hide = new FadeTransition(Duration.seconds(1.2), stageLabel);
        hide.setFromValue(1);
        hide.setToValue(0);

        hide.setOnFinished(e -> {
            HeroBattleGameGUI nextBattle = new HeroBattleGameGUI(player, nextEnemy);
            nextBattle.startBattleScene(primaryStage);
        });

        SequentialTransition seq = new SequentialTransition(show, new PauseTransition(Duration.seconds(1.2)), hide);
        seq.play();
    }

    private void showResultDialog(String title, String message, boolean victory) {
        Alert alert = new Alert(victory ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message + "\n\nPress Restart to play again or Exit to quit.");

        ButtonType restart = new ButtonType("Restart");
        ButtonType exit = new ButtonType("Exit");

        alert.getButtonTypes().setAll(restart, exit);

        alert.showAndWait().ifPresent(response -> {
            if (response == restart) {
                restartGame((Stage) playerView.getScene().getWindow());
            } else {
                System.exit(0);
            }
        });
    }
}
