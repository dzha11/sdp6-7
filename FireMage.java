package hero;

import strategy.MagicAttack;

public class FireMage extends Hero {
    public FireMage(Difficulty difficulty) {
        super("Fire Mage", new MagicAttack(), Difficulty.MEDIUM);  // Пример сложности, может быть изменен
    }

    @Override
    public void attack(Hero target) {
        super.attack(target);
        System.out.println("FireMage casts a fireball on " + target.getName() + "!");
    }
}
