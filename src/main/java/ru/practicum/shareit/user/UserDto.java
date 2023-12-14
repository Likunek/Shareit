package ru.practicum.shareit.user;


import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class UserDto {
    @NotNull
    private Long id;
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message = "name cannot be empty")
    private String name;
}
