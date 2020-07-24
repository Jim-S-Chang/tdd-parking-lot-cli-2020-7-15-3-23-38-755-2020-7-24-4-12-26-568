import Car.Car;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarTest {
    @Test
    public void should_return_car_number_when_get_car_number_given_a_car() {
        // given
        Car car = new Car("A1234");
        // when
        String result = car.getCarNumber();
        // then
        assertEquals("A1234", result);
    }
}
