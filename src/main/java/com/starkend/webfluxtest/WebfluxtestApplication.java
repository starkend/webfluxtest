package com.starkend.webfluxtest;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class WebfluxtestApplication  {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxtestApplication.class, args);
    }

//    @Override
//    protected String getDatabaseName() {
//        return "reactive";
//    }
//
//    @Bean
//    public MongoClient reactiveMongoClient() {
//        return MongoClients.create();
//    }
}
