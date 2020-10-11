package com.starkend.webfluxtest.user;

import com.starkend.webfluxtest.WebfluxtestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebfluxtestApplication.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void whenGetPrettyPrintUsers_thenSucceed() {
        List<String> prettyPrintUsers = userService.getPrettyPrintUsers().collectList().block();

        prettyPrintUsers.forEach(s -> System.out.println(s));
        prettyPrintUsers.forEach(s -> assertTrue(s.matches(".+ - .+")));
    }

    @Test
    public void whenGetAllUserIds_thenSucceed() {
        List<String> allUserIds = userService.getAllUserIds().collectList().block();

        allUserIds.forEach(u -> System.out.println(u));
        assertFalse(allUserIds.isEmpty());
    }

    @Test
    public void whenGetUsersByNameOnly_thenSucceed() {
        List<String> matchingUsers = userService.getUsersByPartialNameOnly("Bob").collectList().block();

        matchingUsers.forEach(u -> System.out.println(u));
        assertFalse(matchingUsers.isEmpty());
    }

    @Test
    public void whenSaveUser_thenSucceed() {
        String USER_NAME = "Bort";
        Mono<User> user = userService.saveUser(USER_NAME);
        assertTrue(USER_NAME.equalsIgnoreCase(user.block().getName()));
    }

    @Test
    public void whenDeleteUser_thenSucceed() {
        String USER_NAME = "Delete User1";
        Mono<User> userMono = userService.saveUser(USER_NAME);
        User user = userMono.block();
        assertTrue(USER_NAME.equalsIgnoreCase(user.getName()));
        Mono<Void> voidUser = userService.deleteUserById(user.getId());
        assertFalse(userService.userExists(USER_NAME).block());
    }
}
