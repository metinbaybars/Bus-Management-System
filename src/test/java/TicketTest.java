import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TicketTest {
    private Company company;
    private City city1;
    private City city2;
    private BusTrip busTripOneHourLater;
    private BusTrip busTrip2HoursLater;
    private int currentSeatNo;
    private int emptySeatNo;
    private Ticket ticket2HoursLater;
    private Ticket ticket1HourLater;
    private String userId;
    private BookManager bookManager;

    @BeforeEach
    void setup() {
        User.registerUser("1234", "Altay", "taneri862@gmail.com", "battletech45", "1234");
        userId = User.getInstance().id;
        company = new PamukKale();
        city1 = new City("İstanbul");
        city2 = new City("Ankara");
        LocalDateTime dTime = LocalDateTime.now().plusHours(2);
        LocalDateTime aTime = LocalDateTime.now().plusHours(4);
        busTrip2HoursLater = new BusTrip(dTime, aTime, city1, city2, 150.0);
        busTripOneHourLater = new BusTrip(LocalDateTime.now().plusHours(1), aTime, city1, city2, 150.0);
        currentSeatNo = 22;
        emptySeatNo = 23;
        busTripOneHourLater.assignSeatToUser(currentSeatNo, userId);
        busTrip2HoursLater.assignSeatToUser(currentSeatNo, userId);
        BusTripWithCompany busTripWithCompany = new BusTripWithCompany(busTrip2HoursLater, company);
        ticket2HoursLater = new Ticket(busTripWithCompany, currentSeatNo, userId);
        busTripWithCompany = new BusTripWithCompany(busTripOneHourLater, company);
        ticket1HourLater = new Ticket(busTripWithCompany, currentSeatNo, userId);
        bookManager = mock(BookManager.class);
    }

    @Test
    void testCanUpdateTicketPositive() {
        assertTrue(ticket2HoursLater.canUpdateTicket());
    }
    @Test
    void testCanUpdateTicketNegative() {
        assertFalse(ticket1HourLater.canUpdateTicket());
    }

    @Test
    @DisplayName("Changing seat number returns true")
    void testChangeSeatNumberResultPositive() {
        when(bookManager.changeSeatNumber(currentSeatNo, emptySeatNo, User.getInstance().id, ticket2HoursLater.busTrip)).thenReturn(true);

       boolean result = ticket2HoursLater.changeSeatNumber(bookManager, emptySeatNo);
       assertTrue(result);
    }

    @Test
    @DisplayName("Fail on changing seat number returns false")
    void testChangeSeatNumberResultNegative() {
        when(bookManager.changeSeatNumber(currentSeatNo, emptySeatNo, User.getInstance().id, ticket2HoursLater.busTrip)).thenReturn(false);

        boolean result = ticket2HoursLater.changeSeatNumber(bookManager, emptySeatNo);

        assertFalse(result);
    }

    @Test
    @DisplayName("Changing seat number updates the ticket itself")
    void testChangeSeatNumberUpdatesPositive() {
        when(bookManager.changeSeatNumber(currentSeatNo, emptySeatNo, User.getInstance().id, ticket2HoursLater.busTrip)).thenReturn(true);

        ticket2HoursLater.changeSeatNumber(bookManager, emptySeatNo);
        assertEquals(ticket2HoursLater.getSeatNumber(), emptySeatNo);
    }

    @Test
    @DisplayName("Ticket not updated when changing seat number fails")
    void testChangeSeatNumberUpdatesNegative() {
        when(bookManager.changeSeatNumber(currentSeatNo, emptySeatNo, User.getInstance().id, ticket2HoursLater.busTrip)).thenReturn(false);

        ticket2HoursLater.changeSeatNumber(bookManager, emptySeatNo);
        assertEquals(ticket2HoursLater.getSeatNumber(), currentSeatNo);
    }
}
