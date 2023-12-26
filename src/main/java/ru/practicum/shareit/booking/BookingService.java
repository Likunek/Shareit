package ru.practicum.shareit.booking;


import ru.practicum.shareit.exception.UserNotFoundException;
import ru.practicum.shareit.item.ItemStorage;
import ru.practicum.shareit.user.UserStorage;

public class BookingService {

    UserStorage userStorage;
    BookingStorage bookingStorage;
    ItemStorage itemStorage;

    public BookingService(UserStorage userStorage, BookingStorage bookingStorage, ItemStorage itemStorage) {
        this.userStorage = userStorage;
        this.bookingStorage = bookingStorage;
        this.itemStorage = itemStorage;
    }


    public Booking createBooking(Booking booking) {
        return bookingStorage.createBooking(booking);
    }


    public Booking getBooking(Long idBooking) {
        return bookingStorage.getBooking(idBooking);
    }


    public void confirmTheBookingByTheOwner(Long idUser, Long idBooking, boolean confirm) {
        if (userStorage.get(idUser) != null) {
            bookingStorage.confirmTheBookingByTheOwner(idUser, idBooking, confirm);
        }
        throw new UserNotFoundException("Пользователь не найден");
    }


    public void cancelTheBookingByTheСlient(Long idUser, Long idBooking) {
        if (userStorage.get(idUser) != null) {
            bookingStorage.cancelTheBookingByTheСlient(idUser, idBooking);
        }
        throw new UserNotFoundException("Пользователь не найден");
    }


    public void addReview(Long idUser, Long idBooking, String review) {
        if (userStorage.get(idUser) != null) {
            bookingStorage.addReview(idUser, idBooking, review);
        }
        throw new UserNotFoundException("Пользователь не найден");
    }
}
