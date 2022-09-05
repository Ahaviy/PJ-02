import Areas.Area;
import Areas.Field;
import Areas.Forest;
import Areas.Vilage;

import java.util.HashMap;
import java.util.Scanner;

public class Game {

    MainCharacter character;

    HashMap<String, Area> areas;
    String currentArea;

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }

    public void startGame(){

        character = new MainCharacter(getCharacterName());


        System.out.println(character.getStatus());
    }

    private String getCharacterName() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Введите ваше имя: ");
            String name = scanner.nextLine();
            System.out.println(name + "- это ваше имя? (y/n)");
            if (scanner.next().toLowerCase().charAt(0) == 'y') {
                return name;

            }
            scanner.nextLine();
        }
    }

    private void createMap() {
        areas = new HashMap<>();
        //создаём список локаций
        areas.put("Vilage", new Vilage("Vilage", 0));
        areas.put("Field01", new Field("Field01", 1));
        areas.put("Forest01", new Forest("Forest01", 1));
        //создаем пути между локациями
        areas.get("Vilage").getDirections().put(Area.direction.NORTH, "Field01");
        areas.get("Field01").getDirections().put(Area.direction.NORTH, "Forest01");
        areas.get("Field01").getDirections().put(Area.direction.SOUTH, "Vilage");
        areas.get("Forest01").getDirections().put(Area.direction.SOUTH, "Field01");

    }
}
