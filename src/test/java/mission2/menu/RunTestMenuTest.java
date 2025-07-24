package mission2.menu;

import mission2.Car;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RunTestMenuTest {
    @Test
    void 제동장치에_BOSCH제품이면_조향장치도_BOSCH제품_써야한다(){
        Car car = new Car();
        car.setBrake("Bosch");
        car.setSteering("WIA");

        RunTestMenu sut = new RunTestMenu();
        sut.getValidComponentResult(car);
    }


}