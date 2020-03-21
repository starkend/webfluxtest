package com.starkend.webfluxtest.repository;

import com.starkend.webfluxtest.WebfluxtestApplication;
import com.starkend.webfluxtest.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebfluxtestApplication.class)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void whenFindAllByExample_thenSucceed() {
        Flux<User> allUsers = userRepository.findAll(Example.of(new User()));
        List<User> userList = allUsers.collectList().block();
        userList.forEach(user -> System.out.println(user.toString()));
        assertTrue(userList.size() > 0);
    }

    @Test
    public void whenInsertUser_thenSucceed() {
        User user = new User("2", "Carol");
        Mono<User> saveUser = userRepository.save(user);

        assertNotNull(saveUser.block());
    }

    @Test
    public void whenFindAll_thenSucceed() {
        Flux<User> allUsers = userRepository.findAll();
        List<User> userList = allUsers.collectList().block();
        userList.forEach(user -> System.out.println(user.toString()));
        assertTrue(userList.size() > 0);
    }

    @Test
    public void whenFindByName_thenSucceed() {
        Flux<User> users = userRepository.findByName("Bobbie");
        List<User> bobbies = users.collectList().block();
        bobbies.forEach(user -> System.out.println(user.toString()));
        assertTrue(bobbies.size() > 0);
    }

    @Test
    public void whenFindByNameContaining_thenSucceed() {
        Flux<User> users = userRepository.findByNameContaining("Bob");
        List<User> bobs = users.collectList().block();
        bobs.forEach(user -> System.out.println(user.toString()));

        assertTrue(bobs.size() > 1);
    }

    @Test
    public void whenExistsById_thenSucceed() {
        Mono<Boolean> userExists = userRepository.existsById("2");
        assertTrue(userExists.block());
    }

    @Test
    public void whenGetUserCount_thenSucceed() {
        Mono<Long> userCount = userRepository.count();
        assertTrue(userCount.block() > 0);
    }
}
