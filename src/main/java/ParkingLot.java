import Car.Car;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class ParkingLot {
    private String parkingLotID;
    private static final int maxPosition = 10;
    private List<Car> parkedCar;
    private int currentUsedPosition;
    ParkingLot(){
        this.parkingLotID = String.valueOf(UUID.randomUUID());
        this.parkedCar = new LinkedList<>();
        this.currentUsedPosition = 0;
    }

    public int getCurrentUsedPosition() {
        return currentUsedPosition;
    }

    public static int getMaxPosition() {
        return maxPosition;
    }

    public List<Car> getParkedCar() {
        return parkedCar;
    }

    public void park(Car car) {
        parkedCar.add(car);
        currentUsedPosition++;
    }

    public void remove(Car car) {
        parkedCar.remove(car);
    }
}
