package game;

import java.util.HashMap;

public class Loot {
    static private Loot instance;

    private HashMap<Item, Integer> lootList;

    public static void newLoot(){
        instance = new Loot();
    }

    public static void nullLoot() {
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

    private Loot() {
        lootList = new HashMap<>();
    }

}
