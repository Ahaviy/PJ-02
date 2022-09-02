import Areas.Area;

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
}
