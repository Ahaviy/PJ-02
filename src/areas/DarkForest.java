package areas;

import game.Battle;
import game.Item;
import game.Loot;
import monsters.MonsterGenerator;

public class DarkForest extends Area{

    public DarkForest(String name, int dangerLevel) {
        super(name, dangerLevel);
    }

    @Override
    public int getCostOfStamina() {
        return 2;
    }

    @Override
    public String getDescription() {
        return "От леса окружающего вас стынет кровь, вам кажется что тут даже деревья живые";
    }

    @Override
    public String getLocalityName() {
        return "темный лес";
    }

    @Override
    protected void generateEnemies() {
        int countEnemy = Math.max(1, dangerLevel / 8);
        for (int i = 0; i < countEnemy; i++) {
            if (Math.random() > 0.3) {
                Battle.getBattle().addEnemy(MonsterGenerator
                        .generateMonster(MonsterGenerator.Type.LESHIY, Math.max(1, dangerLevel - countEnemy * 3 / 2)));
            } else {
                Battle.getBattle().addEnemy(MonsterGenerator
                        .generateMonster(MonsterGenerator.Type.ENT, Math.max(1, dangerLevel - countEnemy * 3 / 2)));
            }
        }
    }

    @Override
    protected void generateLoot() {
        Loot.getLoot().add(Item.PIECEOFWOOD, 5 + 2 * ((int) (Math.random() * dangerLevel)));
        if (Math.random() > 0.3) Loot.getLoot().add(Item.GLOWINGSTONE, 1);
    }
}
