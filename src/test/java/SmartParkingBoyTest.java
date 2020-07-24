import Car.Car;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
