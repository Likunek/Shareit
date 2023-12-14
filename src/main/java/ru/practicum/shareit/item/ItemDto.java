package ru.practicum.shareit.item;

import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.request.ItemRequest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class ItemDto {
    @NotNull
    private Long id;
    @NotBlank(message = "name cannot be empty")
    private String name;
    private String description;
    @NotNull
    private Boolean available;
    @NotNull
    private ItemRequest request;
}
