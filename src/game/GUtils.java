package game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GUtils {
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

    public static void pressToContinue() {
        System.out.println("..нажмите Enter для продолжения..");
        (new Scanner(System.in)).nextLine();
    }

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

    public static int generateParam(int base, int randomAdd) {
        return base + (int) (Math.random() * randomAdd);
    }

}
