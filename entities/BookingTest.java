package hotel.entities;

import hotel.credit.CreditCard;
import hotel.credit.CreditCardType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class BookingTest {

    Date arrivalDate;
    Guest guest;
    CreditCard creditCard;
    Room room;
    Booking booking;
    int stayLength;
    int numberOfOccupants;
    int total;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        arrivalDate = format.parse("10-10-2018");
        cal.setTime(arrivalDate);
        stayLength = 2;
        numberOfOccupants = 1;
        guest = new Guest("Nick", "Enmore", 1);
        creditCard = new CreditCard(CreditCardType.VISA, 1, 1);
        room = new Room(101, RoomType.SINGLE);

        booking = new Booking(guest, room, arrivalDate, stayLength, numberOfOccupants, creditCard);
        total = 0;
    }

    @Test
    void testChargedServiceCost() {
        booking.addServiceCharge(ServiceType.ROOM_SERVICE, 20);
        assertEquals(1, booking.getCharges().size());

        for (ServiceCharge service : booking.getCharges()) {
            total += service.getCost();
        }

        assertEquals(20, total);
    }
}