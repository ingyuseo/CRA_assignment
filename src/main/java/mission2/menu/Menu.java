package mission2.menu;

import mission2.Car;

public interface Menu {
    public void showMenu();
    public boolean isValidInputRange(int answer);
    public void applyChoice(Car car, int choice);
}











