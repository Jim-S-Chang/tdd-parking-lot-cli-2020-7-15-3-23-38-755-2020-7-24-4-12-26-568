import Car.Car;

public class ParkingBoy {
    public Ticket park(Car car) {
        Ticket ticket = new Ticket(car.getCarNumber());
        return ticket;
    }
}
