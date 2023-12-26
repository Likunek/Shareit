package ru.practicum.shareit.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.exception.EmailErrorAlreadyExists;
import ru.practicum.shareit.exception.UserNotFoundException;
import ru.practicum.shareit.exception.ValidationExceptionUser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class UserStorageImpl implements UserStorage {

    private final Map<Long, User> storage = new HashMap<>();
    private long generatedId;


    @Override
    public UserDto get(Long id) {
        if (storage.containsKey(id)) {
            log.info("Get user id {}", id);
            return UserMapper.toUserDto(storage.get(id));
        }
        throw new UserNotFoundException("Пользователь не найден");
    }

    @Override
    public UserDto create(UserDto user) {
        if (user.getEmail() == null) {
            throw new ValidationExceptionUser("Отсутствует email");
        }
        for (User userValue : storage.values()) {
            if (userValue.getEmail().equals(user.getEmail())) {
                throw new EmailErrorAlreadyExists("такой email уже существует");
            }
        }
        user.setId(++generatedId);
        log.info("Creating user {}", user);
        storage.put(user.getId(), UserMapper.toUser(user));
        return user;
    }

    @Override
    public UserDto update(Long id, UserDto user) {
        for (User userValue : storage.values()) {
            if (userValue.getEmail().equals(user.getEmail()) && !userValue.getId().equals(id)) {
                throw new EmailErrorAlreadyExists("такой email уже существует");
            }
        }
        if (storage.containsKey(id)) {
            User userOld = storage.get(id);
            if (user.getEmail() != null) {
                userOld.setEmail(user.getEmail());
            }
            if (user.getName() != null) {
                userOld.setName(user.getName());
            }
            log.info("Update user {}", userOld);
            storage.put(id, userOld);
            return UserMapper.toUserDto(userOld);
        }
        throw new UserNotFoundException("Пользователь не найден");
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void delete(Long id) {
        if (storage.containsKey(id)) {
            log.info("Delete user with id {}", id);
            storage.remove(id);
        } else {
            throw new UserNotFoundException("Пользователь не найден");
        }
    }
}
