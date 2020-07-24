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

    @Test
    public void should_return_right_car_when_after_fetching_given_parking_three_cars_and_a_ticket() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car1 = new Car("A1234");
        Car car2 = new Car("B5678");
        Car car3 = new Car("C6789");
        Ticket ticket1 = parkingBoy.park(car1);
        Ticket ticket2 = parkingBoy.park(car2);
        Ticket ticket3 = parkingBoy.park(car3);

        // when
        Car resultCar1 = parkingBoy.fetch(ticket1);
        Car resultCar3 = parkingBoy.fetch(ticket3);
        Car resultCar2 = parkingBoy.fetch(ticket2);

        // then
        if (resultCar1 != null) {
            assertEquals("A1234", resultCar1.getCarNumber());
        } else {
            assertFalse(true);
        }
        if (resultCar2 != null) {
            assertEquals("B5678", resultCar2.getCarNumber());
        } else {
            assertFalse(true);
        }
        if (resultCar3 != null) {
            assertEquals("C6789", resultCar3.getCarNumber());
        } else {
            assertFalse(true);
        }
    }
}
