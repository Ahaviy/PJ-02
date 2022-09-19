package areas;

import game.Battle;
import game.Item;
import game.Loot;
import monsters.MonsterGenerator;

public class Wasteland extends Area {
    public Wasteland(String name, int dangerLevel) {
        super(name, dangerLevel);
    }

    @Override
    public int getCostOfStamina() {
        return 3;
    }

    @Override
    public String getDescription() {
        return "Пустынная местность, да же если бы тут не было гоблинских набегов выжить тут невозможно.";
    }

    @Override
    public String getLocalityName() {
        return "пустыш";
    }

    @Override
    protected void generateEnemies() {
        int countEnemy = Math.max(1, dangerLevel / 3);
        for (int i = 0; i < countEnemy; i++) {
            if (Math.random() > 0.2) {
                Battle.getBattle().addEnemy(MonsterGenerator
                        .generateMonster(MonsterGenerator.Type.GOBLIN, Math.max(1, dangerLevel - countEnemy / 2)));
            } else {
                Battle.getBattle().addEnemy(MonsterGenerator
                        .generateMonster(MonsterGenerator.Type.SCORPION, Math.max(1, dangerLevel - countEnemy / 2)));
            }
        }
    }

    @Override
    protected void generateLoot() {
        Loot.getLoot().add(Item.BONE, 1 + ((int) (Math.random() * dangerLevel / 5)));
        if (Math.random() > 0.4) Loot.getLoot().add(Item.OBSIDIAN, 1);
    }
}
