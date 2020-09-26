package com.starkend.webfluxtest.user;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("wf")
public class UserController {

    private UserService userService;

    private final Logger LOG = LoggerFactory.getLogger(UserController.class);

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
                .map(user -> ResponseEntity.ok(user))
                .doOnError(e -> LOG.error(String.valueOf(e)));
    }

    @GetMapping("/findUser")
    public Flux<User> findUser(@RequestParam String userName) {
        return userService.findUser(userName)
                .doOnError(e -> LOG.error(String.valueOf(e)));
    }

    @GetMapping("/findUserByPartialName")
    public Flux<User> findUserByPartialName(@RequestParam String userName) {
        return userService.findUserByPartialName(userName)
                .doOnError(e -> LOG.error(String.valueOf(e)));
    }

    @GetMapping("/userExists")
    public Mono<ResponseEntity<Boolean>> userExists(@RequestParam String userId) {
        return userService.userExists(userId)
                .map(exists -> ResponseEntity.ok(exists))
                .doOnError(e -> LOG.error(String.valueOf(e)));
    }

    @GetMapping("userCount")
    public Mono<ResponseEntity<Long>> userCount() {
        return userService.userCount()
                .map(count -> ResponseEntity.ok(count))
                .doOnError(e -> LOG.error(String.valueOf(e)));
    }

    @GetMapping("usersPP")
    public Flux<String> getUsersPP() {
        return userService.getPrettyPrintUsers()
                .doOnError(e -> LOG.error(String.valueOf(e)));
    }
}
