package com.starkend.webfluxtest.user;

import com.starkend.webfluxtest.WebfluxtestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebfluxtestApplication.class)
public class UserServiceTest {

    @Autowired
    UserService userService;

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
}
