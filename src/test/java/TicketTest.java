import Car.Car;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TicketTest {
    @Test
    public void should_return_ticket_number_when_get_ticket_number_given_a_car(){
        // given
        Car car = Mockito.mock(Car.class);
        when(car.getCarNumber()).thenReturn("A1234");
        Ticket ticket = new Ticket(car.getCarNumber());
        // when
        String ticketNumber = ticket.getNumber();
        // then
        assertEquals("A1234", ticketNumber);
    }
}
