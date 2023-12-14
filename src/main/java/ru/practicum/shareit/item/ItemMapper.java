package ru.practicum.shareit.item;

public class ItemMapper {
    public static ItemDto toItemDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .request(item.getRequest())
                .build();
    }
}
