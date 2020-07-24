import Car.Car;

import java.util.LinkedList;

public class SuperSmartParkingBoy extends ParkingBoy{
    public SuperSmartParkingBoy(LinkedList<ParkingLot> parkingLots) {
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
        ParkingLot largestAvailablePositionRateLot = findLargestAvailablePositionRate();
        largestAvailablePositionRateLot.park(car);
        setResponseMessage("");
        return ticket;
    }

    private ParkingLot findLargestAvailablePositionRate() {
        Double maxRateOfEmptyParkingLot = 0.0;
        ParkingLot largestAvailablePositionRateLot = null;
        for (ParkingLot parkingLot : this.getParkingLots()) {
            int maxPosition = parkingLot.getMaxPosition();
            int remainPosition = maxPosition - parkingLot.getCurrentUsedPosition();
            Double remainEmptyPositionRate = (double)remainPosition/ (double)maxPosition;
            if (remainEmptyPositionRate > maxRateOfEmptyParkingLot){
                maxRateOfEmptyParkingLot=remainEmptyPositionRate;
                largestAvailablePositionRateLot = parkingLot;
            }
        }
        return largestAvailablePositionRateLot;
    }
}
