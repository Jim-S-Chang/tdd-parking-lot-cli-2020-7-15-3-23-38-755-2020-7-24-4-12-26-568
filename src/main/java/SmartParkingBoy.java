import Car.Car;

import java.util.LinkedList;

public class SmartParkingBoy extends ParkingBoy{
    public SmartParkingBoy(LinkedList<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        if (isAllParkingLotsFull()) {
            this.setResponseMessage("Not enough position.");
            return null;
        }
        if (isCarParked(car) || car == null) {
            return null;
        }
        Ticket ticket = new Ticket(car.getCarNumber());
        ParkingLot maxEmptyParkingLot = findMaxEmptyParkingLot();
        maxEmptyParkingLot.park(car);
        this.setResponseMessage("");
        return ticket;
    }

    private ParkingLot findMaxEmptyParkingLot() {
        int maxValueOfEmptyParkingLot = 0;
        ParkingLot maxEmptyParkingLot = null;
        for (ParkingLot parkingLot : this.getParkingLots()) {
            int remainEmptyPosition = parkingLot.getMaxPosition() - parkingLot.getCurrentUsedPosition();
            if (remainEmptyPosition > maxValueOfEmptyParkingLot){
                maxValueOfEmptyParkingLot=remainEmptyPosition;
                maxEmptyParkingLot = parkingLot;
            }
        }
        return maxEmptyParkingLot;
    }
}
