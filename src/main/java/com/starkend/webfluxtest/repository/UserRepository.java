package com.starkend.webfluxtest.repository;

import com.starkend.webfluxtest.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Flux<User> findByName(String name);
}
