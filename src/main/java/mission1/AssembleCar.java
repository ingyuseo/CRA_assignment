package mission1;

import java.util.Scanner;

public class AssembleCar {
    private static final String CLEAR_SCREEN = "\033[H\033[2J";

    private static final int CarType_MENU = 0;
    private static final int Engine_MENU = 1;
    private static final int BrakeSystem_MENU = 2;
    private static final int SteeringSystem_MENU = 3;
    private static final int Run_Test_MENU = 4;

    private static final String[] carTypeList = {"", "Sedan", "SUV", "Truck"};
    private static final String[] engineList = {"", "GM", "TOYOTA", "WIA", "고장난 엔진"};
    private static final String[] brakeList = {"", "Mando", "Continental", "Bosch"};
    private static final String[] steeringList = {"", "Bosch", "Mobis"};

    private static final int SEDAN = 1, SUV = 2, TRUCK = 3;
    private static final int GM = 1, TOYOTA = 2, WIA = 3, BROKEN_ENGINE = 4;
    private static final int MANDO = 1, CONTINENTAL = 2, BOSCH_B = 3;
    private static final int BOSCH_S = 1, MOBIS = 2;

    public static final int DELAY_MS = 800;
    public static final int BACK_BUTTON = 0;

    public static final String ExitReply = "바이바이";
    public static final String ERROR_INPUT_NOT_NUMBER = "ERROR :: 숫자만 입력 가능";
    private static int[] carComponent = new int[5];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int menu = CarType_MENU;
        int choice;

        while (true) {
            ClearScreen();
            showMenu(menu);

            String input = getInput(scanner);
            if (isExit(input)) {
                System.out.println(ExitReply);
                break;
            }

            try {
                choice = Integer.parseInt(input);
                if (!isValidInputRange(menu, choice)) {
                    delay(DELAY_MS);
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println(ERROR_INPUT_NOT_NUMBER);
                delay(DELAY_MS);
                continue;
            }

            menu = processAndGetNextMenu(menu, choice);
        }
        scanner.close();
    }


    private static void showMenu(int choiceStep) {
        if (choiceStep == CarType_MENU) showCarTypeMenu();
        else if (choiceStep == Engine_MENU) showEngineMenu();
        else if (choiceStep == BrakeSystem_MENU) showBrakeMenu();
        else if (choiceStep == SteeringSystem_MENU) showSteeringMenu();
        else if (choiceStep == Run_Test_MENU) showRunTestMenu();
        System.out.println("===============================");
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

    private static void showCarTypeMenu() {
        System.out.println("        ______________");
        System.out.println("       /|            |");
        System.out.println("  ____/_|_____________|____");
        System.out.println(" |                      O  |");
        System.out.println(" '-(@)----------------(@)--'");
        System.out.println("===============================");
        System.out.println("어떤 차량 타입을 선택할까요?");

        for (int i = 1; i < carTypeList.length; i++) {
            System.out.println(i + " " + carTypeList[i]);
        }
    }

    private static void showEngineMenu() {
        System.out.println("어떤 엔진을 탑재할까요?");
        System.out.println("0. 뒤로가기");

        for (int i = 1; i < engineList.length; i++) {
            System.out.println(i + " " + engineList[i]);
        }
    }

    private static void showBrakeMenu() {
        System.out.println("어떤 제동장치를 선택할까요?");
        System.out.println("0. 뒤로가기");

        for (int i = 1; i < brakeList.length; i++) {
            System.out.println(i + " " + brakeList[i]);
        }
    }

    private static void showSteeringMenu() {
        System.out.println("어떤 조향장치를 선택할까요?");
        System.out.println("0. 뒤로가기");

        for (int i = 1; i < steeringList.length; i++) {
            System.out.println(i + " " + steeringList[i]);
        }
    }

    private static void showRunTestMenu() {
        System.out.println("멋진 차량이 완성되었습니다.");
        System.out.println("어떤 동작을 할까요?");
        System.out.println("0. 처음 화면으로 돌아가기");
        System.out.println("1. RUN");
        System.out.println("2. Test");
    }

    private static boolean isValidInputRange(int menu, int answer) {
        switch (menu) {
            case CarType_MENU:
                if (answer < 1 || answer >= carTypeList.length) {
                    System.out.println("ERROR :: 차량 타입은 1 ~ " + (carTypeList.length-1) + " 범위만 선택 가능");
                    return false;
                }
                break;
            case Engine_MENU:
                if (answer < 0 || answer >= engineList.length) {
                    System.out.println("ERROR :: 엔진은 1 ~ " + (engineList.length-1) + " 범위만 선택 가능");
                    return false;
                }
                break;
            case BrakeSystem_MENU:
                if (answer < 0 || answer >= brakeList.length) {
                    System.out.println("ERROR :: 제동장치는 1 ~ " + (brakeList.length-1) + " 범위만 선택 가능");
                    return false;
                }
                break;
            case SteeringSystem_MENU:
                if (answer < 0 || answer >= steeringList.length) {
                    System.out.println("ERROR :: 조향장치는 1 ~ " + (steeringList.length-1) + " 범위만 선택 가능");
                    return false;
                }
                break;
            case Run_Test_MENU:
                if (answer < 0 || answer > 2) {
                    System.out.println("ERROR :: Run 또는 Test 중 하나를 선택 필요");
                    return false;
                }
                break;
        }
        return true;
    }

    private static int processAndGetNextMenu(int menu, int choice) {
        if (choice == BACK_BUTTON) {
            return getPrevMenu(menu);
        }

        if (menu == Run_Test_MENU) selectRunTest(choice);
        else if (menu == CarType_MENU) selectCarType(choice);
        else if (menu == Engine_MENU) selectEngine(choice);
        else if (menu == BrakeSystem_MENU) selectBrakeSystem(choice);
        else if (menu == SteeringSystem_MENU) selectSteeringSystem(choice);

        delay(DELAY_MS);

        return getNextMenu(menu);
    }

    private static int getNextMenu(int menu) {
        if (menu == Run_Test_MENU) return menu;
        return menu + 1;
    }

    private static int getPrevMenu(int menu) {
        int prevMenu = menu - 1;
        if (menu == Run_Test_MENU) prevMenu = CarType_MENU;
        return prevMenu;
    }

    private static void selectRunTest(int choice) {
        if (choice == 1) {
            runProducedCar();
            delay(2000);
        } else if (choice == 2) {
            System.out.println("Test...");
            delay(1500);
            testProducedCar();
            delay(2000);
        }
    }

    private static void selectCarType(int choice) {
        carComponent[CarType_MENU] = choice;
        System.out.printf("차량 타입으로 %s을 선택하셨습니다.\n", carTypeList[choice]);
    }

    private static void selectEngine(int choice) {
        carComponent[Engine_MENU] = choice;
        System.out.printf("%s 엔진을 선택하셨습니다.\n", engineList[choice]);
    }

    private static void selectBrakeSystem(int choice) {
        carComponent[BrakeSystem_MENU] = choice;
        System.out.printf("%s 제동장치를 선택하셨습니다.\n", brakeList[choice]);
    }

    private static void selectSteeringSystem(int choice) {
        carComponent[SteeringSystem_MENU] = choice;
        System.out.printf("%s 조향장치를 선택하셨습니다.\n", steeringList[choice]);
    }


    private static String getValidComponentResult() {
        if (carComponent[CarType_MENU] == SEDAN && carComponent[BrakeSystem_MENU] == CONTINENTAL)
            return "Sedan에는 Continental제동장치 사용 불가";
        if (carComponent[CarType_MENU] == SUV && carComponent[Engine_MENU] == TOYOTA)
            return "SUV에는 TOYOTA엔진 사용 불가";
        if (carComponent[CarType_MENU] == TRUCK && carComponent[Engine_MENU] == WIA)
            return "Truck에는 WIA엔진 사용 불가";
        if (carComponent[CarType_MENU] == TRUCK && carComponent[BrakeSystem_MENU] == MANDO)
            return "Truck에는 Mando제동장치 사용 불가";
        if (carComponent[BrakeSystem_MENU] == BOSCH_B && carComponent[SteeringSystem_MENU] != BOSCH_S)
            return "Bosch제동장치에는 Bosch조향장치 이외 사용 불가";

        return "PASS";
    }

    private static void testProducedCar() {
        String result = getValidComponentResult();

        if (result.equals("PASS")) System.out.println("자동차 부품 조합 테스트 결과 : PASS");
        else {
            System.out.println("자동차 부품 조합 테스트 결과 : FAIL");
            System.out.println(result);
        }
    }

    private static void runProducedCar() {
        if (!getValidComponentResult().equals("PASS")) {
            System.out.println("자동차가 동작되지 않습니다");
            return;
        }
        if (carComponent[Engine_MENU] == BROKEN_ENGINE) {
            System.out.println("엔진이 고장나있습니다.");
            System.out.println("자동차가 움직이지 않습니다.");
            return;
        }

        System.out.printf("Car Type : %s\n", carTypeList[carComponent[CarType_MENU]]);
        System.out.printf("Engine   : %s\n", engineList[carComponent[Engine_MENU]]);
        System.out.printf("Brake    : %s\n", brakeList[carComponent[BrakeSystem_MENU]]);
        System.out.printf("Steering : %s\n", steeringList[carComponent[SteeringSystem_MENU]]);
        System.out.println("자동차가 동작됩니다.");
    }


    private static void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }
}