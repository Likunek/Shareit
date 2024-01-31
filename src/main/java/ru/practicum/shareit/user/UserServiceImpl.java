package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.booking.BookingService;
import ru.practicum.shareit.exception.UserNotFoundException;
import ru.practicum.shareit.item.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::toUserDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDto saveUser(UserDto userDto) {
        User user = userRepository.save(UserMapper.toUser(userDto));
        return UserMapper.toUserDto(user);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        if (id.equals(user.getId())) {
            if (userDto.getEmail() != null) {
                user.setEmail(userDto.getEmail());
            }
            if (userDto.getName() != null) {
                user.setName(userDto.getName());
            }
            return UserMapper.toUserDto(userRepository.save(user));
        }
        throw new RuntimeException("the user does not match");
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUser(Long id) {
        return UserMapper.toUserDto(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found")));
    }

    @Override
    public void deleteUser(Long id) {
        getUser(id);
        userRepository.deleteById(id);
    }

}
