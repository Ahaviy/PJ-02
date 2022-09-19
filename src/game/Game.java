package game;

import areas.*;

import java.util.HashMap;

public class Game {

    HashMap<String, Area> areas;
    Area currentArea;
    MainCharacter character;

    int countMovies;

    public void startGame() {
        createMap(); //создаём карту
        character = MainCharacter.getMainCharacter();
        newGame();
        do {
            printStatus();
            printDescription();//выводим описание местности
            int value = GUtils.getActionValue(printActionsList());//выводим список действий и получем ответ от игрока
            makeAction(value);//совершаем выбранное действие
        } while (!character.isDead());//Если умерли завершаем цикл
        System.out.println("Вы погибли. Game Over");
    }

    private void printStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append("---------------------------------------------------------------------------------\n");
        sb.append("Ход: ").append(countMovies).append(". ");
        sb.append(character.getRusName()).append(" Уровень: ").append(character.getLevel());
        sb.append(" Здоровье: ").append(character.getHp()).append("/").append(character.getMaxHp());
        sb.append(" Выносливость: ").append(character.getStamina()).append(".\n");
        sb.append("---------------------------------------------------------------------------------");
        System.out.println(sb);
    }


    private void makeAction(int value) {
        int areaActionValue = value - character.getCharacterActionsList().split("\n").length + 1;
        if (areaActionValue <= 0) {
            character.makeAction(value);
        } else {
            String resultAction = currentArea.makeAction(areaActionValue);
            if (resultAction == null) return;
            switch (resultAction) {
                case "takeLoot" -> {
                    if (!character.staminaActionCheck(4)) return;
                    printListLoot();
                    addLootToBackpack();
                }
                case "startBattle" -> {
                    if (!character.staminaActionCheck(4)) return;
                    Battle battle = Battle.getBattle();
                    System.out.println("На вас напали враги: " + battle.getEnemyNames());
                    GUtils.pressToContinue();
                    battle.startBattle();
                    if (!character.isDead()) {
                        character.gainExp(battle.getExp());
                        battle.generateReward();
                        printListLoot();
                        addLootToBackpack();
                    }
                    Battle.reset();
                }
                case "rest" -> {
                    if (character.rest(false)) {
                        System.out.println("Вы отдохнули и полностью востановили силы. (потрачена 1 ед провизии)");
                    } else System.out.println("Вы немного востановили силы на подножном корму.");
                }
                default -> {
                    movingTo(resultAction);
                }
            }
            countMovies++;
            GUtils.pressToContinue();
        }
    }


    private void printListLoot() {
        Loot loot = Loot.getLoot();
        if (loot == null || loot.getLootList().isEmpty()) return;
        StringBuilder sb = new StringBuilder();
        sb.append("Вы получили: \n");
        for (Item item : loot.getLootList().keySet()) {
            sb.append(item.rusName).append(" - ").append(loot.getLootList().get(item)).append(" шт.\n");
        }
        System.out.println(sb.toString().trim());
    }

    private void addLootToBackpack() {
        Loot loot = Loot.getLoot();
        if (loot == null || loot.getLootList().isEmpty()) return;
        for (Item item : loot.getLootList().keySet()) {
            character.addToBackpack(item, loot.getLootList().get(item));
        }
        Loot.reset();
    }

    private void movingTo(String destinationArea) {
        if (areas.containsKey(destinationArea)) {
            currentArea = areas.get(destinationArea);
            if (!character.staminaActionCheck(currentArea.getCostOfStamina())) return;
            System.out.println("Вы перемещаетесь в " + areas.get(destinationArea).getLocalityName());
        } else {
            System.out.println("не могу найти локацию " + destinationArea);
        }
    }

    private void printDescription() {
        System.out.println(currentArea.getDescription());
        System.out.println(currentArea.getDirectionsDescription());
    }

    private int printActionsList() {
        StringBuilder sb = new StringBuilder();
        String characterActions = character.getCharacterActionsList();
        int count = characterActions.split("\n").length;
        String areaActions = currentArea.getActions(count);
        count += areaActions.split("\n").length;
        System.out.println(characterActions);
        System.out.println(areaActions);
        return count;
    }

    private void newGame() {
        currentArea = areas.get("Village");//стартовая локация
        character.newCharacter(); //создаём персонажа
        countMovies = 1;
    }

    private void createMap() {
        areas = new HashMap<>();
        //создаём список локаций
        areas.put("Village", new Village("Village", 0));
        areas.put("Tavern", new Tavern("Tavern", 0));
        areas.put("Forge", new Forge("Forge", 0));
        areas.put("Field01", new Field("Field01", 1));
        areas.put("Field02", new Field("Field02", 3));
        areas.put("Field03", new Field("Field03", 4));
        areas.put("Field04", new Field("Field04", 8));
        areas.put("Field05", new Field("Field05", 12));
        areas.put("Field06", new Field("Field06", 8));
        areas.put("Field07", new Field("Field07", 10));
        areas.put("Forest01", new Forest("Forest01", 5));
        areas.put("Forest02", new Forest("Forest02", 8));
        areas.put("Forest03", new Forest("Forest03", 12));
        areas.put("Forest04", new Forest("Forest04", 8));
        areas.put("Forest05", new Forest("Forest05", 12));
        areas.put("DarkForest01", new DarkForest("DarkForest01", 10));
        areas.put("DarkForest02", new DarkForest("DarkForest02", 18));
        areas.put("DarkForest03", new DarkForest("DarkForest03", 17));
        areas.put("DarkForest04", new DarkForest("DarkForest04", 25));
        areas.put("DarkForest05", new DarkForest("DarkForest05", 30));
        areas.put("DarkForest06", new DarkForest("DarkForest06", 18));
        areas.put("Cemetery01", new Cemetery("Cemetery01", 25));
        areas.put("Cemetery02", new Cemetery("Cemetery02", 30));
        areas.put("Cemetery03", new Cemetery("Cemetery03", 35));
        areas.put("Wasteland01", new Wasteland("Wasteland01", 18));
        areas.put("Wasteland02", new Wasteland("Wasteland02", 20));
        areas.put("Wasteland03", new Wasteland("Wasteland03", 25));
        //создаем пути между локациями
        areas.get("Field01").getDirections().put(Area.Direction.NORTH, areas.get("Field02"));
        areas.get("Field01").getDirections().put(Area.Direction.EAST, areas.get("Forest01"));
        areas.get("Field01").getDirections().put(Area.Direction.SOUTH, areas.get("Village"));
        areas.get("Field01").getDirections().put(Area.Direction.WEST, areas.get("Field03"));
        areas.get("Field02").getDirections().put(Area.Direction.NORTH, areas.get("Forest04"));
        areas.get("Field02").getDirections().put(Area.Direction.EAST, areas.get("Forest02"));
        areas.get("Field02").getDirections().put(Area.Direction.SOUTH, areas.get("Field01"));
        areas.get("Field02").getDirections().put(Area.Direction.WEST, areas.get("Field04"));
        areas.get("Field03").getDirections().put(Area.Direction.NORTH, areas.get("Field04"));
        areas.get("Field03").getDirections().put(Area.Direction.EAST, areas.get("Field01"));
        areas.get("Field03").getDirections().put(Area.Direction.WEST, areas.get("Field06"));
        areas.get("Field04").getDirections().put(Area.Direction.NORTH, areas.get("Field05"));
        areas.get("Field04").getDirections().put(Area.Direction.EAST, areas.get("Field02"));
        areas.get("Field04").getDirections().put(Area.Direction.SOUTH, areas.get("Field03"));
        areas.get("Field04").getDirections().put(Area.Direction.WEST, areas.get("Field07"));
        areas.get("Field02").getDirections().put(Area.Direction.NORTH, areas.get("Wasteland02"));
        areas.get("Field02").getDirections().put(Area.Direction.EAST, areas.get("Forest04"));
        areas.get("Field02").getDirections().put(Area.Direction.SOUTH, areas.get("Field04"));
        areas.get("Field02").getDirections().put(Area.Direction.WEST, areas.get("Field07"));
        areas.get("Field06").getDirections().put(Area.Direction.NORTH, areas.get("Field07"));
        areas.get("Field06").getDirections().put(Area.Direction.EAST, areas.get("Field03"));
        areas.get("Field07").getDirections().put(Area.Direction.NORTH, areas.get("Wasteland01"));
        areas.get("Field07").getDirections().put(Area.Direction.EAST, areas.get("Field04"));
        areas.get("Field07").getDirections().put(Area.Direction.SOUTH, areas.get("Field06"));
        areas.get("Forest01").getDirections().put(Area.Direction.NORTH, areas.get("Forest02"));
        areas.get("Forest01").getDirections().put(Area.Direction.EAST, areas.get("DarkForest01"));
        areas.get("Forest01").getDirections().put(Area.Direction.WEST, areas.get("Field01"));
        areas.get("Forest02").getDirections().put(Area.Direction.NORTH, areas.get("Forest03"));
        areas.get("Forest02").getDirections().put(Area.Direction.EAST, areas.get("DarkForest03"));
        areas.get("Forest02").getDirections().put(Area.Direction.SOUTH, areas.get("Forest01"));
        areas.get("Forest02").getDirections().put(Area.Direction.WEST, areas.get("Field02"));
        areas.get("Forest03").getDirections().put(Area.Direction.NORTH, areas.get("DarkForest06"));
        areas.get("Forest03").getDirections().put(Area.Direction.EAST, areas.get("DarkForest06"));
        areas.get("Forest03").getDirections().put(Area.Direction.SOUTH, areas.get("Forest02"));
        areas.get("Forest03").getDirections().put(Area.Direction.WEST, areas.get("Forest04"));
        areas.get("Forest04").getDirections().put(Area.Direction.NORTH, areas.get("Forest05"));
        areas.get("Forest04").getDirections().put(Area.Direction.EAST, areas.get("DForest03"));
        areas.get("Forest04").getDirections().put(Area.Direction.SOUTH, areas.get("Field02"));
        areas.get("Forest04").getDirections().put(Area.Direction.WEST, areas.get("Field05"));
        areas.get("Forest05").getDirections().put(Area.Direction.EAST, areas.get("DarkForest06"));
        areas.get("Forest05").getDirections().put(Area.Direction.SOUTH, areas.get("Forest04"));
        areas.get("Forest05").getDirections().put(Area.Direction.WEST, areas.get("Wasteland02"));
        areas.get("DarkForest01").getDirections().put(Area.Direction.NORTH, areas.get("DarkForest03"));
        areas.get("DarkForest01").getDirections().put(Area.Direction.EAST, areas.get("DarkForest02"));
        areas.get("DarkForest01").getDirections().put(Area.Direction.WEST, areas.get("Forest01"));
        areas.get("DarkForest02").getDirections().put(Area.Direction.NORTH, areas.get("Cemetery01"));
        areas.get("DarkForest02").getDirections().put(Area.Direction.WEST, areas.get("DarkForest01"));
        areas.get("DarkForest03").getDirections().put(Area.Direction.NORTH, areas.get("DarkForest04"));
        areas.get("DarkForest03").getDirections().put(Area.Direction.EAST, areas.get("Cemetery01"));
        areas.get("DarkForest03").getDirections().put(Area.Direction.SOUTH, areas.get("DarkForest01"));
        areas.get("DarkForest03").getDirections().put(Area.Direction.WEST, areas.get("Forest02"));
        areas.get("DarkForest04").getDirections().put(Area.Direction.NORTH, areas.get("DarkForest05"));
        areas.get("DarkForest04").getDirections().put(Area.Direction.EAST, areas.get("Cemetery02"));
        areas.get("DarkForest04").getDirections().put(Area.Direction.SOUTH, areas.get("DarkForest03"));
        areas.get("DarkForest04").getDirections().put(Area.Direction.WEST, areas.get("Forest03"));
        areas.get("DarkForest05").getDirections().put(Area.Direction.EAST, areas.get("Cemetery03"));
        areas.get("DarkForest05").getDirections().put(Area.Direction.SOUTH, areas.get("DarkForest04"));
        areas.get("DarkForest05").getDirections().put(Area.Direction.WEST, areas.get("DarkForest06"));
        areas.get("DarkForest06").getDirections().put(Area.Direction.EAST, areas.get("DarkForest05"));
        areas.get("DarkForest06").getDirections().put(Area.Direction.SOUTH, areas.get("Forest03"));
        areas.get("DarkForest06").getDirections().put(Area.Direction.WEST, areas.get("Forest05"));
        areas.get("Cemetery01").getDirections().put(Area.Direction.NORTH, areas.get("Cemetery02"));
        areas.get("Cemetery01").getDirections().put(Area.Direction.SOUTH, areas.get("DarkForest02"));
        areas.get("Cemetery01").getDirections().put(Area.Direction.WEST, areas.get("DarkForest03"));
        areas.get("Cemetery02").getDirections().put(Area.Direction.NORTH, areas.get("Cemetery03"));
        areas.get("Cemetery02").getDirections().put(Area.Direction.SOUTH, areas.get("Cemetery01"));
        areas.get("Cemetery02").getDirections().put(Area.Direction.WEST, areas.get("DarkForest04"));
        areas.get("Cemetery03").getDirections().put(Area.Direction.SOUTH, areas.get("Cemetery02"));
        areas.get("Cemetery03").getDirections().put(Area.Direction.WEST, areas.get("DarkForest05"));
        areas.get("Wasteland01").getDirections().put(Area.Direction.NORTH, areas.get("Wasteland03"));
        areas.get("Wasteland01").getDirections().put(Area.Direction.EAST, areas.get("Field05"));
        areas.get("Wasteland01").getDirections().put(Area.Direction.SOUTH, areas.get("Field07"));
        areas.get("Wasteland02").getDirections().put(Area.Direction.EAST, areas.get("Forest05"));
        areas.get("Wasteland02").getDirections().put(Area.Direction.SOUTH, areas.get("Field05"));
        areas.get("Wasteland02").getDirections().put(Area.Direction.WEST, areas.get("Wasteland03"));
        areas.get("Wasteland03").getDirections().put(Area.Direction.EAST, areas.get("Wasteland02"));
        areas.get("Wasteland03").getDirections().put(Area.Direction.SOUTH, areas.get("Wasteland01"));
    }


}
