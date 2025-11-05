package game;

import hero.Hero;

public class Game {
    private Hero player;
    private Hero enemy;

    // констр, выбирааем игроков
    public Game(Hero player, Hero enemy) {
        this.player = player;
        this.enemy = enemy;
    }


    public void startGame() {
        System.out.println("Game Started!");
    }


    public void playerTurn() {
    }


    public void enemyTurn() {
    }
}
