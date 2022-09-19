package monsters;

import game.Item;

import java.util.HashMap;

public class Ent extends Monster{

    public Ent() {
        rusName = "Энт";
    }

    @Override
    public int getExp() {
        return 25 + level*4;
    }

    @Override
    public HashMap<Item, Integer> getLoot() {
        HashMap<Item, Integer> lootList = new HashMap<>();
        if (Math.random() > 0.3) lootList.put(Item.LIVINGWOOD, 1);
        lootList.put(Item.PIECEOFWOOD, 4+ level/2);
        lootList.put(Item.COIN, 15 + level/4);
        return lootList;
    }
}
