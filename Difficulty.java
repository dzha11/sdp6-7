package hero;

public enum Difficulty {
    EASY(50),  // легкий уровень (50 HP)
    MEDIUM(100),  // средний уровень (100 HP)
    HARD(150);  // сложный уровень (150 HP)

    private final int health;

    Difficulty(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }
}
