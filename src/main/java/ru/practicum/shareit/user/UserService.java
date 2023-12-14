package ru.practicum.shareit.user;

import java.util.List;

public interface UserService {
    public User getUser(Long id);

    public User create(User user);

    public User update(User user);

    public List<User> getAll();
}