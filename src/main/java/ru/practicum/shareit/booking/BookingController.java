package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.exception.ExceptionEnum;
import ru.practicum.shareit.exception.UserNotFoundException;
import ru.practicum.shareit.item.ItemDto;

import javax.validation.Valid;
import java.util.List;

/**
 * TODO Sprint add-bookings.
 */
@RestController
@RequestMapping(path = "/bookings")
public class BookingController {

    BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public Booking createBooking(@RequestHeader("X-Sharer-User-Id") Long userId,
                                 @Valid @RequestBody BookingDto booking) {
        if (userId !=null) {
            return bookingService.createBooking(booking, userId);
        }
        throw new UserNotFoundException("User not found");
    }

    @GetMapping("/{bookingId}")
    public Booking getBooking(@RequestHeader("X-Sharer-User-Id") Long userId, @PathVariable Long bookingId) {
        if (userId !=null) {
            return bookingService.getBooking(bookingId, userId);
        }
        throw new UserNotFoundException("User not found");
    }

    @PatchMapping("/{bookingId}")
    public Booking confirmTheBooking(@RequestHeader("X-Sharer-User-Id") Long userId,
                                  @PathVariable Long bookingId, @RequestParam(name = "approved") boolean approved) {
        if (userId !=null) {
            return bookingService.confirmTheBooking(userId, bookingId, approved);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }
    @GetMapping
    public List<Booking> getAllBookingByUser(@RequestHeader("X-Sharer-User-Id") Long userId,
                                             @RequestParam(name = "state", defaultValue = "ALL") String state) {
        if (userId !=null) {
            try {
                Status stateNew = Status.valueOf(state.toUpperCase());
                return bookingService.getAllBookingByUser(userId, stateNew);
            } catch (RuntimeException e) {
               throw new ExceptionEnum("Unknown state: " + state);
            }
        }
        throw new UserNotFoundException("User not found");
    }

    @GetMapping("/owner")
    public List<Booking> getAllBookingItemsByOwner(@RequestHeader("X-Sharer-User-Id") Long userId,
                                                   @RequestParam(name = "state", defaultValue = "ALL") String state) {
        if (userId !=null) {
            try {
                Status stateNew = Status.valueOf(state.toUpperCase());
                return bookingService.getAllBookingItemsByOwner(userId, stateNew);
            } catch (RuntimeException e) {
                throw new ExceptionEnum("Unknown state: " + state);
            }
        }
        throw new UserNotFoundException("User not found");
    }
}
