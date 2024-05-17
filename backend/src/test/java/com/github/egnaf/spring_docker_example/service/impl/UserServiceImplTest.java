package com.github.egnaf.spring_docker_example.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.github.egnaf.spring_docker_example.ApplicationTest;
import com.github.egnaf.spring_docker_example.domain.User;
import com.github.egnaf.spring_docker_example.exception.UserExistsException;
import com.github.egnaf.spring_docker_example.exception.UserNotFoundException;
import com.github.egnaf.spring_docker_example.repository.UserRepository;
import com.github.egnaf.spring_docker_example.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
public class UserServiceImplTest extends ApplicationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @BeforeEach
    public void setUp() {
        User user1 = new User(1L, "user1", "user1@mail.com", "pass");
        userRepository.save(user1);
        log.debug(user1.toString());

        User user2 = new User(2L, "user2", "user2@mail.com", "test");
        userRepository.save(user2);
        log.debug(user2.toString());
    }

    @Test
    public void getUsers() {
        //expected
        List<User> expected = new ArrayList<>();
        expected.add(new User(1L, "user1", "user1@mail.com", "pass"));
        expected.add(new User(2L, "user2", "user2@mail.com", "test"));

        //actual
        List<User> actual = userService.getUsers();

        //assert
        assertEquals(expected, actual);
    }

    @Test
    public void getUser() throws UserNotFoundException {
        //expected
        User expected = new User(1L, "user1", "user1@mail.com", "pass");

        //actual
        User actual = userService.getUser(1L);

        //assert
        assertEquals(expected, actual);
    }

    @Test
    public void addUser() throws UserExistsException {
        //expected
        User expected = new User(3L, "user3", "user3@mail.com", "demo");

        //actual
        User actual = userService.addUser(expected.getNickname(), expected.getEmail(), expected.getPassword());
        userRepository.deleteById(3L);

        //assert
        assertEquals(expected, actual);
    }
}
