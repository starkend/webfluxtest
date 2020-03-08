package com.starkend.webfluxtest.controller;

import com.starkend.webfluxtest.model.User;
import com.starkend.webfluxtest.repository.UserRepository;
import com.starkend.webfluxtest.service.UserService;
import org.springframework.http.ResponseEntity;
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
    public Mono<ResponseEntity<User>> saveUser(@RequestParam String userName) {
        return userService.saveUser(userName)
                .map(user -> ResponseEntity.ok(user));
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
    public Mono<ResponseEntity<Boolean>> userExists(@RequestParam String userId) {
        return userService.userExists(userId)
                .map(exists -> ResponseEntity.ok(exists));
    }

    @GetMapping("userCount")
    public Mono<ResponseEntity<Long>> userCount() {
        return userService.userCount()
                .map(count -> ResponseEntity.ok(count));
    }

    @GetMapping("usersPP")
    public Flux<String> getUsersPP() {
        return userService.getPrettyPrintUsers();
    }
}
