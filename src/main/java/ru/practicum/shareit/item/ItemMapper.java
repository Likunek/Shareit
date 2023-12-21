package ru.practicum.shareit.item;

public class ItemMapper {
    public static ItemDto toItemDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .request(item.getRequest())
                .build();
    }

    public static Item toItem(ItemDto item, Long user) {

            return Item.builder()
                    .id(item.getId())
                    .name(item.getName())
                    .description(item.getDescription())
                    .available( item.getAvailable())
                    .request(item.getRequest())
                    .ownerId(user)
                    .build();

    }

    public static ItemDtoUpdate toItemDtoUpdate(Item item) {
        return ItemDtoUpdate.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .request(item.getRequest())
                .build();
    }

    public static Item toItem(ItemDtoUpdate item, Long user) {

        return Item.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available( item.getAvailable())
                .request(item.getRequest())
                .ownerId(user)
                .build();

    }
}
