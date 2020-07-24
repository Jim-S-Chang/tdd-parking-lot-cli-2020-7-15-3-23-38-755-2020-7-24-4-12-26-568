import Car.Car;

import java.util.LinkedList;
import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> parkingLots;
    private String responseMessage;
    private static final int maxPosition = 10;

    public ParkingBoy(LinkedList<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
        this.responseMessage = "";
    }

    public Ticket park(Car car) {
        if (isAllParkingLotsFull()) {
            this.responseMessage = "Not enough position.";
            return null;
        }
        if (isCarParked(car) || car == null) {
            return null;
        }
        Ticket ticket = new Ticket(car.getCarNumber());
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getCurrentUsedPosition() == parkingLot.getMaxPosition()) {
                continue;
            } else {
                parkingLot.park(car);
                break;
            }
        }
        this.responseMessage = "";
        return ticket;
    }

    protected boolean isAllParkingLotsFull() {
        for (ParkingLot parkingLot: parkingLots){
            if (parkingLot.getCurrentUsedPosition() != parkingLot.getMaxPosition()){
                return false;
            }
        }
        return true;
    }

    protected boolean isCarParked(Car car) {
        for (ParkingLot parkingLot : parkingLots) {
            for (Car carInParkingLot : parkingLot.getParkedCar()) {
                if (car.getCarNumber() == carInParkingLot.getCarNumber()) {
                    return true;
                }
            }
        }
        return false;
    }

    public Car fetch(Ticket ticket) {
        if (ticket == null) {
            this.responseMessage = "Please provide your parking ticket.";
        }
        Car car = findCar(ticket);
        if (car != null) {
            return car;
        }
        return null;
    }

    private Car findCar(Ticket ticket) {
        if (ticket == null) {
            return null;
        }
        if (parkingLots != null) {
            for (ParkingLot parkingLot : parkingLots) {
                for (Car car : parkingLot.getParkedCar()) {
                    if (car.getCarNumber().equals(ticket.getNumber())) {
                        Car resultCar = car;
                        parkingLot.remove(car);
                        return resultCar;
                    }
                }

            }
        }
        this.responseMessage = "Unrecognized parking ticket.";
        return null;
    }

    public String getResponseMessage() {
        return this.responseMessage;
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
