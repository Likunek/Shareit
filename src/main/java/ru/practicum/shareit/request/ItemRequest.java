package ru.practicum.shareit.request;

import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.user.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;


@Data
@Builder
public class ItemRequest {
    @NotNull
    private Long id;
    private String description;
    @NotNull
    private User requestors;
    @Past(message = "wrong created date")
    private LocalDate created;
}
