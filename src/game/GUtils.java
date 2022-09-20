package game;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Вспомогательный класс для повторяемых действий связанных с вводом выводом в консоль
 */
public class GUtils {

    /**
     * Контролирует ввод пользователем числа для выбора действия
     * count указывает диапозон возможных значений (от 1 до count включиельно)
     */
    public static int getActionValue(int count) {
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

    /**
     * приостанавливает игру до нажатия enter
     */
    public static void pressToContinue() {
        System.out.println("..нажмите Enter для продолжения..");
        (new Scanner(System.in)).nextLine();
    }

    /**
     * Контралирует ввод имени персонажа
     * */
    public static String getCharacterName() {
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


    /**
    * Генерирует число по заданным параметрам
    * */
    public static int generateParam(int base, int randomAdd) {
        return base + (int) (Math.random() * randomAdd);
    }

}
