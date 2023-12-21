package ru.practicum.shareit.item;

import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.request.ItemRequest;

@Data
@Builder
public class ItemDtoUpdate {
    private Long id;
    private String name;
    private String description;
    private Boolean available;
    private ItemRequest request;
}
