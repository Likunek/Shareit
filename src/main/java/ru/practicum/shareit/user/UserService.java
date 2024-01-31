package ru.practicum.shareit.user;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    public UserDto getUser(Long id);

    @Transactional(readOnly = true)
    List<UserDto> getAllUsers();

    public UserDto saveUser(UserDto user);

    public UserDto updateUser(Long id, UserDto userDto);

    public void deleteUser(Long id);
}
