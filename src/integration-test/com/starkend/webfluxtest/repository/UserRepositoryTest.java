package com.starkend.webfluxtest.repository;

import com.starkend.webfluxtest.WebfluxtestApplication;
import com.starkend.webfluxtest.user.User;
import com.starkend.webfluxtest.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebfluxtestApplication.class)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void whenFindAllByExampleSortByName_thenSucceed() {
        Flux<User> allUsers = userRepository.findAll(Example.of(new User()), Sort.by(Sort.Direction.ASC, "name"));
        verifyFluxIsPopulated(allUsers, 0);
    }

    @Test
    public void whenFindAllByExampleSortById_thenSucceed() {
        Flux<User> allUsers = userRepository.findAll(Example.of(new User()), Sort.by(Sort.Direction.ASC, "id"));
        verifyFluxIsPopulated(allUsers, 0);
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
        verifyFluxIsPopulated(allUsers, 0);
    }

    @Test
    public void whenFindByName_thenSucceed() {
        Flux<User> users = userRepository.findByName("Bobbie");
        verifyFluxIsPopulated(users, 0);
    }

    @Test
    public void whenFindByNameContaining_thenSucceed() {
        Flux<User> users = userRepository.findByNameContaining("Bob");
        verifyFluxIsPopulated(users, 1);
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

    private void verifyFluxIsPopulated(Flux<User> allUsers, int sizeLowerBound) {
        List<User> userList = allUsers.collectList().block();
        userList.forEach(user -> System.out.println(user.toString()));
        assertTrue(userList.size() > sizeLowerBound);
    }
}
