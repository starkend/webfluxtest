package com.starkend.webfluxtest.service;

import com.starkend.webfluxtest.model.User;
import com.starkend.webfluxtest.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Flux<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Mono<User> saveUser(String userName) {
        return userRepository.save(new User(UUID.randomUUID().toString(), userName));
    }

    public Flux<User> findUser(String userName) {
        return userRepository.findByName(userName);
    }

    public Flux<User> findUserByPartialName(String userName) {
        return userRepository.findByNameContaining(userName);
    }

    public Mono<Boolean> userExists(String userId) {
        return userRepository.existsById(userId);
    }

    public Mono<Long> userCount() {
        return userRepository.count();
    }

    public Flux<String> getPrettyPrintUsers() {
        Flux<User> allUserList = findAllUsers();

        Flux<String> userFlux = allUserList.map(
                user ->
                        String.format("%s - %s ", user.getName(), user.getId())
        );

        return userFlux;

    }

    public Flux<String> getAllUserIds() {
        return findAllUsers().map(
                user ->
                        user.getId()
        );

    }
}
