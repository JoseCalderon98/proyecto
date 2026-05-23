package com.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@org.springframework.scheduling.annotation.EnableScheduling
@ComponentScan(basePackages = { "com.banking", "domain", "application", "infrastructure", "presentation" })
@EntityScan(basePackages = { "domain", "application", "infrastructure" })
@EnableJpaRepositories(basePackages = { "application.adapters.persistence.sql.repositories", "application.adapters.persistence.jpa.repositories" })
@EnableMongoRepositories(basePackages = { "application.adapters.persistence.nosql.repositories" })
public class BankingApplication {
    public static void main(String[] args) {
        SpringApplication.run(BankingApplication.class, args);
    }
}
