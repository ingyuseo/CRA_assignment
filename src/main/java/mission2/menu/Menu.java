package mission2.menu;

import mission2.Car;

public interface Menu {
    public void showMenu();
    public boolean isValidInputRange(int answer);
    public Menu applyAndgetNextMenu(Car car, int choice) throws InterruptedException;
}











