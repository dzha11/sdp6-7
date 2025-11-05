package hero;

import strategy.RangedAttack;

public class FinalBoss extends Hero {
    public FinalBoss(Difficulty difficulty) {
        super("Final Boss", new RangedAttack(), Difficulty.HARD);  // Пример сложности, может быть изменен
    }

    @Override
    public void attack(Hero target) {
        super.attack(target);
        System.out.println("FinalBoss unleashes an ultimate attack on " + target.getName() + "!");
    }
}
