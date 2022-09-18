package game;

import java.util.HashMap;

public class Loot {
    static private Loot instance;

    private HashMap<Item, Integer> lootList;

    public static void newLoot(){
        instance = new Loot();
    }

    public static void reset() {
        instance = null;
    }

    public static Loot getLoot(){
        return instance;
    }

    public HashMap<Item, Integer> getLootList() {
        return lootList;
    }

    public void add(Item item, int count){
        if (lootList.containsKey(item)){
            int newCount = count + lootList.get(item);
            lootList.replace(item, newCount);
        } else {
            lootList.put(item, count);
        }
    }

    public void add(HashMap<Item, Integer> gainLootList){
        for (Item item : gainLootList.keySet()) {
            add(item, gainLootList.get(item));
        }


    }

    private Loot() {
        lootList = new HashMap<>();
    }

}
