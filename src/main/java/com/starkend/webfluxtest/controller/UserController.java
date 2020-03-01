package com.starkend.webfluxtest.controller;

import com.starkend.webfluxtest.model.User;
import com.starkend.webfluxtest.repository.UserRepository;
import com.starkend.webfluxtest.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("wf")
public class UserController {

    private UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public Flux<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/saveUser")
    public Mono<User> saveUser(@RequestParam String userName) {
        return userService.saveUser(userName);
    }

    @GetMapping("/findUser")
    public Flux<User> findUser(@RequestParam String userName) {
        return userService.findUser(userName);
    }

    @GetMapping("/findUserByPartialName")
    public Flux<User> findUserByPartialName(@RequestParam String userName) {
        return userService.findUserByPartialName(userName);
    }

    @GetMapping("/userExists")
    public Mono<Boolean> userExists(@RequestParam String userId) {
        return userService.userExists(userId);
    }

    @GetMapping("userCount")
    public Mono<Long> userCount() {
        return userService.userCount();
    }
}
