package ru.practicum.shareit.user;


import ru.practicum.shareit.exception.ValidationExceptionUser;

public class UserMapper {

    public static UserDto toUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
    public static User toUser(UserDto userDto) {
       User user = new User();
        if (userDto.getName() != null && userDto.getEmail() != null) {
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            return user;
        }
        throw new ValidationExceptionUser("not all data is entered");
    }
}
