package com.starkend.webfluxtest.repository;

import com.starkend.webfluxtest.WebfluxtestApplication;
import com.starkend.webfluxtest.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebfluxtestApplication.class)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void whenInsertAndFindUser_thenSucceed() {
        User user = new User("2", "Carol");
        Mono<User> saveUser = userRepository.save(user);

        System.out.println(saveUser.block());

        Mono<User> findUser = userRepository.findById("1");

        System.out.println(findUser.block());
        System.out.println("Done");
    }

    @Test
    public void whenFindAll_thenSucceed() {
        Flux<User> allUsers = userRepository.findAll();
        List<User> userList = allUsers.collectList().block();
        userList.forEach(user -> System.out.println(user.toString()));
    }
}
