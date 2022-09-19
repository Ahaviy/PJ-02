package areas;

import game.Battle;
import game.Item;
import game.Loot;
import monsters.MonsterGenerator;

public class Forest extends Area{
    public Forest(String name, int dangerLevel) {
        super(name, dangerLevel);
    }

    @Override
    public String getDescription() {
        return "Лес, окружающий вас, полон еды, а большое количество мелких животных говорит о том что хищники здесь"
                + " не частые гости";
    }

    @Override
    public String getLocalityName() {
        return "лес";
    }

    @Override
    public int getCostOfStamina() {
        return 2;
    }

    @Override
    protected void generateEnemies() {
        int countEnemy = Math.max(1, dangerLevel / 5);
        for (int i = 0; i < countEnemy; i++) {
            if (Math.random() > 0.3) {
                Battle.getBattle().addEnemy(MonsterGenerator
                        .generateMonster(MonsterGenerator.Type.WOLF, Math.max(1, dangerLevel - countEnemy * 3 / 2)));
            } else {
                Battle.getBattle().addEnemy(MonsterGenerator
                        .generateMonster(MonsterGenerator.Type.LESHIY, Math.max(1, dangerLevel - countEnemy * 3 / 2)));
            }
        }
    }

    @Override
    protected void generateLoot() {
        Loot.getLoot().add(Item.PIECEOFWOOD, 2 + ((int) (Math.random() * dangerLevel)));
        if (Math.random() > 0.7) Loot.getLoot().add(Item.GLOWINGSTONE, 1);
    }
}
