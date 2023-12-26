package ru.practicum.shareit.request;

import ru.practicum.shareit.user.User;

public interface ItemRequestStorage {
    public ItemRequest create(ItemRequest itemRequest,  User user);
}
