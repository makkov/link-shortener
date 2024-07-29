package com.colvir.link.shortener.repository;

import com.colvir.link.shortener.model.Link;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.containers.PostgreSQLContainer;

import static com.colvir.link.shortener.generator.LinkGenerator.generateLinkBeforePersist;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class LinkRepositoryTest {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:14-alpine3.17");

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @Autowired
    private LinkRepository linkRepository;

    @Test
    void findByShorted() {
        //Подгоовка входных данных
        Link firstLink = generateLinkBeforePersist()
                .build();

        Link secondLink = generateLinkBeforePersist()
                .setOriginal("https://colvir.com")
                .setShorted("sorted link")
                .build();

        //Подготовка ожидаемого резульатата
        firstLink = linkRepository.save(firstLink);
        secondLink = linkRepository.save(secondLink);

        //Начало теста
        Link expectedFirstLink = linkRepository.findByShorted(firstLink.getShorted());
        Link expectedSecondLink = linkRepository.findByShorted(secondLink.getShorted());
        assertEquals(expectedFirstLink, firstLink);
        assertEquals(expectedSecondLink, secondLink);
        System.out.println(linkRepository.findAll());
    }
}
