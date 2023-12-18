package ru.practicum.shareit.item;

import org.springframework.web.client.HttpClientErrorException;
import ru.practicum.shareit.user.User;

import java.util.List;

public interface ItemStorage {
    public ItemDto get(Long id);

    public ItemDto add(User user) throws HttpClientErrorException;

    public ItemDto update(Long id, User user);

    public List<ItemDto> getAll();
    public void delete(Long id);
}
