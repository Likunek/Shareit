package ru.practicum.shareit.item;

import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.booking.Booking;
import ru.practicum.shareit.booking.BookingByBooker;
import ru.practicum.shareit.request.ItemRequest;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
public class ItemDto {

    private Long id;
    @NotNull
    @NotBlank(message = "name cannot be empty")
    private String name;
    @NotNull
    private String description;
    @NotNull
    private Boolean available;
    private ItemRequest request;

    private BookingByBooker lastBooking;

    private BookingByBooker nextBooking;

    private Set<Comment> comments;
}
