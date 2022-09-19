package monsters;

import game.Item;

import java.util.HashMap;

public class Squirrel extends Monster {
    public Squirrel() {
        rusName = "Дикая белка";
    }

    @Override
    public int getExp() {
        return 3 + level;
    }

    @Override
    public HashMap<Item, Integer> getLoot() {
        HashMap<Item, Integer> lootList = new HashMap<>();
        if (Math.random() > 0.5) lootList.put(Item.SQUIRRELTAIL, 1);
        lootList.put(Item.NUT, level / 3 + 1);
        return lootList;
    }
}
