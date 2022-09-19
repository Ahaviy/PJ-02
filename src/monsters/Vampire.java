package monsters;

import game.Item;

import java.util.HashMap;

public class Vampire extends Monster{
    public Vampire() {
        rusName = "Вампир";
    }

    @Override
    public int getExp() {
        return 40 + level*7;
    }

    @Override
    public HashMap<Item, Integer> getLoot() {
        HashMap<Item, Integer> lootList = new HashMap<>();
        lootList.put(Item.BATWING, 1);
        lootList.put(Item.COIN, 20 + level / 2);
        return lootList;
    }
}
