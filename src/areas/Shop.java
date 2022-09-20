package areas;

import game.GUtils;
import game.Item;
import game.MainCharacter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Shop extends Area {

    HashMap<Item, Integer> priceList;

    public Shop(String name, int dangerLevel) {
        super(name, dangerLevel);
        priceList = new HashMap<>();
        priceList.put(Item.NUT, 1);
        priceList.put(Item.SINGINGGRASS, 20);
        priceList.put(Item.FUR, 1);
        priceList.put(Item.PIECEOFWOOD, 1);
        priceList.put(Item.GLOWINGSTONE, 15);
        priceList.put(Item.BONE, 2);
        priceList.put(Item.DRAGONBONE, 30);
        priceList.put(Item.OBSIDIAN, 40);
        priceList.put(Item.OLDSTONE, 30);
        priceList.put(Item.SQUIRRELTAIL, 5);
        priceList.put(Item.WOLFFANG, 5);
        priceList.put(Item.LESHIYHAIR, 10);
        priceList.put(Item.LIVINGWOOD, 15);
        priceList.put(Item.BATWING, 50);
        priceList.put(Item.SCORPIONTAIL, 35);
    }

    @Override
    public String getDescription() {
        return "Вы находитесь в лавке алхимика. который с удовольствием скупит у вас ингридиенты для своих зелей";
    }

    @Override
    public String getLocalityName() {
        return "магазин";
    }

    @Override
    public int getCostOfStamina() {
        return 0;
    }

    @Override
    public String getAreaActionsList(int number) {
        StringBuilder sb = new StringBuilder();
        sb.append(number++).append(". Продать лут.\n");
        sb.append(number).append(". Выйти в деревню.");
        return sb.toString();
    }

    @Override
    public String makeAction(int value) {
        switch (value) {
            case 1 -> {
                sellLoot();
                GUtils.pressToContinue();
                return null;
            }
            default -> {
                return "Village";
            }
        }
    }

    private void sellLoot() {
        HashMap<Item, Integer> backpack = MainCharacter.getMainCharacter().getBackpack();
        if (backpack.size() == 1) {
            System.out.println("Вам нечего продавать.");
            return;
        }
        int sum = 0;
        StringBuilder sb = new StringBuilder();
        for (Item loot : backpack.keySet()) {
            if (loot != Item.COIN) {
                int count = backpack.get(loot);
                int price = priceList.get(loot);
                sb.append("Лавочник покупает у вас ").append(count).append(" ").append(loot.getRusName()).append(" по ")
                        .append(price).append(" монеты.\n");
                sum += count * price;
            }
        }
        if (sum != 0) {
            sb.append("С продажи вы получаете ").append(sum).append(" монет");
            System.out.println(sb);
            backpack.replace(Item.COIN, backpack.get(Item.COIN) + sum);
            MainCharacter.getMainCharacter().clearBackpack();
        }
    }


    @Override
    protected void generateEnemies() {

    }

    @Override
    protected void generateLoot() {

    }
}
