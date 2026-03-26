package dev.springforge.t3_03;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Exercise 2: Dual Data Store Configuration
 *
 * YOUR TASKS:
 * 1. Add @EnableJpaRepositories for the JPA repositories in this package
 *    Hint: @EnableJpaRepositories(basePackages = "dev.springforge.t3_03")
 * 2. Add @EnableMongoRepositories for the MongoDB repositories in this package
 *    Hint: @EnableMongoRepositories(basePackages = "dev.springforge.t3_03")
 *
 * WHY THIS IS NEEDED:
 * When both JPA and MongoDB starters are on the classpath, Spring enters
 * "strict repository configuration mode." It uses annotations on the domain
 * class (@Entity vs @Document) AND the repository type (JpaRepository vs
 * MongoRepository) to decide which data store handles which repository.
 *
 * Explicit @Enable* annotations ensure there's no ambiguity.
 */
@Configuration
// TODO: Add @EnableJpaRepositories(basePackages = "dev.springforge.t3_03")
// TODO: Add @EnableMongoRepositories(basePackages = "dev.springforge.t3_03")
public class DualStoreConfig {
}
