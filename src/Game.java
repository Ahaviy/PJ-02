import Areas.Area;
import Areas.Field;
import Areas.Forest;
import Areas.Village;
import java.util.HashMap;
import java.util.Scanner;

public class Game {

    private static Game game;
    MainCharacter character;
    HashMap<String, Area> areas;
    Area currentArea;

    private Game() {}

    public static Game getGame(){
        if (game == null) {
            game = new Game();
        }
        return game;
    }

    public static void main(String[] args) {
        Game currentGame = Game.getGame();
        currentGame.startGame();
    }

    public void startGame(){
        createMap(); //создаём карту
        character = MainCharacter.getMainCharacter(); //создаём персонажа
        character.setName("Ivan");
        //character.setName(getCharacterName());
        System.out.println(currentArea.getDescription());

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
        areas.put("Village", new Village("Village", 0));
        areas.put("Field01", new Field("Field01", 1));
        areas.put("Forest01", new Forest("Forest01", 1));
        //создаем пути между локациями
        areas.get("Village").getDirections().put(Area.Direction.NORTH, areas.get("Field01"));
        areas.get("Field01").getDirections().put(Area.Direction.NORTH, areas.get("Forest01"));
        areas.get("Field01").getDirections().put(Area.Direction.SOUTH, areas.get("Village"));
        areas.get("Forest01").getDirections().put(Area.Direction.SOUTH, areas.get("Field01"));
        //стартовая локация
        currentArea = areas.get("Village");

    }
}
