import Car.Car;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

public class SmartParkingBoyTest {
    @Test
    public void should_return_4_in_parking_lot1_3_in_parking_lot2_3_in_parking_lot3_when_parking_given_10_cars_3_parking_lots(){
        // given
        LinkedList<ParkingLot> parkingLots = new LinkedList<>();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingLot parkingLot3 = new ParkingLot();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        parkingLots.add(parkingLot3);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        // when
        for (int i = 0; i < 10; i++) {
            Car car = new Car("A1234" + i);
            smartParkingBoy.park(car);
        }

        // then
        assertEquals(4, parkingLot1.getCurrentUsedPosition());
        assertEquals(3, parkingLot2.getCurrentUsedPosition());
        assertEquals(3, parkingLot3.getCurrentUsedPosition());
    }

    @Test
    public void should_return_ticket_when_after_parking_given_a_car() {
        // given
        LinkedList<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(new ParkingLot());
        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);
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
        LinkedList<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(new ParkingLot());
        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car("A1234");
        Ticket ticket = parkingBoy.park(car);

        // when
        Car resultCar = parkingBoy.fetch(ticket);

        // then
        assertFalse(car == null);
        assertEquals("A1234", resultCar.getCarNumber());
    }

    @Test
    public void should_return_right_car_when_after_fetching_given_parking_three_cars_and_a_ticket() {
        // given
        LinkedList<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(new ParkingLot());
        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);
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
        assertFalse(resultCar1 == null);
        assertEquals("A1234", resultCar1.getCarNumber());
        assertFalse(resultCar2 == null);
        assertEquals("B5678", resultCar2.getCarNumber());
        assertFalse(resultCar3 == null);
        assertEquals("C6789", resultCar3.getCarNumber());
    }

    @Test
    public void should_return_null_when_after_fetching_given_wrong_ticket() {
        // given
        LinkedList<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(new ParkingLot());
        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car("A1234");
        parkingBoy.park(car);
        Ticket wrongTicket = new Ticket("B5678");

        // when
        Car resultCar = parkingBoy.fetch(wrongTicket);

        // then
        assertTrue(resultCar == null);
    }

    @Test
    public void should_return_null_when_after_fetching_given_null() {
        // given
        LinkedList<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(new ParkingLot());
        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car("A1234");
        parkingBoy.park(car);

        // when
        Car resultCar = parkingBoy.fetch(null);

        // then
        assertTrue(resultCar == null);
    }

    @Test
    public void should_return_null_when_after_fetching_given_used_ticket() {
        // given
        LinkedList<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(new ParkingLot());
        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car("A1234");
        Ticket ticket = parkingBoy.park(car);
        Car resultCar1 = parkingBoy.fetch(ticket);

        // when
        Car resultCar2 = parkingBoy.fetch(ticket);

        // then
        assertEquals("A1234", resultCar1.getCarNumber());
        assertTrue(resultCar2 == null);
    }

    @Test
    public void should_return_null_when_parking_given_no_position() {
        // given
        LinkedList<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(new ParkingLot());
        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);
        for (int i = 0; i < 10; i++) {
            Car car = new Car("A1234" + i);
            parkingBoy.park(car);
        }
        Car car11 = new Car("A1234" + 10);

        // when
        Ticket ticket = parkingBoy.park(car11);

        // then
        assertTrue(ticket == null);
    }

    @Test
    public void should_return_null_when_parking_given_parked_car() {
        // given
        LinkedList<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(new ParkingLot());
        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car("A1234" + 10);
        parkingBoy.park(car);

        // when
        Ticket ticket = parkingBoy.park(car);

        // then
        assertTrue(ticket == null);
    }

    @Test
    public void should_return_null_when_parking_given_null_car() {
        // given
        LinkedList<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(new ParkingLot());
        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);

        // when
        Ticket ticket = parkingBoy.park(null);

        // then
        assertTrue(ticket == null);
    }

    @Test
    public void should_return_provide_parking_ticket_when_fetching_given_null() {
        // given
        LinkedList<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(new ParkingLot());
        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car("A1234");
        parkingBoy.park(car);

        // when
        parkingBoy.fetch(null);
        String result = parkingBoy.getResponseMessage();

        // then
        assertEquals("Please provide your parking ticket.", result);
    }

    @Test
    public void should_return_unrecognized_parking_ticket_when_fetching_given_used_ticket() {
        // given
        LinkedList<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(new ParkingLot());
        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);

        Car car = new Car("A1234");
        Ticket ticket = parkingBoy.park(car);
        parkingBoy.fetch(ticket);

        // when
        parkingBoy.fetch(ticket);
        String result = parkingBoy.getResponseMessage();

        // then
        assertEquals("Unrecognized parking ticket.", result);
    }

    @Test
    public void should_return_unrecognized_parking_ticket_when_fetching_given_wrong_ticket() {
        // given
        LinkedList<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(new ParkingLot());
        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car("A1234");
        parkingBoy.park(car);
        Ticket wrongTicket = new Ticket("B5678");

        // when
        parkingBoy.fetch(wrongTicket);
        String result = parkingBoy.getResponseMessage();

        // then
        assertEquals("Unrecognized parking ticket.", result);
    }

    @Test
    public void should_return_not_enough_position_when_parking_given_no_position() {
        // given
        LinkedList<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(new ParkingLot());
        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);
        for (int i = 0; i < 10; i++) {
            Car car = new Car("A1234" + i);
            parkingBoy.park(car);
        }
        Car car11 = new Car("A1234" + 10);

        // when
        parkingBoy.park(car11);
        String result = parkingBoy.getResponseMessage();

        // then
        assertEquals("Not enough position.", result);
    }

    @Test
    public void should_return_ticket_when_parking_full_and_leave_one_given_full_in_2nd_parking_lot_1_empty_in_1st_parking_lot() {
        // given
        LinkedList<ParkingLot> parkingLots = new LinkedList<>();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);
        for (int i = 0; i < 20; i++) {
            Car car = new Car("A1234" + i);
            parkingBoy.park(car);
        }
        parkingBoy.fetch(new Ticket("A12346"));
        Car car21 = new Car("A1234" + 21);

        // when
        Ticket ticket = parkingBoy.park(car21);
        String result = parkingBoy.getResponseMessage();

        // then
        assertEquals("", result);
        assertEquals("A123421", ticket.getNumber());
    }
}
