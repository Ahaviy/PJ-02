package game;

import areas.Area;
import areas.Field;
import areas.Forest;
import areas.Village;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

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
            int value = getActionValue(printActionsList());//выводим список действий и получем ответ от игрока
            makeAction(value);//совершаем выбранное действие
        } while (!character.isDead());//Если умерли завершаем цикл
        System.out.println("Вы погибли. Игра окончена.");
    }

    private void makeAction(int value) {
        int areaActionValue = value - character.getCharacterActionsList().split("\n").length + 1;
        if (areaActionValue < 0) {
            character.makeAction(value);
        } else {
            String destinationArea = currentArea.makeAction(areaActionValue);
            if (destinationArea != null) movingTo(destinationArea);
        }
    }

    private void movingTo(String destinationArea) {
        if (areas.containsKey(destinationArea)) {
            currentArea = areas.get(destinationArea);
            countMovies++;
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

    private int getActionValue(int count) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNext()) {
                try {
                    int value = scanner.nextInt();
                    if (0 < value && value <= count) {
                        return value;
                    }
                } catch (InputMismatchException e) {
                }
                System.out.println("неправильный ввод ");
            }
        }
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
        areas.put("Field01", new Field("Field01", 1));
        areas.put("Forest01", new Forest("Forest01", 1));
        //создаем пути между локациями
        areas.get("Village").getDirections().put(Area.Direction.NORTH, areas.get("Field01"));
        areas.get("Field01").getDirections().put(Area.Direction.NORTH, areas.get("Forest01"));
        areas.get("Field01").getDirections().put(Area.Direction.SOUTH, areas.get("Village"));
        areas.get("Forest01").getDirections().put(Area.Direction.SOUTH, areas.get("Field01"));
    }
}