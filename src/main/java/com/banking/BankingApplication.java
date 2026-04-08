package com.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.banking", "domain", "application", "infrastructure", "presentation"})
@EntityScan(basePackages = {"domain", "infrastructure"})
@EnableJpaRepositories(basePackages = {"infrastructure", "application"})
@EnableMongoRepositories(basePackages = {"infrastructure", "application"})
public class BankingApplication {
    public static void main(String[] args) {
        SpringApplication.run(BankingApplication.class, args);
    }
}
