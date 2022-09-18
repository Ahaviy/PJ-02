package monsters;

import game.Item;

import java.util.HashMap;

public class Squirrel extends Monster {
    public Squirrel() {
        rusName = "Дикая белка";
    }

    @Override
    public int getWeaponDelay() {
        return 600;
    }

    @Override
    public int getExp() {
        return 3 + level;
    }

    @Override
    public HashMap<Item, Integer> getLoot() {
        HashMap<Item, Integer> lootList = new HashMap<>();
        lootList.put(Item.SQUIRRELTAIL, 1);
        lootList.put(Item.COIN, level / 2 + 1);
        return lootList;
    }
}
