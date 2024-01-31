package ru.practicum.shareit.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.practicum.shareit.item.Item;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long>, QuerydslPredicateExecutor<Item> {

    @Query(value = "select * " +
            "from booking as b " +
            "where b.user_id = ?1 " +
            "ORDER BY b.start_time DESC",
            nativeQuery = true)
    List<Booking> findByBookerId(Long userId);

    @Query(value = "select * " +
            "from booking as b " +
            "where b.item_id = ?1 and b.start_time < ?2 and b.status != ?3 " +
            "ORDER BY b.start_time DESC",
            nativeQuery = true)
    List<Booking> findByItemIdPast(Long itemId, LocalDateTime time, Status status);

    @Query(value = "select * " +
            "from booking as b " +
            "where b.item_id = ?1 and b.end_time > ?2 and NOT(b.status LIKE ?3) and b.start_time > ?2 " +
            "ORDER BY b.start_time ASC",
            nativeQuery = true)
    List<Booking> findByItemIdFuture(Long itemId, LocalDateTime time, Status status);

    @Query(value = "select * " +
            "from booking as b " +
            "where b.user_id = ?1 and b.end_time < ?2 " +
            "ORDER BY b.start_time DESC",
            nativeQuery = true)
    List<Booking> findBookingPast(Long userId, LocalDateTime time);

    @Query(value = "select * " +
            "from booking as b " +
            "where b.user_id = ?1 and ?2 between b.start_time and b.end_time " +
            "ORDER BY b.start_time DESC",
            nativeQuery = true)
    List<Booking> findBookingCurrent(Long userId, LocalDateTime time);

    @Query(value = "select * " +
            "from booking as b " +
            "where b.user_id = ?1 and b.end_time > ?2 " +
            "ORDER BY b.start_time DESC",
            nativeQuery = true)
    List<Booking> findBookingFuture(Long userId, LocalDateTime time);

    @Query(value = "select * " +
            "from booking as b " +
            "where b.user_id = ?1 and b.status = ?2 " +
            "ORDER BY b.start_time DESC",
            nativeQuery = true)
    List<Booking> findBookingWaiting(Long userId, String waiting);

    @Query(value = "select * " +
            "from booking as b " +
            "where b.user_id = ?1 and b.status = ?2 " +
            "ORDER BY b.start_time DESC",
            nativeQuery = true)
    List<Booking> findBookingRejected(Long userId, String rejected);

    @Query(value = "select * " +
            "from booking as b " +
            "join items as t ON b.item_id = t.id " +
            "where t.user_id = ?1 " +
            "ORDER BY b.start_time DESC",
            nativeQuery = true)
    List<Booking> findBookingItemsByOwner(Long userId);

    @Query(value = "select * " +
            "from booking as b " +
            "join items as t ON b.item_id = t.id " +
            "where t.user_id = ?1 and b.end_time < ?2 " +
            "ORDER BY b.start_time DESC",
            nativeQuery = true)
    List<Booking> findBookingPastItemsByOwner(Long userId, LocalDateTime time);

    @Query(value = "select * " +
            "from booking as b " +
            "join items as t ON b.item_id = t.id " +
            "where t.user_id = ?1 and ?2 between b.start_time and b.end_time " +
            "ORDER BY b.start_time DESC",
            nativeQuery = true)
    List<Booking> findBookingCurrentItemsByOwner(Long userId, LocalDateTime time);

    @Query(value = "select * " +
            "from booking as b " +
            "join items as t ON b.item_id = t.id " +
            "where t.user_id = ?1 and b.end_time > ?2 " +
            "ORDER BY b.start_time DESC",
            nativeQuery = true)
    List<Booking> findBookingFutureItemsByOwner(Long userId, LocalDateTime time);

    @Query(value = "select * " +
            "from booking as b " +
            "join items as t ON b.item_id = t.id " +
            "where t.user_id = ?1 and b.status = ?2 " +
            "ORDER BY b.start_time DESC",
            nativeQuery = true)
    List<Booking> findBookingWaitingItemsByOwner(Long userId, String status);

    @Query(value = "select b.* " +
            "from booking as b " +
            "join items as t ON b.item_id = t.id " +
            "where t.user_id = ?1 and b.status = ?2 " +
            "ORDER BY b.start_time DESC",
            nativeQuery = true)
    List<Booking> findBookingRejectedItemsByOwner(Long userId, String status);

}
