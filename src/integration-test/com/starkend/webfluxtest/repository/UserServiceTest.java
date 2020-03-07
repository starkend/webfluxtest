package com.starkend.webfluxtest.repository;

import com.starkend.webfluxtest.WebfluxtestApplication;
import com.starkend.webfluxtest.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebfluxtestApplication.class)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void whenGetPrettyPrintUsers_thenSucceed() {
//        List<String> prettyPrintUsers = userService.getPrettyPrintUsers();
//
//        prettyPrintUsers.forEach(s -> System.out.println(s));
//
//        prettyPrintUsers.forEach(s -> assertTrue(s.matches(".+ - .+")));
    }

}
