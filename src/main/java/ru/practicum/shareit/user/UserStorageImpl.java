package ru.practicum.shareit.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserStorageImpl implements UserStorage {

    private final Map<Long, User> storage = new HashMap<>();

    @Override
    public User getUser(Long id) {
        return null;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
