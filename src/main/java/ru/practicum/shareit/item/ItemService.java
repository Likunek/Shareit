package ru.practicum.shareit.item;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ItemService {

    @Transactional
    ItemDto addNewItem(Long userId, ItemDto itemDto);

    ItemDto updateItem(Long itemId, Long userId, ItemDtoUpdate itemDto);

    @Transactional
    void deleteItem(Long userId, Long itemId);

    @Transactional(readOnly = true)
    List<ItemDto> getUserItems(Long userId);

    public ItemDto getItem(Long id, Long userId);

    List<ItemInfo> search(String text);

    public Comment addComment(Long userId, Long itemId, Comment comment);
}
