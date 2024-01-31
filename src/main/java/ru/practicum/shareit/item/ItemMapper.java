package ru.practicum.shareit.item;

import ru.practicum.shareit.exception.ValidationExceptionUser;

public class ItemMapper {
    public static ItemDto toItemDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .comments(item.getComments())
//                .request(item.getRequest())
                .build();
    }

    public static Item toItem(ItemDto itemDto, Long user) {
        Item item = new Item();
        if (itemDto.getName() != null && itemDto.getAvailable() != null && user != null) {
            item.setName(itemDto.getName());
            item.setDescription(itemDto.getDescription());
            item.setAvailable(itemDto.getAvailable());
//            item.setRequest(itemDto.getRequest());
            item.setUserId(user);
            return item;
        }
        throw new ValidationExceptionUser("not all data is entered");
    }

    public static ItemDtoUpdate toItemDtoUpdate(Item item) {
        return ItemDtoUpdate.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
//                .request(item.getRequest())
                .build();
    }

    public static Item toItem(ItemDtoUpdate itemDto, Long user) {

        Item item = new Item();
        item.setId(itemDto.getId());
        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());
        item.setAvailable(itemDto.getAvailable());
//        item.setRequest(itemDto.getRequest());
        item.setUserId(user);
        return item;

    }
}
