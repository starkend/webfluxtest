package com.starkend.webfluxtest.controller;

import com.starkend.webfluxtest.model.User;
import com.starkend.webfluxtest.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("wf")
public class UserController {

    private Logger LOG = LoggerFactory.getLogger(UserController.class);
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public Flux<User> getAllUsers() {
        Flux<User> userFlux = userRepository.findAll();

        return userFlux;
    }

    @GetMapping("/saveUser")
    public Mono<User> saveUser(@RequestParam String userName) {
        Mono<User> saveUser = userRepository.save(new User(UUID.randomUUID().toString(), userName));

        return saveUser;
    }


}
