package ru.practicum.shareit.booking;

import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.item.Item;
import ru.practicum.shareit.user.User;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class Review {
    private Long id;
    private String description;
    @NotNull
    private Item item;
    @NotNull
    private User user;
}
