package com.studies.course.services;

import com.studies.course.entities.User;
import com.studies.course.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Tag("UnitTest")
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void findAll() {
        User u1 = new User(1L, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(2L, "Alex Green", "alex@gmail.com", "977777777", "123456");

        List<User> users = Arrays.asList(u1, u2);

        Mockito.when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.findAll();
        Assertions.assertEquals(users, result);
    }

    @Test
    void findById() {
        User u1 = new User(1L, "Maria Brown", "maria@gmail.com", "988888888", "123456");

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(u1));

        User result = userService.findById(1L);
        Assertions.assertAll("verificando user",
                () -> Assertions.assertEquals(1L, result.getId()),
                () -> Assertions.assertEquals("Maria Brown", result.getName()),
                () -> Assertions.assertEquals("maria@gmail.com", result.getEmail()),
                () -> Assertions.assertEquals("988888888", result.getPhone()),
                () -> Assertions.assertEquals("123456", result.getPassword())
        );
    }

    @Test
    void insert() {
        User u1 = new User(1L, "Maria Brown", "maria@gmail.com", "988888888", "123456");

        Mockito.when(userRepository.save(u1)).thenReturn(u1);

        User result = userService.insert(u1);
        Assertions.assertNotNull(result);
    }

    @Test
    void update() {
        User u1 = new User(1L, "Maria Brown", "maria@gmail.com", "988888888", "123456");

        Mockito.when(userRepository.getReferenceById(1L)).thenReturn(u1);
        Mockito.when(userRepository.save(u1)).thenReturn(u1);

        User result = userService.update(1L, u1);

        Assertions.assertNotNull(result);
    }
}