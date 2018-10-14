# asg4-hotel
There are 2 bugs needed to be fixed in the source code

#1 When checkout, all of the services recorded of a room return $0.00.
    This bug caused by using mistakenly local property cost to create new service.
    https://github.com/nhuthuynh/asg4-hotel/blob/master/entities/Booking.java // error file
    https://github.com/nhuthuynh/asg4-hotel/blob/master/entities/BookingTest.java // test file
#2 It is possible to charge a room for service after the guest has checked out
    This is logic bug caused by not removing the booking of a room when checking out of the room.
    https://github.com/nhuthuynh/asg4-hotel/blob/master/entities/Hotel.java // error file
    https://github.com/nhuthuynh/asg4-hotel/blob/master/entities/HotelTest.java // test file
