import Car.Car;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SuperSmartParkingBoyTest {
    @Test
    public void should_return_2_parked_in_1st_4_parked_in_2nd_when_parking_6_cars_given_1st_parking_lot_max_5_and_2nd_parking_lot_max_10(){
        // given
        LinkedList<ParkingLot> parkingLots = new LinkedList<>();
        ParkingLot parkingLot1 = new ParkingLot(5);
        ParkingLot parkingLot2 = new ParkingLot(10);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);

        // when
        for (int i = 0; i < 6; i++) {
            Car car = new Car("A1234" + i);
            superSmartParkingBoy.park(car);
        }

        // then
        assertEquals(2, parkingLot1.getCurrentUsedPosition());
        assertEquals(4, parkingLot2.getCurrentUsedPosition());
    }
}
