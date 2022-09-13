package areas;

import java.util.HashMap;

public abstract class Area {

    public enum Direction {
        NORTH("север"), EAST("восток"), WEST("запад"), SOUTH("юг");
        final String rusName;

        Direction(String rusName) {
            this.rusName = rusName;
        }

    }

    private final String name;

    public String getName() {
        return name;
    }

    private int dangerLevel;
    private final HashMap<Direction, Area> directions;

    public Area(String name, int dangerLevel) {
        this.name = name;
        this.dangerLevel = dangerLevel;
        directions = new HashMap<>();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        return name.equals(((Area) obj).name);
    }

    public abstract String getDescription();

    public abstract String getLocalityName();

    public String getActions(int number) {
        StringBuilder sb = new StringBuilder();
        sb.append(number++).append(". Заняться поисками.\n");
        sb.append(number++).append(". Cделать привал.\n");
        if (directions.containsKey(Direction.NORTH)) sb.append(actionLine(number++, Direction.NORTH));
        if (directions.containsKey(Direction.EAST)) sb.append(actionLine(number++, Direction.EAST));
        if (directions.containsKey(Direction.SOUTH)) sb.append(actionLine(number++, Direction.SOUTH));
        if (directions.containsKey(Direction.WEST)) sb.append(actionLine(number++, Direction.WEST));
        return sb.toString().trim();
    }

    public String makeAction(int value) {
        switch (value) {
            case 1 -> {
                return lookForSomething();
            }
            case 2 -> {
                //TODO привал
                return null;
            }
            default -> {
                return getDestinationAreaName(value - 2);
            }
        }
    }

    protected abstract String lookForSomething();

    private String getDestinationAreaName(int value) {
        if (directions.containsKey(Direction.NORTH)) {
            if (value == 1) {
                return directions.get(Direction.NORTH).getName();
            } else value--;
        }
        if (directions.containsKey(Direction.EAST)) {
            if (value == 1) {
                return directions.get(Direction.EAST).getName();
            } else value--;
        }
        if (directions.containsKey(Direction.SOUTH)) {
            if (value == 1) {
                return directions.get(Direction.SOUTH).getName();
            }
        }
        return directions.get(Direction.WEST).getName();
    }

    private String actionLine(int number, Direction direction) {
        StringBuilder sb = new StringBuilder();
        sb.append(number)
                .append(". Пойти на ")
                .append(direction.rusName)
                .append(", в ")
                .append(directions.get(direction).getLocalityName())
                .append(".\n");
        return sb.toString();

    }

    public HashMap<Direction, Area> getDirections() {
        return directions;
    }

    public String getDirectionsDescription() {
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
