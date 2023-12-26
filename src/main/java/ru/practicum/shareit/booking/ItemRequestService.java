package ru.practicum.shareit.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.UserNotFoundException;
import ru.practicum.shareit.request.ItemRequest;
import ru.practicum.shareit.request.ItemRequestStorage;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserMapper;
import ru.practicum.shareit.user.UserStorage;

@Service
public class ItemRequestService {

    ItemRequestStorage itemRequestStorage;
    UserStorage userStorage;

    @Autowired
    public ItemRequestService(ItemRequestStorage itemRequestStorage, UserStorage userStorage) {
        this.itemRequestStorage = itemRequestStorage;
        this.userStorage = userStorage;
    }

    public ItemRequest create(ItemRequest itemRequest, Long userId) {
        User user = UserMapper.toUser(userStorage.get(userId));
        if (user != null) {
            return itemRequestStorage.create(itemRequest, user);
        }
        throw new UserNotFoundException("Пользователь не найден");
    }
}
