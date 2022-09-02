package Areas;

import java.util.HashMap;

public abstract class Area {

    enum direction {
        NORTH("север"), EAST("восток"), WEST("запад"), SOUTH("юг");
        String rusName;

        direction(String rusName) {
            this.rusName = rusName;
        }

        public String getRusName() {
            return rusName;
        }
    }

    String name;
    private int dangerLevel;
    HashMap<direction, String> directions;

    public Area(String name, int dangerLevel) {
        this.name = name;
        this.dangerLevel = dangerLevel;
        directions = new HashMap<>();
    }

    @Override
    public boolean equals(Object obj) {
        return name.equals(((Area)obj).name);
    }

    public abstract String getDescription();

    public abstract String getLocalityName();


}
