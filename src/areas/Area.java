package areas;

import game.Battle;
import game.Loot;

import java.util.HashMap;

public abstract class Area {

    /**
     * Перечень сторон света для создания таблицы путей из локации
     */
    public enum Direction {
        NORTH("север"), EAST("восток"), WEST("запад"), SOUTH("юг");

        final String rusName;

        Direction(String rusName) {
            this.rusName = rusName;
        }
    }

    private final String name;

    /**
     * Уникальное имя локации
     */
    public String getName() {
        return name;
    }

    protected int dangerLevel;
    private final HashMap<Direction, Area> directions;

    /**
     * Конструктор принимает значения
     * name - уникальное имя, которое является индитификатором локации
     * dangerLevel - уровень опасности, определяет сложность боев и иколичество лута
     */
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

    /**
     * Возвращает необходимое количество выносливости необходимое для перехода на данную локацию
     */
    public abstract int getCostOfStamina();

    /**
     * Возвращает описание биома
     */
    public abstract String getDescription();

    /**
     * Возвращает название биома
     */
    public abstract String getLocalityName();

    public HashMap<Direction, Area> getDirections() {
        return directions;
    }

    /**
     * Возвращаетт список возможных действий на локации
     * number - первый номер в списке
     */
    public String getAreaActionsList(int number) {
        StringBuilder sb = new StringBuilder();
        sb.append(number++).append(". Заняться поисками.\n");
        sb.append(number++).append(". Cделать привал.\n");
        if (directions.containsKey(Direction.NORTH)) sb.append(actionLine(number++, Direction.NORTH));
        if (directions.containsKey(Direction.EAST)) sb.append(actionLine(number++, Direction.EAST));
        if (directions.containsKey(Direction.SOUTH)) sb.append(actionLine(number++, Direction.SOUTH));
        if (directions.containsKey(Direction.WEST)) sb.append(actionLine(number++, Direction.WEST));
        return sb.toString().trim();
    }

    /**
     * Выполняет выбронное пользователем действие на локации
     */
    public String makeAction(int value) {
        switch (value) {
            case 1 -> {
                return lookForSomething();
            }
            case 2 -> {
                return "rest";
            }
            default -> {
                return getDestinationAreaName(value - 2);
            }
        }
    }

    /**
     * Дэфолтная реализация поиска на локации (50% лут на 50% бой)
     * В классе биома можно переопределить что бы изменить шансы или добавить особое действие
     */
    protected String lookForSomething() {
        int value = dangerLevel + ((int) (Math.random() * 100)) % 25;
        if (value % 2 == 0) {
            Loot.newLoot();
            generateLoot();
            return "takeLoot";
        } else {
            Battle.newBattle();
            generateEnemies();
            return "startBattle";
        }

    }

    /**
     * Добавляет в битву врагов (в каждом биоме своя реализация)
     */
    protected abstract void generateEnemies();

    /**
     * Добавляет лут найденый на локации (в каждом биоме своя реализация)
     */
    protected abstract void generateLoot();

    /**
     * Возвращает название локации в которую будет переход
     */
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

    /**
     * Формарует строку с описанием куда можно пойти из локации
     */
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

    /**
     * Возвращает краткое описание окружающих локацию локаций
     */
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
