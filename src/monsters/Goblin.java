package monsters;

import game.Item;

import java.util.HashMap;

public class Goblin extends Monster{
    public Goblin() {
        rusName = "Гоблин";
    }

    @Override
    public int getExp() {
        return 20 + level*4;
    }

    @Override
    public HashMap<Item, Integer> getLoot() {
        HashMap<Item, Integer> lootList = new HashMap<>();
        if (Math.random() > 0.2) lootList.put(Item.DRAGONBONE, 1);
        if (Math.random() > 0.6) lootList.put(Item.OBSIDIAN, 1);
        lootList.put(Item.COIN, 20 + level/3);
        return lootList;
    }

}
