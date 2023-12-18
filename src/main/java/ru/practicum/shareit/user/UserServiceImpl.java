package ru.practicum.shareit.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    UserStorage userStorage;

    @Autowired
    public UserServiceImpl(UserStorage userStorage) {
        this.userStorage = userStorage;
    }


    @Override
    public UserDto get(Long id) {
        return userStorage.get(id);
    }

    @Override
    public UserDto create(UserDto user) {
        return userStorage.create(user);
    }

    @Override
    public UserDto update(Long id, UserDto user) {
        return userStorage.update(id, user);
    }

    @Override
    public List<UserDto> getAll() {
        return userStorage.getAll().stream().map(UserMapper::toUserDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        userStorage.delete(id);
    }
}
