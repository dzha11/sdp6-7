package hero;

import strategy.MeleeAttack;

public class TankWarrior extends Hero {
    public TankWarrior(Difficulty difficulty) {
        super("Tank Warrior", new MeleeAttack(), Difficulty.HARD);  // Пример сложности, может быть изменен
    }

    @Override
    public void attack(Hero target) {
        super.attack(target);
        System.out.println("TankWarrior strikes with a shield slam on " + target.getName() + "!");
    }
}
