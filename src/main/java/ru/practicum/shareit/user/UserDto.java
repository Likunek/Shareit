package ru.practicum.shareit.user;


import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;


@Data
@Builder
public class UserDto {
    private Long id;
    @Email(message = "Email should be valid")
    private String email;
    private String name;

}
