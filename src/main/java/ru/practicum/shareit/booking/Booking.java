package ru.practicum.shareit.booking;

import lombok.Data;
import ru.practicum.shareit.item.Item;
import ru.practicum.shareit.user.User;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class Booking {
    private Long id;
    @FutureOrPresent(message = "wrong start date")
    private LocalDate start;
    @Future(message = "wrong end date")
    private LocalDate end;
    @NotNull
    private Item item;
    @NotNull
    private User booker;
    @NotNull
    private Status status;
}
