package ru.practicum.shareit.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.ItemNotFoundException;
import ru.practicum.shareit.exception.UserNotFoundException;
import ru.practicum.shareit.user.UserStorage;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

     UserStorage userStorage;
     ItemStorage itemStorage;

    @Autowired
    public ItemServiceImpl(UserStorage userStorage, ItemStorage itemStorage) {
        this.userStorage = userStorage;
        this.itemStorage = itemStorage;
    }

    @Override
    public ItemDto get(Long itemId) {
       return itemStorage.get(itemId);
    }

    @Override
    public ItemDto add(ItemDto itemDto, Long userId) {
        if (userStorage.get(userId) != null) {
            return itemStorage.add(itemDto, userId);
        }
        throw new UserNotFoundException("Пользователь не найден");
    }

    @Override
    public ItemDtoUpdate update(ItemDtoUpdate itemDto, Long userId, Long itemId) {
        if (userStorage.get(userId) != null) {
            if (get(itemId) != null) {
                return itemStorage.update(itemDto, userId, itemId);
            }
            throw new ItemNotFoundException("Вещь не найдена");
        }
        throw new UserNotFoundException("Пользователь не найден");
    }

    @Override
    public List<ItemDto> getAll(Long userId) {
        if (userStorage.get(userId) != null) {
            return itemStorage.getAll(userId);
        }
        throw new UserNotFoundException("Пользователь не найден");
    }

    @Override
    public void delete(Long itemId, Long userId) {
        if (userStorage.get(userId) != null) {
            itemStorage.delete(itemId, userId);
        } else {
            throw new UserNotFoundException("Пользователь не найден");
        }
    }

    @Override
    public List<ItemDto> search(String text) {
        return itemStorage.search(text);
    }
}
