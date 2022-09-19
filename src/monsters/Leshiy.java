package monsters;

import game.Item;

import java.util.HashMap;

public class Leshiy extends Monster{

    public Leshiy() {
        rusName = "Леший";
    }

    @Override
    public int getExp() {
        return 17 + level*3;
    }

    @Override
    public HashMap<Item, Integer> getLoot() {
        HashMap<Item, Integer> lootList = new HashMap<>();
        if (Math.random() > 0.3) lootList.put(Item.LESHIYHAIR, 1);
        lootList.put(Item.FUR, 1+ level/3);
        lootList.put(Item.COIN, 2 + level/6);
        return lootList;
    }
}
