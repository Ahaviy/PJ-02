package monsters;

/**
 * Класс для генерации врагов
 */
public class MonsterGenerator {
    public enum Type {
        SQUIRREL, WOLF, LESHIY, ENT, SCELETON, VAMPIRE, GOBLIN, SCORPION;
    }

    /**
     * Возращает сгенерированный клас врага заданного типа и уровня
     * */
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
                monster.setWeaponDelay(600);
                monster.setHp(monster.getMaxHp());
            }
            case WOLF -> {
                monster = new Wolf();
                monster.setAgility(6 + level);
                monster.setDexterity(3 + level);
                monster.setDefence(1 + level / 5);
                monster.setPower(2 + level / 5);
                monster.setMaxHp(16 + level * 2);
                monster.setWeaponDelay(800);
                monster.setHp(monster.getMaxHp());
            }
            case LESHIY -> {
                monster = new Leshiy();
                monster.setAgility(7 + level);
                monster.setDexterity(7 + level);
                monster.setDefence(3 + level / 5);
                monster.setPower(3 + level / 5);
                monster.setMaxHp(25 + level * 2);
                monster.setWeaponDelay(1000);
                monster.setHp(monster.getMaxHp());
            }
            case ENT -> {
                monster = new Ent();
                monster.setAgility(6 + level);
                monster.setDexterity(6 + level);
                monster.setDefence(4 + level / 5);
                monster.setPower(7 + level / 5);
                monster.setMaxHp(35 + level * 4);
                monster.setWeaponDelay(1500);
                monster.setHp(monster.getMaxHp());
            }
            case SCELETON -> {
                monster = new Sceleton();
                monster.setAgility(8 + level);
                monster.setDexterity(10 + level);
                monster.setDefence(4 + level / 5);
                monster.setPower(5 + level / 5);
                monster.setMaxHp(15 + level * 2);
                monster.setWeaponDelay(700);
                monster.setHp(monster.getMaxHp());
            }
            case VAMPIRE -> {
                monster = new Vampire();
                monster.setAgility(10 + level);
                monster.setDexterity(10 + level);
                monster.setDefence(2 + level / 5);
                monster.setPower(8 + level / 5);
                monster.setMaxHp(25 + level * 4);
                monster.setWeaponDelay(900);
                monster.setHp(monster.getMaxHp());
            }
            case GOBLIN -> {
                monster = new Goblin();
                monster.setAgility(7 + level);
                monster.setDexterity(7 + level);
                monster.setDefence(2 + level / 5);
                monster.setPower(3 + level / 5);
                monster.setMaxHp(20 + level * 2);
                monster.setWeaponDelay(700);
                monster.setHp(monster.getMaxHp());
            }
            case SCORPION -> {
                monster = new Scorpion();
                monster.setAgility(8 + level);
                monster.setDexterity(8 + level);
                monster.setDefence(5 + level / 5);
                monster.setPower(4 + level / 5);
                monster.setMaxHp(25 + level * 3);
                monster.setWeaponDelay(1200);
                monster.setHp(monster.getMaxHp());
            }
        }
        monster.setLevel(level);
        return monster;
    }

}
