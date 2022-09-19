package areas;

import game.Battle;
import game.Item;
import game.Loot;
import monsters.MonsterGenerator;

public class Cemetery extends Area {
    public Cemetery(String name, int dangerLevel) {
        super(name, dangerLevel);
    }

    @Override
    public int getCostOfStamina() {
        return 3;
    }

    @Override
    public String getDescription() {
        return "Не известно что тут происходило: битва или массовая казнь. Но множенство трпов превратили эту местность в царство нечести";
    }

    @Override
    public String getLocalityName() {
        return "кладбище";
    }

    @Override
    protected void generateEnemies() {
        int countEnemy = Math.max(1, dangerLevel / 7);
        for (int i = 0; i < countEnemy; i++) {
            if (Math.random() > 0.2) {
                Battle.getBattle().addEnemy(MonsterGenerator
                        .generateMonster(MonsterGenerator.Type.SCELETON, Math.max(1, dangerLevel - countEnemy * 3 / 2)));
            } else {
                Battle.getBattle().addEnemy(MonsterGenerator
                        .generateMonster(MonsterGenerator.Type.VAMPIRE, Math.max(1, dangerLevel - countEnemy * 3 / 2)));
            }
        }
    }

    @Override
    protected void generateLoot() {
        Loot.getLoot().add(Item.BONE, 1 + ((int) (Math.random() * dangerLevel)));
        if (Math.random() > 0.7) Loot.getLoot().add(Item.OLDSTONE, 1);
    }
}
