package monsters;

import game.Item;

import java.util.HashMap;

public class Wolf extends Monster {
    public Wolf() {
        rusName = "Волк";
    }

    @Override
    public int getWeaponDelay() {
        return 800;
    }

    @Override
    public int getExp() {
        return 12 + level * 2;
    }

    @Override
    public HashMap<Item, Integer> getLoot() {
        HashMap<Item, Integer> lootList = new HashMap<>();
        lootList.put(Item.WOLFFANG, 1);
        lootList.put(Item.COIN, level + 3);
        return lootList;
    }
}
