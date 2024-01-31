package ru.practicum.shareit.booking;

import lombok.Data;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class BookingDto {
    @NotNull
    @FutureOrPresent
    private LocalDateTime start;
    @NotNull
    private LocalDateTime end;
    @NotNull
    private Long itemId;
}
