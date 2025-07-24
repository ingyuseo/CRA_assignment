package mission2;

import mission2.Car;
import mission2.menu.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Spy;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AssembleCarTest {
    //sut
    CarTypeMenu carTypeMenu = mock(CarTypeMenu.class);
    EngineMenu engineMenu = mock(EngineMenu.class);
    BrakeSystemMenu brakeSystemMenu = mock(BrakeSystemMenu.class);
    SteeringSystemMenu steeringSystemMenu = mock(SteeringSystemMenu.class);
    RunTestMenu runTestMenu = mock(RunTestMenu.class);

    @Test
    @DisplayName("Exit을_누르면_바로_나간다")
    void Exit을_누르면_바로_나간다(){
        String input = "exit";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        AssembleCar.main(new String[0]);

        verify(carTypeMenu, times(0)).isValidInputRange(anyInt());
    }

    @Test
    @DisplayName("정상입력시_마지막_RunTest진입확인")
    void 정상입력시_마지막_RunTest진입확인(){
        String input = "1\n1\n1\n1\n1\nexit";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        AssembleCar.main(new String[0]);

        verify(runTestMenu, times(1)).showMenu();
    }

    @Test
    @DisplayName("BrakeSystemMenu_잘못된범위_입력처리여부")
    void BrakeSystemMenu_잘못된범위_입력처리여부(){
        String input = "1\n1\n5\nexit";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        AssembleCar.main(new String[0]);

        //verify(brakeSystemMenu, times(2)).showMenu();
    }

    @Test
    @DisplayName("EngineMenu_잘못된범위_입력처리여부")
    void EngineMenu_잘못된범위_입력처리여부(){
        String input = "1\n5\nexit";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        AssembleCar.main(new String[0]);

        //verify(brakeSystemMenu, times(2)).showMenu();
    }

    @Test
    @DisplayName("SteeringMenu_잘못된범위_입력처리여부")
    void SteeringMenu_잘못된범위_입력처리여부(){
        String input = "1\n1\n1\n5\nexit";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        AssembleCar.main(new String[0]);

        //verify(brakeSystemMenu, times(2)).showMenu();
    }


    @Test
    void 잘못된형식_입력_예외처리(){
        String input = "asdf\nexit";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        AssembleCar.main(new String[1]);
    }

    @Test
    void 잘못된범위_입력_예외처리(){
        String input = "10\nexit";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        AssembleCar.main(new String[1]);
    }
}
