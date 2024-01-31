package ru.practicum.shareit.booking;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.exception.*;
import ru.practicum.shareit.item.Item;
import ru.practicum.shareit.item.ItemRepository;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static jdk.nashorn.internal.objects.Global.undefined;
import static ru.practicum.shareit.booking.Status.REJECTED;
import static ru.practicum.shareit.booking.Status.WAITING;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingServiceImpl implements  BookingService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    @Transactional
    @Override
    public Booking createBooking(BookingDto bookingDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Item item = itemRepository.findById(bookingDto.getItemId())
                .orElseThrow(() -> new ItemNotFoundException("Item not found"));
        if (item.getAvailable() && bookingDto.getStart().isBefore(bookingDto.getEnd())) {
            if (!user.getId().equals(item.getUserId())) {
                return bookingRepository.save(BookingMapper.toBooking(bookingDto, user, item));
            }
            throw new AccessRightsError("you do not have the necessary access rights");
        }
        throw new TheItemHasAlreadyBeenBooked("You can't book this thing");
    }
    @Override
    public Booking getBooking(Long bookingId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found"));
        Item item = itemRepository.findById(booking.getItem().getId())
                .orElseThrow(() -> new ItemNotFoundException("Item not found"));
        if (booking.getBooker().getId().equals(userId) || item.getUserId().equals(userId)) {
            return booking;
        }
        throw new AccessRightsError("you do not have the necessary access rights");
    }

    @Override
    public Booking confirmTheBooking(Long userId, Long bookingId, boolean confirm) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found"));
        Item item = itemRepository.findById(booking.getItem().getId())
                .orElseThrow(() -> new ItemNotFoundException("Item not found"));
        if (user.getId().equals(item.getUserId())) {
            if (confirm) {
                if (!booking.getStatus().equals(Status.APPROVED)) {
                    booking.setStatus(Status.APPROVED);
                } else {
                    throw new TheItemHasAlreadyBeenBooked("The status has already been confirmed");
                }
            } else {
                if (!booking.getStatus().equals(REJECTED)){
                    booking.setStatus(REJECTED);
                } else {
                    throw new TheItemHasAlreadyBeenBooked("The status has already been confirmed");
                }
            }
            return bookingRepository.save(booking);
        }
        throw new AccessRightsError("you do not have the necessary access rights");
    }

    @Override
    public List<Booking> getAllBookingByUser(Long userId, Status state) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
//        String dateOfNote = DateTimeFormatter
//                .ofPattern("yyyy.MM.dd hh:mm:ss")
//                .withZone(ZoneOffset.UTC)
//                .format(Instant.now());
        ZoneId zone = ZoneId.of("UTC+7");
        LocalDateTime date = LocalDateTime.ofInstant(Instant.now(), zone);
        switch (state) {
            case ALL:
                return bookingRepository.findByBookerId(userId);
            case PAST:
                return bookingRepository.findBookingPast(userId, date);
            case FUTURE:
                return bookingRepository.findBookingFuture(userId, date);
            case CURRENT:
                List<Booking> bookings = bookingRepository.findBookingCurrent(userId, date);
                return bookings;
            case WAITING:
                return bookingRepository.findBookingWaiting(userId, WAITING.name());
            case REJECTED:
                return bookingRepository.findBookingRejected(userId, REJECTED.name());
            default: throw new RuntimeException("the request was not found");
        }
    }

    @Override
    public List<Booking> getAllBookingItemsByOwner(Long userId, Status state) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
//        String dateOfNote = DateTimeFormatter
//                .ofPattern("yyyy.MM.dd hh:mm:ss")
//                .withZone(ZoneOffset.UTC)
//                .format(Instant.now());
        ZoneId zone = ZoneId.of("UTC+7");
        LocalDateTime date = LocalDateTime.ofInstant(Instant.now(), zone);
        switch (state) {
            case ALL:
                return bookingRepository.findBookingItemsByOwner(userId);
            case PAST:
                return bookingRepository.findBookingPastItemsByOwner(userId, date);
            case FUTURE:
                return bookingRepository.findBookingFutureItemsByOwner(userId, date);
            case CURRENT:
                return bookingRepository.findBookingCurrentItemsByOwner(userId, date);
            case WAITING:
                return bookingRepository.findBookingWaitingItemsByOwner(userId, WAITING.name());
            case REJECTED:
                return bookingRepository.findBookingRejectedItemsByOwner(userId, REJECTED.name());
            default: throw new RuntimeException("the request was not found");
        }
    }


}
