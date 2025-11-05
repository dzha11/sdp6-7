package strategy;

import hero.Hero;

//hеализация ближней атаки.yаносит средний урон и подходит для воинов.
 
public class MeleeAttack implements AttackStrategy {

    @Override
    public int attack(Hero attacker, Hero target) {
        int damage = 20;
        target.decreaseHealth(damage);
        System.out.println(attacker.getName() + " strikes " + target.getName() +
                " for " + damage + " damage (Melee Attack)!");
        return damage;
    }
}
