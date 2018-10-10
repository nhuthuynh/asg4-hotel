package hotel.entities;

import hotel.credit.CreditCard;
import hotel.credit.CreditCardType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class HotelTest {

    @Mock
    Booking mockBooking;
    @Mock Room mockRoom;
    @InjectMocks
    Hotel hotel;

    Guest guest;
    Date arrivalDate;
    Booking booking;
    Room room;
    int stayLength;
    int numberOfOccupants;
    CreditCard creditCard;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        arrivalDate = format.parse("10-10-2018");
        cal.setTime(arrivalDate);
        guest = new Guest("Nick", "Enmore", 1);
        creditCard = new CreditCard(CreditCardType.VISA, 1, 1);
        room = new Room(101, RoomType.SINGLE);
        booking = new Booking(guest, room, arrivalDate, stayLength, numberOfOccupants, creditCard);
    }

    @Test
    void testPossibleAddServiceChargeWhenTheRoomCheckOut() {
        when(mockRoom.book(any(Guest.class), any(Date.class), anyInt(), anyInt(), any(CreditCard.class))).thenReturn(booking);
        hotel.book(room, guest, arrivalDate, stayLength, numberOfOccupants, creditCard);
        assertEquals(1, hotel.bookingsByConfirmationNumber.size());
        hotel.checkin(booking.getConfirmationNumber());
        assertEquals(1, hotel.activeBookingsByRoomId.size());
        hotel.addServiceCharge(room.getId(), ServiceType.BAR_FRIDGE, 20);
        assertNotNull(hotel.findActiveBookingByRoomId(room.getId()));
        assertEquals(1, hotel.findActiveBookingByRoomId(room.getId()).getCharges().size());
        hotel.checkout(room.getId());
        assertThrows(RuntimeException.class, () -> hotel.addServiceCharge(room.getId(), ServiceType.BAR_FRIDGE, 200));
        assertNull(hotel.findActiveBookingByRoomId(room.getId()));
    }
}