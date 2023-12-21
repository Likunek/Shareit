package ru.practicum.shareit.item;

import java.util.List;

public interface ItemStorage {
    public ItemDto get(Long itemId);

    public ItemDto add(ItemDto itemDto, Long userId);

    public ItemDtoUpdate update(ItemDtoUpdate itemDto, Long userId, Long itemId);

    public List<ItemDto> getAll(Long userId);

    public void delete(Long itemId, Long userId);

    public List<ItemDto> search(String text);
}
