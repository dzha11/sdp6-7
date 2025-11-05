package game;

import hero.Hero;
import strategy.*;

public class GameFacade {
    private Hero player;
    private Hero enemy;

    public GameFacade(Hero player, Hero enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    // запуск битвы
    public void startBattle() {
        System.out.println(player.getName() + " starts fighting " + enemy.getName());
    }

    public void changeStrategy(String strategy) {
        switch (strategy) {
            case "Melee":
                player.setAttackStrategy(new MeleeAttack());
                break;
            case "Ranged":
                player.setAttackStrategy(new RangedAttack());
                break;
            case "Magic":
                player.setAttackStrategy(new MagicAttack());
                break;
        }
        System.out.println(player.getName() + " switched to " + strategy + " attack!");
    }

    public void attackEnemy() {
        player.attack(enemy);
        if (enemy.getHealth() <= 0) {
            System.out.println(enemy.getName() + " has been defeated!");
        }
    }
}
