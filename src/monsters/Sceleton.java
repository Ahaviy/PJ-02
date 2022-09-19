package monsters;

import game.Item;

import java.util.HashMap;

public class Sceleton extends Monster {
    public Sceleton() {
        rusName = "Скелет";
    }

    @Override
    public int getExp() {
        return 30 + level * 5;
    }

    @Override
    public HashMap<Item, Integer> getLoot() {
        HashMap<Item, Integer> lootList = new HashMap<>();
        if (Math.random() > 0.4) lootList.put(Item.GLOWINGSTONE, 1);
        lootList.put(Item.BONE, 5 + level / 3);
        lootList.put(Item.COIN, 6 + level / 4);
        return lootList;
    }

}
