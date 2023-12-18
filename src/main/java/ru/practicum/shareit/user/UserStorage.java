package ru.practicum.shareit.user;

import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

public interface UserStorage {
    public UserDto get(Long id);

    public UserDto create(UserDto user);

    public UserDto update(Long id, UserDto user);

    public List<User> getAll();
    public void delete(Long id);
}
