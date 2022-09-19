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
            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("Ход: " + countMovies);
            System.out.println("---------------------------------------------------------------------------------");
            printDescription();//выводим описание местности
            int value = GUtils.getActionValue(printActionsList());//выводим список действий и получем ответ от игрока
            makeAction(value);//совершаем выбранное действие
        } while (!character.isDead());//Если умерли завершаем цикл
        System.out.println("Вы погибли. Game Over");
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
                case "rest" ->{
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
        areas.put("Field01", new Field("Field01", 1));
        areas.put("Forest01", new Forest("Forest01", 1));

        //создаем пути между локациями
        areas.get("Village").getDirections().put(Area.Direction.NORTH, areas.get("Field01"));
        areas.get("Field01").getDirections().put(Area.Direction.NORTH, areas.get("Forest01"));
        areas.get("Field01").getDirections().put(Area.Direction.SOUTH, areas.get("Village"));
        areas.get("Forest01").getDirections().put(Area.Direction.SOUTH, areas.get("Field01"));
    }


}
