import Car.Car;

import java.util.LinkedList;
import java.util.List;

public class ParkingBoy {
    private List<Car> parkingLot;
    private static final int maxPosition = 10;

    public ParkingBoy() {
        this.parkingLot = new LinkedList<Car>();
    }

    public Ticket park(Car car) {
        if (parkingLot.size() == maxPosition) {
            return null;
        }
        Ticket ticket = new Ticket(car.getCarNumber());
        parkingLot.add(car);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
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
        if (parkingLot != null) {
            for (Car car : parkingLot) {
                if (car.getCarNumber().equals(ticket.getNumber())) {
                    Car resultCar = car;
                    parkingLot.remove(car);
                    return resultCar;
                }
            }
        }

        return null;
    }
}
