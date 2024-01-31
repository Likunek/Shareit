//package ru.practicum.shareit.booking;
//
//
//import ru.practicum.shareit.exception.BookingNotFoundException;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class BookingStorageImpl implements BookingStorage {
//
//    private final Map<Long, Booking> storage = new HashMap<>();
//    private final Map<Long, Review> storageReview = new HashMap<>();
//    private long generatedIdBooking;
//    private long generatedIdReview;
//
//
//    @Override
//    public Booking createBooking(Booking booking) {
//        booking.setId(generatedIdBooking++);
//        booking.setStatus(Status.WAITING);
//        storage.put(booking.getId(), booking);
//        return booking;
//    }
//
//    @Override
//    public Booking getBooking(Long idBooking) {
//        if (storage.containsKey(idBooking)) {
//            storage.get(idBooking);
//        }
//        throw new BookingNotFoundException("бронирование не найдено");
//    }
//
//    @Override
//    public void confirmTheBookingByTheOwner(Long userId, Long idBooking, boolean confirm) {
//        Booking booking = getBooking(idBooking);
//        if (booking != null) {
//            if (booking.getItem().getOwnerId().equals(userId)) {
//                if (confirm) {
//                    storage.get(idBooking).setStatus(Status.APPROVED);
//                } else {
//                    storage.get(idBooking).setStatus(Status.REJECTED);
//                }
//            }
//        }
//        throw new BookingNotFoundException("бронирование не найдено");
//    }
//
//    @Override
//    public void cancelTheBookingByTheСlient(Long userId, Long idBooking) {
//        Booking booking = getBooking(idBooking);
//        if (booking != null) {
//            if (booking.getBooker().getId().equals(userId)) {
//                storage.get(idBooking).setStatus(Status.CANCELED);
//            }
//        }
//        throw new BookingNotFoundException("бронирование не найдено");
//    }
//
//    @Override
//    public void addReview(Long userId, Long idBooking, String review) {
//        Booking booking = getBooking(idBooking);
//        if (booking != null) {
//            if (booking.getBooker().getId().equals(userId)) {
//                Review newReview = Review.builder()
//                        .id(generatedIdReview++)
//                        .description(review)
//                        .item(booking.getItem())
//                        .user(booking.getBooker())
//                        .build();
//                storageReview.put(newReview.getId(), newReview);
//            }
//        }
//        throw new BookingNotFoundException("бронирование не найдено");
//    }
//}
