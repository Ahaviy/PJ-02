package Areas;

import java.awt.*;
import java.util.HashMap;

public abstract class Area {

    public enum Direction {
        NORTH("север"), EAST("восток"), WEST("запад"), SOUTH("юг");
        String rusName;

        Direction(String rusName) {
            this.rusName = rusName;
        }

        public String getRusName() {
            return rusName;
        }
    }

    String name;
    private int dangerLevel;
    private HashMap<Direction, Area> directions;

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

    public abstract String getActions();

    public HashMap<Direction, Area> getDirections() {
        return directions;
    }

    protected String getDirectionsDescription(){
        if (directions.keySet().isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (Direction direction : directions.keySet()) {
            sb.append("На ")
                    .append(direction.rusName)
                    .append("е находится ")
                    .append(directions.get(direction).getLocalityName())
                    .append(".\n");
        }
        return sb.toString();
    }
}
