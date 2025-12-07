package com.pot.app.jenkinsexample;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class JenkinsExampleApplicationTests {

    @Test
    void contextLoads() {
        assertTrue(true);
    }

    @Test
    void testHello() {
        JenkinsExampleApplication app = new JenkinsExampleApplication();
        // Просто проверяем, что класс создается
        assertNotNull(app);
    }

}
