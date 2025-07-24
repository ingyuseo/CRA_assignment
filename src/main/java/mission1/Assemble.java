package mission1;

import java.util.Scanner;

public class Assemble {
    private static final String CLEAR_SCREEN = "\033[H\033[2J";

    private static final int CarType_MENU = 0;
    private static final int Engine_MENU = 1;
    private static final int BrakeSystem_MENU = 2;
    private static final int SteeringSystem_MENU = 3;
    private static final int Run_Test_MENU = 4;

    private static final int SEDAN = 1, SUV = 2, TRUCK = 3;
    private static final int GM = 1, TOYOTA = 2, WIA = 3;
    private static final int MANDO = 1, CONTINENTAL = 2, BOSCH_B = 3;
    private static final int BOSCH_S = 1, MOBIS = 2;
    public static final String ExitReply = "바이바이";
    public static final String ERROR_INPUT_NOT_NUMBER = "ERROR :: 숫자만 입력 가능";
    private static final int LOOP_BREAK = 1, LOOP_CONTINUE = 2, LOOP_NON = 3;
    public static final int DELAY_MS = 800;
    public static final int BACK_BUTTON = 0;

    private static int[] carComponent = new int[5];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int menu = CarType_MENU;
        int choice;

        while (true) {
            ClearScreen();
            showMenu(menu);

            String input = getInput(sc);

            if (isExit(input)) {
                System.out.println(ExitReply);
                break;
            }

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(ERROR_INPUT_NOT_NUMBER);
                delay(DELAY_MS);
                continue;
            }

            if (!isValidInputRange(menu, choice)) {
                delay(DELAY_MS);
                continue;
            }

            menu = processAndGetNextMenu(menu, choice);
        }
        sc.close();
    }

    private static int processAndGetNextMenu(int menu, int choice) {
        if (choice == BACK_BUTTON) {
            if (menu == Run_Test_MENU) {
                return CarType_MENU;
            } else if (menu > CarType_MENU) {
                return menu--;
            }
        }

        if(menu == CarType_MENU){
            selectCarType(choice);
            delay(DELAY_MS);
            return Engine_MENU;
        }
        else if(menu == Engine_MENU) {
            selectEngine(choice);
            delay(DELAY_MS);
            return BrakeSystem_MENU;
        }
        else if(menu == BrakeSystem_MENU) {
            selectBrakeSystem(choice);
            delay(DELAY_MS);
            return SteeringSystem_MENU;
        }
        else if(menu == SteeringSystem_MENU) {
            selectSteeringSystem(choice);
            delay(DELAY_MS);
            return Run_Test_MENU;
        }
        else if(menu == Run_Test_MENU) {
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

        return menu;
    }



    private static void showMenu(int choiceStep) {
        if(choiceStep == CarType_MENU) showCarTypeMenu();
        else if(choiceStep == Engine_MENU) showEngineMenu();
        else if(choiceStep == BrakeSystem_MENU) showBrakeMenu();
        else if(choiceStep == SteeringSystem_MENU) showSteeringMenu();
        else if(choiceStep == Run_Test_MENU) showRunTestMenu();
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
        System.out.println("1. Sedan");
        System.out.println("2. SUV");
        System.out.println("3. Truck");
        System.out.println("===============================");
    }

    private static void showEngineMenu() {
        System.out.println("어떤 엔진을 탑재할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. GM");
        System.out.println("2. TOYOTA");
        System.out.println("3. WIA");
        System.out.println("4. 고장난 엔진");
        System.out.println("===============================");
    }

    private static void showBrakeMenu() {
        System.out.println("어떤 제동장치를 선택할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. MANDO");
        System.out.println("2. CONTINENTAL");
        System.out.println("3. BOSCH");
        System.out.println("===============================");
    }

    private static void showSteeringMenu() {
        System.out.println("어떤 조향장치를 선택할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. BOSCH");
        System.out.println("2. MOBIS");
        System.out.println("===============================");
    }

    private static void showRunTestMenu() {
        System.out.println("멋진 차량이 완성되었습니다.");
        System.out.println("어떤 동작을 할까요?");
        System.out.println("0. 처음 화면으로 돌아가기");
        System.out.println("1. RUN");
        System.out.println("2. Test");
        System.out.println("===============================");
    }

    private static boolean isValidInputRange(int menu, int answer) {
        switch (menu) {
            case CarType_MENU:
                if (answer < 1 || answer > 3) {
                    System.out.println("ERROR :: 차량 타입은 1 ~ 3 범위만 선택 가능");
                    return false;
                }
                break;
            case Engine_MENU:
                if (answer < 0 || answer > 4) {
                    System.out.println("ERROR :: 엔진은 1 ~ 4 범위만 선택 가능");
                    return false;
                }
                break;
            case BrakeSystem_MENU:
                if (answer < 0 || answer > 3) {
                    System.out.println("ERROR :: 제동장치는 1 ~ 3 범위만 선택 가능");
                    return false;
                }
                break;
            case SteeringSystem_MENU:
                if (answer < 0 || answer > 2) {
                    System.out.println("ERROR :: 조향장치는 1 ~ 2 범위만 선택 가능");
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

    private static void selectCarType(int a) {
        carComponent[CarType_MENU] = a;
        System.out.printf("차량 타입으로 %s을 선택하셨습니다.\n", a == 1 ? "Sedan" : a == 2 ? "SUV" : "Truck");
    }
    private static void selectEngine(int a) {
        carComponent[Engine_MENU] = a;
        String name = a == 1 ? "GM" : a == 2 ? "TOYOTA" : a == 3 ? "WIA" : "고장난 엔진";
        System.out.printf("%s 엔진을 선택하셨습니다.\n", name);
    }
    private static void selectBrakeSystem(int a) {
        carComponent[BrakeSystem_MENU] = a;
        String name = a == 1 ? "MANDO" : a == 2 ? "CONTINENTAL" : "BOSCH";
        System.out.printf("%s 제동장치를 선택하셨습니다.\n", name);
    }
    private static void selectSteeringSystem(int a) {
        carComponent[SteeringSystem_MENU] = a;
        String name = a == 1 ? "BOSCH" : "MOBIS";
        System.out.printf("%s 조향장치를 선택하셨습니다.\n", name);
    }


    private static boolean isValidCheck() {
        if (carComponent[CarType_MENU] == SEDAN && carComponent[BrakeSystem_MENU] == CONTINENTAL) return false;
        if (carComponent[CarType_MENU] == SUV   && carComponent[Engine_MENU] == TOYOTA)       return false;
        if (carComponent[CarType_MENU] == TRUCK && carComponent[Engine_MENU] == WIA)          return false;
        if (carComponent[CarType_MENU] == TRUCK && carComponent[BrakeSystem_MENU] == MANDO)  return false;
        if (carComponent[BrakeSystem_MENU] == BOSCH_B && carComponent[SteeringSystem_MENU] != BOSCH_S) return false;
        return true;
    }

    private static void runProducedCar() {
        if (!isValidCheck()) {
            System.out.println("자동차가 동작되지 않습니다");
            return;
        }
        if (carComponent[Engine_MENU] == 4) {
            System.out.println("엔진이 고장나있습니다.");
            System.out.println("자동차가 움직이지 않습니다.");
            return;
        }

        String[] carNames = {"", "Sedan", "SUV", "Truck"};
        String[] engNames = {"", "GM", "TOYOTA", "WIA"};
        System.out.printf("Car Type : %s\n", carNames[carComponent[CarType_MENU]]);
        System.out.printf("Engine   : %s\n", engNames[carComponent[Engine_MENU]]);
        System.out.printf("Brake    : %s\n",
                carComponent[BrakeSystem_MENU]==1? "Mando":
                        carComponent[BrakeSystem_MENU]==2? "Continental":"Bosch");
        System.out.printf("Steering : %s\n",
                carComponent[SteeringSystem_MENU]==1? "Bosch":"Mobis");
        System.out.println("자동차가 동작됩니다.");
    }

    private static void testProducedCar() {
        if (carComponent[CarType_MENU] == SEDAN && carComponent[BrakeSystem_MENU] == CONTINENTAL) {
            fail("Sedan에는 Continental제동장치 사용 불가");
        } else if (carComponent[CarType_MENU] == SUV && carComponent[Engine_MENU] == TOYOTA) {
            fail("SUV에는 TOYOTA엔진 사용 불가");
        } else if (carComponent[CarType_MENU] == TRUCK && carComponent[Engine_MENU] == WIA) {
            fail("Truck에는 WIA엔진 사용 불가");
        } else if (carComponent[CarType_MENU] == TRUCK && carComponent[BrakeSystem_MENU] == MANDO) {
            fail("Truck에는 Mando제동장치 사용 불가");
        } else if (carComponent[BrakeSystem_MENU] == BOSCH_B && carComponent[SteeringSystem_MENU] != BOSCH_S) {
            fail("Bosch제동장치에는 Bosch조향장치 이외 사용 불가");
        } else {
            System.out.println("자동차 부품 조합 테스트 결과 : PASS");
        }
    }

    private static void fail(String msg) {
        System.out.println("자동차 부품 조합 테스트 결과 : FAIL");
        System.out.println(msg);
    }


    private static void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }
}