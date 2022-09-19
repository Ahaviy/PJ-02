package monsters;

import game.Item;

import java.util.HashMap;

public class Scorpion extends Monster {
    public Scorpion() {
        rusName = "Гигантский скорпион";
    }

    @Override
    public int getExp() {
        return 30 + level * 5;
    }

    @Override
    public HashMap<Item, Integer> getLoot() {
        HashMap<Item, Integer> lootList = new HashMap<>();
        lootList.put(Item.SCORPIONTAIL, 1);
        return lootList;
    }
}
