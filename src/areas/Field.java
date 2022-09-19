package areas;

import game.Battle;
import game.Item;
import game.Loot;
import monsters.MonsterGenerator;

public class Field extends Area {

    public Field(String name, int dangerLevel) {
        super(name, dangerLevel);
    }

    @Override
    public String getDescription() {
        return "Поля окружающие вас кажутся спокойными и безметежными. Наврятли тут можно найти ценные ресурсы и"
                + " опасных врагов, но можно попытаться";
    }

    @Override
    public String getLocalityName() {
        return "поле";
    }

    @Override
    public int getCostOfStamina() {
        return 1;
    }

    @Override
    protected void generateEnemies() {
        int countEnemy = Math.max(1, dangerLevel / 5);
        for (int i = 0; i < countEnemy; i++) {
            if (Math.random() > 0.3) {
                Battle.getBattle().addEnemy(MonsterGenerator
                        .generateMonster(MonsterGenerator.Type.SQUIRREL, Math.max(1, dangerLevel - countEnemy * 3 / 2)));
            } else {
                Battle.getBattle().addEnemy(MonsterGenerator
                        .generateMonster(MonsterGenerator.Type.WOLF, Math.max(1, dangerLevel - countEnemy * 3 / 2)));
            }
        }
    }

    @Override
    protected void generateLoot() {
        Loot.getLoot().add(Item.NUT, 1 + ((int) (Math.random() * dangerLevel)));
        if (Math.random() > 0.7) Loot.getLoot().add(Item.SINGINGGRASS, 1);
    }
}
