package monsters;

import game.Item;

import java.util.HashMap;

public abstract class Monster extends Persona {
    protected int level;
    public Monster() {
        rusName = "монстер";
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public abstract int getExp();

    public abstract HashMap<Item, Integer> getLoot();
}
