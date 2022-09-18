package monsters;

public class MonsterGenerator {
    public enum Type {
        SQUIRREL,
        WOLF;
    }

    public static Persona generateMonster(Type type, int level) {
        Monster monster = null;
        switch (type) {
            case SQUIRREL -> {
                monster = new Squirrel();
                monster.setAgility(2 + level);
                monster.setDexterity(5 + level);
                monster.setDefence(level / 5);
                monster.setPower(1 + level / 5);
                monster.setMaxHp(7 + level * 2);
                monster.setHp(monster.getMaxHp());
            }
            case WOLF -> {
                monster = new Wolf();
                monster.setAgility(6 + level);
                monster.setDexterity(3 + level);
                monster.setDefence(1 + level / 5);
                monster.setPower(2 + level / 5);
                monster.setMaxHp(16 + level * 2);
                monster.setHp(monster.getMaxHp());
            }
        }
        monster.setLevel(level);
        return monster;
    }

}
