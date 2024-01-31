package ru.practicum.shareit.booking;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookingService {

    @Transactional
    Booking createBooking(BookingDto bookingDto, Long userId);

    @Transactional
    Booking getBooking(Long bookingId, Long userId);

    public Booking confirmTheBooking(Long idUser, Long idBooking, boolean confirm);

    @Transactional
    List<Booking> getAllBookingByUser(Long userId, Status state);

    List<Booking> getAllBookingItemsByOwner(Long userId, Status state);

}
