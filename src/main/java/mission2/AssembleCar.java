package mission2;

import mission2.menu.CarTypeMenu;
import mission2.menu.Menu;

import java.util.Scanner;

public class AssembleCar {
    private static final String CLEAR_SCREEN = "\033[H\033[2J";

    public static final int DELAY_MS = 800;

    public static final String EXIT_REPLY = "바이바이";
    public static final String ERROR_INPUT_NOT_NUMBER = "ERROR :: 숫자만 입력 가능";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu currentMenu = new CarTypeMenu();
        Car car = new Car();

        int choice;

        while (true) {
            ClearScreen();
            currentMenu.showMenu();

            String input = getInput(scanner);
            if (isExit(input)) {
                System.out.println(EXIT_REPLY);
                break;
            }

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(ERROR_INPUT_NOT_NUMBER);
                delay(DELAY_MS);
                continue;
            }

            if (!currentMenu.isValidInputRange(choice)) {
                delay(DELAY_MS);
                continue;
            }

            currentMenu.applyChoice(car, choice);
        }
        scanner.close();
    }

    private static String getInput(Scanner sc) {
        System.out.print("INPUT > ");
        return sc.nextLine().trim();
    }

    private static boolean isExit(String buf) {
        return buf.equalsIgnoreCase("exit");
    }

    private static void ClearScreen() {
        System.out.print(CLEAR_SCREEN);
        System.out.flush();
    }


    public static void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }
}