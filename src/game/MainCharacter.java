package game;

import monsters.Persona;

import java.util.Scanner;

public class MainCharacter extends Persona {
    private static MainCharacter character;
    String name;

    public static MainCharacter getMainCharacter() {
        if (character == null) {
            character = new MainCharacter();
        }
        return character;
    }

    private MainCharacter() {
    }

    public void newCharacter() {
        //TODO незабыть заменить обратно
        name = "Ivan";
        //name = getCharacterName();
    }

    public boolean isDead(){
        //TODO переделать
        return false;
    }



    public String getCharacterActionsList() {
        StringBuilder sb = new StringBuilder();
        sb.append("Выберете действие:\n");
        sb.append("1. Посмотреть статус.\n");
        sb.append("2. Посмотреть инвентарь.\n");
        sb.append("3. Снять/Одеть\n");
        return sb.toString().trim();
    }

    public void makeAction(int value) {
        switch (value) {
            case 1 -> MainCharacter.getMainCharacter().printStatus();
            case 2 -> MainCharacter.getMainCharacter().printInventory();
            case 3 -> MainCharacter.getMainCharacter().changeEquipment();
        }
    }

    private String printStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append("Вас зовут ").append(name).append("\n");
        //TODO реализовать статус
        return sb.toString();
    }

    private void printInventory() {
        System.out.println("пока не реализовано");
        //TODO реализовать инвентарь
    }

    private void changeEquipment() {
        System.out.println("пока не реализовано");
        //TODO реализовать смену оснащения
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
