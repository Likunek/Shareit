package ru.practicum.shareit.item;

import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.request.ItemRequest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
}
