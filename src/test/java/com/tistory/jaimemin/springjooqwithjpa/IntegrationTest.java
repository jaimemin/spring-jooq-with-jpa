package com.tistory.jaimemin.springjooqwithjpa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
public class IntegrationTest {

//    @Container
//    private static final MariaDBContainer<?> mariaDBContainer = new MariaDBContainer<>("mariadb:10.9.2")
//            .withDatabaseName("jooq")
//            .withUsername("test")
//            .withPassword("test");

    @Test
    public void yourTest() {
        // Your test logic using mariaDBContainer.getJdbcUrl(), .getUsername(), and .getPassword()
    }
}
