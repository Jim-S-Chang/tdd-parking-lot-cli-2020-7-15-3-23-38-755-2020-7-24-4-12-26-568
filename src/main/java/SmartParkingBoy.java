import Car.Car;

import java.util.LinkedList;

public class SmartParkingBoy extends ParkingBoy{
    public SmartParkingBoy(LinkedList<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        if (this.getParkingLots().get(this.getParkingLots().size() - 1).getCurrentUsedPosition() ==
                this.getParkingLots().get(this.getParkingLots().size() - 1).getMaxPosition()) {
            this.setResponseMessage("Not enough position.");
            return null;
        }
        if (isCarParked(car) || car == null) {
            return null;
        }
        Ticket ticket = new Ticket(car.getCarNumber());
        ParkingLot maxEmptyParkingLot = findMaxEmptyParkingLot();
        maxEmptyParkingLot.park(car);
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
