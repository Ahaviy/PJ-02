package areas;

import game.GUtils;
import game.MainCharacter;

public class Tavern extends Area{

    public Tavern(String name, int dangerLevel) {
        super(name, dangerLevel);
    }

    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("Вы находитесь в таверне.\n");
        sb.append("Это маленькая, но уютная таверна, в которой можно отдохнуть и пополнить припасы");
        return sb.toString();
    }

    @Override
    public String getLocalityName() {
        return "таверна";
    }

    @Override
    public String getAreaActionsList(int number) {
        StringBuilder sb = new StringBuilder();
        sb.append(number++).append(". Отдохнуть за 10 монет.\n");
        sb.append(number++).append(". Обновить и восполнить припасы на 3 дня (за 100 монет).\n");
        sb.append(number).append(". Выйти в деревню.");
        return sb.toString();
    }

    @Override
    public String makeAction(int value) {
        switch (value) {
            case 1 -> {
                if (MainCharacter.getMainCharacter().takeCoins(10)){
                    MainCharacter.getMainCharacter().rest(true);
                    System.out.println("Вы отдохнули и полностью востановили силы.");
                }
                GUtils.pressToContinue();
                return null;
            }
            case 2 -> {
                if (MainCharacter.getMainCharacter().takeCoins(100)){
                    MainCharacter.getMainCharacter().restoreProvisions();
                    System.out.println("Вы восполнили запас провизии.");
                }
                GUtils.pressToContinue();
                return null;
            }
            default -> {
                return "Village";
            }
        }
    }

    @Override
    public int getCostOfStamina() {
        return 0;
    }

    @Override
    protected void generateEnemies() {

    }

    @Override
    protected void generateLoot() {

    }
}
