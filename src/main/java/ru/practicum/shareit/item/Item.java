package ru.practicum.shareit.item;


import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.request.ItemRequest;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class Item {

    @NotNull
    private Long id;
    @NotBlank(message = "login cannot be empty")
    private String name;
    private String description;
    @NotNull
    private Boolean available;

    @NotNull
    private Long ownerId;

    private ItemRequest request;
}
