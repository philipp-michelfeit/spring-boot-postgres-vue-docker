package com.github.egnaf.spring_docker_example.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.github.egnaf.spring_docker_example.ApplicationTest;
import com.github.egnaf.spring_docker_example.domain.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
public class UserRepositoryTest extends ApplicationTest {

    @Autowired
    private UserRepository userRepository;

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
    public void existsByEmailIsTrue() {
        //actual
        boolean actual = userRepository.existsByEmail("user1@mail.com");

        //assert
        assertTrue(actual);
    }

    @Test
    public void existsByEmailIsFalse() {
        //actual
        boolean actual = userRepository.existsByEmail("user77@mail.com");

        //assert
        assertFalse(actual);
    }

    @Test
    public void existsByNicknameIsTrue() {
        //actual
        boolean actual = userRepository.existsByNickname("user1");

        //assert
        assertTrue(actual);
    }

    @Test
    public void existsByNicknameIsFalse() {
        //actual
        boolean actual = userRepository.existsByNickname("user77");

        //assert
        assertFalse(actual);
    }
}
