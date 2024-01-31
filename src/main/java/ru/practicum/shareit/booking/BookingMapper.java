package ru.practicum.shareit.booking;

import ru.practicum.shareit.item.Item;
import ru.practicum.shareit.user.User;

public class BookingMapper {

    static public Booking toBooking(BookingDto bookingDto, User user, Item item) {
        Booking booking = new Booking();
        booking.setStart(bookingDto.getStart());
        booking.setEnd(bookingDto.getEnd());
        booking.setBooker(user);
        booking.setItem(item);
        booking.setStatus(Status.WAITING);
        return booking;
    }

    static public BookingByBooker toBookingByBooker(Booking booking) {
        return BookingByBooker
                .builder()
                .id(booking.getId())
                .bookerId(booking.getBooker().getId())
                .build();
    }
}
