package ru.practicum.shareit.item;

import org.springframework.web.client.HttpClientErrorException;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserDto;

import java.util.List;

public interface ItemService {
    public ItemDto get(Long id);

    public ItemDto add(Long userId, ItemDto itemDto);

    public ItemDto update(Long userId, ItemDto itemDto);

    public List<ItemDto> getAll();
    public void delete(Long id);
}
