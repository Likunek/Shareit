package ru.practicum.shareit.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(path = "/users")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDto get(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping
    public UserDto create(@Valid @RequestBody UserDto user) {
        return userService.saveUser(user);
    }

    @PatchMapping("/{id}")
    public UserDto update(@PathVariable Long id, @Valid @RequestBody UserDto user) {
        return userService.updateUser(id, user);
    }

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public void deleteFriend(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
