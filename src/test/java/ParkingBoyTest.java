import Car.Car;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

public class ParkingBoyTest {
    @Test
    public void should_return_ticket_when_after_parking_given_a_car() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = Mockito.mock(Car.class);
        when(car.getCarNumber()).thenReturn("A1234");

        // when
        Ticket ticket = parkingBoy.park(car);

        // then
        assertEquals("A1234", ticket.getNumber());
    }

    @Test
    public void should_return_car_when_after_fetching_given_a_ticket() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car("A1234");
        Ticket ticket = parkingBoy.park(car);

        // when
        Car resultCar = parkingBoy.fetch(ticket);

        // then
        if (car != null) {
            assertEquals("A1234", resultCar.getCarNumber());
        } else {
            assertFalse(true);
        }
    }
}
