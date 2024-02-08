package ru.practicum.shareit.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    private UserRepository repository;
    private UserService service;
    private UserDto mapper;

    @BeforeEach
    void setUp() {
        repository = mock(UserRepository.class);
        mapper = UserMapper.toUser(UserDto.class);
    }
    @Test
    void create() {
    }
    @Test
    void update() {
    }
    @Test
    void getById() {
    }

    @Test
    void getAll() {
        var user = new User();
        user.setId(1L);
        user.setName("first");
        user.setEmail("mail@mail.ru");
        when(repository.findAll()).thenReturn(Collections.singletonList(user));

        var result = service.getAllUsers();
        assertNotNull(result);
        assertEquals(user.getId(), result.get(0).getId());
    }

    @Test
    void delete() {
    }
}
